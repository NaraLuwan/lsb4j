package com.luwan.github.lsb;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * @author luwan
 * @date 2019/5/29
 */
public class LsbTest {

    @Test
    public void toImgAESWithPW() throws Exception {
        String imgPath = "/test.png";   // 图片路径
        String text = "hello lsb";      // 要隐藏的数据
        String passWord = "lsb";        // 密码 可根据需要指定
        File file = new File(LsbTest.class.getResource(imgPath).toURI());
        boolean writeToImgResult = ImageSteganography.writeToImg(text, passWord, file.getPath());
        Assert.assertTrue(writeToImgResult);

        String data = ImageSteganography.readFromImg(LsbTest.class.getResourceAsStream(imgPath));
        Assert.assertTrue(text.equals(data));
    }

}
