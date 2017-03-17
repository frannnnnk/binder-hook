package com.itdog.hook.binder;

import android.content.Context;
import android.os.IBinder;

import com.itdog.hook.binder.IClipBoardBinderHookHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by tianbei on 2017/3/17.
 */

public class RawBinderHookHandler implements InvocationHandler {

    private IBinder rawBinder;

    private Context context;

    private Class clipBoardInterface;

    private Class clipBoardStubInterface;

    public RawBinderHookHandler(Context context, IBinder rawBinder) throws ClassNotFoundException {

        this.rawBinder = rawBinder;
        this.context = context;

        this.clipBoardInterface = Class.forName("android.content.IClipboard");
        this.clipBoardStubInterface = Class.forName("android.content.IClipboard$Stub");

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if ("queryLocalInterface".equals(method.getName())) {

            return Proxy.newProxyInstance(context.getClassLoader(), new Class[]{clipBoardInterface},
                    new IClipBoardBinderHookHandler(rawBinder, clipBoardStubInterface));

        }

        return method.invoke(rawBinder, args);
    }

}
