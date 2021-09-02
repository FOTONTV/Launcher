package fun.fotontv.launcher.request;

import fun.fotontv.utils.TypeSerializeInterface;

/**
 * The interface of all events sent by the server to the client
 */
public interface WebSocketEvent extends TypeSerializeInterface {
    String getType();
}
