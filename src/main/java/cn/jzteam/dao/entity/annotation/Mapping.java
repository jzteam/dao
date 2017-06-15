package cn.jzteam.dao.entity.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段映射：数据库字段跟entity字段不一致，需要此注解配置映射关系
 * 
 * @author zhujz
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mapping {

    /**
     * 表字段真实名称, 可空, 默认entity字段名
     */
    String fieldName() default "";

    /**
     * 表字段jdbc类型, 必填
     */
    JdbcType fieldType();

    /**
     * 处理类
     */
    Class<? extends MappingHandler> mappingHandler() default MappingHandler.class;

}
