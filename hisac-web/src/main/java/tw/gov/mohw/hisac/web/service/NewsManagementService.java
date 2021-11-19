package tw.gov.mohw.hisac.web.service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.dao.NewsManagementDAO;
import tw.gov.mohw.hisac.web.dao.TicketQueueDAO;
import tw.gov.mohw.hisac.web.domain.Member;
import tw.gov.mohw.hisac.web.domain.NewsManagement;
import tw.gov.mohw.hisac.web.domain.NewsManagementGroup;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.SpNewsManagementReport;
import tw.gov.mohw.hisac.web.domain.ViewMessageAlertEvent;
import tw.gov.mohw.hisac.web.domain.ViewNewsManagementMember;
import tw.gov.mohw.hisac.web.domain.ViewSubscribeMember;

/**
 * 最新消息管理服務
 */
@Service
public class NewsManagementService {

	@Autowired
	NewsManagementDAO newsManagementDAO;

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
	@Autowired
	private NewsManagementGroupService newsManagementGroupService;		

	static String newsGlobalData = null;
	static String newsGlobalDataPublic = null;

	public void setGlobalData(String globalData) {
		newsGlobalData = globalData;
	}

	public void resetGlobalData() {
		newsGlobalData = null;
	}

	public String getGlobalData() {
		return newsGlobalData;
	}
	
	public void setGlobalDataPublic(String globalDataPublic) {
		newsGlobalDataPublic = globalDataPublic;
	}

	public void resetGlobalDataPublic() {
		newsGlobalDataPublic = null;
	}

	public String getGlobalDataPublic() {
		return newsGlobalDataPublic;
	}

	/**
	 * 取得所有最新消息資料
	 * 
	 * @return 最新消息資料
	 */
	public List<NewsManagement> getAll() {
		return newsManagementDAO.getAll();
	}

