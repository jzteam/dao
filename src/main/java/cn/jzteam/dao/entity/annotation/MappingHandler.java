package cn.jzteam.dao.entity.annotation;

/**
 * 数据库和实体类型转换类接口
 * 
 * @author zhujz
 *
 * @param <D>
 * @param <E>
 */
public interface MappingHandler<D, E> {

    /**
     * 数据库转实体
     */
    public E convert(D d);

    /**
     * 实体转数据库
     */
    public D reconvert(E e);

}
