package com.jackson.result;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;
import java.util.Objects;

@JsonFormat
public class PagingResult {
    private Long total;
    private List list;

    public PagingResult() {
    }

    public PagingResult(Long total, List list) {
        this.total = total;
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PagingResult that = (PagingResult) o;
        return Objects.equals(total, that.total) && Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, list);
    }

    @Override
    public String toString() {
        return "PagingResult{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}
