## 项目简介

- 基于LSB算法隐藏数据到图片中丨从图片中提取隐藏的数据
- 支持png、bmp等不压缩或无损压缩的图片格式

## Quick Start

- 示例代码详见： [LsbTest](https://github.com/NaraLuwan/lsb4j/blob/master/src/test/java/luwan/lsb/LsbTest.java)

## 项目结构
```text
lsb4j
└─src
    ├─main
    │  ├─java
    │  │  └─luwan
    │  │      └─lsb
    │  │          │  ImageSteganography.java
    │  │          │  Steganography.java
    │  │          │
    │  │          └─utils
    │  │                  AesCrypt.java
    │  │                  ImageUtils.java
    │  │                  LsbUtils.java
    │  │                  StringUtils.java
    │  │
    │  └─resources
    │          test.png
    │
    └─test
        └─java
            └─luwan
                └─lsb
                        LsbTest.java
```
