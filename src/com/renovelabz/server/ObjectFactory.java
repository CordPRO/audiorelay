
package com.renovelabz.server;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.renovelabz.server package. 
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

    private final static QName _GetFrameBufferResponse_QNAME = new QName("http://server.renovelabz.com/", "getFrameBufferResponse");
    private final static QName _GetFrameBuffer_QNAME = new QName("http://server.renovelabz.com/", "getFrameBuffer");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.renovelabz.server
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetFrameBufferResponse }
     * 
     */
    public GetFrameBufferResponse createGetFrameBufferResponse() {
        return new GetFrameBufferResponse();
    }

    /**
     * Create an instance of {@link GetFrameBuffer }
     * 
     */
    public GetFrameBuffer createGetFrameBuffer() {
        return new GetFrameBuffer();
    }

    /**
     * Create an instance of {@link FrameBuffer }
     * 
     */
    public FrameBuffer createFrameBuffer() {
        return new FrameBuffer();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFrameBufferResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.renovelabz.com/", name = "getFrameBufferResponse")
    public JAXBElement<GetFrameBufferResponse> createGetFrameBufferResponse(GetFrameBufferResponse value) {
        return new JAXBElement<GetFrameBufferResponse>(_GetFrameBufferResponse_QNAME, GetFrameBufferResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFrameBuffer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.renovelabz.com/", name = "getFrameBuffer")
    public JAXBElement<GetFrameBuffer> createGetFrameBuffer(GetFrameBuffer value) {
        return new JAXBElement<GetFrameBuffer>(_GetFrameBuffer_QNAME, GetFrameBuffer.class, null, value);
    }

}
