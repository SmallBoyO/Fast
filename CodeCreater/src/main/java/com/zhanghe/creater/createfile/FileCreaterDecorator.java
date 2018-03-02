package com.zhanghe.creater.createfile;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public abstract class FileCreaterDecorator implements FileCreater {
	
	protected FileCreater fileCreater;
	protected FileConfig fileConfig;
	
	public FileCreaterDecorator(FileCreater fileCreater,FileConfig fileConfig){
		this.fileCreater = fileCreater;
		this.fileConfig = fileConfig;
	}
	
	public void create(VelocityContext context,VelocityEngine ve) {
		fileCreater.create( context, ve);
	}
}
