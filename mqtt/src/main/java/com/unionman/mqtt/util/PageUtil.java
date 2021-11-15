package com.unionman.mqtt.util;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PageUtil<T>{
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCurrentPages() {
		return currentPages;
	}

	public void setCurrentPages(int currentPages) {
		this.currentPages = currentPages;
	}

	/**需要传入分页的数据集合*/
    private List<T> dataList;
    public List<T> getResultsList() {
		return resultsList;
	}

	public void setResultsList(List<T> resultsList) {
		this.resultsList = resultsList;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public boolean isHasPrevious() {
		return hasPrevious;
	}

	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public boolean isHasFirst() {
		return hasFirst;
	}

	public void setHasFirst(boolean hasFirst) {
		this.hasFirst = hasFirst;
	}

	public boolean isHasLast() {
		return hasLast;
	}

	public void setHasLast(boolean hasLast) {
		this.hasLast = hasLast;
	}

	public List<T> getDataList() {
		return dataList;
	}

	/**当前页号*/
    private int currentPages;
    /**每页条数*/
    private int size;
    /**分页后的返回集合*/
    private List<T> resultsList;
    /**页数*/
    private int pages;
    /**记录条数*/
    private int recordCount;
    /**是否有前一页*/
    private boolean hasPrevious = false;
    /**是否有下一页*/
    private boolean hasNext = false;
    /**是否有前一页*/
    private boolean hasFirst = false;
    /**是否有下一页*/
    private boolean hasLast = false;

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
        // 计算条数
        recordCount = dataList.size();
        // 计算页数
        pages=(int)Math.ceil(Double.valueOf(recordCount)/Double.valueOf(size));
        //是否有下一页
        if (currentPages>1){
        	hasPrevious=true;
        	hasFirst=true;
        }else {
        	hasPrevious=false;
        	hasFirst=false;
        }
        if (currentPages<pages){
        	hasNext=true;
        	hasLast=true;
        }else {
        	hasNext=false;
        	hasLast=false;
        }

        // 筛选工作
        resultsList = new ArrayList<T>();
        for (int i = (currentPages - 1) * size; i < currentPages * size && i < recordCount; i++) {
            resultsList.add(dataList.get(i));
        }

    }
}
