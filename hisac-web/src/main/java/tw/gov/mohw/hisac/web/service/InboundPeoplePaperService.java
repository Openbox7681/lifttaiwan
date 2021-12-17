package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.InboundPeoplePaperDAO;
import tw.gov.mohw.hisac.web.domain.ViewInboundPeoplePaper;

@Service
public class InboundPeoplePaperService {
	@Autowired
	InboundPeoplePaperDAO inboundPeoplePaperDAO;

	public List<ViewInboundPeoplePaper> getAll() {
		return inboundPeoplePaperDAO.getAll();
	}

	public List<ViewInboundPeoplePaper> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return inboundPeoplePaperDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Object[]> getLineData() {
		try {
			return inboundPeoplePaperDAO.getLineData();
		} catch (Exception e) {
			return null;
		}
	}

	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return inboundPeoplePaperDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	public ViewInboundPeoplePaper insert(Long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String code = obj.isNull("Code") == true ? null : obj.getString("Code");
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			String iconStyle = obj.isNull("IconStyle") == true ? null : obj.getString("IconStyle");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			boolean isShow = obj.isNull("IsShow") == true ? false : obj.getBoolean("IsShow");
			long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");
			Date now = new Date();
			ViewInboundPeoplePaper entity = new ViewInboundPeoplePaper();
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

			inboundPeoplePaperDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	public ViewInboundPeoplePaper update(long memberId, String json) {
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
			ViewInboundPeoplePaper entity = inboundPeoplePaperDAO.get(id);
//			entity.setCode(code);
//			entity.setName(name);
//			entity.setIconStyle(iconStyle);
//			entity.setIsEnable(isEnable);
//			entity.setIsShow(isShow);
//			entity.setSort(sort);
//			entity.setModifyId(memberId);
//			entity.setModifyTime(now);

			inboundPeoplePaperDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	public boolean delete(Long id) {
		try {
			ViewInboundPeoplePaper entity = inboundPeoplePaperDAO.get(id);
			inboundPeoplePaperDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	public ViewInboundPeoplePaper getById(Long id) {
		return inboundPeoplePaperDAO.get(id);
	}

	public boolean isExist(Long id) {
		return inboundPeoplePaperDAO.get(id) != null;
	}

	public ViewInboundPeoplePaper findByName(String name) {
		return inboundPeoplePaperDAO.getByName(name);
	}

	public boolean isNameExist(String name) {
		return findByName(name) != null;
	}

	public ViewInboundPeoplePaper findByCode(String code) {
		return inboundPeoplePaperDAO.getByCode(code);
	}

	public boolean isCodeExist(String code) {
		return findByCode(code) != null;
	}

}
