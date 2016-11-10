package com.tnt.fund.analyse.service.dao.impl;

import com.tnt.fund.analyse.service.dao.BaseDAO;
import com.tnt.fund.analyse.util.BeanToMapUtil;
import com.tnt.fund.analyse.util.page.Page;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

public class BaseDAOMybatis<T, PK extends Serializable> implements BaseDAO<T, PK> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public static final String POSTFIX_SELECTBYID = ".selectByPrimaryKey";
    public static final String POSTFIX_SELECTLISTBYMAP = ".selectListByMap";

    public static final String POSTFIX_UPDATE = ".updateByPrimaryKeySelective";
    public static final String POSTFIX_UPDATEBYMAP = ".updateByMap";

    public static final String POSTFIX_INSERT = ".insertSelective";

    public static final String POSTFIX_DELETEBYID = ".deleteByPrimaryKey";

    public static final String TOTAL_COUNT_END_STR = ".TotalCount";
    public static final String POSTFIX_SELECTPAGEBYMAP = ".selectPageByMap";
    public static final String START_ROW = "startRow";
    public static final String MAX_ROW_NUM = "maxRowNum";

    protected Class<T> clazz;
    protected String clazzName;

    public BaseDAOMybatis() {
        clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        clazzName = clazz.getName() + "Mapper";
    }

    @Override
    public T get(PK id) {
        T t = (T) sqlSessionTemplate.selectOne(clazzName + POSTFIX_SELECTBYID, id);
        return t;
    }

    @Override
    public int delete(PK id) {
        int affectedRow = sqlSessionTemplate.delete(clazzName + POSTFIX_DELETEBYID, id);
        return affectedRow;
    }

    @Override
    public T save(T entity) {
        int affectedRow = sqlSessionTemplate.insert(clazzName + POSTFIX_INSERT, entity);
        if (affectedRow == 1) {
            return entity;
        } else {
            return null;
        }
    }

    @Override
    public int update(T entity) {
        return sqlSessionTemplate.update(clazzName + POSTFIX_UPDATE, entity);
    }

    @Override
    public int update(Map map) {
        return sqlSessionTemplate.update(clazzName + POSTFIX_UPDATEBYMAP, map);
    }

    @Override
    public T findUniqueBy(String propertyName, Object value) {
        try {
            T t = clazz.newInstance();
            PropertyUtils.setProperty(t, propertyName, value);
            List<T> list = findListByEnity(t);
            if (CollectionUtils.isNotEmpty(list)) {
                return list.get(0);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
        return null;
    }

    public List<T> findListByEnity(T entity) {
        Map parameterMap = BeanToMapUtil.convertBean(entity);
        return sqlSessionTemplate.selectList(clazzName + POSTFIX_SELECTLISTBYMAP, parameterMap);
    }

    @Override
    public List<T> findListByMap(Map map) {
        return sqlSessionTemplate.selectList(clazzName + POSTFIX_SELECTLISTBYMAP, map);
    }

    @Override
    public Page<T> getPage(Page<T> page, T e) {
        Map paramMap = BeanToMapUtil.convertBean(e);
        return getPageByMap(page, paramMap);
    }

    @Override
    public Page<T> getPageByMap(Page<T> page, Map paramMap) {
        // 统计并设置结果集记录总数
        if (page.isAutoCount()) {
            Object totalCount = sqlSessionTemplate.selectOne(clazzName + TOTAL_COUNT_END_STR, paramMap);
            page.setTotalCount((Long) totalCount);
        }

        // 获得page中的参数并设置到ParameterMap
        // 获取记录的开始行数,从0开始
        int startRow = page.getFirst();
        // 步长,一共获取几行
        int maxRowNum = page.getPageSize();

        // 参数并设置到ParameterMap
        paramMap.put(START_ROW, startRow);
        paramMap.put(MAX_ROW_NUM, maxRowNum);

        // 根据statementName和查询条件parameterObject查询
        List resultList = sqlSessionTemplate.selectList(clazzName + POSTFIX_SELECTPAGEBYMAP, paramMap);
        page.setResult(resultList);

        return page;
    }

}
