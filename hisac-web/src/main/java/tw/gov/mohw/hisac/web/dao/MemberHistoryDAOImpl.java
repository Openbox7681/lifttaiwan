package tw.gov.mohw.hisac.web.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.domain.MemberHistory;

@Repository
@Transactional
public class MemberHistoryDAOImpl extends BaseSessionFactory implements MemberHistoryDAO {

	public void insert(MemberHistory entity) {
		Date now = new Date();
		entity.setCreateTime(now);
		entity.setModifyTime(now);
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(MemberHistory entity) {
		Date now = new Date();
		entity.setModifyTime(now);
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(MemberHistory entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public MemberHistory get(Long id) {
		return getSessionFactory().getCurrentSession().get(MemberHistory.class, id);
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<MemberHistory> getAll() {
		return getSessionFactory().getCurrentSession().createCriteria(MemberHistory.class).list();
	}

	@SuppressWarnings("deprecation")
	public MemberHistory getByMemberId(Long memberId) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MemberHistory.class);
		cr.add(Restrictions.eq("memberId", memberId));
		cr.addOrder(Order.desc("id"));
		cr.setMaxResults(1);
		return (MemberHistory) cr.uniqueResult();
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public boolean checkMemberHistory(Long memberId, String newCode, int historyTimes, int historyDays) {
		boolean resultTimes = false;
		boolean resultDays = false;
		{
			// History Times
			Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MemberHistory.class);
			cr.add(Restrictions.eq("memberId", memberId));
			cr.addOrder(Order.desc("id"));
			cr.setMaxResults(historyTimes);
			List<MemberHistory> memberHistory = cr.list();
			for (MemberHistory history : memberHistory) {
				if (history.getPassword().equals(WebCrypto.getHash(WebCrypto.HashType.SHA512, newCode + history.getSalt()))) {
					resultTimes = true;
				}
			}
		}
		{
			// History Days
			Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MemberHistory.class);
			cr.add(Restrictions.eq("memberId", memberId));
			cr.add(Restrictions.ge("createTime", WebDatetime.addDays(new Date(), -(historyDays))));
			cr.addOrder(Order.desc("id"));
			List<MemberHistory> memberHistory = cr.list();
			for (MemberHistory history : memberHistory) {
				if (history.getPassword().equals(WebCrypto.getHash(WebCrypto.HashType.SHA512, newCode + history.getSalt()))) {
					resultDays = true;
				}
			}
		}
		return resultTimes || resultDays;
	}
	
	//給定天數,清查出該天數前所有審核過帳號
	@SuppressWarnings({"deprecation", "unchecked"})
	public  List<MemberHistory> getMemberHistoryIsExamine(int historyDays){
		
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(MemberHistory.class);
		
		

		
		
			
			
			
			
		return null;		
	};
		
		
		
		
}