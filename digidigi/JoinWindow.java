package digidigi;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JoinWindow extends JFrame implements ActionListener{
	private JTextField idField;
	private JPasswordField pwField;
	private JTextField nickField;
	private JButton joinButton;
	private JButton proButton;
	private JLabel profile;
	private byte[] imageData;
	private String sql;
	PreparedStatement pstmt;
	Connection conn;
	
	public JoinWindow() {
		//회원가입창 Frame.
		setTitle("Join");
		setSize(400,550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		//JVM에서 완전한 창닫기. 닫기버튼 클릭시 창 종료.
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		add(panel);
		placeComponents(panel);//패널에 컴포넌트 배치
		
	    ImageIcon icon = new ImageIcon(getClass().getResource("/css/talk.png"));
	    // 프레임의 아이콘으로 설정
	    setIconImage(icon.getImage());
		setVisible(true);
		//프레임을 화면에 보이도록 설정
	}

	//패널에 컴포넌트들을 배치하는 메서드
	private void placeComponents(JPanel panel) {
		//패널의 레이아웃을 null로 설정하여 수동으로 컴포넌트의 위치와 크기지정.
		panel.setLayout(null);
		

		//Join 텍스트 생성.
		JLabel joinLabel = new JLabel("JOIN");
		joinLabel.setBounds(159,50,80,25);
		//setBounds(x, y, w, h) x좌표, y좌표, 가로,세로 크기
		joinLabel.setFont(joinLabel.getFont().deriveFont(25f));
		panel.add(joinLabel);
		
		profile = new JLabel("profile");
		profile.setBounds(165,90,80,80);
		panel.add(profile);
		
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/css/add.png"));
		proButton = new JButton(addIcon);
		proButton.addActionListener(this);
		
		proButton.setContentAreaFilled(false); //기존버튼디자인 제거 
		proButton.setBorderPainted(false);
		proButton.setOpaque(false);
		proButton.setBounds(155,180,50,20);
		panel.add(proButton);
		//ID라벨 생성
		JLabel idLabel = new JLabel("ID :");
		idLabel.setBounds(90,220,80,25);
		//setBounds(x, y, w, h) x좌표, y좌표, 가로,세로 크기
		panel.add(idLabel);
		
		//ID입력 필드 생성 및 위치 생성.
		idField = new JTextField(20);
		idField.setBounds(130,220,165,25);
		panel.add(idField);
		
		//비밀번호 라벨 생성 및 위치 생성.
		JLabel pwLabel = new JLabel("Password :");
		pwLabel.setBounds(50,260,80,25); //크기 설정
		panel.add(pwLabel);
		
		//비밀번호 입력 필드 생성 및 위치 생성.
		pwField = new JPasswordField(20);
		pwField.setBounds(130,260,165,25);
		panel.add(pwField);
		
		//닉네임 라벨 생성 및 위치 생성.
		JLabel nickLabel = new JLabel("nickName :");
		nickLabel.setBounds(45,300,80,25); //크기 설정
		panel.add(nickLabel);
		
		//닉네임 입력 필드 생성 및 위치 생성.
		nickField = new JTextField(20);
		nickField.setBounds(130,300,165,25);
		panel.add(nickField);
		
		//회원가입 버튼 생성 및 위치 생성.
		ImageIcon joinIcon = new ImageIcon(getClass().getResource("/css/join1.png"));
		joinButton = new JButton(joinIcon);
		joinButton.setBounds(150,360,80,90);
		joinButton.setContentAreaFilled(false); //기존버튼디자인 제거 
		joinButton.setBorderPainted(false); // 기존버튼디자인 제거
		//버튼 클릭 시 이벤트 리스너.
		joinButton.addActionListener(this);
		panel.add(joinButton);
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == joinButton) { //회원가입버튼 클릭시.
		String id = idField.getText();
		char[] charPw = pwField.getPassword(); // getPassword는 char타입으로만 get가능.
		String pw = new String(charPw); // String type으로 전환.
		String nickName = nickField.getText();
		
		conn = DbConnect.getConn().getDb();
		
		sql = "insert into user (id, pw, nickName, photo) values (?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql); //join 쿼리문 작성
			pstmt.setString(1,id);
			pstmt.setString(2,pw);
			pstmt.setString(3,nickName);
			pstmt.setBytes(4,imageData);
			
			pstmt.executeUpdate();
			
            dispose();
            LoginWindow loginWindow = new LoginWindow();
            
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		} else if(e.getSource()==proButton) {
			JFileChooser add = new JFileChooser();
			int result = add.showOpenDialog(this);
			  if (result == add.APPROVE_OPTION) {
		            File selectFile = add.getSelectedFile();
		            try {
		                // 선택한 이미지 파일 로드
		                Image image = ImageIO.read(selectFile);
		                // 이미지를 JLabel에 설정하여 표시
		                imageData = Files.readAllBytes(selectFile.toPath());
		                profile.setIcon(new ImageIcon(image));
		                profile.setText("");
	
		                
		                
		                } catch (Exception ex) {
		                ex.printStackTrace();
		            }
			  }
		}
	}
}

