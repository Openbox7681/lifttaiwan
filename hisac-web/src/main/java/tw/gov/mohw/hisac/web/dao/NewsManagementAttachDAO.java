package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.NewsManagementAttach;
import tw.gov.mohw.hisac.web.domain.ViewNewsManagementAttachMember;

public interface NewsManagementAttachDAO {
	public void insert(NewsManagementAttach entity);
	public void update(NewsManagementAttach entity);
	public void delete(NewsManagementAttach entity);
	public NewsManagementAttach get(Long id);
	public List<NewsManagementAttach> getAll();
	public List<ViewNewsManagementAttachMember> getAllByNewsManagementId(long newsManagementId);
	public long getListSize(JSONObject obj);
}
