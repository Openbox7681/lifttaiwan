package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import org.json.JSONObject;
import tw.gov.mohw.hisac.web.domain.NewsManagementGroup;
import tw.gov.mohw.hisac.web.domain.ViewNewsManagementGroupMember;

public interface NewsManagementGroupDAO {
	public void insert(NewsManagementGroup entity);
	public void update(NewsManagementGroup entity);
	public void delete(NewsManagementGroup entity);
	public NewsManagementGroup get(Long id);
	public List<NewsManagementGroup> getAll();
	public NewsManagementGroup getByName(String name);
	public List<ViewNewsManagementGroupMember> getList(JSONObject obj);
	public List<NewsManagementGroup> getList();
	public long getListSize(JSONObject obj);
}
