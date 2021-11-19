package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import tw.gov.mohw.hisac.web.domain.ViewMenuLimit;

public interface ViewMenuLimitDAO {
	public List<ViewMenuLimit> getMenu(Long memberId);
	public ViewMenuLimit getAction(Long memberId, String controllerName, String actionName);
}