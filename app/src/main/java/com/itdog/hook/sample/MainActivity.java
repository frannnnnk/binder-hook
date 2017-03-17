package com.itdog.hook.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.itdog.hook.binder.BinderHookHelper;

import java.lang.reflect.InvocationTargetException;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            BinderHookHelper.hookClipBoardService(this);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
