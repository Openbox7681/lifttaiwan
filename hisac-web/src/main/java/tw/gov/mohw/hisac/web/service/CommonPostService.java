package tw.gov.mohw.hisac.web.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.gov.mohw.hisac.web.dao.CommonPostDAO;
import tw.gov.mohw.hisac.web.domain.CommonPost;
import tw.gov.mohw.hisac.web.domain.ViewCommonPostMember;
/**
 * 認識本系統維護/組織架構管理/何謂ISAC維護服務
 */
@Service
public class CommonPostService {
	@Autowired
	CommonPostDAO commonPostDAO;

	/**
	 * 取得所有文章資料
	 * 
	 * @return 文章資料
	 */
	public List<CommonPost> getAll() {
		return commonPostDAO.getAll();
	}

	/**
	 * 取得文章資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 文章資料
	 */
	public List<ViewCommonPostMember> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return commonPostDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 取得文章資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 文章資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return commonPostDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 新增文章資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            文章資料
	 * @return 文章資料
	 */
	public CommonPost insert(Long memberId, String json) {
		try {
			// 宣告日期物件
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			JSONObject obj = new JSONObject(json);
			String title = obj.isNull("Title") == true ? null : obj.getString("Title");
			String content = obj.isNull("Content") == true ? null : obj.getString("Content");
			boolean isBreakLine = obj.isNull("IsBreakLine") == true ? true : obj.getBoolean("IsBreakLine");
			// String startDateTime = obj.isNull("StartDateTime") == true ? null
			// : obj.getString("StartDateTime");
			// String endDateTime = obj.isNull("StartDateTime") == true ? null :
			// obj.getString("EndDateTime");

			Date startDateTime = obj.isNull("StartDateTime") == true ? dateFormat.parse("0000/00/00") : dateFormat.parse(obj.getString("StartDateTime"));
			Date endDateTime = obj.isNull("EndDateTime") == true ? dateFormat.parse("0000/00/00") : dateFormat.parse(obj.getString("EndDateTime"));

			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			Long postType = obj.isNull("PostType") == true ? null : obj.getLong("PostType");

			Date now = new Date();
			CommonPost entity = new CommonPost();
			entity.setPostType(postType);
			entity.setTitle(title);
			entity.setContent(content);

			// if ( startDateTime != null && !startDateTime.equals("") ) {
			// entity.setStartDateTime(new SimpleDateFormat("yyyy-MM-dd
			// HH:mm:ss").parse(startDateTime));
			// } else {
			// entity.setStartDateTime(null);
			// }
			// if ( endDateTime != null && !endDateTime.equals("") ) {
			// entity.setEndDateTime(new SimpleDateFormat("yyyy-MM-dd
			// HH:mm:ss").parse(endDateTime));
			// } else {
			// entity.setEndDateTime(null);
			// }

			entity.setStartDateTime(startDateTime);
			entity.setEndDateTime(endDateTime);

			entity.setIsEnable(isEnable);
			entity.setIsBreakLine(isBreakLine);
			//
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);

			commonPostDAO.insert(entity);

			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新文章資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            文章Id
	 * @return 文章資料
	 */
	public CommonPost update(long memberId, String json) {
		try {
			// 宣告日期物件
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String title = obj.isNull("Title") == true ? null : obj.getString("Title");
			String content = obj.isNull("Content") == true ? null : obj.getString("Content");
			boolean isBreakLine = obj.isNull("IsBreakLine") == true ? false : obj.getBoolean("IsBreakLine");
			// String StartDateTime = obj.isNull("StartDateTime") == true ? null
			// : obj.getString("StartDateTime");
			// String EndDateTime = obj.isNull("StartDateTime") == true ? null :
			// obj.getString("EndDateTime");
			Date startDateTime = obj.isNull("StartDateTime") == true ? dateFormat.parse("0000/00/00") : dateFormat.parse(obj.getString("StartDateTime"));
			Date endDateTime = obj.isNull("EndDateTime") == true ? dateFormat.parse("0000/00/00") : dateFormat.parse(obj.getString("EndDateTime"));

			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			Date now = new Date();

			CommonPost entity = commonPostDAO.get(id);
			entity.setTitle(title);
			entity.setContent(content);
			entity.setIsBreakLine(isBreakLine);
			// if ( StartDateTime != null && !StartDateTime.equals("") ) {
			// entity.setStartDateTime(new SimpleDateFormat("yyyy-MM-dd
			// HH:mm:ss").parse(StartDateTime));
			// } else {
			// entity.setStartDateTime(null);
			// }
			// if ( EndDateTime != null && !EndDateTime.equals("") ) {
			// entity.setEndDateTime(new SimpleDateFormat("yyyy-MM-dd
			// HH:mm:ss").parse(EndDateTime));
			// } else {
			// entity.setEndDateTime(null);
			// }

			entity.setStartDateTime(startDateTime);
			entity.setEndDateTime(endDateTime);
			entity.setIsEnable(isEnable);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			commonPostDAO.update(entity);

			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	/**
	 * 刪除文章資料
	 * 
	 * @param id
	 *            文章Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			CommonPost entity = commonPostDAO.get(id);
			commonPostDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 取得文章資料
	 * 
	 * @param id
	 *            文章資料Id
	 * @return 文章資料
	 */
	public CommonPost getById(Long id) {
		return commonPostDAO.get(id);
	}
	/**
	 * 文章資料是否存在
	 * 
	 * @param id
	 *            文章資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return commonPostDAO.get(id) != null;
	}

	/**
	 * 標題
	 * 
	 * @param title
	 *            標題
	 * @return 依標題搜尋資料
	 */
	public CommonPost findByTitle(String title) {
		return commonPostDAO.getByTitle(title);
	}

	/**
	 * is_enable
	 * 
	 * @param postType
	 *            postType
	 * @return 依 is_搜尋資料
	 */
	public CommonPost findForPub(Long postType) {
		return commonPostDAO.findForPub(postType);
	}

}
