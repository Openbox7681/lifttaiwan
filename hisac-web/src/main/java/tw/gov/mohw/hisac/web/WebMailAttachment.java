package tw.gov.mohw.hisac.web;

/**
 * 電子郵件夾檔
 */
public class WebMailAttachment {
	private String AttachmentName;
	private byte[] AttachmentBody;
	/**
	 * 取得夾檔名稱
	 * 
	 * @return 夾檔名稱
	 */
	public String getAttachmentName() {
		return AttachmentName;
	}
	/**
	 * 設定夾檔名稱
	 * 
	 * @param attachmentName
	 *            夾檔名稱
	 */
	public void setAttachmentName(String attachmentName) {
		AttachmentName = attachmentName;
	}
	/**
	 * 取得夾檔內容
	 * 
	 * @return 夾檔內容
	 */
	public byte[] getAttachmentBody() {
		return AttachmentBody;
	}
	/**
	 * 設定夾檔內容
	 * 
	 * @param attachmentBody
	 *            夾檔內容
	 */
	public void setAttachmentBody(byte[] attachmentBody) {
		AttachmentBody = attachmentBody;
	}
}
