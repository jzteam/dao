package cn.jzteam.dao.entity.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义表实体信息：数据库名、表名、是否启用一级缓存、分库规则名
 * 
 * @author zhujz
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityInfo {
    /**
     * 数据库名, 必填
     */
    String dbName();

    /**
     * 表名, 可空, 默认类名
     */
    String tableName() default "";

    /**
     * 是否启用一级缓存, 可空, 默认启用
     */
    boolean useCache() default true;

    /**
     * 分库策略定义名, 可空, 默认不分库
     */
    String shardingSchema() default "";

    /**
     * 标示在针对这个表的insert或delete时输出日志
     */
    boolean changeLog() default false;
}
