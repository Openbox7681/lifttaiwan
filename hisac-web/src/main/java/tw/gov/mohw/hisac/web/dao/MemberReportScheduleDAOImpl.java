package tw.gov.mohw.hisac.web.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.domain.MemberReportSchedule;




@Repository
@Transactional
public class MemberReportScheduleDAOImpl extends BaseSessionFactory implements MemberReportScheduleDAO {

	public void insert(MemberReportSchedule role) {
		getSessionFactory().getCurrentSession().save(role);
	}

	public void update(MemberReportSchedule role) {
		getSessionFactory().getCurrentSession().update(role);
	}

	public void delete(MemberReportSchedule role) {
		getSessionFactory().getCurrentSession().delete(role);
	}

	public MemberReportSchedule get(Long id) {
		return (MemberReportSchedule) getSessionFactory().getCurrentSession().get(MemberReportSchedule.class, id);
	}
	
	
	
	
	
	@SuppressWarnings({"deprecation", "unchecked"})

	
	public String getLastScheduleTime() {
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MemberReportSchedule.class);
		cr.add(Restrictions.eq("isOldSchedule", true));
		cr.addOrder(Order.desc("scheduleTime"));

		cr.setFirstResult(0);
		cr.setMaxResults(1);
		
		
		List<MemberReportSchedule> list = cr.list();
		
		if (list.size() > 0) {
			MemberReportSchedule memberReportSchedule = list.get(0);
			
			
			return WebDatetime.toString(memberReportSchedule.getScheduleTime(), "yyyy-MM-dd");
			
		}else {
			
			//初始時間
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, 2020);
			cal.set(Calendar.MONTH, 12);
			cal.set(Calendar.DATE, 1);
			
			Date dt = cal.getTime();
			
			
			return WebDatetime.toString(dt, "yyyy-MM-dd");

		}

	
	}
	

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<MemberReportSchedule> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MemberReportSchedule.class);
		List<MemberReportSchedule> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getListSize(String json) {
		JSONObject obj = new JSONObject(json);
		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		
		String sdate = obj.isNull("Sdate") == true ? null : obj.getString("Sdate");
		String edate = obj.isNull("Edate") == true ? null : obj.getString("Edate");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MemberReportSchedule.class);
		cr.setProjection(Projections.rowCount());
		if (id != 0) {
			cr.add(Restrictions.eq("id", id));
		}
		
		try {
			if (sdate != null) {
				cr.add(Restrictions.ge("scheduleTime", new SimpleDateFormat("yyyy-MM-dd").parse(sdate)));
			}
			if (edate != null) {
				cr.add(Restrictions.le("scheduleTime", new SimpleDateFormat("yyyy-MM-dd").parse(edate)));
			}
		} catch (ParseException e) {
			//e.printStackTrace();
		}
		
		long total = (long) cr.list().get(0);
		return total;
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<MemberReportSchedule> getList(String json) {
		JSONObject obj = new JSONObject(json);

		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 10 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");

		long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
		
		
		String sdate = obj.isNull("Sdate") == true ? null : obj.getString("Sdate");
		String edate = obj.isNull("Edate") == true ? null : obj.getString("Edate");

		
		
		
		
		
		// long createId = obj.isNull("CreateId") == true ? 0 :
		// obj.getLong("CreateId");
		// long modifyId = obj.isNull("ModifyId") == true ? 0 :
		// obj.getLong("ModifyId");

		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MemberReportSchedule.class);
		if (id != 0) {
			cr.add(Restrictions.eq("id", id));
		}
		
		
		try {
			if (sdate != null) {
				cr.add(Restrictions.ge("scheduleTime", new SimpleDateFormat("yyyy-MM-dd").parse(sdate)));
			}
			if (edate != null) {
				cr.add(Restrictions.le("scheduleTime", new SimpleDateFormat("yyyy-MM-dd").parse(edate)));
			}
		} catch (ParseException e) {
			//e.printStackTrace();
		}
		
		if (dir == true)
			cr.addOrder(Order.desc(sort));
		else
			cr.addOrder(Order.asc(sort));
		cr.setFirstResult(start);
		cr.setMaxResults(maxRows);
		List<MemberReportSchedule> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

}
