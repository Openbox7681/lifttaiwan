package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.ActivityManagement;
import tw.gov.mohw.hisac.web.domain.SpActivityManagementReport;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.ViewActivityManagementMember;
import tw.gov.mohw.hisac.web.domain.ViewMessageAlertEvent;

public interface ActivityManagementDAO {
	public void insert(ActivityManagement entity);
	public void update(ActivityManagement entity);
	public void delete(ActivityManagement entity);
	// 依  id 取得資料（不含取得關聯的欄位選項值內容）
	public ActivityManagement get(Long id);
	// 依  id 取得資料（含取得關聯的欄位選項值內容）
	public ViewActivityManagementMember getByDetail(Long id);
	public List<ActivityManagement> getAll();
	public List<ViewActivityManagementMember> getList(JSONObject obj);
	public List<SpActivityManagementReport> getReport(JSONObject obj);
	public long getListSize(JSONObject obj);
	
	// 流程紀錄用 - 開始
	public ViewMessageAlertEvent getById(String id);
	// 給 MSSQL 預存程序 xp_activity_management 用
	public List<ViewActivityManagementMember> getSpList(JSONObject obj);
	public long getSpListSize(JSONObject obj);
	public long getSpFormCount(JSONObject obj);
	// 流程紀錄用 - 結束
	
	// 統計狀態筆數用 - 開始
	public List<SpButtonCount> getSpButtonCount(JSONObject obj);
	// 統計狀態筆數用 - 結束
}