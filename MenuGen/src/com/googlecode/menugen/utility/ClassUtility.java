/**
 * 
 */
package com.googlecode.menugen.utility;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.beanlib.hibernate3.Hibernate3BeanReplicator;
import net.sf.beanlib.provider.replicator.BeanReplicator;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Entity;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;

import com.googlecode.menugen.exception.MenuGenRuntimeException;

/**
 * Provides utility methods for Classes.
 */
public class ClassUtility {
	private static final Logger LOG = Logger.getLogger(ClassUtility.class);
	private static final String CLASS_SEPARATOR = "\\.";

	private static final String FILE_SEPARATOR = "/";
	private static final char SPACE = ' ';

	private static final Hibernate3BeanReplicator HIBERNATE_BEAN_REPLICATOR = new Hibernate3BeanReplicator();
	private static final BeanReplicator BEAN_REPLICATOR = new BeanReplicator();

	private static final String BEAN_SUFFIX = "Bean";
	private static final String MODEL_SUFFIX = "Model";

	private static final String JAVASSIST_CLASS_NAME = "javassist";

	/**
	 * Perform a shallow copy of the given {@link Bean} to a new instance of the
	 * given {@link Model} class.
	 * 
	 * @param <M>
	 *            type of {@link Model}
	 * @param modelClass
	 *            Class type of {@link Model}
	 * @param bean
	 *            source {@link Bean} instance
	 * @return {@link Model} shallow copy of given {@link Bean}
	 */
	public static <M extends Model> M copy(Bean bean, Class<M> modelClass) {
		return shallowCopy(bean, modelClass);
	}

	/**
	 * Perform a shallow copy of the given {@link Model} to a new instance of
	 * the given {@link Bean} class.
	 * 
	 * @param <B>
	 *            type of {@link Bean}
	 * @param beanClass
	 *            Class type of {@link Bean}
	 * @param model
	 *            source {@link Model} instance
	 * @return {@link Bean} shallow copy of given {@link Model}
	 */
	public static <B extends Bean> B copy(Model model, Class<B> beanClass) {
		return shallowCopy(model, beanClass);
	}

	/**
	 * Deeply copy the given object, removing Hibernate proxies along the way.
	 * 
	 * @param <T>
	 *            type to copy
	 * @param source
	 *            Object instance to copy
	 * @return Object deeply copied instance
	 */
	public static Object deepCopy(Object source) {
		Object copy;

		if (source.getClass().getAnnotation(Entity.class) == null) {
			copy = BEAN_REPLICATOR.replicateBean(source);
		} else {
			copy = HIBERNATE_BEAN_REPLICATOR.deepCopy(source);
		}

		return copy;
	}

	/**
	 * Return a Set of {@link FormField} that are annotated with the given
	 * {@link Annotation} on the given {@link Class} or any of its super
	 * classes.
	 * 
	 * @param annotation
	 *            Annotation class to find
	 * @param clazz
	 *            Class from which to reflect annotated fields
	 * @return Set of annotated {@link FormField}
	 */
	public static Set<Field> findAnnotatedFields(Class annotation, Class clazz) {
		Set<Field> fields = new HashSet<Field>();
		findAnnotatedFields(annotation, clazz, fields);

		return fields;
	}

	/**
	 * Adds all fields with the specified {@link Annotation} of {@link Class}
	 * clazz and its super classes to the given {@link Set} annotatedFields.
	 * <p>
	 * Code copied from XWork's AnnotationUtils class. A dependency was not
	 * added to XWork so that Struts wouldn't be a dependency for all projects.
	 * 
	 * @param annotationClass
	 *            Annotation class to find
	 * @param clazz
	 *            Class from which to reflect annotated fields
	 * @param annotatedFields
	 *            Set of FormField with the given annotation
	 * 
	 * @see com.opensymphony.xwork2.util.AnnotationUtils#addAllFields(Class,
	 *      Class, List)
	 */
	public static void findAnnotatedFields(Class annotationClass, Class clazz,
			Set<Field> annotatedFields) {

		if (clazz == null) {
			return;
		}

		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			if (field.isAnnotationPresent(annotationClass)) {
				annotatedFields.add(field);
			}
		}

