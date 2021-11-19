package tw.gov.mohw.hisac.web.dao;

import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.domain.TicketQueue;

@Repository
@Transactional
public class TicketQueueDAOImpl extends BaseSessionFactory implements TicketQueueDAO {

	public void insert(TicketQueue entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(TicketQueue entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(TicketQueue entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}
	@SuppressWarnings("deprecation")
	public TicketQueue get(JSONObject obj) {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(TicketQueue.class);

		String tableName = obj.isNull("TableName") == true ? null : obj.getString("TableName");
		Boolean isApply = obj.isNull("IsApply") == true ? null : obj.getBoolean("IsApply");
		String pre = obj.isNull("Pre") == true ? null : obj.getString("Pre");
		String code = obj.isNull("Code") == true ? null : obj.getString("Code");
		String year = obj.isNull("Year") == true ? null : obj.getString("Year");
		cr.add(Restrictions.eq("tableName", tableName));
		cr.add(Restrictions.eq("isApply", isApply));
		cr.add(Restrictions.eq("pre", pre));
		cr.add(Restrictions.eq("code", code));
		cr.add(Restrictions.eq("year", year));
		return (TicketQueue) cr.uniqueResult();
	}

	/**
	 * 產生新的警訊或通報編號時對 TicketQueue 處理
	 * 
	 * @param tableName,isApply,pre,code
	 * @return postId
	 */
	@SuppressWarnings("deprecation")
	public String insertPostId(String tableName, Boolean isApply, String pre, String code) {
		String postId = "";
		Date now = new Date();
		JSONObject ticketQueueObj = new JSONObject();
		ticketQueueObj.put("TableName", tableName);
		ticketQueueObj.put("IsApply", isApply);
		ticketQueueObj.put("Pre", pre);
		if (code != null)
			ticketQueueObj.put("Code", code);
		else
			ticketQueueObj.put("Code", "NULL");
		ticketQueueObj.put("Year", String.valueOf(now.getYear() + 1900));
		TicketQueue ticketQueue = get(ticketQueueObj);
		int maxId = 0;
		if (ticketQueue != null) {
			maxId = ticketQueue.getNumber();
			ticketQueue.setNumber(maxId + 1);
			update(ticketQueue);
		} else {
			ticketQueue = new TicketQueue();
			ticketQueue.setTableName(ticketQueueObj.getString("TableName"));
			ticketQueue.setIsApply(ticketQueueObj.getBoolean("IsApply"));
			ticketQueue.setPre(ticketQueueObj.getString("Pre"));
			ticketQueue.setCode(ticketQueueObj.getString("Code"));
			ticketQueue.setYear(ticketQueueObj.getString("Year"));
			ticketQueue.setNumber(maxId + 1);
			insert(ticketQueue);
		}
		String maxid = String.format("%04d", maxId + 1);
		if (!isApply)
			postId = postId + "TEMP-";
		postId = postId + ticketQueueObj.getString("Pre") + "-" + ticketQueueObj.getString("Code") + "-" + ticketQueueObj.getString("Year") + "-" + maxid;

		return postId;
	}

	/**
	 * 更新警訊或通報編號時對 TicketQueue 處理
	 * 
	 * @param tableName,isApply,pre,prePostId,code
	 * @return postId
	 */
	@SuppressWarnings("deprecation")
	public String updatePostId(String tableName, Boolean isApply, String pre, String prePostId, String code) {
		String postId = "";
		Date now = new Date();
		JSONObject ticketQueueObj = new JSONObject();
		ticketQueueObj.put("TableName", tableName);
		ticketQueueObj.put("IsApply", isApply);
		ticketQueueObj.put("Pre", pre);
		if (code != null)
			ticketQueueObj.put("Code", code);
		else
			ticketQueueObj.put("Code", "NULL");
		ticketQueueObj.put("Year", String.valueOf(now.getYear() + 1900));
		TicketQueue ticketQueue = get(ticketQueueObj);
		int length = prePostId.split("-").length;
		String preCode = prePostId.split("-")[length - 3];
		int maxId = 0;
		boolean preIsApply = !(prePostId.contains("TEMP-"));
		if (!ticketQueueObj.getString("Code").equals(preCode) || (preIsApply ^ isApply)) {
			if (ticketQueue != null) {
				maxId = ticketQueue.getNumber();
				ticketQueue.setNumber(maxId + 1);
				update(ticketQueue);
			} else {
				ticketQueue = new TicketQueue();
				ticketQueue.setTableName(ticketQueueObj.getString("TableName"));
				ticketQueue.setIsApply(ticketQueueObj.getBoolean("IsApply"));
				ticketQueue.setPre(ticketQueueObj.getString("Pre"));
				ticketQueue.setCode(ticketQueueObj.getString("Code"));
				ticketQueue.setYear(ticketQueueObj.getString("Year"));
				ticketQueue.setNumber(maxId + 1);
				insert(ticketQueue);
			}
		} else
			return prePostId;
		String maxid = String.format("%04d", maxId + 1);
		if (!isApply)
			postId = postId + "TEMP-";
		postId = postId + ticketQueueObj.getString("Pre") + "-" + ticketQueueObj.getString("Code") + "-" + ticketQueueObj.getString("Year") + "-" + maxid;
		return postId;
	}
}