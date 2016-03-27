package com.renovelabz.server;

import javax.jws.WebService;

/**
 * Created by Fujitsu on 3/27/2016.
 */

@WebService(endpointInterface="com.renovelabz.server.AudioRelay")
public class AudioRelayImpl implements AudioRelay {
    public FrameBufferImpl buffer = new FrameBufferImpl(5);
    @Override
    public FrameBufferImpl getFrameBuffer() {
        return buffer;
    }
}
