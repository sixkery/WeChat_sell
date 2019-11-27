package com.sixkery.utils;

import com.sixkery.enums.CodeEnum;

/**
 * @author sixkery
 * @date 2019/11/21
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
