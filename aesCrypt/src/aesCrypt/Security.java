package aesCrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class Security {
	final static String CodeParameter = "ZK5P789RHNPY6BYX";
	final static String IvParameter = "3MYRZDDT2T89NNGD";

	public static void main(String[] args) {
		String cipherText = "";
		String plainText = "";
		cipherText = aesEncrypt(CodeParameter, IvParameter, "ji394_III");
		plainText = aesDecrypt(CodeParameter, IvParameter, cipherText);
		System.out.println(plainText + " => " + cipherText);
		
		cipherText = aesEncrypt(CodeParameter, IvParameter, "hisac-ji394_III");
		plainText = aesDecrypt(CodeParameter, IvParameter, cipherText);
		System.out.println(plainText + " => " + cipherText);
		
		cipherText = aesEncrypt(CodeParameter, IvParameter, "hisac-cert_ji394III");
		plainText = aesDecrypt(CodeParameter, IvParameter, cipherText);
		System.out.println(plainText + " => " + cipherText);
		
		cipherText = aesEncrypt(CodeParameter, IvParameter, "hisac123456");
		plainText = aesDecrypt(CodeParameter, IvParameter, cipherText);
		System.out.println(plainText + " => " + cipherText);
		
		cipherText = aesEncrypt(CodeParameter, IvParameter, "hisac123456789");
		plainText = aesDecrypt(CodeParameter, IvParameter, cipherText);
		System.out.println(plainText + " => " + cipherText);
	}

	/**
	 * AES加密
	 * 
	 * @param keyString
	 *            金鑰
	 * @param ivString
	 *            初始值
	 * @param plainText
	 *            明文
	 * @return 密文
	 */
	public static String aesEncrypt(String keyString, String ivString,
			String plainText) {
		try {
			IvParameterSpec iv = new IvParameterSpec(
					ivString.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(
					keyString.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(plainText.getBytes());
			return Base64.encodeBase64String(encrypted);
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * AES解密
	 * 
	 * @param keyString
	 *            金鑰
	 * @param ivString
	 *            初始值
	 * @param cipherText
	 *            密文
	 * @return 明文
	 */
	public static String aesDecrypt(String keyString, String ivString,
			String cipherText) {
		try {
			IvParameterSpec iv = new IvParameterSpec(
					ivString.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(
					keyString.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			byte[] original = cipher.doFinal(Base64.decodeBase64(cipherText));

			return new String(original);
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
}
