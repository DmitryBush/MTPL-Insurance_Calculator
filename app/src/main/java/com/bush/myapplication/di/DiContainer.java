package com.bush.myapplication.di;

import java.lang.reflect.InvocationTargetException;

public class DiContainer {
    public static void initialize(Object object){
        Class<?> clazz = object.getClass();
        for (var field: clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)){
                try {
                    Object dependency;
                    if (field.getType().isAnnotationPresent(Component.class))
                        dependency = field.getType()
                                .getDeclaredMethod("getInstance").invoke(null);
                    else
                        dependency = field.getType()
                                .getDeclaredConstructor().newInstance();
                    field.setAccessible(true);
                    field.set(object, dependency);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
