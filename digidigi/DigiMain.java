package digidigi;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.swing.UIManager;

import Day20.DbExam01;


public class DigiMain {

	public void placeFont() {

		try {
		    // 폰트 파일 로드
		    InputStream is = DigiMain.class.getResourceAsStream("/css/MyFont.ttf");
		    Font font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(12f); // 폰트 크기 설정

		    // UIManager를 통해 전역 폰트 변경
		    Enumeration<Object> keys = UIManager.getDefaults().keys();
		    while (keys.hasMoreElements()) {
		        Object key = keys.nextElement();
		        Object value = UIManager.get(key);
		        if (value instanceof Font) {
		            UIManager.put(key, font);
		        }
		    }
		} catch (IOException | FontFormatException e) {
		    e.printStackTrace();
		}
	}
	
	public static void main(String []args) {

		
		DigiMain main = new DigiMain();
		main.placeFont();
		//전역 폰트 설정
		

		new LoginWindow();
		//user 초기 창 호출
		
	}
	


}
