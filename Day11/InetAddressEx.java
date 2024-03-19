//package Day11;
//import java.net.*;
//
//public class InetAddressEx {
//	
//	public static void main(String[] args)
//		throws UnknownHostException { //InetAddress 이용시 반드시예외처리.
//		
//		InetAddress iaddr= InetAddress.getLocalHost();
//		System.out.printf("호스트 이름 : %s %n", iaddr.getHostName());
//		System.out.printf("호스트 IP주소 : %s %n", iaddr.getHostAddress());
//		
//		iaddr = InetAddress.getByName("java.sun.com");
//		System.out.printf("호스트 이름 : %s %n", iaddr.getHostName());
//		System.out.printf("호스트 IP주소 : %s %n", iaddr.getHostAddress());
//		
//        InetAddress sw[] = InetAddress.getAllByName("www.naver.com");
//        for (InetAddress temp_sw : sw) {
////            System.out.printf("호스트 이름 : %s %n", 
////            		temp_sw.getHostName());
////            System.out.printf("호스트 IP주소 : %s %n", 
////            		temp_sw.getHostAddress());
//            System.out.println(temp_sw.toString());
//        }
//	}
//}
