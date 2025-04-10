package kr.amc.amis.common.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pageable {

    private int pageIndex;
    private int pageSize;

    public Pageable() {
        this.pageIndex = 1;
        this.pageSize = 10;
    }

    public Pageable(int pageIndex, int pageSize) {
        if (pageIndex < 1) {
            throw new IllegalArgumentException("pageIndex must be greater than 0");
        }
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return (this.pageIndex - 1) * this.pageSize;
    }
    public int getLimit() {
        return this.pageSize;
    }
}
