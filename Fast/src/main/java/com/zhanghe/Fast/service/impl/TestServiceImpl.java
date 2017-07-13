package com.zhanghe.Fast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhanghe.Fast.entity.Test;
import com.zhanghe.Fast.mapper.TestMapper;
import com.zhanghe.Fast.service.TestService;
@Service
@Transactional
public class TestServiceImpl implements TestService {
	@Autowired
	public TestMapper testMapper;
	@Override
	public Test getTestByid(Integer id) {
		return testMapper.getTestByid(id);
	}

}
