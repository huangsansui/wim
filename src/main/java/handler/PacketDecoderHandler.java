package handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import protocol.PacketCode;

import java.util.List;

/**
 * Created on 2019/6/27
 *
 * @author qing.huang
 */
public class PacketDecoderHandler extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(PacketCode.INSTANCE.decode(in));
    }
}
