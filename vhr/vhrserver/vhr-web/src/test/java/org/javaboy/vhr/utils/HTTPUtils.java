package org.javaboy.vhr.utils;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class HTTPUtils {
    static class SkipHttpsUtil {
        // https://www.cnblogs.com/jtwbdm/p/11507121.html
        //绕过证书
        public static CloseableHttpClient wrapClient() {
            try {
                SSLContext ctx = SSLContext.getInstance("TLS");
                X509TrustManager tm = new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] arg0, String arg1) {
                    }

                    public void checkServerTrusted(X509Certificate[] arg0, String arg1) {
                    }
                };
                ctx.init(null, new TrustManager[]{tm}, null);
                SSLConnectionSocketFactory ssf = new SSLConnectionSocketFactory(
                        ctx, NoopHostnameVerifier.INSTANCE);
                return HttpClients.custom()
                        .setSSLSocketFactory(ssf).build();
            } catch (Exception e) {
                return HttpClients.createDefault();
            }
        }

        public static void main(String[] args) {

        }

    }

    public static CloseableHttpClient GetHttpClient(Boolean verify, int func) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        if (verify) {
            return HttpClients.createDefault();
        }
        // https://zhuanlan.zhihu.com/p/83224432
        switch (func) {
            case 1:
                return SkipHttpsUtil.wrapClient();
            default:
                return HttpClients.custom().setSSLSocketFactory(new SSLConnectionSocketFactory(new SSLContextBuilder()
                        .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                        .build())).build();

        }
    }
}
