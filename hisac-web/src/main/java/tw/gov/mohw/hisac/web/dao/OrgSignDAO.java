package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import org.json.JSONObject;
import tw.gov.mohw.hisac.web.domain.OrgSign;
import tw.gov.mohw.hisac.web.domain.SpOrgSign;

public interface OrgSignDAO {
	public void insert(OrgSign entity);
	public void update(OrgSign entity);
	public void delete(OrgSign entity);
	public OrgSign get(Long id);
	public List<OrgSign> getAll();
	public List<OrgSign> getByOrgId(Long orgId);
	public List<OrgSign> getByParentOrgId(Long parentorgId);
	//public List<OrgSign> getList(JSONObject obj);
	//public long getListSize(JSONObject obj);
	
	public List<SpOrgSign> getSpList(JSONObject obj);
	

}
