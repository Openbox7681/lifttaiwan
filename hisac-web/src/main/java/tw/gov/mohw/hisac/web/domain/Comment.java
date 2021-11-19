package tw.gov.mohw.hisac.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comment", uniqueConstraints = {@UniqueConstraint(columnNames = "Id")})
public class Comment {
    private Long id;
    private @NotNull Long type;
    private @NotNull String articleId;
    private @NotNull String comment;
    private @NotNull Long createId;
    private @NotNull Date createTime;
    private @NotNull Boolean isHideName;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, insertable = false, updatable = false)
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }

    @Column(name = "Type", nullable = false)
    public Long getType(){
        return this.type;
    }
    public void setType(Long type){
        this.type = type;
    }

    @Column(name = "ArticleId", nullable = false)
    public String getArticleId(){
        return this.articleId;
    }
    public void setArticleId(String articleId){
        this.articleId = articleId;
    }

    @Column(name = "Comment", nullable = false)
    public String getComment(){
        return this.comment;
    }
    public void setComment(String comment){
        this.comment = comment;
    }

    @Column(name = "CreateId", nullable = false, updatable = false)
    public Long getCreateId(){
        return this.createId;
    }
    public void setCreateId(Long createId){
        this.createId = createId;
    }

    @Column(name = "CreateTime", nullable = false, updatable = false)
    public Date getCreateTime(){
        return this.createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    
    @Column(name = "IsHideName", nullable = false)
	public Boolean getIsHideName() {
		return isHideName;
	}
	public void setIsHideName(Boolean isHideName) {
		this.isHideName = isHideName;
	}
}