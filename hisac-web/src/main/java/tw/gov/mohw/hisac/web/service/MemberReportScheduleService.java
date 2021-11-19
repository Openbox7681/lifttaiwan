package tw.gov.mohw.hisac.web.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.MemberReportScheduleDAO;
import tw.gov.mohw.hisac.web.domain.MemberReport;
import tw.gov.mohw.hisac.web.domain.MemberReportSchedule;

/**
 * 會員報表排程資料維護服務
 */
@Service
public class MemberReportScheduleService {
	@Autowired
	MemberReportScheduleDAO memberReportScheduleDAO;
	
	
	/**
	 * 刪除會員統計報表資料
	 * 
	 */
	
	public boolean delete(Long id) {
		try {
			MemberReportSchedule entity = memberReportScheduleDAO.get(id);
			memberReportScheduleDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
	
	

	/**
	 * 取得會員報表排程狀態資料
	 * 
	 * @return 會員報表排程資料
	 */
	public List<MemberReportSchedule> getAll() {
		List<MemberReportSchedule> entitys = memberReportScheduleDAO.getAll();
		return entitys;
	}

	/**
	 * 取得會員報表排程狀態資料
	 * 
	 * @param json
	 *            報表排程狀態資料
	 * @return 報表排程狀態資料
	 */
	public List<MemberReportSchedule> getList(String json) {
		List<MemberReportSchedule> entitys = memberReportScheduleDAO.getList(json);
		return entitys;
	}
	
	/**
	 * 取得報表排程最新時間
	 * 
	 * @param json
	 *            報表排程狀態資料
	 * @return 報表排程狀態資料
	 */
	
	public String getLastScheduleTime() {
		return memberReportScheduleDAO.getLastScheduleTime();
		
	};


	/**
	 * 取得會員報表排程狀態資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 會員報表排程狀態資料
	 */
	public long getMemberReportScheduleListSize(String json) {
		return memberReportScheduleDAO.getListSize(json);
	}

	/**
	 * 新增會員報表排程狀態資料
	 * 
	 * @param roleId
	 *            roleId
	 * @param json
	 *            會員報表排程狀態資料
	 * @return 會員報表排程狀態資料
	 */
	public MemberReportSchedule insertData(Long status,Date scheduleTime , boolean isOldSchedule, String message) {
		try {
			

			MemberReportSchedule entity = new MemberReportSchedule();
			entity.setStatus(status);
			entity.setScheduleTime(scheduleTime);
			entity.setIsOldSchedule(isOldSchedule);
			entity.setMessage(message);
			
		
			memberReportScheduleDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得會員報表排程狀態資料
	 * 
	 * @param id
	 *            id
	 * @return 會員報表排程狀態資料
	 */
	public MemberReportSchedule getDataById(Long id) {
		return memberReportScheduleDAO.get(id);
	}

	/**
	 * 會員報表排程狀態資料
	 * 
	 * @param id
	 *            id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return memberReportScheduleDAO.get(id) != null;
	}

	/**
	 * 更新會員報表排程狀態資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            角色資料
	 * @return 角色資料
	 */
	public MemberReportSchedule updateData(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");
			String scheduleTime = obj.isNull("scheduleTime") == true ? "" : obj.getString("scheduleTime");
			boolean isOldSchedule = obj.isNull("IsOldSchedule") == false ? false : obj.getBoolean("IsOldSchedule");
			String message = obj.isNull("Message") == true ? null : obj.getString("Message");

			MemberReportSchedule entity = memberReportScheduleDAO.get(id);
			entity.setStatus(status);
			entity.setScheduleTime(new SimpleDateFormat("yyyy-MM-dd").parse(scheduleTime));
			entity.setIsOldSchedule(isOldSchedule);
			entity.setMessage(message);

			memberReportScheduleDAO.update(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 刪除會員報表排程狀態資料
	 * 
	 * @param id
	 *            id
	 * @return 是否刪除成功
	 */
	public boolean deleteDataById(Long id) {
		try {
			MemberReportSchedule entity = memberReportScheduleDAO.get(id);
			memberReportScheduleDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
}
