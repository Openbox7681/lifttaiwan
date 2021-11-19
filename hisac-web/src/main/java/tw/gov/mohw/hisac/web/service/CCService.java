
package tw.gov.mohw.hisac.web.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.CCDAO;
import tw.gov.mohw.hisac.web.domain.CC;

/**
 * 表單資料維護服務
 */
@Service
public class CCService {
	final static Logger logger = LoggerFactory.getLogger(CCService.class);

	@Autowired
	CCDAO ccDAO;

	/**
	 * 新增CC資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            CC資料
	 * @return CC資料
	 */
	public boolean insert(Long memberId, String json) {
		try {			
			JSONObject obj = new JSONObject(json);
			JSONArray arr = obj.getJSONArray("Data");
			for (int i=1; i<arr.length();i++) {					
				String dnameOrIpname = arr.getJSONArray(i).getString(1);
				String strfirstDate = arr.getJSONArray(i).getString(2);
				String strlastDate = arr.getJSONArray(i).getString(3);
				Date firstDate = new Date();
				if (strfirstDate != null)
					firstDate = new SimpleDateFormat("yyyy-MM-dd").parse(strfirstDate);
				Date lastDate = new Date();
				if (strlastDate != null)
					lastDate = new SimpleDateFormat("yyyy-MM-dd").parse(strlastDate);
				Date now = new Date();
				CC entity = new CC();
				entity.setDnameOrIpname(dnameOrIpname);
				entity.setFirstDate(firstDate);
				entity.setLastDate(lastDate);			
				entity.setCreateId(memberId);
				entity.setCreateTime(now);			
				ccDAO.insert(entity);
			}
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 刪除全部CC資料
	 * 
	 * @return 是否刪除成功
	 */
	public boolean delete() {
		try {			 
			ccDAO.delete();
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * DnameOrIpname是否在CC資料中
	 * 
	 * @param id
	 *            DnameOrIpname
	 * @return 是否存在
	 */
	public boolean getByDnameOrIpname(String dnameOrIpname) {
		return ccDAO.getByDnameOrIpname(dnameOrIpname);
	}

}
