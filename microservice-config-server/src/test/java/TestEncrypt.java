import com.king.microservice.config.server.ConfigServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.config.server.encryption.EncryptionController;
import org.springframework.cloud.config.server.encryption.KeyStoreTextEncryptorLocator;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * @author huangjie
 * @description
 * @date 2019/2/12
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ConfigServerApplication.class)
public class TestEncrypt {

    @Autowired
    private EncryptionController encryptionController;

    public static void main(String[] args) throws Exception {

    }

    @Test
    public void testEncrypt(){
        System.out.println("加密后的密文  "+encryptionController.encrypt("dev",null));
    }


    public static String encrypt(String plainText,String secretKey) throws Exception {
        Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec secretKeySpec=new SecretKeySpec(secretKey.getBytes("UTF-8"),"AES");
        //IvParameterSpec iv = new IvParameterSpec(cKey.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] p=plainText.getBytes("UTF-8");
        byte[] result=cipher.doFinal(p);
        BASE64Encoder encoder=new BASE64Encoder();
        return encoder.encode(result);

    }


}
