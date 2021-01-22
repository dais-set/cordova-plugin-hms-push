/*
    Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.

    Licensed under the Apache License, Version 2.0 (the "License")
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        https://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package com.huawei.hms.cordova.push.receiver;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.huawei.hms.cordova.push.HMSPush;
import com.huawei.hms.cordova.push.local.HmsLocalNotificationActionPublisher;

import org.apache.cordova.CordovaPlugin;

import org.json.JSONException;

public class NotificationActionHandler implements Runnable {
    private static String TAG = NotificationActionHandler.class.getSimpleName();
    Bundle bundle;
    private static CordovaPlugin staticPlugin = HMSPush.getPlugin();

    public NotificationActionHandler(Context context, Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public void run() {


        if (staticPlugin != null) {
            HmsLocalNotificationActionPublisher hmsLocalNotificationActionPublisher = new HmsLocalNotificationActionPublisher(staticPlugin);

            try {
                hmsLocalNotificationActionPublisher.notifyNotificationAction(bundle);
            } catch (JSONException e) {
                Log.w(TAG, "run: " + e.getLocalizedMessage());
            }
        }
    }
}

