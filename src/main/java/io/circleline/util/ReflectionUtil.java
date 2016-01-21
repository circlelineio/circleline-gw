package io.circleline.util;

import java.lang.reflect.Constructor;

/**
 * Created by 1002515 on 2016. 1. 21..
 */
public class ReflectionUtil {
    public static <T> T newInstance(Class clazz, Object...args){
        //TODO 람다로 변경
       Class[] aClass = new Class[args.length];
        for(int i = 0 ; i < args.length ; i++){
            aClass[i] = args[i].getClass();
        }
        try {
            Constructor<T> c = clazz.getDeclaredConstructor(aClass);
            return c.newInstance(args);
        } catch (Exception e) {
            throw new IllegalArgumentException("invalid class");
        }
    }

    private ReflectionUtil(){}
}
