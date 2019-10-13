package com.iroshnk.undertow.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/demo")
public class DemoResource {
    @GET
    public Response sayHello(@QueryParam("name") String name) {
        return Response.ok("Hello " + name).build();
    }
}
