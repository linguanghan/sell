package com.wechat.sell.utils;

import com.wechat.sell.enums.CodeEnum;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-16 10:38
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for(T each:enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }

        return null;
    }
}
