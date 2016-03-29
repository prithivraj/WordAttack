/*
 * $Id$
 * 04/12/15
 */
package com.extremeboredom.wordattack.callbacks;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.extremeboredom.wordattack.CurrentSetting;
import com.extremeboredom.wordattack.EditorActivity;
import com.extremeboredom.wordattack.TimerHandler;
import com.extremeboredom.wordattack.ViewObjectsHolder;

import java.util.StringTokenizer;

public class EditorTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(final Editable s) {
        PreferenceManager.getDefaultSharedPreferences(ViewObjectsHolder.getActivityInstance()).edit().putString("content", s.toString()).commit();
        StringTokenizer st = new StringTokenizer(s.toString());
        int words = st.countTokens();
        int limit = CurrentSetting.getInstance().getWordLimit();

        if (words < limit) {
            ViewObjectsHolder.getWordCount().setText(words + " words");
        } else {
            ViewObjectsHolder.getWordCount().setText("Objective complete!");
            TimerHandler.getInstance().pauseClock();
            TimerHandler.getInstance().relaxPunishments();
            ClipboardManager clipboard = (ClipboardManager) ViewObjectsHolder.getActivityInstance().getSystemService(Activity.CLIPBOARD_SERVICE);
            clipboard.setPrimaryClip(ClipData.newPlainText("WordAttack", s.toString()));
            Toast.makeText(ViewObjectsHolder.getActivityInstance(), "Your text is also on the clipboard.", Toast.LENGTH_LONG).show();

            new MaterialDialog.Builder(ViewObjectsHolder.getActivityInstance())
                    .title("Work Complete!")
                    .positiveText("Share")
                    .content("Take a break now.")
                    .cancelable(false)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                            String shareBody = s.toString();
                            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                            sharingIntent.setType("text/plain");
                            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                            ViewObjectsHolder.getActivityInstance().startActivity(Intent.createChooser(sharingIntent, "Share using.."));
                            PreferenceManager.getDefaultSharedPreferences(ViewObjectsHolder.getActivityInstance()).edit().putString("content", "").commit();
                            materialDialog.dismiss();
                            ViewObjectsHolder.init(ViewObjectsHolder.getActivityInstance());
                            ((EditorActivity) ViewObjectsHolder.getActivityInstance()).showSettings();
                        }
                    })
                    .show();
        }

    }
}
