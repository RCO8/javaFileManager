package inter;

import java.io.File;
import java.util.Vector;

import main.Data;

public interface Doing {
	public void doingNameChange(Data db, String temp);
	//이름 일괄 수정
	public void doingRemoveAll(Data db);
	//파일 제거
	public void doingDivision(Data db);
	//확장자별 분류
	public void doingRemoveCopy(Data db);
	//중복파일 제거
}

class abc implements Doing{

	@Override
	public void doingNameChange(Data db, String temp) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'doingNameChange'");
	}

	@Override
	public void doingRemoveAll(Data db) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'doingRemoveAll'");
	}

	@Override
	public void doingDivision(Data db) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'doingDivision'");
	}

	@Override
	public void doingRemoveCopy(Data db) {
		//복사된 파일 감지한 후 지우기
		Vector<Vector<File>> f_hyliky = new Vector<>(db.fileList.size());
		for(File f : db.fileList){
			String ext = f.getName().split(".")[1]; //확장자
			String Name = f.getName().split(".")[0]; //이름
			long f_size = f.length(); //크기
			long f_date = f.lastModified(); //마지막 수정날짜

			for(Vector<File> vf:f_hyliky){
				int is_copy = 0;
				if(vf.get(0).getName().split(".")[1].equals(ext)) is_copy += 1;
				String tempName = vf.get(0).getName().split(".")[0];
				if(tempName.split("-")[1].equals(" 복사본")) is_copy += 1;
				else if(tempName.split(" ")[1].equals(" 복사본")) is_copy += 1;
				else if(tempName.split("-")[1].equals(" 사본")) is_copy += 1;
				else if(tempName.split(" ")[1].equals(" 사본")) is_copy += 1;
				else if(tempName.matches("*( [0-9] )")) is_copy += 1;
				else if(tempName.matches("*([0-9])")) is_copy += 1;
				if(f_size >= vf.get(0).length() && f_size <= vf.get(0).length() + 256 * 2) is_copy += 1; 
				//크기가 얼추 맞다면 이름의 최대 길이 255글자가 각각 1~4바이트라고 했을 때 파일 용량에 이름이 포함될 경우

				if(is_copy >= 3){
					
				}
			}
		}
	}
	
}