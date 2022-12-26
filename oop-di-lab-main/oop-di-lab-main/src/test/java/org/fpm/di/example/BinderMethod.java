package org.fpm.di.example;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BinderMethod {

    public <T> void bindMethodSinglton(Class<T> clazz, List<Class<?>> listOfSingeltonClass)
            throws InstantiationException, IllegalAccessException, InvocationTargetException {

        Constructor<?>[] constructors = clazz.getConstructors();

        ArrayList<Constructor<?>> constructorWithInject = new ArrayList<>();
        for(Constructor<?> con: constructors){
            if(con.getAnnotation(Inject.class) != null){
                constructorWithInject.add(con);
            }
        }

        if (constructors.length == 1 || constructorWithInject.size() == 1) {
            listOfSingeltonClass.add(clazz);
        } else {
            throw new RuntimeException("Неможливо вибрати підходящий конструктор");
        }
    }

    public <T> void bindMethodPrototype(Class<T> clazz, Map<Class<?>, Constructor<?>> mapWithConstructor)
            throws InstantiationException, IllegalAccessException, InvocationTargetException {

        Constructor<?>[] constructors = clazz.getConstructors();

        ArrayList<Constructor<?>> constructorWithInject = new ArrayList<>();
        for(Constructor<?> con: constructors){
            if(con.getAnnotation(Inject.class) != null){
                constructorWithInject.add(con);
            }
        }

        if (constructors.length == 1) {
            mapWithConstructor.put(clazz, constructors[0]);
        } else if (constructorWithInject.size() == 1) {
            mapWithConstructor.put(clazz, constructorWithInject.get(0));
        } else{
            throw new RuntimeException("Неможливо вибрати підходящий конструктор");
        }
    }

}
