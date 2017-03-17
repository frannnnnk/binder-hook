package com.itdog.hook.binder;

import android.content.ClipData;
import android.os.IBinder;
import android.os.IInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by tianbei on 2017/3/17.
 */

public class IClipBoardBinderHookHandler implements InvocationHandler {

    private IInterface clipboardInterface;

    public IClipBoardBinderHookHandler(IBinder rawBinder, Class<?> clipboardStubInterface)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method asInterfaceMethod = clipboardStubInterface.getMethod("asInterface", IBinder.class);
        this.clipboardInterface = (IInterface) asInterfaceMethod.invoke(null, rawBinder);

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if ("getPrimaryClip".equals(method.getName())) {

            return ClipData.newPlainText(null, "You are hooked!!!");

        }

        if ("hasPrimaryClip".equals(method.getName())) {

            return true;

        }

        return method.invoke(clipboardInterface, args);
    }

}
