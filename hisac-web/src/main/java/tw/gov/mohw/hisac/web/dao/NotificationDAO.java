package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.Notification;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.SpNotification;

public interface NotificationDAO {
	public void insert(Notification entity);
	public void update(Notification entity);
	public void delete(Notification entity);
	public Notification get(String id);
	public List<Notification> getAll();
	public List<Notification> getList(JSONObject obj);
	public long getListSize(JSONObject obj);

	public List<SpNotification> getSpList(JSONObject obj);
	public long getSpListSize(JSONObject obj);
	public long getSpFormCount(JSONObject obj);
	public List<SpButtonCount> getSpButtonCount(JSONObject obj);

}
