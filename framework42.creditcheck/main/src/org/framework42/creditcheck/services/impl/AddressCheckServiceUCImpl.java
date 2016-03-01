package org.framework42.creditcheck.services.impl;

import org.framework42.creditcheck.exceptions.AddressCheckException;
import org.framework42.creditcheck.model.*;
import org.framework42.creditcheck.model.impl.AddressCheckResponseImpl;
import org.framework42.creditcheck.model.impl.CreditBureauContextImpl;
import org.framework42.creditcheck.parsers.uc.ApplicantParser;
import org.framework42.creditcheck.services.AddressCheckService;
import org.framework42.address.model.TrustedAddress;
import org.framework42.services.ProxyService;
import uc_webservice.*;

public class AddressCheckServiceUCImpl extends ProxyService<AddressCheckServiceUCImpl> implements AddressCheckService {

    public static void main(String[] args) throws AddressCheckException {

        CreditBureauContext context = new CreditBureauContextImpl(0, CreditBureau.UC, "UC", "KOYT2", "X0", "UC", "3", "MGS");

        new AddressCheckServiceUCImpl().getAddress(context, "7511133519");
    }

    public AddressCheckServiceUCImpl() {
        super("org.framework42.creditcheck.services");
    }

    public AddressCheckResponse getAddress(CreditBureauContext context, String governmentId) throws AddressCheckException {

        UcOrders orders = new UCOrderService().getUcOrders2();

        UcReply ucReply = orders.identityCheck(createIdentityCheck(context, governmentId));

        if("error".equalsIgnoreCase(ucReply.getStatus().getResult())) {

            if("101".equalsIgnoreCase(ucReply.getStatus().getMessage().getId())) {

                throw new AddressCheckException("Government ID not valid!"+ucReply.getStatus().getMessage().getId()+" - "+ucReply.getStatus().getMessage().getValue());
            }

            throw new AddressCheckException("Error in communication with address provider! "+ucReply.getStatus().getMessage().getId()+" - "+ucReply.getStatus().getMessage().getValue());

        } else {

            Report report = ucReply.getUcReport().get(0).getXmlReply().getReports().get(0).getReport().get(0);

            TrustedAddress address = ApplicantParser.INSTANCE.createAddress(report.getGroup().get(0));

            AddressStatus status = AddressStatus.OK;

            for(Term term: report.getGroup().get(0).getTerm()) {

                if("W08020".equalsIgnoreCase(term.getId())) {

                    if("03".equalsIgnoreCase(term.getValue())) {

                        status = AddressStatus.EMIGRATED;
                        
                    } else if("05".equalsIgnoreCase(term.getValue())) {

                        status = AddressStatus.DECEASED;

                    } else if("06".equalsIgnoreCase(term.getValue())) {

                        status = AddressStatus.OTHER;

                    } else if("08".equalsIgnoreCase(term.getValue())) {

                        status = AddressStatus.OTHER;

                    } else if("09".equalsIgnoreCase(term.getValue())) {

                        status = AddressStatus.OTHER;

                    } else if("11".equalsIgnoreCase(term.getValue())) {

                        status = AddressStatus.LOST_ID;

                    }
                }

                /*if("W08020".equalsIgnoreCase(term.getId()) && !"11".equalsIgnoreCase(term.getValue())) {

                        throw new AddressCheckException("Government ID locked in some way.");
                }*/
            }

            ApplicantNames names = ApplicantParser.INSTANCE.createApplicantNamesAutomaticCorrection(report.getGroup().get(0));

            return new AddressCheckResponseImpl(status, names.getFirstName(), names.getSurname(), names.getFullName(), address);
        }

    }

    private IdentityCheck createIdentityCheck(CreditBureauContext context, String governmentId) {

        IdentityCheck identityCheck = new IdentityCheck();

        Customer ucCustomer = new Customer();
        ucCustomer.setUserId(context.getUserId());
        ucCustomer.setName(context.getName());
        ucCustomer.setPassword(context.getPassword());

        identityCheck.setCustomer(ucCustomer);

        identityCheck.setIdentifier(governmentId);

        //identityCheck.setVersion("");

        return identityCheck;
    }

}
