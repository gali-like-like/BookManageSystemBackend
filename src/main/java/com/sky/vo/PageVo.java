package com.sky.vo;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "分页数据")
public class PageVo<T> {
	@Schema(description = "总记录数")
	private Integer totalRecord;//总记录数
	@Schema(description = "总页数")
	private Integer totalPage;//总页数
	@Schema(description = "当前页数据")
	private List<T> currentPageData;//当前页数据
	
	private PageVo() {}
	
	private PageVo(Integer totalRecord, Integer totalPage, List<T> currentPageData) {
		super();
		this.totalRecord = totalRecord;
		this.totalPage = totalPage;
		this.currentPageData = currentPageData;
	}

	public static <T> PageVo getPageVo(Integer total,Integer totalPage,List<T> data) {
		return new PageVo(total, totalPage, data);
	}
	
	public Integer getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getCurrentPageData() {
		return currentPageData;
	}
	public void setCurrentPageData(List<T> currentPageData) {
		this.currentPageData = currentPageData;
	}
	
	@Override
	public String toString() {
		return "PageBookVo [totalRecord=" + totalRecord + ", totalPage=" + totalPage + ", currentPageData="
				+ currentPageData + "]";
	}
}
