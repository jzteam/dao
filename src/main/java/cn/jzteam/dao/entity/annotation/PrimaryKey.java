package cn.jzteam.dao.entity.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识主键索引类型：是否是自增的；可空，默认自增
 * 
 * @author zhujz
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PrimaryKey {

    IndexType indexType() default IndexType.AUTO_INCREMENT;

}
