
package tw.gov.mohw.hisac.web.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.MemberSignApplyDAO;
import tw.gov.mohw.hisac.web.domain.ViewMemberSignApply;

/**
 * 會員審查服務
 */
@Service
public class MemberSignApplyService {
	// final static Logger logger =
	// LoggerFactory.getLogger(MemberSignApplyService.class);

	@Autowired
	MemberSignApplyDAO memberSignApplyDAO;

	/**
	 * 取得Member資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return Member資料
	 */
	public List<ViewMemberSignApply> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return memberSignApplyDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 取得Member資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return Member資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return memberSignApplyDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

}