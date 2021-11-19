

ALTER TABLE [hisac].[dbo].[malware_management] ADD [IncidentId] [nvarchar](128) NULL;
ALTER TABLE [hisac].[dbo].[malware_management] ADD  [EventTypeCode] [varchar](8)  NULL ;
ALTER TABLE [hisac].[dbo].[malware_management] ADD  [ReporterName] [nvarchar](256)  NULL ;
ALTER TABLE [hisac].[dbo].[malware_management] ADD  [ResponderPartyName] [nvarchar](64)  NULL ;
ALTER TABLE [hisac].[dbo].[malware_management] ADD  [ResponderContactNumbers] [nvarchar](64)   NULL ;
ALTER TABLE [hisac].[dbo].[malware_management] ADD  [ImpactQualification] [tinyint]  NULL ;
ALTER TABLE [hisac].[dbo].[malware_management] ADD  [CoaDescription] [nvarchar](128)  NULL ;
ALTER TABLE [hisac].[dbo].[malware_management] ADD  [Confidence] [nvarchar](128)  NULL ;
ALTER TABLE [hisac].[dbo].[malware_management] ADD  [Reference] [nvarchar](max)  NULL ;
ALTER TABLE [hisac].[dbo].[malware_management] ADD  [AffectedSoftwareDescription] [nvarchar](max)  NULL ;


ALTER TABLE  [hisac].[dbo].[malware_management] 

alter COLUMN CoaDescription nvarchar(max)


ALTER TABLE  [hisac].[dbo].[ana_management] ADD  IsMalware bit Not NULL DEFAULT 1



SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER VIEW [dbo].[v_ana_management_member]
AS
SELECT          a.Id, a.IncidentId, a.IncidentTitle, a.IncidentDiscoveryTime, a.IncidentReportedTime, a.Description, a.EventTypeCode, 
                            d.Code + '-' + d.Name AS EventTypeName, a.ReporterName, a.ResponderPartyName, a.ResponderContactNumbers, 
                            a.ResponderElectronicAddressIdentifiers, a.ImpactQualification, a.CoaDescription, a.Confidence, a.Reference, 
                            a.AffectedSoftwareDescription, a.StartDateTime, a.EndDateTime, a.IsEnable, a.CreateId, b.Name AS CreateName, 
                            a.CreateTime, a.ModifyId, c.Name AS ModifyName, a.ModifyTime, a.Status, a.PostId, a.TransferInType, 
                            a.TransferInId, a.TransferOutType, a.TransferOutId, a.IsMedical, a.Sort, a.IsMalware
FROM              dbo.ana_management AS a LEFT OUTER JOIN
                            dbo.member AS b ON a.CreateId = b.Id LEFT OUTER JOIN
                            dbo.member AS c ON a.ModifyId = c.Id LEFT OUTER JOIN
                            dbo.event_type AS d ON a.EventTypeCode = d.Code
WHERE          (d.AlertCode = 'ANA')
GO


/*  新增資安訊息搜尋區間 */





SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER  PROCEDURE [dbo].[xp_ana_management] 
	@start bigint,
	@maxRows bigint,
	@dir bit,
	@sort nvarchar(30),
	@RoleId bigint,
	@MemberId bigint = NULL,

    @QuerySReportedTime datetime = null,
    @QueryEReportedTime datetime = null,


	@IncidentTitle nvarchar(MAX) = NULL,
	@IncidentDiscoveryTime datetime = NULL,
	@IncidentReportedTime datetime = NULL,
	@EventTypeCode varchar(8) = NULL,
	@ImpactQualification tinyint = NULL,
	@IsEnable bit = NULL,
	@IsMedical bit = NULL,
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
        v_ana_management_member
    WHERE
		IncidentTitle LIKE '%' + ISNULL(@IncidentTitle, '') + '%'
		AND IncidentDiscoveryTime = ISNULL(@IncidentDiscoveryTime, IncidentDiscoveryTime)
		AND IncidentReportedTime = ISNULL(@IncidentReportedTime, IncidentReportedTime)
		AND EventTypeCode = ISNULL(@EventTypeCode, EventTypeCode)
		AND ImpactQualification = ISNULL(@ImpactQualification, ImpactQualification)
		AND IsEnable = ISNULL(@IsEnable, IsEnable)
		AND IsMedical = ISNULL(@IsMedical, IsMedical)
		AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
		AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
        
         /* 新增報表搜尋區間 */
        AND (IncidentReportedTime >= ISNULL(@QuerySReportedTime, StartDateTime) or @QuerySReportedTime IS  NULL)
        AND (IncidentReportedTime <= ISNULL(@QueryEReportedTime, StartDateTime) or @QuerySReportedTime IS  NULL)


		AND Status = ISNULL(@Status, Status)
    ORDER BY
       CASE WHEN @sort = 'incidentId' AND @dir = 0 THEN IncidentId end ASC,
			CASE WHEN @sort = 'incidentId' AND @dir = 1 THEN IncidentId end DESC,
			CASE WHEN @sort = 'incidentTitle' AND @dir = 0 THEN IncidentTitle end ASC,
			CASE WHEN @sort = 'incidentTitle' AND @dir = 1 THEN IncidentTitle end DESC,
			CASE WHEN @sort = 'incidentDiscoveryTime' AND @dir = 0 THEN IncidentDiscoveryTime end ASC,
			CASE WHEN @sort = 'incidentDiscoveryTime' AND @dir = 1 THEN IncidentDiscoveryTime end DESC,
			CASE WHEN @sort = 'incidentReportedTime' AND @dir = 0 THEN IncidentReportedTime end ASC,
			CASE WHEN @sort = 'incidentReportedTime' AND @dir = 1 THEN IncidentReportedTime end DESC,
			CASE WHEN @sort = 'eventTypeCode' AND @dir = 0 THEN EventTypeCode end ASC,
			CASE WHEN @sort = 'eventTypeCode' AND @dir = 1 THEN EventTypeCode end DESC,
			CASE WHEN @sort = 'reporterName' AND @dir = 0 THEN ReporterName end ASC,
			CASE WHEN @sort = 'reporterName' AND @dir = 1 THEN ReporterName end DESC,
			CASE WHEN @sort = 'impactQualification' AND @dir = 0 THEN ImpactQualification end ASC,
			CASE WHEN @sort = 'impactQualification' AND @dir = 1 THEN ImpactQualification end DESC,
			CASE WHEN @sort = 'isEnable' AND @dir = 0 THEN IsEnable end ASC,
			CASE WHEN @sort = 'isEnable' AND @dir = 1 THEN IsEnable end DESC,
			CASE WHEN @sort = 'isMedical' AND @dir = 0 THEN IsMedical end ASC,
			CASE WHEN @sort = 'isMedical' AND @dir = 1 THEN IsMedical end DESC,
			CASE WHEN @sort = 'status' AND @dir = 0 THEN Status end ASC,
			CASE WHEN @sort = 'status' AND @dir = 1 THEN Status end DESC,
			CASE WHEN @sort = 'sort' AND @dir = 0 THEN Sort end ASC, 
			CASE WHEN @sort = 'sort' AND @dir = 1 THEN Sort end DESC, 
			CASE WHEN @sort = 'sort' AND @dir = 0 THEN IncidentReportedTime end ASC, 
			CASE WHEN @sort = 'sort' AND @dir = 1 THEN IncidentReportedTime end DESC,
			CASE WHEN @sort = 'sort' AND @dir = 0 THEN Id end ASC, 
			CASE WHEN @sort = 'sort' AND @dir = 1 THEN Id end DESC 
		OFFSET  @start ROWS FETCH NEXT @maxRows ROWS ONLY

