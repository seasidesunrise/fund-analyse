package com.tnt.fund.analyse.service.service.impl;

import com.tnt.fund.analyse.service.dao.BaseDAO;
import com.tnt.fund.analyse.service.service.BaseService;
import com.tnt.fund.analyse.util.page.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Transactional
public abstract class BaseServiceImpl<E, PK extends Serializable> implements BaseService<E, PK> {
    protected final Log log = LogFactory.getLog(getClass().getName());

    protected abstract BaseDAO<E, PK> getBaseDao();

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public E get(PK id) {
        return getBaseDao().get(id);
    }

    public int delete(PK id) {
        return getBaseDao().delete(id);
    }

    public E save(E entity) {
        return getBaseDao().save(entity);
    }

    public int update(E entity) {
        return getBaseDao().update(entity);
    }

    public int update(Map map) {
        return getBaseDao().update(map);
    }

    public E findUniqueBy(String propertyName, Object value) {
        return getBaseDao().findUniqueBy(propertyName, value);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<E> findListByMap(Map<String, Object> map) {
        return getBaseDao().findListByMap(map);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public Page<E> getPage(Page<E> page, E e) {
        return getBaseDao().getPage(page, e);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public Page<E> getPageByMap(Page<E> page, Map map) {
        return getBaseDao().getPageByMap(page, map);
    }

}
