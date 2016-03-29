/*
 * $Id$
 * 04/12/15
 */
package com.extremeboredom.wordattack;

import android.app.Activity;
import android.preference.PreferenceManager;
import android.widget.EditText;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapLabel;
import com.beardedhen.androidbootstrap.BootstrapProgressBar;
import com.extremeboredom.wordattack.callbacks.EditorTextWatcher;

import java.util.StringTokenizer;

public class ViewObjectsHolder {

    private static BootstrapProgressBar progressBar = null;
    private static Activity globalInstance = null;
    private static BootstrapButton pauseButton = null;
    private static EditText editor = null;
    private static BootstrapLabel wordCount = null;

    public static void init(Activity editorActivity) {
        progressBar = (BootstrapProgressBar) editorActivity.findViewById(R.id.main_timer);
        progressBar.setStriped(false);
        progressBar.setAnimated(true);
        editor = (EditText) editorActivity.findViewById(R.id.main_editor);
        editor.removeTextChangedListener(new EditorTextWatcher());
        editor.setText("");
        wordCount = (BootstrapLabel) editorActivity.findViewById(R.id.wordCount);
        globalInstance = editorActivity;
        String savedText = PreferenceManager.getDefaultSharedPreferences(editorActivity).getString("content", "");
        int start = PreferenceManager.getDefaultSharedPreferences(editorActivity).getInt("start", 0);
        int end = PreferenceManager.getDefaultSharedPreferences(editorActivity).getInt("end", 0);
        if (savedText == "") {

        } else {
            editor.setText(savedText);
            editor.setSelection(start, end);
        }
        StringTokenizer st = new StringTokenizer(savedText.toString());
        int words = st.countTokens();
        wordCount.setText(words + " words");
        editor.addTextChangedListener(new EditorTextWatcher());
        pauseButton = (BootstrapButton) editorActivity.findViewById(R.id.main_pause);
    }

    public static Activity getActivityInstance() {
        return globalInstance;
    }

    public static BootstrapProgressBar getProgressBar() {
        return progressBar;
    }

    public static BootstrapButton getPauseButton() {
        return pauseButton;
    }

    public static int getSelectionStart() {
        return editor.getSelectionStart();
    }

    public static int getSelectionEnd() {
        return editor.getSelectionEnd();
    }

    public static EditText getEditor() {
        return editor;
    }

    public static BootstrapLabel getWordCount() {
        return wordCount;
    }
}
