//package Day10;
//
//import java.awt.*;
//import java.awt.event.*;
//
//public class ActionEventEx extends Frame implements ActionListener {
//	Panel p;
//	Button input, exit;
//	TextArea ta;
//	
//	public ActionEventEx() {
//		super("ActionEvent Test");
//		p = new Panel();
//		input = new Button("Apeach World!");
//		exit = new Button("Exit");
//		ta = new TextArea();
//		
//		input.addActionListener(this);
//		exit.addActionListener(this);
//		
//		p.add(input);
//		p.add(exit);
//		add(p,BorderLayout.NORTH);
//		add(ta,BorderLayout.CENTER);
//		
//		setBounds(300,300,300,200);
//		setVisible(true);
//	}
//	
//	public void actionPerformed(ActionEvent ae) {
//		String name;
//		name = ae.getActionCommand();
//		
//		if(name.equals("Apeach World!")) {
//			ta.append("Pipi : pi~~\n");
//		} else {
//				ta.append("Apeach World Exit!\n");
//				try {
//					Thread.sleep(2000);
//				}catch(Exception e) {}
//				
//				System.exit(0);
//			}
//		}
//	
//	public static void main(String[] args) {
//		new ActionEventEx();
//		
//	}
//}
