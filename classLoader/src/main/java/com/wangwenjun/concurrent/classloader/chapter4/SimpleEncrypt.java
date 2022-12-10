package com.wangwenjun.concurrent.classloader.chapter4;


import java.nio.charset.StandardCharsets;

/**
 * 字节码加密
 */
public class SimpleEncrypt {
    private static final String plain = "Hello ClassLoader";

    private static final byte ENCRYPT_FACTORY = (byte)0xff;

    public static void main(String[] args) {
        byte[] bytes = plain.getBytes(StandardCharsets.UTF_8);

        // 加密
        byte[] encrypt = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            encrypt[i] = (byte)(bytes[i]^ENCRYPT_FACTORY); // 异或, 0-> 1, 1-> 0
        }

        System.out.println(new String(encrypt));

        // 解密
        byte[] decrypt = new byte[encrypt.length];
        for (int i = 0; i < encrypt.length; i++) {
            decrypt[i] = (byte)(encrypt[i]^ENCRYPT_FACTORY); // 异或, 0-> 1, 1-> 0
        }

        System.out.println(new String(decrypt));
    }
}
