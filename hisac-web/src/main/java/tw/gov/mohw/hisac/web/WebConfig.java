package tw.gov.mohw.hisac.web;

/**
 * 網頁共用設定
 */
public class WebConfig {
	/**
	 * 網站位置
	 */
	public static String WEB_SITE_URL;
	/**
	 * 可容許錯誤次數
	 */
	public static int MAX_ERROR_TIMES;
	/**
	 * 錯誤次數到達時鎖定時間
	 */
	public static int LOCK_MINUTES;
	/**
	 * 工作階段結束幾分鐘前提醒
	 */
	public static int BEFORE_SESSION_TIMEOUT_MINUTES;
	/**
	 * 一次性密碼有效時間(秒)
	 */
	public static int OTP_SURVIVAL_TIME;
	/**
	 * 忘記密碼驗證碼有效時間
	 */
	public static int FORGOT_EXPIRE_MINUTES;
	/**
	 * 密碼歷程最小次數
	 */
	public static int HISTORY_TIMES;
	/**
	 * 密碼歷程最小天數
	 */
	public static int HISTORY_DAYS;
	/**
	 * 開發模式
	 */
	public static boolean DEVELOPMENT_MODE;
	/**
	 * 除錯模式
	 */
	public static boolean DEBUG_MODE;
	/**
	 * 是否發送簡訊
	 */
	public static boolean ENABLE_SMS;
	/**
	 * 是否寄送信件
	 */
	public static boolean ENABLE_SENDMAIL;
	/**
	 * PDF 預設字體
	 */
	public static String PDF_FONT;
	/**
	 * 是否包含數位簽章
	 */
	public static boolean ENABLE_DIGITAL_SIGNATURE;
	/**
	 * 憑證檔案
	 */
	public static String PFX_FILENAME;
	/**
	 * 憑證密碼
	 */
	public static String PFX_CODE;
	/**
	 * SMTP User Name
	 */
	public static String MAIL_USERNAME;
	/**
	 * SMTP User Password
	 */
	public static String MAIL_CODE;
	/**
	 * SMTP Host
	 */
	public static String MAIL_SMTP_HOST;
	/**
	 * SMTP Port
	 */
	public static String MAIL_SMTP_PORT;
	/**
	 * SMTP Auth
	 */
	public static String MAIL_SMTP_AUTH;
	/**
	 * SMTP Start TLS Enable
	 */
	public static String MAIL_SMTP_STARTTLS_ENABLE;
	/**
	 * Enable Google reCAPTCHA
	 */
	public static boolean ENABLE_GOOGLE_RECAPTCHA;
	/**
	 * Google reCAPTCHA Site Key
	 */
	public static String GOOGLE_RECAPTCHA_SITE_KEY;
	/**
	 * Google reCAPTCHA Security Key
	 */
	public static String GOOGLE_RECAPTCHA_SECURITY_KEY;
	/**
	 * NISAC Key Store
	 */
	public static String NISAC_KEY_STORE;
	/**
	 * NISAC Key Store P
	 */
	public static String NISAC_KEY_STORE_P;
	/**
	 * NISAC Trust Store
	 */
	public static String NISAC_TRUST_STORE;
	/**
	 * NISAC Trust Store P
	 */
	public static String NISAC_TRUST_STORE_P;
	/**
	 * NISAC Url
	 */
	public static String NISAC_URL;
	/**
	 * NISAC Member Id
	 */
	public static String NISAC_MEMBER_ID;
	/**
	 * NISAC Member P
	 */
	public static String NISAC_MEMBER_P;
	/**
	 * NISAC Member Dcn
	 */
	public static String NISAC_MEMBER_DCN;

