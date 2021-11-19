package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.ActivityManagementPic;
import tw.gov.mohw.hisac.web.domain.ViewActivityManagementPicMember;

public interface ActivityManagementPicDAO {
	public void insert(ActivityManagementPic entity);
	public void update(ActivityManagementPic entity);
	public void delete(ActivityManagementPic entity);
	public ActivityManagementPic get(Long id);
	public List<ActivityManagementPic> getAll();
	public List<ViewActivityManagementPicMember> getAllByActivityManagementId(long activityManagementId);
	public long getListSize(JSONObject obj);
}
