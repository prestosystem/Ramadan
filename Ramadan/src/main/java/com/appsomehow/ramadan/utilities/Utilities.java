package com.appsomehow.ramadan.utilities;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.NotificationCompat;
import com.appsomehow.ramadan.R;
import com.appsomehow.ramadan.receiver.NotificationCancelReceiver;
import com.dibosh.experiments.android.support.customfonthelper.AndroidCustomFontSupport;

/**
 * Created by Sharif on 5/27/2014.
 */
public class Utilities {

    public static Typeface getFont(Context context) {
        return Typeface.createFromAsset(context.getAssets(),
                "fonts/" + context.getString(R.string.font_solaimanlipi));
    }

    public static android.text.SpannableString getBanglaText(String banglaText, Context context) {
        return AndroidCustomFontSupport.getCorrectedBengaliFormat(banglaText, getFont(context), -1);
    }


    public static void customNotification(Context context) {
        Intent notificationIntent = new Intent(context, NotificationCancelReceiver.class);
        notificationIntent.setAction("notification_cancelled");
        PendingIntent deletePendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        // Alarm alarm = new Alarm(context);
        // alarm.setOneTimeAlarm(NotificationCancelReceiver.class, 10);

        int mNotificationId = 001;

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.icon_tarabih)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!").setDeleteIntent(deletePendingIntent);
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        mBuilder.setAutoCancel(true);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }


    public static android.text.SpannableString[] banglaSpannableStrings(String[] banglaRegionNames, Context context) {
        android.text.SpannableString[] banglaText = new android.text.SpannableString[banglaRegionNames.length];
        for (int counter = 0; counter < banglaRegionNames.length; counter++) {
            banglaText[counter] = getBanglaText(banglaRegionNames[counter], context);
        }
        return banglaText;
    }

    public static String replaceBanglaCharacter(String input) {
        char[] array = input.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            stringBuilder.append(Character.toChars((int) array[i] + 2486));
        }
        return stringBuilder.toString();
    }
}
