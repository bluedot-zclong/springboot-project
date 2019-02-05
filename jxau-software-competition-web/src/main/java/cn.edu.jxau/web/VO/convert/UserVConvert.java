package cn.edu.jxau.web.VO.convert;

import cn.edu.jxau.dao.dataobject.UserDO;
import cn.edu.jxau.dao.dataobject.UserQuery;
import cn.edu.jxau.service.bo.UserBO;
import cn.edu.jxau.service.bo.UserQueryBO;
import cn.edu.jxau.web.VO.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户信息转换器
 *
 * @author：zclong
 * @date：2019/1/31
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserVConvert {

    UserVConvert INSTANCE = Mappers.getMapper(UserVConvert.class);

    /**
     * 将UserBO转换为UserVO
     *
     * @param dto
     * @return
     */
    UserVO doToVO(UserBO dto);

    /**
     * 将UserVO转换为UserBO
     *
     * @param bto
     * @return
     */
    UserBO doToBO(UserVO bto);

    /**
     * 将List<UserVO>转换为List<UserBO>
     *
     * @param dto
     * @return
     */
    List<UserBO> doListToBOList(List<UserVO> dto);

    /**
     * 将List<UserBO>转换为List<UserVO>
     *
     * @param dto
     * @return
     */
    List<UserVO> doListToVOList(List<UserBO> dto);

    /**
     * 将UserVO转换为UserQueryBO
     *
     * @param userVO
     * @return
     */
    UserQueryBO toQuery(UserVO userVO);

}
