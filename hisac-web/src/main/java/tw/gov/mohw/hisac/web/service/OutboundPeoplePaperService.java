package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.OutboundPeoplePaperDAO;
import tw.gov.mohw.hisac.web.domain.ViewOutboundPeoplePaper;

@Service
public class OutboundPeoplePaperService {
	@Autowired
	OutboundPeoplePaperDAO outboundPeoplePaperDAO;

	public List<ViewOutboundPeoplePaper> getAll() {
		return outboundPeoplePaperDAO.getAll();
	}

	public List<ViewOutboundPeoplePaper> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return outboundPeoplePaperDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Object[]> getLineData() {
		try {
			return outboundPeoplePaperDAO.getLineData();
		} catch (Exception e) {
			return null;
		}
	}

	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return outboundPeoplePaperDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	public ViewOutboundPeoplePaper insert(Long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String code = obj.isNull("Code") == true ? null : obj.getString("Code");
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			String iconStyle = obj.isNull("IconStyle") == true ? null : obj.getString("IconStyle");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			boolean isShow = obj.isNull("IsShow") == true ? false : obj.getBoolean("IsShow");
			long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");
			Date now = new Date();
			ViewOutboundPeoplePaper entity = new ViewOutboundPeoplePaper();
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

			outboundPeoplePaperDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	public ViewOutboundPeoplePaper update(long memberId, String json) {
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
			ViewOutboundPeoplePaper entity = outboundPeoplePaperDAO.get(id);
//			entity.setCode(code);
//			entity.setName(name);
//			entity.setIconStyle(iconStyle);
//			entity.setIsEnable(isEnable);
//			entity.setIsShow(isShow);
//			entity.setSort(sort);
//			entity.setModifyId(memberId);
//			entity.setModifyTime(now);

			outboundPeoplePaperDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	public boolean delete(Long id) {
		try {
			ViewOutboundPeoplePaper entity = outboundPeoplePaperDAO.get(id);
			outboundPeoplePaperDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	public ViewOutboundPeoplePaper getById(Long id) {
		return outboundPeoplePaperDAO.get(id);
	}

	public boolean isExist(Long id) {
		return outboundPeoplePaperDAO.get(id) != null;
	}

	public ViewOutboundPeoplePaper findByName(String name) {
		return outboundPeoplePaperDAO.getByName(name);
	}

	public boolean isNameExist(String name) {
		return findByName(name) != null;
	}

	public ViewOutboundPeoplePaper findByCode(String code) {
		return outboundPeoplePaperDAO.getByCode(code);
	}

	public boolean isCodeExist(String code) {
		return findByCode(code) != null;
	}

}
