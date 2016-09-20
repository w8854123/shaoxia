package com.shaoxia.bean;

import java.util.List;

/**
 * dataTables返回数据
 * @author wu885
 *
 * @param <T>
 */
public class DataTablesResult<T> {
	/** 绘制计数器。这个是用来确保Ajax从服务器返回的是对应的（Ajax是异步的，因此返回的顺序是不确定的）。 要求在服务器接收到此参数后再返回 */
	private Long draw;
	/** 总记录数 */
	private Long recordsTotal;
	/** 过滤后总记录数 */
	private Long recordsFiltered;
	/** 数据源 */
	private List<T> data;
	
	public Long getDraw() {
		return draw;
	}
	public void setDraw(Long draw) {
		this.draw = draw;
	}
	public Long getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public Long getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
}
