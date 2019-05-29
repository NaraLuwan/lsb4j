package com.luwan.github.lsb;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * @author luwan
 * @date 2019/5/29
 */
public class LsbTest {

    private String DATA = "hello lsb";

    @Test
    public void toImgAESWithPW() throws Exception {
        URL url = LsbTest.class.getResource("/test.png");
        File file = new File(url.toURI());
        boolean retBool = ImageSteganography.toImg(DATA, "testPassword", file.getPath());
        Assert.assertTrue(retBool);

        InputStream inputStream = LsbTest.class.getResourceAsStream("/test.png");
        String data = ImageSteganography.fromImg(inputStream);
        Assert.assertTrue(DATA.equals(data));
    }

}
