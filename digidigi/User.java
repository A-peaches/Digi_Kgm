package digidigi;

public class User {
	private String id;
	private String pw;
	private String nickName;
	private String photo;
	private boolean admin;
	private boolean cutOff;
	private boolean isLogin;
	
	//constructor
	public User(String id, String pw, String nickName) {
		this(id, pw, nickName, null, false, false);
	}
	
	public User(String id, String pw, String nickName, String photo) {
		this(id, pw, nickName, photo, false, false);
	}
	//id와 pw, nickName은 NOT NULL.
	
	public User(String id, String pw, String nickName, String photo,
			boolean admin, boolean cutOff) {
		this.id = id;
		this.pw = pw;
		this.nickName = nickName;
		this.photo = photo;
		this.admin = admin;
		this.cutOff = cutOff;
		// admin과 cutOff는 오직 관리자의 권한. 설정은 메서드 통해서만 가능.
	}

	
	//getter, setter
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
	

	public String getPhoto() {
		return photo;
	}
	

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	


	
}
