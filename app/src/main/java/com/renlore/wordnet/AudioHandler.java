package com.renlore.wordnet;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Ng on 6/5/2015.
 */
public class AudioHandler {
    private MediaPlayer music, swosh, netted, achieve;
    private Context context;
    private GameAreaManager gameAreaManager;

    public AudioHandler(Context context, GameAreaManager gameAreaManager) {
        this.context = context;
        this.gameAreaManager = gameAreaManager;
        netted = MediaPlayer.create(context.getApplicationContext(), R.raw.netted);
        swosh = MediaPlayer.create(context.getApplicationContext(), R.raw.swosh);
        achieve = MediaPlayer.create(context.getApplicationContext(), R.raw.achievement);
        music = MediaPlayer.create(context.getApplicationContext(), R.raw.whimsicalpopsicle);
        music.setVolume(0.3f, 0.3f);
        music.start();
        music.setLooping(true);
    }

    public void releaseAll() {
        music.release();
        swosh.release();
        achieve.release();
        netted.release();
    }

    public void playSwosh() {
        if (swosh.isPlaying()) {
            swosh.seekTo(0);
        } else {
            swosh.start();
        }
    }

    public void playNetted() {
        if (netted.isPlaying()) {
            netted.seekTo(0);
        } else {
            netted.start();
        }
    }

    public void playAchieve() {
        if (!achieve.isPlaying()) {
            achieve.start();
        }
    }
}
