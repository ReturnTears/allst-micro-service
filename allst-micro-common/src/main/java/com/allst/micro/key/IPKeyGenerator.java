package com.allst.micro.key;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Hutu
 * @since 2022-10-16 下午 04:18
 */
public class IPKeyGenerator implements KeyGenerator {

    private static volatile IPKeyGenerator ipKeyGenerator;

    private IPKeyGenerator() {
    }


    private final DefaultKeyGenerator defaultKeyGenerator = new DefaultKeyGenerator();

    static {
        initWorkerId();
    }

    static void initWorkerId() {
        InetAddress address;
        try {
            address = InetAddress.getLocalHost();
        } catch (final UnknownHostException e) {
            throw new IllegalStateException("Cannot get LocalHost InetAddress, please check your network!", e);
        }
        byte[] ipAddressByteArray = address.getAddress();
        DefaultKeyGenerator.setWorkerId((long) (((ipAddressByteArray[ipAddressByteArray.length - 2] & 0B11) << Byte.SIZE) + (ipAddressByteArray[ipAddressByteArray.length - 1] & 0xFF)));
    }

    @Override
    public Number generateKey() {
        return defaultKeyGenerator.generateKey();
    }

    public static IPKeyGenerator getInstance() {
        if (ipKeyGenerator == null) {
            synchronized (IPKeyGenerator.class) {
                if (ipKeyGenerator == null) {
                    ipKeyGenerator = new IPKeyGenerator();
                }
            }
        }
        return ipKeyGenerator;
    }
}
