
package com.kwantler.wsdl.service.invoke.hello;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.kwantler.interchange.wsdl.service.hello.clientInvoke package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetUsersResponse_QNAME = new QName("http://hello.service.wsdl.interchange.kwantler.com/", "getUsersResponse");
    private final static QName _GetValueResponse_QNAME = new QName("http://hello.service.wsdl.interchange.kwantler.com/", "getValueResponse");
    private final static QName _GetUsers_QNAME = new QName("http://hello.service.wsdl.interchange.kwantler.com/", "getUsers");
    private final static QName _GetValue_QNAME = new QName("http://hello.service.wsdl.interchange.kwantler.com/", "getValue");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.kwantler.interchange.wsdl.service.hello.clientInvoke
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetValue }
     * 
     */
    public GetValue createGetValue() {
        return new GetValue();
    }

    /**
     * Create an instance of {@link GetUsers }
     * 
     */
    public GetUsers createGetUsers() {
        return new GetUsers();
    }

    /**
     * Create an instance of {@link GetValueResponse }
     * 
     */
    public GetValueResponse createGetValueResponse() {
        return new GetValueResponse();
    }

    /**
     * Create an instance of {@link GetUsersResponse }
     * 
     */
    public GetUsersResponse createGetUsersResponse() {
        return new GetUsersResponse();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hello.service.wsdl.interchange.kwantler.com/", name = "getUsersResponse")
    public JAXBElement<GetUsersResponse> createGetUsersResponse(GetUsersResponse value) {
        return new JAXBElement<GetUsersResponse>(_GetUsersResponse_QNAME, GetUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetValueResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hello.service.wsdl.interchange.kwantler.com/", name = "getValueResponse")
    public JAXBElement<GetValueResponse> createGetValueResponse(GetValueResponse value) {
        return new JAXBElement<GetValueResponse>(_GetValueResponse_QNAME, GetValueResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hello.service.wsdl.interchange.kwantler.com/", name = "getUsers")
    public JAXBElement<GetUsers> createGetUsers(GetUsers value) {
        return new JAXBElement<GetUsers>(_GetUsers_QNAME, GetUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetValue }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hello.service.wsdl.interchange.kwantler.com/", name = "getValue")
    public JAXBElement<GetValue> createGetValue(GetValue value) {
        return new JAXBElement<GetValue>(_GetValue_QNAME, GetValue.class, null, value);
    }

}