	/**
	 * NCERT Key Store
	 */
	public static String NCERT_KEY_STORE;
	/**
	 * NCERT Key Store P
	 */
	public static String NCERT_KEY_STORE_P;
	/**
	 * NCERT Trust Store
	 */
	public static String NCERT_TRUST_STORE;
	/**
	 * NCERT Trust Store P
	 */
	public static String NCERT_TRUST_STORE_P;
	/**
	 * NCERT Url
	 */
	public static String NCERT_URL;
	/**
	 * NCERT Inbox Url
	 */	
	public static String NCERT_INBOX_URL;
	/**
	 * NCERT Member Id
	 */
	public static String NCERT_MEMBER_ID;
	/**
	 * NCERT Member P
	 */
	public static String NCERT_MEMBER_P;
	/**
	 * NCERT Member Dcn
	 */
	public static String NCERT_MEMBER_DCN;
	/**
	 * VADER UPLOAD URL
	 */
	public static String UPLOAD_URL;
	/**
	 * NVADER SEARCH URL
	 */
	public static String SEARCH_URL;

	/**
	 * 取得網站位置
	 * 
	 * @return 網站位置
	 */
	public String getWEB_SITE_URL() {
		return WEB_SITE_URL;
	}
	/**
	 * 設定網站位置
	 * 
	 * @param wEB_SITE_URL
	 *            網站位置
	 */
	public void setWEB_SITE_URL(String wEB_SITE_URL) {
		WEB_SITE_URL = wEB_SITE_URL;
	}
	/**
	 * 取得可容許錯誤次數
	 * 
	 * @return 可容許錯誤次數
	 */
	public int getMAX_ERROR_TIMES() {
		return MAX_ERROR_TIMES;
	}
	/**
	 * 設定可容許錯誤次數
	 * 
	 * @param mAX_ERROR_TIMES
	 *            可容許錯誤次數
	 */
	public void setMAX_ERROR_TIMES(int mAX_ERROR_TIMES) {
		MAX_ERROR_TIMES = mAX_ERROR_TIMES;
	}
	/**
	 * 取得錯誤次數到達時鎖定時間
	 * 
	 * @return 錯誤次數到達時鎖定時間
	 */
	public int getLOCK_MINUTES() {
		return LOCK_MINUTES;
	}
	/**
	 * 設定錯誤次數到達時鎖定時間
	 * 
	 * @param lOCK_MINUTES
	 *            錯誤次數到達時鎖定時間
	 */
	public void setLOCK_MINUTES(int lOCK_MINUTES) {
		LOCK_MINUTES = lOCK_MINUTES;
	}
	/**
	 * 取得工作階段結束幾分鐘前提醒
	 * 
	 * @return 工作階段結束幾分鐘前提醒
	 */
	public int getBEFORE_SESSION_TIMEOUT_MINUTES() {
		return BEFORE_SESSION_TIMEOUT_MINUTES;
	}
	/**
	 * 設定工作階段結束幾分鐘前提醒
	 * 
	 * @param bEFORE_SESSION_TIMEOUT_MINUTES
	 *            工作階段結束幾分鐘前提醒
	 */
	public void setBEFORE_SESSION_TIMEOUT_MINUTES(int bEFORE_SESSION_TIMEOUT_MINUTES) {
		BEFORE_SESSION_TIMEOUT_MINUTES = bEFORE_SESSION_TIMEOUT_MINUTES;
	}
	/**
	 * 取得一次性密碼有效時間
	 * 
	 * @return 一次性密碼有效時間(秒)
	 */
	public int getOTP_SURVIVAL_TIME() {
		return OTP_SURVIVAL_TIME;
	}
	/**
	 * 設定一次性密碼有效時間
	 * 
	 * @param oTP_SURVIVAL_TIME
	 *            一次性密碼有效時間(秒)
	 */
	public void setOTP_SURVIVAL_TIME(int oTP_SURVIVAL_TIME) {
		OTP_SURVIVAL_TIME = oTP_SURVIVAL_TIME;
	}
	/**
	 * 取得忘記密碼驗證碼有效時間
	 * 
	 * @return 忘記密碼驗證碼有效時間
	 */
	public int getFORGOT_EXPIRE_MINUTES() {
		return FORGOT_EXPIRE_MINUTES;
	}
	/**
	 * 設定忘記密碼驗證碼有效時間
	 * 
	 * @param fORGOT_EXPIRE_MINUTES
	 *            忘記密碼驗證碼有效時間
	 */
	public void setFORGOT_EXPIRE_MINUTES(int fORGOT_EXPIRE_MINUTES) {
		FORGOT_EXPIRE_MINUTES = fORGOT_EXPIRE_MINUTES;
	}
	/**
	 * 取得密碼歷程最小次數
	 * 
	 * @return 密碼歷程最小次數
	 */
	public int getHISTORY_TIMES() {
		return HISTORY_TIMES;
	}
	/**
	 * 設定密碼歷程最小次數
	 * 
	 * @param hISTORY_TIMES
	 *            密碼歷程最小次數
	 */
	public void setHISTORY_TIMES(int hISTORY_TIMES) {
		HISTORY_TIMES = hISTORY_TIMES;
	}
	/**
	 * 取得密碼歷程最小天數
	 * 
	 * @return 密碼歷程最小天數
	 */
	public int getHISTORY_DAYS() {
		return HISTORY_DAYS;
	}
	/**
	 * 設定密碼歷程最小天數
	 * 
	 * @param hISTORY_DAYS
	 *            密碼歷程最小天數
	 */
	public void setHISTORY_DAYS(int hISTORY_DAYS) {
		HISTORY_DAYS = hISTORY_DAYS;
	}
	/**
	 * 是否為開發模式
	 * 
	 * @return true: 開發模式 ; false: 正常模式
	 */
	public boolean getDEVELOPMENT_MODE() {
		return DEVELOPMENT_MODE;
	}
	/**
	 * 設定除錯模式
	 * 
	 * @param dEVELOPMENT_MODEG_MODE
	 *            是否為除錯模式
	 */
	public void setDEVELOPMENT_MODE(boolean dEVELOPMENT_MODEG_MODE) {
		DEVELOPMENT_MODE = dEVELOPMENT_MODEG_MODE;
	}
	/**
	 * 是否為除錯模式
	 * 
	 * @return true: 除錯模式 ; false: 正常模式
	 */
	public boolean getDEBUG_MODE() {
		return DEBUG_MODE;
	}
	/**
	 * 設定除錯模式
	 * 
	 * @param dEBUG_MODE
	 *            是否為除錯模式
	 */
	public void setDEBUG_MODE(boolean dEBUG_MODE) {
		DEBUG_MODE = dEBUG_MODE;
	}
	/**
	 * 是否發送簡訊
	 * 
	 * @return true: 發送簡訊 ; false: 不發送簡訊
	 */
	public boolean getENABLE_SMS() {
		return ENABLE_SMS;
	}
	/**
	 * 設定是否發送簡訊
	 * 
	 * @param eNABLE_SMS
	 *            是否發送簡訊
	 */
	public void setENABLE_SMS(boolean eNABLE_SMS) {
		ENABLE_SMS = eNABLE_SMS;
	}
	/**
	 * 是否發送信件
	 * 
	 * @return true: 發送信件 ; false: 不發送信件
	 */
	public boolean getENABLE_SENDMAIL() {
		return ENABLE_SENDMAIL;
	}
	/**
	 * 設定是否發送信件
	 * 
	 * @param eNABLE_SENDMAIL
	 *            是否發送簡訊
	 */
	public void setENABLE_SENDMAIL(boolean eNABLE_SENDMAIL) {
		ENABLE_SENDMAIL = eNABLE_SENDMAIL;
	}
	/**
	 * 取得PDF 預設字體路徑
	 * 
	 * @return PDF 預設字體路徑
	 */
	public String getPDF_FONT() {
		return PDF_FONT;
	}
	/**
	 * 設定PDF 預設字體路徑
	 * 
	 * @param pDF_FONT
	 *            PDF 預設字體路徑
	 */
	public void setPDF_FONT(String pDF_FONT) {
		PDF_FONT = pDF_FONT;
	}
	/**
	 * 是否包含數位簽章
	 * 
	 * @return true: 發送信件時使用數位簽章 ; false: 發送信件時不使用數位簽章
	 */
	public boolean getENABLE_DIGITAL_SIGNATURE() {
		return ENABLE_DIGITAL_SIGNATURE;
	}
	/**
	 * 設定是否包含數位簽章
	 * 
	 * @param eNABLE_DIGITAL_SIGNATURE
	 *            是否包含數位簽章
	 */
	public void setENABLE_DIGITAL_SIGNATURE(boolean eNABLE_DIGITAL_SIGNATURE) {
		ENABLE_DIGITAL_SIGNATURE = eNABLE_DIGITAL_SIGNATURE;
	}
	/**
	 * 取得憑證檔案名稱 取得檔案位置為src/main/resources路徑下
	 * 
	 * @return 憑證檔案名稱
	 */
	public String getPFX_FILENAME() {
		return PFX_FILENAME;
	}
	/**
	 * 設定憑證檔案名稱 檔案位置須放在src/main/resources路徑下
	 * 
	 * @param pFX_FILENAME
	 *            憑證檔案名稱
	 */
	public void setPFX_FILENAME(String pFX_FILENAME) {
		PFX_FILENAME = pFX_FILENAME;
	}
	/**
	 * 取得憑證密碼
	 * 
	 * @return 憑證密碼
	 */
	public String getPFX_CODE() {
		return PFX_CODE;
	}
	/**
	 * 設定憑證憑證密碼
	 * 
	 * @param pFX_CODE
	 *            憑證密碼
	 */
	public void setPFX_CODE(String pFX_CODE) {
		PFX_CODE = pFX_CODE;
	}
	/**
	 * 取得SMTP User Name
	 * 
	 * @return SMTP User Name
	 */
	public String getMAIL_USERNAME() {
		return MAIL_USERNAME;
	}
	/**
	 * 設定SMTP User Name
	 * 
	 * @param mAIL_USERNAME
	 *            SMTP User Name
	 */
	public void setMAIL_USERNAME(String mAIL_USERNAME) {
		MAIL_USERNAME = mAIL_USERNAME;
	}
	/**
	 * 取得SMTP User Password
	 * 
	 * @return SMTP User Password
	 */
	public String getMAIL_CODE() {
		return MAIL_CODE;
	}
	/**
	 * 設定SMTP User Password
	 * 
	 * @param mAIL_CODE
	 *            User Password
	 */
	public void setMAIL_CODE(String mAIL_CODE) {
		MAIL_CODE = mAIL_CODE;
	}
	/**
	 * 取得SMTP Host
	 * 
	 * @return SMTP Host
	 */
	public String getMAIL_SMTP_HOST() {
		return MAIL_SMTP_HOST;
	}
	/**
	 * 設定SMTP Host
	 * 
	 * @param mAIL_SMTP_HOST
	 *            SMTP Host
	 */
	public void setMAIL_SMTP_HOST(String mAIL_SMTP_HOST) {
		MAIL_SMTP_HOST = mAIL_SMTP_HOST;
	}
	/**
	 * 取得SMTP Port
	 * 
	 * @return SMTP Port
	 */
	public String getMAIL_SMTP_PORT() {
		return MAIL_SMTP_PORT;
	}
	/**
	 * 設定SMTP Port
	 * 
	 * @param mAIL_SMTP_PORT
	 *            SMTP Port
	 */
	public void setMAIL_SMTP_PORT(String mAIL_SMTP_PORT) {
		MAIL_SMTP_PORT = mAIL_SMTP_PORT;
	}
	/**
	 * 取得SMTP Auth
	 * 
	 * @return SMTP Auth
	 */
	public String MAIL_SMTP_AUTH() {
		return MAIL_SMTP_AUTH;
	}
	/**
	 * 設定SMTP Auth
	 * 
	 * @param mAIL_SMTP_AUTH
	 *            SMTP Auth
	 */
	public void setMAIL_SMTP_AUTH(String mAIL_SMTP_AUTH) {
		MAIL_SMTP_AUTH = mAIL_SMTP_AUTH;
	}
	/**
	 * 取得SMTP Start TLS Enable
	 * 
	 * @return SMTP Start TLS Enable
	 */
	public String getMAIL_SMTP_STARTTLS_ENABLE() {
		return MAIL_SMTP_STARTTLS_ENABLE;
	}
	/**
	 * 設定SMTP Start TLS Enable
	 * 
	 * @param mAIL_SMTP_STARTTLS_ENABLE
	 *            SMTP Start TLS Enable
	 */
	public void setMAIL_SMTP_STARTTLS_ENABLE(String mAIL_SMTP_STARTTLS_ENABLE) {
		MAIL_SMTP_STARTTLS_ENABLE = mAIL_SMTP_STARTTLS_ENABLE;
	}
	/**
	 * 是否啟用Google reCAPTCHA
	 * 
	 * @return Is Google reCAPTCHA Enabled
	 */
	public boolean getENABLE_GOOGLE_RECAPTCHA() {
		return ENABLE_GOOGLE_RECAPTCHA;
	}
	/**
	 * Set Google reCAPTCHA Enabled or Disable
	 * 
	 * @param Is
	 *            Google reCAPTCHA Enabled 是否啟用Google reCAPTCHA
	 */
	public void setENABLE_GOOGLE_RECAPTCHA(boolean eNABLE_GOOGLE_RECAPTCHA) {
		ENABLE_GOOGLE_RECAPTCHA = eNABLE_GOOGLE_RECAPTCHA;
	}
	/**
	 * 取得Google reCAPTCHA Site Key
	 * 
	 * @return Google reCAPTCHA Site Key
	 */
	public String getGOOGLE_RECAPTCHA_SITE_KEY() {
		return GOOGLE_RECAPTCHA_SITE_KEY;
	}
	/**
	 * 設定Google reCAPTCHA Site Key
	 * 
	 * @param gOOGLE_RECAPTCHA_SITE_KEY
	 *            Google reCAPTCHA Site Key
	 */
	public void setGOOGLE_RECAPTCHA_SITE_KEY(String gOOGLE_RECAPTCHA_SITE_KEY) {
		GOOGLE_RECAPTCHA_SITE_KEY = gOOGLE_RECAPTCHA_SITE_KEY;
	}
	/**
	 * 取得Google reCAPTCHA Security Key
	 * 
	 * @return Google reCAPTCHA Security Key
	 */
	public String getGOOGLE_RECAPTCHA_SECURITY_KEY() {
		return GOOGLE_RECAPTCHA_SECURITY_KEY;
	}
	/**
	 * 設定Google reCAPTCHA Security Key
	 * 
	 * @param gOOGLE_RECAPTCHA_SECURITY_KEY
	 *            Google reCAPTCHA Security Key
	 */
	public void setGOOGLE_RECAPTCHA_SECURITY_KEY(String gOOGLE_RECAPTCHA_SECURITY_KEY) {
		GOOGLE_RECAPTCHA_SECURITY_KEY = gOOGLE_RECAPTCHA_SECURITY_KEY;
	}

