package com.lance.study.rsa;

import javax.crypto.Cipher;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


public class RSAUtils {

    private static final String ARITHMETIC_RSA = "RSA";

    private static final String ARITHMETIC_SHA1WITHRSA = "SHA1withRSA";

    private static final String ARITHMETIC_MD5WITHRSA = "MD5withRSA";

    private static final String CHAR_SET = "UTF-8";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 数据签名
     * 签名内容
     * @param rawData
     * 私钥
     * @param privateKey
     * 算法
     * @param arithmetic
     * @return
     * @throws Exception
     */
    public static String signData(String rawData, String privateKey,String arithmetic) throws Exception{
        PrivateKey prikey = getPrivateKey(privateKey);
        Signature signature = Signature.getInstance(arithmetic);
        signature.initSign(prikey);
        signature.update(rawData.getBytes(CHAR_SET));
        byte[] sign = signature.sign();
        byte[] encode = Base64.getEncoder().encode(sign);
        return new String(encode);
    }

    /**
     *
     * @param rawData
     * @param sign
     * @param publicKey
     * @param arithmetic
     * @return
     * @throws Exception
     */
    public static boolean verifySignData(String rawData, String sign, String publicKey, String arithmetic) throws Exception{
        PublicKey pubKey = getPublicKey(publicKey);
        Signature signature = Signature.getInstance(arithmetic);
        signature.initVerify(pubKey);
        signature.update(rawData.getBytes(CHAR_SET));
        byte[] decodeSign = Base64.getDecoder().decode(sign);
        boolean verify = signature.verify(decodeSign);
        return verify;
    }

    /**
     * 获取base64加密后的字符串的原始公钥
     * @param publicKey  密钥字符串
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception{
        byte[] keyByte = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyByte);
        KeyFactory keyFactory = KeyFactory.getInstance(ARITHMETIC_RSA);
        PublicKey pubKey = keyFactory.generatePublic(x509EncodedKeySpec);
        return pubKey;
    }


    /**
     * 获取base64加密后的字符串的原始私钥
     * @param privateKey 密钥字符串
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception{
        byte[] keyByte = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyByte);
        KeyFactory keyFactory = KeyFactory.getInstance(ARITHMETIC_RSA);
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        return priKey;
    }

    /**
     * 获取字符串类型的key
     * @param key
     * @return
     */
    public static String getKeyString(Key key){
        byte[] keyBytes = key.getEncoded();
        byte[] encode = Base64.getEncoder().encode(keyBytes);
        return new String(encode);
    }

    /**
     * 加密
     * @param rawData
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(String rawData, Key key) throws Exception{
        Cipher ch = Cipher.getInstance(ARITHMETIC_RSA);
        ch.init(Cipher.ENCRYPT_MODE, key);
        byte[] bytes = ch.doFinal(rawData.getBytes(CHAR_SET));
        return bytes;
    }

    /**
     * 解密
     * @param encryptData
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(String encryptData, Key key) throws Exception{
        byte[] decodeData = Base64.getDecoder().decode(encryptData);
        Cipher ch = Cipher.getInstance(ARITHMETIC_RSA);
        ch.init(Cipher.DECRYPT_MODE, key);
        byte[] bytes = ch.doFinal(decodeData);
        return bytes;
    }

    /**
     * 加密
     * @param rawData
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptToBase64(String rawData,Key key) throws Exception{
        byte[] encrypt = encrypt(rawData, key);
        if(encrypt == null){
            return null;
        }
        byte[] encode = Base64.getEncoder().encode(encrypt);
        return new String(encode);
    }

    /**
     * 解密
     * @param encryptData
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptToBase64(String encryptData, Key key) throws Exception{
        byte[] decrypt = decrypt(encryptData, key);
        if(decrypt == null){
            return null;
        }
        return new String(decrypt);
    }

    /**
     * 分段加密
     * @param rawData
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] segmentationEncrypt(String rawData, Key key) throws Exception{
        ByteArrayOutputStream outputStream = null;
        try {
            Cipher cipher = Cipher.getInstance(ARITHMETIC_RSA);
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte[] dataBytes = rawData.getBytes();
            int inputLen = dataBytes.length;
            int offSet = 0;
            int i = 0;
            byte[] cache;
            outputStream= new ByteArrayOutputStream();
            while (inputLen - offSet > 0){
                if(inputLen - offSet > MAX_ENCRYPT_BLOCK){
                    cache = cipher.doFinal(dataBytes, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(dataBytes, offSet, inputLen - offSet);
                }
                outputStream.write(cache);
                i++;
                offSet = i*MAX_ENCRYPT_BLOCK;
            }
            byte[] encryptedData = outputStream.toByteArray();
            return encryptedData;
        } finally {
            if(outputStream != null){
                outputStream.close();
            }
        }
    }

    /**
     * 分段解密
     * @param encryptData
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] segmentationDecrypt(String encryptData, Key key) throws Exception{
        byte[] dataBytes = Base64.getDecoder().decode(encryptData);
        ByteArrayOutputStream outputStream = null;

        try {
            Cipher cipher = Cipher.getInstance(ARITHMETIC_RSA);
            cipher.init(Cipher.DECRYPT_MODE,key);
            int inputLen = dataBytes.length;
            int offSet = 0;
            int i = 0;
            byte[] cache;
            outputStream= new ByteArrayOutputStream();
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(dataBytes, offSet, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(dataBytes, offSet, inputLen - offSet);
                }
                outputStream.write(cache);
                i++;
                offSet = i*MAX_DECRYPT_BLOCK;
            }
            byte[] encryptedData = outputStream.toByteArray();
            return encryptedData;
        } finally {
            if(outputStream != null){
                outputStream.close();
            }
        }
    }


    /**
     * 分段加密
     * @param rawData
     * @param key
     * @return
     * @throws Exception
     */
    public static String segmentationEncryptToBase64(String rawData,Key key) throws Exception{
        byte[] encrypt = segmentationEncrypt(rawData, key);
        byte[] encode = Base64.getEncoder().encode(encrypt);
        return new String(encode);
    }

