package handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import protocol.Packet;
import protocol.PacketCode;

/**
 * Created on 2019/6/27
 *
 * @author qing.huang
 */
public class PacketEncoderHandler extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCode.INSTANCE.encode(out, msg);
    }
}
