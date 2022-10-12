package com.allst.micro.utils;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.util.Map;

/**
 * @author Hutu
 * @since 2022-10-12 下午 07:55
 */
public class HttpUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

    private static CloseableHttpClient httpClient = HttpClients.createDefault();
    private static CloseableHttpClient noopSslHttpClient = buildNoSSLAndRetryHandlerHttpClient();

    public static HttpEntity get(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();

        return entity;
    }

    public static HttpEntity post(String url, String body, ContentType contentType, boolean withoutSslVerify) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        HttpEntity stringEntity = new StringEntity(body, contentType);
        httpPost.setEntity(stringEntity);
        final CloseableHttpClient client = withoutSslVerify ? noopSslHttpClient : httpClient;
        CloseableHttpResponse response = client.execute(httpPost);
        return response.getEntity();
    }

    public static HttpEntity sslPost(String url, String body, ContentType contentType, SSLContext sslContext) throws IOException {
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                sslContext,
                new String[]{"TLSv1"},
                null,
                new DefaultHostnameVerifier());

        BasicHttpClientConnectionManager connManager = new BasicHttpClientConnectionManager(
                RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.getSocketFactory())
                        .register("https", sslConnectionSocketFactory)
                        .build(),
                null,
                null,
                null
        );
        HttpPost httpPost = new HttpPost(url);
        HttpEntity stringEntity = new StringEntity(body, contentType);
        httpPost.setEntity(stringEntity);
        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .build();
        HttpResponse response = httpClient.execute(httpPost);
        return response.getEntity();
    }

    public static HttpEntity postXml(String url, Map<String, String> params) throws IOException {
        return post(url, XmlUtils.mapToXml(params), ContentType.create(ContentType.APPLICATION_XML.getMimeType(), Consts.UTF_8), false);
    }

    public static HttpEntity postXmlWithoutSSlVerify(String url, Map<String, String> params) throws IOException {
        return post(url, XmlUtils.mapToXml(params), ContentType.create(ContentType.APPLICATION_XML.getMimeType(), Consts.UTF_8), true);
    }

    public static HttpEntity sslPostXml(String url, Map<String, String> params, SSLContext sslContext) throws IOException {
        return sslPost(url, XmlUtils.mapToXml(params), ContentType.create(ContentType.APPLICATION_XML.getMimeType(), Consts.UTF_8), sslContext);
    }

    private static CloseableHttpClient buildNoSSLAndRetryHandlerHttpClient() {
        return HttpClients.custom()
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .setRetryHandler(new HttpRequestRetryHandler() {
                    @Override
                    public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                        if (executionCount > 3) {
                            LOGGER.warn("Maximum tries reached for client http pool", exception);
                            return false;
                        }
                        if (exception instanceof NoHttpResponseException) {
                            LOGGER.warn("No response from server on " + executionCount + " call");
                            return true;
                        }
                        return false;
                    }
                }).build();
    }
}
