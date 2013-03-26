package org.framework42.rss.services;

import org.framework42.model.users.User;
import org.framework42.rss.model.RSSFeed;
import org.framework42.rss.model.RSSItem;
import org.framework42.rss.model.impl.RSSFeedImpl;
import org.framework42.rss.model.impl.RSSItemImpl;
import org.framework42.services.ProxyService;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RSSFeedReaderImpl extends ProxyService<RSSFeedReaderImpl> implements RSSFeedReader {

    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);

    public RSSFeedReaderImpl() {
        super("org.framework42.rss.services");
    }

    public static void main(String[] args) {

        //RSSFeed feed = new RSSFeedReaderImpl().findNewItems(null, "http://cyber.law.harvard.edu/rss/examples/rss2sample.xml", new GregorianCalendar(2000,0,1).getTime());
        RSSFeed feed = new RSSFeedReaderImpl().findNewItems(null, "http://www.svd.se/?service=rss&amp;type=senastenytt", new GregorianCalendar(2013,2,26,14,0).getTime());
    }

    @Override
    public RSSFeed findNewItems(User invocationUser, String url, Date lastCheckDate) {

        RSSFeed feed = null;

        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(url);

            doc.getDocumentElement().normalize();

            //System.out.println("rss version: "+doc.getElementsByTagName("rss").item(0).getAttributes().getNamedItem("version").getNodeValue());

            NodeList childNodes = doc.getChildNodes().item(0).getChildNodes().item(1).getChildNodes();

            String title = "";
            String link = "";
            String description = "";

            List<RSSItem> foundList = new ArrayList<RSSItem>();

            for(int i=0; i<childNodes.getLength();i++) {

                Node node = childNodes.item(i);

                if("title".equalsIgnoreCase(node.getNodeName())) {
                    title = node.getTextContent();
                } else if("link".equalsIgnoreCase(node.getNodeName())) {
                    link = node.getTextContent();
                } else if("description".equalsIgnoreCase(node.getNodeName())) {
                    description = node.getTextContent();
                }

                else if("item".equalsIgnoreCase(node.getNodeName())) {

                    String itemTitle = "";
                    String itemLink = "";
                    String itemDescription = "";
                    String itemAuthor = "";
                    Date itemPubDate = new Date();

                    NodeList itemList = childNodes.item(i).getChildNodes();

                    for(int iChild=0; iChild<itemList.getLength();iChild++) {

                        if("title".equalsIgnoreCase(itemList.item(iChild).getNodeName())) {
                            itemTitle = itemList.item(iChild).getTextContent();
                        } else if("link".equalsIgnoreCase(itemList.item(iChild).getNodeName())) {
                            itemLink = itemList.item(iChild).getTextContent();
                        } else if("description".equalsIgnoreCase(itemList.item(iChild).getNodeName())) {
                            itemDescription = itemList.item(iChild).getTextContent();
                        } else if("author".equalsIgnoreCase(itemList.item(iChild).getNodeName())) {
                            itemAuthor = itemList.item(iChild).getTextContent();
                        } else if("pubDate".equalsIgnoreCase(itemList.item(iChild).getNodeName())) {
                            try {
                                itemPubDate = dateFormat.parse(itemList.item(iChild).getTextContent());
                            } catch(ParseException e) {
                                logger.error(e.getMessage());
                            }
                        }
                    }

                    if(lastCheckDate.before(itemPubDate)) {
                        foundList.add(new RSSItemImpl(
                                itemTitle,
                                itemLink,
                                itemDescription,
                                itemAuthor,
                                itemPubDate
                        ));
                    }
                }

            }

            feed = new RSSFeedImpl(
                    title,
                    link,
                    description,
                    foundList
            );

        } catch (ParserConfigurationException e) {

            logger.error("RSSFeedReaderImpl.findNewItems"+e.getMessage());

        } catch (IOException e) {

            logger.error("RSSFeedReaderImpl.findNewItems"+e.getMessage());

        } catch (SAXException e) {

            logger.error("RSSFeedReaderImpl.findNewItems"+e.getMessage());
        }

        System.out.println(feed.toString());

        return feed;
    }
}
