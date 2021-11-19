package tw.gov.mohw.hisac.web.service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.dao.InformationManagementDAO;
import tw.gov.mohw.hisac.web.domain.InformationManagement;
import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
/**
 * 情資分享管理服務
 */
@Service
public class InformationManagementService {

	@Autowired
	InformationManagementDAO informationManagementDAO;		
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	private MailService mailService;	
	
	@Autowired
	protected ResourceMessageService resourceMessageService;

	/**
	 * 取得情資分享資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 情資分享資料
	 */
	public List<InformationManagement> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return informationManagementDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得情資分享資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 情資分享資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return informationManagementDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 取得情資分享資料
	 * 
	 * @param id
	 *            情資分享資料Id
	 * @return 情資分享資料
	 */
	public InformationManagement get(Long id) {
		return informationManagementDAO.get(id);
	}
	
	
	
	/**
	 * 取得情資分享資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 情資分享資料
	 */
	public List<InformationManagement> getSpList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return informationManagementDAO.getSpList(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得情資分享資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 情資分享資料筆數
	 */
	public long getSpListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return informationManagementDAO.getSpListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}
	
	
	/**
	 * 新增情資分享資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *           情資分享資料	 
	 * @return 情資分享資料
	 */
	public InformationManagement insert(Long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);			
			Date postDateTime = obj.isNull("PostDateTime") == true ? new Date() : WebDatetime.parse(obj.getString("PostDateTime"), "yyyy-MM-dd");
			String titleEdit = obj.isNull("TitleEdit") == true ? "" : obj.getString("TitleEdit");
			String sourceNameEdit = obj.isNull("SourceNameEdit") == true ? null : obj.getString("SourceNameEdit");
			String sourceLinkEdit = obj.isNull("SourceLinkEdit") == true ? null : obj.getString("SourceLinkEdit");			
			String contentEdit = obj.isNull("ContentEdit") == true ? null : obj.getString("ContentEdit");			
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			String email = obj.isNull("Email") == true ? null : obj.getString("Email");
			String title = obj.isNull("Title") == true ? "" : obj.getString("Title");
			String sourceName = obj.isNull("SourceName") == true ? null : obj.getString("SourceName");
			String sourceLink = obj.isNull("SourceLink") == true ? null : obj.getString("SourceLink");			
			String content = obj.isNull("Content") == true ? null : obj.getString("Content");			
			Boolean isBreakLine = obj.isNull("IsBreakLine") == true ? true : obj.getBoolean("IsBreakLine");			
			Boolean isEnable = obj.isNull("IsEnable") == true ? true : obj.getBoolean("IsEnable");
			Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");	
			String remark = obj.isNull("Remark") == true ? null : obj.getString("Remark");	

