package com.dotgreen.notistack;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Base64;
import android.util.Log;

import com.dotgreen.notistack.util.ImageUtils;
import com.firebase.client.Firebase;

import java.io.ByteArrayOutputStream;

/**
 * Created by hieuapp on 06/10/2016.
 */

public class ListenerNotifications extends NotificationListenerService {

    public static final String ROOT = "https://androidstatus.firebaseio.com/";
    public static final String NOTIFICATION_CHILD = "notification";
    public static final String TAG = "NotificationListener";
    Firebase root, notiRef;


    @Override
    public void onCreate() {
        super.onCreate();
        root = new Firebase(ROOT);
        notiRef = root.child(NOTIFICATION_CHILD);

    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        NotiStack notiStack = new NotiStack(sbn);
        String imgBase64Icon = createImageBase64Icon(notiStack);
        notiStack.setImage(imgBase64Icon);
        String sbnId = String.valueOf(sbn.getId());
        notiRef.child(sbnId).setValue(notiStack);
        Log.i("Notifications","have a new Notification");
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        int idRemoved = sbn.getId();
        notiRef.child(String.valueOf(idRemoved)).setValue(null);
        Log.i(TAG,"Remove a notification");
    }

    private String createImageBase64Icon(NotiStack notiStack){
        try {
            Drawable icon = getPackageManager().getApplicationIcon(notiStack.getPackageName());
            if(icon != null){
                Bitmap bitmap = ImageUtils.drawableToBitmap(icon);
                ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteOutput);
                byte[] byteImage = byteOutput.toByteArray();
                String imageBase64 = Base64.encodeToString(byteImage,Base64.DEFAULT);
                return imageBase64;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
