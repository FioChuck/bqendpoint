package com.google.workstreams.bqendpoint.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.google.cloud.bigquery.*;
import com.google.gson.Gson;

@Service
public class BigQueryService {

    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @SuppressWarnings("null")
    public String executeQuery() throws Exception {
        BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(this.query)
                .setUseLegacySql(false)
                .build();

        JobId jobId = JobId.of(UUID.randomUUID().toString());
        Job queryJob = bigquery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());

        queryJob = queryJob.waitFor();

        if (queryJob == null) {
            throw new RuntimeException("Job no longer exists");
        } else if (queryJob.getStatus().getError() != null) {
            throw new RuntimeException(queryJob.getStatus().getError().toString());
        }

        TableResult result = queryJob.getQueryResults();

        List<Map<String, Object>> resultList = new ArrayList<>();
        for (FieldValueList row : result.iterateAll()) {
            Map<String, Object> rowMap = new HashMap<>();
            for (Field field : result.getSchema().getFields()) {
                rowMap.put(field.getName(), row.get(field.getName()).getValue());
            }
            resultList.add(rowMap);
        }

        Gson gson = new Gson();
        String jsonResult = gson.toJson(resultList);

        return jsonResult;
    }

}