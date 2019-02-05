package cn.edu.jxau.dao.impl;

import cn.edu.jxau.dao.BaseEnvironmentAware;
import cn.edu.jxau.dao.BaseRegionAware;
import cn.edu.jxau.dao.dataobject.BaseDO;
import cn.edu.jxau.dao.dataobject.BaseQuery;
import cn.edu.jxau.dao.dataobject.BaseRegion;
import cn.edu.jxau.dao.dataobject.enums.BaseEnvironment;
import cn.edu.jxau.dao.util.MapperUtil;
import org.apache.commons.collections.CollectionUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DAO的抽象基类
 *
 * @author：zclong
 * @date：2019/2/1
 */
public abstract class BaseDAO<T extends BaseDO, Q extends BaseQuery> implements BaseEnvironmentAware, BaseRegionAware {

    @Autowired
    @Qualifier("jxauSqlSessionTemplate")
    SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    MapperUtil mapperUtil;

    public Long insert(T entity) {
        entity.setOwnSign(getCurrentEnv());
        entity.setRegionSign(getCurrentRegion());
        sqlSessionTemplate.insert(getSqlStatement("insert"), entity);
        return entity.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer insertBatch(List<T> entityList) {
        int count = 0;
        List<T> tempList = new ArrayList<>();
        for (T entity : entityList) {
            entity.setOwnSign(getCurrentEnv());
            entity.setRegionSign(getCurrentRegion());
            tempList.add(entity);
            if (++count % 1000 == 0) {
                mapperUtil.insertBatch(getSqlStatement("insert"), tempList);
                tempList.clear();
            }
        }
        if (CollectionUtils.isNotEmpty(tempList)) {
            mapperUtil.insertBatch(getSqlStatement("insert"), tempList);
        }
        return entityList.size();
    }

    public T selectByPrimaryKey(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("call selectByPrimaryKey an id can't be null");
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("id", id);
        return sqlSessionTemplate.selectOne(getSqlStatement("selectByPrimaryKey"), map);
    }

    public List<T> queryPage(Q query) {
        query.setOwnSign(getCurrentEnv());
        return sqlSessionTemplate.selectList(getSqlStatement("queryPage"), query);
    }

    public Integer updateByPrimaryKey(T entity) {
        entity.setOwnSign(getCurrentEnv());
        entity.setRegionSign(getCurrentRegion());
        return sqlSessionTemplate.update(getSqlStatement("updateByPrimaryKey"), entity);
    }

    /**
     * 通用批量更新不会判断更新行数，是否更新成功
     *
     * @param entityList
     * @return
     */
    public Integer updateBatch(List<T> entityList) {
        int count = 0;
        List<T> tempList = new ArrayList<>();
        for (T entity : entityList) {
            entity.setOwnSign(getCurrentEnv());
            entity.setRegionSign(getCurrentRegion());
            tempList.add(entity);
            if (++count % 1000 == 0) {
                mapperUtil.updateBatch(getSqlStatement("updateByPrimaryKey"), tempList);
                tempList.clear();
            }
        }
        if (CollectionUtils.isNotEmpty(tempList)) {
            mapperUtil.updateBatch(getSqlStatement("updateByPrimaryKey"), tempList);
        }
        return entityList.size();
    }

    /**
     * 通用批量删除不会判断更新行数，是否删除成功
     *
     * @param entityList
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteBatch(List<T> entityList) {
        int count = 0;
        List<T> tempList = new ArrayList<>();
        for (T entity : entityList) {
            entity.setOwnSign(getCurrentEnv());
            entity.setRegionSign(getCurrentRegion());
            tempList.add(entity);
            if (++count % 1000 == 0) {
                mapperUtil.deleteBatch(getSqlStatement("delete"), tempList);
                tempList.clear();
            }
        }
        if (CollectionUtils.isNotEmpty(tempList)) {
            mapperUtil.deleteBatch(getSqlStatement("delete"), tempList);
        }
        return entityList.size();
    }

    public List<T> selectAll(Q query) {
        query.setOwnSign(getCurrentEnv());
        return sqlSessionTemplate.selectList(getSqlStatement("selectAll"), query);
    }

    public Integer countByQuery(Q query) {
        query.setOwnSign(getCurrentEnv());
        return sqlSessionTemplate.selectOne(getSqlStatement("countByQuery"), query);
    }

    /**
     * 获取执行标识
     *
     * @param name
     * @return
     */
    protected String getSqlStatement(String name) {
        return getTableName() + "." + name;
    }

    /**
     * 表名称
     *
     * @return
     */
    protected abstract String getTableName();

    /**
     * 环境标识
     * TODO 上线时将状态改为 PROD
     */
    private BaseEnvironment baseEnvironment = BaseEnvironment.DEV;

    /**
     * 区域标识
     */
    private BaseRegion baseRegion;

    @Override
    public void setBaseEnvironment(BaseEnvironment baseEnvironment) {
        this.baseEnvironment = baseEnvironment;
    }

    @Override
    public void setBaseRegion(BaseRegion baseRegion) {
        this.baseRegion = baseRegion;
    }

    /**
     * 获取当前环境标识
     *
     * @return
     */
    public String getCurrentEnv() {
        return baseEnvironment.getName();
    }

    /**
     * 获取当前的区域标识
     * TODO 区域问题
     *
     * @return
     */
    public String getCurrentRegion() {
        if (baseRegion == null) {
            baseRegion = new BaseRegion("jx");
        }
        return baseRegion.getId();
    }
}
