/*
 * $Id$
 * 08/12/15
 */
package com.extremeboredom.wordattack.utils;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import com.extremeboredom.wordattack.TimerHandler;
import com.extremeboredom.wordattack.ViewObjectsHolder;
import com.extremeboredom.wordattack.callbacks.EditorTextWatcher;

public class TimerEditText extends EditText {
    public TimerEditText(Context context) {
        super(context);
    }

    public TimerEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimerEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        Log.d("onselchanged", "start " + selStart + " end " + selEnd);
        if (ViewObjectsHolder.getActivityInstance() != null) {
            PreferenceManager.getDefaultSharedPreferences(ViewObjectsHolder.getActivityInstance()).edit().putInt("start", getSelectionStart()).commit();
            PreferenceManager.getDefaultSharedPreferences(ViewObjectsHolder.getActivityInstance()).edit().putInt("end", getSelectionEnd()).commit();
        }

        if (EditorTextWatcher.completedDialog != null && EditorTextWatcher.completedDialog.isShowing()) {

        } else {
            TimerHandler.getInstance().resetClock();
        }

    }
}
