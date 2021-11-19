package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.dao.OrgVariable.OrgType;
import tw.gov.mohw.hisac.web.domain.ViewMemberSignApply;

@Repository
@Transactional
public class MemberSignApplyDAOImpl extends BaseSessionFactory implements MemberSignApplyDAO {

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewMemberSignApply> getList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMemberSignApply.class);
		cr.add(Restrictions.eq("orgType", OrgType.Member.getValue()));
		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
		cr.setFirstResult(start);
		if (maxRows != 0)
			cr.setMaxResults(maxRows);
		List<ViewMemberSignApply> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMemberSignApply.class);
		cr.setProjection(Projections.rowCount());
		cr.add(Restrictions.eq("orgType", OrgType.Member.getValue()));
		long total = (long) cr.list().get(0);
		return total;
	}

}