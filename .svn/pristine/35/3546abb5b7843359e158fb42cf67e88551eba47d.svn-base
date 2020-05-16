package com.prosay.util;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2017120400369088";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCl+FF76v5DbG+x0azdUtVrIK68J6wR59mPllhTWLhx2s0uLdypZFAmzZEeR2Y2KXygDmajeS/Duo6atRmhLNetrkmvImhnOS9Hm4H89GblStKCvrU30PhAZGIZAHJvoQ6DPJxSOmDPqESs7B/PAaKVRZEY+Iwrg9ACUgomwHpNV698gs3JkbWT8IKvk6DC74gJN4rCpBDFy97ANjNrSlDkLd5Je2oWXyUl6UT4IXfl+KTTCJzwwOGQEq3x7qejG7iRhNf1N8B+uTufXDYwj11f54H5d9nKyP1bAeeg71/ZB/6TN5r1t0N1ZiQ2Nlw7wXR5BHumCxQZCT9x2LrgxapHAgMBAAECggEAQ2/bhBNezvyPgdwldiSDLS0xcqhYBUqEnxBCwVLqVvDFr/7Yi0eEPdAHKBtwz48Z4VsYn1HNc6Uo7nmpeVDkLeEEdZHwhAi7SkhIkASqjn5RfOiKb6yMQnXHWktRNERnxjFpfwvTbGz9wpmK7yzF6AtFQrUzFzdO3LKDB23sA+J1pPFBoUjMG7NDSzzY4KwEeCLx8Y6wXa4OIIItukUBWf81l++6VazrsPntDcLDlcVXm0ASGTNm0QZOuzVXJXLyfSO0J1e7M9Y0KKZUYGlgXCFsHUMuyntf23qwtppowvlwWDvwo3f1zFcRZoC+PdWgCRiNKSINUdd9Sv93QHW8yQKBgQDlejHJTAzpVl7S+fY0GF3SytEZYWjudFjuJ15dqj110+3pG2JDZEbLs/nOBsyb8y335IhHBfZLUnHDOFrCDd6MoyKcE8e0Z+yjzkHE2tDDl8G/hivYl6lzJj9YhB3XYLYw35mryewxSxxshPSJKafthbv0ETVBX6R2JcQpIlDfpQKBgQC5Jw9+2MRIONhieo6vjXXTxmZE4iImK27OzzvaNeL5Fi+Fv2eHLuzL3es3ErYqHHyK1hETBfRbkxeUY9fXmOQmhJWdnJ/ySlVsU44LtlgaWVV4K1YeHt2lJ4RPK3pfdGyTA40VN7pBFg/xQzzqFvSy4xah9s4gv8AHCl+05st+ewKBgHWPIg5DvaGUM8PVdkG98eLZMEHWW1E9nYlz9a36Vr4UMh9nffGUX4hfzSu05AFIlVYXZ5ErBsATnmAqcKRVQpu9vy9CGt7bFqncMmlMyAJLrmAVtkShfZeWEoGgPUFnNC7Zffo52ZxvJEKb4kKaaoMOLfmdDzFMdA5qzpFcGR5ZAoGAY6qBDtkEn5Pp9ujeEeXl/T+ZIY9QrpdGqJCpOUc0jLzCjA6CXiSGVx94VEcARB7dc6bTvhfAd+LwGHK+Z1HnzkwkdZkwCC63WP6JQgi/2D8qYOuDCee3jAV/lVZkIyn6oBCTS06aNhr4Lfjxu1jsE7dmIX1HbB2L8r5E0o5CEC0CgYEAsI6P1uDZMnU9WbczYyYoXXhS8nY92vzUU9j6304kHIFd53/1b3AVrpf80nr+1lxK51jOyR2XHrwA276L0HXY4+vdTO2v9HQgAzItKsNEo4HDUBMf6BU7h9BoyA43ZSvweMEEsDzsHKu3/77TSFhPmYWrOtR+ODC7Yjz/+UcXHK8=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlRZIg3Ob2vyBhuOwv7aufjmNlRsNEjUxBkWqtxTk7E4m3eVCcJrko10iRrhFmt/CnrQb+HCz7Yt8lTxsgydJI7uzSQVvQBvG82/YGb9gk4SZle9JDZRlw+eRzO8b1VBYuqzmm+IYu/tTWV5RIb1L1wrlfk7QOriIVPXgRfBtPqLdytIBDvvmdBKOG1YYg8JDjXr6yBYcYj9eEksTA1aOiLuU6q/x3cD8GkzH9RDUSzm3Tl+kiAp/PpiBLLLpOf/tinNmdoOa0oTBl58LFC6C+ptXY0NoTnTSzEXn7C1thYTfE8HjgkgLypis63WliPDkf9kzj6dzZvoKnTO8VPCHGQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

