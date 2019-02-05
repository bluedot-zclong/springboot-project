package cn.edu.jxau;

import cn.edu.jxau.dao.IUserDAO;
import cn.edu.jxau.dao.dataobject.UserDO;
import cn.edu.jxau.dao.dataobject.UserQuery;
import cn.edu.jxau.service.IUserService;
import cn.edu.jxau.service.bo.UserBO;
import cn.edu.jxau.service.bo.UserQueryBO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JxauSoftwareCompetitionBootstrapApplication.class)
public class JxauSoftwareCompetitionBootstrapApplicationTests {

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserDAO userDAO;

    @Test
    public void mySqlTest() {
        String sql = "select * from user";
        List<UserDO> userList = jdbcTemplate.query(sql, new RowMapper<UserDO>() {
            @Override
            public UserDO mapRow(ResultSet resultSet, int i) throws SQLException {
                UserDO user = new UserDO();
                //user.setId(resultSet.getString("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        });
        System.out.println("查询成功");
        for (UserDO user : userList
        ) {
            System.out.println("name=" + user.getName());
        }
    }

    @Test
    public void testMybatis() {
//        UserBO user = userService.findByNameAndPassword("zcl", "123");
        List<UserDO> list = userDAO.queryPage(UserQuery.builder().name("zcl").password("123456").build());
        System.out.println(list.get(0).getName() + ":" + list.get(0).getPassword());
    }

    @Test
    public void testBatchInsert() {
//        UserBO user = userService.findByNameAndPassword("zcl", "123");

        List<UserDO> list = Lists.newArrayList();
        list.add(UserDO.builder().name("test").password("123").build());
        list.add(UserDO.builder().name("test1").password("123").build());
        userDAO.insertBatch(list);
    }

    @Test
    public void testQueryPage() {
//        UserBO user = userService.findByNameAndPassword("zcl", "123");

        UserQueryBO queryBO = UserQueryBO.builder().name("test").password("123").build();
        List<UserBO> list = userService.queryUserListByPage(queryBO);
        System.out.println(list.get(0).getName() + ":" + list.get(0).getPassword());
    }
}

