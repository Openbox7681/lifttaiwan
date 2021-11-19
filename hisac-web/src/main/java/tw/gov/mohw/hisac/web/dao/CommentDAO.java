package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import tw.gov.mohw.hisac.web.domain.Comment;
import tw.gov.mohw.hisac.web.domain.ViewCommentMember;

public interface CommentDAO{
    public void insert(Comment entity);
    public void update(Comment entity);
    public void delete(Comment entity);
    public Comment get(Long id);
    public Comment get(Long type, String articleId, Long createId);
    public List<ViewCommentMember> getByTypeAndArticleId(Long type, String articleId);
    public List<Comment> getAll();
}