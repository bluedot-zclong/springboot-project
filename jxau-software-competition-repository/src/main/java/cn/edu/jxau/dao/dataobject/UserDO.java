package cn.edu.jxau.dao.dataobject;

import lombok.*;

import java.util.Date;

/**
 * @Entity : 每个持久化pojo类都是一个实体bean，通过在类的定义中使用@Entity注解来声明
 * @Table ：声明此对象映射到数据库的数据表，该注释不是必须的，如果没有，系统会使用默认值（实体的短类名）
 * @Id : 指定表的主键
 * @AllArgsConstructor：在Lombok中，用来生成构造方法，会生成一个包含所有变量的构造方法，同时如果变量使用了NotNull annotation ， 会进行是否为空的校验
 */
@AllArgsConstructor
@ToString
@Data
@NoArgsConstructor
@Builder
public class UserDO extends BaseDO {

    private static final long serialVersionUID = 4642856858530628844L;

    private String name;

    private String password;

    private Date effectiveFrom;

    private Date effectiveTo;
}
