
import com.renovelabz.server.FrameBuffer;
import com.renovelabz.server.FrameBufferImplService;

import javax.sound.sampled.*;
import java.lang.reflect.Array;

/**
 * Created by Fujitsu on 3/27/2016.
 */
public class AudioCapture {
    public static void main(String[] args) throws LineUnavailableException {
        FrameBufferImplService service = new FrameBufferImplService();

        FrameBuffer frameBuffer = service.getFrameBufferImplPort();

        frameBuffer.startNewSegment();

        AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);

        TargetDataLine microphone = AudioSystem.getTargetDataLine(format);

        microphone.open(format);
        microphone.start();

        int numBytesRead;
        byte[] data = new byte[microphone.getBufferSize() / 5];

        while (true) {
            // Read the next chunk of data from the TargetDataLine.
            numBytesRead =  microphone.read(data, 0, data.length);
            // Save this chunk of data.
            System.out.println("Read " + String.valueOf(data.length));

            byte[] sizedData = new byte[numBytesRead];
            System.arraycopy(data,0,sizedData,0,numBytesRead);
            frameBuffer.pushFrame(sizedData);


        }

    }
}
