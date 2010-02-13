package com.googlecode.menugen.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.googlecode.menugen.exception.MenuGenRuntimeException;

/**
 * Load the .properties file for the given class. Caches loaded properties files
 * for increased performance of future loads.
 */
public class PropertiesUtility {
	private static final Map<String, Properties> CACHE = Collections
			.synchronizedMap(new HashMap<String, Properties>());
	private static final String PROPERTIES_EXTENSION = ".properties";
	private static final String APP_CONIFG_PROPERTIES = "org.swri.tspi.tcg.appConfig.properties";

	/**
	 * Load the {@link #APP_CONIFG_PROPERTIES} file and retrieve the property
	 * value for the given key.
	 * 
	 * @param key
	 *            String property key
	 * @return String property value, null if the given key is not present
	 */
	public static String getAppConfigProperty(String key) {
		Properties properties = load(APP_CONIFG_PROPERTIES);

		return properties.getProperty(key);
	}

	/**
	 * Load the {@link #APP_CONIFG_PROPERTIES} file and retrieve the property
	 * value for the given key, using the given default value if the key is not
	 * present.
	 * 
	 * @param key
	 *            String property key
	 * @param defaultValue
	 *            String default value
	 * @return String property value, defaultValue if the given key is not
	 *         present
	 */
	public static String getAppConfigProperty(String key, String defaultValue) {
		Properties properties = load(APP_CONIFG_PROPERTIES);

		return properties.getProperty(key, defaultValue);
	}

	/**
	 * Load the given properties file and retrieve the property value for the
	 * given key.
	 * 
	 * @param path
	 *            String file path to properties file
	 * @param key
	 *            String property key
	 * @return String property value, null if the given key is not present
	 */
	public static String getProperty(String path, String key) {
		Properties properties = load(path);

		return properties.getProperty(key);
	}

	/**
	 * Load the given properties file and retrieve the property value for the
	 * given key, using the given default value if the key is not present.
	 * 
	 * @param path
	 *            String file path to properties file
	 * @param key
	 *            String property key
	 * @param defaultValue
	 *            String default value
	 * @return String property value, defaultValue if the given key is not
	 *         present
	 */
	public static String getProperty(String path, String key,
			String defaultValue) {
		Properties properties = load(path);

		return properties.getProperty(key, defaultValue);
	}

	/**
	 * Load the properties file for the given class and retrieve the property
	 * value for the given key.
	 * 
	 * @param clazz
	 *            Class with properties file as same name
	 * @param key
	 *            String property key
	 * @return String property value, null if the given key is not present
	 */
	public static String getProperty(Class clazz, String key) {
		Properties properties = load(clazz);

		return properties.getProperty(key);
	}

	/**
	 * Load the properties file for the given class and retrieve the property
	 * value for the given key, using the given default value if the key is not
	 * present.
	 * 
	 * @param clazz
	 *            Class with properties file as same name
	 * @param key
	 *            String property key
	 * @param defaultValue
	 *            String default value
	 * @return String property value, defaultValue if the given key is not
	 *         present
	 */
	public static String getProperty(Class clazz, String key,
			String defaultValue) {
		Properties properties = load(clazz);

		return properties.getProperty(key, defaultValue);
	}

	/**
	 * Loads the given .properties file on the current thread's class path.
	 * 
	 * @param path
	 *            String file path to properties file
	 * @return Properties
	 */
	public static Properties load(String path) {
		Properties properties = null;

		if (CACHE.containsKey(path)) {
			properties = CACHE.get(path);
		} else {

			properties = new Properties();
			InputStream inputStream = Thread.currentThread()
					.getContextClassLoader().getResourceAsStream(path);

			try {
				properties.load(inputStream);
			} catch (IOException e) {
				throw new MenuGenRuntimeException(
						"Unable to load properties file " + path, e);
			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						throw new MenuGenRuntimeException(
								"Unable to close InputStream on properties file "
										+ path, e);
					}
				}
			}

			CACHE.put(path, properties);
		}

		return properties;

	}

	/**
	 * Load the .properties file for the given class.
	 * <p>
	 * Searches the class path for a .properties file under the same folder
	 * structure and file name as the qualified class name.
	 * <p>
	 * For example, org/swri/tspi/tcg/comon/model/Model.properties
	 * 
	 * @param clazz
	 *            Class
	 * @return Properties
	 */
	public static Properties load(Class clazz) {
		String path = ClassUtility.toPath(clazz, PROPERTIES_EXTENSION);

		return load(path);
	}

	/**
	 * Cannot instantiate
	 */
	private PropertiesUtility() {
		super();
	}
}