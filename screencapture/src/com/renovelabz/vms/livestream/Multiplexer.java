package com.renovelabz.vms.livestream;

import com.renovelabz.server.FrameBuffer;
import com.renovelabz.server.FrameBufferImplService;
import com.renovelabz.server.FramePullResult;

import java.net.URL;

/**
 * Created by Fujitsu on 3/29/2016.
 */
public class Multiplexer {
    private URL outputURL;
    private URL inputURL = null;
    private boolean running = false;
    private Thread thread = null;
    private boolean switchPending = false;

    public Multiplexer(URL outputURL)
    {
        this.outputURL = outputURL;
    }

    public synchronized URL getOutputURL() {
        return outputURL;
    }

    private synchronized void markSwitchPending()
    {
        switchPending = true;
    }
    private synchronized void clearSwitchPending()
    {
        switchPending = false;
    }
    private synchronized boolean isSwitchPending()
    {
        return switchPending;
    }

    public synchronized URL getInputURL() {
        return inputURL;
    }

    public synchronized void setInputURL(URL inputURL) {
        this.inputURL = inputURL;
        markSwitchPending();
    }

    public synchronized boolean isRunning() {
        return running;
    }

    private synchronized void setRunning(boolean running) {
        this.running = running;
    }

    public synchronized void Stop()
    {
        setRunning(false);
    }

    public synchronized void Start()
    {
        if(isRunning())
            return;

        //if an input url is already assigned, mark that a new switch is pending. this will ensure that start new segment is called in output stream
        if(getInputURL() != null)
            markSwitchPending();

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //open output connection
                FrameBufferImplService outputService = new FrameBufferImplService(getOutputURL());
                FrameBuffer outputBuffer = outputService.getFrameBufferImplPort();

                FrameBufferImplService inputService = null;
                FrameBuffer inputBuffer = null;

                int segmentID = 0;
                int nextFrameID = 0;

                while(isRunning())
                {
                    if(isSwitchPending())
                    {
                        //a switch is pending, implement  it
                        inputService = new FrameBufferImplService(getInputURL());
                        inputBuffer = inputService.getFrameBufferImplPort();

                        segmentID = 0;
                        nextFrameID = 0;

                        outputBuffer.startNewSegment();
                        clearSwitchPending();
                    }
                    else
                    {
                        //tunnel input to output
                        if(inputBuffer != null)
                        {
                            FramePullResult pullResult = inputBuffer.pullFrames(segmentID,nextFrameID);
                            segmentID = pullResult.getSegmentIndex();
                            nextFrameID = pullResult.getNextFrameId();
                            for(byte[] block : pullResult.getData())
                            {
                                outputBuffer.pushFrame(block);
                            }
                        }
                    }
                }

                inputBuffer = null;
                inputService = null;
                outputBuffer = null;
                outputService = null;
            }
        });

        setRunning(true);
        thread.start();
    }
}
