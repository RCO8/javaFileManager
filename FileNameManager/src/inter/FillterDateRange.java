package inter;

import java.util.Date;
import main.Data;

public interface FillterDateRange {
	public void rangeOr(Data db ,Date fdate, Date ldate);
	//날짜 범위만 남기기
	public void rangeOrElse(Data db, Date fdate, Date ldate);
	//날짜 범위를 빼기
}
