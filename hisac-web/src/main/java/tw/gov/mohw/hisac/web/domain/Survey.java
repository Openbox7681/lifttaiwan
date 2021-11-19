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
@Table(name = "survey", uniqueConstraints = {@UniqueConstraint(columnNames = "Id")})
public class Survey {

	private Long id;
	private @NotNull Integer surveyPublic01;
	private String surveyPublic01Suggest;
	private @NotNull Integer surveyPublic02;
	private String surveyPublic02Suggest;
	private @NotNull Integer surveyPublic03;
	private String surveyPublic03Suggest;
	private @NotNull Integer surveyNotify01;
	private String surveyNotify01Suggest;
	private @NotNull Integer surveyNotify02;
	private String surveyNotify02Suggest;
	private @NotNull Integer surveyNotify03;
	private String surveyNotify03Suggest;
	private @NotNull Integer surveyAlert01;
	private String surveyAlert01Suggest;
	private @NotNull Integer surveyAlert02;
	private String surveyAlert02Suggest;
	private @NotNull Integer surveyAlert03;
	private String surveyAlert03Suggest;
	private @NotNull Integer surveyCheck01;
	private String surveyCheck01Suggest;
	private @NotNull Integer surveyCheck02;
	private String surveyCheck02Suggest;
	private @NotNull Integer surveyCheck03;
	private String surveyCheck03Suggest;
	private @NotNull Integer surveyTotal01;
	private String surveyTotal01Suggest;
	private String surveySuggest;
	private @NotNull Long createId;
	private @NotNull Date createTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, insertable = false, updatable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "SurveyPublic01", nullable = false)
	public Integer getSurveyPublic01() {
		return surveyPublic01;
	}
	public void setSurveyPublic01(Integer surveyPublic01) {
		this.surveyPublic01 = surveyPublic01;
	}
	@Column(name = "SurveyPublic01Suggest", nullable = false)
	public String getSurveyPublic01Suggest() {
		return surveyPublic01Suggest;
	}
	public void setSurveyPublic01Suggest(String surveyPublic01Suggest) {
		this.surveyPublic01Suggest = surveyPublic01Suggest;
	}

	@Column(name = "SurveyPublic02", nullable = false)
	public Integer getSurveyPublic02() {
		return surveyPublic02;
	}
	public void setSurveyPublic02(Integer surveyPublic02) {
		this.surveyPublic02 = surveyPublic02;
	}
	@Column(name = "SurveyPublic02Suggest", nullable = false)
	public String getSurveyPublic02Suggest() {
		return surveyPublic02Suggest;
	}
	public void setSurveyPublic02Suggest(String surveyPublic02Suggest) {
		this.surveyPublic02Suggest = surveyPublic02Suggest;
	}
	
	@Column(name = "SurveyPublic03", nullable = false)
	public Integer getSurveyPublic03() {
		return surveyPublic03;
	}
	public void setSurveyPublic03(Integer surveyPublic03) {
		this.surveyPublic03 = surveyPublic03;
	}
	@Column(name = "SurveyPublic03Suggest", nullable = false)
	public String getSurveyPublic03Suggest() {
		return surveyPublic03Suggest;
	}
	public void setSurveyPublic03Suggest(String surveyPublic03Suggest) {
		this.surveyPublic03Suggest = surveyPublic03Suggest;
	}

	@Column(name = "SurveyNotify01", nullable = false)
	public Integer getSurveyNotify01() {
		return surveyNotify01;
	}
	public void setSurveyNotify01(Integer surveyNotify01) {
		this.surveyNotify01 = surveyNotify01;
	}
	@Column(name = "SurveyNotify01Suggest", nullable = false)
	public String getSurveyNotify01Suggest() {
		return surveyNotify01Suggest;
	}
	public void setSurveyNotify01Suggest(String surveyNotify01Suggest) {
		this.surveyNotify01Suggest = surveyNotify01Suggest;
	}

	@Column(name = "SurveyNotify02", nullable = false)
	public Integer getSurveyNotify02() {
		return surveyNotify02;
	}
	public void setSurveyNotify02(Integer surveyNotify02) {
		this.surveyNotify02 = surveyNotify02;
	}
	@Column(name = "SurveyNotify02Suggest", nullable = false)
	public String getSurveyNotify02Suggest() {
		return surveyNotify02Suggest;
	}
	public void setSurveyNotify02Suggest(String surveyNotify02Suggest) {
		this.surveyNotify02Suggest = surveyNotify02Suggest;
	}
	
	@Column(name = "SurveyNotify03", nullable = false)
	public Integer getSurveyNotify03() {
		return surveyNotify03;
	}
	public void setSurveyNotify03(Integer surveyNotify03) {
		this.surveyNotify03 = surveyNotify03;
	}
	@Column(name = "SurveyNotify03Suggest", nullable = false)
	public String getSurveyNotify03Suggest() {
		return surveyNotify03Suggest;
	}
	public void setSurveyNotify03Suggest(String surveyNotify03Suggest) {
		this.surveyNotify03Suggest = surveyNotify03Suggest;
	}

	@Column(name = "SurveyAlert01", nullable = false)
	public Integer getSurveyAlert01() {
		return surveyAlert01;
	}
	public void setSurveyAlert01(Integer surveyAlert01) {
		this.surveyAlert01 = surveyAlert01;
	}
	@Column(name = "SurveyAlert01Suggest", nullable = false)
	public String getSurveyAlert01Suggest() {
		return surveyAlert01Suggest;
	}
	public void setSurveyAlert01Suggest(String surveyAlert01Suggest) {
		this.surveyAlert01Suggest = surveyAlert01Suggest;
	}

	@Column(name = "SurveyAlert02", nullable = false)
	public Integer getSurveyAlert02() {
		return surveyAlert02;
	}
	public void setSurveyAlert02(Integer surveyAlert02) {
		this.surveyAlert02 = surveyAlert02;
	}
	@Column(name = "SurveyAlert02Suggest", nullable = false)
	public String getSurveyAlert02Suggest() {
		return surveyAlert02Suggest;
	}
	public void setSurveyAlert02Suggest(String surveyAlert02Suggest) {
		this.surveyAlert02Suggest = surveyAlert02Suggest;
	}
	
	@Column(name = "SurveyAlert03", nullable = false)
	public Integer getSurveyAlert03() {
		return surveyAlert03;
	}
	public void setSurveyAlert03(Integer surveyAlert03) {
		this.surveyAlert03 = surveyAlert03;
	}
	@Column(name = "SurveyAlert03Suggest", nullable = false)
	public String getSurveyAlert03Suggest() {
		return surveyAlert03Suggest;
	}
	public void setSurveyAlert03Suggest(String surveyAlert03Suggest) {
		this.surveyAlert03Suggest = surveyAlert03Suggest;
	}

	@Column(name = "SurveyCheck01", nullable = false)
	public Integer getSurveyCheck01() {
		return surveyCheck01;
	}
	public void setSurveyCheck01(Integer surveyCheck01) {
		this.surveyCheck01 = surveyCheck01;
	}
	@Column(name = "SurveyCheck01Suggest", nullable = false)
	public String getSurveyCheck01Suggest() {
		return surveyCheck01Suggest;
	}
	public void setSurveyCheck01Suggest(String surveyCheck01Suggest) {
		this.surveyCheck01Suggest = surveyCheck01Suggest;
	}

	@Column(name = "SurveyCheck02", nullable = false)
	public Integer getSurveyCheck02() {
		return surveyCheck02;
	}
	public void setSurveyCheck02(Integer surveyCheck02) {
		this.surveyCheck02 = surveyCheck02;
	}
	@Column(name = "SurveyCheck02Suggest", nullable = false)
	public String getSurveyCheck02Suggest() {
		return surveyCheck02Suggest;
	}
	public void setSurveyCheck02Suggest(String surveyCheck02Suggest) {
		this.surveyCheck02Suggest = surveyCheck02Suggest;
	}
	
	@Column(name = "SurveyCheck03", nullable = false)
	public Integer getSurveyCheck03() {
		return surveyCheck03;
	}
	public void setSurveyCheck03(Integer surveyCheck03) {
		this.surveyCheck03 = surveyCheck03;
	}
	@Column(name = "SurveyCheck03Suggest", nullable = false)
	public String getSurveyCheck03Suggest() {
		return surveyCheck03Suggest;
	}
	public void setSurveyCheck03Suggest(String surveyCheck03Suggest) {
		this.surveyCheck03Suggest = surveyCheck03Suggest;
	}
	
	@Column(name = "SurveyTotal01", nullable = false)
	public Integer getSurveyTotal01() {
		return surveyTotal01;
	}
	public void setSurveyTotal01(Integer surveyTotal01) {
		this.surveyTotal01 = surveyTotal01;
	}
	@Column(name = "SurveyTotal01Suggest", nullable = false)
	public String getSurveyTotal01Suggest() {
		return surveyTotal01Suggest;
	}
	public void setSurveyTotal01Suggest(String surveyTotal01Suggest) {
		this.surveyTotal01Suggest = surveyTotal01Suggest;
	}
	
	@Column(name = "SurveySuggest", nullable = false)
	public String getSurveySuggest() {
		return surveySuggest;
	}
	public void setSurveySuggest(String surveySuggest) {
		this.surveySuggest = surveySuggest;
	}

	@Column(name = "CreateId", nullable = false)
	public Long getCreateId() {
		return createId;
	}
	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	@Column(name = "CreateTime", nullable = false)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
