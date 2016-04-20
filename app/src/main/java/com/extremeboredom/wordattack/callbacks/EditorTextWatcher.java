/*
 * $Id$
 * 04/12/15
 */
package com.extremeboredom.wordattack.callbacks;

import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.extremeboredom.wordattack.CurrentSetting;
import com.extremeboredom.wordattack.EditorActivity;
import com.extremeboredom.wordattack.ShareUtils;
import com.extremeboredom.wordattack.TimerHandler;
import com.extremeboredom.wordattack.ViewObjectsHolder;

import java.util.StringTokenizer;

public class EditorTextWatcher implements TextWatcher {
    public static MaterialDialog completedDialog = null;
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
    @Override
    public void afterTextChanged(final Editable s) {
        Log.d("TextWatcher", "After textchanged starts");
        PreferenceManager.getDefaultSharedPreferences(ViewObjectsHolder.getActivityInstance()).edit().putString("content", s.toString()).commit();
        StringTokenizer st = new StringTokenizer(s.toString());
        int words = st.countTokens();
        int limit = CurrentSetting.getInstance().getWordLimit();


        if (words > limit) {
            ViewObjectsHolder.getWordCount().setText("Objective complete!");
            if(EditorActivity.shareIcon.isVisible() || completedDialog!=null && completedDialog.isShowing()){

            }
            else{
                completedDialog = new MaterialDialog.Builder(ViewObjectsHolder.getActivityInstance())
                        .title("Work Complete!")
                        .positiveText("Share")
                        .content("Take a break now. You could still be punished if you choose to continue writing. You can share the text at anytime though.")
                        .cancelable(false)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                                materialDialog.dismiss();
                                ShareUtils.shareText(ViewObjectsHolder.getActivityInstance(), s.toString());
                                PreferenceManager.getDefaultSharedPreferences(ViewObjectsHolder.getActivityInstance()).edit().putString("content", "").commit();
                                ViewObjectsHolder.init(ViewObjectsHolder.getActivityInstance());
                                ((EditorActivity) ViewObjectsHolder.getActivityInstance()).showSettings();
                            }
                        })
                        .negativeText("Continue Writing")
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                                materialDialog.dismiss();
                            }
                        }).build();

                ShareUtils.addToClipboard(ViewObjectsHolder.getActivityInstance(), s.toString());
                completedDialog.show();
                EditorActivity.shareIcon.setVisible(true);
                TimerHandler.getInstance().pauseClock();
            }

        }
        else{
            ViewObjectsHolder.getWordCount().setText(words + " out of "+limit+" words");
        }
        Log.d("TextWatcher", "After textchanged ends");
    }
}
