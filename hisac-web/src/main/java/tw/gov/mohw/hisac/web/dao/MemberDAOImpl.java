package tw.gov.mohw.hisac.web.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.hibernate.result.ResultSetOutput;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.SpMemberReport;
import tw.gov.mohw.hisac.web.domain.ViewMember;

@Repository
@Transactional
public class MemberDAOImpl extends BaseSessionFactory implements MemberDAO {
	
	private String user = WebCrypto.getUSER();


	public void insert(Member entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(Member entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(Member entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public Member get(Long id) {
		
		try {
			if (user.equals("admin") && id != null){
				return getSessionFactory().getCurrentSession().get(Member.class, id);
			}else {
				return null;
			}
		
		} catch (Exception ex) {
			return null;
			
		}
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<Member> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Member.class);
		List<Member> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public Member getByAccount(String account) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Member.class);
		cr.add(Restrictions.eq("account", account));
		return (Member) cr.uniqueResult();
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewMember> getList(JSONObject obj) {
		
		try {
			
			obj = new JSONObject(WebCrypto.getSafe(obj.toString()));


		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");

		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		long orgId = obj.isNull("OrgId") == true ? 0 : obj.getLong("OrgId");
		String account = obj.isNull("Account") == true ? null : obj.getString("Account");
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");
		String email = obj.isNull("Email") == true ? null : obj.getString("Email");
		String spareEmail = obj.isNull("SpareEmail") == true ? null : obj.getString("SpareEmail");
		String mobilePhone = obj.isNull("MobilePhone") == true ? null : obj.getString("MobilePhone");
		String cityPhone = obj.isNull("CityPhone") == true ? null : obj.getString("CityPhone");
		String faxPhone = obj.isNull("FaxPhone") == true ? null : obj.getString("FaxPhone");
		String address = obj.isNull("Address") == true ? null : obj.getString("Address");
		String department = obj.isNull("Department") == true ? null : obj.getString("Department");
		String title = obj.isNull("Title") == true ? null : obj.getString("Title");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		String status =obj.isNull("Status") == true ? null : obj.getString("Status");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMember.class);

		// 權限控制
		long roleId = obj.isNull("RoleId") == true ? 0 : obj.getLong("RoleId");
		long baseOrgId = obj.isNull("baseOrgId") == true ? 0 : obj.getLong("baseOrgId");

		// if (roleId == 11)// 11會員機構管理者
		// cr.add(Restrictions.eq("orgId", baseOrgId));
		// // else if(roleId==10)//10 會員機構聯絡人
		// // cr.add(Restrictions.eq("id", baseMemberId));
		if (roleId == 2) {
			cr.add(Restrictions.gt("id", (long) 1));
		} else if (roleId == 9 || roleId == 11) {
			cr.add(Restrictions.eq("orgId", baseOrgId));
		}

		// 一般查詢
		if (id != 0) {
			cr.add(Restrictions.eq("id", id));
		}
		if (orgId != 0) {
			cr.add(Restrictions.eq("orgId", orgId));
		}
		if (account != null) {
			cr.add(Restrictions.like("account", "%" + account + "%"));
		}
		if (name != null) {
			cr.add(Restrictions.like("name", "%" + name + "%"));
		}
		if (email != null) {
			cr.add(Restrictions.like("email", "%" + email + "%"));
		}
		if (spareEmail != null) {
			cr.add(Restrictions.like("spareEmail", "%" + spareEmail + "%"));
		}
		if (mobilePhone != null) {
			cr.add(Restrictions.like("mobilePhone", "%" + mobilePhone + "%"));
		}
		if (cityPhone != null) {
			cr.add(Restrictions.like("cityPhone", "%" + cityPhone + "%"));
		}
		if (faxPhone != null) {
			cr.add(Restrictions.like("faxPhone", "%" + faxPhone + "%"));
		}
		if (address != null) {
			cr.add(Restrictions.like("address", "%" + address + "%"));
		}
		if (department != null) {
			cr.add(Restrictions.like("department", "%" + department + "%"));
		}
		if (title != null) {
			cr.add(Restrictions.like("title", "%" + title + "%"));
		}
		if (isEnable != null) {
			cr.add(Restrictions.eq("isEnable", isEnable));
		}		
		
		if (status != null) {
			switch (status) {
				case "memberStatus0":
					cr.add(Restrictions.isNull("errorCount"));
					break;
				case "memberStatus1":
					cr.add(Restrictions.eq("errorCount", (short)-1));
					break;
				case "memberStatus2":
					cr.add(Restrictions.and(Restrictions.isNotNull("errorCount"), Restrictions.ne("errorCount", (short)-1), Restrictions.eq("isEnable", false)));
					break;
				case "memberStatus3":
					cr.add(Restrictions.and(Restrictions.isNotNull("errorCount"), Restrictions.ne("errorCount", (short)-1), Restrictions.eq("isEnable", true)));
					break;
			}
			
		}		

		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
		cr.setFirstResult(start);
		if (maxRows != 0)
			cr.setMaxResults(maxRows);
		List<ViewMember> list = cr.list();		

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		try {
		
		
		obj = new JSONObject(WebCrypto.getSafe(obj.toString()));

		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		long orgId = obj.isNull("OrgId") == true ? 0 : obj.getLong("OrgId");
		String account = obj.isNull("Account") == true ? null : obj.getString("Account");
		String name = obj.isNull("Name") == true ? null : obj.getString("Name");
		String email = obj.isNull("Email") == true ? null : obj.getString("Email");
		String spareEmail = obj.isNull("SpareEmail") == true ? null : obj.getString("SpareEmail");
		String mobilePhone = obj.isNull("MobilePhone") == true ? null : obj.getString("MobilePhone");
		String cityPhone = obj.isNull("CityPhone") == true ? null : obj.getString("CityPhone");
		String faxPhone = obj.isNull("FaxPhone") == true ? null : obj.getString("FaxPhone");
		String address = obj.isNull("Address") == true ? null : obj.getString("Address");
		String department = obj.isNull("Department") == true ? null : obj.getString("Department");
		String title = obj.isNull("Title") == true ? null : obj.getString("Title");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		String status =obj.isNull("Status") == true ? null : obj.getString("Status");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMember.class);
		cr.setProjection(Projections.rowCount());

		// 權限控制
		long roleId = obj.isNull("RoleId") == true ? 0 : obj.getLong("RoleId");
		long baseOrgId = obj.isNull("baseOrgId") == true ? 0 : obj.getLong("baseOrgId");

		// if (roleId == 11)// 11會員機構管理者
		// cr.add(Restrictions.eq("orgId", baseOrgId));
		// // else if(roleId==10)//10 會員機構聯絡人
		// // cr.add(Restrictions.eq("id", baseMemberId));
		if (roleId == 2) {
			cr.add(Restrictions.gt("id", (long) 1));
		} else if (roleId == 9 || roleId == 11) {
			cr.add(Restrictions.eq("orgId", baseOrgId));
		}

		// 一般查詢
		if (id != 0) {
			cr.add(Restrictions.eq("id", id));
		}
		if (orgId != 0) {
			cr.add(Restrictions.eq("orgId", orgId));
		}
		if (account != null) {
			cr.add(Restrictions.like("account", "%" + account + "%"));
		}
		if (name != null) {
			cr.add(Restrictions.like("name", "%" + name + "%"));
		}
		if (email != null) {
			cr.add(Restrictions.like("email", "%" + email + "%"));
		}
		if (spareEmail != null) {
			cr.add(Restrictions.like("spareEmail", "%" + spareEmail + "%"));
		}
		if (mobilePhone != null) {
			cr.add(Restrictions.like("mobilePhone", "%" + mobilePhone + "%"));
		}
		if (cityPhone != null) {
			cr.add(Restrictions.like("cityPhone", "%" + cityPhone + "%"));
		}
		if (faxPhone != null) {
			cr.add(Restrictions.like("faxPhone", "%" + faxPhone + "%"));
		}
		if (address != null) {
			cr.add(Restrictions.like("address", "%" + address + "%"));
		}
		if (department != null) {
			cr.add(Restrictions.like("department", "%" + department + "%"));
		}
		if (title != null) {
			cr.add(Restrictions.like("title", "%" + title + "%"));
		}
		if (isEnable != null) {
			cr.add(Restrictions.eq("isEnable", isEnable));
		}

		if (status != null) {
			switch (status) {
				case "memberStatus0":
					cr.add(Restrictions.isNull("errorCount"));
					break;
				case "memberStatus1":
					cr.add(Restrictions.eq("errorCount", (short)-1));
					break;
				case "memberStatus2":
					cr.add(Restrictions.and(Restrictions.isNotNull("errorCount"), Restrictions.ne("errorCount", (short)-1), Restrictions.eq("isEnable", false)));
					break;
				case "memberStatus3":
					cr.add(Restrictions.and(Restrictions.isNotNull("errorCount"), Restrictions.ne("errorCount", (short)-1), Restrictions.eq("isEnable", true)));
					break;
			}
			
		}
		
		long total = (long) cr.list().get(0);
		return total;
		
		} catch (Exception e) {
			return 0;
		}
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<Member> getByOrgId(Long orgId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Member.class);
		cr.add(Restrictions.eq("orgId", orgId));
		List<Member> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings({"unchecked", "deprecation"})
	public List<Member> getByOrgIds(Long[] orgIds) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Member.class);
		cr.add(Restrictions.in("orgId", orgIds));
		List<Member> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings({"unchecked", "deprecation"})
	public List<Member> getByIds(Long[] ids) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Member.class);
		cr.add(Restrictions.in("id", ids));
		List<Member> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<SpMemberReport> getReport(JSONObject obj) {
		String querySdate = obj.isNull("QuerySdate") == true ? null : obj.getString("QuerySdate");
		String queryEdate = obj.isNull("QueryEdate") == true ? null : obj.getString("QueryEdate");				

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_member_report", SpMemberReport.class);
			
		try {
			if (querySdate != null)
				call.registerParameter("QuerySdate", Date.class, ParameterMode.IN).bindValue(new SimpleDateFormat("yyyy-MM-dd").parse(querySdate));
			else
				call.registerParameter("QuerySdate", Date.class, ParameterMode.IN).enablePassingNulls(true);
			if (queryEdate != null)
				call.registerParameter("QueryEdate", Date.class, ParameterMode.IN).bindValue(new SimpleDateFormat("yyyy-MM-dd").parse(queryEdate));
			else
				call.registerParameter("QueryEdate", Date.class, ParameterMode.IN).enablePassingNulls(true);
		}	catch (ParseException e) {
				//e.printStackTrace();
		}

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpMemberReport> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
}