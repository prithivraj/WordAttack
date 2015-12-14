/*
 * $Id$
 * 04/12/15
 */
package com.extremeboredom.wordattack.callbacks;

import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.font.FontAwesome;
import com.extremeboredom.wordattack.TimerHandler;

public class PauseButtonClickListner implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        BootstrapButton button = (BootstrapButton) v;
        if (button.getText().toString().equals(new FontAwesome().unicodeForKey(FontAwesome.FA_PAUSE))) {
            button.setFontAwesomeIcon(FontAwesome.FA_PLAY);
            TimerHandler.getInstance().pauseClock();
        } else {
            button.setFontAwesomeIcon(FontAwesome.FA_PAUSE);
            TimerHandler.getInstance().resume();
        }

    }
}
