package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import org.json.JSONObject;
import tw.gov.mohw.hisac.web.domain.MaintainPlanGroupOrg;
import tw.gov.mohw.hisac.web.domain.SpMaintainPlanGroupOrg;

public interface MaintainPlanGroupOrgDAO {
	public void insert(MaintainPlanGroupOrg entity);
	public void update(MaintainPlanGroupOrg entity);
	public void delete(MaintainPlanGroupOrg entity);
	public MaintainPlanGroupOrg get(Long id);
	public List<MaintainPlanGroupOrg> getAll();
	public MaintainPlanGroupOrg getByOrgId(Long orgId);
	public List<MaintainPlanGroupOrg> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
	public List<MaintainPlanGroupOrg> getByMaintainPlanGroupId(Long maintainPlanGroupId);
	
	public List<SpMaintainPlanGroupOrg> getSpList(JSONObject obj);
	

}
