package com.wechat.sell.VO;

import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 6151144499739343463L;
    /**
     * 错误码.
     */
    private Integer code;
    /**
     * 提示信息.
     */
    private String msg = "";

    private T data;

}
