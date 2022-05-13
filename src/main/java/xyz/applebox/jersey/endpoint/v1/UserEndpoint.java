package xyz.applebox.jersey.endpoint.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xyz.applebox.jersey.domain.value.DataResponse;
import xyz.applebox.jersey.domain.value.UserValue;
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
public final class UserEndpoint {

    private final UserService userService;

    @GET
    public DataResponse<List<UserValue.UserSimpleData>> getAll() {
        return DataResponse.of(userService.findAll());
    }

    @GET
    @Path("/{userId}")
    public DataResponse<UserValue.UserDetailData> getOne(@PathParam("userId") Long userId) {
        return DataResponse.of(userService.findById(userId));
    }

}
