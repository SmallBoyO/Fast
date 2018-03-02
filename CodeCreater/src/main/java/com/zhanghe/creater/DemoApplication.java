package com.zhanghe.creater;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zhanghe.creater.createfile.FileConfig;
import com.zhanghe.creater.createfile.SimpleFileCreater;
import com.zhanghe.creater.createfile.impl.SimpleCreater;
import com.zhanghe.creater.jdbc.ConnectDb;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	
	@Value("${fileSavePath}")
	String fileSavePath;
	
	@Value("${DataBaseName}")
	String DataBaseName;
	
	@Value("${basePackageName}")
	String basePackageName;
	
	@Autowired
	ConnectDb connectDb;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	public void run( String... arg0 ) throws Exception {
		Properties pro = new Properties();
        pro.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        pro.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        pro.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH,  System.getProperty("user.dir")+"\\src\\main\\resources\\template");
		VelocityEngine ve = new VelocityEngine(pro);
        VelocityContext context = new VelocityContext();
		Connection conn = connectDb.Connect();
		QueryRunner qRunner = new QueryRunner();
	    List<Map<String, Object>> maps = qRunner.query(conn,"SHOW TABLES", new MapListHandler());
	    for(Map map:maps){
	    	 List<Map<String, Object>> colomnsmaps = qRunner.query(conn,"SELECT * FROM `information_schema`.`columns`  WHERE table_name='"+map.get("Tables_in_"+DataBaseName)+"' AND table_schema='"+DataBaseName+"'", new MapListHandler());
	    	 String ClassName = (String) map.get("Tables_in_"+DataBaseName);
	    	 String ClassNameLower = ClassName;
	    	 ClassName = ClassName.substring(0, 1).toUpperCase() + ClassName.substring(1, ClassName.length());
	         context.put("packageName",basePackageName);
	         context.put("ModelClass", ClassName);
	         context.put("ModelClassLower", ClassNameLower);
	         List<String> LongColomns = new ArrayList<String>();
	         List<String> BigDecimalColomns = new ArrayList<String>();
	         List<String> StringColomns = new ArrayList<String>();
	         List<String> DateColomns = new ArrayList<String>();
	    	 for(Map colomnsmap:colomnsmaps){
	    		 if("int".equals(colomnsmap.get("DATA_TYPE"))){
	    			 LongColomns.add((String) colomnsmap.get("COLUMN_NAME"));
	    		 }
	    		 if("varchar".equals(colomnsmap.get("DATA_TYPE"))){
	    			 StringColomns.add((String) colomnsmap.get("COLUMN_NAME"));
	    		 }
	    	 }
	    	 context.put("LongColomns", LongColomns);
	    	 context.put("StringColomns", StringColomns);
	    	 SimpleCreater addvocreater = new SimpleCreater(new SimpleFileCreater(), 
	    			 new FileConfig(fileSavePath+"\\vo", "AddVoTemplate.template", "Add"+ClassName+"Vo.java"));
	    	 SimpleCreater updatevocreater = new SimpleCreater(addvocreater, 
	    			 new FileConfig(fileSavePath+"\\vo", "UpdateVoTemplate.template", "Update"+ClassName+"Vo.java"));
	    	 SimpleCreater searchvocreater = new SimpleCreater(updatevocreater, 
	    			 new FileConfig(fileSavePath+"\\vo", "SearchVoTemplate.template", "Search"+ClassName+"Vo.java"));
	    	 SimpleCreater modelcreater = new SimpleCreater(searchvocreater, 
	    			 new FileConfig(fileSavePath+"\\model", "ModelTemplate.template", ClassName+".java"));
	    	 SimpleCreater mappercreater = new SimpleCreater(modelcreater, 
	    			 new FileConfig(fileSavePath+"\\mapper", "MapperTemplate.template", ClassName+"Mapper.java"));
	    	 SimpleCreater servicecreater = new SimpleCreater(mappercreater, 
	    			 new FileConfig(fileSavePath+"\\service", "ServiceTemplate.template", ClassName+"Service.java"));
	    	 SimpleCreater serviceimplcreater = new SimpleCreater(servicecreater, 
	    			 new FileConfig(fileSavePath+"\\service\\impl", "ServiceImplTemplate.template", ClassName+"ServiceImpl.java"));
	    	 serviceimplcreater.create( context, ve);
	    	 
	     }
	}
}
