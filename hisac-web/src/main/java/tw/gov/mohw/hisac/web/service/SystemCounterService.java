package tw.gov.mohw.hisac.web.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import tw.gov.mohw.hisac.web.dao.SystemCounterDAO;
import tw.gov.mohw.hisac.web.domain.SystemCounter;

/**
 * 系統流量統計
 */
@Service
public class SystemCounterService {
	@Autowired
	SystemCounterDAO systemCounterDAO;

	public static Long systemCount = null;

	/**
	 * 新增系統流量統計資料
	 * 
	 * @param ip
	 *            ip
	 */
	@Async
	public void insert(String ip) {
		SystemCounter systemCounter = new SystemCounter();
		systemCounter.setIp(ip);
		systemCounter.setCreateTime(new Date());
		systemCounterDAO.insert(systemCounter);
		getTotal(false);
	}

	/**
	 * 取得系統流量統計資料筆數
	 * 
	 * @return 系統流量統計資料筆數
	 */
	public long getTotal() {
		return getTotal(true);
	}

	/**
	 * 取得系統流量統計資料筆數
	 * 
	 * @param fromCache
	 *            boolean
	 * @return 系統流量統計資料筆數
	 */
	public long getTotal(boolean fromCache) {
		if (systemCount == null || fromCache == false) {
			systemCount = systemCounterDAO.getTotal();
		}
		return systemCount;
	}
}
