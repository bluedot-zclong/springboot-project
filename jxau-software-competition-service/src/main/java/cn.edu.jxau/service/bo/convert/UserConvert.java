package cn.edu.jxau.service.bo.convert;

import cn.edu.jxau.dao.dataobject.UserDO;
import cn.edu.jxau.dao.dataobject.UserQuery;
import cn.edu.jxau.service.bo.UserBO;
import cn.edu.jxau.service.bo.UserQueryBO;
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
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    /**
     * 将UserDO转换为UserBO
     *
     * @param dto
     * @return
     */
    UserBO doToBO(UserDO dto);

    /**
     * 将UserBO转换为UserDO
     *
     * @param bto
     * @return
     */
    UserDO doToDO(UserBO bto);

    /**
     * 将List<UserBO>转换为List<UserDO>
     *
     * @param dto
     * @return
     */
    List<UserDO> doListToDOList(List<UserBO> dto);

    /**
     * 将List<UserDO>转换为List<UserBO>
     *
     * @param dto
     * @return
     */
    List<UserBO> doListToBOList(List<UserDO> dto);

    /**
     * 将UserQueryBO转换为UserQuery
     *
     * @param queryBO
     * @return
     */
    UserQuery toQuery(UserQueryBO queryBO);

}
