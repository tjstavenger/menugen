package com.googlecode.menugen.domain.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate a field as being ignored during {@link #equals(Object)} and
 * {@link #hashCode()}.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD })
public @interface IgnoreEquals {

}
