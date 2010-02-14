/**
 * 
 */
package com.googlecode.menugen.utility;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Load the Spring ApplicationContext and provide methods to retrieve Spring
 * managed beans.
 */
public class SpringContextUtility {
	private static final String APPLICATION_CONTEXT_PATH = "classpath*:applicationContext.xml";

	private static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext(
			APPLICATION_CONTEXT_PATH);

	/**
	 * Get the Spring managed bean of the given type.
	 * 
	 * @param <T>
	 *            type of class to get
	 * @param clazz
	 *            Class to get
	 * @return Spring managed bean
	 */
	public static <T> T getBean(Class<T> clazz) {
		return CONTEXT.getBean(clazz);
	}

	/**
	 * Cannot instantiate
	 */
	private SpringContextUtility() {
		super();
	}
}
