package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.Links;
import tw.gov.mohw.hisac.web.domain.ViewLinksMember;

public interface LinksDAO {
	public void insert(Links entity);
	public void update(Links entity);
	public void delete(Links entity);
	public Links get(Long id);
	public List<Links> getAll();
	public List<ViewLinksMember> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
}