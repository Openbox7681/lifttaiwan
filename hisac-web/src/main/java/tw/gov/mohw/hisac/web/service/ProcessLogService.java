package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.gov.mohw.hisac.web.dao.ProcessLogDAO;
import tw.gov.mohw.hisac.web.domain.ProcessLog;
import tw.gov.mohw.hisac.web.domain.ViewProcessLogMember;

/**
 * MessageProcessLog服務
 */
@Service
public class ProcessLogService {
	@Autowired
	ProcessLogDAO processLogDAO;

	/**
	 * 取得所有MessageProcessLog
	 * 
	 * @return MessageProcessLog
	 */
	public List<ProcessLog> getAll() {
		return processLogDAO.getAll();
	}

	/**
	 * 新增MessageProcessLog資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            MessageProcessLog資料
	 * @param postId
	 *            postId
	 * @return MessageProcessLog資料
	 */
	public ProcessLog insert(Long memberId, String json, String postId) {
		try {
			JSONObject obj = new JSONObject(json);
			String tableName = obj.isNull("TableName") == true ? null : obj.getString("TableName");
			String opinion = obj.isNull("Opinion") == true ? null : obj.getString("Opinion");
			List<ViewProcessLogMember> viewProcessLogMembers = getByPostId(postId, tableName);

			Date now = new Date();
			ProcessLog entity = new ProcessLog();
			if (viewProcessLogMembers != null)
				entity.setPreId(viewProcessLogMembers.get(0).getCreateId());
			else
				entity.setPreId(memberId);
			entity.setPostId(postId);
			entity.setTableName(tableName);
			try {
				String preStatus = obj.isNull("PreStatus") == true ? null : obj.getString("PreStatus");
				String status = obj.isNull("Status") == true ? null : obj.getString("Status");
				entity.setPreStatus(preStatus);
				entity.setStatus(status);
			} catch (Exception e) {
				int preStatus = obj.isNull("PreStatus") == true ? 0 : obj.getInt("PreStatus");
				int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");
				entity.setPreStatus(Integer.toString(preStatus));
				entity.setStatus(Integer.toString(status));
			}
			entity.setOpinion(opinion);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);

			processLogDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 新增MessageProcessLog資料
	 * 
	 * @param memberId
	 *            使用者Id
	 * @param json
	 *            MessageProcessLog資料
	 * @param postId
	 *            postId
	 * @return MessageProcessLog資料
	 */
	public ProcessLog insertInspect(Long memberId, String json, String postId) {
		try {
			JSONObject obj = new JSONObject(json);
			String tableName = obj.isNull("TableName") == true ? null : obj.getString("TableName");
			String opinion = obj.isNull("Opinion") == true ? null : obj.getString("Opinion");

			Date now = new Date();
			ProcessLog entity = new ProcessLog();
			entity.setPreId(memberId);
			entity.setPostId(postId);
			entity.setTableName(tableName);
			try {
				String preStatus = obj.isNull("PreStatus") == true ? null : obj.getString("PreStatus");
				String status = obj.isNull("Status") == true ? null : obj.getString("Status");
				entity.setPreStatus(preStatus);
				entity.setStatus(status);
			} catch (Exception e) {
				int preStatus = obj.isNull("PreStatus") == true ? 0 : obj.getInt("PreStatus");
				int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");
				entity.setPreStatus(Integer.toString(preStatus));
				entity.setStatus(Integer.toString(status));
			}
			entity.setOpinion(opinion);
			entity.setCreateId(memberId);
			entity.setCreateTime(now);

			processLogDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 取得MessageProcessLog資料
	 * 
	 * @param postId
	 *            postId
	 * @param tableName
	 *            tableName
	 * @return MessageProcessLog資料
	 */
	public List<ViewProcessLogMember> getByPostId(String postId, String tableName) {
		try {
			return processLogDAO.getByPostId(postId, tableName);
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
}
