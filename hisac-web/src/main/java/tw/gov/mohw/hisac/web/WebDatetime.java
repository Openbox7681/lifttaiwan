package tw.gov.mohw.hisac.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期時間格式化
 */
public class WebDatetime {
	final static Logger logger = LoggerFactory.getLogger(WebDatetime.class);

//	private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static long ONE_SECOND_IN_MILLIS = 1000;
	private final static long ONE_MINUTE_IN_MILLIS = 60000;
	public final static String MAX_DATETIME = "9999-12-31 23:59:59.9999";
	public final static String MIN_DATETIME = "1753-01-01 00:00:00.0000";

	/**
	 * 取得日期時間字串 若是null則回傳空字串
	 * 
	 * @param datetime
	 *            日期時間
	 * @return 日期時間字串(yyyy-MM-dd HH:mm:ss)
	 */
	public static String toString(Date datetime) {
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = "";
		try {
			if (datetime != null) {
				result = simpleDateFormat.format(datetime);
			}
		} catch (Exception e) {
			////e.printStackTrace();
			//logger.error(e.getMessage());
		}
		return result;
	}

	/**
	 * 取得日期時間字串 若是null則回傳空字串
	 * 
	 * @param datetime
	 *            日期時間
	 * @param format
	 *            格式
	 * @return 日期時間字串
	 */
	public static String toString(Date datetime, String format) {
		String result = "";
		try {
			if (datetime != null) {
				result = new SimpleDateFormat(format).format(datetime);
			}
		} catch (Exception e) {
			////e.printStackTrace();
			//logger.error(e.getMessage());
		}
		return result;
	}

	/**
	 * 轉換日期時間
	 * 
	 * @param datetime
	 *            日期時間字串
	 * @return 日期時間(yyyy-MM-dd HH:mm:ss)
	 */
	public static Date parse(String datetime) {
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date result = new Date();
		try {
			result = simpleDateFormat.parse(datetime);
		} catch (Exception e) {
			////e.printStackTrace();
			//logger.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 轉換開始日期時間
	 * 
	 * @param datetime
	 *            日期時間字串
	 * @return 日期時間(yyyy-MM-dd HH:mm:ss)
	 */
	public static Date parseSdate(String datetime) {
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date result = new Date();
		try {
			result = simpleDateFormat.parse(datetime+" 00:00:00.0000");
		} catch (Exception e) {
			////e.printStackTrace();
			//logger.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 轉換結束日期時間
	 * 
	 * @param datetime
	 *            日期時間字串
	 * @return 日期時間(yyyy-MM-dd HH:mm:ss)
	 */
	public static Date parseEdate(String datetime) {
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date result = new Date();
		try {
			result = simpleDateFormat.parse(datetime+" 23:59:59.9999");
		} catch (Exception e) {
			////e.printStackTrace();
			//logger.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 轉換日期時間
	 * 
	 * @param datetime
	 *            日期時間字串
	 * @param format
	 *            格式
	 * @return 日期時間
	 */
	public static Date parse(String datetime, String format) {
		Date result = new Date();
		try {
			result = new SimpleDateFormat(format).parse(datetime);
		} catch (Exception e) {
			result = new Date();
			////e.printStackTrace();
			//logger.error(e.getMessage());
		}
		return result;
	}

	/**
	 * 對傳入時間增加秒數
	 * 
	 * @param datetime
	 *            傳入時間
	 * @param seconds
	 *            增加秒數
	 * @return 已增加後的時間
	 */
	public static Date addSeconds(Date datetime, long seconds) {
		Date result = new Date();
		try {
			if (datetime == null) {
				datetime = result;
			}
			long nowTime = datetime.getTime();
			result = new Date(nowTime + (seconds * ONE_SECOND_IN_MILLIS));
		} catch (Exception e) {
			////e.printStackTrace();
			//logger.error(e.getMessage());
		}
		return result;
	}

	/**
	 * 對傳入時間增加分鐘
	 * 
	 * @param datetime
	 *            傳入時間
	 * @param minutes
	 *            增加分鐘
	 * @return 已增加後的時間
	 */
	public static Date addMinutes(Date datetime, long minutes) {
		Date result = new Date();
		try {
			if (datetime == null) {
				datetime = result;
			}
			long nowTime = datetime.getTime();
			result = new Date(nowTime + (minutes * ONE_MINUTE_IN_MILLIS));
		} catch (Exception e) {
			////e.printStackTrace();
			//logger.error(e.getMessage());
		}
		return result;
	}

	/**
	 * 對傳入時間增加天數
	 * 
	 * @param datetime
	 *            傳入時間
	 * @param days
	 *            增加天數
	 * @return 已增加後的時間
	 */
	public static Date addDays(Date datetime, long days) {
		Date result = new Date();
		try {
			if (datetime == null) {
				datetime = result;
			}
			long nowTime = datetime.getTime();
			result = new Date(nowTime + (days * 24 * 60 * ONE_MINUTE_IN_MILLIS));
		} catch (Exception e) {
			////e.printStackTrace();
			//logger.error(e.getMessage());
		}
		return result;
	}

	/**
	 * 
	 * @param year
	 *            int 年份
	 * @param month
	 *            int 月份
	 * 
	 * @return int 某年某月的最后一天 需要注意的是：月份是從0開始的，比如說如果輸入5的話，實際上顯示的是4月份的最後一天，千萬不要搞錯了哦
	 */
	public static int getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, (month - 1));
		// 某年某月的最后一天
		return cal.getActualMaximum(Calendar.DATE);
	}

}
