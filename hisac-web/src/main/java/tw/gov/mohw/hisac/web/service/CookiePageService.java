package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.WebConfig;
import tw.gov.mohw.hisac.web.WebSms;
import tw.gov.mohw.hisac.web.dao.CookiePageDAO;
import tw.gov.mohw.hisac.web.domain.CookiePage;

/**
 * cookie同意政策內容修改
 */
@Service
public class CookiePageService {
	final static Logger logger = LoggerFactory.getLogger(CookiePageService.class);

	@Autowired
	CookiePageDAO cookiePageDAO;

	
	/**
	 * cookie同意政策內容是否存在
	 * 
	 * @param name
	 *            name
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return getDataById(id) != null;
	}
	
	/**
	 * 取得cookie同意政策內容
	 * 
	 * @param id
	 *            id
	 * @return cookie同意政策內容
	 */
	public CookiePage getDataById(Long id) {
		return cookiePageDAO.get(id);
	}

	
	/**
	 * 新增cookie同意政策內容
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            隱私權政策資料
	 * @return 隱私權政策資料
	 */
	public CookiePage insert(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String title = obj.isNull("Title") == true ? "" : obj.getString("Title");
			
			
			

			
			CookiePage entity = new CookiePage();
			entity.setTitle(title);
			

			cookiePageDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 新增cookie同意政策內容
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            cookie同意政策內容
	 * @return cookie同意政策內容
	 */
	public CookiePage update(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			
			String title = obj.isNull("Title") == true ? "" : obj.getString("Title");
			

			CookiePage entity = cookiePageDAO.get(id);
			
			
			entity.setTitle(title);
		

			cookiePageDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
		
	}
	

			
	
		
		
		
	
}