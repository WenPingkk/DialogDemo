package com.example.administrator.dialogdemo;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;

/**
 * Created by YinZeTong on 2017/10/11.
 */

public class VerifyCodeDialog extends Dialog{

    public VerifyCodeDialog(@NonNull Context context) {
        super(context);
    }

    public VerifyCodeDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected VerifyCodeDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
