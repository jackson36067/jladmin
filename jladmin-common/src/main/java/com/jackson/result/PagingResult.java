package com.jackson.result;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

@JsonFormat
public class PagingResult {
    private Integer total;
    private List list;

    public PagingResult() {
    }

    public PagingResult(Integer total, List list) {
        this.total = total;
        this.list = list;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
