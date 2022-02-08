package encript;

import org.junit.Test;
import tw.gov.mohw.hisac.web.;


public class encript {
	
	final static String CodeParameter = "ZK5P789RHNPY6BYX";
	final static String IvParameter = "3MYRZDDT2T89NNGD";


	@Test
	public void test1() {
		
		String code = "ag0NjThLOVdVtHIXejEezIUIXGfn/f6+11ah6sGAwDw=";
		
//		System.out.println(.getRandomCode(8));
		
		
		
		System.out.println(WebCrypto.aesDecrypt(CodeParameter, IvParameter, code));
		
//		WebCrypto.aesDecrypt(CodeParameter, IvParameter, code);
		
		
		//明文
		String plainText = "STPIwebdev202110";

		System.out.println(plainText);

		
		
		WebCrypto.aesEncrypt(CodeParameter, IvParameter, plainText);
		
//		System.out.println(WebCrypto.aesDecrypt(CodeParameter, IvParameter, code));
		
		//加密後 code
		System.out.println(WebCrypto.aesEncrypt(CodeParameter, IvParameter, plainText));



	}

}