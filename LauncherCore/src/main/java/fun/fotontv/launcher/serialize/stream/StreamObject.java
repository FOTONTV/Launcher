package fun.fotontv.launcher.serialize.stream;

import fun.fotontv.launcher.serialize.HInput;
import fun.fotontv.launcher.serialize.HOutput;
import fun.fotontv.utils.helper.IOHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public abstract class StreamObject {
    public final byte[] write() throws IOException {
        try (ByteArrayOutputStream array = IOHelper.newByteArrayOutput()) {
            try (HOutput output = new HOutput(array)) {
                write(output);
            }
            return array.toByteArray();
        }
    }

    public abstract void write(HOutput output) throws IOException;


    @FunctionalInterface
    public interface Adapter<O extends StreamObject> {

        O convert(HInput input);
    }
}
