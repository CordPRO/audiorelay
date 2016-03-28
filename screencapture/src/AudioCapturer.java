import com.renovelabz.server.FrameBuffer;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Fujitsu on 3/28/2016.
 */
public class AudioCapturer {
    //continuosly take screen captures and write to buffer

    private final FrameBuffer writeBuffer;
    private boolean keepCapturing = false;

    private AudioFormat format;

    private TargetDataLine microphone;

    public synchronized boolean IsCapturing()
    {
        return keepCapturing;
    }



    private Thread captureThread = null;

    public AudioCapturer(FrameBuffer buffer)
    {
        this.writeBuffer = buffer;


        this.format = new AudioFormat(8000.0f, 16, 1, true, true);


    }

    public synchronized void StartCapture() {
        if(IsCapturing())
            return;

        captureThread = new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    microphone = AudioSystem.getTargetDataLine(getFormat());
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }

                try {
                    microphone.open(getFormat());
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
                microphone.start();

                int numBytesRead;
                byte[] data = new byte[microphone.getBufferSize() / 5];


                while(IsCapturing())
                {
                    numBytesRead =  microphone.read(data, 0, data.length);
                    // Save this chunk of data.
                    System.out.println("Read " + String.valueOf(data.length));

                    byte[] sizedData = new byte[numBytesRead];
                    System.arraycopy(data,0,sizedData,0,numBytesRead);
                    writeBuffer.pushFrame(sizedData);


                }
                microphone.stop();
                microphone.close();

            }
        });

        keepCapturing = true;
        writeBuffer.startNewSegment();
        captureThread.start();
    }

    public synchronized void StopCapturing()
    {
        keepCapturing = false;
    }

    public synchronized AudioFormat getFormat() {
        return format;
    }

    public synchronized void setFormat(AudioFormat format) throws Exception {
        if(IsCapturing())
            throw new Exception("Capturing has already started");
        this.format = format;
    }
}
