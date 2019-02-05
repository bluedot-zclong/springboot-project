package cn.edu.jxau.dao.util;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Collection;

/**
 * 数据库批量操作template
 *
 * @author：zclong
 * @date：2019/2/1
 */
public abstract class AbstractMapperTemplate {

    public void doBatch(SqlSessionFactory sqlSessionFactory, String sqlStatement, Collection<?> objects) {
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try {
            for (Object object : objects) {
                singleOperation(session, sqlStatement, object);
            }
            session.commit();
            session.clearCache();
        } catch (Exception ex) {
            session.rollback();
            throw new RuntimeException("doBatch Exception.", ex);
        } finally {
            session.close();
        }
    }

    protected abstract void singleOperation(SqlSession session, String sqlStatement, Object object);
}
