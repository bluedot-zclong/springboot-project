package cn.edu.jxau.service.impl;

import cn.edu.jxau.dao.IUserDAO;
import cn.edu.jxau.service.IUserService;
import cn.edu.jxau.service.bo.UserBO;
import cn.edu.jxau.service.bo.UserQueryBO;
import cn.edu.jxau.service.bo.UserWriteBO;
import cn.edu.jxau.service.bo.convert.UserConvert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zark.zcl
 * @date 2018/10/4
 */
@Service("userService")
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDAO userDAO;

    /**
     * 批量插入
     *
     * @param userList
     * @return
     */
    @Override
    public Integer batchInsert(List<UserBO> userList) {
        return userDAO.insertBatch(UserConvert.INSTANCE.doListToDOList(userList));
    }

    @Override
    public List<UserBO> loadAllUser(UserQueryBO queryBO) {
        return null;
    }

    @Override
    public List<UserBO> queryUserListByPage(UserQueryBO queryBO) {
        log.info("queryUserListByPage");
        return UserConvert.INSTANCE.doListToBOList(userDAO.queryPage(UserConvert.INSTANCE.toQuery(queryBO)));
    }

    @Override
    public Boolean createUser(UserWriteBO writeBO) {
        return null;
    }

    @Override
    public Boolean batchCreateUser(List<UserWriteBO> writeBOList) {
        return null;
    }

    @Override
    public Boolean deleteByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public Boolean deleteUser(UserBO userBO) {
        return null;
    }

    @Override
    public Boolean batchDeleteUser(List<UserBO> userBOList) {
        return null;
    }

    @Override
    public Boolean updateUser(UserWriteBO userWriteBO) {
        return null;
    }
}
