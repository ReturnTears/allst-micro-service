package com.allst.micro.mult;

/**
 * @author Hutu
 * @since 2022-08-30 下午 09:31
 */
public class MultAuthenticationContext {

    private static final ThreadLocal<MultAuthentication> holder = new ThreadLocal<>();

    public static void set(MultAuthentication multAuthentication) {
        holder.set(multAuthentication);
    }

    public static MultAuthentication get() {
        return holder.get();
    }

    public static void clear() {
        holder.remove();
    }
}