ELSE IF @RoleId=1 OR @RoleId=2 OR @RoleId=3 OR @RoleId=4 /* 1-SuperAdmin、2-H-ISAC管理者、3-H-ISAC內容維護者、4-H-ISAC內容審核者 */

	IF (@Status = '1')
	
		SELECT
			*
		FROM
			v_ana_management_member
		WHERE
			IncidentTitle LIKE '%' + ISNULL(@IncidentTitle, '') + '%'
			AND IncidentDiscoveryTime = ISNULL(@IncidentDiscoveryTime, IncidentDiscoveryTime)
			AND IncidentReportedTime = ISNULL(@IncidentReportedTime, IncidentReportedTime)
			AND EventTypeCode = ISNULL(@EventTypeCode, EventTypeCode)
			AND ImpactQualification = ISNULL(@ImpactQualification, ImpactQualification)
			AND IsEnable = ISNULL(@IsEnable, IsEnable)
			AND IsMedical = ISNULL(@IsMedical, IsMedical)
			AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
			AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)

            /* 新增報表搜尋區間 */
            AND (IncidentReportedTime >= ISNULL(@QuerySReportedTime, StartDateTime) or @QuerySReportedTime IS  NULL)
            AND (IncidentReportedTime <= ISNULL(@QueryEReportedTime, StartDateTime) or @QuerySReportedTime IS  NULL)
            
			AND Status IN (1, 6)
		ORDER BY
			CASE WHEN @sort = 'incidentId' AND @dir = 0 THEN IncidentId end ASC,
			CASE WHEN @sort = 'incidentId' AND @dir = 1 THEN IncidentId end DESC,
			CASE WHEN @sort = 'incidentTitle' AND @dir = 0 THEN IncidentTitle end ASC,
			CASE WHEN @sort = 'incidentTitle' AND @dir = 1 THEN IncidentTitle end DESC,
			CASE WHEN @sort = 'incidentDiscoveryTime' AND @dir = 0 THEN IncidentDiscoveryTime end ASC,
			CASE WHEN @sort = 'incidentDiscoveryTime' AND @dir = 1 THEN IncidentDiscoveryTime end DESC,
			CASE WHEN @sort = 'incidentReportedTime' AND @dir = 0 THEN IncidentReportedTime end ASC,
			CASE WHEN @sort = 'incidentReportedTime' AND @dir = 1 THEN IncidentReportedTime end DESC,
			CASE WHEN @sort = 'eventTypeCode' AND @dir = 0 THEN EventTypeCode end ASC,
			CASE WHEN @sort = 'eventTypeCode' AND @dir = 1 THEN EventTypeCode end DESC,
			CASE WHEN @sort = 'reporterName' AND @dir = 0 THEN ReporterName end ASC,
			CASE WHEN @sort = 'reporterName' AND @dir = 1 THEN ReporterName end DESC,
			CASE WHEN @sort = 'impactQualification' AND @dir = 0 THEN ImpactQualification end ASC,
			CASE WHEN @sort = 'impactQualification' AND @dir = 1 THEN ImpactQualification end DESC,
			CASE WHEN @sort = 'isEnable' AND @dir = 0 THEN IsEnable end ASC,
			CASE WHEN @sort = 'isEnable' AND @dir = 1 THEN IsEnable end DESC,
			CASE WHEN @sort = 'isMedical' AND @dir = 0 THEN IsMedical end ASC,
			CASE WHEN @sort = 'isMedical' AND @dir = 1 THEN IsMedical end DESC,
			CASE WHEN @sort = 'status' AND @dir = 0 THEN Status end ASC,
			CASE WHEN @sort = 'status' AND @dir = 1 THEN Status end DESC,
			CASE WHEN @sort = 'sort' AND @dir = 0 THEN Sort end ASC, 
			CASE WHEN @sort = 'sort' AND @dir = 1 THEN Sort end DESC, 
			CASE WHEN @sort = 'sort' AND @dir = 0 THEN IncidentReportedTime end ASC, 
			CASE WHEN @sort = 'sort' AND @dir = 1 THEN IncidentReportedTime end DESC,
			CASE WHEN @sort = 'sort' AND @dir = 0 THEN Id end ASC, 
			CASE WHEN @sort = 'sort' AND @dir = 1 THEN Id end DESC 
		OFFSET  @start ROWS FETCH NEXT @maxRows ROWS ONLY

	ELSE
    
		SELECT
			*
		FROM
			v_ana_management_member
		WHERE
			IncidentTitle LIKE '%' + ISNULL(@IncidentTitle, '') + '%'
			AND IncidentDiscoveryTime = ISNULL(@IncidentDiscoveryTime, IncidentDiscoveryTime)
			AND IncidentReportedTime = ISNULL(@IncidentReportedTime, IncidentReportedTime)
			AND EventTypeCode = ISNULL(@EventTypeCode, EventTypeCode)
			AND ImpactQualification = ISNULL(@ImpactQualification, ImpactQualification)
			AND IsEnable = ISNULL(@IsEnable, IsEnable)
			AND IsMedical = ISNULL(@IsMedical, IsMedical)
			AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
			AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)

            /* 新增報表搜尋區間 */
            AND (IncidentReportedTime >= ISNULL(@QuerySReportedTime, StartDateTime) or @QuerySReportedTime IS  NULL)
            AND (IncidentReportedTime <= ISNULL(@QueryEReportedTime, StartDateTime) or @QuerySReportedTime IS  NULL)

			AND Status = ISNULL(@Status, Status)
		ORDER BY
			CASE WHEN @sort = 'incidentId' AND @dir = 0 THEN IncidentId end ASC,
			CASE WHEN @sort = 'incidentId' AND @dir = 1 THEN IncidentId end DESC,
			CASE WHEN @sort = 'incidentTitle' AND @dir = 0 THEN IncidentTitle end ASC,
			CASE WHEN @sort = 'incidentTitle' AND @dir = 1 THEN IncidentTitle end DESC,
			CASE WHEN @sort = 'incidentDiscoveryTime' AND @dir = 0 THEN IncidentDiscoveryTime end ASC,
			CASE WHEN @sort = 'incidentDiscoveryTime' AND @dir = 1 THEN IncidentDiscoveryTime end DESC,
			CASE WHEN @sort = 'incidentReportedTime' AND @dir = 0 THEN IncidentReportedTime end ASC,
			CASE WHEN @sort = 'incidentReportedTime' AND @dir = 1 THEN IncidentReportedTime end DESC,
			CASE WHEN @sort = 'eventTypeCode' AND @dir = 0 THEN EventTypeCode end ASC,
			CASE WHEN @sort = 'eventTypeCode' AND @dir = 1 THEN EventTypeCode end DESC,
			CASE WHEN @sort = 'reporterName' AND @dir = 0 THEN ReporterName end ASC,
			CASE WHEN @sort = 'reporterName' AND @dir = 1 THEN ReporterName end DESC,
			CASE WHEN @sort = 'impactQualification' AND @dir = 0 THEN ImpactQualification end ASC,
			CASE WHEN @sort = 'impactQualification' AND @dir = 1 THEN ImpactQualification end DESC,
			CASE WHEN @sort = 'isEnable' AND @dir = 0 THEN IsEnable end ASC,
			CASE WHEN @sort = 'isEnable' AND @dir = 1 THEN IsEnable end DESC,
			CASE WHEN @sort = 'isMedical' AND @dir = 0 THEN IsMedical end ASC,
			CASE WHEN @sort = 'isMedical' AND @dir = 1 THEN IsMedical end DESC,
			CASE WHEN @sort = 'status' AND @dir = 0 THEN Status end ASC,
			CASE WHEN @sort = 'status' AND @dir = 1 THEN Status end DESC,
			CASE WHEN @sort = 'sort' AND @dir = 0 THEN Sort end ASC, 
			CASE WHEN @sort = 'sort' AND @dir = 1 THEN Sort end DESC, 
			CASE WHEN @sort = 'sort' AND @dir = 0 THEN IncidentReportedTime end ASC, 
			CASE WHEN @sort = 'sort' AND @dir = 1 THEN IncidentReportedTime end DESC,
			CASE WHEN @sort = 'sort' AND @dir = 0 THEN Id end ASC, 
			CASE WHEN @sort = 'sort' AND @dir = 1 THEN Id end DESC 
		OFFSET  @start ROWS FETCH NEXT @maxRows ROWS ONLY


