package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.domain.Links;
import tw.gov.mohw.hisac.web.domain.ViewLinksMember;

/**
 * 相關連結管理
 */
@Repository
@Transactional
public class LinksDAOImpl extends BaseSessionFactory implements LinksDAO {

	public void insert(Links entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(Links entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(Links entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public Links get(Long id) {
		return getSessionFactory().getCurrentSession().get(Links.class, id);
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	public List<Links> getAll() {
		// String postType = "1";
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Links.class);
		// cr.add(Restrictions.eq("postType", postType));
		List<Links> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewLinksMember> getList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		// boolean dir = obj.isNull("dir") == true ? false :
		// obj.getBoolean("dir");
		// String sort = obj.isNull("sort") == true ? "Id" :
		// obj.getString("sort");

		Long linksId = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		String startShowDateTime = obj.isNull("StartShowDateTime") == true ? null : obj.getString("StartShowDateTime");
		String postDateTime = obj.isNull("PostDateTime") == true ? null : obj.getString("PostDateTime");
		String startPostDateTime = obj.isNull("StartPostDateTime") == true ? null : obj.getString("StartPostDateTime");
		String endPostDateTime = obj.isNull("StartPostDateTime") == true ? null : obj.getString("StartPostDateTime");
		String title = obj.isNull("Title") == true ? null : obj.getString("Title");
		// String content = obj.isNull("Content") == true ? null :
		// obj.getString("Content");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewLinksMember.class);

		if (linksId != 0) {
			cr.add(Restrictions.eq("linksId", linksId));
		}
		if (startShowDateTime != null) {
			cr.add(Restrictions.le("startDateTime", WebDatetime.parse(startShowDateTime, "yyyy-MM-dd")));
		}
		if (postDateTime != null) {
			cr.add(Restrictions.eq("postDateTime", WebDatetime.parse(postDateTime, "yyyy-MM-dd")));
		}
		if (startPostDateTime != null) {
			cr.add(Restrictions.le("postDateTime", WebDatetime.parse(startShowDateTime, "yyyy-MM-dd")));
		}
		if (endPostDateTime != null) {
			cr.add(Restrictions.ge("postDateTime", WebDatetime.parse(endPostDateTime, "yyyy-MM-dd")));
		}
		if (title != null) {
			cr.add(Restrictions.like("title", "%" + title + "%"));
		}
		// if (content != null) {
		// cr.add(Restrictions.like("content", "%" + content + "%"));
		// }
		if (isEnable != null) {
			cr.add(Restrictions.eq("isEnable", isEnable));
		}

		// if (dir == true)
		// cr.addOrder(Order.desc(sort));
		// else
		// cr.addOrder(Order.asc(sort));

		cr.setFirstResult(start);
		if (maxRows != 0)
			cr.setMaxResults(maxRows);
		List<ViewLinksMember> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(JSONObject obj) {

		Long linksId = obj.isNull("LinksId") == true ? 0 : obj.getLong("LinksId");
		String startShowDateTime = obj.isNull("StartShowDateTime") == true ? null : obj.getString("StartShowDateTime");
		String postDateTime = obj.isNull("PostDateTime") == true ? null : obj.getString("PostDateTime");
		String startPostDateTime = obj.isNull("StartPostDateTime") == true ? null : obj.getString("StartPostDateTime");
		String endPostDateTime = obj.isNull("StartPostDateTime") == true ? null : obj.getString("StartPostDateTime");
		String title = obj.isNull("Title") == true ? null : obj.getString("Title");
		// String content = obj.isNull("Content") == true ? null :
		// obj.getString("Content");
		Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Links.class);
		if (linksId != 0) {
			cr.add(Restrictions.eq("linksId", linksId));
		}
		if (startShowDateTime != null) {
			cr.add(Restrictions.le("startDateTime", WebDatetime.parse(startShowDateTime, "yyyy-MM-dd")));
		}
		if (postDateTime != null) {
			cr.add(Restrictions.eq("postDateTime", WebDatetime.parse(postDateTime, "yyyy-MM-dd")));
		}
		if (startPostDateTime != null) {
			cr.add(Restrictions.le("postDateTime", WebDatetime.parse(startShowDateTime, "yyyy-MM-dd")));
		}
		if (endPostDateTime != null) {
			cr.add(Restrictions.ge("postDateTime", WebDatetime.parse(endPostDateTime, "yyyy-MM-dd")));
		}
		if (title != null) {
			cr.add(Restrictions.like("title", "%" + title + "%"));
		}
		// if (content != null) {
		// cr.add(Restrictions.like("content", "%" + content + "%"));
		// }
		if (isEnable != null) {
			cr.add(Restrictions.eq("isEnable", isEnable));
		}
		long total = (long) cr.list().size();
		return total;
	}
}