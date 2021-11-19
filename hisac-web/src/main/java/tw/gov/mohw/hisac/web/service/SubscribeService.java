package tw.gov.mohw.hisac.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.SubscribeDAO;
import tw.gov.mohw.hisac.web.domain.Subscribe;

/**
 * 警訊種類服務
 */
@Service
public class SubscribeService {
	@Autowired
	SubscribeDAO subscribeDAO;

	/**
	 * 取得所有訂閱種類資料
	 * 
	 * @return Subscribe List
	 */
	public List<Subscribe> getAll() {
		List<Subscribe> entitys = subscribeDAO.getAll();
		return entitys;
	}
}