			Date now = new Date();
			InformationManagement entity = new InformationManagement();		
			entity.setName(name);
			entity.setEmail(email);
			entity.setPostDateTime(postDateTime);
			entity.setTitleEdit(titleEdit);
			entity.setSourceNameEdit(sourceNameEdit);
			entity.setSourceLinkEdit(sourceLinkEdit);			
			entity.setContentEdit(contentEdit);		
			entity.setTitle(titleEdit);
			entity.setSourceName(sourceNameEdit);
			entity.setSourceLink(sourceLinkEdit);			
			entity.setContent(contentEdit);		
			entity.setIsBreakLine(isBreakLine);			
			entity.setIsEnable(isEnable);			
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			entity.setStatus(status);
			entity.setRemark(remark);		
			informationManagementDAO.insert(entity);			
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 更新情資分享資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            情資分享資料	
	 * @return 情資分享資料
	 */
	public InformationManagement update(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");			
			Date postDateTime = obj.isNull("PostDateTime") == true ? new Date() : WebDatetime.parse(obj.getString("PostDateTime"), "yyyy-MM-dd");
			String titleEdit = obj.isNull("TitleEdit") == true ? "" : obj.getString("TitleEdit");
			String sourceNameEdit = obj.isNull("SourceNameEdit") == true ? null : obj.getString("SourceNameEdit");
			String sourceLinkEdit = obj.isNull("SourceLinkEdit") == true ? null : obj.getString("SourceLinkEdit");			
			String contentEdit = obj.isNull("ContentEdit") == true ? null : obj.getString("ContentEdit");			
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			String email = obj.isNull("Email") == true ? null : obj.getString("Email");
			String title = obj.isNull("Title") == true ? "" : obj.getString("Title");
			String sourceName = obj.isNull("SourceName") == true ? null : obj.getString("SourceName");
			String sourceLink = obj.isNull("SourceLink") == true ? null : obj.getString("SourceLink");			
			String content = obj.isNull("Content") == true ? null : obj.getString("Content");			
			Boolean isBreakLine = obj.isNull("IsBreakLine") == true ? true : obj.getBoolean("IsBreakLine");			
			Boolean isEnable = obj.isNull("IsEnable") == true ? true : obj.getBoolean("IsEnable");
			Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");	
			String remark = obj.isNull("Remark") == true ? null : obj.getString("Remark");	

			Date now = new Date();
			InformationManagement entity = informationManagementDAO.get(id);

			entity.setName(name);
			entity.setEmail(email);
			entity.setPostDateTime(postDateTime);
			entity.setTitleEdit(titleEdit);
			entity.setSourceNameEdit(sourceNameEdit);
			entity.setSourceLinkEdit(sourceLinkEdit);			
			entity.setContentEdit(contentEdit);		
			entity.setTitle(titleEdit);
			entity.setSourceName(sourceNameEdit);
			entity.setSourceLink(sourceLinkEdit);			
			entity.setContent(contentEdit);			
			entity.setIsBreakLine(isBreakLine);			
			entity.setIsEnable(isEnable);
			entity.setCreateId(memberId);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			entity.setStatus(status);
			entity.setRemark(remark);		
			informationManagementDAO.update(entity);			
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 更新情資分享資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            情資分享資料
	 * @return 情資分享資料
	 */
	public InformationManagement modify(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");		
			Date postDateTime = obj.isNull("PostDateTime") == true ? new Date() : WebDatetime.parse(obj.getString("PostDateTime"), "yyyy-MM-dd");
			String titleEdit = obj.isNull("TitleEdit") == true ? "" : obj.getString("TitleEdit");
			String sourceNameEdit = obj.isNull("SourceNameEdit") == true ? null : obj.getString("SourceNameEdit");
			String sourceLinkEdit = obj.isNull("SourceLinkEdit") == true ? null : obj.getString("SourceLinkEdit");			
			String contentEdit = obj.isNull("ContentEdit") == true ? null : obj.getString("ContentEdit");			
			String title = obj.isNull("Title") == true ? "" : obj.getString("Title");
			String sourceName = obj.isNull("SourceName") == true ? null : obj.getString("SourceName");
			String sourceLink = obj.isNull("SourceLink") == true ? null : obj.getString("SourceLink");			
			String content = obj.isNull("Content") == true ? null : obj.getString("Content");			
			Boolean isBreakLine = obj.isNull("IsBreakLine") == true ? null : obj.getBoolean("IsBreakLine");			
			Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
			Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");	
			String remark = obj.isNull("Remark") == true ? null : obj.getString("Remark");

			Date now = new Date();
			InformationManagement entity = informationManagementDAO.get(id);

			entity.setPostDateTime(postDateTime);
			entity.setTitleEdit(titleEdit);
			entity.setSourceNameEdit(sourceNameEdit);
			entity.setSourceLinkEdit(sourceLinkEdit);			
			entity.setContentEdit(contentEdit);		
			entity.setTitle(title);
			entity.setSourceName(sourceName);
			entity.setSourceLink(sourceLink);			
			entity.setContent(content);			
			entity.setIsBreakLine(isBreakLine);			
			entity.setIsEnable(isEnable);			
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			entity.setStatus(status);
			entity.setRemark(remark);	
			informationManagementDAO.update(entity);		
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 情資分享資料是否存在
	 * 
	 * @param id
	 *            情資分享資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return informationManagementDAO.get(id) != null;
	}
	
	
	/**
	 * 停用情資分享資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            情資分享資料
	 * @return 情資分享資料
	 */
	public InformationManagement disable(long memberId, Long id) {
		try {
			Date now = new Date();
			InformationManagement entity = informationManagementDAO.get(id);
			entity.setIsEnable(false);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			informationManagementDAO.update(entity);			
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 啟用情資分享資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            情資分享資料
	 * @return 情資分享資料
	 */
	public InformationManagement enable(long memberId, Long id) {
		try {
			Date now = new Date();
			InformationManagement entity = informationManagementDAO.get(id);
			entity.setIsEnable(true);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			informationManagementDAO.update(entity);			
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 刪除情資分享資料
	 * 
	 * @param id
	 *            情資分享資料Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			InformationManagement entity = informationManagementDAO.get(id);
			informationManagementDAO.delete(entity);		
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 審核情資分享資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param id
	 *            情資分享Id
	 * @param status
	 *            String
	 * @return 情資分享資料
	 */
	public InformationManagement examine(long memberId, String id, String status, String title, String sourceName, String sourceLink, String content, String remark, Boolean isBreakLine, Boolean isEnable) {
		try {
			Date now = new Date();
			InformationManagement entity = informationManagementDAO.get(Long.parseLong(id, 10));
			Member member = memberService.get(entity.getModifyId());

			if (member != null) {
			// 3-審核中 → 4-已公告
			if (status.equals("4")) {				

				// 寄信		
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailInformationManagement3To4Subject"), entity.getTitle());
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailInformationManagement3To4Body"), member.getName(), entity.getTitle());
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
				entity.setPostDateTime(now);
			}

			// 3-審核中 → 6-編輯中(退回)
			if (status.equals("6")) {
				// 寄信
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailInformationManagement3To6Subject"), entity.getTitle());
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailInformationManagement3To6Body"), member.getName(), entity.getTitle());
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);			
			}

			// 3-審核中 → 2-撤銷中
			if (status.equals("2")) {
				// 寄信
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailInformationManagement3Back2Subject"), entity.getTitle());
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailInformationManagement3Back2Body"), member.getName(), entity.getTitle());
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);			
			}
			}

			entity.setTitle(title);
			entity.setSourceName(sourceName);
			entity.setSourceLink(sourceLink);			
			entity.setContent(content);		
			entity.setIsBreakLine(isBreakLine);			
			entity.setIsEnable(isEnable);
			entity.setStatus(Long.parseLong(status));
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			entity.setRemark(remark);
			informationManagementDAO.update(entity);			
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 取得情資分享資料button count資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 情資分享資料狀態筆數
	 */
	public List<SpButtonCount> getSpButtonCount(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return informationManagementDAO.getSpButtonCount(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**
	 * 取得情資分享資料Form筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 情資分享資料筆數
	 */
	public long getSpFormCount(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return informationManagementDAO.getSpFormCount(obj);
		} catch (Exception e) {
			return 0;
		}
	}

}
