package com.revature.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.controller.DonutsController;
import com.revature.controller.CustomerController;
import io.javalin.Javalin;
import io.javalin.json.JsonMapper;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

import static io.javalin.apibuilder.ApiBuilder.*;

import static io.javalin.apibuilder.ApiBuilder.path;

public class JavalinAppConfig {

    Gson gson = new GsonBuilder().create();

    JsonMapper gsonMapper = new JsonMapper() {
        @Override
        public String toJsonString(@NotNull Object obj, @NotNull Type type) {
            return gson.toJson(obj, type);
        }

        @Override
        public <T> T fromJsonString(@NotNull String json, @NotNull Type targetType) {
            return gson.fromJson(json, targetType);
        }
    };


    private Javalin app = Javalin.create(config -> config.jsonMapper(gsonMapper))

            .routes(()->{
                path("customers", ()->{
                    get(CustomerController::handleGetAllOrders);
//                    path("{number}",()->{
//                        get(CustomerController::handleGetByNumber);
//                    });
                });
            });


    public void start (int port){
        app.start(port);
    }
}
