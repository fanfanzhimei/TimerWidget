package com.zhi.timerwidget;

import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import com.zhi.service.TimerWidgetService;

/**
 * Created by Administrator on 2016/11/12.
 */
public class TimerWidgetProvider extends AppWidgetProvider{

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        context.startService(new Intent(context, TimerWidgetService.class));
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        context.stopService(new Intent(context, TimerWidgetService.class));
    }
}