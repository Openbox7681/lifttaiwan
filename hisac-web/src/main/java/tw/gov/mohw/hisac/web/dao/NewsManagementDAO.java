package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.NewsManagement;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.SpNewsManagementReport;
import tw.gov.mohw.hisac.web.domain.ViewMessageAlertEvent;
import tw.gov.mohw.hisac.web.domain.ViewNewsManagementMember;

public interface NewsManagementDAO {
	public void insert(NewsManagement entity);
	public void update(NewsManagement entity);
	public void delete(NewsManagement entity);
	// 依  id 取得資料（不含取得關聯的欄位選項值內容）
	public NewsManagement get(Long id);
	// 依  id 取得資料（含取得關聯的欄位選項值內容）
	public ViewNewsManagementMember getByDetail(Long id);
	public List<NewsManagement> getAll();
	public List<ViewNewsManagementMember> getList(JSONObject obj);
	public List<SpNewsManagementReport> getReport(JSONObject obj);
	public long getListSize(JSONObject obj);
	
	// 流程紀錄用 - 開始
	public ViewMessageAlertEvent getById(String id);
	// 給 MSSQL 預存程序 xp_news_management 用
	public List<ViewNewsManagementMember> getSpList(JSONObject obj);
	public long getSpListSize(JSONObject obj);
	public long getSpFormCount(JSONObject obj);
	// 流程紀錄用 - 結束
	
	// 統計狀態筆數用 - 開始
	public List<SpButtonCount> getSpButtonCount(JSONObject obj);
	// 統計狀態筆數用 - 結束
}