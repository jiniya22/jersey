package xyz.applebox.jersey.endpoint.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xyz.applebox.jersey.domain.value.DataResponse;
import xyz.applebox.jersey.domain.value.UserValue;
import xyz.applebox.jersey.service.UserService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
@Component
@Path("/users")
public final class UserEndpoint {

    private final UserService userService;

    @GET
    public DataResponse<List<UserValue.Response.UserSimpleData>> getAll() {
        return DataResponse.of(userService.findAll());
    }

    @POST
    public Response save(@Valid UserValue.Request.UserNB data) {
        Long id = userService.save(data);
        return Response.created(URI.create("/v1/users/" + id)).build();
    }

    @GET
    @Path("/{userId}")
    public DataResponse<UserValue.Response.UserData> getOne(@PathParam("userId") Long userId) {
        return DataResponse.of(userService.findById(userId));
    }

}
