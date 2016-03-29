/*
 * $Id$
 * 04/12/15
 */
package com.extremeboredom.wordattack.punishment;

import android.app.Activity;
import android.media.MediaPlayer;

import com.extremeboredom.wordattack.R;

public class NoisePunishment extends DialogPunishment {

    @Override
    public void punish(Activity activity) {
        super.punish(activity);
        final MediaPlayer mp = MediaPlayer.create(activity, R.raw.noise);
        mp.start();
    }
}
