package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.InformationManagementPic;

public interface InformationManagementPicDAO {
	public void insert(InformationManagementPic entity);
	public void update(InformationManagementPic entity);
	public void delete(InformationManagementPic entity);
	public InformationManagementPic get(Long id);
	public List<InformationManagementPic> getAll();
	public List<InformationManagementPic> getAllByInformationManagementId(long informationManagementId);
	public long getListSize(JSONObject obj);
}
