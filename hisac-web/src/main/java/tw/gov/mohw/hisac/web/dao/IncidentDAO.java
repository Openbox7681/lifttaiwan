package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.Incident;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.ViewIncidentMember;
import tw.gov.mohw.hisac.web.domain.ViewMessageAlertEvent;

public interface IncidentDAO {
	
	public List<Incident> getAll();
	public List<ViewIncidentMember> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
	
	public void insert(Incident entity);
	public void update(Incident entity);
	public void delete(Incident entity);
	// 依  id 取得資料（不含取得關聯的欄位選項值內容）
	public Incident get(Long id);
	
	public Incident getByNotificationId(String notificationId);
	// 依  id 取得資料（含取得關聯的欄位選項值內容）
	public ViewIncidentMember getByDetail(Long id);
	
	// 流程紀錄用 - 開始
	public ViewMessageAlertEvent getById(String id);
	// 給 MSSQL 預存程序 xp_incident 用
	public List<ViewIncidentMember> getSpList(JSONObject obj);
	public long getSpListSize(JSONObject obj);
	public long getSpFormCount(JSONObject obj);
	// 流程紀錄用 - 結束
	
	// 統計狀態筆數用 - 開始
	public List<SpButtonCount> getSpButtonCount(JSONObject obj);
	// 統計狀態筆數用 - 結束
}