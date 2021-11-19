package tw.gov.mohw.hisac.web.dao;

/**
 * Member Role Variable
 */
public class MemberRoleVariable {
	/**
	 * 1.Admin
	 */
	public boolean IsAdmin = false;
	/**
	 * 2.HISAC管理者
	 */
	public boolean IsHisac = false;
	/**
	 * 3.HISAC內容維護者
	 */
	public boolean IsHisacContent = false;
	/**
	 * 4.HISAC內容審核者
	 */
	public boolean IsHisacContentSign = false;
	/**
	 * 5.HISAC警訊建立者
	 */
	public boolean IsHisacInfoBuilder = false;
	/**
	 * 6.HISAC警訊審核者
	 */
	public boolean IsHisacInfoSign = false;
	/**
	 * 7.權責單位警訊審核者
	 */
	public boolean IsApplySign = false;
	/**
	 * 8.權責單位聯絡人
	 */
	public boolean IsApplyContact = false;
	/**
	 * 9.權責單位管理者
	 */
	public boolean IsApplyAdmin = false;
	/**
	 * 10.會員機構聯絡人
	 */
	public boolean IsMemberContact = false;
	/**
	 * 11.會員機構管理者
	 */
	public boolean IsMemberAdmin = false;
	/**
	 * 12.通報審核者
	 */
	public boolean IsHisacNotifySign = false;
	/**
	 * 13.情資維護者
	 */
	public boolean IsHisacIXContent = false;
	/**
	 * 14.情資審核者
	 */
	public boolean IsHisacIXContentSign = false;
	/**
	 * 15.權責單位通報審核者
	 */
	public boolean IsApplySingAdmin = false;
	/**
	 * 16.主管機關
	 */
	public boolean IsHisacBoss = false;
	/**
	 * 17.H-CERT審核者 (108/6/11 新增)
	 */
	public boolean IsHCERTContentSign = false;	
	/**
	 * 18.事件處理單位聯絡人 (108/6/11 新增)
	 */
	public boolean IsEventHandlingUnitContact = false;
	
	
	/**
	 * 20.資安維護計畫稽核委員 (110/08/05 新增)
	 */
	public boolean IsMaintainInspectCommittee = false;
	
	/**
	 * 21.法尊稽核計畫PMO (110/08/24 新增)
	 */
	public boolean IsPmo = false;
	
	
	/**
	 * 22.系統開發人員 (110/08/24 新增)
	 */
	public boolean IsSystemDeveloper = false;
	
	
}