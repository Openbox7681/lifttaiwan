package tw.gov.mohw.hisac.web.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.dao.MaintainInspectCommitteeDAO;
import tw.gov.mohw.hisac.web.domain.MaintainPlanMember;
import tw.gov.mohw.hisac.web.domain.ViewMaintainInspectCommitteeMemberOrg;
import tw.gov.mohw.hisac.web.domain.ViewMaintainInspectMemberOrg;
import tw.gov.mohw.hisac.web.domain.ViewMaintainPlanMemberOrg;

/**
 * 資安維護計畫對象服務
 */
@Service
public class MaintainInspectCommitteeUploadService {
	@Autowired
	MaintainInspectCommitteeDAO maintainInspectCommitteeDAO;
	
	public List<ViewMaintainInspectCommitteeMemberOrg> getAdminList(String json){
		try {					
			JSONObject obj = new JSONObject(json);
			return maintainInspectCommitteeDAO.getMaintainInspectCommitteeList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	public long getAdminListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return maintainInspectCommitteeDAO.getMaintainInspectCommitteeListSize(obj);
		} catch (Exception e) {
			return 0;		
		}
	}
	
	public List<ViewMaintainInspectCommitteeMemberOrg> getList(Long committeeId, String json){
		try {					
			JSONObject obj = new JSONObject(json);
			obj.put("CommitteeId", committeeId);
			return maintainInspectCommitteeDAO.getMaintainInspectCommitteeList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	public long getListSize(Long committeeId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			obj.put("CommitteeId", committeeId);
			return maintainInspectCommitteeDAO.getMaintainInspectCommitteeListSize(obj);
		} catch (Exception e) {
			return 0;		
		}
	}
	public ViewMaintainInspectCommitteeMemberOrg getById(long id){
		return maintainInspectCommitteeDAO.getView(id);
	}
}
