import com.renovelabz.server.FrameBuffer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Fujitsu on 3/28/2016.
 */
public class ScreenCapturer {
    //continuosly take screen captures and write to buffer
    private final int captureInterval;
    private final FrameBuffer writeBuffer;
    private boolean keepCapturing = false;

    public synchronized boolean IsCapturing()
    {
        return keepCapturing;
    }



    private Thread captureThread = null;

    public ScreenCapturer(FrameBuffer buffer, int interval)
    {
        this.writeBuffer = buffer;
        this.captureInterval = interval;


    }

    public synchronized void StartCapture()
    {
        if(IsCapturing())
            return;

        captureThread = new Thread(new Runnable() {

            @Override
            public void run() {

                while(IsCapturing())
                {
                    try {
                        SwingUtilities.invokeAndWait(new Runnable() {
                            @Override
                            public void run() {
                                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                                BufferedImage image = null;
                                try {
                                    image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                                } catch (AWTException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    ImageIO.write(image, "png", outputStream);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                writeBuffer.pushFrame(outputStream.toByteArray());
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.currentThread().sleep(captureInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

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
}
