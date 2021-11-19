package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.gov.mohw.hisac.web.dao.MemberRoleDAO;
import tw.gov.mohw.hisac.web.dao.MemberRoleVariable;
import tw.gov.mohw.hisac.web.domain.MemberRole;
import tw.gov.mohw.hisac.web.domain.SpMemberRoleName;
import tw.gov.mohw.hisac.web.domain.ViewMemberRoleMember;

/**
 * 會員權限服務
 */
@Service
public class MemberRoleService {
	@Autowired
	MemberRoleDAO memberRoleDAO;

	/**
	 * 取得所有會員權限資料
	 * 
	 * @return 會員權限資料
	 */
	public List<MemberRole> getAll() {
		return memberRoleDAO.getAll();
	}

	/**
	 * 取得會員權限資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 會員權限資料join org
	 */

	public List<SpMemberRoleName> getMemberRoleList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return memberRoleDAO.getMemberRoleList(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 新增/更新會員權限資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param member_Id
	 *            member_Id
	 * @param roleId
	 *            角色Id
	 * @return 會員權限資料
	 */
	public MemberRole insert(long memberId, Long member_Id, Long roleId) {
		try {
			MemberRole entity = new MemberRole();
			Date now = new Date();
			entity.setMemberId(member_Id);
			entity.setRoleId(roleId);
			entity.setIsEnable(true);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			memberRoleDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 新增/更新會員權限資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param id
	 *            id
	 * @param member_Id
	 *            使用者Id
	 * @param roleId
	 *            角色Id
	 * @param flag
	 *            IsEnable
	 */
	public void insertOrDelete(long memberId, Long id, Long member_Id, Long roleId, boolean flag) {
		Date now = new Date();

		if (flag == true && id == 0) {
			MemberRole entity = new MemberRole();
			entity.setId(id);
			entity.setMemberId(member_Id);
			entity.setRoleId(roleId);
			entity.setIsEnable(flag);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			memberRoleDAO.insert(entity);
		} else if (flag == false && id != 0) {
			this.delete(id);
		}

	}

	/**
	 * 刪除會員權限資料
	 * 
	 * @param id
	 *            會員權限Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			MemberRole entity = memberRoleDAO.get(id);
			memberRoleDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 取得會員權限資料
	 * 
	 * @param id
	 *            會員權限資料Id
	 * @return 會員權限資料
	 */
	public MemberRole getById(Long id) {
		return memberRoleDAO.get(id);
	}
	/**
	 * 會員權限資料是否存在
	 * 
	 * @param id
	 *            會員權限資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return memberRoleDAO.get(id) != null;
	}
	/**
	 * 會員權限資料名稱
	 * 
	 * @param roleId
	 *            會員權限名稱
	 * @return 會員權限名稱資料
	 */
	public List<ViewMemberRoleMember> findByRoleId(long roleId) {
		return memberRoleDAO.getByRoleId(roleId);
	}

	/**
	 * 會員權限資料名稱
	 * 
	 * @param memberId
	 *            使用者Id
	 * @return 會員權限名稱資料
	 */
	public List<MemberRole> getByMemberId(long memberId) {
		return memberRoleDAO.getAllByMemberId(memberId);
	}
	
	
	/**
	 * 會員權限資料名稱
	 * 
	 * @param memberId
	 *            使用者Id
	 * @return 會員權限名稱資料
	 */
	public List<MemberRole> getByMemberIds(Long[] memberIds) {
		return memberRoleDAO.getAllByMemberIds(memberIds);
	}

	public MemberRoleVariable getMemberRoleVariable(Long memberId) {
		MemberRoleVariable memberRoleVariable = new MemberRoleVariable();
		List<MemberRole> memberRoles = memberRoleDAO.getByMemberId(memberId);
		for (MemberRole memberRole : memberRoles) {
			Long roleId = memberRole.getRoleId();
			if (roleId == (long) 1) {
				memberRoleVariable.IsAdmin = true;
			} else if (roleId == (long) 2) {
				memberRoleVariable.IsHisac = true;
			} else if (roleId == (long) 3) {
				memberRoleVariable.IsHisacContent = true;
			} else if (roleId == (long) 4) {
				memberRoleVariable.IsHisacContentSign = true;
			} else if (roleId == (long) 5) {
				memberRoleVariable.IsHisacInfoBuilder = true;
			} else if (roleId == (long) 6) {
				memberRoleVariable.IsHisacInfoSign = true;
			} else if (roleId == (long) 7) {
				memberRoleVariable.IsApplySign = true;
			} else if (roleId == (long) 8) {
				memberRoleVariable.IsApplyContact = true;
			} else if (roleId == (long) 9) {
				memberRoleVariable.IsApplyAdmin = true;
			} else if (roleId == (long) 10) {
				memberRoleVariable.IsMemberContact = true;
			} else if (roleId == (long) 11) {
				memberRoleVariable.IsMemberAdmin = true;
			} else if (roleId == (long) 12) {
				memberRoleVariable.IsHisacNotifySign = true;
			} else if (roleId == (long) 13) {
				memberRoleVariable.IsHisacIXContent = true;
			} else if (roleId == (long) 14) {
				memberRoleVariable.IsHisacIXContentSign = true;
			} else if (roleId == (long) 15) {
				memberRoleVariable.IsApplySingAdmin = true;
			} else if (roleId == (long) 16) {
				memberRoleVariable.IsHisacBoss = true;
				memberRoleVariable.IsHisac = true;
			} else if (roleId == (long) 17) {
				memberRoleVariable.IsHCERTContentSign = true;
			} else if (roleId == (long) 18) {
				memberRoleVariable.IsEventHandlingUnitContact = true;
			} else if (roleId == (long) 20) {
				memberRoleVariable.IsMaintainInspectCommittee = true;
			} else if (roleId == (long) 21) {
				memberRoleVariable.IsPmo = true;
			} else if (roleId == (long) 22) {
				memberRoleVariable.IsSystemDeveloper = true;
			}
			
		}
		return memberRoleVariable;
	}
	
	
	/**
	 * 會員權限資料名稱(包含isEnable為false)
	 * 
	 * @param memberId
	 *            使用者Id
	 * @return 會員權限名稱資料
	 */
	public List<MemberRole> getAllByMemberId(long memberId) {
		return memberRoleDAO.getAllByMemberId(memberId);
	}
	
	/**
	 * 改變會員權限
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param roleId
	 *            roleId
	 * @param isEnable
	 *            isEnable
	 * @return 會員權限名稱資料
	 */
	public MemberRole changeRole(long memberId, long roleId, boolean isEnable) {
		try {
			MemberRole entity = memberRoleDAO.getByMemberIdAndRoleId(memberId, roleId);								
			entity.setIsEnable(isEnable);						
			memberRoleDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}		
	}
	
	
	/**
	 * 更新會員權限資料(第一筆isEnable=true,其他false)
	 * 
	 * @param memberId
	 *            使用者Id	
	 * @return 是否成功
	 */
	public boolean updateIsenable(long memberId) {
		try {
			List<MemberRole> memberRoles = memberRoleDAO.getAllByMemberId(memberId);				
			int num =0;
			if (memberRoles != null)
				for (MemberRole memberRole : memberRoles) {					
					if (num == 0)
						memberRole.setIsEnable(true);
					else
						memberRole.setIsEnable(false);
					num++;					
					memberRoleDAO.update(memberRole);					
				}	
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
}
