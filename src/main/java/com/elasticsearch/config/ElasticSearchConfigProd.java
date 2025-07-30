//package com.elasticsearch.config;
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.json.jackson.JacksonJsonpMapper;
//import co.elastic.clients.transport.ElasticsearchTransport;
//import co.elastic.clients.transport.rest_client.RestClientTransport;
//import jakarta.validation.constraints.NotNull;
//import org.apache.http.HttpHost;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.apache.http.ssl.SSLContextBuilder;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//
//import javax.net.ssl.SSLContext;
//import java.io.File;
//
//@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.elasticsearch.repository")
//public class ElasticSearchConfigProd {
//
//    @Value("${spring.elasticsearch.uris}")
//    private String elasticUrl;
//
//    @Value("${spring.elasticsearch.username}")
//    private String username;
//
//    @Value("${spring.elasticsearch.password}")
//    private String password;
//
//    @Value("${spring.elasticsearch.ssl.cacert-path}") // path to your PEM or P12 file
//    private String caCertPath;
//
//    @Bean
//    public ElasticsearchClient elasticsearchClient() throws Exception {
//
//        SSLContext sslContext = SSLContextBuilder.create()
//                .loadTrustMaterial(new File(caCertPath))  // e.g., /etc/elasticsearch/certs/http_ca.crt
//                .build();
//
//        RestClientBuilder builder = RestClient.builder(HttpHost.create(elasticUrl))
//                .setHttpClientConfigCallback(httpClientBuilder ->
//                        httpClientBuilder
//                                .setSSLContext(sslContext)
//                                .setDefaultCredentialsProvider(credentialsProvider())
//                );
//
//        ElasticsearchTransport transport = new RestClientTransport(
//                builder.build(),
//                new JacksonJsonpMapper()
//        );
//
//        return new ElasticsearchClient(transport);
//    }
//
//    @NotNull
//    private CredentialsProvider credentialsProvider() {
//        BasicCredentialsProvider provider = new BasicCredentialsProvider();
//        provider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
//        return provider;
//    }
//}
