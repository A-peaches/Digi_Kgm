package digidigi;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindow extends JFrame implements ActionListener {
	private JTextField idField;
	private JPasswordField pwField;
	private JButton loginButton;
	private JButton joinButton;
	private String sql;
	private String nickName;
	private byte[] photo;
	PreparedStatement pstmt;
	Connection conn;

	public LoginWindow() {
		// 로그인창 Frame.
		setTitle("Login");
		setSize(400, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// JVM에서 완전한 창닫기. 닫기버튼 클릭시 창 종료.

		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		add(panel);
		placeComponents(panel);// 패널에 컴포넌트 배치

		// 프로그램 아이콘 설정
	    ImageIcon icon = new ImageIcon(getClass().getResource("/css/talk.png"));
	    // 프레임의 아이콘으로 설정
	    setIconImage(icon.getImage());
	    
	    setLocationRelativeTo(null);
		setVisible(true);
		// 프레임을 화면에 보이도록 설정
		
		
		
		
	}

	private void placeComponents(JPanel panel) {
		// 패널의 레이아웃을 null로 설정하여 수동으로 컴포넌트의 위치와 크기지정.
		panel.setLayout(null);

		// Join 텍스트 생성.
		JLabel title = new JLabel("LOGIN");
		title.setBounds(155, 70, 80, 25);
		// setBounds(x, y, w, h) x좌표, y좌표, 가로,세로 크기
		title.setFont(title.getFont().deriveFont(25f));
		panel.add(title);
		
		ImageIcon loginimg2 = new ImageIcon(getClass().getResource("/css/login2.png"));
		JLabel loginimg = new JLabel(loginimg2);
		loginimg.setBounds(160, 120, 60, 60); // 크기 설정
		panel.add(loginimg);


		// ID라벨 생성
		JLabel idLabel = new JLabel("ID :");
		idLabel.setBounds(100, 220, 80, 25);
		// setBounds(x, y, w, h) x좌표, y좌표, 가로,세로 크기
		panel.add(idLabel);

		// ID입력 필드 생성 및 위치 생성.
		idField = new JTextField(20);
		idField.setBounds(130, 220, 165, 25);
		panel.add(idField);

		// 비밀번호 라벨 생성 및 위치 생성.
		JLabel pwLabel = new JLabel("Password :");
		pwLabel.setBounds(65, 260, 80, 25); // 크기 설정
		panel.add(pwLabel);

		// 비밀번호 입력 필드 생성 및 위치 생성.
		pwField = new JPasswordField(20);
		pwField.setBounds(130, 260, 165, 25);
		panel.add(pwField);
		
		pwField.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        login(); // 로그인 처리를 위한 별도의 메서드 호출
		    }
		});

		// 로그인 버튼 생성 및 위치 생성.
		ImageIcon loginIcon = new ImageIcon(getClass().getResource("/css/login.png"));
		loginButton = new JButton(loginIcon);
		loginButton.setContentAreaFilled(false); //기존버튼디자인 제거 
		loginButton.setBorderPainted(false); // 기존버튼디자인 제거
		loginButton.setBounds(150, 310, 80, 35);
		// 버튼 클릭 시 이벤트 리스너.
		loginButton.addActionListener(this);
		panel.add(loginButton);

		// 회원가입창이동
		ImageIcon joinIcon = new ImageIcon(getClass().getResource("/css/join.png"));
		JLabel joinLabel = new JLabel("회원가입이 안되어있으신가요?");
		joinLabel.setBounds(110, 430, 1900, 40);
		joinButton = new JButton(joinIcon);
		joinButton.setContentAreaFilled(false); //기존버튼디자인 제거 
		joinButton.setBorderPainted(false); // 기존버튼디자인 제거
		joinButton.setBounds(160, 460, 60, 30);
		joinButton.addActionListener(this);
		// setBounds(x, y, w, h) x좌표, y좌표, 가로,세로 크기
		panel.add(joinButton);
		panel.add(joinLabel);

	}

	
	public void login() {
		String id = idField.getText();
		char[] charPw = pwField.getPassword(); // getPassword는 char타입으로만 get가능.
		String pw = new String(charPw); // String type으로 전환.
		if(id.isEmpty() || pw.isEmpty()) {
			 JOptionPane.showMessageDialog(null,
                     "ID , PW를 모두 입력해주세요! ", "에러",
                     JOptionPane.ERROR_MESSAGE);
			 
			 return;
		}
		conn = DbConnect.getConn().getDb();
		sql = "select cut_off,admin as admin from user where id=? and pw=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);

			try (ResultSet rs1 = pstmt.executeQuery()) {
				if (rs1.next()) {
					int cut_off = rs1.getInt("cut_off");
					int admin = rs1.getInt("admin");
					if (admin == 0 && cut_off == 0) { // 관리자가 아닐때~
						JOptionPane.showMessageDialog(null, "로그인에 성공하였습니다!", "성공",
								JOptionPane.INFORMATION_MESSAGE);

						// 객체생성하기.
						sql = "select * from user where id=?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, id);
						ResultSet rs2 = pstmt.executeQuery();

						while (rs2.next()) {
							nickName = rs2.getString(3);
							photo = rs2.getBytes(4);
						}

						User thisUser = new User(id, pw, nickName, photo);
						
						//창넘어가면서 User 전달하기,
						UserWindow userWindow = new UserWindow(thisUser);
						dispose();
						
						System.out.println("현재 로그인정보는 ID :"+ thisUser.getId() +"입니다.");
					} else if(admin == 0 && cut_off == 1){
						JOptionPane.showMessageDialog(null,
								"관리자에 의하여 로그인이 차단되었습니다. \n 문의 - > email:digidigi@digi.com", "에러",
								JOptionPane.ERROR_MESSAGE);
						
					}else if(admin == 1) {
						JOptionPane.showMessageDialog(null, "관리자로 로그인하셨습니다!", "성공", 
								JOptionPane.INFORMATION_MESSAGE);

						// 객체생성하기.
						sql = "select * from user where id=?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, id);
						ResultSet rs2 = pstmt.executeQuery();

						while (rs2.next()) {
							nickName = rs2.getString(3);
							photo = rs2.getBytes(4);
						}

						User thisUser = new User(id, pw, nickName, photo);
						
						//창넘어가면서 User 전달하기,
						AdminWindow adminWindow = new AdminWindow(thisUser);
						dispose();
						
						System.out.println("현재 로그인정보는 ID :"+ thisUser.getId() +"입니다.");
					}
				} else {
					 // 사용자 정보가 데이터베이스에 없는 경우
                    JOptionPane.showMessageDialog(null,
                            "ID or Password Error! \n 회원가입이 안되어있다면, 먼저 진행해주세요.", "에러",
                            JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	


	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			login();
		} else if (e.getSource() == joinButton) {
			setLocationRelativeTo(null);
			JoinWindow joinwindow = new JoinWindow();

			// 현재(LoginWindow) 창 닫기
			dispose();
		}

	}

}
