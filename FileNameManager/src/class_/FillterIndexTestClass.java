package class_;

import java.io.File;
import java.util.Vector;

import inter.FillterIndex;
import main.Data;

public class FillterIndexTestClass implements FillterIndex {

	@Override
	public void indexOrElse(Data db, int fIndex, int lIndex) {
		//파일명 필터링 하기 전 임시 파일 리스트 
        Vector<File> filteredList = new Vector<>(db.fileList.size());
		//파일 리스트를 불러옴
		Vector<File> fileList = db.fileList;

		//최소 인덱스 ~ 최대 인덱스 사이의 범위를 삭제
		for (int i = 0; i < fileList.size(); i++) {
            if (i < fIndex - 1 || i > lIndex - 1) {
                filteredList.add(fileList.get(i));
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
	public void indexOr(Data db, int fIndex, int lIndex) {
		//파일명 필터링 하기 전 임시 파일 리스트 
        Vector<File> filteredList = new Vector<>(db.fileList.size());
		//파일 리스트를 불러옴
		Vector<File> fileList = db.fileList;

		//최소 인덱스 ~ 최대 인덱스 사이의 범위를 제외하고 삭제
		for (int i = fIndex - 1; i <= lIndex - 1; i++) {
            filteredList.add(fileList.get(i));
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
