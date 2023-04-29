package inter;

import main.Data;

public interface FillterName {
	public void nameOr(Data db, String temp);
	//파일 이름의 일부만 보고 포함된 파일만 남기기
	
	public void nameOrElse(Data db, String temp);
	//파일 이름의 일부만 보고 포함된 파일만 빼기
	
	public void nameEqualElse(Data db, String temp);
	//파일이름이 동일한 파일만 빼기
	
	public void nameEqual(Data db, String temp);
	//파일이름이 동일한 파일만 남기기
}
