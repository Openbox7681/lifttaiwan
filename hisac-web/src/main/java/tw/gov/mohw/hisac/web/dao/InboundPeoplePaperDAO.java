package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.ViewInboundPeoplePaper;


public interface InboundPeoplePaperDAO {
	public void insert(ViewInboundPeoplePaper entity);
	public void update(ViewInboundPeoplePaper entity);
	public void delete(ViewInboundPeoplePaper entity);
	public ViewInboundPeoplePaper get(Long id);
	public List<ViewInboundPeoplePaper> getAll();
	public ViewInboundPeoplePaper getByCode(String code);
	public ViewInboundPeoplePaper getByName(String name);
	public List<ViewInboundPeoplePaper> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
	public List<Object[]> getLineData();
}
