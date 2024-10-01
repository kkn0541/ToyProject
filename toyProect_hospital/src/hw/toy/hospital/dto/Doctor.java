package hw.toy.hospital.dto;

public class Doctor {

	private String name;
	private String department;
	private String birth;

	public Doctor() {
		// TODO Auto-generated constructor stub
	}

	public Doctor(String name, String department, String birth) {
		super();
		this.name = name;
		this.department = department;
		this.birth = birth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "이름 : " + name + "  |  담당과 :" + department + "  |  생년월일 :" + birth;
	}
}