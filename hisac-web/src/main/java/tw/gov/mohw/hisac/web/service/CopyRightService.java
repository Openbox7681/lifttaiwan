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
import tw.gov.mohw.hisac.web.dao.CopyRightDAO;
import tw.gov.mohw.hisac.web.domain.CopyRight;

/**
 * cookie同意政策內容修改
 */
@Service
public class CopyRightService {
	final static Logger logger = LoggerFactory.getLogger(CopyRightService.class);

	@Autowired
	CopyRightDAO copyRightDAO;

	
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
	public CopyRight getDataById(Long id) {
		return copyRightDAO.get(id);
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
	public CopyRight insert(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String title = obj.isNull("Title") == true ? "" : obj.getString("Title");
			String item1_1 = obj.isNull("Item1_1") == true ? "" : obj.getString("Item1_1");
			String item1_2 = obj.isNull("Item1_2") == true ? "" : obj.getString("Item1_2");
			String item1_3 = obj.isNull("Item1_3") == true ? "" : obj.getString("Item1_3");

			
			

			
			CopyRight entity = new CopyRight();
			entity.setTitle(title);
			entity.setItem1_1(item1_1);
			entity.setItem1_2(item1_2);
			entity.setItem1_3(item1_3);


			copyRightDAO.insert(entity);
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
	public CopyRight update(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			
			String title = obj.isNull("Title") == true ? "" : obj.getString("Title");
			String item1_1 = obj.isNull("Item1_1") == true ? "" : obj.getString("Item1_1");
			String item1_2 = obj.isNull("Item1_2") == true ? "" : obj.getString("Item1_2");
			String item1_3 = obj.isNull("Item1_3") == true ? "" : obj.getString("Item1_3");

			
			

			CopyRight entity = copyRightDAO.get(id);
			
			
			entity.setTitle(title);
			entity.setItem1_1(item1_1);
			entity.setItem1_2(item1_2);
			entity.setItem1_3(item1_3);



			copyRightDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
		
	}
	

			
	
		
		
		
	
}