--CREATE TABLE 勒索專區報表table
CREATE TABLE [dbo].[malware_management](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[PostType] [nvarchar](20) NULL,
	[PostDateTime] [datetime] NOT NULL,
	[Title] [nvarchar](255) NOT NULL,
	[SourceName] [nvarchar](255) NULL,
	[SourceLink] [nvarchar](max) NULL,
	[ContentType] [nchar](1) NULL,
	[Content] [nvarchar](max) NULL,
	[ExternalLink] [nvarchar](max) NULL,
	[IsBreakLine] [bit] NULL,
	[StartDateTime] [datetime] NULL,
	[EndDateTime] [datetime] NULL,
	[IsEnable] [bit] NULL,
	[CreateId] [bigint] NULL,
	[CreateTime] [datetime] NULL,
	[ModifyId] [bigint] NULL,
	[ModifyTime] [datetime] NULL,
	[Status] [tinyint] NULL,
	[PostId] [varchar](128) NULL,
	[IsPublic] [bit] NULL,
	[Sort] [bigint] NULL,
  	PRIMARY KEY (Id)
)


CREATE TABLE [dbo].[malware_management_attach](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[FileName] [nvarchar](max) NULL,
	[FileType] [nvarchar](128) NULL,
	[FileContent] [varbinary](max) NULL,
	[FileHash] [varchar](64) NULL,
	[FileSize] [bigint] NULL,
	[FileDesc] [nvarchar](max) NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
	[MalwareManagementId] [bigint] NOT NULL,
    PRIMARY KEY (Id)
) 

CREATE TABLE [dbo].[malware_management_pic](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[MalwareManagementId] [bigint] NOT NULL,
	[FileName] [nvarchar](max) NULL,
	[FileType] [nvarchar](128) NULL,
	[FileContent] [varbinary](max) NULL,
	[FileHash] [varchar](64) NULL,
	[FileSize] [bigint] NULL,
	[ImageWidth] [bigint] NULL,
	[ImageHeight] [bigint] NULL,
	[FileDesc] [nvarchar](max) NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
    PRIMARY KEY (Id)
)

CREATE TABLE [dbo].[malware_report](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Filename] [varchar](128) NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[Data] [varchar](max) NOT NULL,
      PRIMARY KEY (Id)

) 


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_malware_management_pic_member]
AS
SELECT          a.Id, a.MalwareManagementId, a.FileName, a.FileType, a.FileHash, a.FileSize, a.ImageWidth, a.ImageHeight, 
                            a.FileDesc, a.CreateId, b.Name AS CreateName, a.CreateTime, a.ModifyId, c.Name AS ModifyName, 
                            a.ModifyTime
FROM              dbo.malware_management_pic AS a INNER JOIN
                            dbo.member AS b ON a.CreateId = b.Id INNER JOIN
                            dbo.member AS c ON a.CreateId = c.Id


GO


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[v_malware_management_member]
AS
SELECT          a.Id, a.PostType, a.PostDateTime, a.Title, a.SourceName, a.SourceLink, a.ContentType, a.[Content], a.ExternalLink, 
                            a.IsBreakLine, a.StartDateTime, a.EndDateTime, a.IsEnable, a.CreateId, b.Name AS CreateName, a.CreateTime, 
                            a.ModifyId, c.Name AS ModifyName, a.ModifyTime, a.Status, a.PostId, a.IsPublic, a.Sort
FROM              dbo.malware_management AS a LEFT OUTER JOIN
                            dbo.member AS b ON a.CreateId = b.Id LEFT OUTER JOIN
                            dbo.member AS c ON a.ModifyId = c.Id
WHERE          (a.PostType = N'13')


GO


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_malware_management_attach_member]
AS
SELECT         a.Id, a.MalwareManagementId, a.FileName, a.FileType, a.FileHash, a.FileSize, 
                          a.FileDesc, a.CreateId, b.Name AS CreateName, a.CreateTime, a.ModifyId, 
                          c.Name AS ModifyName, a.ModifyTime
FROM             dbo.malware_management_attach AS a INNER JOIN
                          dbo.member AS b ON a.CreateId = b.Id INNER JOIN
                          dbo.member AS c ON a.ModifyId = c.Id


