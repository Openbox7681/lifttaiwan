package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import tw.gov.mohw.hisac.web.domain.ViewMenuLimit;

public interface ViewMenuLimitDAO {
	public List<ViewMenuLimit> getMenu(Long memberId);
	
	//利用帳號與表單取得表單對應權限
	public ViewMenuLimit getMenuByIdAndName(Long memberId, String formName);

	
	public ViewMenuLimit getAction(Long memberId, String controllerName, String actionName);
}