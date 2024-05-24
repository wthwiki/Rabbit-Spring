package cn.rabbit.springframework.beans;

public class BeansException extends RuntimeException{

    String message;

    private Throwable cause;

    public BeansException(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }

    public BeansException(String message, String message1, Throwable cause) {
        super(message);
        this.message = message1;
        this.cause = cause;
    }

    public BeansException(String message, Throwable cause, String message1, Throwable cause1) {
        super(message, cause);
        this.message = message1;
        this.cause = cause1;
    }

    public BeansException(Throwable cause, String message, Throwable cause1) {
        super(cause);
        this.message = message;
        this.cause = cause1;
    }

    public BeansException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1, Throwable cause1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
        this.cause = cause1;
    }

    public BeansException(String message) {
        this.message = message;
    }
}
