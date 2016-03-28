import com.renovelabz.server.FrameBuffer;
import com.renovelabz.server.FramePullResult;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fujitsu on 3/28/2016.
 */
public class ScreenReceiver {
    private final int receiveInterval;
    private final FrameBuffer readBuffer;
    private boolean keepReceiving = false;
    private  Thread receiveThread = null;

    private int segmentID = 0;
    private int nextFrameIndex = 0;

    private ArrayList<ScreenReceivedListener> screenReceivedListeners = new ArrayList<>();

    public interface ScreenReceivedListener
    {
        public void ScreenReceived(byte[] screen, BufferedImage screenImage);

    }

    public ScreenReceiver(FrameBuffer readBuffer, int receiveInterval) {
        this.receiveInterval = receiveInterval;
        this.readBuffer = readBuffer;
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
                while(IsReceiving())
                {
                    try {
                        SwingUtilities.invokeAndWait(new Runnable() {
                            @Override
                            public void run() {
                                FramePullResult result = readBuffer.pullFrames(segmentID,nextFrameIndex);
                                segmentID = result.getSegmentIndex();
                                nextFrameIndex = result.getNextFrameId();

                                if(result.getData().size() > 0)
                                {
                                    byte[] latestFrame = result.getData().get(result.getData().size()-1);
                                    ByteArrayInputStream stream = new ByteArrayInputStream(latestFrame);
                                    BufferedImage resultImage = null;
                                    try {
                                        resultImage = ImageIO.read(stream);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        stream.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    notifyScreenReceived(latestFrame,resultImage);
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.currentThread().sleep(receiveInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        });
        keepReceiving = true;
        receiveThread.start();
    }

    public synchronized void addScreenReceivedListener(ScreenReceivedListener listener)
    {
        screenReceivedListeners.add(listener);
    }

    public synchronized void removeScreenReceivedListener(ScreenReceivedListener listener)
    {
        screenReceivedListeners.remove(listener);
    }

    private synchronized void notifyScreenReceived(byte[] screen, BufferedImage screenImage)
    {
        for(ScreenReceivedListener lister : screenReceivedListeners)
        {
            lister.ScreenReceived(screen,screenImage);
        }
    }

}
