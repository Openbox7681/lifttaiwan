package tw.gov.mohw.hisac.web;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;
/**
 * BASE 64 Decoded File Upload
 */
public class BASE64DecodedMultipartFile implements MultipartFile {

	private final byte[] imgContent;

	public BASE64DecodedMultipartFile(byte[] imgContent) {
		this.imgContent = imgContent;
	}

	@Override
	public String getName() {
		// TODO - implementation depends on your requirements
		return null;
	}

	@Override
	public String getOriginalFilename() {
		// TODO - implementation depends on your requirements
		return null;
	}

	@Override
	public String getContentType() {
		// TODO - implementation depends on your requirements
		return null;
	}

	@Override
	public boolean isEmpty() {
		return imgContent != null && imgContent.length > 0;
	}

	@Override
	public long getSize() {
		return imgContent.length;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return imgContent;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(imgContent);
	}

	@SuppressWarnings("resource")
	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		new FileOutputStream(dest).write(imgContent);
	}
}