    /**
     * 分段解密
     * @param encryptData
     * @param key
     * @return
     * @throws Exception
     */
    public static String segmentationDecryptToBase64(String encryptData, Key key) throws Exception{
        byte[] decrypt = segmentationDecrypt(encryptData, key);
        return new String(decrypt);
    }


    /**
     * 生成密钥
     * @param size
     */
    public static void createKeyPairs(int size) throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ARITHMETIC_RSA);
        keyPairGenerator.initialize(size);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        PublicKey pubKey = keyPair.getPublic();
        PrivateKey priKey = keyPair.getPrivate();
        String pubKeyString = getKeyString(pubKey);
        String priKeyString = getKeyString(priKey);
        System.err.println("公钥为:"+pubKeyString);
        System.err.println("私钥为:"+priKeyString);
    }

    public static void main(String[] args) throws Exception{
//        createKeyPairs(1024);
//        String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDPU2DRTkwWmUtdoLtNC/NfkA4pOpBPDzhP2kJyfNHL6EeBcCxOXsuwJwyhWrLeUhECs0N4b0B81vUDpcuTwhPV+kMpZUKvVZK/jkypuWkoqn6HE3w43jkC5v1DX0/+uBEIUv4t0ls/hGKUz5hKeI7y33Cb+Jf3L3QaF7TpqrG5NwIDAQAB";
//        String priKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAM9TYNFOTBaZS12gu00L81+QDik6kE8POE/aQnJ80cvoR4FwLE5ey7AnDKFast5SEQKzQ3hvQHzW9QOly5PCE9X6QyllQq9Vkr+OTKm5aSiqfocTfDjeOQLm/UNfT/64EQhS/i3SWz+EYpTPmEp4jvLfcJv4l/cvdBoXtOmqsbk3AgMBAAECgYA+Fv9Yz7UT04bxnFBbq6eQSAklqcdMFgfMSlY6CU2fXooDpr9uHQ+tSpSKwintUPCNOXiMWe0bNhkcpYuJTcc4dMGDFI2DldU380tprQXixyVlnSQPdwpyWbKaIPJ6CfwN4Xre7JONDRiY2sQd1Q6aUt5JNw1ITXE18n4t+QnYAQJBAPTz67gQSVMEl7+FNUEw/NtSrBkHBQlBDXiGQd1ftufr+QV0hMkbDOKLfqUiUkkzSiZd97y18LA8as6X1WfbDAECQQDYrQmAr36a49o+7u/xxnZt58x+Sk7vOc8byMa9k9fX/t7xzsjHECa6ccERIZW4HGTxrC+ID+TV/NQVoVTp5iU3AkABnk7zY2AopUIfaNDmQl2ZUV2DuNEms4BUSIbM4KIDvEJj79YkwgH+yS8oBicHBsi5oitN2uHhODT4cnZdfkwBAkBR6rGCBlc+JdCYJGtwo9CYYp9MS7ml9dwYLAL/H0rmLUSKdNwxbijGKkWMvX7GGW4MdEWQEjo4sJpaByF8QHdnAkAdFAjouDehaoteNSq9ag/uxY1M/E1bOH8PpPKG84vtIhewO3BgG4aeD7BxEO8TzRTVFN5b7YgqeShIwVmcQvhJ";
//        String str = "哈哈哈";
//        String 加密数据 = encryptToBase64(str, getPublicKey(pubKey));
//        System.err.println("加密数据:"+加密数据);
//        String 解密数据 = decryptToBase64(加密数据, getPrivateKey(priKey));
//        System.err.println("解密数据:"+解密数据);
//        String 签名结果 = signData(str, priKey, SHA1withRSA);
//        System.err.println("签名数据:"+签名结果);
//        boolean 验签结果 = verifySignData(str, s, pubKey, SHA1withRSA);
//        System.err.println("验签结果"+验签结果);
//        String 分段加密数据 = segmentationEncryptToBase64(str, getPublicKey(pubKey));
//        System.err.println("加密数据:"+分段加密数据);
//        String 分段解密数据 = segmentationDecryptToBase64(分段加密数据, getPrivateKey(priKey));
//        System.err.println(分段解密数据);

    }
}
