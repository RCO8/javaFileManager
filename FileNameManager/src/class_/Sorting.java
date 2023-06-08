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
                            if(fileTmp == null)
                                fileTmp = db.fileList.get(index);
                            db.fileList.get(index).delete();
                            try
                            {
                                FileWriter replaceFile = new FileWriter(db.fileList.get(index), false);
                                BufferedReader copyFile = new BufferedReader(new FileReader(db.fileList.get(index+1)));

                                String str;
                                while ((str = copyFile.readLine()) != null) {
                                    replaceFile.write(str);
                                }
                                db.fileList.get(index+1).delete();

                                replaceFile = new FileWriter(db.fileList.get(index+1), false);
                                copyFile = new BufferedReader(new FileReader(fileTmp));

                                while ((str = copyFile.readLine()) != null) {
                                    replaceFile.write(str);
                                }
                                fileTmp.delete();

                                replaceFile.close();
                                copyFile.close();
                            }catch(IOException e)
                            {
                                e.printStackTrace();
                            }
                        }
                        index++;
                    }
                }
                else   //오름차순
                { 
                    sortLower.compare(db.fileList.get(index), db.fileList.get(index+1));

                }
                break;
            case 2:
                //날짜순
                if(is_lower)    //내림차순
                {
                        sortOlder.compare(db.fileList.get(index), db.fileList.get(index+1));
                        index++;
                }
                else    //오름차순
                {
                        sortNewer.compare(db.fileList.get(index), db.fileList.get(index+1));
                        index++;
                }
            break;
            default:
            //돌아가기
            break;
        }
    }

    private void SwapFile(File a, File b)
    {
        
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