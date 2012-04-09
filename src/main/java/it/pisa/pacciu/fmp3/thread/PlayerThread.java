/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pisa.pacciu.fmp3.thread;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.Player;

/**
 *
 * @author m.luly
 */
public class PlayerThread extends Thread {

    public static AudioDevice DEV;

    static {
        try {
            DEV = getAudioDevice();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private File file;
    private Integer fCode;
    private Player player;

    public PlayerThread(File file, Integer fCode) throws IOException, JavaLayerException {
        this.file = file;
        this.fCode = fCode;
        this.player=new Player(getInputStream(file), DEV);
    }

    public PlayerThread(PlayerThread pt) throws JavaLayerException, IOException {
        this.file = pt.file;
        this.fCode = pt.fCode;
        this.player = new Player(getInputStream(pt.file), DEV);
    }

    @Override
    public void run() {
        try {
            play();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void play()
            throws JavaLayerException {
        try {
            player.play();
        } catch (Exception ex) {
            throw new JavaLayerException("Problem playing file ", ex);
        }
    }

    public void stopPlayer() {
        player.close();
    }

    /**
     * Playing file from FileInputStream.
     */
    public static InputStream getInputStream(File file)
            throws IOException {
        FileInputStream fin = new FileInputStream(file);
        BufferedInputStream bin = new BufferedInputStream(fin);
        return bin;
    }

    private static AudioDevice getAudioDevice()
            throws JavaLayerException {
        return FactoryRegistry.systemRegistry().createAudioDevice();
    }
}
