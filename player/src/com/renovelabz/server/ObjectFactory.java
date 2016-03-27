
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

    private final static QName _PushFrameResponse_QNAME = new QName("http://server.renovelabz.com/", "pushFrameResponse");
    private final static QName _PullFramesResponse_QNAME = new QName("http://server.renovelabz.com/", "pullFramesResponse");
    private final static QName _StartNewSegment_QNAME = new QName("http://server.renovelabz.com/", "startNewSegment");
    private final static QName _GetSegmentIDResponse_QNAME = new QName("http://server.renovelabz.com/", "getSegmentIDResponse");
    private final static QName _StartNewSegmentResponse_QNAME = new QName("http://server.renovelabz.com/", "startNewSegmentResponse");
    private final static QName _GetSegmentID_QNAME = new QName("http://server.renovelabz.com/", "getSegmentID");
    private final static QName _PushFrame_QNAME = new QName("http://server.renovelabz.com/", "pushFrame");
    private final static QName _PullFrames_QNAME = new QName("http://server.renovelabz.com/", "pullFrames");
    private final static QName _PushFrameArg0_QNAME = new QName("", "arg0");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.renovelabz.server
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PullFrames }
     * 
     */
    public PullFrames createPullFrames() {
        return new PullFrames();
    }

    /**
     * Create an instance of {@link StartNewSegmentResponse }
     * 
     */
    public StartNewSegmentResponse createStartNewSegmentResponse() {
        return new StartNewSegmentResponse();
    }

    /**
     * Create an instance of {@link GetSegmentID }
     * 
     */
    public GetSegmentID createGetSegmentID() {
        return new GetSegmentID();
    }

    /**
     * Create an instance of {@link PushFrame }
     * 
     */
    public PushFrame createPushFrame() {
        return new PushFrame();
    }

    /**
     * Create an instance of {@link StartNewSegment }
     * 
     */
    public StartNewSegment createStartNewSegment() {
        return new StartNewSegment();
    }

    /**
     * Create an instance of {@link GetSegmentIDResponse }
     * 
     */
    public GetSegmentIDResponse createGetSegmentIDResponse() {
        return new GetSegmentIDResponse();
    }

    /**
     * Create an instance of {@link PushFrameResponse }
     * 
     */
    public PushFrameResponse createPushFrameResponse() {
        return new PushFrameResponse();
    }

    /**
     * Create an instance of {@link PullFramesResponse }
     * 
     */
    public PullFramesResponse createPullFramesResponse() {
        return new PullFramesResponse();
    }

    /**
     * Create an instance of {@link FramePullResult }
     * 
     */
    public FramePullResult createFramePullResult() {
        return new FramePullResult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PushFrameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.renovelabz.com/", name = "pushFrameResponse")
    public JAXBElement<PushFrameResponse> createPushFrameResponse(PushFrameResponse value) {
        return new JAXBElement<PushFrameResponse>(_PushFrameResponse_QNAME, PushFrameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PullFramesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.renovelabz.com/", name = "pullFramesResponse")
    public JAXBElement<PullFramesResponse> createPullFramesResponse(PullFramesResponse value) {
        return new JAXBElement<PullFramesResponse>(_PullFramesResponse_QNAME, PullFramesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartNewSegment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.renovelabz.com/", name = "startNewSegment")
    public JAXBElement<StartNewSegment> createStartNewSegment(StartNewSegment value) {
        return new JAXBElement<StartNewSegment>(_StartNewSegment_QNAME, StartNewSegment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSegmentIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.renovelabz.com/", name = "getSegmentIDResponse")
    public JAXBElement<GetSegmentIDResponse> createGetSegmentIDResponse(GetSegmentIDResponse value) {
        return new JAXBElement<GetSegmentIDResponse>(_GetSegmentIDResponse_QNAME, GetSegmentIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartNewSegmentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.renovelabz.com/", name = "startNewSegmentResponse")
    public JAXBElement<StartNewSegmentResponse> createStartNewSegmentResponse(StartNewSegmentResponse value) {
        return new JAXBElement<StartNewSegmentResponse>(_StartNewSegmentResponse_QNAME, StartNewSegmentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSegmentID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.renovelabz.com/", name = "getSegmentID")
    public JAXBElement<GetSegmentID> createGetSegmentID(GetSegmentID value) {
        return new JAXBElement<GetSegmentID>(_GetSegmentID_QNAME, GetSegmentID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PushFrame }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.renovelabz.com/", name = "pushFrame")
    public JAXBElement<PushFrame> createPushFrame(PushFrame value) {
        return new JAXBElement<PushFrame>(_PushFrame_QNAME, PushFrame.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PullFrames }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.renovelabz.com/", name = "pullFrames")
    public JAXBElement<PullFrames> createPullFrames(PullFrames value) {
        return new JAXBElement<PullFrames>(_PullFrames_QNAME, PullFrames.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "arg0", scope = PushFrame.class)
    public JAXBElement<byte[]> createPushFrameArg0(byte[] value) {
        return new JAXBElement<byte[]>(_PushFrameArg0_QNAME, byte[].class, PushFrame.class, ((byte[]) value));
    }

}
