package com.bush.myapplication;

import android.app.Application;
import android.content.Context;

import com.bush.myapplication.database.dao.DAO;
import com.bush.myapplication.di.DiContainer;

import java.lang.reflect.InvocationTargetException;

public class ContextRunner extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            var mtpl = MTPL.getInstance();
            DiContainer.initialize(mtpl);
            initializeDatabase(mtpl);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeDatabase(Object object) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        var clazz = object.getClass();
        for (var field:clazz.getDeclaredFields()) {
            if (DAO.class.isAssignableFrom(field.getType()))
                field.getType().getDeclaredMethod("createDatabase",
                        Context.class).invoke(null, getApplicationContext());
        }
    }
}
