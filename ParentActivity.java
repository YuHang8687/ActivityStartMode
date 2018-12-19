package com.example.activitystartmode;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//定义基类
public class ParentActivity extends AppCompatActivity {
    private Button b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log日志打印相应的类名、任务栈的ID以及hashc值来区分它的启动模式
        Log.i("duyuhang", "*****onCreate()方法******");
        Log.i("duyuhang", "onCreate：" + getClass().getSimpleName() + " TaskId: " + getTaskId() + " hasCode:" + this.hashCode());
        dumpTaskAffinity();
        setContentView(R.layout.activity_parent);
        b4 = (Button) findViewById(R.id.b4);

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                通过跳转相同类型的类来看该实例是复用还是新建
                startActivity(new Intent(ParentActivity.this, MainActivity.class));
            }
        });
    }


    //    如果是复用会回调该方法，并且将跳转的intent对象传入该方法
    @Override   
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(this, intent.getStringExtra("name"), Toast.LENGTH_SHORT).show();
        Log.i("duyuhang", "*****onNewIntent()方法*****");
        Log.i("duyuhang", "onNewIntent：" + getClass().getSimpleName() + " TaskId: " + getTaskId() + " hasCode:" + this.hashCode());
        dumpTaskAffinity();
    }

    protected void dumpTaskAffinity() {
        try {
            ActivityInfo info = this.getPackageManager()
                    .getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            Log.i("duyuhang", "taskAffinity:" + info.taskAffinity);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
