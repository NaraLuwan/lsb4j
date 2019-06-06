# ImageLSB

- 基于LSB算法隐藏数据到图片中丨从图片中提取隐藏的数据
- 支持png、bmp等不压缩或无损压缩的图片格式

# eg

```java
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
```