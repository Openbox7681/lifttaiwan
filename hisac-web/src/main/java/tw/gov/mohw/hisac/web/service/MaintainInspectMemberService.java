package tw.gov.mohw.hisac.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.dao.MaintainInspectCommitteeDAO;
import tw.gov.mohw.hisac.web.dao.MaintainInspectMemberDAO;
import tw.gov.mohw.hisac.web.domain.MaintainInspectCommittee;
import tw.gov.mohw.hisac.web.domain.MaintainInspectMember;
import tw.gov.mohw.hisac.web.domain.MaintainPlanMember;
import tw.gov.mohw.hisac.web.domain.ViewMaintainInspectMemberOrg;

/**
 * 資安維護計畫對象服務
 */
@Service
public class MaintainInspectMemberService {
	@Autowired
	MaintainInspectMemberDAO maintainInspectMemberDAO;
	
	@Autowired
	MaintainInspectCommitteeDAO maintainInspectCommitteeDAO;
	/**
	 * 新增資安維護計畫對象資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            資安維護計畫對象資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫對象資料
	 */
	public MaintainInspectMember insert(String json) {
		try {			
			JSONObject obj = new JSONObject(json);
			long maintainInspectId = obj.isNull("MaintainInspectId") == true ? 0 : obj.getLong("MaintainInspectId");
			long groupid = obj.isNull("Groupid") == true ? 0 : obj.getLong("Groupid");			
			String insertStatus = obj.isNull("InsertStatus") == true ? null : obj.getString("InsertStatus");			
			
			MaintainInspectMember entity = new MaintainInspectMember();			
			entity.setMaintainInspectId(maintainInspectId);
			entity.setGroupId(groupid);
			entity.setInspectStatus(insertStatus);
			maintainInspectMemberDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 退回稽核資料
	 * 	 
	 * @param json
	 *            資安維護計畫對象資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫對象資料
	 */
	public MaintainInspectMember returnToEditData(long maintainInspectId, long groupid, long memberId) {
		try {
			MaintainInspectMember entity =
					maintainInspectMemberDAO.getListByMaintainInspectIdAndGroupId(maintainInspectId, groupid);				
			entity.setInspectStatus("3");						
			entity.setInspectMemberId(memberId);
			maintainInspectMemberDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 退回稽核資料
	 * 	 
	 * @param json
	 *            資安維護計畫對象資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫對象資料
	 */
	public MaintainInspectMember returnToHospital(long maintainInspectId, long groupid, long memberId) {
		try {
			MaintainInspectMember entity =
					maintainInspectMemberDAO.getListByMaintainInspectIdAndGroupId(maintainInspectId, groupid);				
			entity.setInspectStatus("4");						
			entity.setInspectMemberId(memberId);
			maintainInspectMemberDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 退回稽核結果
	 * 	 
	 * @param json
	 *            資安維護計畫對象資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫對象資料
	 */
	public MaintainInspectMember returnToCommitteeList(long maintainInspectId, long groupid, long memberId) {
		try {
			MaintainInspectMember entity =
					maintainInspectMemberDAO.getListByMaintainInspectIdAndGroupId(maintainInspectId, groupid);				
			entity.setInspectStatus("5");						
			entity.setInspectMemberId(memberId);
			maintainInspectMemberDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 退回稽核資料
	 * 	 
	 * @param json
	 *            資安維護計畫對象資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫對象資料
	 */
	public MaintainInspectMember returnToAuditScoreUpload(long maintainInspectId, long groupId, long memberId) {
		try {
			MaintainInspectMember entity =
					maintainInspectMemberDAO.getListByMaintainInspectIdAndGroupId(maintainInspectId, groupId);				
			entity.setInspectStatus("6");						
			entity.setInspectMemberId(memberId);
			maintainInspectMemberDAO.update(entity);
			
			List<MaintainInspectCommittee> maintainInspectCommittees =
					maintainInspectCommitteeDAO.getListByMaintainInspectIdAndGroupId(maintainInspectId, groupId);
			if(maintainInspectCommittees!=null && !maintainInspectCommittees.isEmpty()) {
				Date now = new Date();
				for(MaintainInspectCommittee maintainInspectCommittee : maintainInspectCommittees) {
					maintainInspectCommittee.setStatus(false);
					maintainInspectCommittee.setModifyId(memberId);
					maintainInspectCommittee.setModifyTime(now);
					
					maintainInspectCommitteeDAO.update(maintainInspectCommittee);
				}
			}
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 退回稽核結果
	 * 	 
	 * @param json
	 *            資安維護計畫對象資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫對象資料
	 */
	public MaintainInspectMember returnToAuditResultUpload(long maintainInspectId, long groupid, long memberId) {
		try {
			MaintainInspectMember entity =
					maintainInspectMemberDAO.getListByMaintainInspectIdAndGroupId(maintainInspectId, groupid);				
			entity.setInspectStatus("7");						
			entity.setInspectMemberId(memberId);
			maintainInspectMemberDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 退回改善報告
	 * 	 
	 * @param json
	 *            資安維護計畫對象資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫對象資料
	 */
	public MaintainInspectMember returnToImprovementUpload(long maintainInspectId, long groupid, long memberId) {
		try {
			MaintainInspectMember entity =
					maintainInspectMemberDAO.getListByMaintainInspectIdAndGroupId(maintainInspectId, groupid);				
			entity.setInspectStatus("8");						
			entity.setInspectMemberId(memberId);
			maintainInspectMemberDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	public MaintainInspectMember submitMaintainInspectResult(long maintainPlanId, long groupid, String inspectStatus, long memberId) {
		try {
			MaintainInspectMember entity =
					maintainInspectMemberDAO.getListByMaintainInspectIdAndGroupId(maintainPlanId, groupid);
			entity.setInspectStatus(inspectStatus);
			entity.setInspectMemberId(memberId);
			maintainInspectMemberDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	public MaintainInspectMember switchAllowHospitalPatchMaintainInspectMember(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			boolean allowHospitalPatch = obj.isNull("AllowHospitalPatch") == true ? false : obj.getBoolean("AllowHospitalPatch");

			MaintainInspectMember entity = maintainInspectMemberDAO.get(id);
			entity.setAllowHospitalPatch(allowHospitalPatch);
			maintainInspectMemberDAO.update(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean isExist(Long id) {
		return maintainInspectMemberDAO.get(id) != null;
	}
	
	public MaintainInspectMember updateMaintainInspectMember(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String inspectStatus = obj.isNull("InspectStatus") == true ? null : obj.getString("InspectStatus");
			boolean allowHospitalPatch = obj.isNull("AllowHospitalPatch") == true ? false : obj.getBoolean("AllowHospitalPatch");
			
			String strHospitalUploadSdate = obj.isNull("HospitalUploadSdate") == true ? null : obj.getString("HospitalUploadSdate");
			Date hospitalUploadSdate = null;
			if(strHospitalUploadSdate!=null && strHospitalUploadSdate.length()!=0) {
				hospitalUploadSdate = WebDatetime.parse(strHospitalUploadSdate, "yyyy-MM-dd");
			}

			String strHospitalUploadEdate = obj.isNull("HospitalUploadEdate") == true ? null : obj.getString("HospitalUploadEdate");
			Date hospitalUploadEdate = null;
			if(strHospitalUploadEdate!=null && strHospitalUploadEdate.length()!=0) {
				hospitalUploadEdate = WebDatetime.parse(strHospitalUploadEdate, "yyyy-MM-dd");
			}

			String strCommitteeUploadSdate = obj.isNull("CommitteeUploadSdate") == true ? null : obj.getString("CommitteeUploadSdate");
			Date committeeUploadSdate = null;
			if(strCommitteeUploadSdate!=null && strCommitteeUploadSdate.length()!=0) {
				committeeUploadSdate = WebDatetime.parse(strCommitteeUploadSdate, "yyyy-MM-dd");
			}

			String strCommitteeUploadEdate = obj.isNull("CommitteeUploadEdate") == true ? null : obj.getString("CommitteeUploadEdate");
			Date committeeUploadEdate = null;
			if(strCommitteeUploadEdate!=null && strCommitteeUploadEdate.length()!=0) {
				committeeUploadEdate = WebDatetime.parse(strCommitteeUploadEdate, "yyyy-MM-dd");
			}
			MaintainInspectMember entity = maintainInspectMemberDAO.get(id);
			entity.setInspectStatus(inspectStatus);
			entity.setHospitalUploadSdate(hospitalUploadSdate);
			entity.setHospitalUploadEdate(hospitalUploadEdate);
			entity.setCommitteeUploadSdate(committeeUploadSdate);
			entity.setCommitteeUploadEdate(committeeUploadEdate);
			entity.setAllowHospitalPatch(allowHospitalPatch);
			entity.setInspectMemberId(memberId);

			maintainInspectMemberDAO.update(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ViewMaintainInspectMemberOrg findMaintainInspectById(long id){
		try {
			return maintainInspectMemberDAO.findMaintainInspectById(id);
		} catch (Exception e) {
			return null;
		}
	}

	public List<ViewMaintainInspectMemberOrg> getMaintainInspectListByMaintainInspectId(long maintainInspectId){
		try {
			return maintainInspectMemberDAO.getMaintainInspectListByMaintainInspectId(maintainInspectId);
		} catch (Exception e) {
			return null;
		}
	}

	public List<JSONObject> getMaintainInspectList(String json){
		try {					
			JSONObject obj = new JSONObject(json);
			List<Object[]> maintainPlanMembers = maintainInspectMemberDAO.getMaintainInspectList(obj);
			List<JSONObject> result = new ArrayList<>();
			for(Object[] array : maintainPlanMembers) {
				JSONObject item = new JSONObject();
				item.put("MaintainInspectId", (Long) array[0]);
				item.put("Title", (String) array[1]);
				item.put("Year", (String) array[2]);
				item.put("Status", (String) array[3]);
				item.put("Count", (Long) array[4]);
				result.add(item);
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}
	public long getMaintainInspectListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return maintainInspectMemberDAO.getMaintainInspectListSize(obj);
		} catch (Exception e) {
			return 0;		
		}
	}
	
	/**
	 * 繳交調查表及稽核表資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            資安維護計畫對象資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫對象資料
	 */
	public MaintainInspectMember questionnaire_submit(long maintainInspectId, long groupid) {
		try {							
						
			MaintainInspectMember entity = maintainInspectMemberDAO.getListByMaintainInspectIdAndGroupId(maintainInspectId, groupid);				
			entity.setInspectStatus("5");

			maintainInspectMemberDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 繳交評分表
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            資安維護計畫對象資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫對象資料
	 */
	public MaintainInspectMember score_submit(long maintainPlanId, long groupid) {
		try {							
						
			MaintainInspectMember entity = maintainInspectMemberDAO.getListByMaintainInspectIdAndGroupId(maintainPlanId, groupid);				
			entity.setInspectStatus("7");						

			maintainInspectMemberDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 繳交改善報告
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            資安維護計畫對象資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫對象資料
	 */
	public MaintainInspectMember improvement_submit(long maintainPlanId, long groupid) {
		try {							
						
			MaintainInspectMember entity = maintainInspectMemberDAO.getListByMaintainInspectIdAndGroupId(maintainPlanId, groupid);				
			entity.setInspectStatus("9");
			maintainInspectMemberDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
}
