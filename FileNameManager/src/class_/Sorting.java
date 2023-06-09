package class_;

import main.Data;

import java.io.*;
import java.util.Comparator;

import inter.SortMode;

public class Sorting implements SortMode
{
    
    public void Sort(Data db, int mod, boolean is_lower){
        int index = 0;  //비교하기 위한 파일 인덱스
        int getSize = db.fileList.size();
        SortComparatorLower sortNewer = new SortComparatorLower();
        SortComparatorOlder sortOlder = new SortComparatorOlder();
        SortComparatorUpper sortUpper = new SortComparatorUpper();
        SortComparatorLower sortLower = new SortComparatorLower();

        File fileTmp = null;
        switch(mod)
        {
            case 1:
                //이름순
                if(is_lower)    //내림차순
                {
                    while(db.fileList.get(getSize - 1) != null)
                    {
                        //만약에 아래 코드를 비교해서 두 파일 인덱스를 바꾼다....
                        if(0 < sortUpper.compare(db.fileList.get(index), db.fileList.get(index+1)))
                        {
                            if(fileTmp == null){
                            SwapFile(fileTmp, db.fileList.get(index));
                            SwapFile(db.fileList.get(index), db.fileList.get(index+1));
                            SwapFile(db.fileList.get(index+1), fileTmp);
                            }
                        }
                        index++;
                    }
                }
                else   //오름차순
                { 
                    while(db.fileList.get(getSize - 1) != null)
                    {
                        //만약에 아래 코드를 비교해서 두 파일 인덱스를 바꾼다....
                        if(0 < sortLower.compare(db.fileList.get(index), db.fileList.get(index+1)))
                        {
                            if(fileTmp == null){
                            SwapFile(fileTmp, db.fileList.get(index));
                            SwapFile(db.fileList.get(index), db.fileList.get(index+1));
                            SwapFile(db.fileList.get(index+1), fileTmp);
                            }
                        }
                        index++;
                    }
                }
                break;
            case 2:
                //날짜순
                if(is_lower)    //내림차순
                {
                    while(db.fileList.get(getSize - 1) != null)
                    {
                        if(0 < sortOlder.compare(db.fileList.get(index), db.fileList.get(index+1)))
                        {
                            if(fileTmp == null){
                                SwapFile(fileTmp, db.fileList.get(index));
                                SwapFile(db.fileList.get(index), db.fileList.get(index+1));
                                SwapFile(db.fileList.get(index+1), fileTmp);
                            }
                        }
                        index++;
                    }
                }
                else    //오름차순
                {
                    while(db.fileList.get(getSize - 1) != null)
                    {
                    if(0 < sortNewer.compare(db.fileList.get(index), db.fileList.get(index+1)))
                    {
                        if(fileTmp == null){
                            SwapFile(fileTmp, db.fileList.get(index));
                            SwapFile(db.fileList.get(index), db.fileList.get(index+1));
                            SwapFile(db.fileList.get(index+1), fileTmp);
                        }
                    }
                    index++;
                    }
                }
            break;
            default:
            //돌아가기
            break;
        }
    }

    private File SwapFile(File a, File b)
    {
        try{
            FileWriter replaceFile = new FileWriter(a, false);
            BufferedReader copyFile = new BufferedReader(new FileReader(b));

            String str;
            while ((str = copyFile.readLine()) != null) {
                replaceFile.write(str);
            }
            replaceFile.close();
            copyFile.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            a = null;
        }
        return a;
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