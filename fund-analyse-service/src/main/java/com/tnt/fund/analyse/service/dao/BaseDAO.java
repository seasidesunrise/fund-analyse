package com.tnt.fund.analyse.service.dao;

import com.tnt.fund.analyse.util.page.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDAO<E, PK extends Serializable> {

    /**
     * 根据主键id获取对象
     *
     * @param id 主键
     * @return 实体对象
     */
    E get(PK id);

    /**
     * 根据主键id删除实体
     *
     * @param id 主键
     */
    int delete(PK id);

    /**
     * 保存实体对象
     *
     * @param entity 实体对象
     * @return 实体对象
     */
    E save(E entity);

    /**
     * 更新实体
     *
     * @param entity 实体对象
     * @return 影响记录条数
     */
    int update(E entity);

    /**
     * 根据参数Map更新实体
     *
     * @param map
     * @return 影响记录条数
     */
    int update(Map map);

    /**
     * 根据属性名和属性值查询唯一对象
     *
     * @param propertyName 属性名称
     * @param value        属性值
     * @return 实体对象
     */
    E findUniqueBy(String propertyName, Object value);

    /**
     * 根据属性名和属性值map查询符合条件的对象
     *
     * @param map 属性值map
     * @return 实体对象列表
     */
    List<E> findListByMap(Map map);

    /**
     * 获取分页数据
     *
     * @param page
     * @param e
     * @return
     */
    Page<E> getPage(Page<E> page, E e);

    /**
     * 根据Map获取分页数据
     *
     * @param page
     * @param paramMap
     * @return
     */
    Page<E> getPageByMap(Page<E> page, Map paramMap);

}
