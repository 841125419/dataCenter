
package com.kwantler.interchange.wsdl.service.invoke.hello;

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
@WebServiceClient(name = "JwsServiceHelloService", targetNamespace = "http://hello.service.wsdl.interchange.kwantler.com/", wsdlLocation = "http://127.0.0.1:8080/Service/ServiceHello?wsdl")
public class JwsServiceHelloService
    extends Service
{

    private final static URL JWSSERVICEHELLOSERVICE_WSDL_LOCATION;
    private final static WebServiceException JWSSERVICEHELLOSERVICE_EXCEPTION;
    private final static QName JWSSERVICEHELLOSERVICE_QNAME = new QName("http://hello.service.wsdl.interchange.kwantler.com/", "JwsServiceHelloService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://127.0.0.1:8080/Service/ServiceHello?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        JWSSERVICEHELLOSERVICE_WSDL_LOCATION = url;
        JWSSERVICEHELLOSERVICE_EXCEPTION = e;
    }

    public JwsServiceHelloService() {
        super(__getWsdlLocation(), JWSSERVICEHELLOSERVICE_QNAME);
    }

    public JwsServiceHelloService(WebServiceFeature... features) {
        super(__getWsdlLocation(), JWSSERVICEHELLOSERVICE_QNAME, features);
    }

    public JwsServiceHelloService(URL wsdlLocation) {
        super(wsdlLocation, JWSSERVICEHELLOSERVICE_QNAME);
    }

    public JwsServiceHelloService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, JWSSERVICEHELLOSERVICE_QNAME, features);
    }

    public JwsServiceHelloService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public JwsServiceHelloService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns JwsServiceHello
     */
    @WebEndpoint(name = "JwsServiceHelloPort")
    public JwsServiceHello getJwsServiceHelloPort() {
        return super.getPort(new QName("http://hello.service.wsdl.interchange.kwantler.com/", "JwsServiceHelloPort"), JwsServiceHello.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns JwsServiceHello
     */
    @WebEndpoint(name = "JwsServiceHelloPort")
    public JwsServiceHello getJwsServiceHelloPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://hello.service.wsdl.interchange.kwantler.com/", "JwsServiceHelloPort"), JwsServiceHello.class, features);
    }

    private static URL __getWsdlLocation() {
        if (JWSSERVICEHELLOSERVICE_EXCEPTION!= null) {
            throw JWSSERVICEHELLOSERVICE_EXCEPTION;
        }
        return JWSSERVICEHELLOSERVICE_WSDL_LOCATION;
    }

}
