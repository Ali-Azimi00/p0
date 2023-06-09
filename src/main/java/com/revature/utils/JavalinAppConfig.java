package com.revature.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.controller.DonutsController;
import com.revature.controller.CustomerController;
import com.revature.models.Customer;
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

//    private static final Logger logger = LoggerFactory.getLogger(JavalinAppConfig.class);


    private Javalin app = Javalin.create(config -> config.jsonMapper(gsonMapper))

//            .before(ctx->{
//                //will run before all requests to the server
//                //use to log requests sent
//                logger.info(ctx.method()+" Request sent pat " + ctx.fullUrl());
//            })

            .routes(()->{
                path("customers", ()->{
                    get(CustomerController::handleGetAllOrders);
                    post(CustomerController::handleCreateOrder);
                    put(CustomerController::handleUpdateCustomer);


                    path("{id}",()->{
                        get(CustomerController::handleGetByNumber);
                        delete(CustomerController::handleDeleteOrder);
//                        path("{first_name}", ()->{
//                            put(CustomerController::handleUpdateCustomer);
//                        });

                    });
                });
            });


    public void start (int port){
        app.start(port);
    }
}
