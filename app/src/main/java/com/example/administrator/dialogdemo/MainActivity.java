package com.example.administrator.dialogdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnSubmit;
    private AlertDialog mDialog;
    private TextView mTvCancel;
    private TextView mTvOk;
    private TextView mGetVerifyCode;
    private MyCountDownTimer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtnSubmit = (Button) findViewById(R.id.btn_submit);

        mBtnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                showDialog();
                break;
        }
    }
    AlertDialog mShowAlerDialog;
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View container = LayoutInflater.from(this).inflate(R.layout.container_inputer, null);

        mTvCancel = (TextView) container.findViewById(R.id.verify_input_cancel);
        mTvOk = (TextView) container.findViewById(R.id.verify_input_ok);
//        EditText mYzmNums = (EditText) container.findViewById(R.id.verify_editext);
//        TextView mVerifyState = (TextView) container.findViewById(R.id.verify_state);
        mGetVerifyCode = (TextView) container.findViewById(R.id.verfiy_get_code);

        mDialog = builder.setView(container).create();
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface anInterface, int i, KeyEvent event) {
                if (i == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    if (mShowAlerDialog == null) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                        mShowAlerDialog = builder1
                                .setTitle("温馨提示")
                                .setMessage("现在退出么")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface anInterface, int i) {
                                        if (mDialog != null) {
                                            if (mTimer != null) {
                                                mTimer.cancel();
                                            }
                                            mDialog.dismiss();
                                        }
                                    }
                                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface anInterface, int i) {

                                    }
                                }).create();
                    }
                    mShowAlerDialog.show();
                }
                return false;
            }
        });
        mDialog.show();
        WindowManager.LayoutParams attributes = mDialog.getWindow().getAttributes();
        mDialog.getWindow().setAttributes(attributes);


        mGetVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "getcode", Toast.LENGTH_LONG).show();
                mTimer = new MyCountDownTimer(60000, 1000);
                mGetVerifyCode.setClickable(false);
                mTimer.start();

            }
        });

        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTimer != null) {
                    mTimer.cancel();
                }
                mDialog.dismiss();
                Toast.makeText(MainActivity.this, "cancel", Toast.LENGTH_LONG).show();
            }
        });
        mTvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_LONG).show();
            }
        });
    }

    class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        public void onFinish() {
            mGetVerifyCode.setClickable(true);
            mGetVerifyCode.setText("重新获取");
        }

        public void onTick(long millisUntilFinished) {
            mGetVerifyCode.setText(millisUntilFinished / 1000 + "s");
        }
    }

}
