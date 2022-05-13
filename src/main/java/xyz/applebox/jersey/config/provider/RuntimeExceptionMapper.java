package xyz.applebox.jersey.config.provider;

import xyz.applebox.jersey.domain.value.BaseResponse;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException exception) {
        int status = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        Object entity = BaseResponse.of(exception.getMessage());

        if(exception instanceof WebApplicationException e) {
            status = e.getResponse().getStatus();
        }

        return Response.status(status)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(entity).build();
    }
}
