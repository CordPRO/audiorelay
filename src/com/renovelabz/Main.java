package com.renovelabz;

import javax.sound.sampled.*;
import javax.xml.ws.spi.Invoker;
import java.io.ByteArrayOutputStream;

public class Main {

    static AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                channels, signed, bigEndian);
        return format;
    }


    public static void main(String[] args) throws LineUnavailableException {



        AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);

        DataLine.Info info = new DataLine.Info(TargetDataLine.class,
                format);
        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("Linein not supported");
        }

        TargetDataLine microphone = AudioSystem.getTargetDataLine(format);

        DataLine.Info info2 = new DataLine.Info(SourceDataLine.class, format);
        final SourceDataLine sourceline = (SourceDataLine) AudioSystem.getLine(info2);
        sourceline.open(format);
        sourceline.start();


        microphone.open(format);


        ByteArrayOutputStream out  = new ByteArrayOutputStream();
        int numBytesRead;
        byte[] data = new byte[microphone.getBufferSize() / 5];

// Begin audio capture.
        microphone.start();

        microphone.addLineListener(new LineListener() {
            @Override
            public void update(LineEvent event) {
                System.out.println("Updated");
            }
        });
// Here, stopped is a global boolean set by another thread.
        while (true) {
            // Read the next chunk of data from the TargetDataLine.
            numBytesRead =  microphone.read(data, 0, data.length);
            // Save this chunk of data.
            System.out.println("Read " + String.valueOf(data.length));
            out.write(data, 0, numBytesRead);
            sourceline.write(data, 0, numBytesRead);


        }



    }
}
