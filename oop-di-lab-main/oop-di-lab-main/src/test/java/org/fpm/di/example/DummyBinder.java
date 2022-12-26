package org.fpm.di.example;

import org.fpm.di.Binder;
import javax.inject.Singleton;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DummyBinder implements Binder {
    public HashMap<Class<?>, Class<?>> mapWithClass = new HashMap<>();
    public HashMap<Class<?>, Object> mapWithObject = new HashMap<>();
    public HashMap<Class<?>, Constructor<?>> mapWithConstructor = new HashMap<>();
    public List<Class<?>> listOfSingltonClass = new ArrayList<>();

    @Override
    public <T> void bind(Class<T> clazz) {
        try {
            BinderMethod binderMethod = new BinderMethod();
            if (clazz.getAnnotation(Singleton.class) != null) {
                binderMethod.bindMethodSinglton(clazz, listOfSingltonClass);
            } else {
                binderMethod.bindMethodPrototype(clazz, mapWithConstructor);
            }
        } catch (InstantiationException |
                 IllegalAccessException | InvocationTargetException e){
            throw new RuntimeException();
        }
    }

    @Override
    public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {
        mapWithClass.put(clazz, implementation);
    }

    @Override
    public <T> void bind(Class<T> clazz, T instance) {
        mapWithObject.put(clazz, instance);
    }
}
