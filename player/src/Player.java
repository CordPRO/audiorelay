import com.renovelabz.server.FrameBuffer;
import com.renovelabz.server.FrameBufferImplService;
import com.renovelabz.server.FramePullResult;

import javax.sound.sampled.*;

/**
 * Created by Fujitsu on 3/27/2016.
 */
public class Player {
    public static void main(String[] args) throws LineUnavailableException {
        FrameBufferImplService service = new FrameBufferImplService();

        FrameBuffer frameBuffer = service.getFrameBufferImplPort();


        AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);

        DataLine.Info info2 = new DataLine.Info(SourceDataLine.class, format);
        final SourceDataLine sourceline = (SourceDataLine) AudioSystem.getLine(info2);
        sourceline.open(format);
        sourceline.start();

        int segmentId = 0;
        int frameId = 0;

        while(true)
        {
            FramePullResult res = frameBuffer.pullFrames(segmentId,frameId);
            frameId = res.getNextFrameId();
            segmentId = res.getSegmentIndex();

            for(byte[] b : res.getData())
            {
                sourceline.write(b,0,b.length);
                System.out.println(String.valueOf(b.length) + " bytes sent to audio out.");
            }

        }


    }
}
