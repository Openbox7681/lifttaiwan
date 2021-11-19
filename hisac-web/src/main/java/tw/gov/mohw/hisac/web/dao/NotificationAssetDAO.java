package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.NotificationAsset;




public interface NotificationAssetDAO {
	public void insert(NotificationAsset entity);
	public void update(NotificationAsset entity);
	public void delete(NotificationAsset entity);
	public NotificationAsset get(Long id);
	public List<NotificationAsset> getAll();
	public List<NotificationAsset> getAllByNotificationId(String notificationId);
	public long getListSize(JSONObject obj);
}
