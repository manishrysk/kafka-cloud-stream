package com.cloudstream.kafkademo.util;

import java.lang.reflect.Field;

public class EventValidator<T> {

    @SuppressWarnings("unchecked")
    public void check(T obj) throws IllegalAccessException{
        Class<T> clazz = (Class<T>) obj.getClass();
        Field [] fields = clazz.getDeclaredFields();
        for(Field field : fields) {
            field.setAccessible(true);
            if(field.get(obj) == null) {
                throw new RuntimeException("Null value in field " + field.getName());
            }
            if(field.get(obj).toString().length() == 0) {
                throw new RuntimeException("Empty value in field " + field.getName());
            }
        }
    }
}
