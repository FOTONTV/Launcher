package fun.fotontv.launcher.request.websockets;

import fun.fotontv.utils.TypeSerializeInterface;

public interface WebSocketRequest extends TypeSerializeInterface {
    String getType();
}