	/**
	 * 取得NISAC Key Store
	 * 
	 * @return NISAC Key Store
	 */
	public String getNISAC_KEY_STORE() {
		return NISAC_KEY_STORE;
	}
	/**
	 * 設定NISAC Key Store
	 * 
	 * @param nISAC_KEY_STORE
	 *            NISAC Key Store
	 */
	public void setNISAC_KEY_STORE(String nISAC_KEY_STORE) {
		NISAC_KEY_STORE = nISAC_KEY_STORE;
	}
	/**
	 * 取得NISAC Key Store P
	 * 
	 * @return NISAC Key Store P
	 */
	public String getNISAC_KEY_STORE_P() {
		return NISAC_KEY_STORE_P;
	}
	/**
	 * 設定NISAC Key Store P
	 * 
	 * @param nISAC_KEY_STORE_P
	 *            NISAC Key Store P
	 */
	public void setNISAC_KEY_STORE_P(String nISAC_KEY_STORE_P) {
		NISAC_KEY_STORE_P = nISAC_KEY_STORE_P;
	}

	/**
	 * 取得NISAC Trust Store
	 * 
	 * @return NISAC Trust Store
	 */
	public String getNISAC_TRUST_STORE() {
		return NISAC_TRUST_STORE;
	}
	/**
	 * 設定NISAC Trust Store
	 * 
	 * @param nISAC_TRUST_STORE
	 *            NISAC Trust Store
	 */
	public void setNISAC_TRUST_STORE(String nISAC_TRUST_STORE) {
		NISAC_TRUST_STORE = nISAC_TRUST_STORE;
	}
	/**
	 * 取得NISAC Trust Store P
	 * 
	 * @return NISAC Trust Store P
	 */
	public String getNISAC_TRUST_STORE_P() {
		return NISAC_TRUST_STORE_P;
	}
	/**
	 * 設定NISAC Trust Store P
	 * 
	 * @param nISAC_TRUST_STORE_P
	 *            NISAC Trust Store P
	 */
	public void setNISAC_TRUST_STORE_P(String nISAC_TRUST_STORE_P) {
		NISAC_TRUST_STORE_P = nISAC_TRUST_STORE_P;
	}

