package com.chiendv.examplespringoracle.exception;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TpbException extends RuntimeException {
    private Object tagObject;
    public TpbException() {
        super();
    }
    public TpbException(String errorCode) {
        super(errorCode);
    }
    public TpbException(String errorCode, String errroDesc,  Object... args) {
        super(errorCode);
    }
    public TpbException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }
    public TpbException(String errorCode, Object tag) {
        super(errorCode);
        this.setTagObject(tag);
    }
}