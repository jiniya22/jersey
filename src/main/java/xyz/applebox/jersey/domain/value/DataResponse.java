package xyz.applebox.jersey.domain.value;

import lombok.Getter;

@Getter
public final class DataResponse<T> extends BaseResponse {
    private final T data;

    private DataResponse(T data) {
        super();
        this.data = data;
    }

    public static <T> DataResponse<T> of(T data) {
        return new DataResponse<T>(data);
    }
}
