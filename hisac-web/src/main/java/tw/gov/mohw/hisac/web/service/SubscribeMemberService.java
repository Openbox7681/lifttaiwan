package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.SubscribeMemberDAO;
import tw.gov.mohw.hisac.web.domain.SubscribeMember;
import tw.gov.mohw.hisac.web.domain.ViewSubscribeMember;

/**
 * 人員訂閱服務
 */
@Service
public class SubscribeMemberService {
	@Autowired
	SubscribeMemberDAO subscribeMemberDAO;

	/**
	 * 新增人員訂閱資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            人員訂閱資料
	 * @return 人員訂閱資料
	 */
	public SubscribeMember insert(Long createId, Long memberId, String json) {
		try {			
			JSONObject obj = new JSONObject(json);
			long subscribeId = obj.isNull("SubscribeId") == true ? 0 : obj.getLong("SubscribeId");			
			Date now = new Date();
			SubscribeMember entity = new SubscribeMember();			
			entity.setSubscribeId(subscribeId);
			entity.setMemberId(memberId);
			entity.setCreateId(createId);
			entity.setCreateTime(now);
			entity.setModifyId(createId);
			entity.setModifyTime(now);

			subscribeMemberDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 刪除人員訂閱資料
	 * 
	 * @param id
	 *            SubscribeMemberId
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			SubscribeMember entity = subscribeMemberDAO.get(id);
			subscribeMemberDAO.delete(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 人員訂閱名稱
	 * 
	 * @param memberId
	 *            使用者Id
	 * @return 人員訂閱資料
	 */
	public List<SubscribeMember> getByMemberId(long memberId) {
		return subscribeMemberDAO.getByMemberId(memberId);
	}
	
	/**
	 * 人員訂閱名稱
	 * 
	 * @param subscribeId
	 *            訂閱Id
	 * @return 人員訂閱資料
	 */
	public List<ViewSubscribeMember> getBySubscribeName(String subscribeName) {
		return subscribeMemberDAO.getBySubscribeName(subscribeName);
	}
}