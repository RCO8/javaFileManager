package class_;

import java.util.Date;

import inter.FillterDateRange;
import main.Data;

public class FilterDateRange implements FillterDateRange{

    public void rangeOr(Data db, Date fdate, Date ldate) {
        long dt;
        Date getDate;
        
        int i;
        
        for(i=0;i<db.fileList.size();i++)
        {
            System.out.println("파일명 : "+db.fileList.get(i).getName());
            dt = db.fileList.get(i).lastModified();
            getDate = new Date(dt);

            if(fdate.compareTo(getDate) > 0 || ldate.compareTo(getDate) < 0)
            {
                
                System.out.println("실행");
                //파일들을 필터링
                db.fileList.remove(db.fileList.get(i));
                i--;
            }
        }
    }

    public void rangeOrElse(Data db, Date fdate, Date ldate) {
        long dt;
        Date getDate;
        
        int i;
        
        for(i=0;i<db.fileList.size();i++)
        {
            System.out.println("파일명 : "+db.fileList.get(i).getName());
            dt = db.fileList.get(i).lastModified();
            getDate = new Date(dt);

            if(fdate.compareTo(getDate) < 0 && ldate.compareTo(getDate) > 0)
            {
                
                System.out.println("실행");
                //파일들을 필터링
                db.fileList.remove(db.fileList.get(i));
                i--;
            }
        }
    }
    
}
