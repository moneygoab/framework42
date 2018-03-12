package org.framework42.creditcheck.services.impl;

import com.sun.org.apache.xerces.internal.dom.DeferredElementImpl;
import org.framework42.address.model.InformationProvider;
import org.framework42.address.model.PostalCodeFormat;
import org.framework42.address.model.TrustedAddress;
import org.framework42.address.model.impl.PostalCodeImpl;
import org.framework42.address.model.impl.SimpleSecureAddressImpl;
import org.framework42.creditcheck.exceptions.AddressCheckException;
import org.framework42.creditcheck.model.AddressCheckResponse;
import org.framework42.creditcheck.model.AddressStatus;
import org.framework42.creditcheck.model.CreditBureau;
import org.framework42.creditcheck.model.CreditBureauContext;
import org.framework42.creditcheck.model.impl.AddressCheckResponseImpl;
import org.framework42.creditcheck.model.impl.CreditBureauContextImpl;
import org.framework42.creditcheck.services.AddressCheckService;
import org.framework42.model.Country;
import org.framework42.services.ProxyService;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddressCheckServiceSynaImpl extends ProxyService<AddressCheckServiceSynaImpl> implements AddressCheckService {

    public AddressCheckServiceSynaImpl() {
        super("org.framework42.creditcheck");
    }

    public static void main(String[] args) throws Exception {

        new AddressCheckServiceSynaImpl().getAddress(new CreditBureauContextImpl(0, CreditBureau.SYNA, "Syna", "500317", "apigo", "Syna Adressfråga", "policy", "83.68.231.218", false, false), "7511133510");
    }

    @Override
    public AddressCheckResponse getAddress(CreditBureauContext context, String governmentId) throws AddressCheckException {

        AddressCheckResponse resp;

        AddressStatus status = AddressStatus.OTHER;
        String firstName = "";
        String surname = "";
        String fullName = "";

        TrustedAddress address = new SimpleSecureAddressImpl();

        try {

            String urlParameters = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?><Fraga><Produkt id=\"BFbokf\" ver=\"1.2\"><Objektlista antal=\"1\"><Objekt><Idnr>"+governmentId+"</Idnr></Objekt></Objektlista></Produkt><Process timestamp=\"\"  timeout=\"\" /><Kund nr=\""+context.getUserId()+"\" anv=\""+context.getPassword()+"\" ipaddress=\""+context.getPolicyRules()+"\" /></Fraga>";

            URL url = new URL("https://arkivet.syna.se");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=iso-8859-1");

            con.setRequestProperty("Content-Length",
                    Integer.toString(urlParameters.getBytes().length));
            con.setRequestProperty("Content-Language", "en-US");

            con.setUseCaches(false);
            con.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();

            //Get Response
            InputStream is = con.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            try {

                builder = factory.newDocumentBuilder();
                Document document = builder.parse( new InputSource( new StringReader( response.toString() ) ) );

                Node ansNode = findSubNode("svar", document);
                if("OK".equalsIgnoreCase(((DeferredElementImpl) ansNode).getAttribute("ec"))) {

                    status = AddressStatus.OK;

                    Node object = findSubNode("Omfragad", document);

                    if(((DeferredElementImpl) object).getAttribute("not").contains("avliden")) {

                        status = AddressStatus.DECEASED;

                    } else if(((DeferredElementImpl) object).getAttribute("not").contains("Utvandrad")) {

                        status = AddressStatus.EMIGRATED;

                    } else {

                        Node nameList = findSubNode("Namnlista", document);

                        for(int i=0; i<nameList.getChildNodes().getLength(); i++) {

                            Node nameNode =  nameList.getChildNodes().item(i);


                            fullName = nameNode.getTextContent().split("\\,")[1];
                            surname = nameNode.getTextContent().split("\\,")[0];

                            if (((DeferredElementImpl) nameNode).hasAttribute("tilltal")) {

                                firstName = ((DeferredElementImpl) nameNode).getAttribute("tilltal");
                            } else {

                                firstName = fullName;
                            }

                            break;
                        }

                        Node addressList = findSubNode("Adresslista", document);

                        for(int i=0; i<addressList.getChildNodes().getLength(); i++) {

                            Node addressNode =  addressList.getChildNodes().item(i);

                            if ("fbf".equalsIgnoreCase(((DeferredElementImpl) addressNode).getAttribute("typ"))) {

                                String careOf = "";
                                if(findSubNode("Coadress", addressNode)!=null) {
                                    careOf = findSubNode("Coadress", addressNode).getTextContent();
                                }

                                address = new SimpleSecureAddressImpl(
                                        0,
                                        firstName+" "+surname,
                                        careOf,
                                        findSubNode("Gatabox", addressNode).getTextContent(),
                                        new PostalCodeImpl(PostalCodeFormat.getByCountry(Country.SWEDEN), findSubNode("Postnr", addressNode).getTextContent()),
                                        findSubNode("Postort", addressNode).getTextContent(),
                                        Country.SWEDEN,
                                        InformationProvider.POPULATION_REGISTERS
                                );
                            }
                        }
                    }
                } else {

                    String errorMess = "";

                    Node errorNode = findSubNode("Error", document);
                    for(int i=0; i<errorNode.getChildNodes().getLength();i++) {

                        errorMess += " - "+ errorNode.getChildNodes().item(i).getTextContent();
                    }

                    throw new AddressCheckException("Error in communication with address provider Syna! "+errorMess, false);
                }

                System.out.println(document.toString());

                /*
                <?xml version="1.0" encoding="ISO-8859-1"?>
                <Svar ec="OK">
                    <Produkt id="BFbokf" ver="1.2"></Produkt>
                    <Process prddebug="0" debug="0" trace="0"></Process>
                    <Objektlista antal="1">
                        <Objekt>
                            <Omfragad id="7511133519" not="" fbfdatum="20170404">
                                <Namnlista>
                                    <Namn tilltal="Fredrik">Gustavsson, Bernt Fredrik Daniel</Namn>
                                </Namnlista>
                                <Adresslista>
                                    <Adress typ="fbf" not="">
                                        <Gatabox>Ritaregatan 1</Gatabox>
                                        <Postnr>25656</Postnr>
                                        <Postort>Helsingborg</Postort>
                                        <Lan kod="12">Skåne län</Lan>
                                        <Kommun kod="83">Helsingborg</Kommun>
                                        <Landsdel kod="1">Götaland</Landsdel>
                                        <Landskap kod="01">Skåne</Landskap>
                                        <Distrikt kod="254">Raus</Distrikt>
                                    </Adress>
                                </Adresslista>
                            </Omfragad>
                        </Objekt>
                    </Objektlista>
                </Svar>

            FEL i anrop!
                <?xml version="1.0" encoding="ISO-8859-1"?>
                <Svar ec="E ">
                    <Produkt id="BFbokf" ver="1.2"></Produkt>
                    <Process prddebug="0" debug="0" trace="0"></Process>
                    <Objektlista antal="1"></Objektlista>
                    <Error>
                        <Msg id="XML8001" sev="E" runid="1">XML internal error:  Error &quot;E&quot; severity prohibited xml output for runid: 1</Msg>
                        <Msg id="STD0001" sev="E" runid="1">Det finns ingen person folkbokförd i Sverige, 16 år eller äldre, med personnummer 751113-3510</Msg>
                    </Error>
                </Svar>

            Avliden person
                <?xml version="1.0" encoding="ISO-8859-1"?>
                <Svar ec="OK">
                    <Produkt id="BFbokf" ver="1.2"></Produkt>
                    <Process prddebug="0" debug="0" trace="0"></Process>
                    <Objektlista antal="1">
                        <Objekt>
                            <Omfragad id="4307280109" not="Personen är avliden 2016-07-19">
                                <Namnlista>
                                    <Namn tilltal="">Zayer, Fatma Kadim</Namn>
                                </Namnlista>
                                <Adresslista>
                                    <Adress typ="fbf" not="Senast kända folkbokföringsuppgifter:">
                                        <Gatabox>Karl Hovbergsgatan 41 A lgh 1403</Gatabox>
                                        <Postnr>63353</Postnr>
                                        <Postort>Eskilstuna</Postort>
                                        <Lan kod="04">Södermanlands län</Lan>
                                        <Kommun kod="84">Eskilstuna</Kommun>
                                        <Landsdel kod="2">Svealand</Landsdel>
                                        <Landskap kod="12">Södermanland</Landskap>
                                        <Distrikt kod="111">Eskilstuna Kloster</Distrikt>
                                    </Adress>
                                </Adresslista>
                            </Omfragad>
                        </Objekt>
                    </Objektlista>
                </Svar>

            Utvandrad person
                <?xml version="1.0" encoding="ISO-8859-1"?>
                <Svar ec="OK">
                    <Produkt id="BFbokf" ver="1.2"></Produkt>
                    <Process prddebug="0" debug="0" trace="0"></Process>
                    <Objektlista antal="1">
                        <Objekt>
                            <Omfragad id="6801208635" not="Utvandrad eller avregistrerad av annat skäl" fbfdatum="20121001">
                                <Namnlista>
                                    <Namn tilltal="Avelino">Peixoto Ribeiro, Avelino Jorge</Namn>
                                </Namnlista>
                                <Adresslista>
                                    <Adress typ="fbf" not="Senast kända folkbokföringsuppgifter:">
                                        <Gatabox>Oxelstigen 14</Gatabox>
                                        <Postnr>43246</Postnr>
                                        <Postort>Varberg</Postort>
                                        <Lan kod="13">Hallands län</Lan>
                                        <Kommun kod="83">Varberg</Kommun>
                                        <Forsamling kod="01">Varberg</Forsamling>
                                        <Landsdel kod=""></Landsdel>
                                        <Landskap kod=""></Landskap>
                                        <Distrikt kod=""></Distrikt>
                                    </Adress>
                                    <Adress typ="spu" not="">
                                        <Coadress>Rua Das Boucinhas 18</Coadress>
                                        <Fortsadress>4610-254 Felgueiras</Fortsadress>
                                        <Land>Portugal</Land>
                                    </Adress>
                                </Adresslista>
                            </Omfragad>
                        </Objekt>
                    </Objektlista>
                </Svar>

                */

            } catch (ParserConfigurationException  | SAXException e) {

                logger.error(e.getMessage());
            }



        } catch (IOException e ) {

            logger.fatal("AddressCheckServiceSynaImpl.getAddress: "+ e.getMessage());
        }


        resp = new AddressCheckResponseImpl(status, firstName, surname, fullName, address);

        return resp;
    }

    public Node findSubNode(String name, Document doc) {

        NodeList list = doc.getChildNodes();
        for(int i=0; i<list.getLength(); i++) {

            Node node = list.item(i);

            if(node.getNodeName().equalsIgnoreCase(name)) {

                return node;
            }

            Node foundNode = findSubNode(name, node);

            if(foundNode!=null) {

                return foundNode;
            }
        }

        return null;
    }

    public Node findSubNode(String name, Node startNode) {

        if(startNode.getNodeName().equalsIgnoreCase(name)) {

            return startNode;
        }

        NodeList list = startNode.getChildNodes();
        for(int i=0; i<list.getLength(); i++) {

            Node node = list.item(i);

            if(node.getNodeName().equalsIgnoreCase(name)) {

                return node;
            }

            Node foundNode = findSubNode(name, node);

            if(foundNode!=null) {

                return foundNode;
            }

        }

        return null;
    }


}
