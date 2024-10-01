package hw.toy.hospital.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import hw.toy.hospital.dto.Admin;
import hw.toy.hospital.dto.Doctor;
import hw.toy.hospital.dto.Patient;

public class HospitalService {

	private Scanner sc = new Scanner(System.in);

	// set map list 하나씩 다써보기

	// List<Admin> admin = new ArrayList<Admin>();

	Set<Admin> admin = new LinkedHashSet<Admin>();

	List<Admin> login = new ArrayList<Admin>(); // 로그인한 사람에 대한 정보 넣을 리스트

	List<Doctor> doctor = new ArrayList<Doctor>();

	List<Patient> patient = new ArrayList<Patient>();

	/**
	 * 관리자에 대한 정보 미리 삽입
	 * 
	 * 환자 , 의사에 대한 데이터 출력 확인용 데이터 미리 삽입
	 * 
	 */
	public HospitalService() {
		// TODO Auto-generated constructor stub

		admin.add(new Admin("admin", "0000", "0000", "19931116"));
		admin.add(new Admin("admin2", "1234", "1234", "19921016"));

		// admin.add(new Admin("admin", "0000", "0000", "19931116"));
		// admin.add(new Admin("admin2", "1234", "1234", "19921016"));

		// map으로 해보기

		doctor.add(new Doctor("김내과", "내과", "19611212"));
		doctor.add(new Doctor("박외과", "외과", "19770204"));
		doctor.add(new Doctor("김안과", "안과", "19810915"));
		doctor.add(new Doctor("권내과", "내과", "19931116"));
		doctor.add(new Doctor("최외과", "외과", "19831002"));

		patient.add(new Patient("김길동", "19550102", 'm', 01011112222, "내과", 20240101));
		patient.add(new Patient("마이콜", "19990718", 'm', 01055556666, "안과", 20210502));
		patient.add(new Patient("홍도치", "19710812", 'w', 01033334444, "외과", 20220601));
		patient.add(new Patient("박둘리", "20051006", 'w', 01011112222, "외과", 20230825));

	}

	public void select() {
		for (Admin ad : admin) {
			System.out.println(ad);
		}
		System.out.println();
		for (Doctor dt : doctor) {
			System.out.println(dt);
		}

		System.out.println();
		for (Patient pt : patient) {
			System.out.println(pt);
		}
	}

	public void displayMenu() {
		System.out.println();
		System.out.println("===========병원 환자관리 프로그램입니다.==========");
		System.out.println();

		int menuNum = 0; // 메뉴 선택용 변수

		do {
			System.out.println("1. 로그인");
			System.out.println("2. 관리자 회원가입");

			System.out.print("번호 선택 : ");

			try {
				menuNum = sc.nextInt();
				System.out.println();

				switch (menuNum) {
				case 1:
					adminLogin();
					break;
				case 2:
					adminAdd();
					break;

				case 0:
					System.out.println("프로그램 종료");
					break;
				default:
					System.out.println("해당 메뉴는 존재하지 않습니다.다시 입력해주세요");
				}

			} catch (InputMismatchException e) {
				System.out.println("\n 숫자를 입력하시지 않았습니다. 다시 시도해 주세요 ");

				sc.nextLine(); // 입력버퍼에 남아있는 잘못된 코드 제거

				menuNum = -1; // 첫 반복 시 잘못 입력하는 경우
				// menuNum이 0을 가지고 있어 종료되는데
				// 이를 방지하기 위해 임의 값 -1 대입
			}

		} while (menuNum != 0); // false일 경우 do while문 종료
								// true 일경우 do부분 실행문 실행
	}

	/**
	 * 관리자 회원가입
	 * 
	 * @return
	 */
	public void adminAdd() {

		System.out.println("====관리자 회원가입 페이지 입니다 ====");
		System.out.println();
		boolean flag = true;

		System.out.print("아이디:");
		String id = sc.next();

		for (Admin ad : admin) {
			if (ad.getId().equals(id)) {
				System.out.println("이미 존재하는 아이디입니다. 다시 입력하세요 ");
				adminAdd();

				break; // 없으니까 java.util.ConcurrentModificationException 발생
			}
		}

		System.out.println(" @@ 사용 가능한 id 입니다.");

//		while (flag) {
//
//			for (Admin ad : admin) {
//				if (ad.getId().equals(id)) {
//					System.out.println("이미 존재하는 아이디입니다. 다시 입력하세요 ");
//					adminAdd();
//				} else {
//					System.out.println(" @@ 사용 가능한 id 입니다.");
//					flag = false; // 없으니까 비밀번호까지 안넘어감 왜그러지
//				}
//
//				break; // 없으니까 java.util.ConcurrentModificationException 발생 왜 그러지
//			}
//		}

		System.out.print("비밀번호:");

		String pw1 = sc.next();

		System.out.print("비밀번호 확인:");
		String pw2 = sc.next();

		while (true) {

			if (pw1.equals(pw2)) {
				System.out.println("비밀번호가 일치합니다.");
				break;
			} else {
				System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요");
				adminAdd();
			}
		}
		System.out.print("생년월일:");
		String birth = sc.next();

		System.out.println("회원가입 성공! 다시 로그인 해주세요");
		admin.add(new Admin(id, pw1, pw2, birth));

		System.out.println(admin);

		adminLogin();

	}

