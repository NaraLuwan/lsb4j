package luwan.lsb;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * @author NaraLuwan
 * @date 2019/5/29
 */
public class LsbTest {

    @Test
    public void toImgAESWithPW() throws Exception {
        // 图片路径
        String imgPath = "/test.png";
        // 要隐藏的数据
        String text = "hello lsb";
        // 密码 可根据需要指定
        String passWord = "lsb";
        File file = new File(LsbTest.class.getResource(imgPath).toURI());
        boolean writeToImgResult = ImageSteganography.writeToImg(text, passWord, file.getPath());
        Assert.assertTrue(writeToImgResult);

        String data = ImageSteganography.readFromImg(LsbTest.class.getResourceAsStream(imgPath));
        Assert.assertEquals(text, data);
    }
}
