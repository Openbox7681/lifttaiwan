USE [hisac]
GO

auditScoreList??????
insert into resource_message values(N'分數1,分數2,分數3',1,'2021-08-11',1,'2021-08-11')

insert into hisac.dbo.[role] (id, Name, IsEnable, Sort, CreateId, CreateTime, ModifyId, ModifyTime)
values (20, '資安維護計畫稽核委員', 1, 20, 1, '2021-08-06 00:00:00' ,1, '2021-08-06 00:00:00')

insert into hisac.dbo.[role] (id, Name, IsEnable, Sort, CreateId, CreateTime, ModifyId, ModifyTime)
values (21, 'PMO', 1, 21, 1, '2021-08-24 00:00:00' ,1, '2021-08-24 00:00:00')

insert into hisac.dbo.[role] (id, Name, IsEnable, Sort, CreateId, CreateTime, ModifyId, ModifyTime)
values (22, '系統開發人員', 1, 22, 1, '2021-08-24 00:00:00' ,1, '2021-08-24 00:00:00')

/****** Alter Table Start ******/
ALTER TABLE dbo.maintain_plan_checkList_attach ADD PRIMARY KEY (Id);
ALTER TABLE dbo.maintain_plan_checkList_attach DROP CONSTRAINT FK__maintain___Maint__2B0043CC;

ALTER TABLE dbo.maintain_plan_questionnaire_attach ADD PRIMARY KEY (Id);
ALTER TABLE dbo.maintain_plan_questionnaire_attach DROP CONSTRAINT FK__maintain___Maint__2917FB5A;

ALTER TABLE dbo.maintain_plan_score_attach ADD PRIMARY KEY (Id)
ALTER TABLE dbo.maintain_plan_score_attach DROP CONSTRAINT FK__maintain___Maint__3C2ACFCE;
ALTER TABLE dbo.maintain_plan_score_attach ADD CommitteeId bigint NULL;

ALTER TABLE dbo.maintain_plan_result_attach ADD PRIMARY KEY (Id)
ALTER TABLE dbo.maintain_plan_result_attach DROP CONSTRAINT FK__maintain___Maint__3E131840;

ALTER TABLE dbo.maintain_plan_improvement_attach ADD PRIMARY KEY (Id);
ALTER TABLE dbo.maintain_plan_improvement_attach DROP CONSTRAINT FK__maintain___Maint__4F3DA442;
/****** Alter Table End ******/

