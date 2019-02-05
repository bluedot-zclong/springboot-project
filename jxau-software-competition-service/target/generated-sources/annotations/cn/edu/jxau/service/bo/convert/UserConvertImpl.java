package cn.edu.jxau.service.bo.convert;

import cn.edu.jxau.dao.dataobject.UserDO;
import cn.edu.jxau.dao.dataobject.UserDO.UserDOBuilder;
import cn.edu.jxau.dao.dataobject.UserQuery;
import cn.edu.jxau.dao.dataobject.UserQuery.UserQueryBuilder;
import cn.edu.jxau.service.bo.UserBO;
import cn.edu.jxau.service.bo.UserQueryBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-02-05T18:42:12+0800",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 1.8.0_91 (Oracle Corporation)"
)
public class UserConvertImpl implements UserConvert {

    @Override
    public UserBO doToBO(UserDO dto) {
        if ( dto == null ) {
            return null;
        }

        UserBO userBO = new UserBO();

        if ( dto.getId() != null ) {
            userBO.setId( String.valueOf( dto.getId() ) );
        }
        userBO.setName( dto.getName() );
        userBO.setPassword( dto.getPassword() );

        return userBO;
    }

    @Override
    public UserDO doToDO(UserBO bto) {
        if ( bto == null ) {
            return null;
        }

        UserDOBuilder userDO = UserDO.builder();

        userDO.name( bto.getName() );
        userDO.password( bto.getPassword() );

        return userDO.build();
    }

    @Override
    public List<UserDO> doListToDOList(List<UserBO> dto) {
        if ( dto == null ) {
            return null;
        }

        List<UserDO> list = new ArrayList<UserDO>( dto.size() );
        for ( UserBO userBO : dto ) {
            list.add( doToDO( userBO ) );
        }

        return list;
    }

    @Override
    public List<UserBO> doListToBOList(List<UserDO> dto) {
        if ( dto == null ) {
            return null;
        }

        List<UserBO> list = new ArrayList<UserBO>( dto.size() );
        for ( UserDO userDO : dto ) {
            list.add( doToBO( userDO ) );
        }

        return list;
    }

    @Override
    public UserQuery toQuery(UserQueryBO queryBO) {
        if ( queryBO == null ) {
            return null;
        }

        UserQueryBuilder userQuery = UserQuery.builder();

        userQuery.name( queryBO.getName() );
        userQuery.password( queryBO.getPassword() );
        userQuery.effectiveFrom( queryBO.getEffectiveFrom() );
        userQuery.effectiveTo( queryBO.getEffectiveTo() );

        return userQuery.build();
    }
}
