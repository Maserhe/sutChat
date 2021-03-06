package top.maserhe.mapper;

import top.maserhe.entity.vo.FriendRequestVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Maserhe
 * @description
 * @date 2021/1/12 17:24
 */
@Mapper
public interface CustomMsgMapper {

    /**
     * 对消息进行批量签收
     * @param ids 消息ID集合
     */
    void batchSignMsg(List<String> ids);

    /**
     * 根据账号id查找发送添加请求的好友列表
     * @param userId 我的账号id
     * @return 发送添加请求的好友列表
     */
    List<FriendRequestVO> getRequestByUserId(String userId);
}
