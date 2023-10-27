import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;

public class apiTest {


    public static void main(String[] args) throws AlipayApiException {
        String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCksa22XIa9T+fCeLiIq7NAP/4kIgPLux8wZdHu0GL+rlxguEEgLTCwZFHeXGQ9r6cECccXrnkHT6scTtgvi7rE/XhNfH96uYK4bJWPnq0iA+n6zHQjgE798WYBBHh2tme6x46vfP6bFI5SEOVhInlYiyS4QEh4FKmNNg04yWhFOIpdYjWl9EVMVYlxhZb0+Tr54gAuEQk6RjOqpJa4J/ZmZj30IlXhMo81wTaLWjFdu/Koi150q1rk9Cgiys7bnout0AT2GdI23hSbLGfiVvvtBNJ5uBg+2rUXj1W2y4qiYYZuObczre7PY7WDhFw6Hd0Ou0RBWlC7D5MG92JgFjjVAgMBAAECggEAALLYP8vIrojmPMbyUXIZAo7C7zhNt9GFYlksOM0dfmMS9T4S2KYYbMcgKcdOjYxvMu8vrwQIc2V9HlOfSyRWmHtSxVFf4Q9kZI/hWoibx2co2O2BGJ10uFML0blJIYfIjNxH2Kq5EcNWjzaPIohY520/rKBpLkk4JucxyynfjVucRu3cDjBrlkL3j98X/9HdzHUifmx6pV6t+u0tMiMETDA0t/b1o1Vqj860bg8q3GXhYrhzHuDQxjn8KBPEFVTK09xhZrtUIEfScg7gloHZinEEE4E9SD8txMwGRVshq8/AjuVcluMPoHe2Y6LWpSn0aMe+YeBitOtliuSNJ7YQwQKBgQDxFkh9fWb9qh1j/HfjtHfr/ZfvESfCdJ/Qsh7YJlNPj0CkANT0kqhnsrGBhVSmWEQj9rHTmpZhtHl8+DSlA/B+V9am8H8GPgwmYgpoPydmZwKoTL8nV2etDg8x/xtTz9tt4Vox/QrH3AH23jZDwYfiCjLeK8h2ZPyTpOmrrnOK/QKBgQCu4a3+GWFI2PYBsToFSdZhGr04Mh2zkrPgkimLojXc+4SJN74XXiT0qJJASolMzYiVDD770O6d/DYsB6pGTL57POtQGwiCqaxtaaQqkF2VQVoXR2MJUdhP4wPCrTAwbcKITHyKgnhRvpSGJu/olYBF2gg/wfwizbD16GnBgKBouQKBgDvIFcOgY/iExXL0uNnq9026fYFXlK9gcfyzHnjk0AXLdPD2D9hxwXatVdzTLhFtk8ADPUdGbwHdWb7GYW+F0Y3SxDRzMFxg6GxgJUqCVofLBVqsZT4hK2E7OkNgies3gKb3mYEJyTGT+Zy4r2YPyF0hwEldPAE3u3X1D8h1ossVAoGAIHz+hNkUMKNVuGTDQZ5A/FMqWdk9mVGkpxCgNTeSSBZVveQkJVUxCl6SpOzz6te4CvB2dvOOkLz9F1rpMBwruttLX9tVzmhVBxJ8FAgzynmUkgSKCn4w8E5dMXpZHHYXkJic/mJUleMQ1oc/RHcZmGBz7dxMye5qdJSTqQYLRskCgYAVk/7iqG1Yn/8R/uggp+X+fGpsmhQzNDpgDtJsxZTkmdWy7pxVKHM4hirQ5TFqQ61TmZEKdpcRiM32Th4TF5VNIpH5y6H5EZsnpL4XfQa++1WP1MHbnICwBT8z1DMD5TAXmRN8AyCpu2nNOhuf2XqJeUbuVu0J0CCqKKjv3oaHgQ==";
        String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyQqJDHvyMzrJFdu6zBTyVyJMQWT/XlulmN3fmzRmYeRqlqVpyFzQp84QRs8m8yZb3jCde/+FgI2LMLqAEdI6CgbOUc4rhWws0r5U73JHz/XfDxvU7MP3Gx1agcYeugfbQd+xJx4JFZMfdEhHvd/NW0Uv7CY3B+4oQYkLBGM5qb2uxJLbJA2vTJDyeVReZri5922INeH4gwlYqRkHNuuGmKnmLdNl72hJRx8yV7G9dDEUBqW9xzaAIiY4pIt7hNJt5ssiYYw6Be543nK0+YUObaKLGcCvwLYjdpj81Bpz0OGRBg7iCT1UpWxUHDg94Le/D3K/65uoUs/mc3bR2i15fwIDAQAB";
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl("https://openapi-sandbox.dl.alipaydev.com/gateway.do");
        alipayConfig.setAppId("2021000122646170");
        alipayConfig.setPrivateKey(privateKey);
        alipayConfig.setFormat("json");
        alipayConfig.setAlipayPublicKey(alipayPublicKey);
        alipayConfig.setCharset("UTF8");
        alipayConfig.setSignType("RSA2");
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo("20150320010101001");
        model.setTotalAmount("88.88");
        model.setSubject("Iphone6 16G");
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        model.setQrPayMode("2");
        request.setBizModel(model);
        AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
        System.out.println(response.getBody());
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }
}
