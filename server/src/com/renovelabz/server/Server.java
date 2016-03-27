/**
 * Created by Fujitsu on 3/27/2016.
 */
package com.renovelabz.server;

import javax.xml.ws.Endpoint;

public class Server {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8000/audiorelay", new FrameBufferImpl(5));

    }
}
