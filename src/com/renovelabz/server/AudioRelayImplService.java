
package com.renovelabz.server;

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
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "AudioRelayImplService", targetNamespace = "http://server.renovelabz.com/", wsdlLocation = "http://localhost:8000/audiorelay?wsdl")
public class AudioRelayImplService
    extends Service
{

    private final static URL AUDIORELAYIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException AUDIORELAYIMPLSERVICE_EXCEPTION;
    private final static QName AUDIORELAYIMPLSERVICE_QNAME = new QName("http://server.renovelabz.com/", "AudioRelayImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8000/audiorelay?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        AUDIORELAYIMPLSERVICE_WSDL_LOCATION = url;
        AUDIORELAYIMPLSERVICE_EXCEPTION = e;
    }

    public AudioRelayImplService() {
        super(__getWsdlLocation(), AUDIORELAYIMPLSERVICE_QNAME);
    }

    public AudioRelayImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), AUDIORELAYIMPLSERVICE_QNAME, features);
    }

    public AudioRelayImplService(URL wsdlLocation) {
        super(wsdlLocation, AUDIORELAYIMPLSERVICE_QNAME);
    }

    public AudioRelayImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, AUDIORELAYIMPLSERVICE_QNAME, features);
    }

    public AudioRelayImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AudioRelayImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns AudioRelay
     */
    @WebEndpoint(name = "AudioRelayImplPort")
    public AudioRelay getAudioRelayImplPort() {
        return super.getPort(new QName("http://server.renovelabz.com/", "AudioRelayImplPort"), AudioRelay.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AudioRelay
     */
    @WebEndpoint(name = "AudioRelayImplPort")
    public AudioRelay getAudioRelayImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://server.renovelabz.com/", "AudioRelayImplPort"), AudioRelay.class, features);
    }

    private static URL __getWsdlLocation() {
        if (AUDIORELAYIMPLSERVICE_EXCEPTION!= null) {
            throw AUDIORELAYIMPLSERVICE_EXCEPTION;
        }
        return AUDIORELAYIMPLSERVICE_WSDL_LOCATION;
    }

}
