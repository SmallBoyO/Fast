package com.zhanghe.Fast.service;

import com.google.gson.JsonObject;

public interface PermissionService {
	
	JsonObject getRightByUrlId(Long id);
	
}
