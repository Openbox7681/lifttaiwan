package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.AnaManagementAttach;
import tw.gov.mohw.hisac.web.domain.ViewAnaManagementAttachMember;

public interface AnaManagementAttachDAO {
	public void insert(AnaManagementAttach entity);
	public void update(AnaManagementAttach entity);
	public void delete(AnaManagementAttach entity);
	public AnaManagementAttach get(Long id);
	public List<AnaManagementAttach> getAll();
	public List<ViewAnaManagementAttachMember> getAllByAnaManagementId(long anaManagementId);
	public List<AnaManagementAttach> getAllAttachByAnaManagementId(long anaManagementId) ;
	public long getListSize(JSONObject obj);
}
