package com.googlecode.menugen.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.hibernate.collection.PersistentBag;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.collection.PersistentList;
import org.hibernate.collection.PersistentMap;
import org.hibernate.collection.PersistentSet;
import org.hibernate.collection.PersistentSortedMap;
import org.hibernate.collection.PersistentSortedSet;
import org.hibernate.proxy.HibernateProxy;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.ConverterLookup;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.AbstractXmlFriendlyMapper;
import com.thoughtworks.xstream.mapper.Mapper;
import com.thoughtworks.xstream.mapper.MapperWrapper;

/**
 * Perform serialization/deserialization on objects.
 */
public class SerializationUtility {

	// Per http://xstream.codehaus.org/faq.html#Scalability, XStream is
	// thread-safe and should be instantiated once for performance
	private static final XStream XSTREAM = newXStream();

	/**
	 * Instantiate and configure an instance of {@link XStream} that removes
	 * Hibernate lazy loading proxy classes.
	 * 
	 * @return {@link XStream}
	 * 
	 * @see http://jira.codehaus.org/browse/XSTR-226
	 */
	private static XStream newXStream() {
		XStream xstream = new XStream();
		final Mapper cm = xstream.getMapper();

		xstream = new XStream() {
			protected MapperWrapper wrapMapper(MapperWrapper next) {
				return new HibernateMapper(next);
			}

			protected Mapper buildMapper() {
				return new HibernateCollectionsMapper(cm);
			}
		};

		xstream.registerConverter(new HibernateCollectionConverter(xstream
				.getConverterLookup()));

		xstream.registerConverter(new HibernateProxyConverter(xstream
				.getMapper(), new PureJavaReflectionProvider()),
				XStream.PRIORITY_VERY_HIGH);

		return xstream;
	}

	/**
	 * Serialize an Object into an XML String.
	 * 
	 * @param obj
	 *            Object to serialize
	 * @return XML String
	 */
	public static String serialize(Object obj) {
		return XSTREAM.toXML(obj);
	}

	/**
	 * Deserialize the XML String into an Object.
	 * 
	 * @param <T>
	 *            type of Object
	 * @param xml
	 *            serialized Object as XML String
	 * @return deserialized Object
	 */
	public static <T> T deserialize(String xml) {
		return (T) XSTREAM.fromXML(xml);
	}

	/**
	 * Cannot instantiate.
	 */
	private SerializationUtility() {
		super();
	}

	/**
	 * XStream converter that strips HB collections specific information and
	 * retrieves the underlying collection which is then parsed by the delegated
	 * converter. This converter only takes care of the values inside the
	 * collections while the mapper takes care of the collections naming.
	 * 
	 * @see http://jira.codehaus.org/browse/XSTR-226
	 */
	private static class HibernateCollectionConverter implements Converter {
		private Converter listSetConverter;
		private Converter mapConverter;
		private Converter treeMapConverter;
		private Converter treeSetConverter;
		private Converter defaultConverter;

		public HibernateCollectionConverter(ConverterLookup converterLookup) {
			listSetConverter = converterLookup
					.lookupConverterForType(ArrayList.class);
			mapConverter = converterLookup
					.lookupConverterForType(HashMap.class);
			treeMapConverter = converterLookup
					.lookupConverterForType(TreeMap.class);
			treeSetConverter = converterLookup
					.lookupConverterForType(TreeSet.class);
			defaultConverter = converterLookup
					.lookupConverterForType(Object.class);
		}

		/**
		 * @see com.thoughtworks.xstream.converters.Converter#canConvert(java.lang.Class)
		 */
		public boolean canConvert(Class type) {
			return PersistentCollection.class.isAssignableFrom(type);
		}

		/**
		 * @see com.thoughtworks.xstream.converters.Converter#marshal(java.lang.Object,
		 *      com.thoughtworks.xstream.io.HierarchicalStreamWriter,
		 *      com.thoughtworks.xstream.converters.MarshallingContext)
		 */
		public void marshal(Object source, HierarchicalStreamWriter writer,
				MarshallingContext context) {
			Object collection = source;

			if (source instanceof PersistentCollection) {
				PersistentCollection col = (PersistentCollection) source;
				col.forceInitialization();
				collection = col.getStoredSnapshot();
			}

			if (source instanceof PersistentSortedSet) {
				collection = new TreeSet(((HashMap) collection).values());
			} else if (source instanceof PersistentSet) {
				collection = new HashSet(((HashMap) collection).values());
			}

			// delegate the collection to the appropriate converter
			if (listSetConverter.canConvert(collection.getClass())) {
				listSetConverter.marshal(collection, writer, context);
				return;
			}
			if (mapConverter.canConvert(collection.getClass())) {
				mapConverter.marshal(collection, writer, context);
				return;
			}
			if (treeMapConverter.canConvert(collection.getClass())) {
				treeMapConverter.marshal(collection, writer, context);
				return;
			}
			if (treeSetConverter.canConvert(collection.getClass())) {
				treeSetConverter.marshal(collection, writer, context);
				return;
			}

			defaultConverter.marshal(collection, writer, context);
		}

		/**
		 * @see com.thoughtworks.xstream.converters.Converter#unmarshal(com.thoughtworks.xstream.io.HierarchicalStreamReader,
		 *      com.thoughtworks.xstream.converters.UnmarshallingContext)
		 */
		public Object unmarshal(HierarchicalStreamReader reader,
				UnmarshallingContext context) {
			return null;
		}
	}

