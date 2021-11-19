package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.domain.ActivityManagementPic;
import tw.gov.mohw.hisac.web.domain.ViewActivityManagementPicMember;

/**
 * 最新活動管理-上傳引用圖檔
 */
@Repository
@Transactional
public class ActivityManagementPicDAOImpl extends BaseSessionFactory implements ActivityManagementPicDAO {
	
	private String user = WebCrypto.getUSER();

	public void insert(ActivityManagementPic entity) {
		try {
			if (user.equals("admin")&& entity != null){
				getSessionFactory().getCurrentSession().save(entity);
			}
		} catch (Exception ex) {
		}	
			
	}

	public void update(ActivityManagementPic entity) {
		try {
			if (user.equals("admin")&& entity != null){
				getSessionFactory().getCurrentSession().update(entity);
			}
		}catch (Exception ex) {
			}	
	}

	public void delete(ActivityManagementPic entity) {
		try {
			if (user.equals("admin")&& entity != null){
				getSessionFactory().getCurrentSession().delete(entity);
			}
		}catch (Exception ex) {
		}	
			
	}

	public ActivityManagementPic get(Long id) {
		try {
			if (user.equals("admin")&& id != null){
				return getSessionFactory().getCurrentSession().get(ActivityManagementPic.class, id);
				}
			else {
					return null;
				}
			}catch (Exception ex) {
				return null;
			}
			
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ActivityManagementPic> getAll() {
		
		try {
			Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ActivityManagementPic.class);
			List<ActivityManagementPic> list = cr.list();
			if (list.size() > 0) {
				return list;
			} else {
				return null;
			}
		}catch (Exception ex) {
			return null;
		}
		
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewActivityManagementPicMember> getAllByActivityManagementId(long activityManagementId) {
		try {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewActivityManagementPicMember.class);
		if (activityManagementId != 0)
			cr.add(Restrictions.eq("activityManagementId", activityManagementId));
		try {
			List<ViewActivityManagementPicMember> list = cr.list();

			if (list.size() > 0) {
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			// e.getStackTrace();
			return null;
		}
		}
		catch (Exception ex) {
			return null;
		}
		
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		try {
		long activityManagementId = obj.isNull("ActivityManagementId") == true ? 0 : obj.getLong("ActivityManagementId");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ActivityManagementPic.class);
		cr.setProjection(Projections.rowCount());
		if (activityManagementId != 0)
			cr.add(Restrictions.eq("activityManagementId", activityManagementId));
		long total = (long) cr.list().get(0);
		return total;
		}
		catch (Exception ex) {
			return 0;
		}
		
	}
}
