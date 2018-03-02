package com.zhanghe.creater.createfile.impl;

import java.io.File;
import java.io.IOException;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.zhanghe.creater.createfile.FileConfig;
import com.zhanghe.creater.createfile.FileCreater;
import com.zhanghe.creater.createfile.FileCreaterDecorator;
import com.zhanghe.creater.util.FileUtil;

public class SimpleCreater extends FileCreaterDecorator {

	public SimpleCreater( FileCreater fileCreater,FileConfig fileConfig){
		super(fileCreater,fileConfig);
	}
	
	@Override
	public void create(VelocityContext context,VelocityEngine ve) {
		super.create(context, ve);
		Template t = ve.getTemplate(fileConfig.getTmplateName(), "UTF-8");
		File file = new File(fileConfig.getFileSavePath(), fileConfig.getFileName());
		try {
			FileUtil.writeFile(file, t, context);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