	/**
	 * Replaces Hibernate 3 specific collections with java.util implementations.
	 * 
	 * <strong>NOTE</strong> This mapper takes care only of the writing to the
	 * XML (deflating) not the other way around (inflating) because there is no
	 * need.
	 * 
	 * @see http://jira.codehaus.org/browse/XSTR-226
	 */
	private static class HibernateCollectionsMapper extends
			AbstractXmlFriendlyMapper // MapperWrapper
	{
		private final static String[] hbClassNames = {
				PersistentList.class.getName(), PersistentSet.class.getName(),
				PersistentMap.class.getName(),
				PersistentSortedSet.class.getName(),
				PersistentSortedMap.class.getName() };

		private final static String[] jdkClassNames = {
				ArrayList.class.getName(), HashSet.class.getName(),
				HashMap.class.getName(), TreeSet.class.getName(),
				TreeMap.class.getName() };

		private final static Class[] hbClasses = { PersistentList.class,
				PersistentSet.class, PersistentMap.class,
				PersistentSortedSet.class, PersistentSortedMap.class };

		private final static Class[] jdkClasses = { ArrayList.class,
				HashSet.class, HashMap.class, TreeSet.class, TreeMap.class };

		public HibernateCollectionsMapper(Mapper wrapped) {
			super(wrapped);
		}

		public Class realClass(String elementName) {
			return super.realClass(unescapeClassName(elementName));
		}

		public String realMember(Class type, String serialized) {
			return unescapeFieldName(super.realMember(type, serialized));
		}

		/**
		 * @see com.thoughtworks.xstream.mapper.Mapper#serializedClass(java.lang.Class)
		 */
		public String serializedClass(Class type) {
			return escapeClassName(super.serializedClass(replaceClasses(type)));
		}

		/**
		 * @see com.thoughtworks.xstream.mapper.Mapper#serializedMember(java.lang.Class,
		 *      java.lang.String)
		 */
		public String serializedMember(Class type, String fieldName) {
			return escapeFieldName(super.serializedMember(replaceClasses(type),
					fieldName));
		}

		/**
		 * Simple replacements between the HB 3 collections and their underlying
		 * collections from java.util.
		 * 
		 * @param name
		 * @return the equivalent JDK class name
		 */
		private String replaceClasses(String name) {
			for (int i = 0; i < hbClassNames.length; i++) {
				if (name.equals(hbClassNames[i]))
					return jdkClassNames[i];
			}

			return name;
		}

		/**
		 * Simple replacements between the HB 3 collections and their underlying
		 * collections from java.util.
		 * 
		 * @param clazz
		 * @return the equivalent JDK class
		 */
		private Class replaceClasses(Class clazz) {
			for (int i = 0; i < hbClasses.length; i++) {
				if (clazz.equals(hbClasses[i]))
					return jdkClasses[i];
			}
			return clazz;
		}
	}

	/**
	 * Map between Hibernate lazy loading Collection types and Java Collection
	 * types.
	 * 
	 * @see http://jira.codehaus.org/browse/XSTR-226
	 */
	private static class HibernateMapper extends MapperWrapper {

		Map collectionMap = new HashMap();

		public HibernateMapper(Mapper arg0) {
			super(arg0);
			init();
		}

		public void init() {
			collectionMap.put(PersistentBag.class, ArrayList.class);
			collectionMap.put(PersistentList.class, ArrayList.class);
			collectionMap.put(PersistentMap.class, HashMap.class);
			collectionMap.put(PersistentSet.class, HashSet.class);
			collectionMap.put(PersistentSortedMap.class, TreeMap.class);
			collectionMap.put(PersistentSortedSet.class, TreeSet.class);
		}

		public Class defaultImplementationOf(Class clazz) {
			// System.err.println("checking class:" + clazz);
			if (collectionMap.containsKey(clazz)) {
				// System.err.println("** substituting " + clazz + " with " +
				// collectionMap.get(clazz));
				return (Class) collectionMap.get(clazz);
			}

			return super.defaultImplementationOf(clazz);
		}

		public String serializedClass(Class clazz) {
			// check whether we are hibernate proxy and substitute real name
			for (int i = 0; i < clazz.getInterfaces().length; i++) {
				if (HibernateProxy.class.equals(clazz.getInterfaces()[i])) {
					// System.err.println("resolving to class name:" +
					// clazz.getSuperclass().getName());
					return clazz.getSuperclass().getName();
				}
			}
			if (collectionMap.containsKey(clazz)) {
				// System.err.println("** substituting " + clazz + " with " +
				// collectionMap.get(clazz));
				return ((Class) collectionMap.get(clazz)).getName();
			}

			return super.serializedClass(clazz);
		}

	}

	/**
	 * Remove Hibernate lazy loading proxy classes.
	 * 
	 * @see http://jira.codehaus.org/browse/XSTR-226
	 */
	private static class HibernateProxyConverter extends ReflectionConverter {

		public HibernateProxyConverter(Mapper arg0, ReflectionProvider arg1) {
			super(arg0, arg1);
		}

		/**
		 * be responsible for hibernate proxy
		 */
		public boolean canConvert(Class clazz) {
			return HibernateProxy.class.isAssignableFrom(clazz);
		}

		public void marshal(Object arg0, HierarchicalStreamWriter arg1,
				MarshallingContext arg2) {
			super.marshal(((HibernateProxy) arg0).getHibernateLazyInitializer()
					.getImplementation(), arg1, arg2);
		}

	}
}