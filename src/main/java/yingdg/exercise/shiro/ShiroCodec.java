package yingdg.exercise.shiro;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

/**
 * Created by yingdg on 2017/8/7.
 */
public class ShiroCodec {
    public static void main(String[] args) {
        // base64
        byte[] encode = Base64.encode("hello".getBytes());
        String s = Base64.decodeToString(encode);
        System.out.println(s);

        // base64
        String encodeToString = Base64.encodeToString("shiro".getBytes());
        System.out.println(encodeToString);
        String s1 = Base64.decodeToString(encodeToString);
        System.out.println(s1);

        // hex
        String base64Encoded = Hex.encodeToString("shiro2".getBytes());
        System.out.println(base64Encoded);
        String str0 = new String(Hex.decode(base64Encoded));
        System.out.println(str0);

        // 散列算法.md5
        String str = "hello";
        String salt = "123";
        String md5 = new Md5Hash(str, salt).toString();
        System.out.println(md5);
        String md5_2 = new Md5Hash(str, salt, 2).toString();
        System.out.println(md5_2);

        // sha256
        String str2 = "hello";
        String salt2 = "123";
        String sha1 = new Sha256Hash(str2, salt2).toString();
        System.out.println(sha1);

        // shiro自定义指定
        String str3 = "hello";
        String salt3 = "123";
        String simpleHash = new SimpleHash("SHA-1", str, salt).toString();
        System.out.println(simpleHash);

        // shiro建造模式实现
        DefaultHashService hashService = new DefaultHashService(); //默认算法SHA-512
        hashService.setHashAlgorithmName("SHA-512");
        hashService.setPrivateSalt(new SimpleByteSource("123")); //私盐，默认无
        hashService.setGeneratePublicSalt(true);//是否生成公盐，默认false
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator()); // 用于生成公盐。默认就这个
        hashService.setHashIterations(1); //生成Hash值的迭代次数
        HashRequest request = new HashRequest.Builder()
                .setAlgorithmName("MD5")
                .setSource(ByteSource.Util.bytes("hello"))
                .setSalt(ByteSource.Util.bytes("123"))
                .setIterations(2)
                .build();
        String hex = hashService.computeHash(request).toHex();
        System.out.println(hex);
    }
}
