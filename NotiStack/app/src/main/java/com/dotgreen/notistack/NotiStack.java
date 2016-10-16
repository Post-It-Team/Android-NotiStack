package com.dotgreen.notistack;

import android.annotation.TargetApi;
import android.app.Notification;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;

/**
 * Created by hieuapp on 06/10/2016.
 */

public class NotiStack {

    private String key;
    private int id;
    private long timestamp;
    private String packageName;
    private boolean isOngoing;
    private boolean isClearable;
    private MiniNotification notification;
    private String image;

    public NotiStack(){

    }

    @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
    public NotiStack(StatusBarNotification sbn){
        this.key = sbn.getKey();
        this.timestamp = sbn.getPostTime();
        this.packageName = sbn.getPackageName();
        this.isOngoing = sbn.isOngoing();
        this.isClearable = sbn.isClearable();
        this.notification = new MiniNotification(sbn.getNotification());
        this.id = sbn.getId();
    }

    public String getKey() {
        return key;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getPackageName() {
        return packageName;
    }

    public MiniNotification getNotification() {
        return notification;
    }

    public int getId() {
        return id;
    }

    public boolean isOngoing() {
        return isOngoing;
    }

    public boolean isClearable() {
        return isClearable;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    static class MiniNotification{

        private String title;
        private boolean allowDuringSetup;
        private String subText;
        private int icon;
        private String text;
        private int progress;
        private int progressMax;
        private boolean showWhen;
        private boolean progressIndeterminate;
        private int priority;
        private String picutre;

        public MiniNotification(){

        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        public MiniNotification(Notification notification){
            Bundle bundle = notification.extras;
            this.title = bundle.getString("android.title");
            this.subText = bundle.getString("android.subText");
            this.text = bundle.getString("android.text");
            this.icon = notification.icon;
            this.priority = notification.priority;
            this.progress = bundle.getInt("android.progress");
            this.progressMax = bundle.getInt("android.progressMax");
            this.progressIndeterminate = bundle.getBoolean("android.progressIndeterminate");
        }

        public String getTitle() {
            return title;
        }


        public boolean isAllowDuringSetup() {
            return allowDuringSetup;
        }

        public String getSubText() {
            return subText;
        }

        public int getIcon() {
            return icon;
        }

        public String getText() {
            return text;
        }

        public int getProgress() {
            return progress;
        }

        public int getProgressMax() {
            return progressMax;
        }

        public boolean isShowWhen() {
            return showWhen;
        }

        public boolean isProgressIndeterminate() {
            return progressIndeterminate;
        }

        public int getPriority() {
            return priority;
        }

        public String getPicutre() {
            return picutre;
        }
    }
}
