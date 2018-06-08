package app




import org.pac4j.sparkjava.CallbackRoute
import spark.Spark.*


fun main(args: Array<String>) {
    val JWT_SALT = "12345678901234567890123456789012"
    staticFiles.header("server", "Jetty")
    staticFiles.location("/html/dist/html")
    val config = TestConfigFactory()
    config.init(JWT_SALT)
    config.build()


    //callback.setRenewSession(false);
    //before("/test",SecurityFilter())

    get("/hello") { req, res -> "Hello World" }




}
