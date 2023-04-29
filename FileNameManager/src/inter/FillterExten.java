package inter;

import main.Data;

public interface FillterExten {
	public void extenOr(Data db, String temp);
	//확장자만 남기기
	public void extenOrElse(Data db, String temp);
	//확장자만 빼기
}
