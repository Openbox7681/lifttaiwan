package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.gov.mohw.hisac.web.dao.InformationSourceDAO;
import tw.gov.mohw.hisac.web.domain.InformationSource;
/**
 * 情資資料來源服務
 */
@Service
public class InformationSourceService {
	@Autowired
	InformationSourceDAO informationSourceDAO;

	/**
	 * 取得所有情資資料來源資料
	 * 
	 * @return 情資資料來源資料
	 */
	public List<InformationSource> getAll() {
		return informationSourceDAO.getAll();
	}

	/**
	 * 取得情資資料來源資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 情資資料來源資料
	 */
	public List<InformationSource> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return informationSourceDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 取得情資資料來源資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 情資資料來源資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return informationSourceDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 新增情資資料來源資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            情資資料來源資料
	 * @return 情資資料來源資料
	 */
	public InformationSource insert(Long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String code = obj.isNull("Code") == true ? null : obj.getString("Code");
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");
			Date now = new Date();
			InformationSource entity = new InformationSource();
			entity.setCode(code);
			entity.setName(name);
			entity.setIsEnable(isEnable);
			entity.setSort(sort);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);

			informationSourceDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新情資資料來源資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            情資資料來源Id
	 * @return 情資資料來源資料
	 */
	public InformationSource update(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String code = obj.isNull("Code") == true ? null : obj.getString("Code");
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");
			Date now = new Date();

			InformationSource entity = informationSourceDAO.get(id);
			entity.setCode(code);
			entity.setName(name);
			entity.setIsEnable(isEnable);
			entity.setSort(sort);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			informationSourceDAO.update(entity);

			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	/**
	 * 刪除情資資料來源資料
	 * 
	 * @param id
	 *            情資資料來源Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			InformationSource entity = informationSourceDAO.get(id);
			informationSourceDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 取得情資資料來源資料
	 * 
	 * @param id
	 *            情資資料來源資料Id
	 * @return 情資資料來源資料
	 */
	public InformationSource getById(Long id) {
		return informationSourceDAO.get(id);
	}
	/**
	 * 情資資料來源資料是否存在
	 * 
	 * @param id
	 *            情資資料來源資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return informationSourceDAO.get(id) != null;
	}
	/**
	 * 情資資料來源資料名稱
	 * 
	 * @param name
	 *            情資資料來源名稱
	 * @return 情資資料來源名稱資料
	 */
	public InformationSource findByName(String name) {
		return informationSourceDAO.getByName(name);
	}
	/**
	 * 情資資料來源資料名稱是否存在
	 * 
	 * @param name
	 *            名稱
	 * @return 是否存在
	 */
	public boolean isNameExist(String name) {
		return findByName(name) != null;
	}
	/**
	 * 情資資料來源資料名稱
	 * 
	 * @param code
	 *            情資資料來源編號
	 * @return 情資資料來源名稱資料
	 */
	public InformationSource findByCode(String code) {
		return informationSourceDAO.getByCode(code);
	}
	/**
	 * 情資資料來源資料編號是否存在
	 * 
	 * @param code
	 *            編號
	 * @return 是否存在
	 */
	public boolean isCodeExist(String code) {
		return findByCode(code) != null;
	}

}
