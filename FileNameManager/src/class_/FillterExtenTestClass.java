package class_;

import java.io.File;
import java.util.Vector;

import inter.FillterExten;
import main.Data;

public class FillterExtenTestClass implements FillterExten {

	@Override
	public void extenOr(Data db, String temp) {
        //파일명 필터링 하기 전 임시 파일 리스트 
        Vector<File> filteredList = new Vector<>(db.fileList.size());

        //temp와 일치하는 파일만 filteredList에 추가함
        for (File file : db.fileList) {
            if (!file.isDirectory() && file.getName().endsWith("." + temp)) {
                filteredList.add(file);
            }
        }
    
        db.fileList.clear();                //fileList업데이트 하기위해 초기화
        db.fileList.addAll(filteredList);   //filteredList의 리스트를 fileList에 추가
    
        //업데이트 된 fileList의 리스트 출력
        System.out.println("Updated File List:");
        for (File file : db.fileList) {
            System.out.println(file.getName());
        }
	}

	@Override
	public void extenOrElse(Data db, String temp) {
        //파일명 필터링 하기 전 임시 파일 리스트 
		Vector<File> filteredList = new Vector<>(db.fileList.size());

        //temp와 일치하는 파일만 filteredList에 추가함
        for (File file : db.fileList) {
            if (!file.isDirectory() && !file.getName().endsWith("." + temp)) {
                filteredList.add(file);
            }
        }

        db.fileList.clear();                //fileList업데이트 하기위해 초기화
        db.fileList.addAll(filteredList);   //filteredList의 리스트를 fileList에 추가

        //업데이트 된 fileList의 리스트 출력
        System.out.println("Updated File List:");
        for (File file : db.fileList) {
            System.out.println(file.getName());
        }
	}

}