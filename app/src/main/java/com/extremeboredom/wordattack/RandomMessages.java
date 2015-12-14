/*
 * $Id$
 * 04/12/15
 */
package com.extremeboredom.wordattack;

import com.extremeboredom.wordattack.utils.RandomUtils;

public class RandomMessages {
    private static String[] TIMEOUT_TITLES = {
            "Hey, You still there?",
            "Err, I thought you wanted to write.",
            "This isn't working, right?"
    };

    private static String[] TIMEOUT_QUOTES = {
            "\"Success is the fruit of consistent effort.\"",
            "\"Hard work works. Always.\"",
            "\"Without hard work, nothing grows but weeds.\"",
            "\"Talent is cheaper than table salt. What separates the talented individual from the successful one is a lot of hard work.\"",
            "\"The road to success is not easy to navigate, but with hard work, drive and passion, it's possible to achieve the American dream.\"",
            "\"An unsteady mind is the source of all distractions.\"",
            "\"Success is the result of perfection, hard work, learning from failure, loyalty, and persistence.\"",
            "\"A dream doesn't become reality through magic; it takes sweat, determination and hard work.\"",
    };

    public static String getRandomTitle() {
        return getRandomItemFromArray(TIMEOUT_TITLES);
    }

    public static String getRandomQuote() {
        return getRandomItemFromArray(TIMEOUT_QUOTES);
    }

    private static String getRandomItemFromArray(String[] array) {
        int index = RandomUtils.getRandom(array.length);
        return array[index];
    }
}
