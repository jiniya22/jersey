package xyz.applebox.jersey.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/v1")
public class ResourceV1Config extends ResourceConfig {

    public ResourceV1Config() {
        packages("xyz.applebox.jersey.endpoint.v1");
    }
}