GO


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER  PROCEDURE [dbo].[xp_malware_management] 
	@start bigint,
	@maxRows bigint,
	@dir bit,
	@sort nvarchar(30),
	@RoleId bigint,
	@MemberId bigint = NULL,

	@PostType nchar(20),
	@PostDateTime datetime = NULL,
	@Title nvarchar(255) = NULL,
	@IsEnable bit = NULL,
	@IsPublic bit = NULL,
	@StartDateTime datetime = NULL,
	@Status nvarchar(2) = NULL
AS 
BEGIN 
	SET NOCOUNT ON;
/* 
1-SuperAdmin
2-H-ISAC管理者
3-H-ISAC內容維護者
4-H-ISAC內容審核者
5-H-ISAC警訊建立者
6-H-ISAC警訊審核者
12-HISAC通報審核者
13-HISAC情資維護者
14-HISAC-情資審核者
8-權責單位聯絡人
9-權責單位管理者
7-權責單位警訊審核者
15-權責單位通報審核者
10-會員單位聯絡人
11-會員單位管理者

*/


IF @RoleId=0 /* 訪客 For 公開資訊那4個窗格讀取資料用 */

    SELECT
        *
    FROM
        v_malware_management_member
    WHERE
        PostType = ISNULL(@PostType, PostType)
		AND PostDateTime = ISNULL(@PostDateTime, PostDateTime)
		AND IsEnable = ISNULL(@IsEnable, IsEnable)
		AND IsPublic = ISNULL(@IsPublic, IsPublic)
		AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
		AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
		AND (Title LIKE '%' + ISNULL(@Title, '') + '%' OR Content LIKE '%' + ISNULL(@Title, '') + '%' )
		AND Status = ISNULL(@Status, Status)
   ORDER BY
			CASE WHEN @sort = 'postDateTime' AND @dir = 0 THEN PostDateTime end ASC,
			CASE WHEN @sort = 'postDateTime' AND @dir = 1 THEN PostDateTime end DESC,
			CASE WHEN @sort = 'title' AND @dir = 0 THEN Title end ASC,
			CASE WHEN @sort = 'title' AND @dir = 1 THEN Title end DESC,
			CASE WHEN @sort = 'isEnable' AND @dir = 0 THEN IsEnable end ASC,
			CASE WHEN @sort = 'isEnable' AND @dir = 1 THEN IsEnable end DESC,
			CASE WHEN @sort = 'isPublic' AND @dir = 0 THEN IsPublic end ASC,
			CASE WHEN @sort = 'isPublic' AND @dir = 1 THEN IsPublic end DESC,
			CASE WHEN @sort = 'status' AND @dir = 0 THEN Status end ASC,
			CASE WHEN @sort = 'status' AND @dir = 1 THEN Status end DESC,
			CASE WHEN @sort = 'sort' AND @dir = 0 THEN Sort end ASC, 
			CASE WHEN @sort = 'sort' AND @dir = 1 THEN Sort end DESC, 
			CASE WHEN @sort = 'sort' AND @dir = 0 THEN PostDateTime end ASC, 
			CASE WHEN @sort = 'sort' AND @dir = 1 THEN PostDateTime end DESC,
			CASE WHEN @sort = 'sort' AND @dir = 0 THEN PostId end ASC,
			CASE WHEN @sort = 'sort' AND @dir = 1 THEN PostId end DESC 
		OFFSET  @start ROWS FETCH NEXT @maxRows ROWS ONLY

