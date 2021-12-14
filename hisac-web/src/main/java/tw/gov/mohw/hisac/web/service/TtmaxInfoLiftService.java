package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.gov.mohw.hisac.web.dao.TtmaxInfoLiftDAO;
import tw.gov.mohw.hisac.web.domain.TtmaxInfoLift;

@Service
public class TtmaxInfoLiftService {
	@Autowired
	TtmaxInfoLiftDAO ttmaxInfoLiftDAO;

	public List<TtmaxInfoLift> getAll() {
		return ttmaxInfoLiftDAO.getAll();
	}

	public List<TtmaxInfoLift> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return ttmaxInfoLiftDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}

	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return ttmaxInfoLiftDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	public TtmaxInfoLift insert(Long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String code = obj.isNull("Code") == true ? null : obj.getString("Code");
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			String iconStyle = obj.isNull("IconStyle") == true ? null : obj.getString("IconStyle");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			boolean isShow = obj.isNull("IsShow") == true ? false : obj.getBoolean("IsShow");
			long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");
			Date now = new Date();
			TtmaxInfoLift entity = new TtmaxInfoLift();
//			entity.setCode(code);
//			entity.setName(name);
//			entity.setIconStyle(iconStyle);
//			entity.setIsEnable(isEnable);
//			entity.setIsShow(isShow);
//			entity.setSort(sort);
//			entity.setCreateId(memberId);
//			entity.setCreateTime(now);
//			entity.setModifyId(memberId);
//			entity.setModifyTime(now);

			ttmaxInfoLiftDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	public TtmaxInfoLift update(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String code = obj.isNull("Code") == true ? null : obj.getString("Code");
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			String iconStyle = obj.isNull("IconStyle") == true ? null : obj.getString("IconStyle");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			boolean isShow = obj.isNull("IsShow") == true ? false : obj.getBoolean("IsShow");
			long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");
			Date now = new Date();
			TtmaxInfoLift entity = ttmaxInfoLiftDAO.get(id);
//			entity.setCode(code);
//			entity.setName(name);
//			entity.setIconStyle(iconStyle);
//			entity.setIsEnable(isEnable);
//			entity.setIsShow(isShow);
//			entity.setSort(sort);
//			entity.setModifyId(memberId);
//			entity.setModifyTime(now);

			ttmaxInfoLiftDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	public boolean delete(Long id) {
		try {
			TtmaxInfoLift entity = ttmaxInfoLiftDAO.get(id);
			ttmaxInfoLiftDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	public TtmaxInfoLift getById(Long id) {
		return ttmaxInfoLiftDAO.get(id);
	}

	public boolean isExist(Long id) {
		return ttmaxInfoLiftDAO.get(id) != null;
	}

	public TtmaxInfoLift findByName(String name) {
		return ttmaxInfoLiftDAO.getByName(name);
	}

	public boolean isNameExist(String name) {
		return findByName(name) != null;
	}

	public TtmaxInfoLift findByCode(String code) {
		return ttmaxInfoLiftDAO.getByCode(code);
	}

	public boolean isCodeExist(String code) {
		return findByCode(code) != null;
	}

}
