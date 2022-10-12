package com.allst.micro.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 签名工具类
 *
 * @author Hutu
 * @since 2022-10-12 下午 08:03
 */
@Slf4j
public class SignUtils {
    public static String signSource(Map<String, String> params, String... excludes) {
        for (String exclude : excludes) {
            params.remove(exclude);
        }
        return params.keySet().stream().sorted().filter(k -> StringUtils.isNotEmpty(params.get(k))).map(k -> k + "=" + params.get(k)).collect(Collectors.joining("&"));
    }

    public static void addRsa256Sign(Signature priKeySign, Map<String, String> params) {
        String sign = rsa256Sign(priKeySign, params);
        if (StringUtils.isNotEmpty(sign)) {
            params.put("sign", sign);
        }
    }

    public static String rsa256Sign(Signature priKeySign, Map<String, String> params) {
        String signSource = signSource(params, "sign");
        try {
            priKeySign.update(signSource.getBytes(StandardCharsets.UTF_8));
            byte[] signed = priKeySign.sign();

            return new String(Base64.getEncoder().encode(signed));
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean verifyRsa256Sign(Signature pubKeySign, Map<String, String> params) {

        String sign = params.get("sign");
        String signSource = signSource(params, "sign", "sign_type");
        if (StringUtils.isEmpty(sign)) {
            return false;
        }
        try {
            pubKeySign.update(signSource.getBytes());
            return pubKeySign.verify(Base64.getDecoder().decode(sign.getBytes()));
        } catch (SignatureException e) {
            e.printStackTrace();
        }
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return verifyRsa256Sign(pubKeySign, sign, signSource);
        return false;
    }

    public static boolean verifyRsa256Sign(Signature pubKeySign, String sign, String source) {
        if (StringUtils.isEmpty(sign)) {
            return false;
        }
        try {
            pubKeySign.update(source.getBytes(StandardCharsets.UTF_8));
            return pubKeySign.verify(Base64.getDecoder().decode(sign.getBytes()));
        } catch (SignatureException e) {
            e.printStackTrace();
        }

        return false;
    }


    public static void addMd5Sign(Map<String, String> params, String key) {
        String sign = md5Sign(params, key);
        params.put("sign", sign);
    }

    public static String md5Sign(Map<String, String> params, String key) {
        String signSource = signSource(params, "sign");
        signSource += "&key=" + key;
        return DigestUtils.md5Hex(signSource).toUpperCase();
    }

    public static boolean verfifyMd5Sign(Map<String, String> params, String key) {
        String sign = params.get("sign");
        if (StringUtils.isEmpty(sign)) {
            return false;
        }
        String newSign = md5Sign(params, key);

        return newSign.equals(sign);
    }
}
