package app


import java.util.HashMap;
import org.pac4j.core.context.HttpConstants;
import org.pac4j.sparkjava.DefaultHttpActionAdapter;
import org.pac4j.sparkjava.SparkWebContext;
import spark.ModelAndView;
import spark.TemplateEngine;
class DavidHttpActionAdapter :DefaultHttpActionAdapter(){




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