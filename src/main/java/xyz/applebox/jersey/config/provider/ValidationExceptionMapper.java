package xyz.applebox.jersey.config.provider;

import xyz.applebox.jersey.domain.value.BaseResponse;

import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException exception) {
        int status = Response.Status.BAD_REQUEST.getStatusCode();
        Object entity = BaseResponse.of(exception.getMessage());

        return Response.status(status)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(entity).build();
    }
}