package class_;

import main.Data;

import java.io.*;
import java.util.Comparator;

import inter.SortMode;

public class Sorting implements SortMode
{
    public void Sort(Data db, int mod, boolean is_lower){
        SortComparatorLower sortNewer = new SortComparatorLower();
        SortComparatorOlder sortOlder = new SortComparatorOlder();
        SortComparatorUpper sortUpper = new SortComparatorUpper();
        SortComparatorLower sortLower = new SortComparatorLower();
        
        switch(mod)
        {
            case 1:
                //이름순
                if(is_lower)    //내림차순
                {
                    db.fileList.sort(sortLower);
                }
                else   //오름차순
                { 
                    db.fileList.sort(sortUpper);
                }
                break;
            case 2:
                //날짜순
                if(is_lower)    //내림차순
                {
                    db.fileList.sort(sortOlder);
                }
                else    //오름차순
                {
                    db.fileList.sort(sortNewer);
                }
            break;
            default:
            //돌아가기
            break;
        }
    }

    //오래된 순
    class SortComparatorOlder implements Comparator<File>{

        @Override
        public int compare(File o1, File o2) {
            if(o1.lastModified() > o2.lastModified()) return 1;
            if(o1.lastModified() < o2.lastModified()) return -1;
            return 0;
        }
    }
    //최신 순
    class SortComparatorNewer implements Comparator<File>{

        @Override
        public int compare(File o1, File o2) {
            if(o1.lastModified() > o2.lastModified()) return -1;
            if(o1.lastModified() < o2.lastModified()) return 1;
            return 0;
        }
    }

    class SortComparatorUpper implements Comparator<File>{
        
        @Override
        public int compare(File a, File b)
        {
            return a.getName().compareTo(b.getName());
        }
    }
    class SortComparatorLower implements Comparator<File>{
        
        @Override
        public int compare(File a, File b)
        {
            return a.getName().compareTo(b.getName()) * -1;
        }
    }
}