/****** Create Table Start ******/
CREATE TABLE hisac.dbo.maintain_inspect (
	Id bigint IDENTITY(10000,1) PRIMARY KEY,
	Title nvarchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	[Year] varchar(4) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	Sdate datetime NULL,
	Edate datetime NULL,
	Status varchar(2) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	Item nvarchar(MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	[Member] nvarchar(MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CreateId bigint NOT NULL,
	CreateTime datetime NOT NULL,
	ModifyId bigint NOT NULL,
	ModifyTime datetime NOT NULL
)

CREATE TABLE hisac.dbo.maintain_inspect_member (
	Id bigint IDENTITY(1,1) PRIMARY KEY,
	MaintainInspectId bigint REFERENCES maintain_inspect(Id) NOT NULL,
	GroupId bigint NOT NULL,
	InspectStatus varchar(2) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	InspectMemberId bigint NULL,
	HospitalUploadSdate datetime NULL,
	HospitalUploadEdate datetime NULL,
	CommitteeUploadSdate datetime NULL,
	CommitteeUploadEdate datetime NULL,
	AllowHospitalPatch bit NULL
)

CREATE TABLE hisac.dbo.maintain_inspect_committee (
	Id bigint IDENTITY(1,1) PRIMARY KEY,
	MaintainInspectId bigint REFERENCES dbo.maintain_inspect(Id) NOT NULL,
	GroupId bigint NOT NULL,
	CommitteeId bigint REFERENCES dbo.[member](Id) NOT NULL,
	Status bit NULL,
	CreateId bigint NOT NULL,
	CreateTime datetime NOT NULL,
	ModifyId bigint NOT NULL,
	ModifyTime datetime NOT NULL
)

CREATE TABLE dbo.maintain_plan_selfEvaluation_sample_attach(
	Id bigint IDENTITY(1,1) PRIMARY KEY,
	FileName nvarchar(MAX) null,
	FileType nvarchar(128) null,
	FileHash varchar(64) null,
	FileSize bigint null,
	FileDesc nvarchar(MAX) null,
	FileContent varbinary(MAX) null,
	CreateId bigint not null,
	CreateTime datetime not null,
	ModifyId bigint not null,
	ModifyTime datetime not null
)

CREATE TABLE dbo.maintain_plan_selfEvaluation_attach(
	Id bigint IDENTITY(1,1) PRIMARY KEY,
	MaintainPlanId bigint not null,
	GroupId bigint not null,
	FileName nvarchar(MAX) null,
	FileType nvarchar(128) null,
	FileHash varchar(64) null,
	FileSize bigint null,
	FileDesc nvarchar(MAX) null,
	FileContent varbinary(MAX) null,
	CreateId bigint not null,
	CreateTime datetime not null,
	ModifyId bigint not null,
	ModifyTime datetime not null
)

CREATE TABLE dbo.maintain_plan_other_attach(
	Id bigint IDENTITY(1,1) PRIMARY KEY,
	MaintainPlanId bigint not null,
	GroupId bigint not null,
	FileName nvarchar(MAX) null,
	FileType nvarchar(128) null,
	FileHash varchar(64) null,
	FileSize bigint null,
	FileDesc nvarchar(MAX) null,
	FileContent varbinary(MAX) null,
	CreateId bigint not null,
	CreateTime datetime not null,
	ModifyId bigint not null,
	ModifyTime datetime not null
)

CREATE TABLE dbo.maintain_plan_reviewOpinion_attach(
	Id bigint IDENTITY(1,1) PRIMARY KEY,
	MaintainPlanId bigint not null,
	GroupId bigint not null,
	CommitteeId  bigint not null,
	FileName nvarchar(MAX) null,
	FileType nvarchar(128) null,
	FileHash varchar(64) null,
	FileSize bigint null,
	FileDesc nvarchar(MAX) null,
	FileContent varbinary(MAX) null,
	CreateId bigint not null,
	CreateTime datetime not null,
	ModifyId bigint not null,
	ModifyTime datetime not null
)
/****** Create Table End ******/

/****** Create View Start ******/
CREATE VIEW [dbo].[v_maintain_inspect_member_org] AS
SELECT
	dbo.maintain_inspect_member.Id, 
	dbo.maintain_inspect_member.MaintainInspectId, 
	dbo.maintain_inspect_member.GroupId, 
	dbo.org.Name as OrgName,
	dbo.maintain_inspect_member.HospitalUploadSdate,
	dbo.maintain_inspect_member.HospitalUploadEdate,
	dbo.maintain_inspect_member.CommitteeUploadSdate,
	dbo.maintain_inspect_member.CommitteeUploadEdate,
	dbo.maintain_inspect_member.InspectStatus,
	dbo.maintain_inspect_member.InspectMemberId,
	dbo.[member].Name as InspectMemberName,
	dbo.maintain_inspect_member.AllowHospitalPatch,
	dbo.maintain_inspect.Title,
	dbo.maintain_inspect.[Year],
	dbo.maintain_inspect.Status
FROM dbo.maintain_inspect_member
	INNER JOIN dbo.org ON dbo.maintain_inspect_member.GroupId = dbo.org.Id
    INNER JOIN dbo.maintain_inspect ON dbo.maintain_inspect_member.MaintainInspectId = dbo.maintain_inspect.Id 
    LEFT OUTER JOIN dbo.[member] ON dbo.maintain_inspect_member.InspectMemberId = dbo.[member].Id


CREATE VIEW [dbo].[v_maintain_inspect_committee_member_org] AS
SELECT 
	a.Id,
	a.CommitteeId,
	a.CommitteeMemberName,
	a.Status,
	a.MaintainInspectId,
	a.Title,	
	a.Year,
	a.GroupId,
	a.OrgName,
	a.OrgCode,
	a.InspectStatus,
	a.HospitalUploadSdate,
	a.HospitalUploadEdate,
	a.CommitteeUploadSdate,
	a.CommitteeUploadEdate,
	a.AllowHospitalPatch,
	dbo.maintain_plan_score_attach.Id AS FileId,
	dbo.maintain_plan_score_attach.FileName,
	dbo.maintain_plan_score_attach.FileSize,
	dbo.maintain_plan_score_attach.FileDesc,
	dbo.maintain_plan_reviewOpinion_attach.Id as ReviewOpinionFileId,
	dbo.maintain_plan_reviewOpinion_attach.FileName as ReviewOpinionFileName,
	dbo.maintain_plan_reviewOpinion_attach.FileSize as ReviewOpinionFileSize,
	dbo.maintain_plan_reviewOpinion_attach.FileDesc  as ReviewOpinionFileDesc
FROM (SELECT
	dbo.maintain_inspect_committee.Id,
	dbo.maintain_inspect_committee.CommitteeId,
	dbo.maintain_inspect_committee.Status,
	dbo.member.Name AS CommitteeMemberName,
	dbo.maintain_inspect_committee.MaintainInspectId,
	dbo.maintain_inspect.Title,	
	dbo.maintain_inspect.Year,
	dbo.maintain_inspect_committee.GroupId,
	dbo.org.Name AS OrgName,
	dbo.org.Code AS OrgCode,
	dbo.maintain_inspect_member.InspectStatus,
	dbo.maintain_inspect_member.HospitalUploadSdate,
	dbo.maintain_inspect_member.HospitalUploadEdate,
	dbo.maintain_inspect_member.CommitteeUploadSdate,
	dbo.maintain_inspect_member.CommitteeUploadEdate,
	dbo.maintain_inspect_member.AllowHospitalPatch
FROM dbo.maintain_inspect_committee
	INNER JOIN dbo.maintain_inspect ON dbo.maintain_inspect_committee.MaintainInspectId = dbo.maintain_inspect.Id
	INNER JOIN dbo.maintain_inspect_member ON dbo.maintain_inspect_committee.GroupId = dbo.maintain_inspect_member.GroupId
	AND dbo.maintain_inspect_committee.MaintainInspectId = dbo.maintain_inspect_member.MaintainInspectId
	INNER JOIN dbo.member ON dbo.maintain_inspect_committee.CommitteeId = dbo.member.Id
	INNER JOIN dbo.org ON dbo.maintain_inspect_member.GroupId = dbo.org.Id) AS a
LEFT OUTER JOIN dbo.maintain_plan_score_attach ON a.CommitteeId = dbo.maintain_plan_score_attach.CommitteeId
AND a.MaintainInspectId = dbo.maintain_plan_score_attach.MaintainPlanId
AND a.GroupId = dbo.maintain_plan_score_attach.GroupId
LEFT OUTER JOIN dbo.maintain_plan_reviewOpinion_attach ON a.CommitteeId = dbo.maintain_plan_reviewOpinion_attach.CommitteeId
AND a.MaintainInspectId = dbo.maintain_plan_reviewOpinion_attach.MaintainPlanId
AND a.GroupId = dbo.maintain_plan_reviewOpinion_attach.GroupId
/****** Create View End ******/
