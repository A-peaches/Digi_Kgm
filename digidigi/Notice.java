package digidigi;

public class Notice {
	private String notice_post;
	private String notice_date;
	
	//Constructor
	public Notice() {
		this(null,null);
	}
	
	public Notice(String notice_post) {
		this.notice_post = notice_post;
		this.notice_date = null;
	}
	
	public Notice(String notice_post, String notice_date) {
		this.notice_post = notice_post;
		this.notice_date = notice_date;
	}
	
	
	public String getNotice_post() {
		return notice_post;
	}
	public void setNotice_post(String notice_post) {
		this.notice_post = notice_post;
	}
	public String getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(String notice_date) {
		this.notice_date = notice_date;
	}
	
	
}
