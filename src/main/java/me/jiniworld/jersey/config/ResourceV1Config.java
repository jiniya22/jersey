package me.jiniworld.jersey.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import me.jiniworld.jersey.resource.TestResource;

@Component
@ApplicationPath("/api/v1")
public class ResourceV1Config extends ResourceConfig {

	public ResourceV1Config() {
		register(TestResource.class);
	}

}
