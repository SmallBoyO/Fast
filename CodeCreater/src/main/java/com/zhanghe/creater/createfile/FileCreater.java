package com.zhanghe.creater.createfile;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public interface FileCreater {
	
	public void create(VelocityContext context,VelocityEngine ve);
	
}
