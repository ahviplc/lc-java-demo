package com.taobaoMessage;

import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

//阿里大于 -alibaba.aliqin.fc.sms.num.send (短信发送)
//已测试 好使！

public class aliMessageSend {

	public static void main(String[] args) throws ApiException {

		String url = "http://gw.api.taobao.com/router/rest";

		// appkey secret-阿里大于-控制台-应用列表-设置-查看key和secret

		String appkey = "#";
		String secret = "#";

		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		/*
		 * 公共回传参数，在“消息返回”中会透传回该参数；举例：用户可以传入自己下级的会员ID，在消息返回时，该会员ID会包含在内，
		 * 用户可以根据该会员ID识别是哪位会员使用了你的应用
		 */
		req.setExtend("");
		// 短信类型，传入值请填写normal
		req.setSmsType("normal");

		// 短信签名，传入的短信签名必须是在阿里大于“管理中心-验证码/短信通知/推广短信-配置短信签名”中的可用签名。
		req.setSmsFreeSignName("短信测试使用");

		/*
		 * 短信模板变量，传参规则{"key":"value"}，key的名字须和申请模板中的变量名一致，多个变量之间以逗号隔开。
		 * 示例：针对模板“验证码${code}，您正在进行${product}身份验证，
		 * 打死不要告诉别人哦！”，传参时需传入{"code":"1234","product":"alidayu"}
		 */

		// req.setSmsParamString("{\"code\":\"1234\",\"product\":\"alidayu\"}");

		// gson使用和 实现随机4位的整数
		int x;// 定义两变量
		Random ne = new Random();// 实例化一个random的对象ne
		x = ne.nextInt(9999 - 1000 + 1) + 1000;// 为变量赋随机值1000-9999

		Gson gsonMsg = new Gson();
		JsonObject msgJsonObject = new JsonObject();
		msgJsonObject.addProperty("name", "LC");

		// int 转 string 1》String.valueOf(i) 2》 Integer.toString(i) 3》 i+""

		msgJsonObject.addProperty("number", String.valueOf(x));

		String gg = gsonMsg.toJson(msgJsonObject);

		req.setSmsParamString(gg);

		// req.setSmsParamString("{\"name\":\"LC\",\"number\":\"1249\"}");

		// 短信接收号码
		req.setRecNum("15855891980");

		//
		req.setSmsTemplateCode("SMS_34800040");
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		System.out.println(rsp.getBody());

	}

}
