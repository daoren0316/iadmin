package cc.kokoko.server.common.model;

/**
 * 分页基本信息对象(包括页面大小,页数等)
 * 
 */
public class PageInfo {
	private int pageSize = 20; // 页大小
	private int totalCount; // 总记录数
	private int toPage = 1; // 页数

	private boolean isComputeTotal = true; // 是否计算获取总记录数

	/**
	 * @return the isComputeTotal
	 */
	public boolean isComputeTotal() {
		return isComputeTotal;
	}

	/**
	 * @param isComputeTotal
	 *            the isComputeTotal to set
	 */
	public void setComputeTotal(boolean isComputeTotal) {
		this.isComputeTotal = isComputeTotal;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the toPage
	 */
	public int getToPage() {
		return toPage;
	}

	/**
	 * @param toPage
	 *            the toPage to set
	 */
	public void setToPage(int toPage) {
		this.toPage = toPage;
	}

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount
	 *            the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 根据分页大小计算出当前起始行
	 * 
	 * @return
	 */
	public int getBeginRow() {
		return (toPage - 1) * pageSize;
	}

	/**
	 * 根据当前记录数及页面
	 * 
	 * @return
	 */
	public int getTotalPage() {
		return pageSize > 0 && totalCount > 0 ? (totalCount % pageSize > 0 ? totalCount
				/ pageSize + 1
				: totalCount / pageSize)
				: 0;
	}
}
