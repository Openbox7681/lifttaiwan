package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.ActivityManagementAttach;
import tw.gov.mohw.hisac.web.domain.ViewActivityManagementAttachMember;

public interface ActivityManagementAttachDAO {
	public void insert(ActivityManagementAttach entity);
	public void update(ActivityManagementAttach entity);
	public void delete(ActivityManagementAttach entity);
	public ActivityManagementAttach get(Long id);
	public List<ActivityManagementAttach> getAll();
	public List<ViewActivityManagementAttachMember> getAllByActivityManagementId(long activityManagementId);
	public long getListSize(JSONObject obj);
}
