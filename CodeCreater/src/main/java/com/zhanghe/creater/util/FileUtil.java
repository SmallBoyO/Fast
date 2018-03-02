package com.zhanghe.creater.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class FileUtil {
	public static void writeFile(File file,Template t,VelocityContext context) throws IOException{
		if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        if (!file.exists())
            file.createNewFile();
        FileOutputStream outStream = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(outStream,
                "UTF-8");
        BufferedWriter sw = new BufferedWriter(writer);
        t.merge(context, sw);
        sw.flush();
        sw.close();
        outStream.close();
	} 
}
