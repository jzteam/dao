package cn.jzteam.dao.entity;

import com.sun.xml.internal.ws.api.addressing.WSEndpointReference.Metadata;

public interface IEntity {

    /** 获取当前实体元数据 */
    public Metadata metadata();

    /** 获取当前实体的主键的值 */
    public Long pkValue();

}
