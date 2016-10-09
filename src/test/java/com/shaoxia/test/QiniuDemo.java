package com.shaoxia.test;

import java.io.IOException;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class QiniuDemo {
	// 设置好账号的ACCESS_KEY和SECRET_KEY
	String ACCESS_KEY = "FsSkVH2Z-LsVIDU5r0z9xkJbnLLmus8QhqdZie6t";
	String SECRET_KEY = "gVroZIxoFtZ3CGw-lOlif6RrSWtpuq3wQ-8w4t9i";
	// 要上传的空间
	String bucketname = "blogpicture";
	// 上传到七牛后保存的文件名
	String key = "my-java---asd.png";
	// 上传文件的路径
	String FilePath = "F:\\asd.png";

	// 密钥配置
	Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	// 创建上传对象
	UploadManager uploadManager = new UploadManager();

	// 简单上传，使用默认策略，只需要设置上传的空间名就可以了
	public String getUpToken() {
//		return auth.uploadToken(bucketname);
		return auth.uploadToken(bucketname,null,3600,new StringMap()
		          .put("callbackUrl","http://source.tccarticle.com")
		          .put("callbackBody", "filename=$(fname)&filesize=$(fsize)"));
	}

	public void upload() throws IOException {
		try {
			// 调用put方法上传
			Response res = uploadManager.put(FilePath, null, getUpToken());
			// 打印返回的信息
			System.out.println(res.bodyString());
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			System.out.println("请求失败时打印的异常的信息");
			System.out.println(r.toString());
			try {
				// 响应的文本信息
				System.out.println("请求失败时响应的文本信息");
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
	}

	public static void main(String args[]) throws IOException {
		new QiniuDemo().upload();
	}
}
