/**
 * Created by Fujitsu on 3/28/2016.
 */
import com.renovelabz.server.FrameBuffer;
import com.renovelabz.server.FramePullResult;

import javax.sound.sampled.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Fujitsu on 3/28/2016.
 */
public class AudioReceiver {

    private final FrameBuffer readBuffer;
    private boolean keepReceiving = false;
    private  Thread receiveThread = null;



    private boolean playReceived = true;
    private AudioFormat format;
    private ArrayList<AudioReceivedListener> audioReceivedListeners = new ArrayList<>();

    public synchronized boolean isPlayReceived() {
        return playReceived;
    }

    public synchronized void setPlayReceived(boolean playReceived) {
        this.playReceived = playReceived;

    }

    public synchronized AudioFormat getFormat() {
        return format;
    }

    public synchronized void setFormat(AudioFormat format) throws Exception  {
        if(IsReceiving())
            throw new Exception("Receiving has started already");
        this.format = format;
    }

    public interface AudioReceivedListener
    {
        public void AudioReceived(byte[] frame);

    }

    public AudioReceiver(FrameBuffer readBuffer) {

        this.readBuffer = readBuffer;
        format = new AudioFormat(8000.0f, 16, 1, true, true);
    }

    public synchronized boolean IsReceiving()
    {
        return keepReceiving;
    }
    public synchronized void StopReceiving()
    {
        keepReceiving = false;
    }


    public synchronized void StartReceiving()
    {


        receiveThread = new Thread(new Runnable() {
            @Override
            public void run() {

                DataLine.Info info2 = new DataLine.Info(SourceDataLine.class, format);
                final SourceDataLine sourceline;
                try {

                    int segmentID = 0;
                    int nextFrameIndex = 0;

                    sourceline = (SourceDataLine) AudioSystem.getLine(info2);
                    sourceline.open(format);

                    sourceline.start();

                    while (IsReceiving()) {

                        FramePullResult result = readBuffer.pullFrames(segmentID, nextFrameIndex);
                        segmentID = result.getSegmentIndex();
                        nextFrameIndex = result.getNextFrameId();

                        for(byte[] b : result.getData())
                        {
                            if(isPlayReceived())
                            {
                                sourceline.write(b,0,b.length);
                            }
                            System.out.println(String.valueOf(b.length) + " bytes sent to audio out.");
                            notifyAudioReceived(b);
                        }

                        //notifyAudioReceived();


                    }
                    sourceline.stop();
                    sourceline.close();

                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }


            }

        });
        keepReceiving = true;
        receiveThread.start();
    }

    public synchronized void addAudioReceivedListener(AudioReceivedListener listener)
    {
        audioReceivedListeners.add(listener);
    }

    public synchronized void removeScreenReceivedListener(AudioReceivedListener listener)
    {
        audioReceivedListeners.remove(listener);
    }

    private synchronized void notifyAudioReceived(final byte[] frame)
    {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                synchronized (AudioReceiver.this)
                {
                    for(AudioReceivedListener lister : audioReceivedListeners)
                    {
                        lister.AudioReceived(frame);
                    }
                }

            }
        });

    }

}

