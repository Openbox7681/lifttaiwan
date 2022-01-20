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
import tw.gov.mohw.hisac.web.dao.PrivacyPageDAO;
import tw.gov.mohw.hisac.web.domain.PrivacyPage;
import tw.gov.mohw.hisac.web.domain.ResourceMessage;

/**
 * 隱私權政策內容修改
 */
@Service
public class PrivacyPageService {
	final static Logger logger = LoggerFactory.getLogger(PrivacyPageService.class);

	@Autowired
	PrivacyPageDAO privacyPageDAO;

	
	/**
	 * 隱私權政策資料是否存在
	 * 
	 * @param name
	 *            name
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return getDataById(id) != null;
	}
	
	/**
	 * 取得隱私權政策資料
	 * 
	 * @param id
	 *            id
	 * @return 隱私權政策資料
	 */
	public PrivacyPage getDataById(Long id) {
		return privacyPageDAO.get(id);
	}

	
	/**
	 * 新增隱私權政策資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            隱私權政策資料
	 * @return 隱私權政策資料
	 */
	public PrivacyPage insert(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String title = obj.isNull("Title") == true ? "" : obj.getString("Title");
			
			
			String item1 = obj.isNull("Item1") == true ? "" : obj.getString("Item1");
			String item2_1 = obj.isNull("Item2_1") == true ? "" : obj.getString("Item2_1");
			String item2_2 = obj.isNull("Item2_2") == true ? "" : obj.getString("Item2_2");
			String item2_3 = obj.isNull("Item2_3") == true ? "" : obj.getString("Item2_3");
			String item2_4 = obj.isNull("Item2_4") == true ? "" : obj.getString("Item2_4");
			String item2_5 = obj.isNull("Item2_5") == true ? "" : obj.getString("Item2_5");

			String item3_1 = obj.isNull("Item3_1") == true ? "" : obj.getString("Item3_1");
			String item3_2 = obj.isNull("Item3_2") == true ? "" : obj.getString("Item3_2");

			String item4_1 = obj.isNull("Item4_1") == true ? "" : obj.getString("Item4_1");
			String item4_2 = obj.isNull("Item4_2") == true ? "" : obj.getString("Item4_2");
			String item4_3 = obj.isNull("Item4_3") == true ? "" : obj.getString("Item4_3");
			String item4_4 = obj.isNull("Item4_4") == true ? "" : obj.getString("Item4_4");
			
			String item5_1 = obj.isNull("Item5_1") == true ? "" : obj.getString("Item5_1");

			
			PrivacyPage entity = new PrivacyPage();
			entity.setTitle(title);
			entity.setItem1(item1);
			entity.setItem2_1(item2_1);
			entity.setItem2_2(item2_2);
			entity.setItem2_3(item2_3);
			entity.setItem2_4(item2_4);
			entity.setItem2_5(item2_5);
			entity.setItem3_1(item3_1);
			entity.setItem3_2(item3_2);

			entity.setItem4_1(item4_1);
			entity.setItem4_2(item4_2);
			entity.setItem4_3(item4_3);
			entity.setItem4_4(item4_4);

			entity.setItem5_1(item5_1);

			privacyPageDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 新增隱私權政策資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            隱私權政策資料
	 * @return 隱私權政策資料
	 */
	public PrivacyPage update(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			
			String title = obj.isNull("Title") == true ? "" : obj.getString("Title");
			
			
			String item1 = obj.isNull("Item1") == true ? "" : obj.getString("Item1");
			String item2_1 = obj.isNull("Item2_1") == true ? "" : obj.getString("Item2_1");
			String item2_2 = obj.isNull("Item2_2") == true ? "" : obj.getString("Item2_2");
			String item2_3 = obj.isNull("Item2_3") == true ? "" : obj.getString("Item2_3");
			String item2_4 = obj.isNull("Item2_4") == true ? "" : obj.getString("Item2_4");
			String item2_5 = obj.isNull("Item2_5") == true ? "" : obj.getString("Item2_5");

			String item3_1 = obj.isNull("Item3_1") == true ? "" : obj.getString("Item3_1");
			String item3_2 = obj.isNull("Item3_2") == true ? "" : obj.getString("Item3_2");

			String item4_1 = obj.isNull("Item4_1") == true ? "" : obj.getString("Item4_1");
			String item4_2 = obj.isNull("Item4_2") == true ? "" : obj.getString("Item4_2");
			String item4_3 = obj.isNull("Item4_3") == true ? "" : obj.getString("Item4_3");
			String item4_4 = obj.isNull("Item4_4") == true ? "" : obj.getString("Item4_4");
			
			String item5_1 = obj.isNull("Item5_1") == true ? "" : obj.getString("Item5_1");

			PrivacyPage entity = privacyPageDAO.get(id);
			
			
			entity.setTitle(title);
			entity.setItem1(item1);
			entity.setItem2_1(item2_1);
			entity.setItem2_2(item2_2);
			entity.setItem2_3(item2_3);
			entity.setItem2_4(item2_4);
			entity.setItem2_5(item2_5);
			entity.setItem3_1(item3_1);
			entity.setItem3_2(item3_2);

			entity.setItem4_1(item4_1);
			entity.setItem4_2(item4_2);
			entity.setItem4_3(item4_3);
			entity.setItem4_4(item4_4);

			entity.setItem5_1(item5_1);

			

			privacyPageDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
		
	}
	

			
	
		
		
		
	
}