package tw.gov.mohw.hisac.web.service;



import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.MaintainPlanItemDAO;
import tw.gov.mohw.hisac.web.domain.MaintainPlanItem;
import tw.gov.mohw.hisac.web.domain.ViewMaintainPlanItemContent;
import tw.gov.mohw.hisac.web.domain.ViewMaintainPlanItemContentFilter;

/**
 * 資安維護計畫對象服務
 */
@Service
public class MaintainPlanItemService {
	@Autowired
	MaintainPlanItemDAO maintainPlanItemDAO;

	/**
	 * 新增資安維護計畫項目資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            資安維護計畫項目資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫項目資料
	 */
	public MaintainPlanItem insert(String json) {
		try {			
			JSONObject obj = new JSONObject(json);
			long maintainPlanId = obj.isNull("MaintainPlanId") == true ? 0 : obj.getLong("MaintainPlanId");
			long parentId = obj.isNull("ParentId") == true ? 0 : obj.getLong("ParentId");
			String desc = obj.isNull("ItemDesc") == true ? null : obj.getString("ItemDesc");			
			String format = obj.isNull("ItemType") == true ? null : obj.getString("ItemType");
			String title = obj.isNull("ItemName") == true ? null : obj.getString("ItemName");								
			
			MaintainPlanItem entity = new MaintainPlanItem();			
			entity.setMaintainPlanId(maintainPlanId);
			entity.setParentId(parentId);
			entity.setDes(desc);
			entity.setFormat(format);
			entity.setTitle(title);					

			maintainPlanItemDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	public List<ViewMaintainPlanItemContent> getListByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId){
		try {
			return maintainPlanItemDAO.getListByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<ViewMaintainPlanItemContentFilter> getListByMaintainPlanId(long maintainPlanId){
		try {
			return maintainPlanItemDAO.getListByMaintainPlanId(maintainPlanId);
		} catch (Exception e) {
			return null;
		}
	}
}
