/*
 * $Id$
 * 24/11/15
 */
package com.extremeboredom.wordattack;

import android.os.CountDownTimer;

public class TimerHandler {
    private static int timeout = -1;
    private static int ONE_SECOND = 1000;
    private static CountDownTimer waitTimer = null;
    private static TimerHandler ourInstance = new TimerHandler();

    private TimerHandler() {
    }

    public static TimerHandler getInstance() {
        return ourInstance;
    }

    public void start(int timeOut) {
        timeout = timeOut;
        startClock();
    }

    private void startClock() {
        waitTimer = new CountDownTimer(timeout, ONE_SECOND) {

            public void onTick(long millisUntilFinished) {
                //called every ONE_SECOND milliseconds, which could be used to
                //TODO: Update timer
            }

            public void onFinish() {
                //After timeout milliseconds (60 sec) finish current
                //TODO: Show punishment
            }
        }.start();
    }

    public void resetClock() {
        if (waitTimer == null) {
            return;
        }
        waitTimer.onTick(timeout);
    }
}
