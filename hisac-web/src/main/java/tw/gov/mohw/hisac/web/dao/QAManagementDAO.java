package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.QAManagement;
import tw.gov.mohw.hisac.web.domain.ViewQAManagementGroup;

public interface QAManagementDAO {
	public void insert(QAManagement entity);
	public void update(QAManagement entity);
	public void delete(QAManagement entity);
	public QAManagement get(Long id);
	public List<QAManagement> getAll();
	// public List<QAManagementGroup> getAllQAManagementGroup();
	public List<ViewQAManagementGroup> getList(Boolean isEnable, String qaMgId);
	public List<ViewQAManagementGroup> getList(Boolean isEnable, String qaMgId, String queryString, int perPage);
	public List<ViewQAManagementGroup> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
}
