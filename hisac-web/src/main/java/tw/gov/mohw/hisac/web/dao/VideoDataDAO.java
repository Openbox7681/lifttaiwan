package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.VideoData;

public interface VideoDataDAO {
	public void insert(VideoData entity);
	public void update(VideoData entity);
	public void delete(VideoData entity);
	public VideoData get(Long id);
	public List<VideoData> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
	public List<VideoData> getAll();
}
