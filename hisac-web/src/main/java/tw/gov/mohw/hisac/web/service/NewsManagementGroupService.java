package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.gov.mohw.hisac.web.dao.NewsManagementGroupDAO;
import tw.gov.mohw.hisac.web.domain.NewsManagementGroup;
import tw.gov.mohw.hisac.web.domain.ViewNewsManagementGroupMember;

/**
 * 最新消息類別管理服務
 */
@Service
public class NewsManagementGroupService {
	@Autowired
	NewsManagementGroupDAO newsManagementGroupDAO;

	/**
	 * 取得所有最新消息類別資料
	 * 
	 * @return 最新消息類別資料
	 */
	public List<NewsManagementGroup> getAll() {
		return newsManagementGroupDAO.getAll();
	}

	/**
	 * 取得最新消息類別資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 最新消息類別資料
	 */
	public List<ViewNewsManagementGroupMember> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return newsManagementGroupDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 取得News Management Group排序過的資料
	 * 
	 * @return NewsManagementGroup List
	 */
	public List<NewsManagementGroup> getList() {
		List<NewsManagementGroup> entitys = newsManagementGroupDAO.getList();
		return entitys;
	}
	/**
	 * 取得最新消息類別資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 最新消息類別資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return newsManagementGroupDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 新增最新消息類別資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            最新消息類別資料
	 * @return 最新消息類別資料
	 */
	public NewsManagementGroup insert(Long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			Date now = new Date();
			NewsManagementGroup entity = new NewsManagementGroup();
			entity.setName(name);
			entity.setIsEnable(isEnable);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);

			newsManagementGroupDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新最新消息類別資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            最新消息類別群組Id
	 * @return 最新消息類別資料
	 */
	public NewsManagementGroup update(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			Date now = new Date();
			NewsManagementGroup entity = newsManagementGroupDAO.get(id);
			entity.setName(name);
			entity.setIsEnable(isEnable);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			newsManagementGroupDAO.update(entity);

			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	/**
	 * 刪除最新消息類別資料
	 * 
	 * @param id
	 *            最新消息類別群組Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			NewsManagementGroup entity = newsManagementGroupDAO.get(id);
			newsManagementGroupDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 取得最新消息類別資料
	 * 
	 * @param id
	 *            最新消息類別資料Id
	 * @return 最新消息類別資料
	 */
	public NewsManagementGroup getById(Long id) {
		return newsManagementGroupDAO.get(id);
	}
	/**
	 * 最新消息類別資料是否存在
	 * 
	 * @param id
	 *            最新消息類別資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return newsManagementGroupDAO.get(id) != null;
	}
	/**
	 * 最新消息類別資料名稱
	 * 
	 * @param name
	 *            最新消息類別群組名稱
	 * @return 最新消息類別群組名稱資料
	 */
	public NewsManagementGroup findByName(String name) {
		return newsManagementGroupDAO.getByName(name);
	}
	/**
	 * 最新消息類別資料名稱是否存在
	 * 
	 * @param name
	 *            名稱
	 * @return 是否存在
	 */
	public boolean isNameExist(String name) {
		return findByName(name) != null;
	}

}
