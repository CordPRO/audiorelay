
package com.renovelabz.server;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "AudioRelay", targetNamespace = "http://server.renovelabz.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface AudioRelay {


    /**
     * 
     * @return
     *     returns com.renovelabz.server.FrameBuffer
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getFrameBuffer", targetNamespace = "http://server.renovelabz.com/", className = "com.renovelabz.server.GetFrameBuffer")
    @ResponseWrapper(localName = "getFrameBufferResponse", targetNamespace = "http://server.renovelabz.com/", className = "com.renovelabz.server.GetFrameBufferResponse")
    @Action(input = "http://server.renovelabz.com/AudioRelay/getFrameBufferRequest", output = "http://server.renovelabz.com/AudioRelay/getFrameBufferResponse")
    public FrameBuffer getFrameBuffer();

}
