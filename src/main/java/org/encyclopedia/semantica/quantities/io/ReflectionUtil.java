package org.encyclopedia.semantica.quantities.io;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.*;

public final class ReflectionUtil {
    private static final Set<Class<?>> primitiveTypes = new HashSet<>();

    static {
        primitiveTypes.add(Boolean.class);
        primitiveTypes.add(Integer.class);
        primitiveTypes.add(Long.class);
        primitiveTypes.add(Short.class);
        primitiveTypes.add(Double.class);
        primitiveTypes.add(Float.class);
        primitiveTypes.add(Date.class);
        primitiveTypes.add(String.class);
        primitiveTypes.add(Character.class);
        primitiveTypes.add(URI.class);
    }

    public static List<Field> getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<>(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            fields.addAll(getAllFields(type.getSuperclass()));
        }

        return fields;
    }

    public static boolean isPrimitive(Object obj) {
        return isPrimitive(obj.getClass());
    }

    public static boolean isPrimitive(Class<?> obj) {
        return primitiveTypes.contains(obj);
    }
}
