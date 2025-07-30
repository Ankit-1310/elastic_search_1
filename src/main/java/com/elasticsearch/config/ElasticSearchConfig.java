package com.elasticsearch.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import jakarta.validation.constraints.NotNull;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.net.ssl.SSLContext;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Configuration
@EnableElasticsearchRepositories(basePackages = "com.elasticsearch.repository")
public class ElasticSearchConfig  {
    @Value("${spring.elasticsearch.uris}")
    private String elasticUrl;

    @Value("${spring.elasticsearch.username}")
    private String username;

    @Value("${spring.elasticsearch.password}")
    private String password;
    @Bean
    public RestClient client() {
        return RestClient.builder(HttpHost.create(elasticUrl)).build();
    }
//    @Bean
//    public ElasticsearchClient elasticsearchClient() throws Exception {
//        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY,
//                new UsernamePasswordCredentials("elastic", "your_password"));
//
//        SSLContext sslContext = SSLContexts.custom()
//                .loadTrustMaterial(null, (certificate, authType) -> true) // Trust all certs
//                .build();
//
//        RestClient restClient = RestClient.builder(
//                        new HttpHost("localhost", 9200, "https"))
//                .setHttpClientConfigCallback(httpClientBuilder ->
//                        httpClientBuilder.setSSLContext(sslContext)
//                                .setDefaultCredentialsProvider(credentialsProvider)
//                )
//                .build();
//
//        ElasticsearchTransport transport = new RestClientTransport(
//                restClient, new JacksonJsonpMapper());
//
//        return new ElasticsearchClient(transport);
//    }


//    private String getBasicAuthHeader(String username, String password) {
//        String credentials = username + ":" + password;
//        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
//    }

//    @Bean
//    public ElasticsearchClient elasticsearchClient() throws Exception {
//        final SSLContext sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(null, new TrustManager[]{new X509TrustManager() {
//            public void checkClientTrusted(X509Certificate[] xcs, String string) {}
//            public void checkServerTrusted(X509Certificate[] xcs, String string) {}
//            public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
//        }}, new SecureRandom());
//
//        RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "https"))
//                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder
//                        .setSSLContext(sslContext)
//                        .setSSLHostnameVerifier((hostname, session) -> true)
//                );
//
//        ElasticsearchTransport transport = new RestClientTransport(
//                builder.build(), new JacksonJsonpMapper());
//
//        return new ElasticsearchClient(transport);
//    }


    @NotNull
    private CredentialsProvider credentialsProvider() {
        BasicCredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        return provider;
    }

}
