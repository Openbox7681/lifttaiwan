package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.WebCrypto;
import tw.gov.mohw.hisac.web.dao.MessagePostDAO;
import tw.gov.mohw.hisac.web.domain.MessagePost;

/**
 * MessagePost服務
 */
@Service
public class MessagePostService {
	@Autowired
	MessagePostDAO messagePostDAO;

	/**
	 * 取得所有MessagePost
	 * 
	 * @return MessagePost
	 */
	public List<MessagePost> getAll() {
		return messagePostDAO.getAll();
	}

	/**
	 * 新增MessagePost
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            MessagePost
	 * @param messageId
	 *            messageId
	 * @return 是否成功
	 */
	public Boolean insert(Long memberId, String json, String messageId) {
		try {
			JSONObject obj = new JSONObject(json);			
			JSONArray memberList = obj.getJSONArray("MemberList");			
			JSONArray recipientList = obj.getJSONArray("RecipientList");			
			JSONArray replyList = obj.getJSONArray("ReplyList");
			JSONArray sn_array = new JSONArray();			
			for (int i = 0; i < memberList.length(); i++) {
				JSONObject sn_json = new JSONObject();
				JSONObject objOrg = memberList.getJSONObject(i);
				if (objOrg.getBoolean("Action")) {
					sn_json.put("PostType", "7");
					sn_json.put("PostContent", Long.toString(objOrg.getLong("Id")));
					sn_json.put("PostName", "");
					sn_json.put("PostMobilePhone", "");
					sn_array.put(sn_json);
				}
			}		
			for (int i = 0; i < recipientList.length(); i++) {
				JSONObject sn_json = new JSONObject();
				JSONObject objRecipientList = recipientList.getJSONObject(i);
				sn_json.put("PostType", "4");
				sn_json.put("PostContent", objRecipientList.getString("RecipientEmail"));
				sn_json.put("PostName", objRecipientList.getString("RecipientName"));
				sn_json.put("PostMobilePhone", objRecipientList.getString("RecipientMobilePhone"));
				sn_array.put(sn_json);
			}			
			for (int i = 0; i < replyList.length(); i++) {
				JSONObject sn_json = new JSONObject();
				JSONObject objReplyList = replyList.getJSONObject(i);
				sn_json.put("PostType", "6");
				sn_json.put("PostContent", objReplyList.getString("ReplyOption"));
				sn_json.put("PostName", "");
				sn_json.put("PostMobilePhone", "");
				sn_array.put(sn_json);
			}

			Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
			Date now = new Date();
			for (int i = 0; i < sn_array.length(); i++) {
				String postType = sn_array.getJSONObject(i).getString("PostType");
				String postContent = sn_array.getJSONObject(i).getString("PostContent");
				String postName = sn_array.getJSONObject(i).getString("PostName");
				String postMobilePhone = sn_array.getJSONObject(i).getString("PostMobilePhone");
				MessagePost entity = new MessagePost();
				entity.setId(WebCrypto.generateUUID());
				entity.setPostType(postType);
				entity.setPostContent(postContent);
				entity.setPostName(postName);
				entity.setPostMobilePhone(postMobilePhone);
				entity.setIsEnable(isEnable);
				entity.setMessageId(messageId);
				entity.setCreateId(memberId);
				entity.setCreateTime(now);
				entity.setModifyId(memberId);
				entity.setModifyTime(now);
				messagePostDAO.insert(entity);
			}
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 更新messagePost
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            messagePost Id
	 * @return messagePost
	 */
	public MessagePost update(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String id = obj.isNull("Id") == true ? null : obj.getString("Id");
			String postType = obj.isNull("PostType") == true ? null : obj.getString("PostType");
			String postContent = obj.isNull("PostContent") == true ? null : obj.getString("PostContent");
			Boolean isEnable = obj.isNull("IsEnable") == true ? null : obj.getBoolean("IsEnable");
			String messageId = obj.isNull("MessageId") == true ? null : obj.getString("MessageId");

			Date now = new Date();
			MessagePost entity = messagePostDAO.get(id);
			entity.setPostType(postType);
			entity.setPostContent(postContent);
			entity.setIsEnable(isEnable);
			entity.setMessageId(messageId);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			messagePostDAO.update(entity);

			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	/**
	 * 刪除messagePost
	 * 
	 * @param id
	 *            messagePost Id
	 * @return 是否刪除成功
	 */
	public boolean delete(String id) {
		try {
			MessagePost entity = messagePostDAO.get(id);
			messagePostDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 取得MessagePost
	 * 
	 * @param messageId
	 *            messageId
	 * @return MessagePost
	 */
	public List<MessagePost> getBymessageId(String messageId) {
		return messagePostDAO.getBymessageId(messageId);
	}
	
	/**
	 * 取得MessagePost
	 * 
	 * @param messageId
	 *            messageId
	 * @param postType
	 *            postType
	 * @return MessagePost
	 */
	public List<MessagePost> getBymessageIdAndPostType(String messageId, String postType) {
		return messagePostDAO.getBymessageIdAndPostType(messageId, postType);
	}

	/**
	 * 刪除messagePost
	 * 
	 * @param messageId
	 *            messagePost Id
	 * @return 是否刪除成功
	 */
	public boolean deleteBymessageid(String messageId) {
		try {
			List<MessagePost> messagePosts = messagePostDAO.getBymessageId(messageId);
			if (messagePosts != null)
				for (MessagePost messagePost : messagePosts)
					messagePostDAO.delete(messagePost);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
}
