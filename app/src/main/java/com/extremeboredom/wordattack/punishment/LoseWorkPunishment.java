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
                .positiveText("Resume Writing")
                .content("I hate to say this, but some of your content is gone. It's time you start focusing on your writing.")
                .cancelable(false)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                        TimerHandler.getInstance().resetClock();
                    }
                })
                .show();
        CharSequence text = attachedEdittext.getText();
        int loseCharsCount = 30;
        int end = text.length() < loseCharsCount ? 0 : text.length() - loseCharsCount;
        attachedEdittext.setText(text.subSequence(0, end));
        attachedEdittext.setSelection(attachedEdittext.length());
    }
}
