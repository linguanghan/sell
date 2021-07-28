package com.wechat.sell.exception;

import com.wechat.sell.enums.ResultEnum;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-10 9:52
 */
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message){
        super(message);
        this.code = code;
    }
}
