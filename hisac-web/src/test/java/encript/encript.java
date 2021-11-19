package encript;

import org.junit.Test;
import tw.gov.mohw.hisac.web.WebCrypto;


public class encript {
	
	final static String CodeParameter = "ZK5P789RHNPY6BYX";
	final static String IvParameter = "3MYRZDDT2T89NNGD";


	@Test
	public void test1() {
		
		String code = "K9+g4nZcYUNHpIJt1b9JAQ==";
		System.out.println("test");
		
//		System.out.println(WebCrypto.getRandomCode(8));
		
		
		
		System.out.println(WebCrypto.aesDecrypt(CodeParameter, IvParameter, code));
		
//		WebCrypto.aesDecrypt(CodeParameter, IvParameter, code);

	}

}