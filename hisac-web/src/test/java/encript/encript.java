package encript;

import org.junit.Test;
import tw.gov.mohw.hisac.web.WebCrypto;


public class encript {
	
	final static String CodeParameter = "ZK5P789RHNPY6BYX";
	final static String IvParameter = "3MYRZDDT2T89NNGD";


	@Test
	public void test1() {
		
		String code = "6L4MKKolI17feAgmeiGAHQ==";
		System.out.println("test");
		
//		System.out.println(WebCrypto.getRandomCode(8));
		
		
		
		System.out.println(WebCrypto.aesDecrypt(CodeParameter, IvParameter, code));
		
//		WebCrypto.aesDecrypt(CodeParameter, IvParameter, code);
		
		String plainText = "123";

		
		
		WebCrypto.aesEncrypt(CodeParameter, IvParameter, plainText);
		
//		System.out.println(WebCrypto.aesDecrypt(CodeParameter, IvParameter, code));
		
		System.out.println(WebCrypto.aesEncrypt(CodeParameter, IvParameter, plainText));



	}

}