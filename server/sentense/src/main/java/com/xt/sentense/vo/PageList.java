package com.xt.sentense.vo;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * 分页Page， 自带的那个感觉不好用
 * @author XiangTao
 *
 */
public class PageList {
	private Object content; //将所有数据返回为List
	private int number; //当前第几页   返回当前页的数目。总是非负的
	private int size; //当前页面的大小
	private int totalPages; //返回分页总数。
	private int numberOfElements;   //返回当前页上的元素数。
	private long totalElements;    //返回元素总数。
	private boolean previousPage;  //上一页。
	private boolean firstPage;      //当前页是否为第一页。
	private boolean nextPage;      //如果有下一页。
	private boolean lastPage;       //当前页是否为最后一页。
	private boolean hasContent;     //数据是否有内容。
	
	public PageList(Page page){
		this.content = page.getContent();
		this.number = page.getNumber();
		this.size = page.getSize();
		this.totalPages = page.getTotalPages();
		this.numberOfElements = page.getNumberOfElements();
		this.totalElements = page.getTotalElements();
		this.previousPage = page.hasPrevious();
		this.firstPage = page.isFirst();
		this.nextPage = page.hasNext();
		this.lastPage = page.isLast();
		this.hasContent = page.hasContent();
	}
	
	public PageList(){
		
	}
	
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getNumberOfElements() {
		return numberOfElements;
	}
	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	public boolean isPreviousPage() {
		return previousPage;
	}
	public void setPreviousPage(boolean previousPage) {
		this.previousPage = previousPage;
	}
	public boolean isFirstPage() {
		return firstPage;
	}
	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}
	public boolean isNextPage() {
		return nextPage;
	}
	public void setNextPage(boolean nextPage) {
		this.nextPage = nextPage;
	}
	public boolean isLastPage() {
		return lastPage;
	}
	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
	public boolean isHasContent() {
		return hasContent;
	}
	public void setHasContent(boolean hasContent) {
		this.hasContent = hasContent;
	}
	@Override
	public String toString() {
		return "PageList [content=" + content + ", number=" + number + ", size=" + size + ", totalPages=" + totalPages
				+ ", numberOfElements=" + numberOfElements + ", totalElements=" + totalElements + ", previousPage="
				+ previousPage + ", firstPage=" + firstPage + ", nextPage=" + nextPage + ", lastPage=" + lastPage
				+ ", hasContent=" + hasContent + "]";
	}
}
