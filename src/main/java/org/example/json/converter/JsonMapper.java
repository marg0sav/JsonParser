package org.example.json.converter;

import org.example.json.container.JsonContainer.JSONValue;
import org.example.json.container.JsonContainer.JSONArray;
import org.example.json.container.JsonContainer.JSONObject;
import org.example.json.container.JsonContainer.JSONString;
import org.example.json.container.JsonContainer.JSONNumber;
import org.example.json.container.JsonContainer.JSONBoolean;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * The JsonMapper class provides methods to map JSON objects to Java objects and vice versa.
 */
public class JsonMapper {

    /**
     * Creates an instance of a specified class from a JSONObject.
     *
     * @param clazz The class to create an instance of.
     * @param json The JSONObject containing data to populate the instance.
     * @param <T> The type of the class to create.
     * @return An instance of the specified class populated with data from the JSONObject.
     * @throws Exception If an error occurs during instantiation or mapping.
     */
    public static <T> T createSpecifiedClassFromJson(Class<T> clazz, JSONObject json) throws Exception {
        Constructor<T> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        T instance = constructor.newInstance();
        Map<String, JSONValue> map = json.values();
        System.out.println("Creating instance of class: " + clazz.getName());

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (map.containsKey(field.getName())) {
                JSONValue value = map.get(field.getName());
                System.out.println("Processing field: " + field.getName() + " with value: " + value);

                if (field.getType().isArray()) {
                    Class<?> componentType = field.getType().getComponentType();
                    JSONArray jsonArray = (JSONArray) value;
                    List<JSONValue> list = jsonArray.getValues();
                    Object array = Array.newInstance(componentType, list.size());
                    for (int i = 0; i < list.size(); i++) {
                        JSONValue element = list.get(i);
                        System.out.println("Array element before conversion: " + element);
                        if (componentType.isPrimitive() || componentType.equals(String.class)) {
                            Array.set(array, i, convertJsonValueToObject(element, componentType));
                        } else {
                            if (element instanceof JSONObject) {
                                Object nestedInstance = createSpecifiedClassFromJson(componentType, (JSONObject) element);
                                Array.set(array, i, nestedInstance);
                            } else {
                                Array.set(array, i, componentType.cast(convertJsonValueToObject(element, componentType)));
                            }
                        }
                        System.out.println("Array element after conversion: " + Array.get(array, i));
                    }
                    field.set(instance, array);
                } else if (value instanceof JSONObject) {
                    Object nestedObject = createSpecifiedClassFromJson(field.getType(), (JSONObject) value);
                    field.set(instance, nestedObject);
                } else {
                    Object convertedValue = convertJsonValueToObject(value, field.getType());
                    setFieldValue(instance, field, convertedValue);
                }
            }
        }
        return instance;
    }


    /**
     * Converts a JSONValue object to a Java object of the specified type.
     *
     * @param value The JSONValue to convert.
     * @param targetType The target type to convert to.
     * @return The converted Java object.
     */
    private static Object convertJsonValueToObject(JSONValue value, Class<?> targetType) {
        System.out.println("Converting JSONValue to Object: " + value + " for target type: " + targetType.getName());
        if (value instanceof JSONString) {
            return ((JSONString) value).getValue();
        } else if (value instanceof JSONNumber) {
            double numberValue = ((JSONNumber) value).getValue();
            if (targetType == int.class || targetType == Integer.class) {
                return (int) numberValue;
            } else if (targetType == double.class || targetType == Double.class) {
                return numberValue;
            } else if (targetType == float.class || targetType == Float.class) {
                return (float) numberValue;
            } else if (targetType == long.class || targetType == Long.class) {
                return (long) numberValue;
            } else {
                return numberValue;
            }
        } else if (value instanceof JSONBoolean) {
            return ((JSONBoolean) value).getValue();
        } else if (value instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) value;
            List<JSONValue> list = jsonArray.getValues();
            if (targetType.isArray()) {
                Object array = Array.newInstance(targetType.getComponentType(), list.size());
                for (int i = 0; i < list.size(); i++) {
                    Array.set(array, i, convertJsonValueToObject(list.get(i), targetType.getComponentType()));
                }
                return array;
            } else {
                // handle case where targetType is Object.class
                Object[] array = new Object[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    array[i] = convertJsonValueToObject(list.get(i), Object.class);
                }
                return array;
            }
        } else if (value instanceof JSONObject) {
            return value; // return as is for nested objects
        }
        return null;
    }


    /**
     * Sets the value of a field in an object.
     *
     * @param instance The object instance.
     * @param field The field to set the value for.
     * @param value The value to set.
     * @throws IllegalAccessException If the field is inaccessible.
     */
    private static void setFieldValue(Object instance, Field field, Object value) throws IllegalAccessException {
        if (field.getType().isPrimitive()) {
            if (field.getType() == int.class) {
                field.setInt(instance, (int) value);
            } else if (field.getType() == double.class) {
                field.setDouble(instance, (double) value);
            } else if (field.getType() == float.class) {
                field.setFloat(instance, (float) value);
            } else if (field.getType() == long.class) {
                field.setLong(instance, (long) value);
            } else if (field.getType() == boolean.class) {
                field.setBoolean(instance, (boolean) value);
            } else if (field.getType() == char.class) {
                field.setChar(instance, (char) value);
            } else if (field.getType() == byte.class) {
                field.setByte(instance, (byte) value);
            } else if (field.getType() == short.class) {
                field.setShort(instance, (short) value);
            }
        } else {
            field.set(instance, value);
        }
    }

    public static Map<String, Object> createMapFromJson(JSONObject json) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, JSONValue> jsonMap = json.values();
        for (Map.Entry<String, JSONValue> entry : jsonMap.entrySet()) {
            resultMap.put(entry.getKey(), convertJsonValueToObject(entry.getValue(), Object.class));
        }
        return resultMap;
    }

    public static String createJsonFromObject(Object obj) {
        try {
            Map<String, JSONValue> jsonMap = new HashMap<>();
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(obj);
                if (value != null) {
                    System.out.println("Processing field: " + field.getName() + " with value: " + value);
                    if (value.getClass().isArray()) {
                        int length = Array.getLength(value);
                        JSONArray jsonArray = new JSONArray();
                        for (int i = 0; i < length; i++) {
                            Object element = Array.get(value, i);
                            System.out.println("Array element before conversion: " + element);
                            jsonArray.add(convertObjectToJsonValue(element));
                            System.out.println("Array element after conversion: " + element);
                        }
                        jsonMap.put(field.getName(), jsonArray);
                    } else {
                        jsonMap.put(field.getName(), convertObjectToJsonValue(value));
                    }
                }
            }
            return new JSONObject(jsonMap).toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static JSONValue convertObjectToJsonValue(Object value) {
        System.out.println("Converting Object to JSONValue: " + value);
        if (value instanceof String) {
            return new JSONString((String) value);
        } else if (value instanceof Number) {
            return new JSONNumber(((Number) value).doubleValue());
        } else if (value instanceof Boolean) {
            return new JSONBoolean((Boolean) value);
        } else if (value instanceof Map) {
            Map<String, JSONValue> jsonMap = new HashMap<>();
            for (Map.Entry<?, ?> entry : ((Map<?, ?>) value).entrySet()) {
                jsonMap.put(entry.getKey().toString(), convertObjectToJsonValue(entry.getValue()));
            }
            return new JSONObject(jsonMap);
        } else if (value.getClass().isArray()) {
            JSONArray jsonArray = new JSONArray();
            int length = Array.getLength(value);
            for (int i = 0; i < length; i++) {
                jsonArray.add(convertObjectToJsonValue(Array.get(value, i)));
            }
            return jsonArray;
        } else {
            return createMapFromObject(value);
        }
    }

    private static JSONObject createMapFromObject(Object obj) {
        try {
            Map<String, JSONValue> jsonMap = new HashMap<>();
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(obj);
                if (value != null) {
                    System.out.println("Processing field: " + field.getName() + " with value: " + value);
                    jsonMap.put(field.getName(), convertObjectToJsonValue(value));
                }
            }
            return new JSONObject(jsonMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
