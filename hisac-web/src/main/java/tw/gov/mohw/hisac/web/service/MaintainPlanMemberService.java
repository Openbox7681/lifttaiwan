package tw.gov.mohw.hisac.web.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.MaintainInspectCommitteeDAO;
import tw.gov.mohw.hisac.web.dao.MaintainPlanMemberDAO;
import tw.gov.mohw.hisac.web.domain.MaintainPlanMember;
import tw.gov.mohw.hisac.web.domain.ViewMaintainInspectMemberOrg;
import tw.gov.mohw.hisac.web.domain.ViewMaintainPlanMemberOrg;

/**
 * 資安維護計畫對象服務
 */
@Service
public class MaintainPlanMemberService {
	@Autowired
	MaintainPlanMemberDAO maintainPlanMemberDAO;
	
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
	public MaintainPlanMember insert(String json) {
		try {			
			JSONObject obj = new JSONObject(json);
			long maintainPlanId = obj.isNull("MaintainPlanId") == true ? 0 : obj.getLong("MaintainPlanId");
			long groupid = obj.isNull("Groupid") == true ? 0 : obj.getLong("Groupid");			
			String str_sdate = obj.isNull("Sdate") == true ? null : obj.getString("Sdate");
			Date sdate = new Date();
			if (str_sdate != null)
				sdate = new SimpleDateFormat("yyyy-MM-dd").parse(str_sdate);
			String str_edate = obj.isNull("Edate") == true ? null : obj.getString("Edate");
			Date edate = new Date();
			if (str_edate != null)
				edate = new SimpleDateFormat("yyyy-MM-dd").parse(str_edate);
			String status = obj.isNull("Status") == true ? null : obj.getString("Status");			
			
			MaintainPlanMember entity = new MaintainPlanMember();			
			entity.setMaintainPlanId(maintainPlanId);
			entity.setGroupId(groupid);
			entity.setSdate(sdate);
			entity.setEdate(edate);
			entity.setStatus(status);			

			maintainPlanMemberDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
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
	public MaintainPlanMember replySave(long maintainPlanId, long groupid, long memberId) {
		try {							
						
			MaintainPlanMember entity = maintainPlanMemberDAO.getListByMaintainPlanIdAndGroupId(maintainPlanId, groupid);				
			entity.setMemberId(memberId);			

			maintainPlanMemberDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
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
	public MaintainPlanMember reply(long maintainPlanId, long groupid, long memberId) {
		try {							
			
			Date now = new Date();
			MaintainPlanMember entity = maintainPlanMemberDAO.getListByMaintainPlanIdAndGroupId(maintainPlanId, groupid);				
			entity.setStatus("3");
			entity.setReplyTime(now);
			entity.setMemberId(memberId);	

			maintainPlanMemberDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	public List<ViewMaintainPlanMemberOrg> getListByMaintainPlanId(long maintainPlanId){
		try {
			return maintainPlanMemberDAO.getListByMaintainPlanId(maintainPlanId);
		} catch (Exception e) {
			return null;
		}
	}
	
	public MaintainPlanMember getListByMaintainPlanIdAndGroupId(long maintainPlanId, long groupId){
		try {
			return maintainPlanMemberDAO.getListByMaintainPlanIdAndGroupId(maintainPlanId, groupId);
		} catch (Exception e) {
			return null;
		}
	}
	public List<ViewMaintainPlanMemberOrg> getList(Long orgId, String json){
		try {					
			JSONObject obj = new JSONObject(json);
			obj.put("GoupId", orgId);
			return maintainPlanMemberDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	public long getListSize(Long orgId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			obj.put("GoupId", orgId);
			return maintainPlanMemberDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;		
	}
}
	
	/**
	 * 清空此填寫者資料
	 * 	 
	 * @param json
	 *            資安維護計畫對象資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫對象資料
	 */
	public MaintainPlanMember clearMember(long maintainPlanId, long groupid) {
		try {							
						
			MaintainPlanMember entity = maintainPlanMemberDAO.getListByMaintainPlanIdAndGroupId(maintainPlanId, groupid);							
			entity.setMemberId((long)0);	

			maintainPlanMemberDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 退回資料
	 * 	 
	 * @param json
	 *            資安維護計畫對象資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫對象資料
	 */
	public MaintainPlanMember returnMember(long maintainPlanId, long groupid) {
		try {													
			MaintainPlanMember entity = maintainPlanMemberDAO.getListByMaintainPlanIdAndGroupId(maintainPlanId, groupid);							
			entity.setReplyTime(null);
			entity.setStatus("4");
			maintainPlanMemberDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 啟動稽核資料
	 * 	 
	 * @param json
	 *            資安維護計畫對象資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫對象資料
	 */
	public MaintainPlanMember startAudit(long maintainPlanId, long groupid) {
		try {			
			this.clearMember(maintainPlanId, groupid);
			MaintainPlanMember entity = maintainPlanMemberDAO.getListByMaintainPlanIdAndGroupId(maintainPlanId, groupid);										
			entity.setStatus("5");
			maintainPlanMemberDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 進行資安維護計畫實施情形流程
	 * 	 
	 * @param json
	 *            資安維護計畫對象資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫對象資料
	 */
	public MaintainPlanMember startImplement(long maintainPlanId, long groupid) {
		try {			
			this.clearMember(maintainPlanId, groupid);
			MaintainPlanMember entity = maintainPlanMemberDAO.getListByMaintainPlanIdAndGroupId(maintainPlanId, groupid);										
			entity.setStatus("31");
			maintainPlanMemberDAO.update(entity);
			return entity;
		} catch (Exception e) {
//			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 退回資安維護計畫實施情形資料
	 * 	 
	 * @param json
	 *            資安維護計畫對象資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫對象資料
	 */
	public MaintainPlanMember returnImplement(long maintainPlanId, long groupid) {
		try {			
			
			MaintainPlanMember entity = maintainPlanMemberDAO.getListByMaintainPlanIdAndGroupId(maintainPlanId, groupid);										
			entity.setStatus("31");
			maintainPlanMemberDAO.update(entity);
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
	public MaintainPlanMember returnAudit(long maintainPlanId, long groupid) {
		try {			
			
			MaintainPlanMember entity = maintainPlanMemberDAO.getListByMaintainPlanIdAndGroupId(maintainPlanId, groupid);										
			entity.setStatus("5");
			maintainPlanMemberDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 退回改善報告資料
	 * 	 
	 * @param json
	 *            資安維護計畫對象資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫對象資料
	 */
	public MaintainPlanMember returnImprovement(long maintainPlanId, long groupid) {
		try {			
			
			MaintainPlanMember entity = maintainPlanMemberDAO.getListByMaintainPlanIdAndGroupId(maintainPlanId, groupid);										
			entity.setStatus("8");
			maintainPlanMemberDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 繳交資安維護計畫實施情形
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            資安維護計畫對象資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫對象資料
	 */
	public MaintainPlanMember implement_submit(long maintainPlanId, long groupid, long memberId) {
		try {
			MaintainPlanMember entity = maintainPlanMemberDAO.getListByMaintainPlanIdAndGroupId(maintainPlanId, groupid);
			entity.setStatus("32");			
			entity.setMemberId(memberId);
			
			maintainPlanMemberDAO.update(entity);
			return entity;
			
		}
		catch (Exception e) {
			//e.printStackTrace();
			return null;
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
	public MaintainPlanMember questionnaire_submit(long maintainPlanId, long groupid, long memberId) {
		try {							
						
			MaintainPlanMember entity = maintainPlanMemberDAO.getListByMaintainPlanIdAndGroupId(maintainPlanId, groupid);				
			entity.setStatus("6");
//			entity.setInspectStatus("6");
			entity.setMemberId(memberId);	

			maintainPlanMemberDAO.update(entity);
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
	public MaintainPlanMember score_submit(long maintainPlanId, long groupid) {
		try {							
						
			MaintainPlanMember entity = maintainPlanMemberDAO.getListByMaintainPlanIdAndGroupId(maintainPlanId, groupid);
			entity.setStatus("7");						
//			entity.setInspectStatus("7");
			
			maintainPlanMemberDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 繳交結果報告
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            資安維護計畫對象資料
	 * @param isApply
	 *            Boolean
	 * @return 資安維護計畫對象資料
	 */
	public MaintainPlanMember result_submit(long maintainPlanId, long groupid) {
		try {							
				
			this.clearMember(maintainPlanId, groupid);
			MaintainPlanMember entity = maintainPlanMemberDAO.getListByMaintainPlanIdAndGroupId(maintainPlanId, groupid);				
			entity.setStatus("8");						

			maintainPlanMemberDAO.update(entity);
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
	public MaintainPlanMember improvement_submit(long maintainPlanId, long groupid, long memberId) {
		try {							
						
			MaintainPlanMember entity = maintainPlanMemberDAO.getListByMaintainPlanIdAndGroupId(maintainPlanId, groupid);				
			entity.setStatus("9");
//			entity.setInspectStatus("9");
			entity.setMemberId(memberId);	

			maintainPlanMemberDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	public List<JSONObject> getMaintainInspectList(String json){
		try {					
			JSONObject obj = new JSONObject(json);
			List<Object[]> maintainPlanMembers = maintainPlanMemberDAO.getMaintainInspectList(obj);
			List<JSONObject> result = new ArrayList<>();
			for(Object[] array : maintainPlanMembers) {
				JSONObject item = new JSONObject();
				item.put("MaintainPlanId", (Long) array[0]);
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
			return maintainPlanMemberDAO.getMaintainInspectListSize(obj);
		} catch (Exception e) {
			return 0;		
		}
	}
	
	public List<ViewMaintainInspectMemberOrg> getMaintainInspectListByMaintainPlanId(long maintainPlanId){
		try {
			return maintainPlanMemberDAO.getMaintainInspectListByMaintainPlanId(maintainPlanId);
		} catch (Exception e) {
			return null;
		}
	}
	
	public ViewMaintainInspectMemberOrg findMaintainInspectById(long id){
		try {
			return maintainPlanMemberDAO.findMaintainInspectById(id);
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean isExist(Long id) {
		return maintainPlanMemberDAO.get(id) != null;
	}
	
//	public MaintainPlanMember updateMaintainInspectMember(long memberId, String json) {
//		try {
//			JSONObject obj = new JSONObject(json);
//			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
//			String inspectStatus = obj.isNull("InspectStatus") == true ? null : obj.getString("InspectStatus");
//			boolean allowHospitalPatch = obj.isNull("AllowHospitalPatch") == true ? false : obj.getBoolean("AllowHospitalPatch");
//
//			String strHospitalUploadSdate = obj.isNull("HospitalUploadSdate") == true ? null : obj.getString("HospitalUploadSdate");
//			Date hospitalUploadSdate = WebDatetime.parse(strHospitalUploadSdate, "yyyy-MM-dd");
//			String strHospitalUploadEdate = obj.isNull("HospitalUploadEdate") == true ? null : obj.getString("HospitalUploadEdate");
//			Date hospitalUploadEdate = WebDatetime.parse(strHospitalUploadEdate, "yyyy-MM-dd");
//			String strCommitteeUploadSdate = obj.isNull("CommitteeUploadSdate") == true ? null : obj.getString("CommitteeUploadSdate");
//			Date committeeUploadSdate = WebDatetime.parse(strCommitteeUploadSdate, "yyyy-MM-dd");
//			String strCommitteeUploadEdate = obj.isNull("CommitteeUploadEdate") == true ? null : obj.getString("CommitteeUploadEdate");
//			Date committeeUploadEdate = WebDatetime.parse(strCommitteeUploadEdate, "yyyy-MM-dd");
//
//			MaintainPlanMember entity = maintainPlanMemberDAO.get(id);
//			entity.setInspectStatus(inspectStatus);
//			entity.setHospitalUploadSdate(hospitalUploadSdate);
//			entity.setHospitalUploadEdate(hospitalUploadEdate);
//			entity.setCommitteeUploadSdate(committeeUploadSdate);
//			entity.setCommitteeUploadEdate(committeeUploadEdate);
//			entity.setAllowHospitalPatch(allowHospitalPatch);
//			entity.setInspectMemberId(memberId);
//
//			maintainPlanMemberDAO.update(entity);
//			return entity;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		
//	}
//
//	public MaintainPlanMember modifyMaintainInspectMember(long memberId, String json) {
//		try {
//			JSONObject obj = new JSONObject(json);
//			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
//			boolean allowHospitalPatch = obj.isNull("AllowHospitalPatch") == true ? false : obj.getBoolean("AllowHospitalPatch");
//
//			MaintainPlanMember entity = maintainPlanMemberDAO.get(id);
//			entity.setAllowHospitalPatch(allowHospitalPatch);
//			entity.setInspectMemberId(memberId);
//
//			maintainPlanMemberDAO.update(entity);
//			return entity;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	public MaintainPlanMember submitMaintainInspectResult(long maintainPlanId, long groupid, long memberId) {
//		try {							
//			MaintainPlanMember entity = maintainPlanMemberDAO.getListByMaintainPlanIdAndGroupId(maintainPlanId, groupid);				
//			entity.setInspectStatus("8");						
//			entity.setInspectMemberId(memberId);
//			maintainPlanMemberDAO.update(entity);
//			return entity;
//		} catch (Exception e) {
//			//e.printStackTrace();
//			return null;
//		}
//	}
//	
//	/**
//	 * 退回稽核資料
//	 * 	 
//	 * @param json
//	 *            資安維護計畫對象資料
//	 * @param isApply
//	 *            Boolean
//	 * @return 資安維護計畫對象資料
//	 */
//	public MaintainPlanMember returnMaintainInspectHospital(long maintainPlanId, long groupid, long memberId) {
//		try {
//			MaintainPlanMember entity = maintainPlanMemberDAO.getListByMaintainPlanIdAndGroupId(maintainPlanId, groupid);				
//			entity.setInspectStatus("5");						
//			entity.setInspectMemberId(memberId);
//			maintainPlanMemberDAO.update(entity);
//			return entity;
//		} catch (Exception e) {
//			//e.printStackTrace();
//			return null;
//		}
//	}
//	
//	/**
//	 * 退回稽核資料
//	 * 	 
//	 * @param json
//	 *            資安維護計畫對象資料
//	 * @param isApply
//	 *            Boolean
//	 * @return 資安維護計畫對象資料
//	 */
//	public MaintainPlanMember returnMaintainInspectCommittee(long maintainPlanId, long groupid, long memberId) {
//		try {
//			MaintainPlanMember entity = maintainPlanMemberDAO.getListByMaintainPlanIdAndGroupId(maintainPlanId, groupid);				
//			entity.setInspectStatus("6");						
//			entity.setInspectMemberId(memberId);
//			maintainPlanMemberDAO.update(entity);
//			
//			List<MaintainInspectCommittee> maintainInspectCommittees =
//					maintainInspectCommitteeDAO.getListByMaintainInspectIdAndGroupId(maintainPlanId, groupid);
//			if(maintainInspectCommittees!=null && !maintainInspectCommittees.isEmpty()) {
//				Date now = new Date();
//				for(MaintainInspectCommittee maintainInspectCommittee : maintainInspectCommittees) {
//					maintainInspectCommittee.setStatus(false);
//					maintainInspectCommittee.setModifyId(memberId);
//					maintainInspectCommittee.setModifyTime(now);
//					
//					maintainInspectCommitteeDAO.update(maintainInspectCommittee);
//				}
//			}
//			return entity;
//		} catch (Exception e) {
//			//e.printStackTrace();
//			return null;
//		}
//	}
//	
//	
//	/**
//	 * 退回稽核資料
//	 * 	 
//	 * @param json
//	 *            資安維護計畫對象資料
//	 * @param isApply
//	 *            Boolean
//	 * @return 資安維護計畫對象資料
//	 */
//	public MaintainPlanMember returnMaintainInspectResult(long maintainPlanId, long groupid, long memberId) {
//		try {
//			MaintainPlanMember entity = maintainPlanMemberDAO.getListByMaintainPlanIdAndGroupId(maintainPlanId, groupid);				
//			entity.setInspectStatus("7");						
//			entity.setInspectMemberId(memberId);
//			maintainPlanMemberDAO.update(entity);
//			return entity;
//		} catch (Exception e) {
//			//e.printStackTrace();
//			return null;
//		}
//	}
}
