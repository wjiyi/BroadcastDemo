package com.example.administrator.broadcastdemo;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import dalvik.annotation.TestTarget;

/**
 * 用于管理所有的活动
 * Created by Administrator on 2018/4/29 0029.
 */

public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();
    public static void addActivity(Activity activity)
    {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity)
    {
        activities.remove(activity);
    }

    public static void finishAll()
    {
        for(Activity activity:activities)
        {
            if(!activity.isFinishing())
            {
                activity.finish();
            }
        }
        activities.clear();
    }
}
