package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.WeaknessManagementAttach;
import tw.gov.mohw.hisac.web.domain.ViewWeaknessManagementAttachMember;

public interface WeaknessManagementAttachDAO {
	public void insert(WeaknessManagementAttach entity);
	public void update(WeaknessManagementAttach entity);
	public void delete(WeaknessManagementAttach entity);
	public WeaknessManagementAttach get(Long id);
	public List<WeaknessManagementAttach> getAll();
	public List<ViewWeaknessManagementAttachMember> getAllByWeaknessManagementId(long anaManagementId);
	public long getListSize(JSONObject obj);
}
