package tw.gov.mohw.hisac.web.dao;

import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.VideoData;

/**
 * 表單服務
 */
@Repository
@Transactional
public class VideoDataDAOImpl extends BaseSessionFactory implements VideoDataDAO {

	public void insert(VideoData entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(VideoData entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(VideoData entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public VideoData get(Long id) {
		return getSessionFactory().getCurrentSession().get(VideoData.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<VideoData> getList(JSONObject obj) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");

		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		String title = obj.isNull("Title") == true ? null : obj.getString("Title");
		String date = obj.isNull("Date") == true ? null : obj.getString("Date");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Boolean isShow = obj.isNull("IsShow") == true ? null : obj.getBoolean("IsShow");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(VideoData.class);
		if (id != 0) {
			cr.add(Restrictions.eq("id", id));
		}
		if (title != null && title.length()>0) {
			cr.add(Restrictions.like("title", "%"+title+"%"));
		}
		if (date != null && date.length()>0) {
			try {
				cr.add(Restrictions.eq("createTime", sdf.parse(sdf.format(sdf2.parse(date.split("T")[0])))));
			} catch(Exception e) {
				
			}
		}
		if (isEnable != null) {
			cr.add(Restrictions.eq("isEnable", isEnable));
		}
		if (isShow != null) {
			cr.add(Restrictions.eq("isShow", isShow));
		}
		if (dir == true) {
			cr.addOrder(Order.desc(sort));
		}else {
			cr.addOrder(Order.asc(sort));
		}
		cr.setFirstResult(start);
		if (maxRows != 0) {
			cr.setMaxResults(maxRows);
		}
		List<VideoData> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		String title = obj.isNull("Title") == true ? null : obj.getString("Title");
		String date = obj.isNull("Date") == true ? null : obj.getString("Date");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Boolean isShow = obj.isNull("IsShow") == true ? null : obj.getBoolean("IsShow");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(VideoData.class);
		cr.setProjection(Projections.rowCount());

		if (id != 0) {
			cr.add(Restrictions.eq("id", id));
		}
		if (title != null && title.length()>0) {
			cr.add(Restrictions.like("title", title));
		}
		if (date != null && date.length()>0) {
			cr.add(Restrictions.eq("date", sdf.format(date)));
		}
		if (isEnable != null) {
			cr.add(Restrictions.eq("isEnable", isEnable));
		}
		if (isShow != null) {
			cr.add(Restrictions.eq("isShow", isShow));
		}
		long total = (long) cr.list().get(0);
		return total;
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<VideoData> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(VideoData.class);
		List<VideoData> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
}
