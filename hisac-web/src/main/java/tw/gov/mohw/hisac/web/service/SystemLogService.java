package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.SystemLogDAO;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.domain.SpOrgReport;
import tw.gov.mohw.hisac.web.domain.SpSigninCountTop10;
import tw.gov.mohw.hisac.web.domain.SpSystemLogByOrgTop5;
import tw.gov.mohw.hisac.web.domain.SpWebSiteLoad;
import tw.gov.mohw.hisac.web.domain.SystemLog;

/**
 * 操作記錄服務
 */
@Service
public class SystemLogService {
	final static Logger logger = LoggerFactory.getLogger(SystemLogService.class);

	@Autowired
	SystemLogDAO systemLogDAO;

	/**
	 * 新增SystemLog資料
	 * 
	 * @param controllerName
	 *            控制器名稱
	 * @param actionName
	 *            方法名稱
	 * @param inputValue
	 *            inputValue
	 * @param action
	 *            SystemLogVariable.Action
	 * @param status
	 *            SystemLogVariable.Status
	 * @param ipAddress
	 *            ipAddress
	 * @param memberAccount
	 *            memberAccount
	 */
	public void insert(String controllerName, String actionName, String inputValue, SystemLogVariable.Action action, SystemLogVariable.Status status, String ipAddress, String memberAccount) {
		try {
			Date now = new Date();
			SystemLog systemLog = new SystemLog();
			systemLog.setAppName(controllerName);
			systemLog.setFuncName(actionName);
			systemLog.setInputValue(inputValue);
			systemLog.setActionName(action.toString());
			systemLog.setStatus(status.toString());
			systemLog.setIp(ipAddress);
			systemLog.setCreateAccount(memberAccount);
			systemLog.setCreateTime(now);
			systemLogDAO.insert(systemLog);
		} catch (Exception e) {
			//logger.error(e.getMessage());
		}
	}
	/**
	 * 取得SystemLog資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return SystemLog資料
	 */
	public List<SystemLog> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return systemLogDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 取得SystemLog資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return SystemLog資料
	 */
	public List<SystemLog> getListForLoginLog(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return systemLogDAO.getListForLoginLog(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 取得SystemLog資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return SystemLog資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return systemLogDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * 取得SystemLog資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return SystemLog資料筆數
	 */
	public long getListSizeForLoginLog(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return systemLogDAO.getListSizeForLoginLog(obj);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public List<SpWebSiteLoad> getWebSiteLoad(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return systemLogDAO.getWebSiteLoad(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	public List<SpSigninCountTop10> getSignTop10(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return systemLogDAO.getSignTop10(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<SpSystemLogByOrgTop5> getNewsByOrgTop5(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			obj.put("AppName", "p01_News");
			obj.put("FuncName", "QueryById");
			obj.put("ActionName", "Read");
			return systemLogDAO.getSpSystemLogByOrgTop5(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	public List<SpSystemLogByOrgTop5> getActivityByOrgTop5(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			obj.put("AppName", "p02_Activity");
			obj.put("FuncName", "QueryById");
			obj.put("ActionName", "Read");
			return systemLogDAO.getSpSystemLogByOrgTop5(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<SpSystemLogByOrgTop5> getAnaByOrgTop5(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			obj.put("AppName", "p04_Ana");
			obj.put("FuncName", "QueryById");
			obj.put("ActionName", "Read");
			return systemLogDAO.getSpSystemLogByOrgTop5(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<SpSystemLogByOrgTop5> getSecBuzzerByOrgTop5(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			obj.put("AppName", "p06_SecBuzzer");
			obj.put("FuncName", "QueryById");
			obj.put("ActionName", "Read");
			return systemLogDAO.getSpSystemLogByOrgTop5(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<SpSystemLogByOrgTop5> getSpSystemLogByOrg(String json) {
		try {
			JSONObject obj = new JSONObject(json);			
			return systemLogDAO.getSpSystemLogByOrg(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<SpOrgReport> getOrgReport(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return systemLogDAO.getOrgReport(obj);
		} catch (Exception e) {
			return null;
		}
	}
}