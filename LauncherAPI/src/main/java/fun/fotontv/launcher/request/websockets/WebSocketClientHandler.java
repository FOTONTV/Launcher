package fun.fotontv.launcher.request.websockets;

import fun.fotontv.utils.helper.LogHelper;
import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

public class WebSocketClientHandler extends SimpleChannelInboundHandler<Object> {
    private final WebSocketClientHandshaker handshaker;
    private final ClientJSONPoint clientJSONPoint;
    private ChannelPromise handshakeFuture;

    public WebSocketClientHandler(final WebSocketClientHandshaker handshaker, ClientJSONPoint clientJSONPoint) {
        this.handshaker = handshaker;
        this.clientJSONPoint = clientJSONPoint;
    }

    public ChannelFuture handshakeFuture() {
        return handshakeFuture;
    }

    @Override
    public void handlerAdded(final ChannelHandlerContext ctx) {
        handshakeFuture = ctx.newPromise();
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        handshaker.handshake(ctx.channel());
        clientJSONPoint.onOpen();
        ctx.executor().scheduleWithFixedDelay(() -> ctx.channel().writeAndFlush(new PingWebSocketFrame()), 20L, 20L, TimeUnit.SECONDS);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void channelInactive(final ChannelHandlerContext ctx) throws Exception {
        clientJSONPoint.onDisconnect();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        final Channel ch = ctx.channel();
        if (!handshaker.isHandshakeComplete()) {
            // web socket client connected
            handshaker.finishHandshake(ch, (FullHttpResponse) msg);
            handshakeFuture.setSuccess();
            return;
        }

        if (msg instanceof final FullHttpResponse response) {
            throw new Exception("Unexpected FullHttpResponse (getStatus=" + response.status() + ", content="
                    + response.content().toString(CharsetUtil.UTF_8) + ')');
        }

        final WebSocketFrame frame = (WebSocketFrame) msg;
        if (frame instanceof final TextWebSocketFrame textFrame) {
            clientJSONPoint.onMessage(textFrame.text());
            if (LogHelper.isDevEnabled()) {
                LogHelper.dev("Message: %s", textFrame.text());
            }
        } else if ((frame instanceof PingWebSocketFrame)) {
            frame.content().retain();
            ch.writeAndFlush(new PongWebSocketFrame(frame.content()), ch.voidPromise());
        } else if (frame instanceof CloseWebSocketFrame)
            ch.close();


    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) {
        LogHelper.error(cause);

        if (!handshakeFuture.isDone()) {
            handshakeFuture.setFailure(cause);
        }

        ctx.close();
    }
}
