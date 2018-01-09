package com.zhanghe.Fast.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.zhanghe.Fast.util.PageUtil;
/**  
 * PageVO
 *   
 * @author Clevo  
 * @date 2018/1/9 21:23
 */  
@ApiModel
public class PageVO<T> {
	
	@ApiModelProperty(value="当前页")
	private Long correntPage;
	@ApiModelProperty(value="每页数据条数")
    private Long pageSize;
    
	public Long getCorrentPage() {
		return correntPage;
	}
	public void setCorrentPage( Long correntPage ) {
		this.correntPage = correntPage;
	}
	public Long getPageSize() {
		return pageSize;
	}
	public void setPageSize( Long pageSize ) {
		this.pageSize = pageSize;
	}
    
	public PageUtil<T> toPageUtil(){
		PageUtil<T> page = new PageUtil<T>();
		page.setCorrentPage(correntPage);
		page.setPageSize(pageSize);
		return page;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("PageVO{");
		sb.append("correntPage=").append(correntPage);
		sb.append(", pageSize=").append(pageSize);
		sb.append('}');
		return sb.toString();
	}
}
