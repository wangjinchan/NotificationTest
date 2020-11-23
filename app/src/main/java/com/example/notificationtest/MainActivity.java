package com.example.notificationtest;

import androidx.core.app.NotificationCompat;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button sendNotice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendNotice = findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_notice:
                //获取通知管理实例
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                //8.0一张版本判断
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                    NotificationChannel channel=new NotificationChannel("important","Important",NotificationManager.IMPORTANCE_HIGH);
                    assert manager != null;
                    manager.createNotificationChannel(channel);
                }
                //通知点击事项
                Intent intent=new Intent(this,NotificationActivity.class);
                PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);

                Notification notification= new NotificationCompat.Builder(this,"important")
                        .setContentTitle("收到一条通知")
                        .setContentText("你好")
                        .setSmallIcon(R.mipmap.ic_launcher)//通知图标
                        .setContentIntent(pendingIntent)//点击跳到通知详情
                        .setAutoCancel(true)//当点击通知后显示栏的通知不再显示
                        .build();
                assert manager != null;
                manager.notify(1,notification);
                break;
            default:
                break;
        }
    }
}
