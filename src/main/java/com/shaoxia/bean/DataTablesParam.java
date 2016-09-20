package com.shaoxia.bean;

/**
 * dataTables接收参数
 * @author wu885
 *
 */
public class DataTablesParam {
	
	//绘制计数器。这个是用来确保Ajax从服务器返回的是对应的（Ajax是异步的，因此返回的顺序是不确定的）。 要求在服务器接收到此参数后再返回
	private Long draw;
	//第一条数据的起始位置，比如0代表第一条数据
	private Integer start;
	//每页显示的条数，这个数字会等于返回的 data集合的记录数，可能会大于因为服务器可能没有那么多数据。
	private Integer length;
//	//查询字段
//	private String searchColumn;
//	//查询条件
//	private String searchValue;
//	//排序字段
//	private String orderColumn;
//	//排序方式，desc 降序 asc升序
//	private String orderDir;
	
	//查询条件
	private String search;
	//排序字段
	private String order;
	
	public Long getDraw() {
		return draw;
	}
	public void setDraw(Long draw) {
		this.draw = draw;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	@Override
	public String toString() {
		return "DataTablesParam [draw=" + draw + ", start=" + start + ", length=" + length + ", search=" + search
				+ ", order=" + order + "]";
	}
	
}
