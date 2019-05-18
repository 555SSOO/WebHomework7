package vu.che.mvcrest;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/coupon")
public class RestApp extends ResourceConfig {

    public RestApp() {
         packages("vu.che.mvcrest.coupon");
    }
}
