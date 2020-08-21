
package com.microfocus.silkcentral.webservices.requirements.stubs;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "RequirementsServiceService", targetNamespace = "http://www.microfocus.com/silkcentral/webservices/requirements", wsdlLocation = "http://localhost:19120/Services1.0/jaxws/requirements?wsdl")
public class RequirementsServiceService
    extends Service
{

    private final static URL REQUIREMENTSSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException REQUIREMENTSSERVICESERVICE_EXCEPTION;
    private final static QName REQUIREMENTSSERVICESERVICE_QNAME = new QName("http://www.microfocus.com/silkcentral/webservices/requirements", "RequirementsServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:19120/Services1.0/jaxws/requirements?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        REQUIREMENTSSERVICESERVICE_WSDL_LOCATION = url;
        REQUIREMENTSSERVICESERVICE_EXCEPTION = e;
    }

    public RequirementsServiceService() {
        super(__getWsdlLocation(), REQUIREMENTSSERVICESERVICE_QNAME);
    }

    public RequirementsServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), REQUIREMENTSSERVICESERVICE_QNAME, features);
    }

    public RequirementsServiceService(URL wsdlLocation) {
        super(wsdlLocation, REQUIREMENTSSERVICESERVICE_QNAME);
    }

    public RequirementsServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, REQUIREMENTSSERVICESERVICE_QNAME, features);
    }

    public RequirementsServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RequirementsServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns RequirementsService
     */
    @WebEndpoint(name = "RequirementsServicePort")
    public RequirementsService getRequirementsServicePort() {
        return super.getPort(new QName("http://www.microfocus.com/silkcentral/webservices/requirements", "RequirementsServicePort"), RequirementsService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns RequirementsService
     */
    @WebEndpoint(name = "RequirementsServicePort")
    public RequirementsService getRequirementsServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.microfocus.com/silkcentral/webservices/requirements", "RequirementsServicePort"), RequirementsService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (REQUIREMENTSSERVICESERVICE_EXCEPTION!= null) {
            throw REQUIREMENTSSERVICESERVICE_EXCEPTION;
        }
        return REQUIREMENTSSERVICESERVICE_WSDL_LOCATION;
    }

}
