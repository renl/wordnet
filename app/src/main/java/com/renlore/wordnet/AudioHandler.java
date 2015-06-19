package com.renlore.wordnet;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;

/**
 * Created by Ng on 6/5/2015.
 */
public class AudioHandler {
    private final static String TAG = AudioHandler.class.getSimpleName();
    private static SoundPool soundPool;
    private static int swosh, netted;
    private static int achieve;
    private static SoundPool.Builder spb;
    private static MediaPlayer music;

    public static void load() {
        if (soundPool == null) {
            if (Build.VERSION.SDK_INT >= 21) {
                Log.d(TAG, "Loading audio -----> SDK > 21");
                spb = new SoundPool.Builder();
                spb.setMaxStreams(30);
                spb.setAudioAttributes(new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).build());
                soundPool = spb.build();
            } else {
                Log.d(TAG, "Loading audio");
                soundPool = new SoundPool(30, AudioManager.STREAM_MUSIC, 0);
            }
        }
        netted = soundPool.load(WordNet.context.getApplicationContext(), R.raw.netted, 1);
        swosh = soundPool.load(WordNet.context.getApplicationContext(), R.raw.swosh, 1);
        achieve = soundPool.load(WordNet.context.getApplicationContext(), R.raw.achievement, 1);
        music = MediaPlayer.create(WordNet.context.getApplicationContext(), R.raw.whimsicalpopsicle);
        music.setVolume(0.3f, 0.3f);
        music.setLooping(true);
        music.start();
    }

    public static void releaseAll() {
        music.release();
        soundPool.release();
        soundPool = null;
        music = null;
    }

    public static void playSwosh() {
        soundPool.play(swosh, 1, 1, 1, 0, 1);
    }

    public static void playNetted() {
        soundPool.play(netted, 1, 1, 1, 0, 1);
    }

    public static void playAchieve() {
        soundPool.play(achieve, 1, 1, 1, 0, 1);
    }
}