ELSE IF @RoleId=1 OR @RoleId=2 OR @RoleId=3 OR @RoleId=4 /* 1-SuperAdmin、2-H-ISAC管理者、3-H-ISAC內容維護者、4-H-ISAC內容審核者 */

	IF (@Status = '1')
	
		SELECT
			*
		FROM
			v_malware_management_member
		WHERE
			PostType = ISNULL(@PostType, PostType)
			AND PostDateTime = ISNULL(@PostDateTime, PostDateTime)
			AND (Title LIKE '%' + ISNULL(@Title, '') + '%' OR Content LIKE '%' + ISNULL(@Title, '') + '%' )
			AND IsEnable = ISNULL(@IsEnable, IsEnable)
			AND IsPublic = ISNULL(@IsPublic, IsPublic)
			AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
			AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
			AND Status IN (1, 6)
		ORDER BY
			CASE WHEN @sort = 'postDateTime' AND @dir = 0 THEN PostDateTime end ASC,
			CASE WHEN @sort = 'postDateTime' AND @dir = 1 THEN PostDateTime end DESC,
			CASE WHEN @sort = 'title' AND @dir = 0 THEN Title end ASC,
			CASE WHEN @sort = 'title' AND @dir = 1 THEN Title end DESC,
			CASE WHEN @sort = 'isEnable' AND @dir = 0 THEN IsEnable end ASC,
			CASE WHEN @sort = 'isEnable' AND @dir = 1 THEN IsEnable end DESC,
			CASE WHEN @sort = 'isPublic' AND @dir = 0 THEN IsPublic end ASC,
			CASE WHEN @sort = 'isPublic' AND @dir = 1 THEN IsPublic end DESC,
			CASE WHEN @sort = 'status' AND @dir = 0 THEN Status end ASC,
			CASE WHEN @sort = 'status' AND @dir = 1 THEN Status end DESC,
			CASE WHEN @sort = 'sort' AND @dir = 0 THEN Sort end ASC, 
			CASE WHEN @sort = 'sort' AND @dir = 1 THEN Sort end DESC, 
			CASE WHEN @sort = 'sort' AND @dir = 0 THEN PostDateTime end ASC, 
			CASE WHEN @sort = 'sort' AND @dir = 1 THEN PostDateTime end DESC,
			CASE WHEN @sort = 'sort' AND @dir = 0 THEN PostId end ASC,
			CASE WHEN @sort = 'sort' AND @dir = 1 THEN PostId end DESC
		OFFSET  @start ROWS FETCH NEXT @maxRows ROWS ONLY

	ELSE
    
		SELECT
			*
		FROM
			v_malware_management_member
		WHERE
			PostType = ISNULL(@PostType, PostType)
			AND PostDateTime = ISNULL(@PostDateTime, PostDateTime)
			AND (Title LIKE '%' + ISNULL(@Title, '') + '%' OR Content LIKE '%' + ISNULL(@Title, '') + '%' )
			AND IsEnable = ISNULL(@IsEnable, IsEnable)
			AND IsPublic = ISNULL(@IsPublic, IsPublic)
			AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
			AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
			AND Status = ISNULL(@Status, Status)
		ORDER BY
			CASE WHEN @sort = 'postDateTime' AND @dir = 0 THEN PostDateTime end ASC,
			CASE WHEN @sort = 'postDateTime' AND @dir = 1 THEN PostDateTime end DESC,
			CASE WHEN @sort = 'title' AND @dir = 0 THEN Title end ASC,
			CASE WHEN @sort = 'title' AND @dir = 1 THEN Title end DESC,
			CASE WHEN @sort = 'isEnable' AND @dir = 0 THEN IsEnable end ASC,
			CASE WHEN @sort = 'isEnable' AND @dir = 1 THEN IsEnable end DESC,
			CASE WHEN @sort = 'isPublic' AND @dir = 0 THEN IsPublic end ASC,
			CASE WHEN @sort = 'isPublic' AND @dir = 1 THEN IsPublic end DESC,
			CASE WHEN @sort = 'status' AND @dir = 0 THEN Status end ASC,
			CASE WHEN @sort = 'status' AND @dir = 1 THEN Status end DESC,
			CASE WHEN @sort = 'sort' AND @dir = 0 THEN Sort end ASC, 
			CASE WHEN @sort = 'sort' AND @dir = 1 THEN Sort end DESC, 
			CASE WHEN @sort = 'sort' AND @dir = 0 THEN PostDateTime end ASC, 
			CASE WHEN @sort = 'sort' AND @dir = 1 THEN PostDateTime end DESC,
			CASE WHEN @sort = 'sort' AND @dir = 0 THEN PostId end ASC,
			CASE WHEN @sort = 'sort' AND @dir = 1 THEN PostId end DESC 
		OFFSET  @start ROWS FETCH NEXT @maxRows ROWS ONLY


