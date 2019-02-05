package cn.edu.jxau.web.VO.convert;

import cn.edu.jxau.service.bo.UserBO;
import cn.edu.jxau.service.bo.UserQueryBO;
import cn.edu.jxau.service.bo.UserQueryBO.UserQueryBOBuilder;
import cn.edu.jxau.web.VO.UserVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-02-05T18:42:15+0800",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 1.8.0_91 (Oracle Corporation)"
)
public class UserVConvertImpl implements UserVConvert {

    @Override
    public UserVO doToVO(UserBO dto) {
        if ( dto == null ) {
            return null;
        }

        UserVO userVO = new UserVO();

        userVO.setId( dto.getId() );
        userVO.setName( dto.getName() );
        userVO.setPassword( dto.getPassword() );

        return userVO;
    }

    @Override
    public UserBO doToBO(UserVO bto) {
        if ( bto == null ) {
            return null;
        }

        UserBO userBO = new UserBO();

        userBO.setId( bto.getId() );
        userBO.setName( bto.getName() );
        userBO.setPassword( bto.getPassword() );

        return userBO;
    }

    @Override
    public List<UserBO> doListToBOList(List<UserVO> dto) {
        if ( dto == null ) {
            return null;
        }

        List<UserBO> list = new ArrayList<UserBO>( dto.size() );
        for ( UserVO userVO : dto ) {
            list.add( doToBO( userVO ) );
        }

        return list;
    }

    @Override
    public List<UserVO> doListToVOList(List<UserBO> dto) {
        if ( dto == null ) {
            return null;
        }

        List<UserVO> list = new ArrayList<UserVO>( dto.size() );
        for ( UserBO userBO : dto ) {
            list.add( doToVO( userBO ) );
        }

        return list;
    }

    @Override
    public UserQueryBO toQuery(UserVO userVO) {
        if ( userVO == null ) {
            return null;
        }

        UserQueryBOBuilder userQueryBO = UserQueryBO.builder();

        userQueryBO.name( userVO.getName() );
        userQueryBO.password( userVO.getPassword() );

        return userQueryBO.build();
    }
}
