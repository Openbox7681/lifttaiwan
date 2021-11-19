package tw.gov.mohw.hisac.web.dao;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.dao.OrgVariable.AuthType;
import tw.gov.mohw.hisac.web.domain.Org;
import tw.gov.mohw.hisac.web.domain.ViewOrgOrgSign;
import tw.gov.mohw.hisac.web.domain.ViewParentOrg;

public interface OrgDAO {
	public void insert(Org entity);
	public void update(Org entity);
	public void delete(Org entity);
	public Org get(Long id);
	public List<Org> getAll();
	public List<Org> getByOrgType(String orgType);
	public List<Org> getList(Boolean isEnable, String orgType);
	public List<Org> getList(Boolean isEnable, String orgType, String authType);
	public List<Org> getList(Boolean isEnable, String orgType, String queryString, int perPage);
	public List<Org> getList(JSONObject obj);
	public List<Org> querymember(JSONObject obj);	
	public long getListSize(JSONObject obj);
	public ViewParentOrg getParentOrg(Long orgId, AuthType authType);
	public Boolean updateApiKeyData(Org entity , String status, String apiKey, Date apiKeyExpiryDate);
	public Boolean updateApiKeyStatus(Org entity , String status);

	
	/* for 事件處理用
	 * 108/6/11 added by bowwow 
	 */
	public List<ViewOrgOrgSign> getByParentOrgId(Long parentOrgId);
	/* for 事件處理用
	 * 108/6/11 added by bowwow 
	 */
	public List<ViewOrgOrgSign> getOrgOrgSignById(Long id);
	
	public List<Org> getOrgReport();
	
}
