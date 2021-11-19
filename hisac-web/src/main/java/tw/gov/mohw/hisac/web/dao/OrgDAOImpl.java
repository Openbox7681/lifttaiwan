package tw.gov.mohw.hisac.web.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.dao.OrgVariable.AuthType;
import tw.gov.mohw.hisac.web.domain.Org;
import tw.gov.mohw.hisac.web.domain.ViewOrgOrgSign;
import tw.gov.mohw.hisac.web.domain.ViewParentOrg;

@Repository
@Transactional
public class OrgDAOImpl extends BaseSessionFactory implements OrgDAO {

	public void insert(Org entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(Org entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(Org entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public Org get(Long id) {
		return getSessionFactory().getCurrentSession().get(Org.class, id);
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<Org> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Org.class);
		cr.addOrder(Order.desc("city"));
		cr.addOrder(Order.desc("town"));
		cr.addOrder(Order.desc("address"));
		List<Org> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<Org> getAllMember() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Org.class);
		cr.add(Restrictions.eq("orgType", "1"));
		cr.add(Restrictions.eq("isEnable", true));
		cr.addOrder(Order.desc("city"));
		cr.addOrder(Order.desc("town"));
		cr.addOrder(Order.desc("address"));
		List<Org> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<Org> getByOrgType(String orgType) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Org.class);
		cr.add(Restrictions.eq("orgType", orgType));
		cr.add(Restrictions.eq("isEnable", true));
		cr.addOrder(Order.desc("city"));
		cr.addOrder(Order.desc("town"));
		cr.addOrder(Order.desc("address"));
		cr.addOrder(Order.desc("name"));
		List<Org> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<Org> getList(Boolean isEnable, String orgType) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Org.class);
		if (isEnable != null) {
			cr.add(Restrictions.eq("isEnable", isEnable));
		}
		if (orgType != null && !orgType.isEmpty()) {
			cr.add(Restrictions.eq("orgType", orgType));
		}
		cr.addOrder(Order.desc("city"));
		cr.addOrder(Order.desc("town"));
		cr.addOrder(Order.desc("address"));
		List<Org> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<Org> getList(Boolean isEnable, String orgType, String authType) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Org.class);
		if (isEnable != null) {
			cr.add(Restrictions.eq("isEnable", isEnable));
		}
		if (orgType != null && !orgType.isEmpty()) {
			cr.add(Restrictions.eq("orgType", orgType));
		}
		if (authType != null && !authType.isEmpty()) {
			cr.add(Restrictions.eq("authType", authType));
		}
		cr.addOrder(Order.desc("city"));
		cr.addOrder(Order.desc("town"));
		cr.addOrder(Order.desc("address"));
		List<Org> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<Org> getList(Boolean isEnable, String orgType, String queryString, int perPage) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Org.class);
		if (isEnable != null) {
			cr.add(Restrictions.eq("isEnable", isEnable));
		}
		if (orgType != null && !orgType.isEmpty()) {
			cr.add(Restrictions.eq("orgType", orgType));
		}
		cr.add(Restrictions.or(Restrictions.like("code", "%" + queryString + "%"), Restrictions.like("name", "%" + queryString + "%")));
		if (perPage > 0) {
			cr.setFirstResult(0);
			cr.setMaxResults(perPage);
		}
		cr.addOrder(Order.desc("city"));
		cr.addOrder(Order.desc("town"));
		cr.addOrder(Order.desc("address"));
		List<Org> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<Org> getList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");
		String code = obj.isNull("Code") == true ? null : obj.getString("Code");
		String orgType = obj.isNull("OrgType") == true ? null : obj.getString("OrgType");
		String authType = obj.isNull("AuthType") == true ? null : obj.getString("AuthType");
		String ciLevel = obj.isNull("CiLevel") == true ? null : obj.getString("CiLevel");
		String tel = obj.isNull("Tel") == true ? null : obj.getString("Tel");
		String fax = obj.isNull("Fax") == true ? null : obj.getString("Fax");
		String city = obj.isNull("City") == true ? null : obj.getString("City");
		String town = obj.isNull("Town") == true ? null : obj.getString("Town");
		String address = obj.isNull("Address") == true ? null : obj.getString("Address");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Boolean isApply = obj.isNull("IsApply") == true ? null : obj.getBoolean("IsApply");
		Boolean isPublic = obj.isNull("IsPublic") == true ? null : obj.getBoolean("IsPublic");
		Long healthLevelId = obj.isNull("HealthLevelId") == true ? null : obj.getLong("HealthLevelId");
		Long securityLevel = obj.isNull("SecurityLevel") == true ? null : obj.getLong("SecurityLevel");
		Long isLocate = obj.isNull("IsLocate") == true ? null : obj.getLong("IsLocate");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Org.class);
		if (id != 0)
			cr.add(Restrictions.eq("id", id));
		if (name != null)
			cr.add(Restrictions.like("name", "%" + name + "%"));
		if (code != null)
			cr.add(Restrictions.like("code", "%" + code + "%"));
		if (orgType != null)
			cr.add(Restrictions.eq("orgType", orgType));
		if (authType != null)
			cr.add(Restrictions.eq("authType", authType));
		if (ciLevel != null) {
			if (ciLevel.equals("0")) {
				cr.add(Restrictions.or(Restrictions.isNull("ciLevel"), Restrictions.eq("ciLevel", ciLevel)));
			} else {
				cr.add(Restrictions.eq("ciLevel", ciLevel));
			}
		}
		if (tel != null)
			cr.add(Restrictions.like("tel", "%" + tel + "%"));
		if (fax != null)
			cr.add(Restrictions.like("fax", "%" + fax + "%"));
		if (city != null)
			cr.add(Restrictions.like("city", "%" + city + "%"));
		if (town != null)
			cr.add(Restrictions.like("town", "%" + town + "%"));
		if (address != null)
			cr.add(Restrictions.like("address", "%" + address + "%"));

		if (isEnable != null)
			cr.add(Restrictions.eq("isEnable", isEnable));
		
		if (isApply != null)
			cr.add(Restrictions.eq("isApply", isApply));
		
		if (isPublic != null)
			cr.add(Restrictions.eq("isPublic", isPublic));
		
		if (healthLevelId != null)
			cr.add(Restrictions.eq("healthLevelId", healthLevelId));
		
		if (securityLevel != null)
			cr.add(Restrictions.eq("securityLevel", securityLevel));
		
		if (isLocate != null)
			cr.add(Restrictions.eq("isLocate", isLocate));
		

		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
		if (start != 0)
			cr.setFirstResult(start);
		if (maxRows != 0)
			cr.setMaxResults(maxRows);

		List<Org> list = cr.list();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		// String name = obj.isNull("Name") == true ? null :
		// obj.getString("Name");
		// Boolean isEnable = obj.isNull("IsEnable") == true ? null :
		// obj.getBoolean("IsEnable");
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");
		String code = obj.isNull("Code") == true ? null : obj.getString("Code");
		String orgType = obj.isNull("OrgType") == true ? null : obj.getString("OrgType");
		String authType = obj.isNull("AuthType") == true ? null : obj.getString("AuthType");
		String ciLevel = obj.isNull("CiLevel") == true ? null : obj.getString("CiLevel");
		String tel = obj.isNull("Tel") == true ? null : obj.getString("Tel");
		String fax = obj.isNull("Fax") == true ? null : obj.getString("Fax");
		String city = obj.isNull("City") == true ? null : obj.getString("City");
		String town = obj.isNull("Town") == true ? null : obj.getString("Town");
		String address = obj.isNull("Address") == true ? null : obj.getString("Address");

		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Boolean isApply = obj.isNull("IsApply") == true ? null : obj.getBoolean("IsApply");
		
		Boolean isPublic = obj.isNull("IsPublic") == true ? null : obj.getBoolean("IsPublic");
		Long healthLevelId = obj.isNull("HealthLevelId") == true ? null : obj.getLong("HealthLevelId");
		Long securityLevel = obj.isNull("SecurityLevel") == true ? null : obj.getLong("SecurityLevel");
		Long isLocate = obj.isNull("IsLocate") == true ? null : obj.getLong("IsLocate");


		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Org.class);
		cr.setProjection(Projections.rowCount());
		if (id != 0)
			cr.add(Restrictions.eq("id", id));
		if (name != null)
			cr.add(Restrictions.like("name", "%" + name + "%"));
		if (code != null)
			cr.add(Restrictions.like("code", "%" + code + "%"));
		if (orgType != null)
			cr.add(Restrictions.eq("orgType", orgType));
		if (authType != null)
			cr.add(Restrictions.eq("authType", authType));
		if (ciLevel != null) {
			if (ciLevel.equals("0")) {
				cr.add(Restrictions.or(Restrictions.isNull("ciLevel"), Restrictions.eq("ciLevel", ciLevel)));
			} else {
				cr.add(Restrictions.eq("ciLevel", ciLevel));
			}
		}
		if (tel != null)
			cr.add(Restrictions.like("tel", "%" + tel + "%"));
		if (fax != null)
			cr.add(Restrictions.like("fax", "%" + fax + "%"));
		if (city != null)
			cr.add(Restrictions.like("city", "%" + city + "%"));
		if (town != null)
			cr.add(Restrictions.like("town", "%" + town + "%"));
		if (address != null)
			cr.add(Restrictions.like("address", "%" + address + "%"));

		if (isEnable != null)
			cr.add(Restrictions.eq("isEnable", isEnable));
		
		if (isApply != null)
			cr.add(Restrictions.eq("isApply", isApply));
		
		if (isPublic != null)
			cr.add(Restrictions.eq("isPublic", isPublic));
		
		if (healthLevelId != null)
			cr.add(Restrictions.eq("healthLevelId", healthLevelId));
		
		if (securityLevel != null)
			cr.add(Restrictions.eq("securityLevel", securityLevel));
		if (isLocate != null)
			cr.add(Restrictions.eq("isLocate", isLocate));

		long total = (long) cr.list().get(0);
		return total;

	}

	@SuppressWarnings({"deprecation"})
	public ViewParentOrg getParentOrg(Long orgId, AuthType authType) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewParentOrg.class);
		if (orgId != null) {
			cr.add(Restrictions.eq("orgId", orgId));
		}
		if (authType.equals(AuthType.Local) || authType.equals(AuthType.Supervise)) {
			cr.add(Restrictions.eq("authType", authType.getValue()));
		}
		cr.add(Restrictions.eq("isEnable", true));
		cr.setMaxResults(1);
		return (ViewParentOrg) cr.uniqueResult();
	}
	

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<ViewOrgOrgSign> getByParentOrgId(Long parentOrgId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewOrgOrgSign.class);

		// debug
