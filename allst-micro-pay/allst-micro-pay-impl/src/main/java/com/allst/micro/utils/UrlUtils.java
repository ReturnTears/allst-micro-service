package com.allst.micro.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Hutu
 * @since 2022-10-12 下午 08:06
 */
public class UrlUtils {
    public static String encode(Map<String, String> map) {
        return map.keySet().stream().map(k -> {
            try {
                String v = URLEncoder.encode(map.get(k), "UTF-8");
                return k + "=" + v;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.joining("&"));
    }
}
