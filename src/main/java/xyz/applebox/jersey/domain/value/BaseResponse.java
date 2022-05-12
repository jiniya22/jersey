package xyz.applebox.jersey.domain.value;

import lombok.Getter;

@Getter
public class BaseResponse {
    private final String result;
    private final String reason;

    protected BaseResponse(){
        this.result = "SUCCESS";
        this.reason  = "";
    }

    private BaseResponse(String result){
        this.reason = "FAIL";
        this.result = result;
    }

    public static BaseResponse of() {
        return new BaseResponse();
    }
    public static BaseResponse of(String result) {
        return new BaseResponse(result);
    }
}
