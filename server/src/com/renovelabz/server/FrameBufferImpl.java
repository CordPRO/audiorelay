/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renovelabz.server;
import javax.jws.WebService;
import java.util.Scanner;

/**
 *
 * @author Madhawa
 */
@WebService(endpointInterface="com.renovelabz.server.FrameBuffer")
public class FrameBufferImpl implements FrameBuffer {

    private int bufferSize = 10;
    private byte[][] frames = null;
    private int nextFrameId = 0;

    private int segmentID = 0;

    public FrameBufferImpl(int bufferSize) {
        this.bufferSize = bufferSize;
        frames = new byte[bufferSize][];
    }

    public int pushFrame(byte[] frame) {
        int slotId = nextFrameId % bufferSize;
        frames[slotId] = frame;
        System.out.println(Integer.toString(frame.length)+ " bytes received");
        return ++nextFrameId;
    }

    public FramePullResultImpl pullFrames(int segmentID, int startFrameId) {
        if (segmentID == this.segmentID) {
            if (nextFrameId - startFrameId > bufferSize) {
                //buffer overrun. return all available frames
                byte[][] resultsData = new byte[bufferSize][];
                for (int i = 0; i < bufferSize; i++) {
                    int slot = (i + nextFrameId) % bufferSize;
                    resultsData[i] = frames[slot];
                }

                FramePullResultImpl result = new FramePullResultImpl();
                result.setBufferOverRun(true);
                result.setData(resultsData);
                result.setNextFrameId( nextFrameId);
                result.setSegmentIndex( segmentID);
                return result;
            } else {
                int frameCount = nextFrameId - startFrameId;
                byte[][] resultsData = new byte[frameCount][];
                for (int i = 0; i < frameCount; i++) {
                    int slot = (startFrameId + i) % bufferSize;
                    resultsData[i] = frames[slot];
                }

                FramePullResultImpl result = new FramePullResultImpl();
                result.setBufferOverRun(false);
                result.setData(resultsData);
                result.setNextFrameId(nextFrameId);
                result.setSegmentIndex(segmentID);
                return result;
            }
        } else {
            
            int dataCount = 0;
            for(int i = 0; i < bufferSize; i++)
            {
                //see whether the buffer is filled
                if(frames[i] != null)
                    dataCount++;
            }
            if(dataCount == bufferSize)
            {
                //buffer is filled
                //return all available frames
                byte[][] resultsData = new byte[bufferSize][];
                for (int i = 0; i < bufferSize; i++) {
                    int slot = (i + nextFrameId) % bufferSize;
                    resultsData[i] = frames[slot];
                }

                FramePullResultImpl result = new FramePullResultImpl();
                result.setBufferOverRun(true);
                result.setData(resultsData);
                result.setNextFrameId(nextFrameId);
                result.setSegmentIndex( this.segmentID);
                return result;
            }
            else
            {
                //buffer is partly filled
                //return all available frames
                byte[][] resultsData = new byte[dataCount][];
                for (int i = 0; i < dataCount; i++) {
                    int slot = (i + nextFrameId - dataCount) % bufferSize;
                    resultsData[i] = frames[slot];
                }

                FramePullResultImpl result = new FramePullResultImpl();
                result.setBufferOverRun(false);
                result.setData(resultsData);
                result.setNextFrameId(nextFrameId);
                result.setSegmentIndex( this.segmentID);
                return result;
            }
            
        }

    }

    public void startNewSegment() {
        segmentID++;
        frames = new byte[bufferSize][];
        nextFrameId = 0;
        System.out.println("New Segment Started");
    }

    /**
     * @return the segmentID
     */
    public int getSegmentID() {
        return segmentID;
    }



    public static void main(String[] args) {
        FrameBufferImpl fb = new FrameBufferImpl(5);
        Scanner sc = new Scanner(System.in);

        int segmentIndex = 0;
        int readIndex = 0;
        while (true) {
            System.out.println("1 = Insert, 2 = pull, 3 = new Segment. Buffer Size = " + String.valueOf(fb.bufferSize));
            int i = sc.nextInt();
            if (i == 1) {
                System.out.println("Enter value to put in");
                byte b = sc.nextByte();
                fb.pushFrame(new byte[]{b});

            } else if (i == 2) {
                FramePullResultImpl res = fb.pullFrames(segmentIndex,readIndex);
                readIndex = res.getNextFrameId();
                segmentIndex = res.getSegmentIndex();
                System.out.println("New Frame Index: " + Integer.toString(readIndex) + " Segment Index: " + Integer.toString(segmentIndex));
                for (byte[] b : res.getData()) {
                    System.out.println(Integer.valueOf(b[0]));
                }
            }
            else if(i==3)
            {
                fb.startNewSegment();
            }
        }
    }
}
