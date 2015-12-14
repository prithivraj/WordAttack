package com.extremeboredom.wordattack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.extremeboredom.wordattack.callbacks.PauseButtonClickListner;
import com.extremeboredom.wordattack.punishment.DialogPunishment;
import com.rey.material.widget.Slider;

public class EditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceProvider.registerDefaultIconSets();
        setContentView(R.layout.activity_editor);

        ViewObjectsHolder.init(this);

        bindListners();

        new MaterialDialog.Builder(this)
                .title("Settings")
                .positiveText("Start writing")
                .cancelable(false)
                .customView(R.layout.activity_settings, true)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    private int selectedModeID = 0;
                    private int timeOut = 0;

                    @Override
                    public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                        RadioGroup modeSelector = (RadioGroup) materialDialog.findViewById(R.id.settings_mode);
                        int selectedMode = modeSelector.getCheckedRadioButtonId();
                        Slider timeoutSlider = (Slider) materialDialog.findViewById(R.id.settings_timeoutSlider);
                        timeOut = timeoutSlider.getValue() * 1000;

                        if (selectedMode == R.id.settings_mode_1) {
                            TimerHandler.getInstance().startCountDown(new DialogPunishment(), timeOut);
                        } else {

                        }
                    }
                })
                .show();
    }

    @Override
    protected void onPause() {
        TimerHandler.getInstance().pauseClock();
        super.onPause();
    }

    @Override
    protected void onResume() {
        TimerHandler.getInstance().resume();
        super.onResume();
    }

    private void bindListners() {
        ViewObjectsHolder.getPauseButton().setOnClickListener(new PauseButtonClickListner());
    }
}