	/**
	 * 取得NISAC Url
	 * 
	 * @return NISAC Url
	 */
	public String getNISAC_URL() {
		return NISAC_URL;
	}
	/**
	 * 設定NISAC URL
	 * 
	 * @param nISAC_URL
	 *            NISAC Url
	 */
	public void setNISAC_URL(String nISAC_URL) {
		NISAC_URL = nISAC_URL;
	}

	/**
	 * 取得NISAC Member Id
	 * 
	 * @return NISAC Member Id
	 */
	public String getNISAC_MEMBER_ID() {
		return NISAC_MEMBER_ID;
	}
	/**
	 * 設定NISAC Member Id
	 * 
	 * @param nISAC_MEMBER_ID
	 *            NISAC Member Id
	 */
	public void setNISAC_MEMBER_ID(String nISAC_MEMBER_ID) {
		NISAC_MEMBER_ID = nISAC_MEMBER_ID;
	}

	/**
	 * 取得NISAC Member P
	 * 
	 * @return NISAC Member P
	 */
	public String getNISAC_MEMBER_P() {
		return NISAC_MEMBER_P;
	}
	/**
	 * 設定NISAC Member P
	 * 
	 * @param nISAC_MEMBER_P
	 *            NISAC Member P
	 */
	public void setNISAC_MEMBER_P(String nISAC_MEMBER_P) {
		NISAC_MEMBER_P = nISAC_MEMBER_P;
	}

