package digidigi;

public class ChatRoom {
	private int roomNum;
	private String roomName;
	
	//roomNum와 roomName이 notNull임!
	public ChatRoom(int roomNum, String roomName) {
		this.roomNum = roomNum;
		this.roomName = roomName;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	@Override
	public String toString() {
	    return roomName;
	}
	
}
