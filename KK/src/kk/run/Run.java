package kk.run;

import java.util.Scanner;

import kk.vo.Employee;

public class Run {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Employee emp[] = new Employee[3];

		emp[0] = new Employee();
		emp[1] = new Employee(1, "홍길동", 19, 'm', "01022223333", "서울 잠실");
		emp[2] = new Employee(2, "강말순", "교육부", "강사", 20, 'F', 1000000, 0.01, "01011112222", "서울 마곡");

		for (int i = 0; i < emp.length; i++) {
			System.out.println(emp[i].information());
		}

		for (int j = 0; j < emp.length; j++) {

			if (emp[j].getDept() == null) {
				System.out.println(j + "빈곳잇음");

				System.out.println("==========수정입력-================");

				System.out.print("사번:");
				int empNo = sc.nextInt();

				System.out.print("사원명:");
				String empName = sc.next();

				System.out.print("소속부서:");
				String dept = sc.next();

				System.out.print("직급");
				String job = sc.next();

				System.out.print("나이:");
				int age = sc.nextInt();

				System.out.print("성별:");
				char gender = sc.next().charAt(0);

				System.out.print("급여:");
				int salary = sc.nextInt();

				System.out.println("보너스 포인트");
				double bonusPoint = sc.nextDouble();

				sc.nextLine();

				System.out.println("전화번호");
				String phone = sc.next();

				sc.nextLine();

				System.out.println("주소");
				String address = sc.nextLine();

				emp[j] = new Employee(empNo, empName, dept, job, age, gender, salary, bonusPoint, phone, address);


			}
			System.out.println("emp[" + j + "]" + emp[j].information());
			System.out.println("==================================");
			
			break;
			
		}
		
		int total = 0;
		int totalSalary = 0;
		
		for(int i = 0; i < emp.length; i++) {
			totalSalary = (int)(emp[i].getSalary() + emp[i].getSalary()*emp[i].getBonusPoint())*12;
			System.out.println(emp[i].getEmpName() + "의 연봉 : " + totalSalary + "원");
			total += totalSalary;
		}
		System.out.println("======================================================================");
		
		System.out.println("직원들의 연봉의 평균 : " + total/emp.length + "원");
		
	}

}
