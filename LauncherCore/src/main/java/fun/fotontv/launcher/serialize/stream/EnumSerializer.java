package fun.fotontv.launcher.serialize.stream;

import fun.fotontv.launcher.serialize.HInput;
import fun.fotontv.launcher.serialize.HOutput;
import fun.fotontv.utils.helper.VerifyHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class EnumSerializer<E extends Enum<?> & EnumSerializer.Itf> {
    private final Map<Integer, E> map = new HashMap<>(16);


    public EnumSerializer(Class<E> clazz) {
        for (E e : clazz.getEnumConstants())
            VerifyHelper.putIfAbsent(map, e.getNumber(), e, "Duplicate number for enum constant " + e.name());
    }

    public static void write(HOutput output, Itf itf) throws IOException {
        output.writeVarInt(itf.getNumber());
    }

    public E read(HInput input) throws IOException {
        int n = input.readVarInt();
        return VerifyHelper.getMapValue(map, n, "Unknown enum number: " + n);
    }


    @FunctionalInterface
    public interface Itf {

        int getNumber();
    }
}
