package com.mg.axe.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean isSupportBlue = false;

    private Button mBtnOpen;
    private BluetoothAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取本地蓝牙的设配器
        mAdapter = BluetoothAdapter.getDefaultAdapter();
        //判断蓝牙功能是否存在（有些机器不支持蓝牙）
        if(mAdapter == null){
            //不支持蓝牙
            isSupportBlue = false;
            showMessage("该设备不支持蓝牙");
        }else{
            isSupportBlue = true;

        }

        mBtnOpen = (Button) findViewById(R.id.btnSwitch);
        mBtnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开本地蓝牙设备
                if(!isSupportBlue){
                    return;
                }
                //蓝牙是否关闭？
                if(mAdapter.isEnabled()){
                    showMessage("蓝牙处于打开状态");
                    boolean isopen = mAdapter.disable();
                    showMessage("蓝牙的状态"+isopen);
                }else{
                    //直接使用 enable打开
//                    boolean isopen = mAdapter.enable();
//                    showMessage("蓝牙的状态"+isopen);

                    //直接使用 系统API打开
                    Intent open = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(open,1000);
                }
            }
        });
    }

    private void showMessage(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if(resultCode == RESULT_CANCELED) {
                showMessage("请求失败");
            }else{
                showMessage("请求成功");
            }
        }
    }
}
