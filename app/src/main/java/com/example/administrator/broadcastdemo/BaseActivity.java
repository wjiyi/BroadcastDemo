package com.example.administrator.broadcastdemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * 所有活动的父类
 * Created by Administrator on 2018/4/29 0029.
 */

public class BaseActivity extends Activity {

    private ForceOfflineReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    protected void onResume()
    {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.administrator.broadcastdemo.FORCE_OFFLINE");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver,intentFilter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(receiver != null)
        {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    public static class ForceOfflineReceiver extends BroadcastReceiver
    {
        public void onReceive(final Context context, final Intent intent)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("You are forced to be offline.Please try to login again.");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finishAll();
                    Intent intent1 = new Intent(context,LoginActivity.class);
                    context.startActivity(intent1);
                }
            });
            builder.show();
        }
    }
}
