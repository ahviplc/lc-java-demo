

String url="http://gw.api.taobao.com/router/rest";
		String appkey="23571242";
		String secret="5215302a5acdc1db75c9f271f3e03891";
		
		

TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
req.setExtend( "" );
req.setSmsType( "normal" );
req.setSmsFreeSignName( "短信测试使用" );
req.setSmsParamString( "{name:'LC',number:'1234'}" );
req.setRecNum( "15855891980" );
req.setSmsTemplateCode( "SMS_34800040" );
AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
System.out.println(rsp.getBody());