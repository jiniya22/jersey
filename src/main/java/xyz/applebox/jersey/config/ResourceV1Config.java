package xyz.applebox.jersey.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import xyz.applebox.jersey.endpoint.v1.IndexEndpoint;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/v1")
public class ResourceV1Config extends ResourceConfig {

    public ResourceV1Config() {
        packages(IndexEndpoint.class.getPackage().getName());
    }
}
