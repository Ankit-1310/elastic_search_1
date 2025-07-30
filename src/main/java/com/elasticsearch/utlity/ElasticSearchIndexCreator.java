package com.elasticsearch.utlity;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ElasticSearchIndexCreator {


    private static final String INDEX_NAME = "data_from_ankit";
    private static Logger logger =  LoggerFactory.getLogger(ElasticSearchIndexCreator.class);

    private ElasticsearchClient elasticsearchClient;

    @Autowired
    public ElasticSearchIndexCreator(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    @PostConstruct
    public void createIndexIfNotExists() {
        try {
            boolean indexExists = elasticsearchClient.indices()
                    .exists(e -> e.index(INDEX_NAME))
                    .value();

            if (!indexExists) {
                CreateIndexResponse response = elasticsearchClient.indices().create(c -> c
                        .index(INDEX_NAME)
                        .mappings(m -> m
                                .properties("id", p -> p.long_(v -> v))
                                .properties("name", p -> p.text(t -> t.analyzer("standard")))
                                .properties("email", p -> p.keyword(k -> k))
                                .properties("heighestEducation", p -> p.text(t -> t))
                                .properties("dob", p -> p.date(d -> d.format("strict_date_optional_time||epoch_millis")))
                                .properties("age", p -> p.integer(i -> i))
                        )
                );
                logger.info("Index created: " + response.acknowledged());
            } else {
                logger.info("Index already exists: " + INDEX_NAME);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
    }
}

