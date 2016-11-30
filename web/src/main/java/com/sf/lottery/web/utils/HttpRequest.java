package com.sf.lottery.web.utils;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/11/29.
 */
@Component
public class HttpRequest {
    @Value("${http.isProxy}")
    private boolean isProxy;
    @Value("${http.proxyHost}")
    private String proxyHost;
    @Value("${http.proxyPort}")
    private int proxyPort;

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果content的json串
     */
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
                public HttpRoute determineRoute(HttpHost httpHost, org.apache.http.HttpRequest httpRequest, HttpContext httpContext) throws HttpException {
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
