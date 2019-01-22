package com.biex.api.client.mapping;

import com.biex.api.client.bean.sign.BiexResponse;
import com.biex.api.client.bean.sign.FieldMethod;
import com.biex.api.client.bean.sign.Reader;
import com.biex.api.client.tool.StringUtils;
import com.biex.api.client.exception.ApiException;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Conversion tool class.
 * 
 * @since 1.0, Apr 11, 2010
 */
public class ApiConverters
{
    // Whether to check the data type returned by JSON, the default is not verified. The switch used to return the internal test JSON.
    // Rule: The only "basic" type returned is String, Long, Boolean, Date, which takes a strict check. If the type does not match, an error is reported.
    public static boolean            isCheckJsonType = false;

    private static final Set<String> baseFields      = new HashSet<String>();

    private static final Set<String> excludeFields   = new HashSet<String>();

    /** Attributes covered by subclasses */
    private static final Set<String> overideFields   = new HashSet<String>();
    static
    {
        baseFields.add("code");
        baseFields.add("msg");
        baseFields.add("subCode");
        baseFields.add("subMsg");
        baseFields.add("body");
        baseFields.add("params");
        baseFields.add("success");
        excludeFields.add("errorCode");
        overideFields.add("code");
        overideFields.add("msg");
    }

    private ApiConverters()
    {
    }

