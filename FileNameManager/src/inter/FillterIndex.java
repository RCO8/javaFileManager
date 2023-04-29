package inter;

import main.Data;

public interface FillterIndex {
	public void indexOrElse(Data db, int fIndex, int lIndex);
	//인덱스 범위를 빼기
	public void indexOr(Data db, int fIndex, int lIndex);
	//인덱스 볌위를 남기기
}
