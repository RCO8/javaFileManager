package class_;
import java.io.File;
import inter.Doing;
import main.Data;
import java.util.Vector;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;


public class DoingTestClass implements Doing{

	@Override
	public void doingNameChange(Data db, String temp) {
		// TODO Auto-generated method stub
		String fileName = temp;
		String number;
		String newRootPath;
		String date;
		long dm;
		int startIndex;
		if (db.fileList.size() == 0) {
			return;
		}
		
	
		for(int i = 0; i < db.fileList.size(); i++) {
			String extension = getFileExtension(db.fileList.get(i).getName());
			number = i + "";
			System.out.println("0차 :" + number + "확장자 : " + extension);
			if(extension != null){
			if (temp.contains("[n]")) {
				fileName = temp.replace("[n]", i + "");
				System.out.println("1차 :" + fileName + " "+ i);
			}
			if (temp.contains("[d]")) {
				dm = db.fileList.get(i).lastModified();
				date = new Date(dm).toString().trim();
				startIndex = date.indexOf("KST");
				date = date.substring(0, startIndex);
				date = date.replace(":", "");
				fileName = fileName.replace("[d]", " " + date);
				System.out.println("2차 :" + fileName);
			}
			fileName = fileName + "."+ extension;
				newRootPath = db.rootPath;
				newRootPath = newRootPath.replace(db.fileList.get(i).getName(), fileName);
				File nfile = new File(newRootPath + "\\\\" + fileName);
				System.out.println(nfile.getPath().toString() + db.fileList.get(i).getPath().toString());
				System.out.println();
				if(db.fileList.get(i).renameTo(nfile)){
					System.out.println("바뀌었습니다!");
				}
				else{
					System.out.println("바뀌지 않았습니다!!1");
				}
				
			}
		}
	}

	@Override
	public void doingRemoveAll(Data db) {
		// TODO Auto-generated method stub
		if (db.rootPath != null) {
            for (int i = 0; i < db.fileList.size(); i++)  {
                if (db.fileList.get(i).isFile()) {
                    boolean deleted = db.fileList.get(i).delete();
                    if (deleted) {
                        System.out.println("파일 삭제 성공: " + db.fileList.get(i).getName());
                    } else {
                        System.out.println("파일 삭제 실패: " + db.fileList.get(i).getName());
                    }
                }
            }
        }
	}

	@Override
	public void doingDivision(Data db) {
		for (int i = 0; i < db.fileList.size(); i++){
			if (db.fileList != null) {
						// 파일의 확장자 가져오기
						String extension = getFileExtension(db.fileList.get(i).getName());
	
						if (extension != null) {
							// 폴더 생성
							File destinationDirectory = new File(db.rootPath + "\\" + extension);
							if (!destinationDirectory.exists()) {
								boolean created = destinationDirectory.mkdir();
								if (created) {
									System.out.println("폴더 생성: " + destinationDirectory.getName());
								} else {
									System.out.println("폴더 생성 실패: " + destinationDirectory.getName());
									continue;
								}
							}
	
							// 파일 이동
							Path sourcePath = db.fileList.get(i).toPath();
							Path destinationPath = destinationDirectory.toPath().resolve(db.fileList.get(i).getName());
	
							try {
								Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
								System.out.println("파일 이동: " + db.fileList.get(i).getName() + " -> " + destinationDirectory.getName());
							} catch (Exception e) {
								System.out.println("파일 이동 실패: " + db.fileList.get(i).getName());
								e.printStackTrace();
							}
				}
			}
					
				
		}
	}
        
    


    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        }
        return null;
    }
	
	

	@Override
   public void doingRemoveCopy(Data db) {
      //복사된 파일 감지한 후 지우기
      Vector<Vector<File>> f_hyliky = new Vector<>(db.fileList.size()); //복사본끼리 모은 파일 묶음
      for(File f : db.fileList){
         String ext = f.getName().split(".")[1]; //확장자
         String Name = f.getName().split(".")[0]; //이름
         long f_size = f.length(); //크기
         long f_date = f.lastModified(); //마지막 수정날짜
         boolean is_pushed = false;

         for(Vector<File> vf:f_hyliky){
            int is_copy = 0;
            if(vf.get(0).getName().split(".")[1].equals(ext)) is_copy += 1; //확장자가 같으면 +1

            String tempName = vf.get(0).getName().split(".")[0];

            if(tempName.matches(Name)){
               if(tempName.split("-")[1].equals(" 복사본")) is_copy += 1;
               else if(tempName.split(" ")[1].equals(" 복사본")) is_copy += 1;
               else if(tempName.split("-")[1].equals(" 사본")) is_copy += 1;
               else if(tempName.split(" ")[1].equals(" 사본")) is_copy += 1;
               else if(tempName.matches("*( [0-9] )*")) is_copy += 1;
               else if(tempName.matches("*([0-9])*")) is_copy += 1;
            }
            //원본 이름이 포함되어 있으면서, 뒤에 복사본 혹은 (n) 이 붙어있을 경우 +1

            if(f_size >= vf.get(0).length() && f_size <= vf.get(0).length() + 255 * 2) is_copy += 1; 
            //크기가 얼추 맞다면 이름의 최대 길이 255글자가 각각 1~4바이트라고 했을 때 파일 용량에 이름이 포함될 경우 +1

            if(is_copy >= 3 && !is_pushed){
               is_pushed = true;
               if(vf.get(0).lastModified() < f_date) //어느게 더 오래전파일(원본파일)인지 확인
                  vf.add(f);
               else //기존의 대표 파일이 더 최신이면 복제품이므로
                  vf.add(0,f);
            }
         }

         if(!is_pushed){ //만약 어느 파일 묶음에도 들어가지 않았다면 묶음을 새로 만듦
            Vector<File> temp_v = new Vector<>(1);
            temp_v.add(f);
            f_hyliky.add(temp_v);
         }
      }

      for(Vector<File> vf: f_hyliky){
         while(vf.size() > 1){ //파일 묶음이 하나가 남을때 까지 제거
            vf.get(1).delete();
			
         }
      }
   }


}

