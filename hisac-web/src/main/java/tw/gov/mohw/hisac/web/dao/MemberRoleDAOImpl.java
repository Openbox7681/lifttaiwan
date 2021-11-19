package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.hibernate.result.ResultSetOutput;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.MemberRole;
import tw.gov.mohw.hisac.web.domain.SpMemberRoleName;
import tw.gov.mohw.hisac.web.domain.ViewMemberRoleMember;

/**
 * 會員權限
 */
@Repository
@Transactional
public class MemberRoleDAOImpl extends BaseSessionFactory implements MemberRoleDAO {

	public void insert(MemberRole entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(MemberRole entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(MemberRole entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public MemberRole get(Long id) {
		return getSessionFactory().getCurrentSession().get(MemberRole.class, id);
	}
	
	@SuppressWarnings("deprecation")
	@Transactional
	public MemberRole getByMemberIdAndRoleId(long memberId, long roleId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MemberRole.class);
		cr.add(Restrictions.eq("memberId", memberId));
		cr.add(Restrictions.eq("roleId", roleId));
		return (MemberRole) cr.uniqueResult();
	}
	
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<MemberRole> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MemberRole.class);
		List<MemberRole> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<MemberRole> getByMemberId(long memberId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MemberRole.class);
		cr.add(Restrictions.eq("memberId", memberId));
		cr.add(Restrictions.eq("isEnable", true));
		List<MemberRole> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<MemberRole> getAllByMemberId(long memberId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MemberRole.class);
		cr.add(Restrictions.eq("memberId", memberId));		
		List<MemberRole> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings({"deprecation", "unchecked"})
	public List<MemberRole> getAllByMemberIds(Long[] memberIds) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MemberRole.class);
		cr.add(Restrictions.in("memberId", memberIds));		
		List<MemberRole> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings({"unchecked", "deprecation"})
	public List<ViewMemberRoleMember> getByRoleId(long roleId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMemberRoleMember.class);
		cr.add(Restrictions.eq("roleId", roleId));
		List<ViewMemberRoleMember> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<SpMemberRoleName> getMemberRoleList(JSONObject obj) {
		int memberId = obj.isNull("Id") == true ? 0 : obj.getInt("Id");
		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_member_role_name", SpMemberRoleName.class);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);
		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpMemberRoleName> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
}
