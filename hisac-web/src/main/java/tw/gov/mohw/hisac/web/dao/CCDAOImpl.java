package tw.gov.mohw.hisac.web.dao;

import java.util.List;

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

import tw.gov.mohw.hisac.web.domain.CC;
import tw.gov.mohw.hisac.web.domain.SpDashboard;

/**
 * 表單服務
 */
@Repository
@Transactional
public class CCDAOImpl extends BaseSessionFactory implements CCDAO {

	public void insert(CC entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void delete() {
		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_cc_delete");
		call.getOutputs();		
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public boolean getByDnameOrIpname(String dnameOrIpname) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(CC.class);
		cr.add(Restrictions.eq("dnameOrIpname", dnameOrIpname));
		List<CC> list = cr.list();
		if (list.size() > 0) {
			return true;
		}
		else {
			return false;
		}
	}
}