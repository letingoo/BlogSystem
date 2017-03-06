package util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by letingoo on 2016/12/16.
 */
public class GsonUtil {

    // 因为RabbitMQ的Json转换对日期和Gson的解析不匹配，额外处理
    private static Gson gson_forQueue = null;

    private static Gson gson = new Gson();

    static {


        // 让Gson能够解析timestamp格式的json字符串
        GsonBuilder builder = new GsonBuilder();

        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });

        gson_forQueue = builder.create();
    }


    public static Gson useGsonForQueue() {
        return gson_forQueue;
    }

    public static Gson useGson() { return gson; }

}
