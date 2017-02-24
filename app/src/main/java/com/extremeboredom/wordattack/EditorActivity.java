package com.extremeboredom.wordattack;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.beardedhen.androidbootstrap.font.FontAwesome;
import com.extremeboredom.wordattack.callbacks.PauseButtonClickListner;
import com.extremeboredom.wordattack.punishment.DialogPunishment;
import com.extremeboredom.wordattack.punishment.LoseWorkPunishment;
import com.extremeboredom.wordattack.punishment.NoisePunishment;
import com.extremeboredom.wordattack.punishment.Punishment;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

public class EditorActivity extends AppCompatActivity {

    public static MenuItem shareIcon = null;
    MaterialDialog splashDialog = null;//test
    MaterialDialog settingsDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceProvider.registerDefaultIconSets();
        setContentView(R.layout.activity_editor);
        ViewObjectsHolder.init(this);
        splashDialog = new MaterialDialog.Builder(this)
                .cancelable(false)
                .customView(R.layout.activity_splash, true)
                .build();
        settingsDialog = new MaterialDialog.Builder(this)
                .title("Settings")
                .positiveText("Write")
                .cancelable(false)
                .customView(R.layout.activity_settings, true)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    private int selectedModeID = 0;
                    private int timeOut = 0;

                    @Override
                    public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                        RadioGroup modeSelector = (RadioGroup) materialDialog.findViewById(R.id.settings_mode);
                        int selectedMode = modeSelector.getCheckedRadioButtonId();

                        DiscreteSeekBar timeoutSlider = (DiscreteSeekBar) materialDialog.findViewById(R.id.settings_timeoutSlider);
                        timeOut = timeoutSlider.getProgress() * 1000;

                        DiscreteSeekBar wordLimit = (DiscreteSeekBar) materialDialog.findViewById(R.id.settings_wordlimitSlider);
                        int maxWordLimit = wordLimit.getProgress();

                        Punishment currentPunishment = null;
                        if (selectedMode == R.id.settings_mode_1) {
                            currentPunishment = new DialogPunishment();
                        } else if (selectedMode == R.id.settings_mode_2) {
                            currentPunishment = new NoisePunishment();
                        } else if (selectedMode == R.id.settings_mode_3) {
                            currentPunishment = new LoseWorkPunishment(ViewObjectsHolder.getEditor());
                        }
                        TimerHandler.getInstance().startCountDown(currentPunishment, timeOut);
                        CurrentSetting.setInstance(new CurrentSetting(maxWordLimit, currentPunishment));
                    }
                })
                .build();
        bindListners();
        SharedPreferences userDetails = getSharedPreferences("userdetails", MODE_PRIVATE);
        boolean shouldSkipSplash = userDetails.getBoolean("dontShow", false);

        if (shouldSkipSplash) {
            settingsDialog.show();
        } else {
            splashDialog.show();
        }
    }

    @Override
    protected void onPause() {
        TimerHandler.getInstance().pauseClock();
        super.onPause();
    }

    @Override
    protected void onResume() {
        TimerHandler.getInstance().resume();
        ViewObjectsHolder.getPauseButton().setFontAwesomeIcon(FontAwesome.FA_PAUSE);
        super.onResume();
    }

    private void bindListners() {
        ViewObjectsHolder.getPauseButton().setOnClickListener(new PauseButtonClickListner());
        splashDialog.findViewById(R.id.splash_gotit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatCheckBox checkBox = (AppCompatCheckBox) splashDialog.findViewById(R.id.splash_chkbox_dontshowagain);
                SharedPreferences.Editor editor = getSharedPreferences("userdetails", MODE_PRIVATE).edit();
                editor.putBoolean("dontShow", checkBox.isChecked());
                editor.commit();
                splashDialog.dismiss();
                settingsDialog.show();
            }
        });
    }

    public void showSettings() {
        TimerHandler.getInstance().pauseClock();
        settingsDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TimerHandler.getInstance().relaxPunishments();
        TimerHandler.getInstance().pauseClock();
        TimerHandler.getInstance().resetClock();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem settingsIcon = menu.findItem(R.id.menu_settings);
        settingsIcon.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (shareIcon.isVisible()) {
                    new MaterialDialog.Builder(ViewObjectsHolder.getActivityInstance())
                            .title("New writing?")
                            .positiveText("Change settings")
                            .content("Do you want to adjust the settings of this session or start a new one?")
                            .cancelable(true)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                                    showSettings();
                                }
                            })
                            .negativeText("New text")
                            .onNegative(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                                    ViewObjectsHolder.init(ViewObjectsHolder.getActivityInstance());
                                    shareIcon.setVisible(false);
                                    showSettings();
                                }
                            })
                            .show();
                } else {
                    showSettings();
                }
                return false;
            }
        });

        shareIcon = menu.findItem(R.id.menu_share);
        shareIcon.setVisible(false);

        shareIcon.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String s = ViewObjectsHolder.getEditor().getText().toString();
                ShareUtils.addToClipboard(ViewObjectsHolder.getActivityInstance(), s);
                ShareUtils.shareText(ViewObjectsHolder.getActivityInstance(), s);
                return false;
            }
        });
        return true;

    }
}
