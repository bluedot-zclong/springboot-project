package cn.edu.jxau.dao.util;

import cn.edu.jxau.dao.dataobject.BaseDO;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * Mapper工具
 *
 * @author：zclong
 * @date：2019/2/1
 */
@Component("mapperUtil")
public class MapperUtil {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public <T extends BaseDO> void insertBatch(String sqlStatement, Collection<T> objects) {
        new AbstractMapperTemplate() {

            @Override
            protected void singleOperation(SqlSession session, String statement, Object object) {
                session.insert(sqlStatement, object);
            }
        }.doBatch(sqlSessionTemplate.getSqlSessionFactory(), sqlStatement, objects);
    }

    public <T extends BaseDO> void updateBatch(String sqlStatement, List<T> objects) {
        new AbstractMapperTemplate() {

            @Override
            protected void singleOperation(SqlSession session, String statement, Object object) {
                session.update(sqlStatement, object);
            }
        }.doBatch(sqlSessionTemplate.getSqlSessionFactory(), sqlStatement, objects);
    }

    public <T extends BaseDO> void deleteBatch(String sqlStatement, List<T> objects) {
        new AbstractMapperTemplate() {

            @Override
            protected void singleOperation(SqlSession session, String sqlStatement, Object object) {
                session.delete(sqlStatement, object);
            }
        }.doBatch(sqlSessionTemplate.getSqlSessionFactory(), sqlStatement, objects);
    }
}
