package com.why.srpc.tcp;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class ThriftFrameDecoder extends ByteToMessageDecoder {

	private int LENGTH_FIELD_LENGTH = 4;

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

		if (in.readableBytes() < LENGTH_FIELD_LENGTH) {
			return;
		}

		in.markReaderIndex();

		int frameLength = in.readInt();

		if (in.readableBytes() < frameLength) {
			in.resetReaderIndex();
			return;
		}

		ByteBuf frame = in.readBytes(frameLength);

		out.add(frame);
	}
}