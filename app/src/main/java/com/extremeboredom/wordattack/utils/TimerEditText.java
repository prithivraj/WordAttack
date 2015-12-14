/*
 * $Id$
 * 08/12/15
 */
package com.extremeboredom.wordattack.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.extremeboredom.wordattack.TimerHandler;

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
        TimerHandler.getInstance().resetClock();
    }
}
