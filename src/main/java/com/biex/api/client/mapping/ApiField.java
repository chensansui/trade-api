package com.biex.api.client.mapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
   * Data structure attribute annotations.
   *
   * @since 1.0, Apr 11, 2010
   */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
public @interface ApiField
{
    /** JSON attribute mapping name **/
    String value() default "";
}
