package com.zhanghe.Fast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhanghe.Fast.entity.Role;
import com.zhanghe.Fast.mapper.RoleMapper;
import com.zhanghe.Fast.service.RoleService;
import com.zhanghe.Fast.util.PageUtil;
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	
	public PageUtil<Role> getRoleListByPage(PageUtil<Role> page,EntityWrapper<Role> wrapper){
		Page<Role> querypage = new Page<Role>(page.getCorrentPage().intValue(), page.getPageSize().intValue());
		page.setResult(roleMapper.selectPage(querypage, wrapper));
		return page;
	}
	
}
