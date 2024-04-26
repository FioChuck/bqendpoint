package com.google.workstreams.bqendpoint;

import java.io.Serializable;

public class Request implements Serializable {
    private int left;
    private int right;

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }
}