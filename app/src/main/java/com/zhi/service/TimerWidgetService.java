package com.zhi.service;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.zhi.timerwidget.R;
import com.zhi.timerwidget.TimerWidgetProvider;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/11/12.
 */
public class TimerWidgetService extends Service {

    private Timer timer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        timer = new Timer();
        timer.schedule(new TimerWidgetTask(), 0, 1000);
    }

    private final class TimerWidgetTask extends TimerTask {

        @Override
        public void run() {
            /*获取当前日期时间*/
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd : HH:mm:ss");
            String date = format.format(new Date());
            /*将当前日期时间显示在TextView上*/
            RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.timer_appwidget);
            remoteViews.setTextViewText(R.id.textView, date);
            /*为TextView 设置点击事件*/
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 10, new Intent(Intent.ACTION_CALL, Uri.parse("tel:665543")), 0);
            remoteViews.setOnClickPendingIntent(R.id.textView, pendingIntent);
            /*更新widget*/
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
            appWidgetManager.updateAppWidget(new ComponentName(getApplicationContext(), TimerWidgetProvider.class), remoteViews);
        }
    }
}