package com.sixkery.utils;

import com.sixkery.VO.ResultVO;

public class ResultsVOUtil {
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("成功！");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success() {
        return null;
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(msg);
        return resultVO;
    }
}
