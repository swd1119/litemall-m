package org.linlinjava.litemall.service.ex;

/**
 * 某用户的收货地址的数量达到上限
 */
public class AddressCountException extends ServiceException {

    private static final long serialVersionUID = 5415705518717712127L;

    public AddressCountException() {
        super();
    }

    public AddressCountException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AddressCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressCountException(String message) {
        super(message);
    }

    public AddressCountException(Throwable cause) {
        super(cause);
    }

}
