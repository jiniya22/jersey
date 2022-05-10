package xyz.applebox.jersey.endpoint.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xyz.applebox.jersey.domain.entity.User;
import xyz.applebox.jersey.service.UserService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
@Component
@Path("/users")
public class UserEndpoint {

    private final UserService userService;

    @GET
    public List<User> getAll() {
        return userService.findAll();
    }

    @GET
    @Path("/{userId}")
    public User getOne(@PathParam("userId") Long userId) {
        return userService.findById(userId);
    }

}
