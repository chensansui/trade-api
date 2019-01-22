package com.biex.api.client.bean.sign;

import com.biex.api.client.exception.ApiException;

import java.util.List;

/**
 * Format converter.
 *
 * @since 1.0, Apr 11, 2010
 */
public interface Reader {
    /**
      * Determines if the returned result contains the specified attribute.
      *
      * @param name attribute name
      * @return true/false
      */
    boolean hasReturnField(Object name);

    /**
      * Read a single base object.
      *
      * @param name map name
      * @return base object value
      */
    Object getPrimitiveObject(Object name);

    /**
      * Read a single custom object.
      *
      * @param name map name
      * @param type mapping type
      * @return instance of mapping type
      * @throws ApiException
      */
    Object getObject(Object name, Class<?> type) throws ApiException;

    /**
      * Read the values of multiple objects.
      *
      * @param listName list name
      * @param itemName map name
      * @param subType nested map type
      * @return nested map type instance list
      * @throws ApiException
      */
    List<?> getListObjects(Object listName, Object itemName, Class<?> subType) throws ApiException;
}
