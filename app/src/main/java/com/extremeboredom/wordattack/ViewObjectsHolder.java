/*
 * $Id$
 * 04/12/15
 */
package com.extremeboredom.wordattack;

import android.app.Activity;
import android.widget.EditText;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapProgressBar;
import com.extremeboredom.wordattack.callbacks.EditorTextWatcher;

public class ViewObjectsHolder {

    private static BootstrapProgressBar progressBar = null;
    private static Activity globalInstance = null;
    private static BootstrapButton pauseButton = null;
    private static EditText editor = null;

    public static void init(Activity editorActivity) {
        progressBar = (BootstrapProgressBar) editorActivity.findViewById(R.id.main_timer);
        progressBar.setStriped(false);
        progressBar.setAnimated(true);
        editor = (EditText) editorActivity.findViewById(R.id.main_editor);
        editor.addTextChangedListener(new EditorTextWatcher());
        pauseButton = (BootstrapButton) editorActivity.findViewById(R.id.main_pause);
        globalInstance = editorActivity;

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
}
