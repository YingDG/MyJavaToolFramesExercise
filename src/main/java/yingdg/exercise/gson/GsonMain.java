package yingdg.exercise.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

public class GsonMain {
    public static void main(String[] args) {
        GsonModel model = new GsonModel(1, "gson", new Date());

        // basic
        Gson gson = new Gson();
        String json = gson.toJson(model);
        System.out.println(json);

        // builder
        Gson gson1 = new GsonBuilder()
                .setDateFormat("YYYY-MM-dd HH:mm:ss")
                .setPrettyPrinting()
                .create();
        String json1 = gson1.toJson(model);
        System.out.println(json1);
        GsonModel gsonModel = gson1.fromJson(json1, GsonModel.class);
        System.out.println(gsonModel);
    }
}
