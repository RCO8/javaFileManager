package inter;
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
