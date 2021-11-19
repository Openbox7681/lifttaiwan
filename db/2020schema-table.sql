-- Drop table

-- DROP TABLE hisac.dbo.maintain_plan_improvement_attach

CREATE TABLE hisac.dbo.maintain_plan_improvement_suggest_attach (
	Id bigint NOT NULL IDENTITY(1,1),
	MaintainPlanId bigint NOT NULL,
	FileName nvarchar(256),
	FileType nvarchar(128),
	FileHash varchar(64),
	FileSize bigint,
	FileDesc nvarchar(256),
	CreateId bigint NOT NULL,
	CreateTime DATETIME NOT NULL,
	ModifyId bigint NOT NULL,
	ModifyTime DATETIME NOT NULL,
	FileContent varbinary,
	FOREIGN KEY (MaintainPlanId) REFERENCES hisac.dbo.maintain_plan(Id) 
) 



CREATE TABLE hisac.dbo.maintain_plan_implement_attach (
	Id bigint NOT NULL IDENTITY(1,1),
	MaintainPlanId bigint NOT NULL,
	GroupId bigint NOT NULL,
	FileName nvarchar(MAX),
	FileType nvarchar(128),
	FileHash varchar(64),
	FileSize bigint,
	FileDesc nvarchar(MAX),
	CreateId bigint NOT NULL,
	CreateTime DATETIME NOT NULL,
	ModifyId bigint NOT NULL,
	ModifyTime DATETIME NOT NULL,
	FileContent varbinary(MAX),
	FOREIGN KEY (MaintainPlanId) REFERENCES hisac.dbo.maintain_plan(Id) 
) 



CREATE TABLE hisac.dbo.maintain_plan_improvement_suggest_read (
	Id bigint NOT NULL IDENTITY(1,1),
	MaintainPlanId bigint NOT NULL,
	AttachId bigint NOT NULL,
	IsRead bit NOT NULL,
	CreateId bigint NOT NULL,
	CreateTime DATETIME NOT NULL,
	ModifyId bigint NOT NULL,
	ModifyTime DATETIME NOT NULL,
	OrgId bigint,
	FOREIGN KEY (MaintainPlanId) REFERENCES hisac.dbo.maintain_plan(Id) 
) 

ALTER TABLE hisac.dbo.org ADD HealthLevelId bigint(19) 

ALTER TABLE hisac.dbo.org ADD SecurityLevel bigint(19) 

ALTER TABLE hisac.dbo.org ADD IsPublic big(1)

ALTER TABLE hisac.dbo.healthcare ADD HealthLevelId bigint(19) 

ALTER TABLE hisac.dbo.healthcare ADD SecurityLevel bigint(19) 

ALTER TABLE hisac.dbo.healthcare ADD IsPublic big(1)



CREATE TABLE hisac.dbo.health_level (
	Id bigint NOT NULL IDENTITY(1,1),
	Name nvarchar(MAX) NOT NULL,
	CreateId bigint NOT NULL,
	CreateTime DATETIME NOT NULL,
	ModifyId bigint NOT NULL,
	ModifyTime DATETIME NOT NULL,
	PRIMARY KEY (Id)
) go;











