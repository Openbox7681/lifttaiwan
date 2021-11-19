package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.WeaknessManagement;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.ViewMessageAlertEvent;
import tw.gov.mohw.hisac.web.domain.ViewWeaknessManagementMember;

public interface WeaknessManagementDAO {
	public void insert(WeaknessManagement entity);
	public void update(WeaknessManagement entity);
	public void delete(WeaknessManagement entity);
	// 依  id 取得資料（不含取得關聯的欄位選項值內容）
	public WeaknessManagement get(Long id);
	// 依  id 取得資料（含取得關聯的欄位選項值內容）
	public ViewWeaknessManagementMember getByDetail(Long id);
	public List<WeaknessManagement> getAll();
	public List<ViewWeaknessManagementMember> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
	
	// 流程紀錄用 - 開始
	public ViewMessageAlertEvent getById(String id);
	// 給 MSSQL 預存程序 xp_weakness_management 用
	public List<ViewWeaknessManagementMember> getSpList(JSONObject obj);
	public long getSpListSize(JSONObject obj);
	public long getSpFormCount(JSONObject obj);
	// 流程紀錄用 - 結束
	
	// 統計狀態筆數用 - 開始
	public List<SpButtonCount> getSpButtonCount(JSONObject obj);
	// 統計狀態筆數用 - 結束
}