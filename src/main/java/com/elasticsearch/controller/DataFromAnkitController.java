package com.elasticsearch.controller;

import com.elasticsearch.service.DataFromAnkitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data_from_ankit")
public class DataFromAnkitController {
	
	private DataFromAnkitService dataFromAnkitService;

	@Autowired
    public DataFromAnkitController(DataFromAnkitService dataFromAnkitService) {
        this.dataFromAnkitService = dataFromAnkitService;
    }

}
