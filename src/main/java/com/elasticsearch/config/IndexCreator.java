package com.elasticsearch.config;

import com.elasticsearch.document.DataFromAnkit;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.stereotype.Component;

@Component
public class IndexCreator {

    private static final Logger logger = LoggerFactory.getLogger(IndexCreator.class);

    @Autowired
    private ElasticsearchOperations operations;



    @PostConstruct
    public void createIndex() {
        try {
            IndexOperations indexOps = operations.indexOps(DataFromAnkit.class);
            if (!indexOps.exists()) {
                indexOps.create();
                indexOps.putMapping(indexOps.createMapping(DataFromAnkit.class));
                logger.info("Index created successfully.");
            } else {
                logger.info("Index already exists.");
            }
        } catch (Exception e) {
            logger.info("Failed To Create Index: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

