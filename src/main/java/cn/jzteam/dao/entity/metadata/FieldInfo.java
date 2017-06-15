package cn.jzteam.dao.entity.metadata;

import cn.jzteam.dao.entity.annotation.JdbcType;
import cn.jzteam.dao.entity.annotation.MappingHandler;

/**
 * 字段基本信息
 * 
 * @author zhujz
 *
 */
@SuppressWarnings("rawtypes")
public class FieldInfo {

    /** 字段名 */
    private String fieldName;

    /** 字段的数据库类型 */
    private JdbcType jdbcType;

    /** 字段映射实例 */
    private Class<? extends MappingHandler> mappingHandlerClass;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public JdbcType getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(JdbcType jdbcType) {
        this.jdbcType = jdbcType;
    }

    public Class<? extends MappingHandler> getMappingHandlerClass() {
        return mappingHandlerClass;
    }

    public void setMappingHandlerClass(Class<? extends MappingHandler> mappingHandlerClass) {
        this.mappingHandlerClass = mappingHandlerClass;
    }

    @Override
    public String toString() {
        return "[fieldName=" + fieldName + ", jdbcType=" + jdbcType + ", mappingHandlerClass=" + mappingHandlerClass
                + "]";
    }

}
