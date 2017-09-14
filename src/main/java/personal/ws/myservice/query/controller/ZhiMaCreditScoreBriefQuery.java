package personal.ws.myservice.query.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
@RequestMapping("/zhimaxinyong")
public class ZhiMaCreditScoreBriefQuery {

	@SuppressWarnings("unused")
	@RequestMapping("/query")
	@ResponseBody
	public String query() {
		//公共请求参数
		//支付宝分配给开发者的应用ID
		String app_id = "";
		//接口名称
		String method = "";
		//仅支持JSON
		String format = "JSON";
		//请求使用的编码格式，如utf-8,gbk,gb2312等
		String charset = "utf-8";
		//商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
		String sign_type = "RSA2";
		//商户请求参数的签名串，详见签名
		String sign = "";
		//发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"
		String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		//调用的接口版本，固定为：1.0
		String version = "1.0";
		//详见应用授权概述
		String app_auth_token = "";
		//请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递，具体参照各产品快速接入文档
		String biz_content = "";
		//芝麻信用分查询请求参数
		//商户请求的唯一标志
		String transaction_id = "";
		String product_code = "";
		String cert_type = "";
		String cert_no = "";
		String name = "";
		String admittance_score = "";
		return null;
	}
}
