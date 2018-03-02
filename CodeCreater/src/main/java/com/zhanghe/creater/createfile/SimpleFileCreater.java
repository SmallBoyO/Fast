package com.zhanghe.creater.createfile;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class SimpleFileCreater implements FileCreater {
	
	public void create(VelocityContext context,VelocityEngine ve) {
		System.out.println("开始创建文件");
	}

}