END

/* xp_news_management_button_count 新增 IsPublic */
SET ANSI_NULLS ON



GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[xp_malware_management_button_count] 
	@RoleId bigint,
	@MemberId bigint = NULL,

	@PostType nchar(20),
	@PostDateTime datetime = NULL,
	@Title nvarchar(255) = NULL,
	@IsEnable bit = NULL,
	@IsPublic bit = NULL,
	@Status nvarchar(2) = NULL
AS 
BEGIN 
	SET NOCOUNT ON;
	/* 
	1-SuperAdmin
	2-H-ISAC管理者
	3-H-ISAC內容維護者
	4-H-ISAC內容審核者
	5-H-ISAC警訊建立者
	6-H-ISAC警訊審核者
	12-HISAC通報審核者
	13-HISAC情資維護者
	14-HISAC-情資審核者
	8-權責單位聯絡人
	9-權責單位管理者
	7-權責單位警訊審核者
	15-權責單位通報審核者
	10-會員單位聯絡人
	11-會員單位管理者

	*/
	
	IF @RoleId=1 OR @RoleId=2 /* 1-SuperAdmin、2-H-ISAC管理者，統計階段 1-編輯中、2-撤銷中、3-審核中、6-編輯中(退回) 之件數 */
		BEGIN
			SELECT
				Status,
				COUNT(Id) AS Count
			FROM
				v_malware_management_member
			WHERE
				PostType = ISNULL( @PostType, PostType)
				AND PostDateTime = ISNULL( @PostDateTime, PostDateTime)
				AND (Title LIKE '%' + ISNULL( @Title, '') + '%' OR Content LIKE '%' + ISNULL( @Title, '') + '%' )
				AND IsEnable = ISNULL( @IsEnable, IsEnable)
				AND IsPublic = ISNULL( @IsPublic, IsPublic)
				AND Status IN (1, 2, 3, 6)
			GROUP BY Status
		END
	ELSE IF @RoleId=3 /* 3-H-ISAC內容維護者，統計階段 1-編輯中、2-撤銷中、6-編輯中(退回) 之件數 */
		BEGIN
			SELECT
				Status,
				COUNT(Id) AS Count
			FROM
				v_malware_management_member
			WHERE
				PostType = ISNULL( @PostType, PostType)
				AND PostDateTime = ISNULL( @PostDateTime, PostDateTime)
				AND (Title LIKE '%' + ISNULL( @Title, '') + '%' OR Content LIKE '%' + ISNULL( @Title, '') + '%' )
				AND IsEnable = ISNULL( @IsEnable, IsEnable)
				AND IsPublic = ISNULL( @IsPublic, IsPublic)
				AND Status IN (1, 2, 6)
			GROUP BY Status
		END
	ELSE IF @RoleId=4 /* 4-H-ISAC內容審核者，統計階段 3-審核中 之件數 */
		BEGIN
			SELECT
				Status,
				COUNT(Id) AS Count
			FROM
				v_malware_management_member
			WHERE
				PostType = ISNULL( @PostType, PostType)
				AND PostDateTime = ISNULL( @PostDateTime, PostDateTime)
				AND (Title LIKE '%' + ISNULL( @Title, '') + '%' OR Content LIKE '%' + ISNULL( @Title, '') + '%' )
				AND IsEnable = ISNULL( @IsEnable, IsEnable)
				AND IsPublic = ISNULL( @IsPublic, IsPublic)
				AND Status = 3
			GROUP BY Status
		END
END

/* xp_news_management_size 新增 IsPublic */
SET ANSI_NULLS ON



GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[xp_malware_management_report] 
	@StartPostDateTime datetime = NULL,
	@EndPostDateTime datetime = NULL
AS 
BEGIN

