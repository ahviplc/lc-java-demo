package com.taobaoMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.ZhimaCreditAntifraudVerifyRequest;
import com.alipay.api.response.ZhimaCreditAntifraudVerifyResponse;

public class mayicreditdemo {

    /**蚂蚁开放平台网关地址*/
    private static final String URL               = "https://openapi.alipay.com/gateway.do";

    /**商户 App ID: eg.1000785*/
    private static final String APPID             = "$APPID";

    /**商户私钥*/
    private static final String APP_PRIVATE_KEY   = "$APP_PRIVATE_KEY";

    /**蚂蚁公钥*/
    private static final String ALIPAY_PUBLIC_KEY = "$ALIPAY_PUBLIC_KEY";

    /**编码格式*/
    private static final String FORMAT            = "json";

    /**字符编码: GBK 或 UTF-8*/
    private static final String CHARSET           = "UTF-8";


    /**加密方式:RSA或RSA2 */
    private static final String SIGN_TYPE           = "RSA2";

    /**产品码,商户同蚂蚁签订合约时获取的产品码,欺诈信息验证产品请使用:w1010100000000002859*/
    private static final String PRODUCT_CODE      = "w1010100000000002859";

    /**用于生成TransactionId的自增器*/
    private static AtomicLong   transAutoIncIdx   = new AtomicLong(1000000000000l);

    /**
     * 
     * @param args 参数
     */
    public static void main(String[] args) {

        // "transaction_id" 是代表一笔请求的唯一标志，该标识作为对账的关键信息，
        // 对于相同 transaction_id 的查询，芝麻在一天（86400秒）内返回首次查询数据，
        // 超过有效期的查询即为无效并返回异常（错误码:TRANSACTION_ID_EXPIRED），
        // 有效期内的重复查询不重新计费。
        String transactionId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
                               + transAutoIncIdx.getAndDecrement();

        AlipayClient alipayClient = new DefaultAlipayClient(URL, APPID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);

        ZhimaCreditAntifraudVerifyRequest request = new ZhimaCreditAntifraudVerifyRequest();
        request.setBizContent("{\"transaction_id\":\"" + transactionId + "\",\"product_code\":\""
                              + PRODUCT_CODE + "    \"cert_no\":\"640202199007164686\","
                              + "    \"cert_type\":\"100\"," + "    \"name\":\"牛德华\","
                              + "    \"mobile\":\"15843991158\","
                              + "    \"email\":\"jnlxhy@alitest.com\","
                              + "    \"bank_card\":\"20110602436748024138\","
                              + "    \"address\":\"北京 北京市 朝阳区 呼家楼街道北京市朝阳区东三环中路1号环球金融中心 东塔9层\","
                              + "    \"ip\":\"101.247.161.1\","
                              + "    \"mac\":\"AA-34-4D-59-61-28\","
                              + "    \"wifimac\":\"22-35-4A-5F-07-88\","
                              + "    \"imei\":\"868331011992179\"" + "  }");
        ZhimaCreditAntifraudVerifyResponse response;
        try {
            // 请求记录
            System.out.println("send zhimaCreditAntifraudVerifyRequest request="
                               + request.getBizContent());
            response = alipayClient.execute(request);

        } catch (Exception e) {
            e.printStackTrace();
            // 一般是网络异常 建议重试 入参(包括transaction_id)不变 不会重复计费
            try {
                // 纪录请求
                System.out.println("send zhimaCreditAntifraudVerifyRequest retry request="
                                   + request.getBizContent());
                response = alipayClient.execute(request);
            } catch (AlipayApiException e1) {
                // 异常时请记录transactionId排查问题
                System.err.println("send zhimaCreditAntifraudVerifyRequest error request="
                                   + request.getBizContent());
                e1.printStackTrace();
                // 包装成业务异常抛出
                throw new RuntimeException(e1);
            }
        }

        if (response.isSuccess()) {
            // 取得欺诈信息验证VerifyCode列表，各个VerifyCode具体含义参见芝麻信用提供的详细技术对接文档
            // 建议记录bizNo用于核对
            System.out.println("欺诈信息验证验证码列表为=" + response.getVerifyCode() + ",bizNo="
                               + response.getBizNo());
        } else {
            // 处理各种异常 异常时请记录transactionId排查问题
            System.out.println("查询芝麻信用-欺诈信息验证的错误 code=" + response.getCode() + ",msg="
                               + response.getMsg() + ",transactionId=" + transactionId);
        }
    }
}
