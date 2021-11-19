package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.CommonPostPic;
import tw.gov.mohw.hisac.web.domain.ViewCommonPostPicMember;

public interface CommonPostPicDAO {
	public void insert(CommonPostPic entity);
	public void update(CommonPostPic entity);
	public void delete(CommonPostPic entity);
	public CommonPostPic get(Long id);
	public List<CommonPostPic> getAll();
	public List<ViewCommonPostPicMember> getAllByCommonPostId(long commonPostId);
	public long getListSize(JSONObject obj);
}
