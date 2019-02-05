package cn.edu.jxau.service;

import cn.edu.jxau.dao.dataobject.UserDO;
import cn.edu.jxau.dao.dataobject.UserQuery;
import cn.edu.jxau.service.bo.UserBO;
import cn.edu.jxau.service.bo.UserQueryBO;
import cn.edu.jxau.service.bo.UserWriteBO;

import java.util.List;

/**
 * @author zark.zcl
 * @date 2018/10/4
 */
public interface IUserService {

    /**
     * 加载所有的用户信息
     *
     * @param queryBO
     * @return
     */
    List<UserBO> loadAllUser(UserQueryBO queryBO);

    /**
     * 分页查询用户信息
     *
     * @param queryBO
     * @return
     */
    List<UserBO> queryUserListByPage(UserQueryBO queryBO);

    /**
     * 新建用户信息
     *
     * @param writeBO
     * @return
     */
    Boolean createUser(UserWriteBO writeBO);

    /**
     * 批量新增用户
     *
     * @param writeBOList
     * @return
     */
    Boolean batchCreateUser(List<UserWriteBO> writeBOList);

    /**
     * 通过主键删除
     *
     * @param id
     * @return
     */
    Boolean deleteByPrimaryKey(Long id);

    /**
     * 删除用户
     *
     * @param userBO
     * @return
     */
    Boolean deleteUser(UserBO userBO);

    /**
     * 批量删除用户
     *
     * @param userBOList
     * @return
     */
    Boolean batchDeleteUser(List<UserBO> userBOList);

    /**
     * 编辑用户
     *
     * @param userWriteBO
     * @return
     */
    Boolean updateUser(UserWriteBO userWriteBO);

    /**
     * mybatis批量插入
     *
     * @param userList
     * @return
     */
    Integer batchInsert(List<UserBO> userList);


}

