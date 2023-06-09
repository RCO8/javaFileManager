package main;

import java.io.File;
import java.util.Vector;

public class Data {
	public String rootPath = "";
	public Vector<File> fileList = new Vector<>();
	
	public void inputFile() {
		File rootdir = new File(rootPath);
		if(rootdir.exists()){
			File[] fileList = rootdir.listFiles();
			this.fileList = new Vector<>(fileList.length);
			
			for(File f : fileList) {
				this.fileList.add(f);
			}
		}
		else{
			System.out.println("유효하지 않은 경로입니다!");
			rootPath = "";
		}
		
	}
	
	public void printFileList() {
		int c = 0;
		for(File f : fileList){
			c+=1;
			System.out.println(c+" >> "+f.getName());
		}
	}
}
