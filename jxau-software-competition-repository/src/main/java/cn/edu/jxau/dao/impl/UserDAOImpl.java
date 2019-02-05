package cn.edu.jxau.dao.impl;

import cn.edu.jxau.dao.IUserDAO;
import cn.edu.jxau.dao.dataobject.UserDO;
import cn.edu.jxau.dao.dataobject.UserQuery;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author：zclong
 * @date：2019/2/1
 */
@Repository("userDAO")
public class UserDAOImpl extends BaseDAO<UserDO, UserQuery> implements IUserDAO {

    @Override
    protected String getTableName() {
        return "user";
    }

    @Override
    public Integer deleteByPrimaryKey(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("call deleteByPrimaryKey an id can't be null");
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("id", id);
        return sqlSessionTemplate.delete(getSqlStatement("deleteByPrimaryKey"), map);
    }

    @Override
    public Integer delete(UserDO record) {
        return sqlSessionTemplate.delete(getSqlStatement("delete"), record);
    }

    @Override
    public Integer deleteBatchByPrimaryKey(List<Long> recordList) {
        return sqlSessionTemplate.delete(getSqlStatement("deleteBatchByPrimaryKey"), recordList);
    }

}