	/**
	 * 관리자 로그인 메서드
	 * 
	 */
	public void adminLogin() {
		// 강의 67번
		System.out.println("============로그인============");

		System.out.println();

		System.out.print("아이디를 입력하세요");
		String id = sc.next();

		System.out.print("비밀번호를 입력하세요");
		String pw = sc.next();

		// 문제 : admin에 들어있는 값이 첫번째 인덱스의 값만 로그인 성공 , 두번쨰인덱스값은 찾지를 못함

		// boolean isSuccess = false;
		for (Admin ad : admin) {
			// System.out.println("admin :" + admin);
			// System.out.println("ad :" + ad);
			if (ad.getId().equals(id) && ad.getPw().equals(pw)) {
				// 1 isSuccess = true;
				System.out.println("ad1 :" + ad);
				List<Admin> loginStore = new ArrayList<Admin>(); // 해당메서드에서 로그인한 정보 리스트 생성
				loginStore.add(ad); // 조건에 맞는 리스트의 값 전체 담기 (list안에 set 담기 )
				login = loginStore; // 전역변수 login 에 복사 ,
				System.out.println("             ★로그인 성공");
				System.out.println(login);

				adminMenu();
				break;
			}

//		for (Admin ad : admin) {
//			if (ad.getId().equals(id) && ad.getPw().equals(pw)) {
//
//				List<Admin> loginStore = new ArrayList<Admin>(); // 해당메서드에서 로그인한 정보 리스트 생성
//				loginStore.add(ad); // 조건에 맞는 리스트의 값 전체 담기  (list안에 set 담기 )
//				login = loginStore; // 전역변수 login 에 복사  , 
//				System.out.println("             ★로그인 성공");
//				System.out.println(ad);
//				System.out.println(login);
//				adminMenu();
//				break;
//			}
//
//
//			// else가 없으면 로그인 2번째도 잘되지만 있으면 계속 2번째는 로그인 안됨
////			else {
////				System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
////				break;
////			}
//
//		}

		}
		System.out.println("로그인 실패 다시 로그인해주세요");
		adminLogin();
	}

	public void adminMenu() {
		System.out.println("===========병원 환자관리 프로그램입니다.==========");
		System.out.println();

		int menuNum = 0; // 메뉴 선택용 변수

		do {
			System.out.println("1. 입원환자조회"); // select
			System.out.println("2. 입원환자추가"); // update
			System.out.println("3. 퇴원"); // delete
			System.out.println("4. 교수 조회"); // select , sort
			System.out.println("5. 담당 교수 할당");

			System.out.print("번호 선택 : ");

			try {
				menuNum = sc.nextInt();
				System.out.println();

				switch (menuNum) {
				case 1:
					patientList();
					break;
				case 2:
					patientAdd();
					break;
				case 3:
					patientRemove();
					break;
				case 4:
					departmentList();
					break;

				case 0:
					System.out.println("프로그램 종료");
					break;
				default:
					System.out.println("해당 메뉴는 존재하지 않습니다.다시 입력해주세요");
				}

			} catch (InputMismatchException e) {
				System.out.println("\n 숫자를 입력하시지 않았습니다. 다시 시도해 주세요 ");

				sc.nextLine(); // 입력버퍼에 남아있는 잘못된 코드 제거

				menuNum = -1; // 첫 반복 시 잘못 입력하는 경우
				// menuNum이 0을 가지고 있어 종료되는데
				// 이를 방지하기 위해 임의 값 -1 대입
			}

		} while (menuNum != 0); // false일 경우 do while문 종료
								// true 일경우 do부분 실행문 실행
	}

	public void patientList() {

		int ptNum = 0;

		for (Patient ptl : patient) {
			ptNum++;
			System.out.println(ptl);
		}

		System.out.println("  총  " + ptNum + "  분이 입원중입니다.");
		System.out.println();

		System.out.println("입원일순으로 정렬하시겠습니까   y/n");

		char yn = sc.next().toUpperCase().charAt(0);
		if (yn == 'Y') {
			patientListByday();

		}

	}

