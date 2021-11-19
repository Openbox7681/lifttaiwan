package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.LinksPic;
//import tw.gov.mohw.hisac.web.domain.ViewCommonPostPicMember;
import tw.gov.mohw.hisac.web.domain.ViewLinksPicMember;

public interface LinksPicDAO {
	public void insert(LinksPic entity);
	public void update(LinksPic entity);
	public void delete(LinksPic entity);
	public LinksPic get(Long id);
	public List<LinksPic> getAll();
	public List<ViewLinksPicMember> getAllByLinksId(long linksId);
	public long getListSize(JSONObject obj);
}
