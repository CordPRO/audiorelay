import com.renovelabz.server.FrameBuffer;
import com.renovelabz.server.FrameBufferImplService;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
    private JButton btnSwitch;
    ScreenCapturer cap;
    ScreenCapturer cap2;
    ScreenReceiver recv;

    private boolean channel1 = false;
    Multiplexer multi;
    public CaptureForm()
    {
        FrameBufferImplService service2 = null;
        FrameBufferImplService service = null;
        FrameBufferImplService serviceOutput = null;
        try {
            service = new FrameBufferImplService(new URL("http://localhost:8000/screenrelay?wsdl"));
            service2 = new FrameBufferImplService(new URL("http://localhost:8000/screenrelay2?wsdl"));
            serviceOutput = new FrameBufferImplService(new URL("http://localhost:8000/screenrelayoutput?wsdl"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            multi = new Multiplexer(new URL("http://localhost:8000/screenrelayoutput?wsdl"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        FrameBuffer outputBuffer = serviceOutput.getFrameBufferImplPort();
        FrameBuffer frameBuffer2 = service2.getFrameBufferImplPort();
        FrameBuffer frameBuffer = service.getFrameBufferImplPort();

        cap = new ScreenCapturer(frameBuffer,5000);
        cap2 = new ScreenCapturer(frameBuffer2,100);

        recv = new ScreenReceiver(outputBuffer,1);

        recv.addScreenReceivedListener(new ScreenReceiver.ScreenReceivedListener() {
            @Override
            public void ScreenReceived(byte[] screen, BufferedImage screenImage) {
                jImageLabel.setIcon(new ImageIcon(screenImage));
            }
        });

        recv.StartReceiving();

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(cap2.IsCapturing() )
                {
                    cap.StopCapturing();
                    cap2.StopCapturing();
                }
                else {
                    cap.StartCapture();
                    cap2.StartCapture();
                    multi.Start();
                }
            }
        });

        btnSwitch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(channel1)
                {
                    channel1 = false;
                    try {
                        setTitle("Channel 2");
                        multi.setInputURL(new URL("http://localhost:8000/screenrelay2?wsdl"));
                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                    }
                }
                else
                {
                    channel1 = true;
                    try {
                        setTitle("Channel 1");
                        multi.setInputURL(new URL("http://localhost:8000/screenrelay1?wsdl"));
                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        });

        jImageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                btnSwitch.doClick();
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
