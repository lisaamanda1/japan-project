package com.hx.bean;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
* @ClassName: PageVO 
* @Description: TODO(Description) 
* @author zhang.shaohua
* @date 2013-4-29 PM1:35:01 
*
 */
public class PageVO<T> {
	
	/**
	 * the page that you want to go.
	 */
	private Integer page = 1;
	
	/**
	 * the number of records that will be showed in one page.
	 */
	private Integer pageSize = 20;
	
	/**
	 *  the number of  all records that you queried.
	 */
	private Integer total = 0;
	
	/**
	 * the all records that will be showed in current page.
	 */
	private List<T> rows = null;
	
	/**
	 * whether get the all records' count
	 */
	private boolean needCount = false;
	
	/**
	 * 
	 */
	private Integer maxShowlength = 11;
	/**
	 * 底部统计
	 */
	private Object footer;
	/**
	 * 
	* <p>Title: </p> 
	* <p>Description: </p>
	 */
	public PageVO() {}
	/**
	 * 
	 * @param page  the page that you want to go.
	 * @param pageSize  the number of records that will be showed in one page.
	 */
	public PageVO(Integer page, Integer pageSize) {				
		this.page = page < 1 ? this.page : page;
		this.pageSize = pageSize < 1 ? this.pageSize : pageSize;
	}
	
	/**
	 * 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param page_
	* @param pageSize_
	 */
	public PageVO(Object page_, Object pageSize_) {			
		int page = null == page_ || "".equals(page_.toString().trim()) ? this.page : Integer.parseInt(page_.toString().trim());
		int pageSize = null == pageSize_ || "".equals(pageSize_.toString().trim()) ? this.pageSize : Integer.parseInt(pageSize_.toString().trim());
		this.page = page < 1 ? this.page : page;
		this.pageSize = pageSize < 1 ? this.pageSize : pageSize;
	}
	
	/**
	 * 
	 * @param page  the page that you want to go.
	 * @param pageSize  the number of records that will be showed in one page.
	 * @param allRecord    all records that you queried.
	 */
	public PageVO(Integer page, Integer pageSize, List<T> allRows) {				
		this.page = page < 1 ? this.page : page;
		this.pageSize = pageSize < 1 ? this.pageSize : pageSize;
		
		this.total = allRows.size();
		int endIndex = this.page*this.pageSize > this.total ? this.total : this.page*this.pageSize;
		this.rows = new ArrayList<T>(allRows.subList((this.page-1)*this.pageSize, endIndex));		
	}
	
	/**
	 * 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param page_
	* @param pageSize_
	* @param allRecords
	 */
	public PageVO(Object page_, Object pageSize_, List<T> allRows) {		
		int page = null == page_ || "".equals(page_.toString().trim()) ? this.page : Integer.parseInt(page_.toString().trim());
		int pageSize = null == pageSize_ || "".equals(pageSize_.toString().trim()) ? this.pageSize : Integer.parseInt(pageSize_.toString().trim());
		this.page = page < 1 ? this.page : page;
		this.pageSize = pageSize < 1 ? this.pageSize : pageSize;
		
		this.total = allRows.size();
		int endIndex = this.page*this.pageSize > this.total ? this.total : this.page*this.pageSize;
		this.rows = new ArrayList<T>(allRows.subList((this.page-1)*this.pageSize, endIndex));		
	}
	/**
	 * 
	 * @param page  the page that you want to go.
	 * @param pageSize   the number of records that will be showed in one page.
	 * @param total  the number of  all records that you queried.
	 * @param rows     the all records that will be showed in current page.
	 */
	public PageVO(Integer page, Integer pageSize, Integer total, List<T> rows) {
		this.page = page < 1 ? this.page : page;
		this.pageSize = pageSize < 1 ? this.pageSize : pageSize;
		
		this.total = total;
		this.rows = rows;
	}
	
	/**
	 * 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param page_
	* @param pageSize_
	* @param total
	* @param rows
	 */
	public PageVO(Object page_, Object pageSize_, Integer total, List<T> rows) {
		Integer page = null == page_ || "".equals(page_.toString().trim()) ? this.page : Integer.parseInt(page_.toString().trim());
		Integer pageSize = null == pageSize_ || "".equals(pageSize_.toString().trim()) ? this.pageSize : Integer.parseInt(pageSize_.toString().trim());
		this.page = page < 1 ? this.page : page;
		this.pageSize = pageSize < 1 ? this.pageSize : pageSize;
		
		this.total = total;
		this.rows = rows;
	}
	
    /**
     * get the page that you want to go.
     * @return int
     */
	public Integer getPage() {
		return page;
	}
	/**
     *  the number of all pages
     * @return int
     */
	public Integer getTotalPage() {
		return this.total%this.pageSize == 0 ? this.total/this.pageSize : this.total/this.pageSize+1;
	}
	/**
     *  get  the all records that will be showed in current page.
     * @return List<Object>
     */
	public List<T> getRows() {
		return rows;
	}
	
	/**
	 * 
	* @Title: setrows 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param rows    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	  /**
     * get the number of records that will be showed in one page.
     * @return int
     */
	public Integer getPageSize() {
		return pageSize;
	}
	  /**
     * get all records that you queried.
     * @return int
     */
	public Integer getTotal() {
		return total;
	}
	/**
	 * 
	* @Title: settotal 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param total    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	/**
	 * 
	* @Title: isNeedCount 
	* @Description: TODO() 
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean isNeedCount() {
		return needCount;
	}

	/**
	 * 
	* @Title: setNeedCount 
	* @Description: TODO(set whether need a count) 
	* @param @param needCount    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void setNeedCount(boolean needCount) {
		this.needCount = needCount;
	}
	/**
	 * 
	 * @return
	 */
	public Object getFooter() {
		return footer;
	}
	/**
	 * 
	 * @param footer
	 */
	public void setFooter(Object footer) {
		this.footer = footer;
	}
	/**
	* @Title: getShowPage 
	* @Description: TODO( get the page number that will be showed.) 
	* @param @return   params
	* @return List<Integer>    returnType
	* @throws
	 */
	public List<Integer> getShowPage() {
		List<Integer> showPage = new ArrayList<Integer>();
		int beginNum = 0;
		int endNum = 0;
		int totalPage = this.getTotalPage();
		if(page <= maxShowlength/2+1){
			beginNum = 1;
			endNum = totalPage < maxShowlength ? totalPage : maxShowlength;
		}else{
			beginNum = 1 + (page - (maxShowlength/2+1));
			endNum = maxShowlength + (page - (maxShowlength/2+1));
			if(totalPage < endNum){
				endNum = totalPage;
				beginNum = totalPage - maxShowlength;
				beginNum = beginNum < 1 ? 1 : beginNum;
			}else{
				beginNum = 1 + (page - (maxShowlength/2+1));
			}
		}	
		for(int i = beginNum; i <= endNum; i++){
			showPage.add(i);
		}
		return showPage;
	}
	
	public String toString(){
//		return JSON.toJSONString(this);
		ObjectMapper mapper = new ObjectMapper();
		String str = null;
		try {
			str = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return str;
	}
}
