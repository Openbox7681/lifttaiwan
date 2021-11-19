package tw.gov.mohw.hisac.web.service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.dao.ActivityManagementDAO;
import tw.gov.mohw.hisac.web.dao.TicketQueueDAO;
import tw.gov.mohw.hisac.web.domain.ActivityManagement;
import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.NewsManagementGroup;
import tw.gov.mohw.hisac.web.domain.SpActivityManagementReport;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.ViewActivityManagementMember;
import tw.gov.mohw.hisac.web.domain.ViewMessageAlertEvent;
import tw.gov.mohw.hisac.web.domain.ViewSubscribeMember;

/**
 * 活動訊息管理服務
 */
@Service
public class ActivityManagementService {
	@Autowired
	ActivityManagementDAO activityManagementDAO;

	@Autowired
	TicketQueueDAO ticketQueueDAO;
	@Autowired
	private MemberService memberService;
	@Autowired
	protected ResourceMessageService resourceMessageService;
	@Autowired
	private MailService mailService;
	@Autowired
	private SubscribeMemberService subscribeMemberService;	

	static String activityGlobalData = null;
	static String activityGlobalDataPublic = null;

	public void setGlobalData(String globalData) {
		activityGlobalData = globalData;
	}

	public void resetGlobalData() {
		activityGlobalData = null;
	}

	public String getGlobalData() {
		return activityGlobalData;
	}

	public void setGlobalDataPublic(String globalDataPublic) {
		activityGlobalDataPublic = globalDataPublic;
	}

	public void resetGlobalDataPublic() {
		activityGlobalDataPublic = null;
	}

	public String getGlobalDataPublic() {
		return activityGlobalDataPublic;
	}

	/**
	 * 取得所有活動訊息管理資料
	 * 
	 * @return 活動訊息管理資料
	 */
	public List<ActivityManagement> getAll() {
		return activityManagementDAO.getAll();
	}

