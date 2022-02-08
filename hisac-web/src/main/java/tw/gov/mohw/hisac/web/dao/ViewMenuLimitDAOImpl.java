package tw.gov.mohw.hisac.web.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tw.gov.mohw.hisac.web.domain.ViewMenuLimit;

@Repository
@Transactional
public class ViewMenuLimitDAOImpl extends BaseSessionFactory implements ViewMenuLimitDAO {

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<ViewMenuLimit> getMenu(Long memberId) {
		List<ViewMenuLimit> list = new ArrayList<ViewMenuLimit>();
		try {
			Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMenuLimit.class);

			Conjunction cr1 = Restrictions.conjunction();
			cr1.add(Restrictions.eqOrIsNull("memberId", null));
			cr1.add(Restrictions.eq("formCode", "separator"));
			cr1.add(Restrictions.eq("formName", "separator"));

			Conjunction cr2 = Restrictions.conjunction();
			cr2.add(Restrictions.eqOrIsNull("memberId", memberId));
			cr2.add(Restrictions.eq("actionRead", true));

			Disjunction or = Restrictions.disjunction();
			or.add(cr1);
			or.add(cr2);

			cr.add(or);
			cr2.add(Restrictions.eq("formIsShow", true));
			cr2.add(Restrictions.eq("subsystemIsShow", true));
			cr.addOrder(Order.asc("subsystemSort"));
			cr.addOrder(Order.asc("formSort"));
			list = cr.list();
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return list;
	}
	
	@SuppressWarnings({"unchecked", "deprecation"})
	public ViewMenuLimit getMenuByIdAndName(Long memberId, String formName){

			
			Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMenuLimit.class);
			cr.add(Restrictions.eq("memberId", memberId));
			cr.add(Restrictions.eq("formName", formName));
			cr.addOrder(Order.desc("formId"));

			cr.setMaxResults(1);
			return (ViewMenuLimit) cr.uniqueResult();
		
		
		
	}

	@SuppressWarnings("deprecation")
	public ViewMenuLimit getAction(Long memberId, String controllerName, String actionName) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewMenuLimit.class);
		cr.add(Restrictions.eq("memberId", memberId));
		cr.add(Restrictions.eq("controllerName", controllerName).ignoreCase());
		cr.add(Restrictions.eq("actionName", actionName).ignoreCase());
		return (ViewMenuLimit) cr.uniqueResult();
	}
}
