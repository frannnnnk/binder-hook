package com.itdog.hook.binder;

import android.content.Context;
import android.os.IBinder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * Created by tianbei on 2017/3/17.
 */

public class BinderHookHelper {

    final static String CLIPBOARD_SERVICE = "clipboard";

    public static void hookClipBoardService(Context context) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {

            Class<?> serviceManagerClass = Class.forName("android.os.ServiceManager");
            Method getServiceMethod = serviceManagerClass.getDeclaredMethod("getService", String.class);
            IBinder rawBinder = (IBinder) getServiceMethod.invoke(null, CLIPBOARD_SERVICE);

            IBinder proxyBinder = (IBinder) Proxy.newProxyInstance(context.getClassLoader(),
                    new Class[]{IBinder.class}, new RawBinderHookHandler(context, rawBinder));
            Field field = serviceManagerClass.getDeclaredField("sCache");
            field.setAccessible(true);
            Map<String, IBinder> sCache = (Map<String, IBinder>) field.get(null);
            sCache.put(CLIPBOARD_SERVICE, proxyBinder);
    }

}
