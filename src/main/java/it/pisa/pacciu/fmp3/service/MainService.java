/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pisa.pacciu.fmp3.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

/**
 *
 * @author m.luly
 */
public class MainService {

    private AdvancedPlayer player = null;
    private final static MainService MS = new MainService();
    private final static Set<Integer> availableKeys = new HashSet<Integer>(12);
    private final static Map<String, Integer> fCodeNames = new HashMap<String, Integer>();
    private Map<Integer, File> files = new HashMap<Integer, File>();

    static {
        for (int i = 112; i < 124; i++) {
            availableKeys.add(i);
            fCodeNames.put("F" + (i - 111), i);
        }
    }

    public static boolean isKeyAvailable(int key) {
        return availableKeys.contains(key);
    }

    public static MainService getInstance() {
        return MS;
    }

    private MainService() {
    }

    public void addFile(String fName, File file) {
        Integer fCode = fCodeNames.get(fName);
        try {
            files.put(fCode, file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void playFile(Integer fCode) {
        File file = files.get(fCode);
        if(file!=null){
            try {
                if(player!=null){
                    stop();
                }
                playMp3(file, new InfoListener());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
//        if (pt != null) {
//            pt.start();
//            try {
//                files.put(fCode, new PlayerThread(pt));
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
    }

    public AdvancedPlayer playMp3(File mp3, PlaybackListener listener) throws IOException, JavaLayerException
  {
    return playMp3(mp3, 0, Integer.MAX_VALUE, listener);
  }

  public AdvancedPlayer playMp3(File mp3, int start, int end, PlaybackListener listener) throws IOException, JavaLayerException
  {
    return playMp3(new BufferedInputStream(new FileInputStream(mp3)), start, end, listener);
  }

  public AdvancedPlayer playMp3(final InputStream is, final int start, final int end, PlaybackListener listener) throws JavaLayerException
  {
    player = new AdvancedPlayer(is);
    player.setPlayBackListener(listener);
    // run in new thread
    new Thread()
    {
      public void run()
      {
        try
        {
          player.play(start, end);
        }
        catch (Exception e)
        {
          throw new RuntimeException(e.getMessage());
        }
      }
    }.start();
    return player;
  }

  public class InfoListener extends PlaybackListener
  {
    public void playbackStarted(PlaybackEvent evt)
    {
      System.out.println("Play started from frame " + evt.getFrame());
    }

    public void playbackFinished(PlaybackEvent evt)
    {
      System.out.println("Play completed at frame " + evt.getFrame());
    }
  }
    public void stop() {
        if(player!=null){
            try{
                player.stop();
            }catch(NullPointerException e){}
            player=null;
        }
    }
}
