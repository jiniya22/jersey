package xyz.applebox.jersey.domain.value;

import lombok.Getter;

@Getter
public class BaseResponseBody {
    private final String result;
    private final String reason;

    private BaseResponseBody(){
        this.result = "SUCCESS";
        this.reason  = "";
    }
    private BaseResponseBody(String result){
        this.reason = "FAIL";
        this.result = result;
    }

    public static BaseResponseBody of() {
        return new BaseResponseBody();
    }
    public static BaseResponseBody of(String result) {
        return new BaseResponseBody(result);
    }
}
