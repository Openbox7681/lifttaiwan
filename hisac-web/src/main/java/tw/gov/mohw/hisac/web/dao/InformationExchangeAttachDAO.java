package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import tw.gov.mohw.hisac.web.domain.InformationExchangeAttach;

public interface InformationExchangeAttachDAO {

	public void insert(InformationExchangeAttach entity);
	public void update(InformationExchangeAttach entity);
	public void delete(InformationExchangeAttach entity);
	public InformationExchangeAttach get(Long id);
	public List<InformationExchangeAttach> getByInfoExId(String informationExchangeId);
	public List<InformationExchangeAttach> getByInformationId(String informationExchangeId);
}
