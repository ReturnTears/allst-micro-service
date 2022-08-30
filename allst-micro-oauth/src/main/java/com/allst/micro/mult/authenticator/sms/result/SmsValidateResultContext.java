package com.allst.micro.mult.authenticator.sms.result;

/**
 * @author Hutu
 * @since 2022-08-30 下午 09:59
 */
public class SmsValidateResultContext {

    private static final ThreadLocal<SmsCodeValidateResult> holder = new ThreadLocal<>();

    public static void set(SmsCodeValidateResult result) {
        holder.set(result);
    }

    public static SmsCodeValidateResult get() {
        return holder.get();
    }

    public static void clear() {
        holder.remove();
    }
}
