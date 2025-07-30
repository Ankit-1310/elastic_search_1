package com.elasticsearch.service;


import com.elasticsearch.repository.DataFromAnkitRepository;
import org.springframework.stereotype.Service;

@Service
public class DataFromAnkitService {
    public DataFromAnkitService(DataFromAnkitRepository dataFromAnkitRepository) {
    }
}
