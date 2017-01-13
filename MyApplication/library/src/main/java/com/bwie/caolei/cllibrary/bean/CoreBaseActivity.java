package com.bwie.caolei.cllibrary.bean;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.bwie.caolei.cllibrary.bean.utils.ActivityCollector;
import com.bwie.caolei.cllibrary.bean.utils.PermissionListener;
import com.bwie.caolei.cllibrary.bean.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 1.
 * 2.曹磊
 * 3.2016/11/23
 */
public abstract class CoreBaseActivity extends Activity {

    protected String TAG;
    private boolean isOpen = false;
    private static PermissionListener mListener;
    Unbinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏透明
        setStatusBarColor();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    public void setStatusBarColor() {
        StatusBarUtil.setTransparent(this);
// StatusBarUtil.setTranslucent(this);
    }

    private void init(Bundle savedInstanceState) {
        TAG = getClass().getSimpleName();
        this.setContentView(this.getLayoutId());
        binder = ButterKnife.bind(this);
        this.initView(savedInstanceState);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        if (binder != null) binder.unbind();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void setContentView(int layoutResID) {
        if (isOpen()) {
            super.setContentView(layoutResID);
        } else {
            View view = LayoutInflater.from(this).inflate(layoutResID, null);
        }
    }

    public boolean isOpen() {
        return isOpen;
    }

    public abstract int getLayoutId();

    public abstract void initView(Bundle savedInstanceState);

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    List<String> deniedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        }
                    }
                    if (deniedPermissions.isEmpty()) {
                        mListener.onGranted();
                    } else {
                        mListener.onDenied(deniedPermissions);
                    }
                }
                break;
            default:
                break;
        }
    }

}
