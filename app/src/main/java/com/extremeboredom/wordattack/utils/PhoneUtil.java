/* $Id$  */
/**
 * Copyright 2014 Zhenguo Jin
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.extremeboredom.wordattack.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * 手机组件调用工具类
 *
 * @author jingle1267@163.com
 */
public final class PhoneUtil {

    /**
     * Don't let anyone instantiate this class.
     */
    private PhoneUtil() {
        throw new Error("Do not need instantiate!");//No i18n
    }

    /**
     * 调用系统发短信界面
     *
     * @param activity    Activity
     * @param phoneNumber 手机号码
     * @param smsContent  短信内容
     */
    public static void sendMessage(Context activity, String phoneNumber,
                                   String smsContent) {
        if (phoneNumber == null || phoneNumber.length() < 4) {
            return;
        }
        Uri uri = Uri.parse("smsto:" + phoneNumber);//No i18n
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", smsContent);//No i18n
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(it);

    }

    /**
     * 调用系统打电话界面
     *
     * @param context     上下文
     * @param phoneNumber 手机号码
     */
    public static void callPhones(Context context, String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() < 1) {
            return;
        }
        Uri uri = Uri.parse("tel:" + phoneNumber);//No i18n
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
