package com.renovelabz.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Fujitsu on 3/27/2016.
 */
@WebService
public interface FrameBuffer {
    @WebMethod
    public int pushFrame(byte[] frame);
    @WebMethod
    public FramePullResultImpl pullFrames(int segmentID, int startFrameId);
    @WebMethod
    public void startNewSegment();
    @WebMethod
    public int getSegmentID();

}
