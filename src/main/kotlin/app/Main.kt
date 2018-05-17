package app


import spark.kotlin.*

fun main(args: Array<String>) {
    staticFiles.header("server", "Jetty")
    staticFiles.location("/html/dist/html")

    before("/*") {
        response.header("server", "Jetty")
    }

    get("/hello") {
        type(contentType = "application/json")
        "{\"message\":\"Hello World\"}"
    }
}
