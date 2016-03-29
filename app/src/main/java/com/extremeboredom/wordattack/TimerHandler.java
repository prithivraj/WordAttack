/*
 * $Id$
 * 24/11/15
 */
package com.extremeboredom.wordattack;

import android.app.Activity;
import android.os.CountDownTimer;

import com.extremeboredom.wordattack.punishment.Punishment;
import com.extremeboredom.wordattack.utils.ToastUtils;

public class TimerHandler {
    private static long timeout = -1;
    private static long ONE_SECOND = 100;
    private static CountDownTimer waitTimer = null;
    private static TimerHandler ourInstance = new TimerHandler();
    private static Punishment currentPunishment = null;
    private static long lastTickedTime = 0;


    private TimerHandler() {
    }

    public static TimerHandler getInstance() {
        return ourInstance;
    }

    public void resume() {
        if (currentPunishment == null) {
            return;
        }
        waitTimer = createNewTimer(lastTickedTime);
        waitTimer.start();
    }

    public boolean isRunning() {
        return lastTickedTime > 0;
    }

    private CountDownTimer createNewTimer(long startTime) {
        return new CountDownTimer(startTime, ONE_SECOND) {
            public void onTick(long millisUntilFinished) {
                lastTickedTime = millisUntilFinished;
                float percentage = (int) (((float) millisUntilFinished / timeout) * 100);
                ViewObjectsHolder.getProgressBar().setProgress((int) percentage);
            }

            public void onFinish() {
                ViewObjectsHolder.getProgressBar().setProgress(0);
                Activity activity = ViewObjectsHolder.getActivityInstance();
                if (activity.hasWindowFocus()) {
                    currentPunishment.punish(ViewObjectsHolder.getActivityInstance());
                } else {
                    ToastUtils.makeToastLong(activity, "Hi!");
                    //TODO: Send push notification
                }
            }
        };
    }
    public void resetClock() {
        if (waitTimer != null) {
            waitTimer.cancel();
        }
        startCountDown(currentPunishment, timeout);
    }

    public void pauseClock() {
        if (waitTimer != null) {
            waitTimer.cancel();
            waitTimer = null;
        }
        return;
    }

    public void startCountDown(Punishment p, long secs) {
        timeout = secs;
        lastTickedTime = secs;
        currentPunishment = p;
        resume();
    }
}
