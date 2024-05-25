package com.manage.field.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.PageUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = -1L;
    public static final int RAINBOW_NUM = 5;
    private Integer pageNo = 1;
    private Integer pageSize = 20;
    private Integer totalPage = 0;
    private Integer totalRows = 0;
    private List<T> rows;

    public PageResult() {
    }

    public PageResult(IPage<T> page) {
        this.setRows(page.getRecords());
        this.setTotalRows(Convert.toInt(page.getTotal()));
        this.setPageNo(Convert.toInt(page.getCurrent()));
        this.setPageSize(Convert.toInt(page.getSize()));
        this.setTotalPage(PageUtil.totalPage(Convert.toInt(page.getTotal()), Convert.toInt(page.getSize())));
    }

    public PageResult(IPage<T> page, List<T> t) {
        this.setRows(t);
        this.setTotalRows(Convert.toInt(page.getTotal()));
        this.setPageNo(Convert.toInt(page.getCurrent()));
        this.setPageSize(Convert.toInt(page.getSize()));
        this.setTotalPage(PageUtil.totalPage(Convert.toInt(page.getTotal()), Convert.toInt(page.getSize())));
    }

    public PageResult(Page<T> page, List<T> t) {
        this.setRows(t);
        this.setTotalRows(Convert.toInt(page.getTotal()));
        this.setPageNo(Convert.toInt(page.getCurrent()));
        this.setPageSize(Convert.toInt(page.getSize()));
        this.setTotalPage(PageUtil.totalPage(Convert.toInt(page.getTotal()), Convert.toInt(page.getSize())));
    }

    public Integer getPageNo() {
        return this.pageNo;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Integer getTotalPage() {
        return this.totalPage;
    }

    public Integer getTotalRows() {
        return this.totalRows;
    }

    public List<T> getRows() {
        return this.rows;
    }

    public PageResult<T> setPageNo(final Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public PageResult<T> setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public PageResult<T> setTotalPage(final Integer totalPage) {
        this.totalPage = totalPage;
        return this;
    }

    public PageResult<T> setTotalRows(final Integer totalRows) {
        this.totalRows = totalRows;
        return this;
    }

    public PageResult<T> setRows(final List<T> rows) {
        this.rows = rows;
        return this;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PageResult)) {
            return false;
        } else {
            PageResult<?> other = (PageResult)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label71: {
                    Object this$pageNo = this.getPageNo();
                    Object other$pageNo = other.getPageNo();
                    if (this$pageNo == null) {
                        if (other$pageNo == null) {
                            break label71;
                        }
                    } else if (this$pageNo.equals(other$pageNo)) {
                        break label71;
                    }

                    return false;
                }

                Object this$pageSize = this.getPageSize();
                Object other$pageSize = other.getPageSize();
                if (this$pageSize == null) {
                    if (other$pageSize != null) {
                        return false;
                    }
                } else if (!this$pageSize.equals(other$pageSize)) {
                    return false;
                }

                label57: {
                    Object this$totalPage = this.getTotalPage();
                    Object other$totalPage = other.getTotalPage();
                    if (this$totalPage == null) {
                        if (other$totalPage == null) {
                            break label57;
                        }
                    } else if (this$totalPage.equals(other$totalPage)) {
                        break label57;
                    }

                    return false;
                }

                Object this$totalRows = this.getTotalRows();
                Object other$totalRows = other.getTotalRows();
                if (this$totalRows == null) {
                    if (other$totalRows != null) {
                        return false;
                    }
                } else if (!this$totalRows.equals(other$totalRows)) {
                    return false;
                }

                Object this$rows = this.getRows();
                Object other$rows = other.getRows();
                if (this$rows == null) {
                    if (other$rows == null) {
                        return true;
                    }
                } else if (this$rows.equals(other$rows)) {
                    return true;
                }

                return false;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PageResult;
    }

    public int hashCode() {
        int result = 1;
        Object $pageNo = this.getPageNo();
        result = result * 59 + ($pageNo == null ? 43 : $pageNo.hashCode());
        Object $pageSize = this.getPageSize();
        result = result * 59 + ($pageSize == null ? 43 : $pageSize.hashCode());
        Object $totalPage = this.getTotalPage();
        result = result * 59 + ($totalPage == null ? 43 : $totalPage.hashCode());
        Object $totalRows = this.getTotalRows();
        result = result * 59 + ($totalRows == null ? 43 : $totalRows.hashCode());
        Object $rows = this.getRows();
        result = result * 59 + ($rows == null ? 43 : $rows.hashCode());
        return result;
    }

    public String toString() {
        return "PageResult(pageNo=" + this.getPageNo() + ", pageSize=" + this.getPageSize() + ", totalPage=" + this.getTotalPage() + ", totalRows=" + this.getTotalRows() + ", rows=" + this.getRows() + ")";
    }
}
