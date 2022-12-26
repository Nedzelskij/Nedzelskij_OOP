package org.fpm.di.example;

import MyClasses.AA;
import MyClasses.AnotherSingleton;
import MyClasses.BB;
import MyClasses.UserUser;
import org.fpm.di.Binder;
import org.fpm.di.Configuration;

public class MyConfiguration implements Configuration {

    @Override
    public void configure(Binder binder) {

        binder.bind(MySingleton.class);
        binder.bind(MyPrototype.class);

        binder.bind(UseA.class);

        binder.bind(A.class, B.class);
        binder.bind(B.class, new B());



        binder.bind(AA.class, BB.class);
        binder.bind(BB.class, new BB());
        binder.bind(AnotherSingleton.class);
        binder.bind(UserUser.class);
    }
}
