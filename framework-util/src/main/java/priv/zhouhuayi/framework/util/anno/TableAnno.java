package priv.zhouhuayi.framework.util.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对应数据表名称注解类
 * 
 * @author zhy
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR,ElementType.TYPE})
@Documented
public @interface TableAnno {
	String tableName();
}
