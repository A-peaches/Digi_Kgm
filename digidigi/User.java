package digidigi;

public class User {
	private String id; 
	private String pw; 
	private String nickName; 
	private byte[] photo;
	private boolean admin; //관리자 권한 여부
	private boolean cutOff; //로그인 제한 여부

	// constructor

	public User(String id, String pw, String nickName) {
		this(id, pw, nickName, null, false, false);
		// id와 pw, nickName은 NOT NULL.
	}
	

	public User(String id, String pw, String nickName, byte[] photo) {
		this(id, pw, nickName, photo, false, false);
	}


	public User(String id, String pw, String nickName, byte[] photo, boolean admin, boolean cutOff) {
		this.id = id;
		this.pw = pw;
		this.nickName = nickName;
		this.photo = photo;
		this.admin = admin;
		this.cutOff = cutOff;
		// admin과 cutOff는 오직 관리자의 권한. 설정은 메서드 통해서만 가능.
	}

	// getter, setter
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo; // 변경된 부분
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isCutOff() {
		return cutOff;
	}

	public void setCutOff(boolean cutOff) {
		this.cutOff = cutOff;
	}

}
