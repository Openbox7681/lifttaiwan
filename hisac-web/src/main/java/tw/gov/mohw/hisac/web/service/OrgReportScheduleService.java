package tw.gov.mohw.hisac.web.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.OrgReportScheduleDAO;
import tw.gov.mohw.hisac.web.domain.MemberReportSchedule;
import tw.gov.mohw.hisac.web.domain.OrgReportSchedule;
/**
 * 醫院層級資料維護服務
 */
@Service
public class OrgReportScheduleService {
	@Autowired
	OrgReportScheduleDAO orgReportScheduleDAO;
	
	

	/**
	 * 刪除會員統計報表資料
	 * 
	 */
	
	public boolean delete(Long id) {
		try {
			OrgReportSchedule entity = orgReportScheduleDAO.get(id);
			orgReportScheduleDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
	
	
	
	

	/**
	 * 取得報表排程狀態資料
	 * 
	 * @return 醫院層級資料
	 */
	public List<OrgReportSchedule> getAll() {
		List<OrgReportSchedule> entitys = orgReportScheduleDAO.getAll();
		return entitys;
	}

	/**
	 * 取得報表排程狀態資料
	 * 
	 * @param json
	 *            報表排程狀態資料
	 * @return 報表排程狀態資料
	 */
	public List<OrgReportSchedule> getList(String json) {
		List<OrgReportSchedule> entitys = orgReportScheduleDAO.getList(json);
		return entitys;
	}

	/**
	 * 取得報表排程狀態資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 報表排程狀態資料筆數
	 */
	public long getOrgReportScheduleListSize(String json) {
		return orgReportScheduleDAO.getListSize(json);
	}
	
	/**
	 * 取得報表排程最新時間
	 * 
	 * @param json
	 *            報表排程狀態資料
	 * @return 報表排程狀態資料
	 */
	
	public String getLastScheduleTime() {
		return orgReportScheduleDAO.getLastScheduleTime();
		
	};

	/**
	 * 新增組織報表排程狀態資料
	 * 
	 * @param roleId
	 *            roleId
	 * @param json
	 *            組織報表排程狀態資料
	 * @return 組織報表排程狀態資料
	 */
	public OrgReportSchedule insertData(Long status,Date scheduleTime , boolean isOldSchedule, String message) {
		try {
			

			OrgReportSchedule entity = new OrgReportSchedule();
			entity.setStatus(status);
			entity.setScheduleTime(scheduleTime);
			entity.setIsOldSchedule(isOldSchedule);
			entity.setMessage(message);
			
		
			orgReportScheduleDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得組織報表排程狀態資料
	 * 
	 * @param id
	 *            id
	 * @return 組織報表排程狀態資料
	 */
	public OrgReportSchedule getDataById(Long id) {
		return orgReportScheduleDAO.get(id);
	}

	/**
	 * 組織報表排程狀態資料
	 * 
	 * @param id
	 *            id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return orgReportScheduleDAO.get(id) != null;
	}

	/**
	 * 更新組織報表排程狀態資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            角色資料
	 * @return 角色資料
	 */
	public OrgReportSchedule updateData(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");
			String scheduleTime = obj.isNull("scheduleTime") == true ? "" : obj.getString("scheduleTime");
			boolean isOldSchedule = obj.isNull("IsOldSchedule") == false ? false : obj.getBoolean("IsOldSchedule");
			String message = obj.isNull("Message") == true ? null : obj.getString("Message");

			OrgReportSchedule entity = orgReportScheduleDAO.get(id);
			entity.setStatus(status);
			entity.setScheduleTime(new SimpleDateFormat("yyyy-MM-dd").parse(scheduleTime));
			entity.setIsOldSchedule(isOldSchedule);
			entity.setMessage(message);

			orgReportScheduleDAO.update(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 刪除組織報表排程狀態資料
	 * 
	 * @param id
	 *            id
	 * @return 是否刪除成功
	 */
	public boolean deleteDataById(Long id) {
		try {
			OrgReportSchedule entity = orgReportScheduleDAO.get(id);
			orgReportScheduleDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
}
