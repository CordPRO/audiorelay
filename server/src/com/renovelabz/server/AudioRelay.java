package com.renovelabz.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Fujitsu on 3/27/2016.
 */
@WebService
public interface AudioRelay {
    @WebMethod
    public FrameBufferImpl getFrameBuffer();
}
