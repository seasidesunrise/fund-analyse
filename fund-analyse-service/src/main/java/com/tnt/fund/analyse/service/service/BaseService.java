package com.tnt.fund.analyse.service.service;

import com.tnt.fund.analyse.util.page.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseService<E, PK extends Serializable> {

    E get(PK id);

    int delete(PK id);

    E save(E entity);

    int update(E entity);

    int update(Map map);

    E findUniqueBy(String propertyName, Object value);

    List<E> findListByMap(Map<String, Object> map);

    Page<E> getPage(Page<E> page, E e);

    Page<E> getPageByMap(Page<E> page, Map paramMap);

}