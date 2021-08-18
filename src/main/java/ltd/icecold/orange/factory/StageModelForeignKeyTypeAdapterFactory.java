package ltd.icecold.orange.factory;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import ltd.icecold.orange.bean.StageBean;

import java.io.IOException;

public class StageModelForeignKeyTypeAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (!StageBean.class.isAssignableFrom(type.getRawType())) {
            return null;
        }

        TypeAdapter defaultAdapter = gson.getAdapter(type);

        return (TypeAdapter<T>) new Adapter(defaultAdapter);
    }

    private static class Adapter<T extends StageBean> extends TypeAdapter<T> {

        private final TypeAdapter<T> defaultAdapter;

        Adapter(TypeAdapter<T> defaultAdapter) {
            this.defaultAdapter = defaultAdapter;
        }

        @Override
        public void write(JsonWriter out, T value) throws IOException {
            out.value(value.getVideo());
        }

        @Override
        public T read(JsonReader in) throws IOException {
            return defaultAdapter.read(in);
        }
    }
}
