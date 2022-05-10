package xyz.applebox.jersey.domain.exception;

import xyz.applebox.jersey.domain.value.BaseResponseBody;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serial;

public class InvalidRequestException extends WebApplicationException {

    @Serial
    private static final long serialVersionUID = -7267339423783060714L;

    public InvalidRequestException() {
        super(Response.status(Response.Status.BAD_REQUEST)
                .entity(BaseResponseBody.of("입력을 잘못했습니다."))
                .type(MediaType.APPLICATION_JSON_TYPE).build());
    }

    public InvalidRequestException(String msg) {
        super(Response.status(Response.Status.BAD_REQUEST).entity(BaseResponseBody.of(msg)).type(MediaType.APPLICATION_JSON_TYPE).build());
    }

}