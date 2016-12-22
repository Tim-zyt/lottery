package com.sf.lottery.web.method;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author wujiang
 * @version 1.0.0.
 * @date 2016/12/21
 */
public class getWeiXinToken {
    boolean isProxy = false;
    String proxyHost = "10.118.214.126";
    int proxyPort = 3128;

    public String sendGet(String url, String param) throws Exception {
        HttpGet httpget = new HttpGet(new StringBuilder().append(url).append("?").append(param).toString());
        //配置请求的超时设置
        RequestConfig requestConfig = null;
        CloseableHttpClient httpclient = buildSSLCloseableHttpClient();

        httpget.setConfig(requestConfig);
        try {
            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            String jsonStr = EntityUtils.toString(entity,"utf-8");
            return jsonStr;
        } finally {
            httpget.releaseConnection();
        }
    }

    private CloseableHttpClient buildSSLCloseableHttpClient() throws Exception {


        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
            //信任所有
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        }).build();
        //ALLOW_ALL_HOSTNAME_VERIFIER:这个主机名验证器基本上是关闭主机名验证的,实现的是一个空操作，并且不会抛出javax.net.ssl.SSLException异常。
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1"}, null,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        if (isProxy) {
            HttpRoutePlanner routePlanner = new HttpRoutePlanner() {

                @Override
                public HttpRoute determineRoute(HttpHost httpHost, org.apache.http.HttpRequest httpRequest, HttpContext httpContext) throws
                        HttpException {
                    return new HttpRoute(httpHost, null, new HttpHost(proxyHost, proxyPort),
                            "https".equalsIgnoreCase(httpHost.getSchemeName()));
                }

            };
            return HttpClients.custom()
                    .setRoutePlanner(routePlanner).setSSLSocketFactory(sslsf)
                    .build();
        } else {
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        }

    }
}
