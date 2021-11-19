package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.NewsManagementPic;
import tw.gov.mohw.hisac.web.domain.ViewNewsManagementPicMember;

public interface NewsManagementPicDAO {
	public void insert(NewsManagementPic entity);
	public void update(NewsManagementPic entity);
	public void delete(NewsManagementPic entity);
	public NewsManagementPic get(Long id);
	public List<NewsManagementPic> getAll();
	public List<ViewNewsManagementPicMember> getAllByNewsManagementId(long newsManagementId);
	public long getListSize(JSONObject obj);
}
