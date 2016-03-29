/*
 * $Id$
 * 28/02/16
 */
package com.extremeboredom.wordattack;

import com.extremeboredom.wordattack.punishment.Punishment;

public class CurrentSetting {
    Punishment punishment;
    int wordLimit;
    int timeLimit;

    public CurrentSetting(int mode, int wordLimit, Punishment punishment) {
        this.punishment = punishment;
        this.timeLimit = timeLimit;
        this.wordLimit = wordLimit;
    }
}
