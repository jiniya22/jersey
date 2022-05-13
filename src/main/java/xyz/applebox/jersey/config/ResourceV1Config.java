package xyz.applebox.jersey.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/v1")
public class ResourceV1Config extends ResourceConfig {

    public ResourceV1Config() {
        packages("xyz.applebox.jersey.endpoint.v1", "xyz.applebox.jersey.config.provider");
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
    }

}
