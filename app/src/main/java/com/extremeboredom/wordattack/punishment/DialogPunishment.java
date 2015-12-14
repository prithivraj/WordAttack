/*
 * $Id$
 * 03/12/15
 */
package com.extremeboredom.wordattack.punishment;

import android.app.Activity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.extremeboredom.wordattack.RandomMessages;
import com.extremeboredom.wordattack.TimerHandler;

public class DialogPunishment implements Punishment {

    @Override
    public void punish(Activity activity) {
        new MaterialDialog.Builder(activity)
                .title(RandomMessages.getRandomTitle())
                .positiveText("Get back to work")
                .content(RandomMessages.getRandomQuote())
                .cancelable(false)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                        TimerHandler.getInstance().resetClock();
                    }
                })
                .show();
    }
}
