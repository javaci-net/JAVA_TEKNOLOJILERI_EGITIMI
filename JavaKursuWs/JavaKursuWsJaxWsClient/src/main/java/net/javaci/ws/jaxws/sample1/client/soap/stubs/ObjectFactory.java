
package net.javaci.ws.jaxws.sample1.client.soap.stubs;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the net.javaci.ws.jaxws.sample1.client.soap.stubs package. 
 * &lt;p&gt;An ObjectFactory allows you to programatically 
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

    private final static QName _GetIt_QNAME = new QName("http://server.sample1.jaxws.ws.javaci.net/", "getIt");
    private final static QName _GetItResponse_QNAME = new QName("http://server.sample1.jaxws.ws.javaci.net/", "getItResponse");
    private final static QName _Salute_QNAME = new QName("http://server.sample1.jaxws.ws.javaci.net/", "salute");
    private final static QName _SaluteResponse_QNAME = new QName("http://server.sample1.jaxws.ws.javaci.net/", "saluteResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: net.javaci.ws.jaxws.sample1.client.soap.stubs
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetIt }
     * 
     */
    public GetIt createGetIt() {
        return new GetIt();
    }

    /**
     * Create an instance of {@link GetItResponse }
     * 
     */
    public GetItResponse createGetItResponse() {
        return new GetItResponse();
    }

    /**
     * Create an instance of {@link Salute }
     * 
     */
    public Salute createSalute() {
        return new Salute();
    }

    /**
     * Create an instance of {@link SaluteResponse }
     * 
     */
    public SaluteResponse createSaluteResponse() {
        return new SaluteResponse();
    }

    /**
     * Create an instance of {@link SalutationRequest }
     * 
     */
    public SalutationRequest createSalutationRequest() {
        return new SalutationRequest();
    }

    /**
     * Create an instance of {@link SalutationResponse }
     * 
     */
    public SalutationResponse createSalutationResponse() {
        return new SalutationResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetIt }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetIt }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.sample1.jaxws.ws.javaci.net/", name = "getIt")
    public JAXBElement<GetIt> createGetIt(GetIt value) {
        return new JAXBElement<GetIt>(_GetIt_QNAME, GetIt.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetItResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetItResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.sample1.jaxws.ws.javaci.net/", name = "getItResponse")
    public JAXBElement<GetItResponse> createGetItResponse(GetItResponse value) {
        return new JAXBElement<GetItResponse>(_GetItResponse_QNAME, GetItResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Salute }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Salute }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.sample1.jaxws.ws.javaci.net/", name = "salute")
    public JAXBElement<Salute> createSalute(Salute value) {
        return new JAXBElement<Salute>(_Salute_QNAME, Salute.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaluteResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SaluteResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.sample1.jaxws.ws.javaci.net/", name = "saluteResponse")
    public JAXBElement<SaluteResponse> createSaluteResponse(SaluteResponse value) {
        return new JAXBElement<SaluteResponse>(_SaluteResponse_QNAME, SaluteResponse.class, null, value);
    }

}
