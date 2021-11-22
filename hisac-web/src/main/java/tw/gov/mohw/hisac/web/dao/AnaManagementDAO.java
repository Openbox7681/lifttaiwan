package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.AnaManagement;
import tw.gov.mohw.hisac.web.domain.SpAnaManagementReport;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.ViewAnaManagementMember;

public interface AnaManagementDAO {
	public void insert(AnaManagement entity);
	public void update(AnaManagement entity);
	public void delete(AnaManagement entity);
	// 依  id 取得資料（不含取得關聯的欄位選項值內容）
	public AnaManagement get(Long id);
	// 依  id 取得資料（含取得關聯的欄位選項值內容）
	public ViewAnaManagementMember getByDetail(Long id);
	public List<AnaManagement> getAll();
	public List<ViewAnaManagementMember> getList(JSONObject obj);
	public List<SpAnaManagementReport> getReport(JSONObject obj);
	public long getListSize(JSONObject obj);
	
	
	// 給 MSSQL 預存程序 xp_ana_management 用
	public List<ViewAnaManagementMember> getSpList(JSONObject obj);
	public long getSpListSize(JSONObject obj);
	public long getSpFormCount(JSONObject obj);
	// 流程紀錄用 - 結束
	
	// 統計狀態筆數用 - 開始
	public List<SpButtonCount> getSpButtonCount(JSONObject obj);
	// 統計狀態筆數用 - 結束
}