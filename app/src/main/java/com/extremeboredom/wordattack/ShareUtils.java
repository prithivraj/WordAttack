/*
 * $Id$
 * 30/03/16
 */
package com.extremeboredom.wordattack;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.widget.Toast;

public class ShareUtils {

    public static void addToClipboard(Activity activity, String s) {
        ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Activity.CLIPBOARD_SERVICE);
        clipboard.setPrimaryClip(ClipData.newPlainText("WordAttack", s.toString()));
        Toast.makeText(activity, "Your text is also on the clipboard.", Toast.LENGTH_LONG).show();
    }

    public static void shareText(Activity activity, String shareBody) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        activity.startActivity(Intent.createChooser(sharingIntent, "Share using.."));
    }
}
