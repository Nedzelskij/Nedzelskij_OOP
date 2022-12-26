package org.fpm.di.example;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import org.fpm.di.Container;

public class DummyContainer implements Container {

    private final DummyBinder myBinder;

    public DummyContainer(DummyBinder binder) {
        myBinder = binder;
    }

    @Override
    public <T> T getComponent(Class<T> clazz) {

        if (myBinder.mapWithClass.containsKey(clazz)) {
            return getComponent((Class<T>) myBinder.mapWithClass.get(clazz));
        } else if (myBinder.mapWithObject.containsKey(clazz)) {
            return (T) myBinder.mapWithObject.get(clazz);
        }

        try {
            if (myBinder.mapWithConstructor.containsKey(clazz)) {
                    Parameter[] parameters = myBinder.mapWithConstructor.get(clazz).getParameters();
                    Object[] argument = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        Class<?> type = parameter.getType();
                        if(myBinder.mapWithConstructor.get(clazz).getAnnotation(Inject.class) != null){
                            argument[i] = getComponent(type);
                        }else {
                            argument[i] = type.newInstance();
                        }
                    }
                    return (T) myBinder.mapWithConstructor.get(clazz).newInstance(argument);
            } else if(myBinder.listOfSingltonClass.contains(clazz)) {
                T instanceSingleton;
                Constructor<?>[] constructors = clazz.getConstructors();

                for(Constructor<?> con: constructors){
                    if(con.getAnnotation(Inject.class) != null){
                        Parameter[] parameters = con.getParameters();
                        Object[] argument = new Object[parameters.length];
                        for (int i = 0; i < parameters.length; i++) {
                            Parameter parameter = parameters[i];
                            Class<?> type = parameter.getType();
                            argument[i] = getComponent(type);
                        }
                        instanceSingleton = (T) con.newInstance(argument);
                        myBinder.bind(clazz, instanceSingleton);
                        return instanceSingleton;
                    }
                }
                Parameter[] parameters = constructors[0].getParameters();
                Object[] argument = new Object[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    Parameter parameter = parameters[i];
                    Class<?> type = parameter.getType();
                    argument[i] = getComponent(type);
                }
                instanceSingleton = (T) constructors[0].newInstance(argument);
                myBinder.bind(clazz, instanceSingleton);
                return instanceSingleton;
            }
        } catch (InstantiationException |
                 IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException();
        }

        return null;
    }
}
