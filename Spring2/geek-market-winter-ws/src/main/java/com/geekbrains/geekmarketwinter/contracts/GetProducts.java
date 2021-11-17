package com.geekbrains.geekmarketwinter.contracts;

import java.io.Serializable;

public class GetProducts implements Serializable {

    public int pageNumber;

    public int pageSize;

    public GetProducts() {
    }

    public GetProducts(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
