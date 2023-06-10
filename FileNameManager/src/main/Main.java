package main;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import class_.DoingTestClass;
import class_.FillterExtenTestClass;
import class_.FillterIndexTestClass;
import class_.FilterNameImpl;
import inter.*;

//TODO 인덱스 입력 받은거 파일 리스트 인덱스를 못 벗어나게 하는 기능 추가필요(변경 완료)


public class Main {
	static Scanner sc;
	static Data db;
	static int input;
	static inter.FillterName filName;
	static inter.FillterDateRange filDbRange;
	static inter.FillterExten filExten;
	static inter.FillterIndex filIndex;
	static inter.SortMode sortMode;
	static inter.Doing doing;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sc = new Scanner(System.in);
		db = new Data();
		input = 0;
		
		doing = new DoingTestClass();
		filExten = new FillterExtenTestClass();
		filIndex = new FillterIndexTestClass();
		filName = new FilterNameImpl();
		/*
		 * filName = new 클래스이름;
		 * filDbRange = new 클래스이름;
		 * filExten = new 클래스이름;
		 * filIndex = new 클래스이름;
		 * sortMode = new 클래스이름;
		 * doing = new 클래스이름;
		 * */
		while(true) {
			System.out.println("파일 이름 관리자");
			System.out.println("현재 경로: "+(db.rootPath.equals("") ? "경로 없음":db.rootPath));
			System.out.println("\t1) 경로 설정");
			if(!db.rootPath.equals("")) {
				System.out.println("\t2) 필터");
				System.out.println("\t3) 정렬");
				System.out.println("\t4) 처리");
				System.out.println("\t5) 파일리스트출력");
			}
			if(db.rootPath.equals("")) {
				if(!_input(1,1)) continue;
			}
			else {
				if(!_input(1,5)) continue;
			}
				
			
			switch(input) {
			case 1:
				System.out.println("폴더 경로를 입력해주세요.");
				
				//경로 설정 만들것.
				db.rootPath = sc.nextLine();
				db.inputFile();
				//폴더안에있는 파일들을 전부 리스트에 넣어야함.
				break;
			case 2:
				_fillter();
				break;
			case 3:
				_sort();
				break;
			case 4:
				_doing();
				break;
			case 5:
				db.printFileList();
				break;
			}
			
		}
	}

	private static boolean _input(int min, int max) {
		try {
			input = Integer.parseInt(sc.nextLine());
			
			if(input < min || input > max) {
				System.out.println();
				System.out.println(min+" ~ "+max+" 범위 내의 번호를 입력해야합니다.");
				System.out.println();
				return false;
			}
			
		}catch (Exception e) {
			System.out.println();
			System.out.println("숫자를 입력해 주세요");
			System.out.println();
			return false;
			// TODO: handle exception
		}
		return true;
	}
	
	private static void _fillter(){
		while(true) {
			System.out.println("어떤 기준으로 필터를 이용하겠습니까?");
			System.out.println("\t1) 이름");
			System.out.println("\t2) 수정날짜");
			System.out.println("\t3) 확장자");
			System.out.println("\t4) 인덱스");
			System.out.println("\t5) 돌아가기");
			if(!_input(1,5)) continue;
			
			switch(input) {
			case 1:
				_filName();
				break;
			case 2:
				_filDate();
				break;
			case 3:
				_filExten();
				break;
			case 4:
				_filIndex();
				break;
			case 5:
				return;
			}
		}
		
	}
	
	private static void _filName() {
		String temp = "";
		while(true) {
			System.out.println("이름을 기준으로");
			System.out.println("\t1) 입력된 이름이 포함된 파일만 남기기");
			System.out.println("\t2) 입력된 이름이 포함된 파일을 빼기");
			System.out.println("\t3) 정확히 입력된 이름의 파일만 빼기");
			System.out.println("\t4) 정확히 입력된 이름의 파일만 남기기");
			System.out.println("\t5) 돌아가기");
			if(!_input(1,7)) continue;
			
			switch(input) {
			case 1:
				System.out.println("파일 이름의 일부를 입력해주세요.");
				System.out.print(">> ");
				temp = sc.nextLine();
				
				filName.nameOr(db, temp);
				break;
			case 2:
				System.out.println("파일 이름의 일부를 입력해주세요.");
				System.out.print(">> ");
				temp = sc.nextLine();
				
				filName.nameOrElse(db, temp);
				break;
			case 3:
				System.out.println("파일 이름을 입력해주세요.");
				System.out.print(">> ");
				temp = sc.nextLine();
				
				filName.nameEqualElse(db, temp);
				break;
			case 4:
				System.out.println("파일 이름을 입력해주세요.");
				System.out.print(">> ");
				temp = sc.nextLine();
				
				filName.nameEqual(db, temp);
				break;
			case 5:
				return;
			}
		}
	}
	
	private static void _filDate() {
		Date fdate, ldate;
		while(true) {
			System.out.println("수정날짜를 기준으로");
			System.out.println("\t1) 날짜 범위의 파일만 남기기");
			System.out.println("\t2) 날짜 범위의 파일만 빼기");
			System.out.println("\t3) 돌아가기");
			if(!_input(1,7)) continue;
			
			switch(input) {
			case 1:
				System.out.println("최소날짜를 입력해주세요");
				fdate = _inputDate(new Date());
				System.out.println("최대날짜를 입력해주세요");
				ldate = _inputDate(fdate);
				if(ldate.before(fdate)) {
					System.out.println("최소날짜보다 이전 날짜를 할 수 없습니다!");
				}
				
				filDbRange.rangeOr(db, fdate, ldate);
				break;
			case 2:
				System.out.println("최소날짜를 입력해주세요");
				fdate = _inputDate(new Date());
				System.out.println("최대날짜를 입력해주세요");
				ldate = _inputDate(fdate);
				if(ldate.before(fdate)) {
					System.out.println("최소날짜보다 이전 날짜를 할 수 없습니다!");
				}
				
				filDbRange.rangeOrElse(db, fdate, ldate);
				break;
			case 3:
				return;
			}
		}
	}
	
	private static Date _inputDate(Date d) {
		Date result = (Date) d.clone();
		SimpleDateFormat krDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
		SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
		while(true) {
			System.out.println("현재: "+krDateFormat.format(result));
			System.out.println("\t1) 직접 입력");
			System.out.println("\t2) 1년 빼기");
			System.out.println("\t3) 1년 더하기");
			System.out.println("\t4) 1달 빼기");
			System.out.println("\t5) 1달 더하기");
			System.out.println("\t6) 1일 빼기");
			System.out.println("\t7) 1일 더하기");
			System.out.println("\t8) 완료");
			if(!_input(1,8)) continue;
			switch (input) {
			case 1:
				System.out.println("yyyy-MM-dd 형태로 작성해주세요");
				try {
					String temp = sc.nextLine();
					result = DateFormat.parse(temp);
				}
				catch(ParseException e){
					System.out.println();
					System.out.println("형식을 맞춰주세요!!");
					System.out.println();
					continue;
				}
				break;
			case 2:
				result.setYear(result.getYear()-1);
				break;
			case 3:
				result.setYear(result.getYear()+1);
				break;
			case 4:
				result.setMonth(result.getMonth()-1);
				break;
			case 5:
				result.setMonth(result.getMonth()+1);
				break;
			case 6:
				result.setDate(result.getDate()-1);
				break;
			case 7:
				result.setDate(result.getDate()+1);
				break;
			case 8:
				return result;
			}
		}
	}
	
	private static void _filExten() {
		String temp;
		while(true) {
			System.out.println("확장자를 기준으로");
			System.out.println("\t1) 입력된 확장자 파일만 남기기");
			System.out.println("\t2) 입력된 확장자 파일만 빼기");
			System.out.println("\t3) 돌아가기");
			if(!_input(1,3)) continue;
			
			switch(input) {
			case 1:
				System.out.println("확장자 명을 입력해주세요.");
				System.out.print(">> ");
				temp = sc.nextLine();
				
				filExten.extenOr(db, temp);
				break;
			case 2:
				System.out.println("확장자 명을 입력해주세요.");
				System.out.print(">> ");
				temp = sc.nextLine();
				
				filExten.extenOrElse(db, temp);
				break;
			case 3:
				return;
			}
		}
	}
	
	private static void _filIndex() {
		int fIndex;
		int lIndex;
		while(true) {
			System.out.println("인덱스를 기준으로");
			System.out.println("\t1) 인덱스 범위를 빼기");
			System.out.println("\t2) 인덱스 범위만 남기기");
			System.out.println("\t3) 돌아가기");
			if(!_input(1,5)) continue;
			
			switch(input) {
			case 1:
				System.out.println("최소 인덱스를 입력해주세요!");
				System.out.print(">>");
				try {
					fIndex = Integer.parseInt(sc.nextLine());
					if(fIndex >= db.fileList.size()){
						System.out.println("최대 인덱스의 범위를 넘을 수 없습니다!");
						continue;
					}
					if(fIndex < 0) {
						System.out.println("양의 정수를 입력해주세요!");
						continue;
					}
					System.out.println("최대 인덱스를 입력해주세요!");
					System.out.print(">>");
					lIndex = Integer.parseInt(sc.nextLine());
					if(lIndex >= db.fileList.size()){
						System.out.println("최대 인덱스의 범위를 넘을 수 없습니다!");
						continue;
					}
					if(lIndex < 0) {
						System.out.println("양의 정수를 입력해주세요!");
						continue;
					}
					if(lIndex < fIndex) {
						System.out.println("최소 인덱스보다 커야합니다!");
						continue;
					}
				}catch(Exception e) {
					System.out.println("숫자를 입력해주세요!");
					continue;
				}
				
				filIndex.indexOrElse(db, fIndex, lIndex);
				break;
				
				//
			case 2:
				System.out.println("최소 인덱스를 입력해주세요!");
				try {
					fIndex = Integer.parseInt(sc.nextLine());
					if(fIndex >= db.fileList.size()){
						System.out.println("최대 인덱스의 범위를 넘을 수 없습니다!");
						continue;
					}
					if(fIndex < 0) {
						System.out.println("양의 정수를 입력해주세요!");
						continue;
					}
					System.out.println("최대 인덱스를 입력해주세요!");
					lIndex = Integer.parseInt(sc.nextLine());
					if(lIndex >= db.fileList.size()){
						System.out.println("최대 인덱스의 범위를 넘을 수 없습니다!");
						continue;
					}
					if(lIndex < 0) {
						System.out.println("양의 정수를 입력해주세요!");
						continue;
					}
					if(lIndex < fIndex) {
						System.out.println("최소 인덱스보다 커야합니다!");
						continue;
					}
				}catch(Exception e) {
					System.out.println("숫자를 입력해주세요!");
					continue;
				}
				
				filIndex.indexOr(db, fIndex, lIndex);
				break;
				
				//
			case 3:
				return;
			}
		}
	}
	
	private static void _sort() {
		int mod;
		boolean is_lower = false;
		while(true) {
			System.out.println("어떤 기준으로 정렬하시겠습니까?");
			System.out.println("\t1) 이름");
			System.out.println("\t2) 수정날짜");
			System.out.println("\t3) 돌아가기");
			if(!_input(1,3)) continue;
			
			if(input == 3) return;
			else mod = input;
			
			while(true) {
				System.out.println("차순을 선택해주세요");
				System.out.println("\t1) 오름차순");
				System.out.println("\t2) 내림차순");
				System.out.println("\t3) 돌아가기");
				if(!_input(1,3)) continue;
				
				if(input == 3) break;
				else if(input == 1) is_lower = false;
				else if(input == 2) is_lower = true;
				
				sortMode.Sort(db, mod, is_lower);
				break;
			}
		}
	}
	
	private static void _doing() {
		String temp = "";
		while(true) {
			System.out.println("파일 처리");
			System.out.println("\t1) 이름 일괄 수정");
			System.out.println("\t2) 일괄 제거");
			System.out.println("\t3) 확장자별 분류");
			System.out.println("\t4) 중복파일 제거");
			System.out.println("\t5) 돌아가기");
			if(!_input(1,5)) continue;
			
			switch(input) {
			case 1:
				System.out.println("수정할 이름을 입력해주세요 (이름중 []안에 n을 입력하면 번호, d를 입력하면 날짜가 추가되어 저장됩니다.)");
				temp = sc.nextLine();
				
				doing.doingNameChange(db, temp);
				break;
			case 2:
				System.out.println("제거중");
				System.out.println("제거완료");
				
				doing.doingRemoveAll(db);
				break;
			case 3:
				System.out.println("확장자별 분류중");
				System.out.println("분류 완료");
				
				doing.doingDivision(db);
				break;
			case 4:
				System.out.println("중복파일 제거중");
				System.out.println("제거완료");
				
				doing.doingRemoveCopy(db);
				break;
			case 5:
				return;
			}
		}
	}
	
}
