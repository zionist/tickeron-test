package com.tickeron.test.common.exceptions;

import java.util.Map;

/**
 * Created by slaviann on 22.10.15.
 */
public class Formatter {
    public static String formatParamsString(Map<String, String> params) {
        String out = "\n===== test params are =====\n";
        for (String key : params.keySet()) {
            out += String.format("key %s: value %s\n", key, params.get(key));
        }
        out += "======\n";
        return out;
    }
}
