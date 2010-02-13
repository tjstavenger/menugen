/**
 * 
 */
package com.googlecode.menugen.domain;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.context.annotation.Bean;

import com.googlecode.menugen.domain.annotation.IgnoreEquals;
import com.googlecode.menugen.utility.ClassUtility;
import com.googlecode.menugen.utility.SerializationUtility;

/**
 * Base super class supporting default implementations of {@link #clone()},
 * {@link Comparable#compareTo(Object)}, {@link #toString()},
 * {@link #hashCode()}, and {@link #equals(Object)}.
 */
public class DomainObject implements Serializable, Cloneable, Comparable {
	private static final long serialVersionUID = 1L;

	private static final String HANDLER = "handler";

	/**
	 * Clone this {@link TcgObject} with
	 * {@link SerializationUtils#clone(Serializable)}
	 * 
	 * @return Object clone of this {@link TcgObject}
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return SerializationUtils.clone(this);
	}

	/**
	 * Compare this {@link TcgObject} with the given {@link TcgObject} using
	 * {@link CompareToBuilder#reflectionCompare(Object, Object, java.util.Collection)}
	 * .
	 * <p>
	 * Fields can be ignored by annotating them with {@link IgnoreEquals} or by
	 * overriding {@link #listIgnoreEquals()}.
	 * 
	 * @param obj
	 *            {@link TcgObject} to compare
	 * @return a negative integer, zero, or a positive integer as this object is
	 *         less than, equal to, or greater than the specified object.
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object obj) {
		return CompareToBuilder
				.reflectionCompare(this, obj, listIgnoreEquals());
	}

	/**
	 * Perform a deep copy of this {@link TcgObject}.
	 * <p>
	 * Serializes the object to XML and deserializes it back into a
	 * {@link TcgObject}.
	 * 
	 * @param <T>
	 *            type of {@link TcgObject}
	 * @return deep copy of this {@link TcgObject}
	 * 
	 * @see SerializationUtility#serialize(Object)
	 * @see SerializationUtility#deserialize(String)
	 */
	@SuppressWarnings("unchecked")
	public <T extends DomainObject> T copy() {
		T copy = (T) ClassUtility.deepCopy(this);

		return copy;
	}

	/**
	 * Test if this {@link TcgObject} is equal to another using
	 * {@link EqualsBuilder#reflectionEquals(Object, Object, java.util.Collection)}
	 * <p>
	 * Fields can be ignored by annotating them with {@link IgnoreEquals} or
	 * overriding by {@link #listIgnoreEquals()}.
	 * 
	 * @param obj
	 *            Object to test equality
	 * @return <code>true</code> if this object is the same as the obj argument;
	 *         <code>false</code> otherwise.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		Object unproxied = obj;

		// EqualsBuilder tests for equality by directly accessing field values.
		// Hibernate proxies do not have the attributes set, so the field values
		// will be null. Unproxy the object first, before using EqualsBuilder.
		// Hibernate automatically unproxies this instance when the equals()
		// method is called, so we don't need to worry about unproxying it.
		if (ClassUtility.isHibernateProxy(unproxied)) {
			unproxied = ClassUtility.deepCopy(unproxied);
		}

		return EqualsBuilder.reflectionEquals(this, unproxied,
				listIgnoreEquals());
	}

	/**
	 * Calculate the hash code of this {@link TcgObject} using
	 * {@link HashCodeBuilder#reflectionHashCode(Object, java.util.Collection)}.
	 * <p>
	 * Fields can be ignored by annotating them with {@link IgnoreEquals} or by
	 * overriding {@link #listIgnoreEquals()}.
	 * 
	 * @return a hash code value for this {@link TcgObject}
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, listIgnoreEquals());
	}

	/**
	 * Produce a String of the attribute values of this {@link TcgObject} using
	 * {@link ToStringBuilder#reflectionToString(Object, ToStringStyle)}.
	 * 
	 * @return a string representation of this {@link TcgObject}
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

	/**
	 * List the fields to be ignored by {@link #equals(Object)} and
	 * {@link #hashCode()}.
	 * <p>
	 * Default implementation finds all fields on this {@link Bean} annotated by
	 * {@link IgnoreEquals}.
	 * <p>
	 * Sub classes wishing to override this method should call the super class
	 * first, to get the default ignored fields.
	 * 
	 * @return String array of field names to ignore
	 */
	public Set<String> listIgnoreEquals() {
		Set<Field> fields = ClassUtility.findAnnotatedFields(
				IgnoreEquals.class, getClass());
		Set<String> fieldNames = new HashSet<String>(fields.size() + 1);
		fieldNames.add(HANDLER);

		for (Field field : fields) {
			fieldNames.add(field.getName());
		}

		return fieldNames;
	}

	/**
	 * Serialize all fields on this object to XML.
	 * 
	 * @return String XML
	 * 
	 * @see SerializationUtility#serialize(Object)
	 */
	public String toXml() {
		return SerializationUtility.serialize(this);
	}
}
