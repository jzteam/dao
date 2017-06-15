package cn.jzteam.dao.entity.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.jzteam.dao.entity.annotation.ChangeLog;
import cn.jzteam.dao.entity.annotation.EntityInfo;
import cn.jzteam.dao.entity.annotation.Index;
import cn.jzteam.dao.entity.annotation.Mapping;
import cn.jzteam.dao.entity.annotation.PrimaryKey;
import cn.jzteam.dao.entity.metadata.FieldInfo;
import cn.jzteam.dao.entity.metadata.IndexRelation;
import cn.jzteam.dao.entity.metadata.Metadata;

public class AnnotationUtil {
    /**
     * 读取实体注解信息
     */
    public static Metadata getMetadata(Class<?> clazz) {
        Metadata metadata = new Metadata();

        // 读取class注解
        String tableName = clazz.getSimpleName().toLowerCase();
        EntityInfo entityInfo = (EntityInfo) clazz.getAnnotation(EntityInfo.class);
        if (entityInfo != null) {
            metadata.setDbName(entityInfo.dbName());
            if (!entityInfo.tableName().equals("")) {
                tableName = entityInfo.tableName();
            }
            if (!entityInfo.shardingSchema().equals("")) {
                metadata.setSchema(entityInfo.shardingSchema());
            }
            metadata.setUseCache(entityInfo.useCache());
            metadata.setTableChangeLog(entityInfo.changeLog());
        }
        metadata.setTableName(tableName);

        // 获取实体所有字段，包含父类字段
        List<Field> fields = new ArrayList<Field>();
        Class<?> tempClazz = clazz;
        while (tempClazz != null && tempClazz != Object.class) {
            fields.addAll(Arrays.asList(tempClazz.getDeclaredFields()));
            tempClazz = tempClazz.getSuperclass();
        }

        // 读取field注解
        IndexRelation indexRelation = null;
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {

                if (annotation instanceof ChangeLog) {
                    // 读取changelog信息
                    field.setAccessible(true);
                    metadata.setChangeLogField(field);
                } else if (annotation instanceof PrimaryKey) {
                    // 读取主键信息
                    PrimaryKey primaryKey = (PrimaryKey) annotation;
                    metadata.setPkField(field);
                    metadata.setPkIndexType(primaryKey.indexType());
                    break;// 主键字段只支持一个注解
                } else if (annotation instanceof Mapping) {
                    // 读取字段信息
                    Mapping mapping = (Mapping) annotation;
                    FieldInfo fieldInfo = new FieldInfo();
                    String name = mapping.fieldName();
                    if (name.equals("")) {
                        name = field.getName();
                    }
                    fieldInfo.setFieldName(name);
                    fieldInfo.setJdbcType(mapping.fieldType());
                    fieldInfo.setMappingHandlerClass(mapping.mappingHandler());
                    metadata.putFieldInfo(field, fieldInfo);
                } else if (annotation instanceof Index) {
                    // index映射索引信息
                    if (indexRelation == null) {
                        Index index = (Index) annotation;
                        indexRelation = new IndexRelation(field.getName(), index.unique());
                    }
                    break;// 实体索引映射字段只能有一个，只支持一个注解
                }
            }
        }
        if (indexRelation != null) {
            indexRelation.setTo(metadata.getPkField().getName());
        }
        metadata.setIndexRelation(indexRelation);

        return metadata;
    }

    /**
     * 读取实体主键字段
     */
    public static Field getPrimaryKeyField(Class<?> clazz) {
        AccessibleObject[] fields = clazz.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (AccessibleObject ao : fields) {
                ao.setAccessible(true);
                Field field = (Field) ao;
                PrimaryKey primaryKey = (PrimaryKey) field.getAnnotation(PrimaryKey.class);
                if (primaryKey != null) {
                    return field;
                }
            }
        }
        return null;
    }
}
