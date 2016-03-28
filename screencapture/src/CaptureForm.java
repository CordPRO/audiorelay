import com.renovelabz.server.FrameBuffer;
import com.renovelabz.server.FrameBufferImplService;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Fujitsu on 3/28/2016.
 */
public class CaptureForm extends JFrame {
    private JPanel jPanel1;
    private JButton button1;
    private JLabel jImageLabel;
    AudioCapturer cap;
    AudioReceiver recv;
    public CaptureForm()
    {
        FrameBufferImplService service = null;
        try {
            service = new FrameBufferImplService(new URL("http://localhost:8000/audiorelay?wsdl"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        FrameBuffer frameBuffer = service.getFrameBufferImplPort();

        cap = new AudioCapturer(frameBuffer);

        recv = new AudioReceiver(frameBuffer);

        /*recv.addScreenReceivedListener(new ScreenReceiver.ScreenReceivedListener() {
            @Override
            public void ScreenReceived(byte[] screen, BufferedImage screenImage) {
                jImageLabel.setIcon(new ImageIcon(screenImage));
            }
        });*/

        recv.StartReceiving();

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(cap.IsCapturing() )
                    cap.StopCapturing();
                else
                    cap.StartCapture();
            }
        });


    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("CaptureForm");
        frame.setContentPane(new CaptureForm().jPanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800,600);
        frame.setVisible(true);


    }

    private Thread ScreenCaptureThread = null;
    public void StartScreenCapture()
    {
        ScreenCaptureThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true)
                {
                    try {
                        SwingUtilities.invokeAndWait(new Runnable() {
                            @Override
                            public void run() {
                                BufferedImage image = null;
                                try {
                                    image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                                } catch (AWTException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    ImageIO.write(image, "png", new File("screenshot.png"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        ScreenCaptureThread.start();
    }

}
