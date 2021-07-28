package com.wechat.sell.utils;

import com.wechat.sell.VO.ResultVO;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-09 12:00
 */
public class ResultVOUtil {
    public static ResultVO success(Object obj){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(obj);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
    public static ResultVO success(){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO success(Integer code, String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }

    public static ResultVO error(Integer code, String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
