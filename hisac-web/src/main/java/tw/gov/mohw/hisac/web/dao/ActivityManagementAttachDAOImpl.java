package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.ActivityManagementAttach;
import tw.gov.mohw.hisac.web.domain.ViewActivityManagementAttachMember;

import  tw.gov.mohw.hisac.web.WebCrypto;

/**
 * 最新活動管理-附件
 * 
 */
@Repository
@Transactional
public class ActivityManagementAttachDAOImpl extends BaseSessionFactory implements ActivityManagementAttachDAO {
	
	private String user = WebCrypto.getUSER();

	public void insert(ActivityManagementAttach entity) {
		
		try {
			if (user.equals("admin")&& entity != null){
			getSessionFactory().getCurrentSession().save(entity);
			}
				} catch (Exception ex) {
			
			}
	}

	public void update(ActivityManagementAttach entity) {
		try {
			if (user.equals("admin")&& entity != null){
			getSessionFactory().getCurrentSession().update(entity);}
			} catch (Exception ex) {
			}
	}

	public void delete(ActivityManagementAttach entity) {
		try {
			if (user.equals("admin")&& entity != null){
			getSessionFactory().getCurrentSession().delete(entity);}
		} catch (Exception ex) {
		}
	}

	public ActivityManagementAttach get(Long id) {
		try {
		
		if (user.equals("admin") && id != null){
		return getSessionFactory().getCurrentSession().get(ActivityManagementAttach.class, id);
		}
		else {
			return null;
		}
		} catch (Exception ex) {
			return null;
			
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ActivityManagementAttach> getAll() {
		
		try {
			Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ActivityManagementAttach.class);
			List<ActivityManagementAttach> list = cr.list();
			if (list.size() > 0) {
				return list;
			} else {
				return null;
			}
		} catch (Exception ex) {
			return null;
			
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewActivityManagementAttachMember> getAllByActivityManagementId(long activityManagementId) {
		
		try {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewActivityManagementAttachMember.class);
		if ( activityManagementId != 0 )
			cr.add(Restrictions.eq("activityManagementId", activityManagementId));
		try {
			List<ViewActivityManagementAttachMember> list = cr.list();

			if (list.size() > 0) {
				return list;
			} else {
				return null;
			}
		} catch(Exception e) {
			//e.getStackTrace();
			return null;
		}
		} catch (Exception ex) {
			return null;
			
		}
		
		
	}
	
	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		try {
			long activityManagementId = obj.isNull("ActivityManagementId") == true ? 0 : obj.getLong("ActivityManagementId");
			Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ActivityManagementAttach.class);
			cr.setProjection(Projections.rowCount());
			if ( activityManagementId != 0 )
				cr.add(Restrictions.eq("activityManagementId", activityManagementId));
			long total = (long) cr.list().get(0);
			return total;
		} catch (Exception ex) {
			return 0;
			
		}
	}
}
