package cc.kokoko.server.common.model;

/**
 * 描述：分页工具类
 *
 * @author HHang
 * @version V1.1
 */
public class PageUtil {
    // 定义如下分页变量

    /**
     * 定义没页要显示的记录数，默认是20条每页 *
     */
    private int lineSize = 20;

    /**
     * 定义一个当前是第几页 *
     */
    private int currentPage = 1;

    /**
     * 记录从那里开始进行分页 *
     */
    private int startRecord;

    /**
     * 计算出总页数 *
     */
    private int pageSize;

    /**
     * 总记录数 / 每页显示的记录数 *
     */
    private int allRecorders;

    /**
     * 加入一个检索标记 *
     */
    private int searchFlag;


    public int getStartRecord() {
        return startRecord;
    }

    public void setStartRecord(int startRecord) {
        this.startRecord = startRecord;
    }

    public int getLineSize() {
        return lineSize;
    }

    public void setLineSize(int lineSize) {
        this.lineSize = lineSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage == 0) {
            currentPage = 1;
        }

        startRecord = (currentPage - 1) * lineSize;

        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {

        this.pageSize = pageSize;
    }


    public int getAllRecorders() {
        return allRecorders;
    }

    public void setAllRecorders(int allRecorders) {
        if (allRecorders != 0) {
            pageSize = allRecorders % lineSize == 0 ? allRecorders / lineSize : allRecorders / lineSize + 1;
        }
        if (pageSize == 0) {
            pageSize = 1;
        }
        if (allRecorders == 0) {
            pageSize = 1;
        }

        this.allRecorders = allRecorders;
    }

    public int getSearchFlag() {
        return searchFlag;
    }

    public void setSearchFlag(int searchFlag) {
        this.searchFlag = searchFlag;
    }
}
