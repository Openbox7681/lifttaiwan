package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.gov.mohw.hisac.web.domain.Comment;
import tw.gov.mohw.hisac.web.domain.ViewCommentMember;

@Repository
@Transactional
public class CommentDAOImpl extends BaseSessionFactory implements CommentDAO{

    public void insert(Comment entity){
        getSessionFactory().getCurrentSession().save(entity);
    }

    public void update(Comment entity){
        getSessionFactory().getCurrentSession().update(entity);
    }

    public void delete(Comment entity){
        getSessionFactory().getCurrentSession().delete(entity);
    }

    public Comment get(Long id){
        return getSessionFactory().getCurrentSession().get(Comment.class, id);
    }

    @SuppressWarnings({"deprecation"})
    public Comment get(Long type, String articleId, Long createId){
        Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Comment.class);
        cr.add(Restrictions.eq("type", type));
        cr.add(Restrictions.eq("articleId", articleId));
        cr.add(Restrictions.eq("createId", createId));
        
        return (Comment) cr.uniqueResult();
    }

    @SuppressWarnings({"deprecation", "unchecked"})
    public List<Comment> getAll(){
        Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Comment.class);

        List<Comment> list = cr.list();
        if (list.size() > 0) {
            return list;
        }else{
            return null;
        }
    }

    @SuppressWarnings({"deprecation", "unchecked"})
    public List<ViewCommentMember> getByTypeAndArticleId(Long type, String articleId){
        Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewCommentMember.class);
        cr.add(Restrictions.eq("type", type));
        cr.add(Restrictions.eq("articleId", articleId));
        cr.addOrder(Order.desc("createTime"));
        List<ViewCommentMember> list = cr.list();
        if (list.size() > 0) {
            return list;
        }else{
            return null;
        }
    }
}