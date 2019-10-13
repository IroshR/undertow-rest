package com.iroshnk.undertow.config;

/*import com.iroshnk.undertow.resource.DemoResource;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;*/
import org.glassfish.jersey.server.ResourceConfig;

public class RestResourceConfig extends ResourceConfig {
    public RestResourceConfig() {
        /*register(DemoResource.class);
        register(JacksonJaxbJsonProvider.class);*/
        packages(true, "com.iroshnk.undertow.resource");
    }

}
