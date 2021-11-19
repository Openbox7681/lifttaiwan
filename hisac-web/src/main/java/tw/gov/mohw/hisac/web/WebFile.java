package tw.gov.mohw.hisac.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 檔案存取
 */
public class WebFile {
	final static Logger logger = LoggerFactory.getLogger(WebFile.class);

	/**
	 * 取得檔案
	 * 
	 * @param filePath
	 *            檔案路徑
	 * @return byte array
	 */
	public static byte[] readBytesFromFile(String filePath) {
		FileInputStream fileInputStream = null;
		byte[] bytesArray = null;
		try {
			File file = new File(filePath);
			bytesArray = new byte[(int) file.length()];
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bytesArray);

		} catch (IOException e) {
			////e.printStackTrace();
			//logger.error(e.getMessage());

		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					//e.printStackTrace();
					//logger.error(e.getMessage());
				}
			}
		}
		return bytesArray;
	}

	public static InputStream readPfxFromResource(String filePath) {
		InputStream inputStream = null;
		try {
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			inputStream = classLoader.getResourceAsStream(filePath);
		} catch (Exception e) {
			////e.printStackTrace();
			//logger.error(e.getMessage());

		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					//e.printStackTrace();
					//logger.error(e.getMessage());
				}
			}
		}
		return inputStream;
	}
}
