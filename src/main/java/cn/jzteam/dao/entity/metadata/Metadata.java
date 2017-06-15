package cn.jzteam.dao.entity.metadata;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.jzteam.dao.entity.annotation.IndexType;

/**
 * 实体的元数据
 * 
 * @author zhujz
 *
 */
public class Metadata {
    /** 库名 */
    private String dbName;

    /** 表名 */
    private String tableName;

    /** 是否启用一级缓存 */
    private boolean useCache = true;

    /** 主键字段 */
    private Field pkField;

    /** 主键字段 */
    private IndexType pkIndexType;

    /** 字段信息 */
    private Map<Field, FieldInfo> fieldInfoMap = new HashMap<Field, FieldInfo>();

    /** 分库策略名 */
    private String schema;

    /** 映射索引信息 */
    private IndexRelation indexRelation;

    /** 是否有针对表在insert或delete时记录变化日志 */
    private boolean tableChangeLog = false;

    /** 存储某些字段update时需要记录变化日志的字段集合 */
    private Set<Field> changeLogFields = new HashSet<Field>();

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public boolean isUseCache() {
        return useCache;
    }

    public void setUseCache(boolean useCache) {
        this.useCache = useCache;
    }

    public Field getPkField() {
        return pkField;
    }

    public void setPkField(Field pkField) {
        this.pkField = pkField;
    }

    public IndexType getPkIndexType() {
        return pkIndexType;
    }

    public void setPkIndexType(IndexType pkIndexType) {
        this.pkIndexType = pkIndexType;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public IndexRelation getIndexRelation() {
        return indexRelation;
    }

    public void setIndexRelation(IndexRelation indexRelation) {
        this.indexRelation = indexRelation;
    }

    public Map<Field, FieldInfo> getFieldInfoMap() {
        return fieldInfoMap;
    }

    public void putFieldInfo(Field field, FieldInfo fieldInfo) {
        this.fieldInfoMap.put(field, fieldInfo);
    }

    public boolean isTableChangeLog() {
        return tableChangeLog;
    }

    public void setTableChangeLog(boolean tableChangeLog) {
        this.tableChangeLog = tableChangeLog;
    }

    public boolean hasChangeLogFields() {
        return this.changeLogFields.size() > 0;
    }

    public void setChangeLogField(Field field) {
        this.changeLogFields.add(field);
    }

    public Set<Field> getChangeLogFields() {
        return changeLogFields;
    }

    @Override
    public String toString() {
        return "Metadata [dbName=" + dbName + ", tableName=" + tableName + ", useCache=" + useCache + ", pkField="
                + pkField + ", pkIndexType=" + pkIndexType + ", fieldInfoMap=" + fieldInfoMap + ", schema=" + schema
                + ", indexRelation=" + indexRelation + ", tableChangeLog=" + tableChangeLog + ", changeLogFields="
                + changeLogFields + "]";
    }
}
