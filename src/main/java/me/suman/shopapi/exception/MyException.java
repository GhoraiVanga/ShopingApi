package me.suman.shopapi.exception;

import me.suman.shopapi.enums.ResultEnum;

public class MyException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1363380730761043681L;
	private Integer code;

    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
