package tw.gov.mohw.hisac.web.service;



import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.MaintainPlanContentDAO;
import tw.gov.mohw.hisac.web.domain.MaintainPlanContent;

/**
 * 資安維護計畫對象服務
 */
@Service
public class MaintainPlanContentService {
	@Autowired
	MaintainPlanContentDAO maintainPlanContentDAO;

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
	public MaintainPlanContent insert(String json, long memberId) {
		try {			
			JSONObject obj = new JSONObject(json);
			long maintainPlanId = obj.isNull("MaintainPlanId") == true ? 0 : obj.getLong("MaintainPlanId");
			long maintainPlanItemId = obj.isNull("MaintainPlanItemId") == true ? 0 : obj.getLong("MaintainPlanItemId");
			long groupId = obj.isNull("GroupId") == true ? 0 : obj.getLong("GroupId");
			String content = obj.isNull("Content") == true ? "" : obj.getString("Content");													
			
			Date now =new Date();
			MaintainPlanContent entity = new MaintainPlanContent();			
			entity.setMaintainPlanId(maintainPlanId);
			entity.setMaintainPlanItemId(maintainPlanItemId);
			entity.setContent(content);
			entity.setGroupId(groupId);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);						

			maintainPlanContentDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 刪除資安維護計畫項目資料
	 * 
	 * @param maintainPlanId
	 *            maintainPlanId
	 * @param groupId
	 *            groupId	
	 * @return true or false
	 */
	public boolean deleteByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId) {
		try {											
			maintainPlanContentDAO.deleteByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
}
