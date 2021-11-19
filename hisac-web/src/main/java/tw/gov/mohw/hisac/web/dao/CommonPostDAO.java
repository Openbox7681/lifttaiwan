package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.CommonPost;
import tw.gov.mohw.hisac.web.domain.ViewCommonPostMember;

public interface CommonPostDAO {
	public void insert(CommonPost entity);
	public void update(CommonPost entity);
	public void delete(CommonPost entity);
	public CommonPost get(Long id);
	public List<CommonPost> getAll();
	public CommonPost getByTitle(String title);
	public List<ViewCommonPostMember> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
	public CommonPost findForPub(long postType);	// 搜尋 IsEnable 為 true && 日期區間
}
