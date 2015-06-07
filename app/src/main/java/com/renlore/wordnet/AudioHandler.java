package com.renlore.wordnet;

import android.media.MediaPlayer;

/**
 * Created by Ng on 6/5/2015.
 */
public class AudioHandler {
    private static MediaPlayer music, swosh, netted, achieve;

    public static void load() {
        netted = MediaPlayer.create(WordNet.context.getApplicationContext(), R.raw.netted);
        swosh = MediaPlayer.create(WordNet.context.getApplicationContext(), R.raw.swosh);
        achieve = MediaPlayer.create(WordNet.context.getApplicationContext(), R.raw.achievement);
        music = MediaPlayer.create(WordNet.context.getApplicationContext(), R.raw.whimsicalpopsicle);
        music.setVolume(0.3f, 0.3f);
        music.start();
        music.setLooping(true);
    }

    public static void releaseAll() {
        music.release();
        swosh.release();
        achieve.release();
        netted.release();
    }

    public static void playSwosh() {
        if (swosh.isPlaying()) {
            swosh.seekTo(0);
        } else {
            swosh.start();
        }
    }

    public static void playNetted() {
        if (netted.isPlaying()) {
            netted.seekTo(0);
        } else {
            netted.start();
        }
    }

    public static void playAchieve() {
        if (!achieve.isPlaying()) {
            achieve.start();
        }
    }
}
