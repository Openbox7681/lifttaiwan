package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.OutboundPeoplePaperNoCorDAO;
import tw.gov.mohw.hisac.web.domain.ViewOutboundPeoplePaperNoCor;

@Service
public class OutboundPeoplePaperNoCorService {
	@Autowired
	OutboundPeoplePaperNoCorDAO outboundPeoplePaperNoCorDAO;

	public List<ViewOutboundPeoplePaperNoCor> getAll() {
		return outboundPeoplePaperNoCorDAO.getAll();
	}

	public List<ViewOutboundPeoplePaperNoCor> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return outboundPeoplePaperNoCorDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Object[]> getLineData() {
		try {
			return outboundPeoplePaperNoCorDAO.getLineData();
		} catch (Exception e) {
			return null;
		}
	}

	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return outboundPeoplePaperNoCorDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	public ViewOutboundPeoplePaperNoCor insert(Long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String code = obj.isNull("Code") == true ? null : obj.getString("Code");
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			String iconStyle = obj.isNull("IconStyle") == true ? null : obj.getString("IconStyle");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			boolean isShow = obj.isNull("IsShow") == true ? false : obj.getBoolean("IsShow");
			long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");
			Date now = new Date();
			ViewOutboundPeoplePaperNoCor entity = new ViewOutboundPeoplePaperNoCor();
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

			outboundPeoplePaperNoCorDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	public ViewOutboundPeoplePaperNoCor update(long memberId, String json) {
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
			ViewOutboundPeoplePaperNoCor entity = outboundPeoplePaperNoCorDAO.get(id);
//			entity.setCode(code);
//			entity.setName(name);
//			entity.setIconStyle(iconStyle);
//			entity.setIsEnable(isEnable);
//			entity.setIsShow(isShow);
//			entity.setSort(sort);
//			entity.setModifyId(memberId);
//			entity.setModifyTime(now);

			outboundPeoplePaperNoCorDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	public boolean delete(Long id) {
		try {
			ViewOutboundPeoplePaperNoCor entity = outboundPeoplePaperNoCorDAO.get(id);
			outboundPeoplePaperNoCorDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	public ViewOutboundPeoplePaperNoCor getById(Long id) {
		return outboundPeoplePaperNoCorDAO.get(id);
	}

	public boolean isExist(Long id) {
		return outboundPeoplePaperNoCorDAO.get(id) != null;
	}

	public ViewOutboundPeoplePaperNoCor findByName(String name) {
		return outboundPeoplePaperNoCorDAO.getByName(name);
	}

	public boolean isNameExist(String name) {
		return findByName(name) != null;
	}

	public ViewOutboundPeoplePaperNoCor findByCode(String code) {
		return outboundPeoplePaperNoCorDAO.getByCode(code);
	}

	public boolean isCodeExist(String code) {
		return findByCode(code) != null;
	}

}