	/**
	 * 取得NISAC Member Dcn
	 * 
	 * @return NISAC Member Dcn
	 */
	public String getNISAC_MEMBER_DCN() {
		return NISAC_MEMBER_DCN;
	}
	/**
	 * 設定NISAC Member Dcn
	 * 
	 * @param nISAC_MEMBER_DCN
	 *            NISAC Member Dcn
	 */
	public void setNISAC_MEMBER_DCN(String nISAC_MEMBER_DCN) {
		NISAC_MEMBER_DCN = nISAC_MEMBER_DCN;
	}

	/**
	 * 取得NCERT Key Store
	 * 
	 * @return NCERT Key Store
	 */
	public String getNCERT_KEY_STORE() {
		return NCERT_KEY_STORE;
	}
	/**
	 * 設定NCERT Key Store
	 * 
	 * @param nCERT_KEY_STORE
	 *            NCERT Key Store
	 */
	public void setNCERT_KEY_STORE(String nCERT_KEY_STORE) {
		NCERT_KEY_STORE = nCERT_KEY_STORE;
	}
	/**
	 * 取得NCERT Key Store P
	 * 
	 * @return NCERT Key Store P
	 */
	public String getNCERT_KEY_STORE_P() {
		return NCERT_KEY_STORE_P;
	}
	/**
	 * 設定NCERT Key Store P
	 * 
	 * @param nCERT_KEY_STORE_P
	 *            NCERT Key Store P
	 */
	public void setNCERT_KEY_STORE_P(String nCERT_KEY_STORE_P) {
		NCERT_KEY_STORE_P = nCERT_KEY_STORE_P;
	}

