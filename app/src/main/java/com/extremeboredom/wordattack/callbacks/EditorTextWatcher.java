/*
 * $Id$
 * 04/12/15
 */
package com.extremeboredom.wordattack.callbacks;

import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;

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
    public void afterTextChanged(Editable s) {
        PreferenceManager.getDefaultSharedPreferences(ViewObjectsHolder.getActivityInstance()).edit().putString("content", s.toString()).commit();
        StringTokenizer st = new StringTokenizer(s.toString());
        int words = st.countTokens();
        ViewObjectsHolder.getWordCount().setText(words+" words");
    }
}
