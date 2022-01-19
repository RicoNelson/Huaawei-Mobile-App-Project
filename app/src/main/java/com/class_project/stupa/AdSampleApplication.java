package com.class_project.stupa;

import android.app.Application;

import com.huawei.hms.ads.HwAds;

public class AdSampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the HUAWEI Ads SDK.
        HwAds.init(this);
    }
}