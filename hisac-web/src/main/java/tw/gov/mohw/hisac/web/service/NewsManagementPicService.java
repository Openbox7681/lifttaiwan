package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.NewsManagementPicDAO;
import tw.gov.mohw.hisac.web.domain.NewsManagementPic;
import tw.gov.mohw.hisac.web.domain.ViewNewsManagementPicMember;

/**
 * 文章附加圖檔服務
 */
@Service
public class NewsManagementPicService {
	@Autowired
	NewsManagementPicDAO newsManagementPicDAO;

	/**
	 * 取得所有文章附加圖檔資料
	 * 
	 * @return 文章附加圖檔資料
	 */
	public List<NewsManagementPic> getAll() {
		return newsManagementPicDAO.getAll();
	}

	/**
	 * 取得文章附加圖檔資料
	 * 
	 * @param newsManagementId
	 *            查詢條件
	 * @return 文章附加圖檔資料
	 */
	public List<ViewNewsManagementPicMember> getAllByNewsManagementId(long newsManagementId) {
		try {
			return newsManagementPicDAO.getAllByNewsManagementId(newsManagementId);
		} catch (Exception e) {
			e.getStackTrace();
			return null;
		}
	}
	/**
	 * 取得文章附加圖檔資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 文章附加圖檔資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return newsManagementPicDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}
	/**
	 * 新增引用圖檔資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            欄位資料
	 * @param bytes
	 *            檔案
	 * @return 引用圖檔資料
	 */
	public NewsManagementPic insert(long memberId, String json, byte[] bytes) {
		try {
			JSONObject obj = new JSONObject(json);
			long newsManagementId = obj.isNull("NewsManagementId") == true ? null : obj.getLong("NewsManagementId");
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");
			String fileName = obj.isNull("FileName") == true ? null : obj.getString("FileName");
			String fileType = obj.isNull("FileType") == true ? null : obj.getString("FileType");
			long fileSize = obj.isNull("FileSize") == true ? null : obj.getLong("FileSize");
			long imageWidth = obj.isNull("ImageWidth") == true ? null : obj.getLong("ImageWidth");
			long imageHeight = obj.isNull("ImageHeight") == true ? null : obj.getLong("ImageHeight");
			String fileHash = obj.isNull("FileHash") == true ? null : obj.getString("FileHash");

			Date now = new Date();
			NewsManagementPic entity = new NewsManagementPic();
			entity.setNewsManagementId(newsManagementId);
			entity.setFileDesc(fileDesc);
			entity.setFileName(fileName);
			entity.setFileType(fileType);
			entity.setFileSize(fileSize);
			entity.setImageWidth(imageWidth);
			entity.setImageHeight(imageHeight);
			entity.setFileHash(fileHash);
			entity.setFileContent(bytes);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);

			newsManagementPicDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新文章附加圖檔資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            相關資料
	 * @return 文章附加圖檔資料
	 */
	public NewsManagementPic update(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String fileDesc = obj.isNull("FileDesc") == true ? null : obj.getString("FileDesc");
			Date now = new Date();

			NewsManagementPic entity = newsManagementPicDAO.get(id);
			entity.setFileDesc(fileDesc);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			newsManagementPicDAO.update(entity);

			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	/**
	 * 刪除文章附加圖檔資料
	 * 
	 * @param id
	 *            文章附加圖檔Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			NewsManagementPic entity = newsManagementPicDAO.get(id);
			newsManagementPicDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 取得文章附加圖檔資料
	 * 
	 * @param id
	 *            文章附加圖檔Id
	 * @return 文章附加圖檔資料
	 */
	public NewsManagementPic getById(Long id) {
		return newsManagementPicDAO.get(id);
	}
	/**
	 * 文章附加圖檔資料是否存在
	 * 
	 * @param id
	 *            文章附加圖檔Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return newsManagementPicDAO.get(id) != null;
	}

}
