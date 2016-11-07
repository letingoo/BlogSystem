package util;

/**
 * Created by letingoo on 2016/11/5.
 * 分页的工具类
 */
public class PageParam {

    /*
     * pageNo从1开始
     */

    private int start, end;

    public PageParam(int pageNo, int pageSize) {

        start = (pageNo - 1) * pageSize;
        end = pageNo * pageSize;
    }


    public int getStart() {
        return start;
    }


    public int getEnd() {
        return end;
    }
}
