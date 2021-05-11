# **Tiny**
an image compression framework.

----
[![](https://jitpack.io/v/WangKiven/Tiny.svg)](https://jitpack.io/#WangKiven/Tiny)

[Blog entry with introduction](http://zhengxiaoyong.com/2017/04/23/Android%E5%9B%BE%E7%89%87%E5%8E%8B%E7%BC%A9%E6%A1%86%E6%9E%B6-Tiny/)
or
[Introduction of compression](http://zhengxiaoyong.com/2017/04/23/%E4%B9%9F%E8%B0%88%E5%9B%BE%E7%89%87%E5%8E%8B%E7%BC%A9/)


## **Effect of compression**

| ImageInfo | Tiny | Wechat |
| :-: | :-: | :-: |
6.66MB (3500x2156) | 151KB (1280x788) | 135KB (1280x788)|
4.28MB (4160x3120) | 219KB (1280x960)| 195KB (1280x960)|
2.60MB (4032x3024) | 193KB (1280x960)| 173KB (1280x960)|
372KB (500x500) | 38.67KB (500x500) | 34.05KB (500x500)|
236KB (960x1280) | 127KB (960x1280) | 118KB (960x1280)|

## **介绍**
`Tiny` 是一个图片压缩工具 。原有的库太老了，不能用于较新的应用开发，所以做了一些修改。

- 适配到最新的系统
- 处理`heif`图片压缩后方向不对的问题。`heif`是新系统支持的图片格式。
- 由于图片方向获取使用了`androidx`的`ExifInterface`库，所以限制只有支持`androidx`的程序可以使用

## **使用**
### **导入**

第一步添加仓库

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
}
```

第二部导入库

```
implementation 'com.github.WangKiven.Tiny:tiny:1.0.1'
```

### **Choose an abi**
**Tiny** provide abi：`armeabi`、`armeabi-v7a`、`arm64-v8a`、`x86`.

Choose what you need **"abi"** version：

```
android {
    defaultConfig {
        ndk {
            abiFilters 'armeabi','x86'//or armeabi-v7a、arm64-v8a、x86
        }
    }
}
```

### **Initialization**

```
        Tiny.getInstance().init(this);
```
### **Compression**

#### **AsBitmap**

```
        Tiny.BitmapCompressOptions options = new Tiny.BitmapCompressOptions();
        //options.height = xxx;//some compression configuration.
        Tiny.getInstance().source("").asBitmap().withOptions(options).compress(new BitmapCallback() {
            @Override
            public void callback(boolean isSuccess, Bitmap bitmap, Throwable t) {
                //return the compressed bitmap object
            }
        });
        
        //or sync compress.
        BitmapResult result = Tiny.getInstance().source("").asBitmap().withOptions(options).compressSync();
```

#### **AsFile**

```
        Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
        Tiny.getInstance().source("").asFile().withOptions(options).compress(new FileCallback() {
            @Override
            public void callback(boolean isSuccess, String outfile, Throwable t) {
                //return the compressed file path
            }
        });
        
        //or sync compress.
        FileResult result = Tiny.getInstance().source("").asFile().withOptions(options).compressSync();
```
#### **AsFileWithReturnBitmap**

```
        Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
        Tiny.getInstance().source("").asFile().withOptions(options).compress(new FileWithBitmapCallback() {
            @Override
            public void callback(boolean isSuccess, Bitmap bitmap, String outfile, Throwable t) {
                //return the compressed file path and bitmap object
            }
        });
        
        //or sync compress.
        FileWithBitmapResult result = Tiny.getInstance().source("").asFile().withOptions(options).compressWithReturnBitmapSync();
```

#### **BatchAsBitmap**

```
        Tiny.BitmapCompressOptions options = new Tiny.BitmapCompressOptions();
        Tiny.getInstance().source("").batchAsBitmap().withOptions(options).batchCompress(new BitmapBatchCallback() {
            @Override
            public void callback(boolean isSuccess, Bitmap[] bitmaps, Throwable t) {
                //return the batch compressed bitmap object
            }
        });
        
        //or sync compress.
        BitmapBatchResult result = Tiny.getInstance().source("").batchAsBitmap().withOptions(options).batchCompressSync();
```
#### **BatchAsFile**

```
        Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
        Tiny.getInstance().source("").batchAsFile().withOptions(options).batchCompress(new FileBatchCallback() {
            @Override
            public void callback(boolean isSuccess, String[] outfile, Throwable t) {
                //return the batch compressed file path
            }
        });
        
        //or sync compress.
        FileBatchResult result = Tiny.getInstance().source("").batchAsFile().withOptions(options).batchCompressSync();
```
#### **BatchAsFileWithReturnBitmap**

```
        Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
        Tiny.getInstance().source("").batchAsFile().withOptions(options).batchCompress(new FileWithBitmapBatchCallback() {
            @Override
            public void callback(boolean isSuccess, Bitmap[] bitmaps, String[] outfile, Throwable t) {
                //return the batch compressed file path and bitmap object
            }
        });
        
        //or sync compress.
        FileWithBitmapBatchResult result = Tiny.getInstance().source("").batchAsFile().withOptions(options).batchCompressWithReturnBitmapResult();
```

## **Version**

* **v0.0.1**：The first version.
* **v0.0.2**：Optimize the compression strategy,and handle with the orientation of bitmap.
* **v0.0.3**：Unified as `libtiny.so`
* **v0.0.4**：Add cover source file configuration—see`{@link FileCompressOptions#overrideSource}`, and setting up the batch compressed file paths—see  `{@link BatchFileCompressOptions#outfiles}`
* **v0.0.5**：Fix google store reject.
* **v0.0.6**：Initialization is not must.Add clear compression directory method,see`{@link Tiny#clearCompressDirectory}`
* **v0.0.7**：fix issue#29
* **v0.1.0**：Add exception thrown interface, add the `Throwable` parameter to the callback method `{@link xxxxCallback#callback}`, see [Update Introduce](https://github.com/Sunzxyong/Tiny/issues/38)
* **v1.0.0**：Add synchronous compression method and compression directory Settings.
* **v1.1.0**：Add baseline setting support.

## **About**
* **Blog**：[https://zhengxiaoyong.com](https://zhengxiaoyong.com)
* **Wechat**：

![](https://raw.githubusercontent.com/Sunzxyong/ImageRepository/master/qrcode.jpg)

## **License**

>
>     Apache License
>
>     Version 2.0, January 2004
>     http://www.apache.org/licenses/
>
>     Copyright 2018 郑晓勇
>
>  Licensed under the Apache License, Version 2.0 (the "License");
>  you may not use this file except in compliance with the License.
>  You may obtain a copy of the License at
>
>      http://www.apache.org/licenses/LICENSE-2.0
>
>  Unless required by applicable law or agreed to in writing, software
>  distributed under the License is distributed on an "AS IS" BASIS,
>  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
>  See the License for the specific language governing permissions and
>  limitations under the License.

