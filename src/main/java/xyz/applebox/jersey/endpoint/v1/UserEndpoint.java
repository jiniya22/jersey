package xyz.applebox.jersey.endpoint.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xyz.applebox.jersey.domain.value.DataResponse;
import xyz.applebox.jersey.domain.value.UserValue;
import xyz.applebox.jersey.service.UserService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
@Component
@Path("/users")
public final class UserEndpoint {

    private final UserService userService;
    @Context
    private UriInfo uriInfo;

    @GET
    public DataResponse<List<UserValue.Response.Simple>> getAll() {
        return DataResponse.of(userService.findAll());
    }

    @POST
    public Response save(@Valid UserValue.Request.Creation data) {
        Long userId = userService.save(data);
        return Response.created(UriBuilder.fromUri(uriInfo.getAbsolutePath()).path("{userId}").build(userId)).build();
    }

    @GET
    @Path("/{userId}")
    public DataResponse<UserValue.Response.Detail> getOne(@PathParam("userId") long userId) {
        System.out.println(uriInfo.getAbsolutePath().getPath());
        UriBuilder.fromUri(uriInfo.getAbsolutePath());
        return DataResponse.of(userService.findById(userId));
    }

    @PATCH
    @Path("/{userId}")
    public Response patch(@PathParam("userId") long userId, @Valid UserValue.Request.Patch data) {
        userService.patch(userId, data);
        return Response.noContent().build();
    }

}
