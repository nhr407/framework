package priv.zhouhuayi.framework.util.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

import priv.zhouhuayi.framework.util.anno.FieldAnno;
import priv.zhouhuayi.framework.util.anno.TableAnno;

/**
 * 实体工具类
 * 
 * @author zhy
 *
 */
public class BeanUtil {
	/**
	 * 获取实体注解对应的数据库字段名用于查询语句
	 * 
	 * @author zhy
	 * @param clazz 实体Class
	 * @return
	 */
	public static String getTableColumnsByFind(Class<?> clazz) {
		StringBuffer columns = new StringBuffer("");
		
		// 获取实体字段集合  
		Field[] fields = clazz.getDeclaredFields();
		try {
	        for (Field f : fields) {
	        	if(f.getName().equals("serialVersionUID")) {
	        		continue;
	        	}
	        	// 通过反射获取该属性对应的值  
	            f.setAccessible(true);
            
				FieldAnno fieldanno = f.getAnnotation(FieldAnno.class);
				String filedName = "";
				if(fieldanno == null) {
					filedName = f.getName();
				} else {
					filedName = fieldanno.fieldName() + " " + f.getName();
				}
				columns.append("," + filedName);
	        }
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
        return columns.substring(1);
	}
	
	/**
	 * 获取实体注解对应的数据库字段名用于添加语句
	 * 
	 * @author zhy
	 * @param clazz 实体Class
	 * @return
	 */
	public static String getTableColumnsByAdd(Class<?> clazz) {
		StringBuffer columns = new StringBuffer("");
		
		// 获取实体字段集合  
		Field[] fields = clazz.getDeclaredFields();
		try {
	        for (Field f : fields) {
	        	if(f.getName().equals("serialVersionUID")) {
	        		continue;
	        	}
	        	// 通过反射获取该属性对应的值  
	            f.setAccessible(true);
            
				FieldAnno fieldanno = f.getAnnotation(FieldAnno.class);
				String filedName = "";
				if(fieldanno == null) {
					filedName = f.getName();
				} else {
					filedName = fieldanno.fieldName();
				}
				columns.append("," + filedName);
	        }
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
        return columns.substring(1);
	}
	
	/**
	 * 获取实体字段列表
	 * 
	 * @author zhy
	 * @param clazz 实体Class
	 * @return
	 */
	public static String getClassColumns(Class<?> clazz) {
		String columns = "";
		// 定义实体信息对象
		BeanInfo beanInfo;
		try {
			// 获取实体详细信息
			beanInfo = Introspector.getBeanInfo(clazz);

			// 获取实体属性描述集合
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				// 获取属性描述
				PropertyDescriptor descriptor = propertyDescriptors[i];

				// 获取属性名
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					columns += propertyName + ",";
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return columns.substring(1);
	}
	
	/**
	 * 得到数据库中的表名
	 * 
	 * @author 周化益
	 * @param entityName 实体Class
	 * @return 实体名
	 */
	public static String getTableName(Class<? extends Object> entityName) {
		return entityName.getAnnotation(TableAnno.class).tableName();
	}
	
	/**
	 * 得到实体名
	 * 
	 * @author 周化益
	 * @param entityName 实体Class
	 * @return 实体名
	 */
	public static String getClassName(Class<? extends Object> entityName) {
		return entityName.getSimpleName().toUpperCase();
	}
}
