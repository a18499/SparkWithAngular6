package app





import org.json.JSONObject
import org.pac4j.sparkjava.SecurityFilter
import spark.Request
import spark.Response
import spark.Spark.*


class DAVIDFront{

    fun protectIndex(request:Request,response: Response):JSONObject{

        var resault = JSONObject()
        resault = resault.put("result","test")

        return resault
    }
}

fun main(args: Array<String>) {
    val JWT_SALT = "12345678901234567890123456789012"
    staticFiles.header("server", "Jetty")
    staticFiles.location("/html/dist/html")
    val config = TestConfigFactory(salt = JWT_SALT).build()
    val davidApp = DAVIDFront()
    //callback.setRenewSession(false);
    //before("/test",SecurityFilter())
    before("/protect",SecurityFilter(config,"ParameterClient"))
    get("/hello") { req, res -> "Hello World" }
    get("/protect"){request: Request, response: Response -> davidApp.protectIndex(request, response)  }


}
