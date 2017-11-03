import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by banma on 2017/9/18.
 */
public class Test3 {
    public static void main(String[] args) {
        System.out.println("1");
        System.out.println(encrypt("341"));

        Integer a = 1;
        System.out.println(a.toString());
    }

    /**
     * 使用默认密钥加密
     * @param sSrc
     * @return
     * @throws Exception
     */
    public static String encrypt(String sSrc){
        String result = "";
        try {
            result = encrypt(sSrc, "hr@maihaoche.com");
            if(!StringUtils.isEmpty(result)) {
                result = result.replace("+", "-").replace("/", "_");
            }
        } catch (Exception e) {
//            LOG.error("aes encrypt error", "hr@maihaoche.com");
        }
        return result;
    }

    public static String encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
//            LOG.warn("aes加密 - Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
//            LOG.warn("aes加密 - Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes("UTF-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        //"算法/模式/补码方式"
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        //使用CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes("UTF-8"));
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("UTF-8"));
        //此处使用BASE64做转码功能，同时能起到2次加密的作用。
        BASE64Encoder s = new BASE64Encoder();
        return  s.encode(encrypted);
    }
}
