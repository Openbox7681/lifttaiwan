package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import tw.gov.mohw.hisac.web.domain.NotificationLog;
import tw.gov.mohw.hisac.web.domain.ViewNotificationLogMember;

public interface NotificationLogDAO {
	public void insert(NotificationLog entity);			
	public List<ViewNotificationLogMember> getByNotificationId(String notificationId);
}
