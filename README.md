# MarsLogDemo
Mars(XLog)简单的封装，只需要更替密钥即可使用
## 初始化
PPLog.init(上下文, BuildConfig.DEBUG, 路径)
## 使用
PPLog.d(TAG,"ssssss")
## so库
目前支持arm64-v8a,armeabi-v7a
# 引入方式（先试用，但是目前版本不支持替换密钥，可以适用于测试）
implementation 'com.github.hpisfox:MarsLogDemo:1.0.0'
