package cn.jzteam.dao.entity.annotation;

/**
 * mysql常用数据类型枚举
 * 
 * @author zhujz
 *
 */
public enum JdbcType {
    /** boolean */
    BIT,
    /** byte */
    TINYINT,
    /** short */
    SMALLINT,
    /** int */
    INTEGER,
    /** long */
    BIGINT,
    /** float */
    FLOAT,
    /** double */
    DOUBLE,
    /** 精确数据类型 */
    DECIMAL,
    /** String */
    CHAR,
    /** String */
    VARCHAR,
    /** String */
    TEXT,
    /** Date */
    DATE,
    /** Time */
    TIME,
    /** TimeStamp */
    TIMESTAMP,
    /** String */
    LONGVARBINARY,
    /** 大文本、二进制 */
    BLOB;
}
