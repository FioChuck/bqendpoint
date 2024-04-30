package com.google.workstreams.bqendpoint.model;

import java.io.Serializable;

public class BigQueryRequest implements Serializable {
    private int query;

    public int getQuery() {
        return query;
    }

    public void setQuery(int query) {
        this.query = query;
    }

}