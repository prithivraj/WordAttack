package com.extremeboredom.wordattack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.rey.material.widget.Slider;

public class EditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceProvider.registerDefaultIconSets();
        setContentView(R.layout.activity_editor);
        new MaterialDialog.Builder(this)
                .title("Settings")
                .positiveText("Start")
                .cancelable(false)
                .customView(R.layout.activity_settings, true)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    private int selectedModeID = 0;
                    private int timeOut = 0;

                    @Override
                    public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                        RadioGroup modeSelector = (RadioGroup) materialDialog.findViewById(R.id.settings_mode);
                        int selectedMode = modeSelector.getCheckedRadioButtonId();
                        Slider timeoutSlider = (Slider) findViewById(R.id.settings_timeoutSlider);
                        timeOut = timeoutSlider.getValue();

                        if (selectedMode == R.id.settings_mode_1) {

                        } else {

                        }
                    }
                })
                .show();


    }
}