	/**
	 * 取得NCERT Trust Store
	 * 
	 * @return NCERT Trust Store
	 */
	public String getNCERT_TRUST_STORE() {
		return NCERT_TRUST_STORE;
	}
	/**
	 * 設定NCERT Trust Store
	 * 
	 * @param nCERT_TRUST_STORE
	 *            NCERT Trust Store
	 */
	public void setNCERT_TRUST_STORE(String nCERT_TRUST_STORE) {
		NCERT_TRUST_STORE = nCERT_TRUST_STORE;
	}
	/**
	 * 取得NCERT Trust Store P
	 * 
	 * @return NCERT Trust Store P
	 */
	public String getNCERT_TRUST_STORE_P() {
		return NCERT_TRUST_STORE_P;
	}
	/**
	 * 設定NCERT Trust Store P
	 * 
	 * @param nCERT_TRUST_STORE_P
	 *            NCERT Trust Store P
	 */
	public void setNCERT_TRUST_STORE_P(String nCERT_TRUST_STORE_P) {
		NCERT_TRUST_STORE_P = nCERT_TRUST_STORE_P;
	}

	/**
	 * 取得NCERT Url
	 * 
	 * @return NCERT Url
	 */
	public String getNCERT_URL() {
		return NCERT_URL;
	}
	/**
	 * 設定NCERT URL
	 * 
	 * @param nCERT_URL
	 *            NCERT Url
	 */
	public void setNCERT_URL(String nCERT_URL) {
		NCERT_URL = nCERT_URL;
	}

