package com.ego.commons.pojo;

import java.util.List;

/**
 * EasyUI datagrid数据模板
 */
public class EasyUIDatagrid {
    private long total;
    private List<?> rows;

    public EasyUIDatagrid(long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public EasyUIDatagrid() {
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<? extends Object> getRows() {
        return rows;
    }

    public void setRows(List<? extends Object> rows) {
        this.rows = rows;
    }
}