	/**
	 * 取得活動訊息管理資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 活動訊息管理資料
	 */
	public List<ViewActivityManagementMember> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return activityManagementDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 取得報表
	 * 
	 * @param json
	 *            查詢條件
	 * @return 報表
	 */
	public List<SpActivityManagementReport> getReport(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return activityManagementDAO.getReport(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得活動訊息管理資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 活動訊息管理資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return activityManagementDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 新增活動訊息管理資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            活動訊息管理資料
	 * @param isApply
	 *            是否允許
	 * @return 活動訊息管理資料
	 */
	public ActivityManagement insert(Long memberId, String json, Boolean isApply) {
		try {
			JSONObject obj = new JSONObject(json);
			String postType = "2";
			Date postDateTime = obj.isNull("PostDateTime") == true ? new Date() : WebDatetime.parse(obj.getString("PostDateTime"), "yyyy-MM-dd");
			String title = obj.isNull("Title") == true ? null : obj.getString("Title");
			String sourceName = obj.isNull("SourceName") == true ? null : obj.getString("SourceName");
			String sourceLink = obj.isNull("SourceLink") == true ? null : obj.getString("SourceLink");
			String contentType = obj.isNull("ContentType") == true ? null : obj.getString("ContentType");
			String content = obj.isNull("Content") == true ? null : obj.getString("Content");
			String externalLink = obj.isNull("ExternalLink") == true ? null : obj.getString("ExternalLink");
			Boolean isBreakLine = obj.isNull("IsBreakLine") == true ? null : obj.getBoolean("IsBreakLine");
			Date startDateTime = obj.isNull("StartDateTime") == true ? WebDatetime.parse("1753-01-01", "yyyy-MM-dd") : WebDatetime.parse(obj.getString("StartDateTime"), "yyyy-MM-dd");
			Date endDateTime = obj.isNull("EndDateTime") == true ? WebDatetime.parse("9999-12-31", "yyyy-MM-dd") : WebDatetime.parse(obj.getString("EndDateTime"), "yyyy-MM-dd");
			Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
			Boolean isPublic = obj.isNull("IsPublic") == true ? null : obj.getBoolean("IsPublic");
			Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");
			Long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");	

			// 流程紀錄用 - 開始
			String tableName = obj.isNull("TableName") == true ? null : obj.getString("TableName");
			String pre = obj.isNull("Pre") == true ? null : obj.getString("Pre");
			String postId = ticketQueueDAO.insertPostId(tableName, isApply, pre, "ACT");
			// 流程紀錄用 - 開始

			Date now = new Date();
			ActivityManagement entity = new ActivityManagement();
			entity.setPostId(postId);
			entity.setPostType(postType);
			entity.setPostDateTime(postDateTime);
			entity.setTitle(title);
			entity.setSourceName(sourceName);
			entity.setSourceLink(sourceLink);
			entity.setContentType(contentType);
			entity.setContent(content);
			entity.setExternalLink(externalLink);
			entity.setIsBreakLine(isBreakLine);
			entity.setStartDateTime(startDateTime);
			entity.setEndDateTime(endDateTime);
			entity.setIsEnable(isEnable);
			entity.setIsPublic(isPublic);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			entity.setStatus(status);
			entity.setSort(sort);
			activityManagementDAO.insert(entity);
			resetGlobalData();
			resetGlobalDataPublic();
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新活動訊息管理資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            活動訊息管理資料
	 * @param isApply
	 *            是否允許
	 * @return 活動訊息管理資料
	 */
	public ActivityManagement update(long memberId, String json, Boolean isApply) {
		try {
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String postType = "2";
			Date postDateTime = obj.isNull("PostDateTime") == true ? new Date() : WebDatetime.parse(obj.getString("PostDateTime"), "yyyy-MM-dd");
			String title = obj.isNull("Title") == true ? null : obj.getString("Title");
			String sourceName = obj.isNull("SourceName") == true ? null : obj.getString("SourceName");
			String sourceLink = obj.isNull("SourceLink") == true ? null : obj.getString("SourceLink");
			String contentType = obj.isNull("ContentType") == true ? null : obj.getString("ContentType");
			String content = obj.isNull("Content") == true ? null : obj.getString("Content");
			String externalLink = obj.isNull("ExternalLink") == true ? null : obj.getString("ExternalLink");
			Boolean isBreakLine = obj.isNull("IsBreakLine") == true ? null : obj.getBoolean("IsBreakLine");
			Date startDateTime = obj.isNull("StartDateTime") == true ? WebDatetime.parse("1753-01-01", "yyyy-MM-dd") : WebDatetime.parse(obj.getString("StartDateTime"), "yyyy-MM-dd");
			Date endDateTime = obj.isNull("EndDateTime") == true ? WebDatetime.parse("9999-12-31", "yyyy-MM-dd") : WebDatetime.parse(obj.getString("EndDateTime"), "yyyy-MM-dd");
			Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
			Boolean isPublic = obj.isNull("IsPublic") == true ? null : obj.getBoolean("IsPublic");
			Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");
			Long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");		

			Date now = new Date();
			ActivityManagement entity = activityManagementDAO.get(id);

			// 流程紀錄用 - 開始
			String postId = ticketQueueDAO.updatePostId("activity_management", isApply, "HISAC", entity.getPostId(), "ACT");
			// 流程紀錄用 - 結束

			entity.setPostId(postId);
			entity.setPostType(postType);
			entity.setPostDateTime(postDateTime);
			entity.setTitle(title);
			entity.setSourceName(sourceName);
			entity.setSourceLink(sourceLink);
			entity.setContentType(contentType);
			entity.setContent(content);
			entity.setExternalLink(externalLink);
			entity.setIsBreakLine(isBreakLine);
			entity.setStartDateTime(startDateTime);
			entity.setEndDateTime(endDateTime);
			entity.setIsEnable(isEnable);
			entity.setIsPublic(isPublic);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			entity.setStatus(status);
			entity.setSort(sort);
			activityManagementDAO.update(entity);
			resetGlobalData();
			resetGlobalDataPublic();
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新活動訊息資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            最新消息資料
	 * @param isApply
	 *            是否允許
	 * @return 最新消息資料
	 */
	public ActivityManagement modify(long memberId, String json, Boolean isApply) {
		try {
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String postType = "2";
			Date postDateTime = obj.isNull("PostDateTime") == true ? new Date() : WebDatetime.parse(obj.getString("PostDateTime"), "yyyy-MM-dd");
			String title = obj.isNull("Title") == true ? null : obj.getString("Title");
			String sourceName = obj.isNull("SourceName") == true ? null : obj.getString("SourceName");
			String sourceLink = obj.isNull("SourceLink") == true ? null : obj.getString("SourceLink");
			String contentType = obj.isNull("ContentType") == true ? null : obj.getString("ContentType");
			String content = obj.isNull("Content") == true ? null : obj.getString("Content");
			String externalLink = obj.isNull("ExternalLink") == true ? null : obj.getString("ExternalLink");
			Boolean isBreakLine = obj.isNull("IsBreakLine") == true ? null : obj.getBoolean("IsBreakLine");
			Date startDateTime = obj.isNull("StartDateTime") == true ? WebDatetime.parse("1753-01-01", "yyyy-MM-dd") : WebDatetime.parse(obj.getString("StartDateTime"), "yyyy-MM-dd");
			Date endDateTime = obj.isNull("EndDateTime") == true ? WebDatetime.parse("9999-12-31", "yyyy-MM-dd") : WebDatetime.parse(obj.getString("EndDateTime"), "yyyy-MM-dd");
			Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
			Boolean isPublic = obj.isNull("IsPublic") == true ? null : obj.getBoolean("IsPublic");
			Long status = obj.isNull("Status") == true ? 0 : obj.getLong("Status");
			Long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");		

			Date now = new Date();
			ActivityManagement entity = activityManagementDAO.get(id);

			entity.setPostType(postType);
			entity.setPostDateTime(postDateTime);
			entity.setTitle(title);
			entity.setSourceName(sourceName);
			entity.setSourceLink(sourceLink);
			entity.setContentType(contentType);
			entity.setContent(content);
			entity.setExternalLink(externalLink);
			entity.setIsBreakLine(isBreakLine);
			entity.setStartDateTime(startDateTime);
			entity.setEndDateTime(endDateTime);
			entity.setIsEnable(isEnable);
			entity.setIsPublic(isPublic);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			entity.setStatus(status);
			entity.setSort(sort);
			activityManagementDAO.update(entity);
			resetGlobalData();
			resetGlobalDataPublic();
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 停用活動訊息資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            活動訊息資料
	 * @return 活動訊息資料
	 */
	public ActivityManagement disable(long memberId, Long id) {
		try {
			Date now = new Date();
			ActivityManagement entity = activityManagementDAO.get(id);
			entity.setIsEnable(false);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			activityManagementDAO.update(entity);
			resetGlobalData();
			resetGlobalDataPublic();
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 刪除活動訊息管理資料
	 * 
	 * @param id
	 *            活動訊息管理Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			ActivityManagement entity = activityManagementDAO.get(id);
			activityManagementDAO.delete(entity);
			resetGlobalData();
			resetGlobalDataPublic();
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 活動訊息管理資料是否存在
	 * 
	 * @param id
	 *            活動訊息管理資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return activityManagementDAO.get(id) != null;
	}

	/**
	 * 取得活動訊息管理資料
	 * 
	 * @param id
	 *            活動訊息管理資料Id
	 * @return 活動訊息管理資料
	 */
	public ActivityManagement get(Long id) {
		return activityManagementDAO.get(id);
	}

	/**
	 * 取得活動訊息管理資料
	 * 
	 * @param id
	 *            活動訊息管理資料Id
	 * @return 活動訊息管理資料
	 */
	public ViewActivityManagementMember getByDetail(Long id) {
		return activityManagementDAO.getByDetail(id);
	}

	/**
	 * 審核活動訊息資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param id
	 *            活動訊息Id
	 * @param status
	 *            String
	 * @return 活動訊息資料
	 */
	public ActivityManagement examine(long memberId, String id, String status) {
		try {
			Date now = new Date();
			ActivityManagement entity = activityManagementDAO.get(Long.parseLong(id, 10));
			Member member = memberService.get(entity.getModifyId());

			// 3-審核中 → 4-已公告
			if (status.equals("4")) {

				String postId = ticketQueueDAO.updatePostId("activity_management", true, "HISAC", entity.getPostId(), "ACT");

				// 寄信
				// 收件者：entity.getEmail()
				// 主旨：H-ISAC活動訊息(postId)審核通過通知
				// 內容：entity.getName()，您好！您所新增之活動訊息(entity.getPostId())，正式活動訊息編號為：postId，經H-ISAC內容審核者審核通過並已發布，特此通知，謝謝！
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailActivity3To4Subject"), postId);
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailActivity3To4Body"), member.getName(), entity.getPostId(), postId);
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);

				// 寄信給訂閱者
				String messageValue_SubscribeSubject = resourceMessageService.getMessageValue("mailSubscribeSubject");
				String messageValue_SubscribeBody = resourceMessageService.getMessageValue("mailSubscribeBody");
				String subscribeName = "活動訊息";
				JSONArray recipientBccs =  new JSONArray();				
				// String recipients = "hisac-cs@mohw.gov.tw";
				String recipients = resourceMessageService.getMessageValue("globalFooterEmail");
				List<ViewSubscribeMember> viewSubscribeMembers = null;											
				viewSubscribeMembers = subscribeMemberService.getBySubscribeName(subscribeName);
				if (viewSubscribeMembers != null) {
					for (ViewSubscribeMember viewSubscribeMember : viewSubscribeMembers) {
						recipientBccs.put(viewSubscribeMember.getEmail());								
					}
				}
				String mailSubject_Subscribe = messageValue_SubscribeSubject;
				String mailBody_Subscribe = MessageFormat.format(messageValue_SubscribeBody, subscribeName,entity.getModifyTime(), entity.getTitle(), entity.getContent());
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(),recipients , null, recipientBccs, mailSubject_Subscribe, mailBody_Subscribe, null);												
				entity.setPostId(postId);
				entity.setPostDateTime(now);
			}

			// 3-審核中 → 6-編輯中(退回)
			if (status.equals("6")) {
				// 寄信
				// 收件者：entity.getEmail()
				// 主旨：H-ISAC活動訊息(entity.getPostId())審核退回通知
				// 內容：entity.getName()，您好！您所新增之活動訊息(entity.getPostId())，經H-ISAC內容審核者審核退回，請您儘快撥冗進行後續處理，謝謝！
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailActivity3To6Subject"), entity.getPostId());
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailActivity3To6Body"), member.getName(), entity.getPostId());
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
			}

			// 3-審核中 → 2-撤銷中
			if (status.equals("2")) {
				// 寄信
				// 收件者：entity.getEmail()
				// 主旨：H-ISAC活動訊息(entity.getPostId())審核撤銷通知
				// 內容：entity.getName()，您好！您所新增之活動訊息(entity.getPostId())，經H-ISAC內容審核者審核撤銷，請您儘快撥冗進行後續處理，謝謝！
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailActivity3Back2Subject"), entity.getPostId());
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailActivity3Back2Body"), member.getName(), entity.getPostId());
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
			}

			entity.setStatus(Long.parseLong(status));
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			activityManagementDAO.update(entity);
			resetGlobalData();
			resetGlobalDataPublic();
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 取得活動訊息資料
	 * 
	 * @param id
	 *            活動訊息資料Id
	 * @return 活動訊息資料
	 */
	public ViewMessageAlertEvent getById(String id) {
		return activityManagementDAO.getById(id);
	}

	/**
	 * 取得活動訊息資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 活動訊息資料
	 */
	public List<ViewActivityManagementMember> getSpList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return activityManagementDAO.getSpList(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得活動訊息資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 活動訊息資料筆數
	 */
	public long getSpListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return activityManagementDAO.getSpListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 取得活動訊息資料Form筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 活動訊息資料筆數
	 */
	public long getSpFormCount(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return activityManagementDAO.getSpFormCount(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 取得活動訊息資料button count資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 活動訊息資料狀態筆數
	 */
	public List<SpButtonCount> getSpButtonCount(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return activityManagementDAO.getSpButtonCount(obj);
		} catch (Exception e) {
			return null;
		}
	}

}
