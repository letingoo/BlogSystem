package util;

/**
 * Created by letingoo on 2016/11/5.
 * 分页的工具类
 */
public class PageParam {

    /*
     * pageNo从1开始
     */

    private int start, size;

    public PageParam(int pageNo, int pageSize) {

        start = (pageNo - 1) * pageSize;
        size = pageSize;
    }


    public int getStart() {
        return start;
    }


    public int getSize() {
        return size;
    }
}