	public void patientListByday() {
		System.out.println("입원일순으로 정렬");

		List<Patient> sortPt = new ArrayList<Patient>(patient);

		// 입원일 순으로 오름차순 정렬
		sortPt.sort(Comparator.comparing(Patient::getDay));

		for (Patient sort : sortPt) {
			System.out.println(sort);
		}

	}

	public void patientAdd() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("============입원 환자 추가============");

		System.out.print("환자 성명 ");
		String name = sc.next();

		System.out.print("환자 생년월일 -8자리로 입력하세요");
		String birth = sc.next();

		System.out.print("환자 성별 - m / w");
		char gender = sc.next().toUpperCase().charAt(0);

		System.out.print("환자 연락처");
		int pNumber = sc.nextInt();

		System.out.print("담당 과 - 내과,외과,안과 중 선택하세요");
		String department = sc.next();

		System.out.println("입원일");
		int day = sc.nextInt();

		for (Patient ptAdd : patient) {

			patient.add(new Patient(name, birth, gender, pNumber, department, day));
			System.out.println(name + "  환자님을 추가하였습니다.");

			patientList();
			break;

		}

	}

	/**
	 * 환자 퇴원 메서드
	 * 
	 */
	public void patientRemove() {
		System.out.println();
		System.out.println("======환자 퇴원 =======");

		System.out.println("환자 이름 입력");
		String name = sc.next();

		System.out.println("입원일 입력");
		int day = sc.nextInt();

		// ConcurrentModificationException 발생 원인 및 해결 방안
		// 동시 수정: ArrayList와 같은 컬렉션을 순회하면서 동시에 요소를 제거하려고 할 때 발생하는 대표적인 예외입니다.

		// List의 복사본 생성 후 삭제:

		// ArrayList의 경우 ArrayList.subList()를 사용하여 복사본을 생성하고, 복사본에서 삭제 작업을 수행한 후 원본 리스트에
		// 반영할 수 있습니다.

		// List<Patient> tempList = new ArrayList<>(patient);

		// new ArrayList<>(patient):
		// new ArrayList<>(): ArrayList의 새로운 인스턴스를 생성합니다.
		// (patient): 생성된 ArrayList에 patient 리스트의 모든 요소를 복사합니다. 즉, patient 리스트에 있는 모든
		// Patient 객체를 tempList에도 동일하게 복사하여 초기화합니다.

		for (Patient ptRmove : patient) {
			if (ptRmove.getName().equals(name) && ptRmove.getDay() == day) {
				System.out.println(name + "님을 퇴원처리 하겠습니까?  (y/n)");
				char ch = sc.next().toUpperCase().charAt(0);

				if (ch == 'Y') {
					patient.remove(ptRmove);
					System.out.println("퇴원 처리를 완료하였습니다.");
					patientList();
					break;
				}

				if (ch == 'N') {
					System.out.println("퇴원 처리 취소");
					patientRemove();
					break;
				}

				// 삭제할 인덱스 찾기

			}
			System.out.println("일치하는 환자가 없습니다. 다시입력해주세요");
			patientRemove();
			break;
		}
	}

	public void doctorList() {
		System.out.println();
		System.out.println("   교수 명단 입니다.");

		for (Doctor dt : doctor) {
			System.out.println(dt);
			System.out.println();
		}
	}

	public void departmentList() {
		System.out.println("교수조회 ");
		Map<String, List<Doctor>> dtByDpt = new HashMap<String, List<Doctor>>();
		// 과를 키로 지정 하고 해당 과를 가진 List를 value로 저장

		for (Doctor dt : doctor) {

			String dpt = dt.getDepartment(); // 과 얻어오기
			dtByDpt.putIfAbsent(dpt, new ArrayList<Doctor>());
			// putIfAbsent() :map에서 제공하는 메서드로
			// 해당 키가 존재하지 않는 경우 전달받은 값을 추가
			// -> Map에 해당 과의 리스트가 없는 경우에만 새로운 리스트를 생성하여 추가

			dtByDpt.get(dpt).add(dt);
			// dpt에 맞는 벨류값이 반환 -> List
			// list.add(dt) ->전달된 dt 객체를 list에 추가

		}
		System.out.println();
		
		for (Entry<String, List<Doctor>> entry :dtByDpt.entrySet()) {
			String department = entry.getKey(); //해당 enrty 의 과 를 얻어옴
		
			List<Doctor> doctors =entry.getValue();
		System.out.println("[담당과  :"  + department+"]");
		
		for(Doctor departList :doctors) {
			System.out.println(departList);
		}
		System.out.println();
		
		}
	}
}