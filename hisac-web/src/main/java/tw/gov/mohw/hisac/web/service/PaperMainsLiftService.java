package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.PaperMainsLiftDAO;
import tw.gov.mohw.hisac.web.domain.PaperMainsLift;

@Service
public class PaperMainsLiftService {
	@Autowired
	PaperMainsLiftDAO paperMainsDAO;

	public List<PaperMainsLift> getAll() {
		return paperMainsDAO.getAll();
	}

	public List<PaperMainsLift> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return paperMainsDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}

	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return paperMainsDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	public PaperMainsLift insert(Long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String code = obj.isNull("Code") == true ? null : obj.getString("Code");
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			String iconStyle = obj.isNull("IconStyle") == true ? null : obj.getString("IconStyle");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			boolean isShow = obj.isNull("IsShow") == true ? false : obj.getBoolean("IsShow");
			long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");
			Date now = new Date();
			PaperMainsLift entity = new PaperMainsLift();
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

			paperMainsDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	public PaperMainsLift update(long memberId, String json) {
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
			PaperMainsLift entity = paperMainsDAO.get(id);
//			entity.setCode(code);
//			entity.setName(name);
//			entity.setIconStyle(iconStyle);
//			entity.setIsEnable(isEnable);
//			entity.setIsShow(isShow);
//			entity.setSort(sort);
//			entity.setModifyId(memberId);
//			entity.setModifyTime(now);

			paperMainsDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	public boolean delete(Long id) {
		try {
			PaperMainsLift entity = paperMainsDAO.get(id);
			paperMainsDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	public PaperMainsLift getById(Long id) {
		return paperMainsDAO.get(id);
	}

	public boolean isExist(Long id) {
		return paperMainsDAO.get(id) != null;
	}

	public PaperMainsLift findByName(String name) {
		return paperMainsDAO.getByName(name);
	}

	public boolean isNameExist(String name) {
		return findByName(name) != null;
	}

	public PaperMainsLift findByCode(String code) {
		return paperMainsDAO.getByCode(code);
	}

	public boolean isCodeExist(String code) {
		return findByCode(code) != null;
	}

}
