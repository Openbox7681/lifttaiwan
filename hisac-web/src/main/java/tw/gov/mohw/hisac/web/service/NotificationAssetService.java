package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.NotificationAssetDAO;
import tw.gov.mohw.hisac.web.domain.Notification;
import tw.gov.mohw.hisac.web.domain.NotificationAsset;



/**
 * MessageProcessLog服務
 */
@Service
public class NotificationAssetService {
	@Autowired
	NotificationAssetDAO notificationAssetDAO;
	
	/**
	 * 刪除notificationAsset資料
	 * 
	 * @param entity
	 *            notificationAsset 物件
	 *            	
	 * @return 是否刪除成功
	 */
	
	public boolean delete (Long id) {
		try {
			NotificationAsset entity = notificationAssetDAO.get(id);
			notificationAssetDAO.delete(entity);
			return true;
		} catch (Exception e) {
			 e.printStackTrace();
			return false;
		}
		
	}
	
	
	
	
	
	
	

	/**
	 * 新增notificationAsset資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            notificationAsset資料
	 *            	
	 * @return notificationAsset資料
	 */
	public NotificationAsset insert(Long memberId, String json, String notificationId) {
		try {
			JSONObject obj = new JSONObject(json);
			String brand = obj.isNull("Brand") == true ? null : obj.getString("Brand");
			String model = obj.isNull("Model") == true ? null : obj.getString("Model");
			String assetClass = obj.isNull("AssetClass") == true ? null : obj.getString("AssetClass");
			String assetClassName = obj.isNull("AssetName") == true ? null:obj.getString("AssetName");
			String operatingSystemVersion = obj.isNull("OperatingSystemVersion") == true ? null : obj.getString("OperatingSystemVersion");
			
			
			
			
			
			NotificationAsset entity = new NotificationAsset();
			entity.setNotificationId(notificationId);
			entity.setBrand(brand);
			entity.setModel(model);
			entity.setAssetClass(assetClass);
			entity.setAssetClassName(assetClassName);
			entity.setCreateId(memberId);
			entity.setCreateTime(new Date());
			entity.setOperatingSystemVersion(operatingSystemVersion);
			notificationAssetDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 取得NotificationAsset資料
	 * 
	 * @param notificationId
	 *            notificationId	
	 * @return NotificationAsset資料
	 */
	public List<NotificationAsset> getAllByNotificationId(String notificationId) {
		try {
			return notificationAssetDAO.getAllByNotificationId(notificationId);
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 取得NotificationAsset 數量資料
	 * 
	 * @param json
	 *            搜尋條件	
	 * @return 資料大小
	 */
	
	
	public long getListSizeAllByNotificationId(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return notificationAssetDAO.getListSize(obj);
		} catch (Exception e) {
			//e.printStackTrace();
			return 0;
		}
	}
	
	
	
	
}
