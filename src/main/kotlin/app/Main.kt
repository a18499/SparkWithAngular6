package app



import build
import org.eclipse.jetty.util.log.JettyLogHandler.config

import spark.Spark.staticFiles


fun main(args: Array<String>) {
    val JWT_SALT = "12345678901234567890123456789012"
    staticFiles.header("server", "Jetty")
    staticFiles.location("/html/dist/html")
    val config = TestConfigFactory()
    config.init(JWT_SALT)
    config.build()
    val callback = CallbackRoute(config, null, true)
    //callback.setRenewSession(false);
    get("/callback", callback)
    post("/callback", callback)


    before("/*") {
        response.header("server", "Jetty")
    }

    get("/hello") {
        type(contentType = "application/json")
        "{\"message\":\"Hello World\"}"
    }


}
