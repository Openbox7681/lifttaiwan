package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.dao.LinksDAO;
import tw.gov.mohw.hisac.web.domain.Links;
import tw.gov.mohw.hisac.web.domain.ViewLinksMember;

/**
 * 相關連結管理服務
 */
@Service
public class LinksService {
	@Autowired
	LinksDAO linksDAO;

	static String newsGlobalData = null;

	public void setGlobalData(String globalData) {
		newsGlobalData = globalData;
	}

	public void resetGlobalData() {
		newsGlobalData = null;
	}

	public String getGlobalData() {
		return newsGlobalData;
	}

	/**
	 * 取得所有相關連結資料
	 * 
	 * @return 相關連結資料
	 */
	public List<Links> getAll() {
		return linksDAO.getAll();
	}

	/**
	 * 取得相關連結資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 相關連結資料
	 */
	public List<ViewLinksMember> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return linksDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得相關連結資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 相關連結資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return linksDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 新增相關連結資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            相關資料
	 * @return 相關連結資料
	 */
	public Links insert(Long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			// String postType = "1";
			Date postDateTime = obj.isNull("PostDateTime") == true ? new Date() : WebDatetime.parse(obj.getString("PostDateTime"), "yyyy-MM-dd");
			String title = obj.isNull("Title") == true ? null : obj.getString("Title");
			String sourceName = obj.isNull("SourceName") == true ? null : obj.getString("SourceName");
			String sourceLink = obj.isNull("SourceLink") == true ? null : obj.getString("SourceLink");
			// String contentType = obj.isNull("ContentType") == true ? null :
			// obj.getString("ContentType");
			// String content = obj.isNull("Content") == true ? null :
			// obj.getString("Content");
			// String externalLink = obj.isNull("ExternalLink") == true ? null :
			// obj.getString("ExternalLink");
			Boolean isBreakLine = obj.isNull("IsBreakLine") == true ? null : obj.getBoolean("IsBreakLine");
			Date startDateTime = obj.isNull("StartDateTime") == true ? WebDatetime.parse("1753-01-01", "yyyy-MM-dd") : WebDatetime.parse(obj.getString("StartDateTime"), "yyyy-MM-dd");
			Date endDateTime = obj.isNull("EndDateTime") == true ? WebDatetime.parse("9999-12-31", "yyyy-MM-dd") : WebDatetime.parse(obj.getString("EndDateTime"), "yyyy-MM-dd");
			Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");

			Date now = new Date();
			Links entity = new Links();
			// entity.setPostType(postType);
			entity.setPostDateTime(postDateTime);
			entity.setTitle(title);
			entity.setSourceName(sourceName);
			entity.setSourceLink(sourceLink);
			// entity.setContentType(contentType);
			// entity.setContent(content);
			// entity.setExternalLink(externalLink);
			entity.setIsBreakLine(isBreakLine);
			entity.setStartDateTime(startDateTime);
			entity.setEndDateTime(endDateTime);
			entity.setIsEnable(isEnable);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			linksDAO.insert(entity);
			resetGlobalData();
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新相關連結資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            相關資料
	 * @return 相關連結資料
	 */
	public Links update(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			// String postType = "1";
			Date postDateTime = obj.isNull("PostDateTime") == true ? new Date() : WebDatetime.parse(obj.getString("PostDateTime"), "yyyy-MM-dd");
			String title = obj.isNull("Title") == true ? null : obj.getString("Title");
			String sourceName = obj.isNull("SourceName") == true ? null : obj.getString("SourceName");
			String sourceLink = obj.isNull("SourceLink") == true ? null : obj.getString("SourceLink");
			// String contentType = obj.isNull("ContentType") == true ? null :
			// obj.getString("ContentType");
			// String content = obj.isNull("Content") == true ? null :
			// obj.getString("Content");
			// String externalLink = obj.isNull("ExternalLink") == true ? null :
			// obj.getString("ExternalLink");
			Boolean isBreakLine = obj.isNull("IsBreakLine") == true ? null : obj.getBoolean("IsBreakLine");
			Date startDateTime = obj.isNull("StartDateTime") == true ? WebDatetime.parse("1753-01-01", "yyyy-MM-dd") : WebDatetime.parse(obj.getString("StartDateTime"), "yyyy-MM-dd");
			Date endDateTime = obj.isNull("EndDateTime") == true ? WebDatetime.parse("9999-12-31", "yyyy-MM-dd") : WebDatetime.parse(obj.getString("EndDateTime"), "yyyy-MM-dd");
			Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");

			Date now = new Date();
			Links entity = linksDAO.get(id);
			// entity.setPostType(postType);
			entity.setPostDateTime(postDateTime);
			entity.setTitle(title);
			entity.setSourceName(sourceName);
			entity.setSourceLink(sourceLink);
			// entity.setContentType(contentType);
			// entity.setContent(content);
			// entity.setExternalLink(externalLink);
			entity.setIsBreakLine(isBreakLine);
			entity.setStartDateTime(startDateTime);
			entity.setEndDateTime(endDateTime);
			entity.setIsEnable(isEnable);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			linksDAO.update(entity);
			resetGlobalData();
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 刪除相關連結資料
	 * 
	 * @param id
	 *            相關連結Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			Links entity = linksDAO.get(id);
			linksDAO.delete(entity);
			resetGlobalData();
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 相關連結資料是否存在
	 * 
	 * @param id
	 *            最新消息管理資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return linksDAO.get(id) != null;
	}

	/**
	 * 取得相關連結資料
	 * 
	 * @param id
	 *            相關連結資料Id
	 * @return 相關連結資料
	 */
	public Links get(Long id) {
		return linksDAO.get(id);
	}

}
