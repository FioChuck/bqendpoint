package com.google.workstreams.bqendpoint.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.workstreams.bqendpoint.model.BigQueryRequest;
import com.google.workstreams.bqendpoint.service.BigQueryService;

@RestController
public class BigQueryController {

    @PostMapping("/storage")
    public String getStorageDescription(@RequestBody BigQueryRequest request) throws Exception {

        BigQueryService bigQueryService = new BigQueryService();

        String inputQuery = "SELECT (sensor_daily.dt ) AS sensor_daily_dt_date, " +
                "MAX(sensor_daily.max_temp_indoor) AS sensor_daily_max_temp_indoor, " +
                "AVG(weather_daily.avg_temp_outdoor) AS weather_daily_avg_temp_outdoor " +
                "FROM `cf-data-analytics.silver.weather_daily`  AS weather_daily " +
                "LEFT JOIN `cf-data-analytics.silver.sensor_daily`  AS sensor_daily ON weather_daily.dt = sensor_daily.dt "
                +
                "GROUP BY " +
                "1 " +
                "ORDER BY " +
                "1 DESC LIMIT 10 ";

        bigQueryService.setQuery(inputQuery);

        String output = bigQueryService.executeQuery();

        System.out.println("test");
        return output;
    }

    @PostMapping("/looker")
    public String getLookerDescription(@RequestBody BigQueryRequest request) {

        System.out.println("test");
        return "done with looker api";
    }

}
