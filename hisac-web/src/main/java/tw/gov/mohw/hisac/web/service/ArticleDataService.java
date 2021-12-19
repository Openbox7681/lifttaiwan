
package tw.gov.mohw.hisac.web.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.ArticleDataDAO;
import tw.gov.mohw.hisac.web.domain.ArticleData;

@Service
public class ArticleDataService {
	final static Logger logger = LoggerFactory.getLogger(MemberService.class);

	@Autowired
	ArticleDataDAO articleDataDAO;

	public List<ArticleData> getAll() {
		return articleDataDAO.getAll();
	}

	public boolean isExist(Long id) {
		return articleDataDAO.get(id) != null;
	}
	
	public List<ArticleData> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return articleDataDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return articleDataDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	public ArticleData insert(Long memberId, String json) {
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		try {
			JSONObject obj = new JSONObject(json);
			String title = obj.isNull("Title") == true ? null : obj.getString("Title");
			String description = obj.isNull("Description") == true ? null : obj.getString("Description");
			String author = obj.isNull("Author") == true ? null : obj.getString("Author");
			String tag = obj.isNull("Tag") == true ? null : obj.getString("Tag");
			Long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");
			String content = obj.isNull("Content") == true ? null : obj.getString("Content");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			boolean isShow = obj.isNull("IsShow") == true ? false : obj.getBoolean("IsShow");
			String img = obj.isNull("Img") == true ? null : obj.getString("Img");
			Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");
			
			ArticleData entity = new ArticleData();
			entity.setTitle(title);
			entity.setDescription(description);
			entity.setAuthor(author);
			entity.setTag(tag);
			entity.setSort(sort);
			entity.setContent(content);
			entity.setImg(img);
			entity.setStatus(status);
			entity.setViews(Long.valueOf(1));
			entity.setIsEnable(isEnable);
			entity.setIsShow(isShow);
			entity.setCreateId(memberId);
			entity.setCreateTime(sdf2.parse(sdf2.format(now)));
			entity.setModifyId(memberId);
			entity.setModifyTime(now);

			articleDataDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			return null;
		}
	}

	public ArticleData update(long memberId, String json) {
		Date now = new Date();
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String title = obj.isNull("Title") == true ? null : obj.getString("Title");
			String description = obj.isNull("Description") == true ? null : obj.getString("Description");
			String author = obj.isNull("Author") == true ? null : obj.getString("Author");
			String tag = obj.isNull("Tag") == true ? null : obj.getString("Tag");
			Long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");
			String content = obj.isNull("Content") == true ? null : obj.getString("Content");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			boolean isShow = obj.isNull("IsShow") == true ? false : obj.getBoolean("IsShow");
			String img = obj.isNull("Img") == true ? null : obj.getString("Img");
			Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");

			ArticleData entity = articleDataDAO.get(id);
			entity.setTitle(title);
			entity.setDescription(description);
			entity.setAuthor(author);
			entity.setTag(tag);
			entity.setSort(sort);
			entity.setContent(content);
			entity.setImg(img);
			entity.setStatus(status);
			entity.setViews(Long.valueOf(1));
			entity.setIsEnable(isEnable);
			entity.setIsShow(isShow);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			articleDataDAO.update(entity);

			return entity;
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean delete(Long id) {
		try {
			ArticleData entity = articleDataDAO.get(id);
			articleDataDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	public ArticleData get(Long id) {
		return articleDataDAO.get(id);
	}

}