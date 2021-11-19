package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.gov.mohw.hisac.web.dao.NotificationLogDAO;
import tw.gov.mohw.hisac.web.domain.NotificationLog;
import tw.gov.mohw.hisac.web.domain.ViewNotificationLogMember;

/**
 * MessageProcessLog服務
 */
@Service
public class NotificationLogService {
	@Autowired
	NotificationLogDAO notificationLogDAO;

	/**
	 * 新增NotificationLog資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            NotificationLog資料
	 *            	
	 * @return NotificationLog資料
	 */
	public NotificationLog insert(Long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String notificationId = obj.isNull("NotificationId") == true ? null : obj.getString("NotificationId");
			int ceffectLevel = obj.isNull("CeffectLevel") == true ? 0 : obj.getInt("CeffectLevel");
			int ieffectLevel = obj.isNull("IeffectLevel") == true ? 0 : obj.getInt("IeffectLevel");
			int aeffectLevel = obj.isNull("AeffectLevel") == true ? 0 : obj.getInt("AeffectLevel");
			int effectLevel = obj.isNull("EffectLevel") == true ? 0 : obj.getInt("EffectLevel");
			
			NotificationLog entity = new NotificationLog();
			entity.setNotificationId(notificationId);
			entity.setAeffectLevel(aeffectLevel);
			entity.setCeffectLevel(ceffectLevel);
			entity.setEffectLevel(effectLevel);
			entity.setIeffectLevel(ieffectLevel);
			entity.setCreateId(memberId);
			entity.setCreateTime(new Date());
			notificationLogDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 取得ViewNotificationLogMember資料
	 * 
	 * @param notificationId
	 *            notificationId	
	 * @return ViewNotificationLogMember資料
	 */
	public List<ViewNotificationLogMember> getByNotificationId(String notificationId) {
		try {
			return notificationLogDAO.getByNotificationId(notificationId);
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
}
