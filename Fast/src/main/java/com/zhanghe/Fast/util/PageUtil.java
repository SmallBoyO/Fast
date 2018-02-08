package com.zhanghe.Fast.util;

import java.util.List;

import com.google.gson.Gson;
import com.zhanghe.Fast.entity.User;
/**  
 * PageUtil
 *   
 * @author Clevo  
 * @date 2018/1/9 21:23
 */  
public class PageUtil<T> {
	
    private Long correntPage;
    private Long pageSize;
    private Long total;
    
    private List<T> result;
    
    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public Long getCorrentPage() {
        return correntPage;
    }

    public void setCorrentPage(Long correntPage) {
        this.correntPage = correntPage;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
    
	public ReturnValue<T> toReturnValue(int ret){
		ReturnValue<T> returnValue = new ReturnValue<T>(1,"");
		returnValue.setPage(this);
		return returnValue;
	}
    
}
