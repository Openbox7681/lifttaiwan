package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.ArticleData;

public interface ArticleDataDAO {
	public void insert(ArticleData entity);
	public void update(ArticleData entity);
	public void delete(ArticleData entity);
	public ArticleData get(Long id);
	public List<ArticleData> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
	public List<ArticleData> getAll();
}
