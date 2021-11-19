package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.CommentDAO;
import tw.gov.mohw.hisac.web.domain.Comment;
import tw.gov.mohw.hisac.web.domain.ViewCommentMember;

/**
 * 留言服務
 */
@Service
public class CommentService{
    @Autowired
    CommentDAO commentDAO;

    /**
     * 取得所有留言資料
     * 
     * @return 所有留言資料
     */
    public List<Comment> getAll() {
        return commentDAO.getAll();
    }

    /**
     * 新增留言資料
     * 
     * @param memberId
     *          使用者Id
     * @param obj
     *          留言資料
     * @return 留言資料
     */
    public Comment insert(Long memberId, JSONObject obj){
        try{
            Long type = obj.isNull("Type") == true ? null : obj.getLong("Type");
            String articleId = obj.isNull("ArticleId") == true ? null : obj.getString("ArticleId");
            String comment = obj.isNull("Comment") == true ? null : obj.getString("Comment");
            boolean isHideName = obj.isNull("IsHideName") == true ? false : obj.getBoolean("IsHideName");           

            Date now = new Date();
            Comment entity = new Comment();
            entity.setType(type);
            entity.setArticleId(articleId);
            entity.setComment(comment);

            entity.setCreateId(memberId);
            entity.setCreateTime(now);
            entity.setIsHideName(isHideName);
            
            commentDAO.insert(entity);

            return entity;
        }catch(Exception err){
            return null;
        }
    }

    /**
     * 更新留言資料
     * 
     * @param memberId
     *          使用者Id
     * @param json
     *          更新資料
     * @return 更新資料
     */
    public Comment update(Long memberId, String json) {
        try{
            JSONObject obj = new JSONObject(json);
            Long id = obj.isNull("Id") == true ? null : obj.getLong("Id");
            Long type = obj.isNull("Type") == true ? null : obj.getLong("Type");
            String articleId = obj.isNull("ArticleId") == true ? null : obj.getString("ArticleId");
            String comment = obj.isNull("Comment") == true ? null : obj.getString("Comment");
            boolean isHideName = obj.isNull("IsHideName") == true ? false : obj.getBoolean("IsHideName");

            Comment entity = commentDAO.get(id);
            entity.setType(type);
            entity.setArticleId(articleId);
            entity.setComment(comment);
            entity.setIsHideName(isHideName);

            commentDAO.update(entity);

            return entity;
        }catch(Exception err){
            return null;
        }
    }

    /**
     * 取得單一留言資料
     * 
     * @param id
     *          留言資料ID
     * @return 留言資料
     */
    public Comment findById(Long id) {
        return commentDAO.get(id);
    }

    /**
     * 取得單一留言資料
     * 
     * @param type
     *          post type ID
     * @param articleId
     *          文章ID
     * @param createId
     *          建立者ID
     * @return 留言資料
     */
    public Comment findById(Long type, String articleId, Long createId) {
        return commentDAO.get(type, articleId, createId);
    }

    /**
     * 留言資料是否存在
     * 
     * @param id
     *         留言資料ID 
     * @return 是否存在
     */
    public Boolean isExist(Long id) {
        return commentDAO.get(id) != null;
    }

    /**
     * 留言資料是否存在
     * 
     * @param type
     *          post type ID
     * @param articleId
     *          文章ID
     * @param createId
     *          建立者ID
     * @return 是否存在
     */
    public Boolean isExist(Long type, String articleId, Long createId) {
        return commentDAO.get(type, articleId, createId) != null;
    }
    
    
    /**
     * 取得所有留言
     * 
     * @param type
     *          post type ID
     * @param articleId
     *          文章ID
     * @return 所有留言
     */
    public List<ViewCommentMember> getByArticleComments(Long type, String articleId){
        try{
            List<ViewCommentMember> list = commentDAO.getByTypeAndArticleId(type, articleId);           
            	return list;                                         
        }catch(Exception err){
            return null;
        }
    }
    
    
    /**
     * 刪除留言
     * 
     * @param id
     *         留言資料ID 
     * @return 是否成功
     */
    public Boolean delete(Long id) {
        try { 
        		commentDAO.delete(commentDAO.get(id));
        		return true;      
        }catch(Exception err){
            return false;
        }
    }
}