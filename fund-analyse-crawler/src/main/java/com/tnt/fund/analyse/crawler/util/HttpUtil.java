package com.tnt.fund.analyse.crawler.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class HttpUtil {
	
	public static String today(String host)throws Exception{
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("UTF-8");
		PostMethod postMethod = new PostMethod(host);
		httpClient.executeMethod(postMethod);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		InputStream in = postMethod.getResponseBodyAsStream();
		int len = 0;
		byte[] buf = new byte[1024];
		while((len=in.read(buf))!=-1){
			out.write(buf, 0, len);
		}
		String responseMsg = out.toString("UTF-8");
		return responseMsg;
//		System.out.println(responseMsg);
	}
	
	public static String history(String host)throws Exception{
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("UTF-8");
		PostMethod postMethod = new PostMethod(host);
		httpClient.executeMethod(postMethod);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		InputStream in = postMethod.getResponseBodyAsStream();
		int len = 0;
		byte[] buf = new byte[1024];
		while((len=in.read(buf))!=-1){
			out.write(buf, 0, len);
		}
		String responseMsg = out.toString("UTF-8");
		return responseMsg;
//		System.out.println(responseMsg);
	}
	
}
