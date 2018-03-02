package com.zhanghe.creater.createfile;

public class FileConfig {
	public FileConfig( String fileSavePath ,String tmplateName ,String fileName ){
		super();
		this.fileSavePath = fileSavePath;
		TmplateName = tmplateName;
		this.fileName = fileName;
	}
	String fileSavePath;
	String TmplateName;
	String fileName;
	public String getFileSavePath() {
		return fileSavePath;
	}
	public void setFileSavePath( String fileSavePath ) {
		this.fileSavePath = fileSavePath;
	}
	public String getTmplateName() {
		return TmplateName;
	}
	public void setTmplateName( String tmplateName ) {
		TmplateName = tmplateName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName( String fileName ) {
		this.fileName = fileName;
	}
	
}