//		System.out.println("OrgDAOImpl.java → getByParentOrgId() → parentOrgId：" + parentOrgId);
		
		if (parentOrgId != null) {
			cr.add(Restrictions.eq("parentOrgId", parentOrgId));
		}
		cr.add(Restrictions.eq("isEnable", true));
		cr.addOrder(Order.desc("name"));
		
		List<ViewOrgOrgSign> list = cr.list();

		// debug
//		System.out.println("OrgDAOImpl.java → getByParentOrgId() → 取得筆數：" + list.size());
		
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<ViewOrgOrgSign> getOrgOrgSignById(Long id) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewOrgOrgSign.class);

		// debug
//		System.out.println("OrgDAOImpl.java → getOrgOrgSignById() → id：" + id);
		
		if (id != null) {
			cr.add(Restrictions.eq("id", id));
		}
		cr.add(Restrictions.eq("orgType", "3")); // 祇抓會員機構(orgType = 3)
		cr.add(Restrictions.eq("isEnable", true));
		cr.addOrder(Order.desc("name"));
		
		List<ViewOrgOrgSign> list = cr.list();

		// debug
//		System.out.println("OrgDAOImpl.java → getOrgOrgSignById() → 取得筆數：" + list.size());
		
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	
	
	@SuppressWarnings({"unchecked", "deprecation"})
	public List<Org> querymember(JSONObject obj) {		
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");		
		String orgType = obj.isNull("OrgType") == true ? null : obj.getString("OrgType");		
		Boolean isCiLevel1 = obj.isNull("IsCiLevel1") == true ? false : obj.getBoolean("IsCiLevel1");	
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");	
		JSONArray ciLevels = obj.optJSONArray("Cilevels");	
		String[] ciLevel = new String[ciLevels.length()];		
		for (int i = 0; i < ciLevels.length(); ++i) {
			ciLevel[i] = ciLevels.optString(i);
		}
		JSONArray orgs = obj.optJSONArray("Orgs");			
		Long[] org = new Long[orgs.length()];		
		for (int i = 0; i < orgs.length(); ++i) {
			org[i] = orgs.optLong(i);
		}
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Org.class);		
		if (name != null)
			cr.add(Restrictions.like("name", "%" + name + "%"));		
		if (orgType != null)
			cr.add(Restrictions.eq("orgType", orgType));				

		if (isEnable != null)
			cr.add(Restrictions.eq("isEnable", isEnable));
			
		
		if (ciLevels.length()>0 && isCiLevel1)
			cr.add(Restrictions.or(Restrictions.isNull("ciLevel"), Restrictions.in("ciLevel", ciLevel)));
		else if (ciLevels.length()>0)
			cr.add(Restrictions.in("ciLevel", ciLevel));
		
		if (orgs.length()>0)
			cr.add(Restrictions.in("id", org));	
		
		if (ciLevels.length()==0 && orgs.length()==0 && name==null)
			return null;
			
			
		List<Org> list = cr.list();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}
	
	
	@SuppressWarnings({"unchecked", "deprecation"})
	public List<Org> getOrgReport() {				
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Org.class);				
		cr.add(Restrictions.eq("isApply", false));
		cr.add(Restrictions.eq("orgType", "3"));
			
		List<Org> list = cr.list();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}

	/**更新金鑰狀態 
	 * @param status
	* 0 : 撤銷機構並禁金鑰
	* 1 : 申請中狀態 ()
	* 2 : 金鑰已審核通過
	*  @param apiKey
	*  金鑰
	*  @param apiKeyExpiryDate
	*  金鑰到期時間
	* 
	* 
	* 
	*/

	@SuppressWarnings({"unchecked", "deprecation"})
	public Boolean updateApiKeyData(Org entity , String status, String apiKey, Date apiKeyExpiryDate) {
		try {
			entity.setApiKeyStatus(status);
			entity.setApiKeyExpiryDate(apiKeyExpiryDate);
			entity.setApiKey(apiKey);
			
			update(entity);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated method stub
			return false;
		}

	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public Boolean updateApiKeyStatus(Org entity, String status) {
		try {
			entity.setApiKeyStatus(status);
			update(entity);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated method stub
			return false;
		}
	}

}
