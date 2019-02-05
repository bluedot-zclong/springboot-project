package cn.edu.jxau.dao;

import cn.edu.jxau.dao.dataobject.UserDO;
import cn.edu.jxau.dao.dataobject.UserQuery;

import java.util.List;


/**
 * @author zark.zcl
 * @date 2018/10/6
 * @Mapper ： Mybatis根据接口定义与Mapper文件中的SQL语句动态创建接口实现
 * @Param ：注解参数，在Mapper.xml配置文件中，可以采用#{}的方式对@Param 注解括号内的参数进行引用
 */
public interface IUserDAO {

    /**
     * 新增用户信息
     *
     * @param record
     * @return
     */
    Long insert(UserDO record);

    Integer insertBatch(List<UserDO> recordList);

    UserDO selectByPrimaryKey(Long id);

    List<UserDO> queryPage(UserQuery query);

    List<UserDO> selectAll(UserQuery query);

    Integer updateByPrimaryKey(UserDO record);

    Integer updateBatch(List<UserDO> recordList);

    Integer deleteByPrimaryKey(Long id);

    Integer delete(UserDO record);

    Integer deleteBatch(List<UserDO> recordList);

    Integer deleteBatchByPrimaryKey(List<Long> recordList);

    Integer countByQuery(UserQuery query);

}