END
GO






SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER  PROCEDURE [dbo].[xp_ana_management_size] 
	@RoleId bigint,
	@MemberId bigint = NULL,
    @QuerySReportedTime datetime = null,
    @QueryEReportedTime datetime = null,


	@IncidentTitle nvarchar(MAX) = NULL,
	@IncidentDiscoveryTime datetime = NULL,
	@IncidentReportedTime datetime = NULL,
	@EventTypeCode varchar(8) = NULL,
	@ImpactQualification tinyint = NULL,
	@IsEnable bit = NULL,
	@IsMedical bit = NULL,
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
		v_ana_management_member
	WHERE
		IncidentTitle LIKE '%' + ISNULL(@IncidentTitle, '') + '%'
		AND IncidentDiscoveryTime = ISNULL(@IncidentDiscoveryTime, IncidentDiscoveryTime)
		AND IncidentReportedTime = ISNULL(@IncidentReportedTime, IncidentReportedTime)
		AND EventTypeCode = ISNULL(@EventTypeCode, EventTypeCode)
		AND ImpactQualification = ISNULL(@ImpactQualification, ImpactQualification)
		AND IsEnable = ISNULL(@IsEnable, IsEnable)
		AND IsMedical = ISNULL(@IsMedical, IsMedical)
		AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
		AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)

         /* 新增報表搜尋區間 */
        AND (IncidentReportedTime >= ISNULL(@QuerySReportedTime, StartDateTime) or @QuerySReportedTime IS  NULL)
        AND (IncidentReportedTime <= ISNULL(@QueryEReportedTime, StartDateTime) or @QuerySReportedTime IS  NULL)
		AND Status = ISNULL(@Status, Status)

