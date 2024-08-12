package com.example.epod.application.job_order.domain.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;


public class JobOrderBoundService extends Service {
    public JobOrderServiceBinder jobOrderServiceBinder = new JobOrderServiceBinder();

    public JobOrderBoundService() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return jobOrderServiceBinder;
    }

    public class JobOrderServiceBinder extends Binder {
        JobOrderBoundService getService() {
            return JobOrderBoundService.this;
        }
    }
}
