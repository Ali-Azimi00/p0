package com.revature;

import com.revature.utils.JavalinAppConfig;
import io.javalin.Javalin;

public class Driver {

    public static void main(String[] args) {
        JavalinAppConfig app = new JavalinAppConfig();

        app.start(8080);
    }
}
