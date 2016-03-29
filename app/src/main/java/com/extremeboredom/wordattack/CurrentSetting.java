/*
 * $Id$
 * 28/02/16
 */
package com.extremeboredom.wordattack;

import com.extremeboredom.wordattack.punishment.Punishment;

public class CurrentSetting {

    static CurrentSetting instance = null;
    Punishment punishment;
    int wordLimit;

    public CurrentSetting(int wordLimit, Punishment punishment) {
        this.punishment = punishment;
        this.wordLimit = wordLimit;
    }

    public static CurrentSetting getInstance() {
        return instance;
    }

    public static void setInstance(CurrentSetting currentSetting) {
        instance = currentSetting;
    }

    public Punishment getPunishment() {
        return punishment;
    }

    public int getWordLimit() {
        return wordLimit;
    }
}
