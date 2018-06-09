package app


import org.json.JSONObject
import java.util.HashMap;
import org.pac4j.core.context.HttpConstants;
import org.pac4j.core.http.adapter.HttpActionAdapter

import org.pac4j.sparkjava.SparkWebContext;
import spark.ModelAndView;
import spark.Spark.stop
import spark.TemplateEngine;
class DavidHttpActionAdapter:HttpActionAdapter<Any,SparkWebContext> {
    override fun adapt(code: Int, context: SparkWebContext?): Any {
        val errorStruct = JSONObject()
        if(code == HttpConstants.UNAUTHORIZED){

            errorStruct.put("Error","401 Error")
            stop()
        }else if(code == HttpConstants.FORBIDDEN){
            errorStruct.put("Error","403 Error")
            stop()
        }else if(code == 500){
            errorStruct.put("Error","500 Error")
            stop()
        }
        return errorStruct

    }


/*
    override fun adapt(code: Int, context: SparkWebContext): Any {
        if(code == HttpConstants.UNAUTHORIZED){
            val errorStruct = JSONObject()
            errorStruct.put("Error","401 Error")
            stop(HttpConstants.UNAUTHORIZED, errorStruct.toString())
        }
        return super.adapt(code, context)
    }*/
/*

        @Override
        public Object adapt(int code, SparkWebContext context) {
            if (code == HttpConstants.UNAUTHORIZED) {
                stop(401, templateEngine.render(new ModelAndView(new HashMap<>(), "error401.mustache")));
            } else if (code == HttpConstants.FORBIDDEN) {
                stop(403, templateEngine.render(new ModelAndView(new HashMap<>(), "error403.mustache")));
            } else {
                return super.adapt(code, context);
            }
            return null;
        }
*/
}