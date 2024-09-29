package hw.toy.hospital.dto;

public class Admin {

	private String id;
	private String pw;
	private String pw2;
	private String birth;

	
	public Admin() {
		// TODO Auto-generated constructor stub
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getPw2() {
		return pw2;
	}


	public void setPw2(String pw2) {
		this.pw2 = pw2;
	}


	public String getBirth() {
		return birth;
	}


	public void setBirth(String birth) {
		this.birth = birth;
	}


	public Admin(String id, String pw, String pw2, String birth) {
		super();
		this.id = id;
		this.pw = pw;
		this.pw2 = pw2;
		this.birth = birth;
	}


	@Override
	public String toString() {
		return "아이디: "+id +"  비밀번호: " + pw +"  생년월일: "+ birth + "\n";
	}
	
	
}
