package yingdg.exercise.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by yingdg on 2017/7/13.
 */
public class 生成二维码 {
    public static void main(String[] args) throws WriterException, IOException {
        // 要生成的字符串
        String text = "https://www.baidu.com";

        // 二维码参数
        int width = 100;
        int height = 100;
        String format = "png";
        Hashtable<EncodeHintType, String> hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter()
                .encode(text, BarcodeFormat.QR_CODE, width, height, hints);

        // 以生成图片的形式
        File outputFile = new File("src/main/java/yingdg/exercise/zxing/我的二维码.png");
        MatrixToImageWriter.writeToPath(bitMatrix, format, outputFile.toPath());
    }
}