	/**
	 * 取得最新消息資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 最新消息資料
	 */
	public List<ViewNewsManagementMember> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return newsManagementDAO.getList(obj);
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
	public List<SpNewsManagementReport> getReport(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return newsManagementDAO.getReport(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得最新消息資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 最新消息資料筆數
	 */
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return newsManagementDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 新增最新消息資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            最新消息資料
	 * @param isApply
	 *            是否允許
	 * @return 最新消息資料
	 */
	public NewsManagement insert(Long memberId, String json, Boolean isApply) {
		try {
			JSONObject obj = new JSONObject(json);
			String postType = "1";
			Long newsManagementGroupId = obj.isNull("NewsManagementGroupId") == true ? 0 : obj.getLong("NewsManagementGroupId");
			Date postDateTime = obj.isNull("PostDateTime") == true ? new Date() : WebDatetime.parse(obj.getString("PostDateTime"), "yyyy-MM-dd");
			String title = obj.isNull("Title") == true ? "" : obj.getString("Title");
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
			String postId = ticketQueueDAO.insertPostId(tableName, isApply, pre, "NEWS");
			// 流程紀錄用 - 開始

			Date now = new Date();
			NewsManagement entity = new NewsManagement();
			entity.setPostId(postId);
			entity.setPostType(postType);
			entity.setNewsManagementGroupId(newsManagementGroupId);
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
			newsManagementDAO.insert(entity);
			resetGlobalData();
			resetGlobalDataPublic();
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新最新消息資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            最新消息資料
	 * @param isApply
	 *            是否允許
	 * @return 最新消息資料
	 */
	public NewsManagement update(long memberId, String json, Boolean isApply) {
		try {
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String postType = "1";
			Long newsManagementGroupId = obj.isNull("NewsManagementGroupId") == true ? 0 : obj.getLong("NewsManagementGroupId");
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
			NewsManagement entity = newsManagementDAO.get(id);

			// 流程紀錄用 - 開始
			String postId = ticketQueueDAO.updatePostId("news_management", isApply, "HISAC", entity.getPostId(), "NEWS");
			// 流程紀錄用 - 結束

			entity.setPostId(postId);
			entity.setPostType(postType);
			entity.setNewsManagementGroupId(newsManagementGroupId);
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
			newsManagementDAO.update(entity);
			resetGlobalData();
			resetGlobalDataPublic();
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新最新消息資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            最新消息資料
	 * @param isApply
	 *            是否允許
	 * @return 最新消息資料
	 */
	public NewsManagement modify(long memberId, String json, Boolean isApply) {
		try {
			JSONObject obj = new JSONObject(json);
			Long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String postType = "1";
			Long newsManagementGroupId = obj.isNull("NewsManagementGroupId") == true ? 0 : obj.getLong("NewsManagementGroupId");
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
			NewsManagement entity = newsManagementDAO.get(id);

			entity.setPostType(postType);
			entity.setNewsManagementGroupId(newsManagementGroupId);
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
			newsManagementDAO.update(entity);
			resetGlobalData();
			resetGlobalDataPublic();
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 停用最新消息資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            最新消息資料
	 * @return 最新消息資料
	 */
	public NewsManagement disable(long memberId, Long id) {
		try {
			Date now = new Date();
			NewsManagement entity = newsManagementDAO.get(id);
			entity.setIsEnable(false);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			newsManagementDAO.update(entity);
			resetGlobalData();
			resetGlobalDataPublic();
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 刪除最新消息資料
	 * 
	 * @param id
	 *            最新消息管理Id
	 * @return 是否刪除成功
	 */
	public boolean delete(Long id) {
		try {
			NewsManagement entity = newsManagementDAO.get(id);
			newsManagementDAO.delete(entity);
			resetGlobalData();
			resetGlobalDataPublic();
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 最新消息資料是否存在
	 * 
	 * @param id
	 *            最新消息資料Id
	 * @return 是否存在
	 */
	public boolean isExist(Long id) {
		return newsManagementDAO.get(id) != null;
	}

	/**
	 * 取得最新消息資料
	 * 
	 * @param id
	 *            最新消息資料Id
	 * @return 最新消息資料
	 */
	public NewsManagement get(Long id) {
		return newsManagementDAO.get(id);
	}

	/**
	 * 取得最新消息資料
	 * 
	 * @param id
	 *            最新消息資料Id
	 * @return 最新消息資料
	 */
	public ViewNewsManagementMember getByDetail(Long id) {
		return newsManagementDAO.getByDetail(id);
	}

	/**
	 * 審核最新消息資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param id
	 *            最新消息Id
	 * @param status
	 *            String
	 * @return 最新消息資料
	 */
	public NewsManagement examine(long memberId, String id, String status) {
		try {
			Date now = new Date();
			NewsManagement entity = newsManagementDAO.get(Long.parseLong(id, 10));
			Member member = memberService.get(entity.getModifyId());

			// 3-審核中 → 4-已公告
			if (status.equals("4")) {

				String postId = ticketQueueDAO.updatePostId("news_management", true, "HISAC", entity.getPostId(), "NEWS");

				// 寄信
				// 收件者：entity.getEmail()
				// 主旨：H-ISAC最新消息(postId)審核通過通知
				// 內容：entity.getName()，您好！您所新增之最新消息(entity.getPostId())，正式最新消息編號為：postId，經H-ISAC內容審核者審核通過並已發布，特此通知，謝謝！
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNews3To4Subject"), postId);
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNews3To4Body"), member.getName(), entity.getPostId(), postId);
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
				// 寄信給訂閱者
				String messageValue_SubscribeSubject = resourceMessageService.getMessageValue("mailSubscribeSubject");
				String messageValue_SubscribeBody = resourceMessageService.getMessageValue("mailSubscribeBody");
				String newsManagementGroupName = "最新消息-";
				JSONArray recipientBccs =  new JSONArray();				
				// String recipients = "hisac-cs@mohw.gov.tw";
				String recipients = resourceMessageService.getMessageValue("globalFooterEmail");
				List<ViewSubscribeMember> viewSubscribeMembers = null;
				NewsManagementGroup newsManagementGroup = newsManagementGroupService.getById(entity.getNewsManagementGroupId());
				if(newsManagementGroup != null) {
					newsManagementGroupName = newsManagementGroupName + newsManagementGroup.getName();				
					viewSubscribeMembers = subscribeMemberService.getBySubscribeName(newsManagementGroupName);
					if (viewSubscribeMembers != null) {
						for (ViewSubscribeMember viewSubscribeMember : viewSubscribeMembers) {
							recipientBccs.put(viewSubscribeMember.getEmail());										
						}
					}					
				}
					
				String mailSubject_Subscribe = messageValue_SubscribeSubject;
				String mailBody_Subscribe = MessageFormat.format(messageValue_SubscribeBody, newsManagementGroupName,entity.getModifyTime(), entity.getTitle(), entity.getContent());
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(),recipients , null, recipientBccs, mailSubject_Subscribe, mailBody_Subscribe, null);												
				entity.setPostId(postId);
				entity.setPostDateTime(now);
			}

			// 3-審核中 → 6-編輯中(退回)
			if (status.equals("6")) {
				// 寄信
				// 收件者：entity.getEmail()
				// 主旨：H-ISAC最新消息(entity.getPostId())審核退回通知
				// 內容：entity.getName()，您好！您所新增之最新消息(entity.getPostId())，經H-ISAC內容審核者審核退回，請您儘快撥冗進行後續處理，謝謝！
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNews3To6Subject"), entity.getPostId());
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNews3To6Body"), member.getName(), entity.getPostId());
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
			}

			// 3-審核中 → 2-撤銷中
			if (status.equals("2")) {
				// 寄信
				// 收件者：entity.getEmail()
				// 主旨：H-ISAC最新消息(entity.getPostId())審核撤銷通知
				// 內容：entity.getName()，您好！您所新增之最新消息(entity.getPostId())，經H-ISAC內容審核者審核撤銷，請您儘快撥冗進行後續處理，謝謝！
				String mailSubject = MessageFormat.format(resourceMessageService.getMessageValue("mailNews3Back2Subject"), entity.getPostId());
				String mailBody = MessageFormat.format(resourceMessageService.getMessageValue("mailNews3Back2Body"), member.getName(), entity.getPostId());
				mailService.Send(this.getClass().getSimpleName() + " - " + Thread.currentThread().getStackTrace()[1].getMethodName(), member.getEmail(), member.getSpareEmail(), null, mailSubject, mailBody, null);
			}

			entity.setStatus(Long.parseLong(status));
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			newsManagementDAO.update(entity);
			resetGlobalData();
			resetGlobalDataPublic();
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 取得最新消息資料
	 * 
	 * @param id
	 *            最新消息資料Id
	 * @return 最新消息資料
	 */
	public ViewMessageAlertEvent getById(String id) {
		return newsManagementDAO.getById(id);
	}

	/**
	 * 取得最新消息資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 最新消息資料
	 */
	public List<ViewNewsManagementMember> getSpList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return newsManagementDAO.getSpList(obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得最新消息資料筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 最新消息資料筆數
	 */
	public long getSpListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return newsManagementDAO.getSpListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 取得最新消息資料Form筆數
	 * 
	 * @param json
	 *            查詢條件
	 * @return 最新消息資料筆數
	 */
	public long getSpFormCount(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return newsManagementDAO.getSpFormCount(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 取得最新消息資料button count資料
	 * 
	 * @param json
	 *            查詢條件
	 * @return 最新消息資料狀態筆數
	 */
	public List<SpButtonCount> getSpButtonCount(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return newsManagementDAO.getSpButtonCount(obj);
		} catch (Exception e) {
			return null;
		}
	}

}
