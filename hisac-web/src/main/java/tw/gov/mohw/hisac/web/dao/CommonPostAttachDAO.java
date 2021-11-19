package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.CommonPostAttach;
import tw.gov.mohw.hisac.web.domain.ViewCommonPostAttachMember;

public interface CommonPostAttachDAO {
	public void insert(CommonPostAttach entity);
	public void update(CommonPostAttach entity);
	public void delete(CommonPostAttach entity);
	public CommonPostAttach get(Long id);
	public List<CommonPostAttach> getAll();
	public List<ViewCommonPostAttachMember> getAllByCommonPostId(long commonPostId);
	public long getListSize(JSONObject obj);
}