ELSE IF @RoleId=1 OR @RoleId=2 OR @RoleId=3 OR @RoleId=4 /* 1-SuperAdmin、2-H-ISAC管理者、3-H-ISAC內容維護者、4-H-ISAC內容審核者 */

	IF (@Status = '1')

		SELECT
			COUNT(Id)
		FROM
			v_ana_management_member
		WHERE
			IncidentTitle LIKE '%' + ISNULL(@IncidentTitle, '') + '%'
			AND IncidentDiscoveryTime = ISNULL(@IncidentDiscoveryTime, IncidentDiscoveryTime)
			AND IncidentReportedTime = ISNULL(@IncidentReportedTime, IncidentReportedTime)
			AND EventTypeCode = ISNULL(@EventTypeCode, EventTypeCode)
			AND ImpactQualification = ISNULL(@ImpactQualification, ImpactQualification)
			AND IsEnable = ISNULL(@IsEnable, IsEnable)
			AND IsMedical = ISNULL(@IsMedical, IsMedical)
			AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
			AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
             /* 新增報表搜尋區間 */
            AND (IncidentReportedTime >= ISNULL(@QuerySReportedTime, StartDateTime) or @QuerySReportedTime IS  NULL)
            AND (IncidentReportedTime <= ISNULL(@QueryEReportedTime, StartDateTime) or @QuerySReportedTime IS  NULL)
			AND Status IN (1, 6)

	ELSE
	
		SELECT
			COUNT(Id)
		FROM
			v_ana_management_member
		WHERE
			IncidentTitle LIKE '%' + ISNULL(@IncidentTitle, '') + '%'
			AND IncidentDiscoveryTime = ISNULL(@IncidentDiscoveryTime, IncidentDiscoveryTime)
			AND IncidentReportedTime = ISNULL(@IncidentReportedTime, IncidentReportedTime)
			AND EventTypeCode = ISNULL(@EventTypeCode, EventTypeCode)
			AND ImpactQualification = ISNULL(@ImpactQualification, ImpactQualification)
			AND IsEnable = ISNULL(@IsEnable, IsEnable)
			AND IsMedical = ISNULL(@IsMedical, IsMedical)
			AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
			AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
             /* 新增報表搜尋區間 */
            AND (IncidentReportedTime >= ISNULL(@QuerySReportedTime, StartDateTime) or @QuerySReportedTime IS  NULL)
            AND (IncidentReportedTime <= ISNULL(@QueryEReportedTime, StartDateTime) or @QuerySReportedTime IS  NULL)
			AND Status = ISNULL(@Status, Status)

END
GO


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER  PROCEDURE [dbo].[xp_malware_management_size] 
	@RoleId bigint,
	@MemberId bigint = NULL,

    @QuerySPostDateTime datetime = null,
    @QueryEPostDateTime datetime = null,


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
        /* 新增報表搜尋區間 */
        AND (PostDateTime >= ISNULL(@QuerySPostDateTime, StartDateTime) or @QuerySPostDateTime IS  NULL)
        AND (PostDateTime <= ISNULL(@QueryEPostDateTime, StartDateTime) or @QueryEPostDateTime IS  NULL)

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
            /* 新增報表搜尋區間 */
        AND (PostDateTime >= ISNULL(@QuerySPostDateTime, StartDateTime) or @QuerySPostDateTime IS  NULL)
        AND (PostDateTime <= ISNULL(@QueryEPostDateTime, StartDateTime) or @QueryEPostDateTime IS  NULL)

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
            /* 新增報表搜尋區間 */
        AND (PostDateTime >= ISNULL(@QuerySPostDateTime, StartDateTime) or @QuerySPostDateTime IS  NULL)
        AND (PostDateTime <= ISNULL(@QueryEPostDateTime, StartDateTime) or @QueryEPostDateTime IS  NULL)

