package app


import spark.kotlin.*

fun main(args: Array<String>) {
    staticFiles.location("/html/dist/html")

    get("/hello") {
        type(contentType = "application/json")
        "{\"message\":\"Hello World\"}"
    }
}