    /**
      * Use the specified reader to convert a string to an object.
      *
      * @param <T> domain generics
      * @param clazz domain type
      * @param reader reader
      * @return domain object
      * @throws ApiException
      */
    public static <T> T convert(Class<T> clazz, Reader reader) throws ApiException
    {
        T rsp = null;
        try
        {
            rsp = clazz.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            boolean isResponseClazz = BiexResponse.class.isAssignableFrom(clazz);
            for (PropertyDescriptor pd : pds)
            {
                Method writeMethod = pd.getWriteMethod();
                if (writeMethod == null)
                { // ignore read-only fields
                    continue;
                }
                String itemName = pd.getName();
                String listName = null;
                // Before the errorCode property is removed
                if (isResponseClazz && excludeFields.contains(itemName))
                {
                    continue;
                }
                List<FieldMethod> fieldMethods = new ArrayList<FieldMethod>();
                if (baseFields.contains(itemName) && isResponseClazz)
                {
                    Field field = BiexResponse.class.getDeclaredField(itemName);
                    FieldMethod fieldMethod = new FieldMethod();
                    fieldMethod.setField(field);
                    // writeMethod belongs to the parent class, then use directly
                    if (writeMethod.getDeclaringClass().getName().contains("BiexResponse"))
                    {
                        fieldMethod.setMethod(writeMethod);
                    }
                    else
                    {
                        // Otherwise take it again from the parent class
                        writeMethod = tryGetSetMethod(BiexResponse.class, field, writeMethod.getName());
                        if (writeMethod == null)
                        {
                            continue;
                        }
                        fieldMethod.setMethod(writeMethod);
                    }
                    fieldMethods.add(fieldMethod);
                    // If it is overridden by a subclass, try to get it from the subclass
                    if (overideFields.contains(itemName))
                    {
                        field = tryGetFieldWithoutExp(clazz, itemName);
                        // The attribute exists, you need to get the access method from the subclass again.
                        if (field != null)
                        {
                            writeMethod = tryGetSetMethod(clazz, field, writeMethod.getName());
                            if (writeMethod == null)
                            {
                                continue;
                            }
                            fieldMethod = new FieldMethod();
                            fieldMethod.setField(field);
                            fieldMethod.setMethod(writeMethod);
                            fieldMethods.add(fieldMethod);
                        }
                    }
                }
                else
                {
                    try {
                        Field field = clazz.getDeclaredField(itemName);
                        FieldMethod fieldMethod = new FieldMethod();
                        fieldMethod.setField(field);
                        fieldMethod.setMethod(writeMethod);
                        fieldMethods.add(fieldMethod);
                    }catch (Exception e) {
                        continue;
                    }

                }
                // Iteratively set properties
                for (FieldMethod fieldMethod : fieldMethods)
                {
                    Field field = fieldMethod.getField();
                    Method method = fieldMethod.getMethod();
                    ApiField jsonField = field.getAnnotation(ApiField.class);
                    if (jsonField != null)
                    {
                        itemName = jsonField.value();
                    }
                    ApiListField jsonListField = field.getAnnotation(ApiListField.class);
                    if (jsonListField != null)
                    {
                        listName = jsonListField.value();
                    }
                    if (!reader.hasReturnField(itemName))
                    {
                        if (listName == null || !reader.hasReturnField(listName))
                        {
                            continue; // ignore non-return field
                        }
                    }
                    Class<?> typeClass = field.getType();
                    // Currently
                    if (String.class.isAssignableFrom(typeClass))
                    {
                        Object value = reader.getPrimitiveObject(itemName);
                        if (value instanceof String)
                        {
                            method.invoke(rsp, value.toString());
                        }
                        else
                        {
                            if (isCheckJsonType && value != null) { throw new ApiException(itemName + " is not a String"); }
                            if (value != null)
                            {
                                method.invoke(rsp, value.toString());
                            }
                            else
                            {
                                method.invoke(rsp, "");
                            }
                        }
                    }
                    else if (Long.class.isAssignableFrom(typeClass))
                    {
                        Object value = reader.getPrimitiveObject(itemName);
                        if (value instanceof Long)
                        {
                            method.invoke(rsp, (Long) value);
                        }
                        else
                        {
                            if (isCheckJsonType && value != null) { throw new ApiException(itemName + " is not a Number(Long)"); }
                            if (StringUtils.isNumeric(value))
                            {
                                method.invoke(rsp, Long.valueOf(value.toString()));
                            }
                        }
                    }
                    else if (Integer.class.isAssignableFrom(typeClass))
                    {
                        Object value = reader.getPrimitiveObject(itemName);
                        if (value instanceof Integer)
                        {
                            method.invoke(rsp, (Integer) value);
                        }
                        else
                        {
                            if (isCheckJsonType && value != null) { throw new ApiException(itemName + " is not a Number(Integer)"); }
                            if (StringUtils.isNumeric(value))
                            {
                                method.invoke(rsp, Integer.valueOf(value.toString()));
                            }
                        }
                    }
                    else if (Boolean.class.isAssignableFrom(typeClass))
                    {
                        Object value = reader.getPrimitiveObject(itemName);
                        if (value instanceof Boolean)
                        {
                            method.invoke(rsp, (Boolean) value);
                        }
                        else
                        {
                            if (isCheckJsonType && value != null) { throw new ApiException(itemName + " is not a Boolean"); }
                            if (value != null)
                            {
                                method.invoke(rsp, Boolean.valueOf(value.toString()));
                            }
                        }
                    }
                    else if (Double.class.isAssignableFrom(typeClass))
                    {
                        Object value = reader.getPrimitiveObject(itemName);
                        if (value instanceof Double)
                        {
                            method.invoke(rsp, (Double) value);
                        }
                        else
                        {
                            if (isCheckJsonType && value != null) { throw new ApiException(itemName + " is not a Double"); }
                        }
                    }
                    else if (Number.class.isAssignableFrom(typeClass))
                    {
                        Object value = reader.getPrimitiveObject(itemName);
                        if (value instanceof Number)
                        {
                            method.invoke(rsp, (Number) value);
                        }
                        else
                        {
                            if (isCheckJsonType && value != null) { throw new ApiException(itemName + " is not a Number"); }
                        }
                    }
                    else if (List.class.isAssignableFrom(typeClass))
                    {
                        Type fieldType = field.getGenericType();
                        if (fieldType instanceof ParameterizedType)
                        {
                            ParameterizedType paramType = (ParameterizedType) fieldType;
                            Type[] genericTypes = paramType.getActualTypeArguments();
                            if (genericTypes != null && genericTypes.length > 0)
                            {
                                if (genericTypes[0] instanceof Class<?>)
                                {
                                    Class<?> subType = (Class<?>) genericTypes[0];
                                    List<?> listObjs = reader.getListObjects(listName, itemName, subType);
                                    if (listObjs != null)
                                    {
                                        method.invoke(rsp, listObjs);
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        Object obj = reader.getObject(itemName, typeClass);
                        if (obj != null)
                        {
                            method.invoke(rsp, obj);
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            throw new ApiException(e);
        }
        return rsp;
    }

    /**
      * Try to get properties
      *
      * will not throw an exception, return null if it does not exist
      *
      * @param clazz
      * @param itemName
      * @return
      */
    private static Field tryGetFieldWithoutExp(Class<?> clazz, String itemName)
    {
        try
        {
            return clazz.getDeclaredField(itemName);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
      * Get property setting properties
      *
      * @param clazz
      * @param field
      * @return
      */
    private static <T> Method tryGetSetMethod(Class<T> clazz, Field field, String methodName)
    {
        try
        {
            return clazz.getDeclaredMethod(methodName, field.getType());
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