END


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

    @QuerySPostDateTime datetime = null,
    @QueryEPostDateTime datetime = null,

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

          /* 新增報表搜尋區間 */
        AND (PostDateTime >= ISNULL(@QuerySPostDateTime, StartDateTime) or @QuerySPostDateTime IS  NULL)
        AND (PostDateTime <= ISNULL(@QueryEPostDateTime, StartDateTime) or @QueryEPostDateTime IS  NULL)
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
             /* 新增報表搜尋區間 */
        AND (PostDateTime >= ISNULL(@QuerySPostDateTime, StartDateTime) or @QuerySPostDateTime IS  NULL)
        AND (PostDateTime <= ISNULL(@QueryEPostDateTime, StartDateTime) or @QueryEPostDateTime IS  NULL)
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
             /* 新增報表搜尋區間 */
        AND (PostDateTime >= ISNULL(@QuerySPostDateTime, StartDateTime) or @QuerySPostDateTime IS  NULL)
        AND (PostDateTime <= ISNULL(@QueryEPostDateTime, StartDateTime) or @QueryEPostDateTime IS  NULL)
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




GO

--CREATE TABLE 資通安全責任等級歷次核定資料table
CREATE TABLE [dbo].[security_level_log](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
    [OrgId] [bigint] NULL,
    [SecurityLevel] [bigint] NULL,
    [Year] varchar(128) NULL,
	[CreateId] [bigint] NULL,
	[CreateTime] [datetime] NULL,
	[ModifyId] [bigint] NULL,
	[ModifyTime] [datetime] NULL,
	
  	PRIMARY KEY (Id)
)


--CREATE TABLE 資通安全責任等級歷次核定資料table
CREATE TABLE [dbo].[ciLevel_log](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
    [OrgId] [bigint] NULL,
    [CiLevel] varchar(1) NULL,
    [Year] varchar(128) NULL,
	[CreateId] [bigint] NULL,
	[CreateTime] [datetime] NULL,
	[ModifyId] [bigint] NULL,
	[ModifyTime] [datetime] NULL,
	
  	PRIMARY KEY (Id)
)



insert into [dbo].[ciLevel_log]
(OrgId,CiLevel, Year, CreateId, CreateTime , ModifyId, ModifyTime)
SELECT [Id] as OrgId 
      ,[CiLevel],
      CONVERT(VARCHAR, YEAR(GETDATE()),120 ) as Year,
      [CreateId]
      ,GETDATE() as [CreateTime]
      ,[ModifyId]
      , GETDATE() as [ModifyTime]
  FROM [hisac].[dbo].[org]



  insert into [dbo].[Security_level_log]
(OrgId,SecurityLevel, Year, CreateId, CreateTime , ModifyId, ModifyTime)
SELECT [Id] as OrgId 
      ,[SecurityLevel],
      CONVERT(VARCHAR, YEAR(GETDATE()),120 ) as Year,
      [CreateId]
      ,GETDATE() as [CreateTime]
      ,[ModifyId]
      , GETDATE() as [ModifyTime]
  FROM [hisac].[dbo].[org]


--Security Level 使用 資通安全責任等級歷次核定資料table


  SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER VIEW [dbo].[v_security_level_log]
AS



select s.Id, dbo.org.Name ,s.OrgId, s.CreateTime, ISNULL(s.SecurityLevel,0) as SecurityLevel,s.Year from [dbo].[security_level_log] as s INNER JOIN
                            dbo.org ON dbo.org.Id = s.OrgId
                            where s.CreateTime in
(SELECT MAX([CreateTime]) 
  FROM [dbo].[security_level_log] GROUP BY OrgId )


GO


ALTER TABLE [hisac].[dbo].[org] add ApiKey VARCHAR(256)

ALTER TABLE [hisac].[dbo].[org] add ApiKeyExpiryDate DATE

ALTER TABLE [hisac].[dbo].[org] add IsLocate bit

ALTER TABLE [hisac].[dbo].[org] ALTER COLUMN CiLevel varchar(20)

