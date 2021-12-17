package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.InboundPeoplePaperNoCorDAO;
import tw.gov.mohw.hisac.web.domain.ViewInboundPeoplePaperNoCor;

@Service
public class InboundPeoplePaperNoCorService {
	@Autowired
	InboundPeoplePaperNoCorDAO inboundPeoplePaperNoCorDAO;

	public List<ViewInboundPeoplePaperNoCor> getAll() {
		return inboundPeoplePaperNoCorDAO.getAll();
	}

	public List<ViewInboundPeoplePaperNoCor> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return inboundPeoplePaperNoCorDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Object[]> getLineData() {
		try {
			return inboundPeoplePaperNoCorDAO.getLineData();
		} catch (Exception e) {
			return null;
		}
	}

	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return inboundPeoplePaperNoCorDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	public ViewInboundPeoplePaperNoCor insert(Long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String code = obj.isNull("Code") == true ? null : obj.getString("Code");
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			String iconStyle = obj.isNull("IconStyle") == true ? null : obj.getString("IconStyle");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			boolean isShow = obj.isNull("IsShow") == true ? false : obj.getBoolean("IsShow");
			long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");
			Date now = new Date();
			ViewInboundPeoplePaperNoCor entity = new ViewInboundPeoplePaperNoCor();
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

			inboundPeoplePaperNoCorDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	public ViewInboundPeoplePaperNoCor update(long memberId, String json) {
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
			ViewInboundPeoplePaperNoCor entity = inboundPeoplePaperNoCorDAO.get(id);
//			entity.setCode(code);
//			entity.setName(name);
//			entity.setIconStyle(iconStyle);
//			entity.setIsEnable(isEnable);
//			entity.setIsShow(isShow);
//			entity.setSort(sort);
//			entity.setModifyId(memberId);
//			entity.setModifyTime(now);

			inboundPeoplePaperNoCorDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	public boolean delete(Long id) {
		try {
			ViewInboundPeoplePaperNoCor entity = inboundPeoplePaperNoCorDAO.get(id);
			inboundPeoplePaperNoCorDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	public ViewInboundPeoplePaperNoCor getById(Long id) {
		return inboundPeoplePaperNoCorDAO.get(id);
	}

	public boolean isExist(Long id) {
		return inboundPeoplePaperNoCorDAO.get(id) != null;
	}

	public ViewInboundPeoplePaperNoCor findByName(String name) {
		return inboundPeoplePaperNoCorDAO.getByName(name);
	}

	public boolean isNameExist(String name) {
		return findByName(name) != null;
	}

	public ViewInboundPeoplePaperNoCor findByCode(String code) {
		return inboundPeoplePaperNoCorDAO.getByCode(code);
	}

	public boolean isCodeExist(String code) {
		return findByCode(code) != null;
	}

}