select count(*) as Count, Name=N'編輯中' from malware_management
where (status=1 or status=6)
and PostDateTime >= ISNULL(@StartPostDateTime, PostDateTime)
and PostDateTime < ISNULL(@EndPostDateTime, PostDateTime)
union
select count(*) as Count, Name=N'已撤銷' from malware_management
where status=5
and PostDateTime >= ISNULL(@StartPostDateTime, PostDateTime)
and PostDateTime < ISNULL(@EndPostDateTime, PostDateTime)
union
select count(*) as Count, Name=N'審核中' from malware_management
where (status=2 or status=3)
and PostDateTime >= ISNULL(@StartPostDateTime, PostDateTime)
and PostDateTime < ISNULL(@EndPostDateTime, PostDateTime)
union
select count(*) as Count, Name=N'已公告,啟用' from malware_management
where status=4 and IsEnable = 1
and PostDateTime >= ISNULL(@StartPostDateTime, PostDateTime)
and PostDateTime < ISNULL(@EndPostDateTime, PostDateTime)
union
select count(*) as Count, Name=N'已公告,不啟用' from malware_management
where status=4 and IsEnable = 0
and PostDateTime >= ISNULL(@StartPostDateTime, PostDateTime)
and PostDateTime < ISNULL(@EndPostDateTime, PostDateTime)

END
SET ANSI_NULLS ON


GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER  PROCEDURE [dbo].[xp_malware_management_size] 
	@RoleId bigint,
	@MemberId bigint = NULL,

	@PostType nchar(20),
	@PostDateTime datetime = NULL,
	@Title nvarchar(255) = NULL,
	@IsEnable bit = NULL,
	@IsPublic bit = NULL,
	@StartDateTime datetime = NULL,
	@Status nvarchar(2) = NULL
AS 
BEGIN 
	SET NOCOUNT ON;
/* 
1-SuperAdmin
2-H-ISAC管理者
3-H-ISAC內容維護者
4-H-ISAC內容審核者
5-H-ISAC警訊建立者
6-H-ISAC警訊審核者
12-HISAC通報審核者
13-HISAC情資維護者
14-HISAC-情資審核者
8-權責單位聯絡人
9-權責單位管理者
7-權責單位警訊審核者
15-權責單位通報審核者
10-會員單位聯絡人
11-會員單位管理者

*/


IF @RoleId=0 /* 訪客 For 公開資訊那4個窗格讀取資料用 */

	SELECT
		COUNT(Id)
	FROM
		v_malware_management_member
	WHERE
		PostType = ISNULL( @PostType, PostType)
		AND PostDateTime = ISNULL( @PostDateTime, PostDateTime)
		AND IsEnable = ISNULL( @IsEnable, IsEnable)
		AND IsPublic = ISNULL( @IsPublic, IsPublic)
		AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
		AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
		AND (Title LIKE '%' + ISNULL( @Title, '') + '%' OR Content LIKE '%' + ISNULL( @Title, '') + '%' )
		AND Status = ISNULL( @Status, Status)

ELSE IF @RoleId=1 OR @RoleId=2 OR @RoleId=3 OR @RoleId=4 /* 1-SuperAdmin、2-H-ISAC管理者、3-H-ISAC內容維護者、4-H-ISAC內容審核者 */

	IF (@Status = '1')

		SELECT
			COUNT(Id)
		FROM
			v_malware_management_member
		WHERE
			PostType = ISNULL( @PostType, PostType)
			AND PostDateTime = ISNULL( @PostDateTime, PostDateTime)
			AND (Title LIKE '%' + ISNULL( @Title, '') + '%' OR Content LIKE '%' + ISNULL( @Title, '') + '%' )
			AND IsEnable = ISNULL( @IsEnable, IsEnable)
			AND IsPublic = ISNULL( @IsPublic, IsPublic)
			AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
			AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
			AND Status IN (1, 6)

	ELSE
	
		SELECT
			COUNT(Id)
		FROM
			v_malware_management_member
		WHERE
			PostType = ISNULL( @PostType, PostType)
			AND PostDateTime = ISNULL( @PostDateTime, PostDateTime)
			AND (Title LIKE '%' + ISNULL( @Title, '') + '%' OR Content LIKE '%' + ISNULL( @Title, '') + '%' )
			AND IsEnable = ISNULL( @IsEnable, IsEnable)
			AND IsPublic = ISNULL( @IsPublic, IsPublic)
			AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
			AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
			AND Status = ISNULL( @Status, Status)

END





GO

