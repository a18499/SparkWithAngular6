package app





import org.pac4j.sparkjava.SecurityFilter
import spark.Spark.*


fun main(args: Array<String>) {
    val JWT_SALT = "12345678901234567890123456789012"
    staticFiles.header("server", "Jetty")
    staticFiles.location("/html/dist/html")
    val config = TestConfigFactory(salt = JWT_SALT).build()

    //callback.setRenewSession(false);
    //before("/test",SecurityFilter())
    before("/test",SecurityFilter(config,"ParameterClient"))
    get("/hello") { req, res -> "Hello World" }




}
