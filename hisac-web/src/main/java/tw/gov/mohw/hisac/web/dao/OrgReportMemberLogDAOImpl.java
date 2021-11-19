package tw.gov.mohw.hisac.web.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.ProcedureCall;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.WebDatetime;

import tw.gov.mohw.hisac.web.domain.OrgReportMemberLog;




/**
 * 機構報表結果
 */
@Repository
@Transactional(timeout = 36000)
public class OrgReportMemberLogDAOImpl extends BaseSessionFactory implements OrgReportMemberLogDAO {
	
	
	public OrgReportMemberLog get(Long id) {
		return (OrgReportMemberLog) getSessionFactory().getCurrentSession().get(OrgReportMemberLog.class, id);
	}
	
	public void delete(OrgReportMemberLog entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}


	
	
	
	@SuppressWarnings({"unchecked", "deprecation"})
	public List<OrgReportMemberLog> getList(JSONObject obj) {
				
		String sdate = obj.isNull("QuerySdate") == true ? null : obj.getString("QuerySdate");
		String edate = obj.isNull("QueryEdate") == true ? null : obj.getString("QueryEdate");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(OrgReportMemberLog.class);		
		
		try {
			if (sdate != null) {
				cr.add(Restrictions.ge("reportCreateTime", new SimpleDateFormat("yyyy-MM-dd").parse(sdate)));
			}
			if (edate != null) {
				cr.add(Restrictions.lt("reportCreateTime", new SimpleDateFormat("yyyy-MM-dd").parse(edate)));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<OrgReportMemberLog> list = cr.list();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public long getListSize(JSONObject obj) {
		String sdate = obj.isNull("QuerySdate") == true ? null : obj.getString("QuerySdate");
		String edate = obj.isNull("QueryEdate") == true ? null : obj.getString("QueryEdate");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(OrgReportMemberLog.class);		
		
		try {
			if (sdate != null) {
				cr.add(Restrictions.ge("reportCreateTime", new SimpleDateFormat("yyyy-MM-dd").parse(sdate)));
			}
			if (edate != null) {
				cr.add(Restrictions.lt("reportCreateTime", new SimpleDateFormat("yyyy-MM-dd").parse(edate)));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		
		
		return cr.list().size();
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<Object[]> getMemberSigninCount(JSONObject obj) {
		String sdate = obj.isNull("QuerySdate") == true ? null : obj.getString("QuerySdate");
		String edate = obj.isNull("QueryEdate") == true ? null : obj.getString("QueryEdate");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(OrgReportMemberLog.class);	
		
		try {
			if (sdate != null) {
				cr.add(Restrictions.ge("createTime", new SimpleDateFormat("yyyy-MM-dd").parse(sdate)));
			}
			if (edate != null) {
				cr.add(Restrictions.lt("createTime", new SimpleDateFormat("yyyy-MM-dd").parse(edate)));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		cr.setProjection(Projections.projectionList()
				.add(Projections.countDistinct("memberId").as("MemberSigninCount"))
				.add(Projections.groupProperty("orgId"))	
				);		
		
		return cr.list();	
		
	}
	
	


	
	
	

}