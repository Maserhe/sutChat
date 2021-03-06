package top.maserhe.netty;

import top.maserhe.utils.LogUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

import static io.netty.handler.timeout.IdleState.*;

/**
 * @author Maserhe
 * @description 用于实现Netty的心跳检测
 * @date 2021/1/17 21:09
 */
public class HeartbeatHandler extends ChannelInboundHandlerAdapter {

    /**
     * 服务端如果在指定时间内没有收到读写事件，将会造成读写空闲，那么将断开该客户端通道
     * 客户端向服务端发送心跳，对于服务端来说这是读事件。
     * 所以客户端发送心跳是保证了服务端的读不处于空闲状态
     * @param ctx ChannelHandlerContext
     * @param evt Object
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 如果evt是空闲状态监听事件
        if (evt instanceof IdleStateEvent) {
            // 强转为IdleStateEvent
            IdleStateEvent event = (IdleStateEvent)evt;
            // 读写空闲触发，关闭客户端的通道
            if (event.state() == ALL_IDLE) {
                LogUtil.info("进入读写空闲，将断开客户端: [{}]", ctx.channel().id().asShortText());
                // 关闭不用的Channel，避免资源浪费
                ctx.channel().close();
            }
        }
    }
}