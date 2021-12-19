package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.ViewInboundPeoplePaperNoCor;


public interface InboundPeoplePaperNoCorDAO {
	public void insert(ViewInboundPeoplePaperNoCor entity);
	public void update(ViewInboundPeoplePaperNoCor entity);
	public void delete(ViewInboundPeoplePaperNoCor entity);
	public ViewInboundPeoplePaperNoCor get(Long id);
	public List<ViewInboundPeoplePaperNoCor> getAll();
	public ViewInboundPeoplePaperNoCor getByCode(String code);
	public ViewInboundPeoplePaperNoCor getByName(String name);
	public List<ViewInboundPeoplePaperNoCor> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
	public List<Object[]> getLineData();
}
