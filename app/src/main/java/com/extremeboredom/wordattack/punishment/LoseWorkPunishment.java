/*
 * $Id$
 * 30/03/16
 */
package com.extremeboredom.wordattack.punishment;

import android.app.Activity;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.extremeboredom.wordattack.TimerHandler;

public class LoseWorkPunishment implements Punishment {

    EditText attachedEdittext = null;

    public LoseWorkPunishment(EditText editText) {
        attachedEdittext = editText;
    }

    @Override
    public void punish(Activity activity) {
        new MaterialDialog.Builder(activity)
                .title("Bad news")
                .positiveText("Start over")
                .content("I hate to say this, but your content is gone. It's time you start focusing on your writing.")
                .cancelable(false)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                        TimerHandler.getInstance().resetClock();
                    }
                })
                .show();
        attachedEdittext.setText("");
    }
}