		findAnnotatedFields(annotationClass, clazz.getSuperclass(),
				annotatedFields);
	}

	/**
	 * Get all declared {@link FormField} in the given {@link Class} and its
	 * super classes.
	 * 
	 * @param clazz
	 *            Class
	 * @param includeStaticFinal
	 *            boolean true if static final attributes should be included in
	 *            the returned Set
	 * @return declared {@link FormField}
	 * 
	 * @see Class#getDeclaredFields()
	 */
	public static Set<Field> getAllDeclaredFields(Class clazz,
			boolean includeStaticFinal) {
		Set<Field> fields = new HashSet<Field>();
		getAllDeclaredFields(clazz, includeStaticFinal, fields);

		return fields;
	}

	/**
	 * Get all declared {@link FormField} in the given {@link Class} and its
	 * super classes.
	 * 
	 * @param clazz
	 *            Class
	 * @param includeStaticFinal
	 *            boolean true if static final attributes should be included in
	 *            the returned Set
	 * @param fields
	 *            Set of declared {@link FormField}
	 * 
	 * @see Class#getDeclaredFields()
	 */
	public static void getAllDeclaredFields(Class clazz,
			boolean includeStaticFinal, Set<Field> fields) {
		if (clazz == null) {
			return;
		}

		Field[] declaredFields = clazz.getDeclaredFields();

		for (Field field : declaredFields) {
			boolean staticFinal = Modifier.isStatic(field.getModifiers())
					&& Modifier.isFinal(field.getModifiers());

			if (!staticFinal || (includeStaticFinal && staticFinal)) {
				fields.add(field);
			}
		}

		getAllDeclaredFields(clazz.getSuperclass(), includeStaticFinal, fields);
	}

	/**
	 * If a class extends a generic typed class and declares the type, find the
	 * class type of the generic.
	 * <p>
	 * If the given class does not extend a generic type class, a
	 * {@link RuntimeException} will be thrown.
	 * 
	 * @param clazz
	 *            generic typed class
	 * @param index
	 *            index of generic type to get
	 * @return Class of generic type
	 */
	public static Class getGenericType(Class clazz, int index) {
		ParameterizedType parameterizedType = (ParameterizedType) clazz
				.getGenericSuperclass();
		Class generic = (Class) parameterizedType.getActualTypeArguments()[index];

		return generic;
	}

	/**
	 * If a {@link Field} is typed, find the class type of the generic at the
	 * given index.
	 * <p>
	 * If the given Field has no generics, null will be returned.
	 * 
	 * @param field
	 *            generic typed {@link Field}
	 * @return {@link Class}
	 */
	public static Class getGenericType(Field field, int index) {
		Class clazz = null;
		Type type = field.getGenericType();

		if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			Type[] generics = parameterizedType.getActualTypeArguments();

			if (generics.length > 0) {
				clazz = (Class) generics[index];
			}
		}

		return clazz;
	}

	/**
	 * Get the value of a property on the given bean.
	 * <p>
	 * Be sure the object has the property first by calling
	 * {@link #hasProperty(Object, String)}!
	 * 
	 * @param bean
	 *            Object to set value on
	 * @param name
	 *            String property name to set
	 * @return Object value
	 * 
	 * @see #hasProperty(Object, String)
	 * @see PropertyUtils#getProperty(Object, String)
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getProperty(Object bean, String name) {
		try {
			return (T) PropertyUtils.getProperty(bean, name);
		} catch (IllegalAccessException e) {
			throw new MenuGenRuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new MenuGenRuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new MenuGenRuntimeException(e);
		}
	}

	/**
	 * Return true if the property is both readable and writeable.
	 * 
	 * @param bean
	 *            Object instance to test
	 * @param name
	 *            String property name
	 * @return boolean
	 * 
	 * @see PropertyUtils#isReadable(Object, String)
	 * @see PropertyUtils#isReadable(Object, String)
	 */
	public static boolean hasProperty(Object bean, String name) {
		return PropertyUtils.isReadable(bean, name)
				&& PropertyUtils.isWriteable(bean, name);
	}

	/**
	 * Return true if the given Object is an instance of a Hibernate proxy. A
	 * class is a Hibernat proxy if it is a Javassist proxy class, which
	 * contains {@link #JAVASSIST_CLASS_NAME} in its class name.
	 * 
	 * @param bean
	 *            Object instance to test
	 * @return boolean
	 */
	public static boolean isHibernateProxy(Object bean) {
		boolean proxy = false;

		if (bean != null) {
			String className = bean.getClass().getName();
			proxy = className.contains(JAVASSIST_CLASS_NAME);
		}

		return proxy;
	}

	/**
	 * Set the value of a property on the given bean.
	 * <p>
	 * Be sure the object has the property first by calling
	 * {@link #hasProperty(Object, String)}!
	 * 
	 * @param bean
	 *            Object to set value on
	 * @param name
	 *            String property name to set
	 * @param value
	 *            Object value to set
	 * 
	 * @see #hasProperty(Object, String)
	 * @see PropertyUtils#setProperty(Object, String, Object)
	 */
	public static void setProperty(Object bean, String name, Object value) {
		try {
			PropertyUtils.setProperty(bean, name, value);
		} catch (IllegalAccessException e) {
			throw new MenuGenRuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new MenuGenRuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new MenuGenRuntimeException(e);
		}
	}

	/**
	 * Perform a shallow copy of the given source to a new instance of the given
	 * destination class.
	 * 
	 * @param <T>
	 *            type of destination
	 * @param source
	 *            source instance
	 * @param destinationClass
	 *            Class type of destination
	 * @return shallow copy of given source
	 */
	private static <T> T shallowCopy(Object source, Class<T> destinationClass) {
		try {
			T destination = destinationClass.newInstance();
			BeanUtils.copyProperties(destination, source);

			return destination;
		} catch (InstantiationException e) {
			throw new MenuGenRuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new MenuGenRuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new MenuGenRuntimeException(e);
		}
	}

	/**
	 * Perform a shallow copy of the given source into the given destination
	 * instance.
	 * 
	 * @param <T>
	 *            type of destination
	 * @param source
	 *            source instance
	 * @param destination
	 *            destination instance
	 * @return shallow copy of given source
	 */
	public static void shallowCopy(Object source, Object destination) {
		try {
			BeanUtils.copyProperties(destination, source);
		} catch (IllegalAccessException e) {
			throw new MenuGenRuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new MenuGenRuntimeException(e);
		}
	}

	/**
	 * Returns the typical field name for a class, the uncaptilalized,
	 * unqualified class name.
	 * 
	 * @param clazz
	 *            Class
	 * @return field name
	 * 
	 * @see #unqualify(Class)
	 * @see StringUtils#uncapitalize(String)
	 */
	public static String toFieldName(Class clazz) {
		String unqualified = unqualify(clazz);

		return StringUtils.uncapitalize(unqualified);
	}

	/**
	 * Unqualify the class and split it by capital letters.
	 * <p>
	 * For example, org.swri.tspi.tcg.common.utility.ClassUtility becomes the
	 * String "Class Utility".
	 * 
	 * @param clazz
	 *            {@link Class}
	 * @return String friendly name
	 */
	public static String toFriendlyName(Class clazz) {
		String unqualified = unqualify(clazz);

		if (unqualified.endsWith(MODEL_SUFFIX)) {
			int index = unqualified.lastIndexOf(MODEL_SUFFIX);
			unqualified = unqualified.substring(0, index);
		}

		if (unqualified.endsWith(BEAN_SUFFIX)) {
			int index = unqualified.lastIndexOf(BEAN_SUFFIX);
			unqualified = unqualified.substring(0, index);
		}

		String[] split = StringUtils.splitByCharacterTypeCamelCase(unqualified);
		String friendlyName = StringUtils.join(split, SPACE);

		return friendlyName;
	}

	/**
	 * Convert the given {@link Class#getName()} into a file path with the given
	 * extension.
	 * 
	 * @param clazz
	 *            {@link Class} to retrieve {@link Class#getName()}
	 * @param extension
	 *            {@link String} file extension to place on returned path
	 * @return String file path
	 */
	public static String toPath(Class clazz, String extension) {
		String className = clazz.getName().replaceAll(CLASS_SEPARATOR,
				FILE_SEPARATOR);
		StringBuffer path = new StringBuffer();
		path.append(className);
		path.append(extension);

		return path.toString();
	}

	/**
	 * Unqualify the class name.
	 * 
	 * @param clazz
	 *            Class
	 * @return unqualified class name
	 * 
	 * @see ClassUtils#getShortClassName(Class)
	 */
	public static String unqualify(Class clazz) {
		return ClassUtils.getShortClassName(clazz);
	}

	/**
	 * Cannot instantiate
	 */
	private ClassUtility() {
		super();
	}
}