package util;

import com.google.gson.Gson;

/**
 * Created by letingoo on 2016/12/16.
 */
public class GsonUtil {

    private static Gson gson = new Gson();


    public static Gson useGson() {
        return gson;
    }

}
