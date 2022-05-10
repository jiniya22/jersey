package xyz.applebox.jersey.endpoint.v1;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Component
@Path("")
public class IndexEndpoint {

    @GET
    public String index() {
        return "hello jersey project!";
    }
}