	/**
	 * 取得NCERT Inbox Url
	 * 
	 * @return NCERT Inbox Url
	 */
	public String getNCERT_INBOX_URL() {
		return NCERT_INBOX_URL;
	}
	/**
	 * 設定NCERT INBOX URL
	 * 
	 * @param nCERT_INBOX_URL
	 *            NCERT Inbox Url
	 */
	public void setNCERT_INBOX_URL(String nCERT_INBOX_URL) {
		NCERT_INBOX_URL = nCERT_INBOX_URL;
	}

	/**
	 * 取得NCERT Member Id
	 * 
	 * @return NCERT Member Id
	 */
	public String getNCERT_MEMBER_ID() {
		return NCERT_MEMBER_ID;
	}
	/**
	 * 設定NCERT Member Id
	 * 
	 * @param nCERT_MEMBER_ID
	 *            NCERT Member Id
	 */
	public void setNCERT_MEMBER_ID(String nCERT_MEMBER_ID) {
		NCERT_MEMBER_ID = nCERT_MEMBER_ID;
	}

	/**
	 * 取得NCERT Member P
	 * 
	 * @return NCERT Member P
	 */
	public String getNCERT_MEMBER_P() {
		return NCERT_MEMBER_P;
	}
	/**
	 * 設定NCERT Member P
	 * 
	 * @param nCERT_MEMBER_P
	 *            NCERT Member P
	 */
	public void setNCERT_MEMBER_P(String nCERT_MEMBER_P) {
		NCERT_MEMBER_P = nCERT_MEMBER_P;
	}

	/**
	 * 取得NCERT Member Dcn
	 * 
	 * @return NCERT Member Dcn
	 */
	public String getNCERT_MEMBER_DCN() {
		return NCERT_MEMBER_DCN;
	}
	
	
	
	/**
	 * 設定NCERT Member Dcn
	 * 
	 * @param nCERT_MEMBER_DCN
	 *            NCERT Member Dcn
	 */
	public void setNCERT_MEMBER_DCN(String nCERT_MEMBER_DCN) {
		NCERT_MEMBER_DCN = nCERT_MEMBER_DCN;
	}
	

	/**
	 * 取得UPLOAD_URL
	 * 
	 * @return UPLOAD_URL
	 */
	public String getUPLOAD_URL() {
		return UPLOAD_URL;
	}
	/**
	 * 設定UPLOAD_URL
	 * 
	 * @param UPLOAD_URL
	 *            UPLOAD_URL
	 */
	public void setUPLOAD_URL(String uPLOAD_URL) {
		UPLOAD_URL = uPLOAD_URL;
	}
	

	/**
	 * 取得SEARCH_URL
	 * 
	 * @return SEARCH_URL
	 */
	public String getSEARCH_URL() {
		return SEARCH_URL;
	}
	/**
	 * 設定SEARCH_URL
	 * 
	 * @param SEARCH_URL
	 *            SEARCH_URL
	 */
	public void setSEARCH_URL(String sEARCH_URL) {
		SEARCH_URL = sEARCH_URL;
	}
	
	
}
