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
import tw.gov.mohw.hisac.web.dao.MaintainInspectHospitalUploadDAO;
import tw.gov.mohw.hisac.web.domain.MaintainPlanMember;
import tw.gov.mohw.hisac.web.domain.ViewMaintainInspectMemberOrg;
import tw.gov.mohw.hisac.web.domain.ViewMaintainPlanMemberOrg;

/**
 * 資安維護計畫對象服務
 */
@Service
public class MaintainInspectHospitalUploadService {
	@Autowired
	MaintainInspectHospitalUploadDAO maintainInspectHospitalUploadDAO;

	public List<ViewMaintainInspectMemberOrg> getList(Long orgId, String json){
		try {					
			JSONObject obj = new JSONObject(json);
			obj.put("GoupId", orgId);
			return maintainInspectHospitalUploadDAO.getMaintainInspectList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	public long getListSize(Long orgId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			obj.put("GoupId", orgId);
			return maintainInspectHospitalUploadDAO.getMaintainInspectListSize(obj);
		} catch (Exception e) {
			return 0;		
		}
	}
	public ViewMaintainInspectMemberOrg getIdByMaintainInspectIdAndGroupId(long maintainInspectId, long groupid){
		try {
			return maintainInspectHospitalUploadDAO.getListByMaintainInspectIdAndGroupId(maintainInspectId,groupid);
		} catch (Exception e) {
			return null;
		}
	}
}
