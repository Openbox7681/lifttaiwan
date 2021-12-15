
package tw.gov.mohw.hisac.web.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.VideoDataDAO;
import tw.gov.mohw.hisac.web.domain.VideoData;

/**
 * 表單資料維護服務
 */
@Service
public class VideoDataService {
	final static Logger logger = LoggerFactory.getLogger(MemberService.class);

	@Autowired
	VideoDataDAO videoDataDAO;

	public List<VideoData> getAll() {
		return videoDataDAO.getAll();
	}

	public boolean isExist(Long id) {
		return videoDataDAO.get(id) != null;
	}
	
	public List<VideoData> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return videoDataDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return videoDataDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	public VideoData insert(Long memberId, String json) {
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			JSONObject obj = new JSONObject(json);
			String title = obj.isNull("Title") == true ? null : obj.getString("Title");
			String video_url = obj.isNull("Video_Url") == true ? null : obj.getString("Video_Url");
			String thumbnail_Url = obj.isNull("Thumbnail_Url") == true ? null : obj.getString("Thumbnail_Url");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			boolean isShow = obj.isNull("IsShow") == true ? false : obj.getBoolean("IsShow");
			Date now = new Date();
			VideoData entity = new VideoData();
			entity.setTitle(title);
			entity.setVideo_Url(video_url);
			entity.setThumbnail_Url(thumbnail_Url);
			entity.setIsEnable(isEnable);
			entity.setIsShow(isShow);
			entity.setCreateId(memberId);
			entity.setCreateTime(sdf2.parse(sdf2.format(now)));
			entity.setModifyId(memberId);
			entity.setModifyTime(now);

			videoDataDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	public VideoData update(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String title = obj.isNull("Title") == true ? null : obj.getString("Title");
			String video_url = obj.isNull("Video_Url") == true ? null : obj.getString("Video_Url");
			String thumbnail_Url = obj.isNull("Thumbnail_Url") == true ? null : obj.getString("Thumbnail_Url");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			boolean isShow = obj.isNull("IsShow") == true ? false : obj.getBoolean("IsShow");
			Date now = new Date();

			VideoData entity = videoDataDAO.get(id);
			entity.setTitle(title);
			entity.setVideo_Url(video_url);
			entity.setThumbnail_Url(thumbnail_Url);
			entity.setIsEnable(isEnable);
			entity.setIsShow(isShow);
			entity.setModifyId(memberId);
			entity.setModifyTime(now);
			videoDataDAO.update(entity);

			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	public boolean delete(Long id) {
		try {
			VideoData entity = videoDataDAO.get(id);
			videoDataDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	public VideoData get(Long id) {
		return videoDataDAO.get(id);
	}

}