package com.renovelabz.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Fujitsu on 3/27/2016.
 */
@WebService
public interface FramePullResult {

        @WebMethod
        public boolean isBufferOverRun();

        @WebMethod
        public byte[][] getData();

        @WebMethod
        public int getLastFrameId();

        @WebMethod
        public void setBufferOverRun(boolean bufferOverRun);

        @WebMethod
        public void setData(byte[][] data);

        @WebMethod
        public void setNextFrameId(int lastFrameId);

        @WebMethod
        public int getNextFrameId();

        @WebMethod
        public int getSegmentIndex();

        @WebMethod
        public void setSegmentIndex(int segmentIndex);


}
