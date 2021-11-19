USE [hisac]
GO

/****** Drop StoredProcedure ******/
DROP PROCEDURE [dbo].[xp_activity_management]
GO
DROP PROCEDURE [dbo].[xp_activity_management_button_count]
GO
DROP PROCEDURE [dbo].[xp_activity_management_from_count]
GO
DROP PROCEDURE [dbo].[xp_activity_management_size]
GO
DROP PROCEDURE [dbo].[xp_ana_management]
GO
DROP PROCEDURE [dbo].[xp_ana_management_button_count]
GO
DROP PROCEDURE [dbo].[xp_ana_management_from_count]
GO
DROP PROCEDURE [dbo].[xp_ana_management_size]
GO
DROP PROCEDURE [dbo].[xp_information_exchange]
GO
DROP PROCEDURE [dbo].[xp_information_exchange_button_count]
GO
DROP PROCEDURE [dbo].[xp_information_exchange_dashboard]
GO
DROP PROCEDURE [dbo].[xp_information_exchange_form_count]
GO
DROP PROCEDURE [dbo].[xp_information_exchange_report_in]
GO
DROP PROCEDURE [dbo].[xp_information_exchange_report_out]
GO
DROP PROCEDURE [dbo].[xp_information_exchange_size]
GO
DROP PROCEDURE [dbo].[xp_member_org]
GO
DROP PROCEDURE [dbo].[xp_member_role_name]
GO
DROP PROCEDURE [dbo].[xp_message]
GO
DROP PROCEDURE [dbo].[xp_message_button_count]
GO
DROP PROCEDURE [dbo].[xp_message_dashboard]
GO
DROP PROCEDURE [dbo].[xp_message_form_count]
GO
DROP PROCEDURE [dbo].[xp_message_group_org_name]
GO
DROP PROCEDURE [dbo].[xp_message_month_report]
GO
DROP PROCEDURE [dbo].[xp_message_month_report_list]
GO
DROP PROCEDURE [dbo].[xp_message_size]
GO
DROP PROCEDURE [dbo].[xp_message_week_report]
GO
DROP PROCEDURE [dbo].[xp_news_management]
GO
DROP PROCEDURE [dbo].[xp_news_management_button_count]
GO
DROP PROCEDURE [dbo].[xp_news_management_from_count]
GO
DROP PROCEDURE [dbo].[xp_news_management_size]
GO
DROP PROCEDURE [dbo].[xp_notification]
GO
DROP PROCEDURE [dbo].[xp_notification_button_count]
GO
DROP PROCEDURE [dbo].[xp_notification_dashboard]
GO
DROP PROCEDURE [dbo].[xp_notification_form_count]
GO
DROP PROCEDURE [dbo].[xp_notification_size]
GO
DROP PROCEDURE [dbo].[xp_org_sign]
GO
DROP PROCEDURE [dbo].[xp_public_dashboard]
GO
DROP PROCEDURE [dbo].[xp_weakness_management]
GO
DROP PROCEDURE [dbo].[xp_weakness_management_button_count]
GO
DROP PROCEDURE [dbo].[xp_weakness_management_from_count]
GO
DROP PROCEDURE [dbo].[xp_weakness_management_size]
GO
USE [hisac]
GO
/****** Object:  StoredProcedure [dbo].[xp_activity_management]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE  PROCEDURE [dbo].[xp_activity_management] 
	@start bigint,
	@maxRows bigint,
	@dir bit,
	@sort nvarchar(30),
	@RoleId bigint,
	@MemberId bigint = NULL,

	@PostType nchar(1),
	@PostDateTime datetime = NULL,
	@Title nvarchar(255) = NULL,
	@IsEnable bit = NULL,
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
10-會員機構聯絡人
11-會員機構管理者

*/


IF @RoleId=0 /* 訪客 For 公開資訊那4個窗格讀取資料用 */

    SELECT
        *
    FROM
        v_activity_management_member
    WHERE
        PostType = ISNULL(@PostType, PostType)
		AND PostDateTime = ISNULL(@PostDateTime, PostDateTime)
		AND IsEnable = ISNULL(@IsEnable, IsEnable)
		AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
		AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
		AND Status = ISNULL(@Status, Status)
    ORDER BY
        postDateTime DESC
    OFFSET  @start ROWS FETCH NEXT @maxRows ROWS ONLY

ELSE IF @RoleId=1 OR @RoleId=2 OR @RoleId=3 OR @RoleId=4 /* 1-SuperAdmin、2-H-ISAC管理者、3-H-ISAC內容維護者、4-H-ISAC內容審核者 */

	IF (@Status = '1')
	
		SELECT
			*
		FROM
			v_activity_management_member
		WHERE
			PostType = ISNULL(@PostType, PostType)
			AND PostDateTime = ISNULL(@PostDateTime, PostDateTime)
			AND (Title LIKE '%' + ISNULL(@Title, '') + '%' OR Content LIKE '%' + ISNULL(@Title, '') + '%' )
			AND IsEnable = ISNULL(@IsEnable, IsEnable)
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
			CASE WHEN @sort = 'status' AND @dir = 0 THEN Status end ASC,
			CASE WHEN @sort = 'status' AND @dir = 1 THEN Status end DESC
		OFFSET  @start ROWS FETCH NEXT @maxRows ROWS ONLY

	ELSE
    
		SELECT
			*
		FROM
			v_activity_management_member
		WHERE
			PostType = ISNULL(@PostType, PostType)
			AND PostDateTime = ISNULL(@PostDateTime, PostDateTime)
			AND (Title LIKE '%' + ISNULL(@Title, '') + '%' OR Content LIKE '%' + ISNULL(@Title, '') + '%' )
			AND IsEnable = ISNULL(@IsEnable, IsEnable)
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
			CASE WHEN @sort = 'status' AND @dir = 0 THEN Status end ASC,
			CASE WHEN @sort = 'status' AND @dir = 1 THEN Status end DESC
		OFFSET  @start ROWS FETCH NEXT @maxRows ROWS ONLY


END
GO
/****** Object:  StoredProcedure [dbo].[xp_activity_management_button_count]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[xp_activity_management_button_count] 
	@RoleId bigint,
	@MemberId bigint = NULL,

	@PostType nchar(1),
	@PostDateTime datetime = NULL,
	@Title nvarchar(255) = NULL,
	@IsEnable bit = NULL,
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
	10-會員機構聯絡人
	11-會員機構管理者

	*/
	
	IF @RoleId=1 OR @RoleId=2 /* 1-SuperAdmin、2-H-ISAC管理者，統計階段 1-編輯中、2-撤銷中、3-審核中、6-編輯中(退回) 之件數 */
		BEGIN
			SELECT
				Status,
				COUNT(Id) AS Count
			FROM
				v_activity_management_member
			WHERE
				PostType = ISNULL( @PostType, PostType)
				AND PostDateTime = ISNULL( @PostDateTime, PostDateTime)
				AND (Title LIKE '%' + ISNULL( @Title, '') + '%' OR Content LIKE '%' + ISNULL( @Title, '') + '%' )
				AND IsEnable = ISNULL( @IsEnable, IsEnable)
				AND Status IN (1, 2, 3, 6)
			GROUP BY Status
		END
	ELSE IF @RoleId=3 /* 3-H-ISAC內容維護者，統計階段 1-編輯中、2-撤銷中、6-編輯中(退回) 之件數 */
		BEGIN
			SELECT
				Status,
				COUNT(Id) AS Count
			FROM
				v_activity_management_member
			WHERE
				PostType = ISNULL( @PostType, PostType)
				AND PostDateTime = ISNULL( @PostDateTime, PostDateTime)
				AND (Title LIKE '%' + ISNULL( @Title, '') + '%' OR Content LIKE '%' + ISNULL( @Title, '') + '%' )
				AND IsEnable = ISNULL( @IsEnable, IsEnable)
				AND Status IN (1, 2, 6)
			GROUP BY Status
		END
	ELSE IF @RoleId=4 /* 4-H-ISAC內容審核者，統計階段 3-審核中 之件數 */
		BEGIN
			SELECT
				Status,
				COUNT(Id) AS Count
			FROM
				v_activity_management_member
			WHERE
				PostType = ISNULL( @PostType, PostType)
				AND PostDateTime = ISNULL( @PostDateTime, PostDateTime)
				AND (Title LIKE '%' + ISNULL( @Title, '') + '%' OR Content LIKE '%' + ISNULL( @Title, '') + '%' )
				AND IsEnable = ISNULL( @IsEnable, IsEnable)
				AND Status = 3
			GROUP BY Status
		END
END
GO
/****** Object:  StoredProcedure [dbo].[xp_activity_management_from_count]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE  PROCEDURE [dbo].[xp_activity_management_from_count] 
	@RoleId bigint,
	@MemberId bigint = NULL
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
10-會員機構聯絡人
11-會員機構管理者

*/


IF @RoleId=1 OR @RoleId=2 /* 1-SuperAdmin、2-H-ISAC管理者、3-H-ISAC內容維護者，統計階段 1-編輯中、2-撤銷中、3-審核中、6-編輯中(退回) 之件數 */
	BEGIN
		SELECT
			COUNT(Id)
		FROM
			v_activity_management_member
		WHERE
			PostType = '2'
			AND Status IN (1, 2, 3, 6)
	END
ELSE IF @RoleId=3 /* 3-H-ISAC內容維護者，統計階段 1-編輯中、2-撤銷中、6-編輯中(退回) 之件數 */
	BEGIN
		SELECT
			COUNT(Id)
		FROM
			v_activity_management_member
		WHERE
			PostType = '2'
			AND Status IN (1, 2, 6)
	END
ELSE IF @RoleId=4 /* 4-H-ISAC內容審核者，統計階段 3-審核中 之件數 */
	BEGIN
		SELECT
			COUNT(Id)
		FROM
			v_activity_management_member
		WHERE
			PostType = '2'
			AND Status IN (3)
	END
END
GO
/****** Object:  StoredProcedure [dbo].[xp_activity_management_size]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE  PROCEDURE [dbo].[xp_activity_management_size] 
	@RoleId bigint,
	@MemberId bigint = NULL,

	@PostType nchar(1),
	@PostDateTime datetime = NULL,
	@Title nvarchar(255) = NULL,
	@IsEnable bit = NULL,
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
10-會員機構聯絡人
11-會員機構管理者

*/


IF @RoleId=0 /* 訪客 For 公開資訊那4個窗格讀取資料用 */

	SELECT
		COUNT(Id)
	FROM
		v_activity_management_member
	WHERE
		PostType = ISNULL( @PostType, PostType)
		AND PostDateTime = ISNULL( @PostDateTime, PostDateTime)
		AND IsEnable = ISNULL( @IsEnable, IsEnable)
		AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
		AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
		AND Status = ISNULL( @Status, Status)

ELSE IF @RoleId=1 OR @RoleId=2 OR @RoleId=3 OR @RoleId=4 /* 1-SuperAdmin、2-H-ISAC管理者、3-H-ISAC內容維護者、4-H-ISAC內容審核者 */

	IF (@Status = '1')

		SELECT
			COUNT(Id)
		FROM
			v_activity_management_member
		WHERE
			PostType = ISNULL( @PostType, PostType)
			AND PostDateTime = ISNULL( @PostDateTime, PostDateTime)
			AND (Title LIKE '%' + ISNULL( @Title, '') + '%' OR Content LIKE '%' + ISNULL( @Title, '') + '%' )
			AND IsEnable = ISNULL( @IsEnable, IsEnable)
			AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
			AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
			AND Status IN (1, 6)

	ELSE
	
		SELECT
			COUNT(Id)
		FROM
			v_activity_management_member
		WHERE
			PostType = ISNULL( @PostType, PostType)
			AND PostDateTime = ISNULL( @PostDateTime, PostDateTime)
			AND (Title LIKE '%' + ISNULL( @Title, '') + '%' OR Content LIKE '%' + ISNULL( @Title, '') + '%' )
			AND IsEnable = ISNULL( @IsEnable, IsEnable)
			AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
			AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
			AND Status = ISNULL( @Status, Status)

END
GO
/****** Object:  StoredProcedure [dbo].[xp_ana_management]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE  PROCEDURE [dbo].[xp_ana_management] 
	@start bigint,
	@maxRows bigint,
	@dir bit,
	@sort nvarchar(30),
	@RoleId bigint,
	@MemberId bigint = NULL,

	@IncidentTitle nvarchar(MAX) = NULL,
	@IncidentDiscoveryTime datetime = NULL,
	@IncidentReportedTime datetime = NULL,
	@EventTypeCode varchar(8) = NULL,
	@ImpactQualification tinyint = NULL,
	@IsEnable bit = NULL,
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
10-會員機構聯絡人
11-會員機構管理者

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
		AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
		AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
		AND Status = ISNULL(@Status, Status)
    ORDER BY
        IncidentReportedTime DESC
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
			AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
			AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
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
			CASE WHEN @sort = 'status' AND @dir = 0 THEN Status end ASC,
			CASE WHEN @sort = 'status' AND @dir = 1 THEN Status end DESC
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
			AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
			AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
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
			CASE WHEN @sort = 'status' AND @dir = 0 THEN Status end ASC,
			CASE WHEN @sort = 'status' AND @dir = 1 THEN Status end DESC
		OFFSET  @start ROWS FETCH NEXT @maxRows ROWS ONLY


END
GO
/****** Object:  StoredProcedure [dbo].[xp_ana_management_button_count]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[xp_ana_management_button_count] 
	@RoleId bigint,
	@MemberId bigint = NULL,

	@IncidentTitle nvarchar(MAX) = NULL,
	@IncidentDiscoveryTime datetime = NULL,
	@IncidentReportedTime datetime = NULL,
	@EventTypeCode varchar(8) = NULL,
	@ImpactQualification tinyint = NULL,
	@IsEnable bit = NULL,
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
	10-會員機構聯絡人
	11-會員機構管理者

	*/
	
	IF @RoleId=1 OR @RoleId=2 /* 1-SuperAdmin、2-H-ISAC管理者、3-H-ISAC內容維護者，統計階段 1-編輯中、2-撤銷中、3-審核中、6-編輯中(退回) 之件數 */
		BEGIN
			SELECT
				Status,
				COUNT(Id) AS Count
			FROM
				v_ana_management_member
			WHERE
				IncidentTitle LIKE '%' + ISNULL(@IncidentTitle, '') + '%'
				AND IncidentDiscoveryTime = ISNULL(@IncidentDiscoveryTime, IncidentDiscoveryTime)
				AND IncidentReportedTime = ISNULL(@IncidentReportedTime, IncidentReportedTime)
				AND EventTypeCode = ISNULL(@EventTypeCode, EventTypeCode)
				AND ImpactQualification = ISNULL(@ImpactQualification, ImpactQualification)
				AND IsEnable = ISNULL(@IsEnable, IsEnable)
				AND Status IN (1, 2, 3, 6)
			GROUP BY Status
		END
	ELSE IF @RoleId=3 /* 3-H-ISAC內容維護者，統計階段 1-編輯中、2-撤銷中、6-編輯中(退回) 之件數 */
		BEGIN
			SELECT
				Status,
				COUNT(Id) AS Count
			FROM
				v_ana_management_member
			WHERE
				IncidentTitle LIKE '%' + ISNULL(@IncidentTitle, '') + '%'
				AND IncidentDiscoveryTime = ISNULL(@IncidentDiscoveryTime, IncidentDiscoveryTime)
				AND IncidentReportedTime = ISNULL(@IncidentReportedTime, IncidentReportedTime)
				AND EventTypeCode = ISNULL(@EventTypeCode, EventTypeCode)
				AND ImpactQualification = ISNULL(@ImpactQualification, ImpactQualification)
				AND IsEnable = ISNULL(@IsEnable, IsEnable)
				AND Status IN (1, 2, 6)
			GROUP BY Status
		END
	ELSE IF @RoleId=4 /* 4-H-ISAC內容審核者，統計階段 3-審核中 之件數 */
		BEGIN
			SELECT
				Status,
				COUNT(Id) AS Count
			FROM
				v_ana_management_member
			WHERE
				IncidentTitle LIKE '%' + ISNULL(@IncidentTitle, '') + '%'
				AND IncidentDiscoveryTime = ISNULL(@IncidentDiscoveryTime, IncidentDiscoveryTime)
				AND IncidentReportedTime = ISNULL(@IncidentReportedTime, IncidentReportedTime)
				AND EventTypeCode = ISNULL(@EventTypeCode, EventTypeCode)
				AND ImpactQualification = ISNULL(@ImpactQualification, ImpactQualification)
				AND IsEnable = ISNULL(@IsEnable, IsEnable)
				AND Status = 3
			GROUP BY Status
		END
END
GO
/****** Object:  StoredProcedure [dbo].[xp_ana_management_from_count]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE  PROCEDURE [dbo].[xp_ana_management_from_count] 
	@RoleId bigint,
	@MemberId bigint = NULL
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
10-會員機構聯絡人
11-會員機構管理者

*/

IF @RoleId=1 OR @RoleId=2 /* 1-SuperAdmin、2-H-ISAC管理者、3-H-ISAC內容維護者，統計階段 1-編輯中、2-撤銷中、3-審核中、6-編輯中(退回) 之件數 */
	BEGIN
		SELECT
			COUNT(Id)
		FROM
			v_ana_management_member
		WHERE
			Status IN (1, 2, 3, 6)
	END
ELSE IF @RoleId=3 /* 3-H-ISAC內容維護者，統計階段 1-編輯中、2-撤銷中、6-編輯中(退回) 之件數 */
	BEGIN
		SELECT
			COUNT(Id)
		FROM
			v_ana_management_member
		WHERE
			Status IN (1, 2, 6)
	END
ELSE IF @RoleId=4 /* 4-H-ISAC內容審核者，統計階段 3-審核中 之件數 */
	BEGIN
		SELECT
			COUNT(Id)
		FROM
			v_ana_management_member
		WHERE
			Status IN (3)
	END
END
GO
/****** Object:  StoredProcedure [dbo].[xp_ana_management_size]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE  PROCEDURE [dbo].[xp_ana_management_size] 
	@RoleId bigint,
	@MemberId bigint = NULL,

	@IncidentTitle nvarchar(MAX) = NULL,
	@IncidentDiscoveryTime datetime = NULL,
	@IncidentReportedTime datetime = NULL,
	@EventTypeCode varchar(8) = NULL,
	@ImpactQualification tinyint = NULL,
	@IsEnable bit = NULL,
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
10-會員機構聯絡人
11-會員機構管理者

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
		AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
		AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
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
			AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
			AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
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
			AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
			AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
			AND Status = ISNULL(@Status, Status)

END
GO
/****** Object:  StoredProcedure [dbo].[xp_information_exchange]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[xp_information_exchange]
 @start bigint , 
 @maxRows bigint, 
 @dir bit, 
 @sort varchar(30), 

 @RoleId bigint, 
 @OrgId  bigint , 
 @MemberId bigint =NULL, 
 @Status bigint,
 @Keyword nvarchar(30) , 
 @SourceCode nvarchar(30)  =NULL , 
 @SModifyTime datetime =NULL, 
 @EModifyTime datetime =NULL,
 @StixTitle varchar(8) = NULL
AS

BEGIN

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
10-會員機構聯絡人
11-會員機構管理者

*/


SET NOCOUNT ON;
	IF @RoleId=2 or @RoleId=13 or @RoleId=1 /*2-H-ISAC管理者  13-HISAC情資維護者 1-SuperAdmin */
			select a.*
			from information_exchange a 
			 where ((@Status = 1 and (a.Status = 1 or a.Status = 8)) 
          or (@Status = 2 and a.Status = 2)
          or (@Status = 3 and a.Status = 3)
          or (@Status = 4 and a.Status = 4) 
          or (@Status = 5 and a.Status = 5)  
          or (@Status = 6 and a.Status = 6)  
          or (@Status = 7 and a.Status = 7)           
          or  @Status = 0) 
			      and (a.SourceCode = ISNULL( @SourceCode,a.SourceCode) or @SourceCode is null) 
				  and (a.ModifyTime >= ISNULL(@SModifyTime,a.ModifyTime) or @SModifyTime is null)
				  and (a.ModifyTime <= ISNULL(@EModifyTime,a.ModifyTime) or @EModifyTime is null)
				  and (a.StixTitle like '%'+@StixTitle+'%')
				  and ( a.IncidentTitle like '%'+@Keyword+'%' or a.Description like '%'+@Keyword+'%' )

			order by 
				  case when @sort = 'postId' and @dir = 0  then a.PostId end ASC,
				  case when @sort = 'postId' and @dir = 1  then a.PostId end DESC, 
				  case when @sort = 'modifyTime' and @dir = 0  then a.ModifyTime end ASC,
				  case when @sort = 'modifyTime' and @dir = 1  then a.ModifyTime end DESC, 
				  case when @sort = 'createTime' and @dir = 0  then a.CreateTime end ASC,
				  case when @sort = 'createTime' and @dir = 1  then a.CreateTime end DESC, 
				  case when @sort = 'sourceCode' and @dir = 0  then a.SourceCode end ASC,
				  case when @sort = 'sourceCode' and @dir = 1  then a.SourceCode end DESC, 
				  case when @sort = 'stixTitle' and @dir = 0  then a.StixTitle end ASC,
				  case when @sort = 'stixTitle' and @dir = 1  then a.StixTitle end DESC, 
				  case when @sort = 'category' and @dir = 0  then a.Category end ASC,
				  case when @sort = 'category' and @dir = 1  then a.Category end DESC, 
				  case when @sort = 'status' and @dir = 0 then a.Status end ASC, 
				  case when @sort = 'status' and @dir = 1 then a.Status end DESC

				  offset  @start rows fetch next  @maxRows rows only 


    IF   @RoleId=14 /*14-HISAC-情資審核者 */

	     select a.*
			from information_exchange a 
			 where ((@Status = 1 and (a.Status = 1 or a.Status = 8)) 
          or (@Status = 2 and a.Status = 2)
          or (@Status = 3 and a.Status = 3)
          or (@Status = 4 and a.Status = 4) 
          or (@Status = 5 and a.Status = 5)  
          or (@Status = 6 and a.Status = 6)  
          or (@Status = 7 and a.Status = 7)           
          or  @Status = 0) 
			      and (a.SourceCode = ISNULL( @SourceCode,a.SourceCode) or @SourceCode is null) 
				  and (a.ModifyTime >= ISNULL(@SModifyTime,a.ModifyTime) or @SModifyTime is null)
				  and (a.ModifyTime <= ISNULL(@EModifyTime,a.ModifyTime) or @EModifyTime is null)
				  and (a.StixTitle like '%'+@StixTitle+'%')
				  and ( a.IncidentTitle like '%'+@Keyword+'%' or a.Description like '%'+@Keyword+'%' )
				  and a.Status!=1
			order by 
				  case when @sort = 'postId' and @dir = 0  then a.PostId end ASC,
				  case when @sort = 'postId' and @dir = 1  then a.PostId end DESC, 
				  case when @sort = 'modifyTime' and @dir = 0  then a.ModifyTime end ASC,
				  case when @sort = 'modifyTime' and @dir = 1  then a.ModifyTime end DESC, 
				  case when @sort = 'createTime' and @dir = 0  then a.CreateTime end ASC,
				  case when @sort = 'createTime' and @dir = 1  then a.CreateTime end DESC, 
				  case when @sort = 'sourceCode' and @dir = 0  then a.SourceCode end ASC,
				  case when @sort = 'sourceCode' and @dir = 1  then a.SourceCode end DESC, 
				  case when @sort = 'stixTitle' and @dir = 0  then a.StixTitle end ASC,
				  case when @sort = 'stixTitle' and @dir = 1  then a.StixTitle end DESC, 
				  case when @sort = 'category' and @dir = 0  then a.Category end ASC,
				  case when @sort = 'category' and @dir = 1  then a.Category end DESC, 
				  case when @sort = 'status' and @dir = 0 then a.Status end ASC, 
				  case when @sort = 'status' and @dir = 1 then a.Status end DESC

				  offset  @start rows fetch next  @maxRows rows only 
	

END
GO
/****** Object:  StoredProcedure [dbo].[xp_information_exchange_button_count]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[xp_information_exchange_button_count] 
 @RoleId bigint, 
 @OrgId bigint, 
 @MemberId bigint =NULL, 
 @Status bigint,  
 @Keyword nvarchar (30) , 
 @SourceCode nvarchar(30)  =NULL , 
 @SModifyTime datetime =NULL, 
 @EModifyTime datetime =NULL,
 @StixTitle varchar(8) = NULL 


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
10-會員機構聯絡人
11-會員機構管理者

*/


 IF @RoleId=13 
          SELECT 
			a.Status as Status,count(a.Id) as Count
			  FROM   information_exchange  a
		   where
			a.Status =1
           or  	a.Status =2
           or  	a.Status =8
		    group by a.Status  		    

ELSE IF @RoleId=14
 SELECT 
			a.Status as Status,count(a.Id) as Count
		 FROM   information_exchange  a
		   where
			a.Status =3          
			   group by a.Status  		    	      

END
GO
/****** Object:  StoredProcedure [dbo].[xp_information_exchange_dashboard]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[xp_information_exchange_dashboard]
	@Sdate datetime =NULL, 
	@Edate datetime =NULL 
AS

BEGIN

SET NOCOUNT ON;

	
	select ISNULL(a.SourceCode, '') as Name, count(a.SourceCode) as count
	from information_exchange a
	where
	   ( a.CreateTime >= ISNULL(@Sdate,a.CreateTime) or @Sdate is null) 
	  and ( a.CreateTime <= ISNULL(@Edate,a.CreateTime) or @Edate is null) 
	  --and (a.Status = 4 or a.Status = 5 or a.Status = 6)
	group by a.SourceCode

END
GO
/****** Object:  StoredProcedure [dbo].[xp_information_exchange_form_count]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[xp_information_exchange_form_count] 
 @RoleId bigint, 
 @OrgId bigint, 
 @MemberId bigint =NULL, 
 @Status nvarchar =NULL, 
 @Keyword nvarchar (30) , 
 @SModifyTime datetime =NULL, 
 @EModifyTime datetime =NULL 


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
10-會員機構聯絡人
11-會員機構管理者

*/


 IF @RoleId=13 
          SELECT 
			count(a.Id)
			FROM   information_exchange  a
			  where
           		a.Status =1
           or  	a.Status =2
           or  	a.Status =8
ELSE IF @RoleId=14
 SELECT 
			count(a.Id)
			FROM   information_exchange  a
			  where
           		a.Status =3          

END
GO
/****** Object:  StoredProcedure [dbo].[xp_information_exchange_report_in]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[xp_information_exchange_report_in]
	@Sdate datetime =NULL, 
	@Edate datetime =NULL 
AS

BEGIN

SET NOCOUNT ON;

	select a.StixTitle as alertCode,a.Category, a.SourceCode as TransferType, COUNT(a.Id) as Count 
	from information_exchange a
	WHERE 
	      ( a.CreateTime >= ISNULL(@Sdate,a.CreateTime) or @Sdate is null) 
	  and ( a.CreateTime <= ISNULL(@Edate,a.CreateTime) or @Edate is null) 
	GROUP BY a.StixTitle,a.Category, a.SourceCode;

END
GO
/****** Object:  StoredProcedure [dbo].[xp_information_exchange_report_out]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[xp_information_exchange_report_out]
	@Sdate datetime =NULL, 
	@Edate datetime =NULL 
AS

BEGIN

SET NOCOUNT ON;

	select a.StixTitle as alertCode,a.Category,a.TransferOutType as TransferType , COUNT(a.Id) as Count 
	from information_exchange a
	WHERE
	   a.TransferOutType !=0 
	  and ( a.CreateTime >= ISNULL(@Sdate,a.CreateTime) or @Sdate is null) 
	  and ( a.CreateTime <= ISNULL(@Edate,a.CreateTime) or @Edate is null) 
	GROUP BY a.StixTitle,a.Category, a.TransferOutType;

END
GO
/****** Object:  StoredProcedure [dbo].[xp_information_exchange_size]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[xp_information_exchange_size] 
 @RoleId bigint, 
 @OrgId bigint, 
 @MemberId bigint =NULL, 
 @Status bigint, 
 @Keyword nvarchar (30) , 
 @SourceCode nvarchar(30)  =NULL , 
 @SModifyTime datetime =NULL, 
 @EModifyTime datetime =NULL ,
 @StixTitle varchar(8) = NULL


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
10-會員機構聯絡人
11-會員機構管理者

*/




	IF @RoleId=2 or @RoleId=13 or @RoleId=1 /*2-H-ISAC管理者  13-HISAC情資維護者 1-SuperAdmin */
	    SELECT 
			count(a.Id)
        FROM   information_exchange  a
		 where ((@Status = 1 and (a.Status = 1 or a.Status = 8)) 
          or (@Status = 2 and a.Status = 2)
          or (@Status = 3 and a.Status = 3)
          or (@Status = 4 and a.Status = 4) 
          or (@Status = 5 and a.Status = 5)  
          or (@Status = 6 and a.Status = 6)  
          or (@Status = 7 and a.Status = 7)           
          or  @Status = 0) 
		  and (a.SourceCode = ISNULL( @SourceCode,a.SourceCode) or @SourceCode is null) 
          and (a.ModifyTime >= ISNULL(@SModifyTime,a.ModifyTime) or @SModifyTime is null)
          and (a.ModifyTime <= ISNULL(@EModifyTime,a.ModifyTime) or @EModifyTime is null)
          and (a.StixTitle like '%'+@StixTitle+'%')
          and ( a.IncidentTitle like '%'+@Keyword+'%' or a.Description like '%'+@Keyword+'%' )

     IF   @RoleId=14 /*14-HISAC-情資審核者 */
	    SELECT 
			count(a.Id)
        FROM   information_exchange  a
		 where ((@Status = 1 and (a.Status = 1 or a.Status = 8)) 
          or (@Status = 2 and a.Status = 2)
          or (@Status = 3 and a.Status = 3)
          or (@Status = 4 and a.Status = 4) 
          or (@Status = 5 and a.Status = 5)  
          or (@Status = 6 and a.Status = 6)  
          or (@Status = 7 and a.Status = 7)           
           or  @Status = 0) 
		   and (a.SourceCode = ISNULL( @SourceCode,a.SourceCode) or @SourceCode is null) 
          and (a.ModifyTime >= ISNULL(@SModifyTime,a.ModifyTime) or @SModifyTime is null)
          and (a.ModifyTime <= ISNULL(@EModifyTime,a.ModifyTime) or @EModifyTime is null)
          and (a.StixTitle like '%'+@StixTitle+'%')
          and ( a.IncidentTitle like '%'+@Keyword+'%' or a.Description like '%'+@Keyword+'%' )
		  and a.Status!=1



END
GO
/****** Object:  StoredProcedure [dbo].[xp_member_org]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

Create PROCEDURE [dbo].[xp_member_org]
 @RoleId bigint,
 @OrgId bigint

AS


BEGIN

 SET NOCOUNT ON;

      IF @RoleId=0 
		select a.Id, a.OrgId, a.Account, a.Name, a.Email, a.SpareEmail, a.MobilePhone, 
		      a.CityPhone, a.FaxPhone, a.Address, a.Department, a.Title, a.IsEnable, b.Name AS OrgName
		from member a, org b 
		where   a.OrgId = b.Id
      ELSE IF @RoleId=1 	
		select a.Id, a.OrgId, a.Account, a.Name, a.Email, a.SpareEmail, a.MobilePhone, 
		      a.CityPhone, a.FaxPhone, a.Address, a.Department, a.Title, a.IsEnable, b.Name AS OrgName
		from member a, org b 
		where  a.OrgId = b.Id

      ELSE IF @RoleId=9 	

	       select a.Id, a.OrgId, a.Account, a.Name, a.Email, a.SpareEmail, a.MobilePhone, 
		   a.CityPhone, a.FaxPhone, a.Address, a.Department, a.Title, a.IsEnable, b.Name AS OrgName
		from member a, org b 
		where  a.OrgId = b.Id and a.OrgId=@OrgId

      ELSE IF @RoleId=11 

		select a.Id, a.OrgId, a.Account, a.Name, a.Email, a.SpareEmail, a.MobilePhone, 
		   a.CityPhone, a.FaxPhone, a.Address, a.Department, a.Title, a.IsEnable, b.Name AS OrgName
		from member a, org b 
		where  a.OrgId = b.Id and a.OrgId=@OrgId

      


END


GO
/****** Object:  StoredProcedure [dbo].[xp_member_role_name]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[xp_member_role_name]
 @MemberId bigint
AS


BEGIN

 SET NOCOUNT ON;

	select *
	from 
	(

	select b.Id,b.RoleId as RoleId,c.Name as Name ,'1' as Flag 
	from member_role b, role c 
	where b.RoleId=c.Id and b.MemberId=@MemberId and b.IsEnable='1'
	union 

	select '0' as Id, a.Id as RoleId,a.Name as Name,'0' as Flag 
	from role a 
	where  a.IsEnable='1' and a.Id not in (select RoleId from member_role  where  MemberId=@MemberId)

	)  d order by RoleId
END

GO
/****** Object:  StoredProcedure [dbo].[xp_message]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE  PROCEDURE [dbo].[xp_message] 
 @start bigint , 
 @maxRows bigint, 
 @dir bit, 
 @sort nvarchar(30), 
 @RoleId bigint, 
 @OrgId  bigint , 
 @MemberId bigint =NULL, 
 @Status nvarchar(2) =NULL, 
 @IsReply bit =NULL, 
 @Keyword nvarchar(30) , 
 @SpostDateTime datetime =NULL, 
 @EpostDateTime datetime =NULL 
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
10-會員機構聯絡人
11-會員機構管理者

*/


IF @RoleId=2 or @RoleId=1


	    SELECT 
			a.*
        FROM   message  a
          where 
          ((@Status = '1' and (a.Status = '1' or a.Status = '10')) 
          or (@Status = '2' and a.Status = '2')
          or (@Status = '3' and a.Status = '3')
          or (@Status = '4' and (a.Status = '5' or a.Status = '7' or a.Status = '8')) 
          or (@Status = '5' and (a.Status = '5' and a.Id in 
          (
          	Select message_post_release.MessageId
						from message_post_release
						where message_post_release.Status = '51'						
          )))  
          or (@Status = '6' and (a.Status = '5' and a.Id in 
          (
          	Select message_post_release.MessageId
						from message_post_release
						where message_post_release.Status = '61'	
						or message_post_release.Status = '62'	
						or message_post_release.Status = '63'					
          )))
          or (@Status = '7' and a.Status = '7')
          or (@Status = '8' and a.Status = '8')
          or (@Status = '9' and a.Status = '9')
          or  @Status is NULL)               
          and ( a.IsReply = ISNULL( @IsReply,a.IsReply)  or @IsReply is null) 
          and ( a.PostDateTime >= ISNULL(@SpostDateTime,a.PostDateTime) or @SpostDateTime is null) 
          and ( a.PostDateTime <= ISNULL( @EpostDateTime,a.PostDateTime) or @SpostDateTime is null) 
          and ( a.PostId like '%'+@Keyword+'%' or a.Subject like '%'+@Keyword+'%' )
		  order by 
          case when @sort = 'postId' and @dir = 0  then a.PostId end ASC, 
          case when @sort = 'postId' and @dir = 1  then a.PostId end DESC, 
          case when @sort = 'postDateTime' and @dir = 0  then a.PostDateTime end ASC, 
          case when @sort = 'postDateTime' and @dir = 1  then a.PostDateTime end DESC, 
		  case when @sort = 'createTime' and @dir = 0  then a.CreateTime end ASC,
		  case when @sort = 'createTime' and @dir = 1  then a.CreateTime end DESC, 
          case when @sort = 'sourceCode' and @dir = 0  then a.SourceCode end ASC, 
          case when @sort = 'sourceCode' and @dir = 1  then a.SourceCode end DESC, 
          case when @sort = 'subject' and @dir = 0  then a.Subject end ASC, 
          case when @sort = 'subject' and @dir = 1  then a.Subject end DESC, 
          case when @sort = 'status' and @dir = 0  then a.Status end ASC, 
          case when @sort = 'status' and @dir = 1  then a.Status end DESC, 
          case when @sort = 'isReply' and @dir = 0  then a.IsReply end ASC, 
          case when @sort = 'isReply' and @dir = 1  then a.IsReply end DESC 
           offset  @start rows fetch next  @maxRows rows only 
		 
ELSE IF @RoleId=5 
        SELECT 
			a.*
        FROM   message  a
          where
          ((@Status = '1' and (a.Status = '1' or a.Status = '10')) 
          or (@Status = '2' and a.Status = '2')
          or (@Status = '3' and a.Status = '3')
          or (@Status = '4' and (a.Status = '5' or a.Status = '7' or a.Status = '8')) 
          or (@Status = '5' and (a.Status = '5' and a.Id in 
          (
          	Select message_post_release.MessageId
						from message_post_release
						where message_post_release.Status = '51'						
          )))  
          or (@Status = '6' and (a.Status = '5' and a.Id in 
          (
          	Select message_post_release.MessageId
						from message_post_release
						where message_post_release.Status = '61'	
						or message_post_release.Status = '62'	
						or message_post_release.Status = '63'					
          )))
          or (@Status = '7' and a.Status = '7')
      or (@Status = '8' and a.Status = '8')
          or (@Status = '9' and a.Status = '9')
  or  @Status is NULL)  
          and ( a.IsReply = ISNULL( @IsReply,a.IsReply)  or @IsReply is null) 
          and ( a.PostDateTime >= ISNULL(@SpostDateTime,a.PostDateTime) or @SpostDateTime is null) 
   and ( a.PostDateTime <= ISNULL( @EpostDateTime,a.PostDateTime) or @SpostDateTime is null) 
          and ( a.PostId like '%'+@Keyword+'%' or a.Subject like '%'+@Keyword+'%' )         

          order by 
           case when @sort = 'postId' and @dir = 0  then a.PostId end ASC, 
          case when @sort = 'postId' and @dir = 1  then a.PostId end DESC, 
          case when @sort = 'postDateTime' and @dir = 0  then a.PostDateTime end ASC, 
          case when @sort = 'postDateTime' and @dir = 1  then a.PostDateTime end DESC, 
          case when @sort = 'createTime' and @dir = 0  then a.CreateTime end ASC,
		  case when @sort = 'createTime' and @dir = 1  then a.CreateTime end DESC, 
          case when @sort = 'sourceCode' and @dir = 0  then a.SourceCode end ASC, 
          case when @sort = 'sourceCode' and @dir = 1  then a.SourceCode end DESC, 
          case when @sort = 'subject' and @dir = 0  then a.Subject end ASC, 
          case when @sort = 'subject' and @dir = 1  then a.Subject end DESC, 
          case when @sort = 'status' and @dir = 0  then a.Status end ASC, 
          case when @sort = 'status' and @dir = 1  then a.Status end DESC, 
          case when @sort = 'isReply' and @dir = 0  then a.IsReply end ASC, 
          case when @sort = 'isReply' and @dir = 1  then a.IsReply end DESC 
          offset  @start rows fetch next  @maxRows rows only            
           
ELSE IF @RoleId=6   /* 6-H-ISAC警訊審核者 */


	    SELECT 
			a.*
        FROM   message  a
          where
          ((@Status = '1' and a.Status = '10')
          or (@Status = '2' and a.Status = '2')
          or (@Status = '3' and a.Status = '3')
          or (@Status = '4' and (a.Status = '5' or a.Status = '7' or a.Status = '8')) 
          or (@Status = '5' and (a.Status = '5' and a.Id in 
          (
          	Select message_post_release.MessageId
						from message_post_release
						where message_post_release.Status = '51'						
          )))  
          or (@Status = '6' and (a.Status = '5' and a.Id in 
          (
          	Select message_post_release.MessageId
						from message_post_release
						where message_post_release.Status = '61'	
						or message_post_release.Status = '62'	
						or message_post_release.Status = '63'					
          )))     
          or (@Status = '7' and a.Status = '7')
          or (@Status = '8' and a.Status = '8')
          or (@Status = '9' and a.Status = '9')
          or  @Status is NULL)
          and ( a.IsReply = ISNULL( @IsReply,a.IsReply)  or @IsReply is null) 
          and ( a.PostDateTime >= ISNULL(@SpostDateTime,a.PostDateTime) or @SpostDateTime is null) 
          and ( a.PostDateTime <= ISNULL( @EpostDateTime,a.PostDateTime) or @SpostDateTime is null) 
          and ( a.PostId like '%'+@Keyword+'%' or a.Subject like '%'+@Keyword+'%' )
		  and a.Status !='1'  
		  order by 
          case when @sort = 'postId' and @dir = 0  then a.PostId end ASC, 
          case when @sort = 'postId' and @dir = 1  then a.PostId end DESC, 
          case when @sort = 'postDateTime' and @dir = 0  then a.PostDateTime end ASC, 
          case when @sort = 'postDateTime' and @dir = 1  then a.PostDateTime end DESC, 
		  case when @sort = 'createTime' and @dir = 0  then a.CreateTime end ASC,
		  case when @sort = 'createTime' and @dir = 1  then a.CreateTime end DESC, 
          case when @sort = 'sourceCode' and @dir = 0  then a.SourceCode end ASC, 
          case when @sort = 'sourceCode' and @dir = 1  then a.SourceCode end DESC, 
          case when @sort = 'subject' and @dir = 0  then a.Subject end ASC, 
          case when @sort = 'subject' and @dir = 1  then a.Subject end DESC, 
          case when @sort = 'status' and @dir = 0  then a.Status end ASC, 
          case when @sort = 'status' and @dir = 1  then a.Status end DESC, 
          case when @sort = 'isReply' and @dir = 0  then a.IsReply end ASC, 
          case when @sort = 'isReply' and @dir = 1  then a.IsReply end DESC 
          offset  @start rows fetch next  @maxRows rows only               


      ELSE IF @RoleId=7 


	    SELECT *
		  FROM
		   (
			   SELECT distinct
					 a.*
			  FROM   message  a
			  where   
			  ((@Status = '1' and a.Status = '1')
			  or (@Status = '2' and a.Status = '2')
			  or (@Status = '3' and a.Status = '3')
			  or (@Status = '4' and (a.Status = '5' or a.Status = '7' or a.Status = '8')) 
			  or (@Status = '5' and (a.Status = '5' and a.Id in 
			  (
          		Select message_post_release.MessageId
							from message_post_release
							where message_post_release.Status = '51'						
			  )))  
			  or (@Status = '6' and (a.Status = '5' and a.Id in 
			  (
          		Select message_post_release.MessageId
							from message_post_release
							where message_post_release.Status = '61'	
							or message_post_release.Status = '62'	
							or message_post_release.Status = '63'					
			  )))    
			  or (@Status = '7' and a.Status = '7')
			  or (@Status = '8' and a.Status = '8')
			  or (@Status = '9' and a.Status = '9')
			  or  @Status is NULL)
					  and ( a.IsReply = ISNULL( @IsReply,a.IsReply)  or @IsReply is null) 
					  and ( a.PostDateTime >= ISNULL(@SpostDateTime,a.PostDateTime) or @SpostDateTime is null) 
					  and ( a.PostDateTime <= ISNULL( @EpostDateTime,a.PostDateTime) or @SpostDateTime is null) 
					  and ( a.PostId like '%'+@Keyword+'%' or a.Subject like '%'+@Keyword+'%' )
					  and (a.Status = '5' or a.Status = '7')
					    and a.Id in
           		(
           			Select message_post_release.MessageId
           			from message_post_release
           			where (((message_post_release.OrgId in 
           			(
           					Select org_sign.OrgId
							from org_sign
							where org_sign.ParentOrgId = @OrgId 
							and org_sign.ParentOrgId in 
						(
							Select org.Id
							from org
							where org.AuthType = '1'
							and org.IsEnable = 1
						)
           			))
           			)
           			or ((message_post_release.Iscc = 1 or message_post_release.IsReview = 1)
						and (message_post_release.OrgId in   
						 (
            					Select org_sign.OrgId
							from org_sign
							where org_sign.ParentOrgId = @OrgId 
							and org_sign.ParentOrgId in 
						(
							Select org.Id
							from org
							where org.AuthType = '2'
							and org.IsEnable = 1
						)
						))
						)))	
       
                ) b

          order by 
          case when @sort = 'postId' and @dir = 0  then b.PostId end ASC, 
          case when @sort = 'postId' and @dir = 1  then b.PostId end DESC, 
          case when @sort = 'postDateTime' and @dir = 0  then b.PostDateTime end ASC, 
          case when @sort = 'postDateTime' and @dir = 1  then b.PostDateTime end DESC, 
		  case when @sort = 'createTime' and @dir = 0  then b.CreateTime end ASC,
		  case when @sort = 'createTime' and @dir = 1  then b.CreateTime end DESC, 
          case when @sort = 'sourceCode' and @dir = 0  then b.SourceCode end ASC, 
          case when @sort = 'sourceCode' and @dir = 1  then b.SourceCode end DESC, 
          case when @sort = 'subject' and @dir = 0  then b.Subject end ASC, 
          case when @sort = 'subject' and @dir = 1  then b.Subject end DESC, 
          case when @sort = 'status' and @dir = 0  then b.Status end ASC, 
          case when @sort = 'status' and @dir = 1  then b.Status end DESC, 
          case when @sort = 'isReply' and @dir = 0  then b.IsReply end ASC, 
          case when @sort = 'isReply' and @dir = 1  then b.IsReply end DESC  
          offset  @start rows fetch next  @maxRows rows only 
          
   ELSE IF @RoleId=8
           SELECT 
		 		a.*
        FROM   message  a
          where  
          ((@Status = '1' and a.Status = '1')
          or (@Status = '2' and a.Status = '2')
          or (@Status = '3' and a.Status = '3')
          or (@Status = '4' and (a.Status = '5' or a.Status = '7' or a.Status = '8'))  
          or (@Status = '5' and a.Status = '5')
          or (@Status = '6' and a.Status = '6')
          or (@Status = '7' and a.Status = '7')
          or (@Status = '8' and a.Status = '8')
          or (@Status = '9' and a.Status = '9')
          or  @Status is NULL)
          and ( a.IsReply = ISNULL( @IsReply,a.IsReply)  or @IsReply is null) 
          and ( a.PostDateTime >= ISNULL(@SpostDateTime,a.PostDateTime) or @SpostDateTime is null) 
          and ( a.PostDateTime <= ISNULL( @EpostDateTime,a.PostDateTime) or @SpostDateTime is null) 
          and ( a.PostId like '%'+@Keyword+'%' or a.Subject like '%'+@Keyword+'%' )
          and (a.Status = '5' or a.Status = '7' or a.Status = '8')
          and (a.Id in
           		(
            					Select message_post_release.MessageId
							from message_post_release
							where message_post_release.OrgId = @OrgId
           		))

          order by 
          case when @sort = 'postId' and @dir = 0  then a.PostId end ASC, 
          case when @sort = 'postId' and @dir = 1  then a.PostId end DESC, 
          case when @sort = 'postDateTime' and @dir = 0  then a.PostDateTime end ASC, 
          case when @sort = 'postDateTime' and @dir = 1  then a.PostDateTime end DESC, 
		  case when @sort = 'createTime' and @dir = 0  then a.CreateTime end ASC,
		  case when @sort = 'createTime' and @dir = 1  then a.CreateTime end DESC, 
          case when @sort = 'sourceCode' and @dir = 0  then a.SourceCode end ASC, 
          case when @sort = 'sourceCode' and @dir = 1  then a.SourceCode end DESC, 
          case when @sort = 'subject' and @dir = 0  then a.Subject end ASC, 
          case when @sort = 'subject' and @dir = 1  then a.Subject end DESC, 
          case when @sort = 'status' and @dir = 0  then a.Status end ASC, 
          case when @sort = 'status' and @dir = 1  then a.Status end DESC, 
          case when @sort = 'isReply' and @dir = 0  then a.IsReply end ASC, 
          case when @sort = 'isReply' and @dir = 1  then a.IsReply end DESC  
          offset  @start rows fetch next  @maxRows rows only 

      ELSE IF @RoleId=10
           SELECT 
			 a.*
        FROM   message  a
          where
          ((@Status = '1' and a.Status = '1')  
          or (@Status = '2' and a.Status = '2')  
          or (@Status = '3' and a.Status = '3')  
          or (@Status = '4' and (a.Status = '5' or a.Status = '7' or a.Status = '8')) 
          or (@Status = '5' and (a.Status = '5' and a.Id in 
          (
          	Select message_post_release.MessageId
						from message_post_release
						where  message_post_release.OrgId = @OrgId  
						and message_post_release.Status = '51'						
          )))  
          or (@Status = '6' and (a.Status = '5' and a.Id in 
          (
          	Select message_post_release.MessageId
						from message_post_release
						where  message_post_release.OrgId = @OrgId  
						and (message_post_release.Status = '61'	
						or message_post_release.Status = '62'	
						or message_post_release.Status = '63')					
          )))
          or (@Status = '7' and a.Status = '7')  
          or (@Status = '8' and a.Status = '8')  
          or (@Status = '9' and a.Status = '9')
          or  @Status is NULL)
          and ( a.IsReply = ISNULL( @IsReply,a.IsReply)  or @IsReply is null) 
          and ( a.PostDateTime >= ISNULL(@SpostDateTime,a.PostDateTime) or @SpostDateTime is null) 
          and ( a.PostDateTime <= ISNULL( @EpostDateTime,a.PostDateTime) or @SpostDateTime is null) 
          and ( a.PostId like '%'+@Keyword+'%' or a.Subject like '%'+@Keyword+'%' )
          and (a.Status = '5' or a.Status = '7' or a.Status = '8')
          and (a.Id in
           		(
            					Select message_post_release.MessageId
							from message_post_release
							where message_post_release.OrgId = @OrgId
           		))	

          order by 
          case when @sort = 'postId' and @dir = 0  then a.PostId end ASC, 
          case when @sort = 'postId' and @dir = 1  then a.PostId end DESC, 
          case when @sort = 'postDateTime' and @dir = 0  then a.PostDateTime end ASC, 
          case when @sort = 'postDateTime' and @dir = 1  then a.PostDateTime end DESC, 
		  case when @sort = 'createTime' and @dir = 0  then a.CreateTime end ASC,
		  case when @sort = 'createTime' and @dir = 1  then a.CreateTime end DESC, 
          case when @sort = 'sourceCode' and @dir = 0  then a.SourceCode end ASC, 
          case when @sort = 'sourceCode' and @dir = 1  then a.SourceCode end DESC, 
          case when @sort = 'subject' and @dir = 0  then a.Subject end ASC, 
          case when @sort = 'subject' and @dir = 1  then a.Subject end DESC, 
          case when @sort = 'status' and @dir = 0  then a.Status end ASC, 
          case when @sort = 'status' and @dir = 1  then a.Status end DESC, 
          case when @sort = 'isReply' and @dir = 0  then a.IsReply end ASC, 
 case when @sort = 'isReply' and @dir = 1  then a.IsReply end DESC 
          offset  @start rows fetch next  @maxRows rows only 

       


END

GO
/****** Object:  StoredProcedure [dbo].[xp_message_button_count]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[xp_message_button_count] 
 @RoleId bigint, 
 @OrgId bigint, 
 @MemberId bigint =NULL, 
 @Status nvarchar(2) =NULL, 
 @IsReply bit =NULL, 
 @Keyword nvarchar(30) , 
 @SpostDateTime datetime =NULL, 
 @EpostDateTime datetime =NULL 


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
10-會員機構聯絡人
11-會員機構管理者

	a.Status as Status,count(a.Id) as Count
		 group by a.Status  		
*/


    IF @RoleId=5 
          SELECT 
			a.Status as Status,count(a.Id) as Count
			FROM   message  a
			  where
           		a.Status ='1'
           or  	a.Status ='2'
           or  	a.Status ='10'
           group by a.Status  			  
	ELSE IF @RoleId=6 
          SELECT 
			DISTINCT a.Status as Status,count(a.Id) as Count
			FROM   message  a
			  where
           		a.Status ='3'
           	or (a.Status = '5'
           		and a.Id in
           		(
            					Select message_post_release.MessageId
							from message_post_release
							where message_post_release.Status ='63'										
           		))
				 group by a.Status  		
    ELSE IF @RoleId=7 
          SELECT 
			DISTINCT  a.Status as Status,count(a.Id) as Count
			FROM   message  a
			  where
           		a.Status = '5'           		  
           		and a.Id in
           		(
           			Select message_post_release.MessageId
           			from message_post_release
           			where (((message_post_release.OrgId in 
           			(
           					Select org_sign.OrgId
							from org_sign
							where org_sign.ParentOrgId = @OrgId 
							and org_sign.ParentOrgId in 
						(
							Select org.Id
							from org
							where org.AuthType = '1'
							and org.IsEnable = 1
						)
           			))
           			and message_post_release.Status = '62')
           			or ((message_post_release.Iscc = 1 or message_post_release.IsReview = 1)
						and (message_post_release.OrgId in   
						 (
            					Select org_sign.OrgId
							from org_sign
							where org_sign.ParentOrgId = @OrgId 
							and org_sign.ParentOrgId in 
						(
							Select org.Id
							from org
							where org.AuthType = '2'
							and org.IsEnable = 1
						)
						))
						and message_post_release.Status = '61')))		
					 group by a.Status  						
	 ELSE IF @RoleId=10 
          SELECT 
			DISTINCT  a.Status as Status,count(a.Id) as Count
			FROM   message  a
			where
			a.Status = '5'  
			and (a.Id in
           		(
            					Select message_post_release.MessageId
							from message_post_release
							where message_post_release.Status ='51'
							and message_post_release.OrgId = @OrgId
           		))		
			group by a.Status  					
			
    

END
GO
/****** Object:  StoredProcedure [dbo].[xp_message_dashboard]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[xp_message_dashboard]
	@Sdate datetime =NULL, 
	@Edate datetime =NULL,
	@OrgId  bigint, 
	@OrgType varchar(1) ='0' 
	
AS

BEGIN

SET NOCOUNT ON;

IF @OrgType= '0' or @OrgType= '1'
	
	select a.AlertCode as Name, count(a.AlertCode) as count
	from message a
	where a.AlertCode is not null
	  and ( a.PostDateTime >= ISNULL(@Sdate,a.PostDateTime) or @Sdate is null) 
      and ( a.PostDateTime <= ISNULL(@Edate,a.PostDateTime) or @Edate is null) 
	  and (a.Status = 5 or a.Status = 7 or a.Status = 8)
	group by a.AlertCode
	
ELSE IF @OrgType= '2'
	
	select a.AlertCode as Name, count(a.AlertCode) as count
	from message a
	where a.AlertCode is not null
	  and ( a.PostDateTime >= ISNULL(@Sdate,a.PostDateTime) or @Sdate is null) 
      and ( a.PostDateTime <= ISNULL(@Edate,a.PostDateTime) or @Edate is null) 
	  and (a.Status = 5 or a.Status = 7)
	  and (a.Id in
           		(
           			Select message_post_release.MessageId
           			from message_post_release
           			where (((message_post_release.OrgId in 
           			(
           					Select org_sign.OrgId
							from org_sign
							where org_sign.ParentOrgId = @OrgId 
							and org_sign.ParentOrgId in 
						(
							Select org.Id
							from org
							where org.AuthType = '1'
							and org.IsEnable = 1
						)
           			))
           			)
           			or ((message_post_release.Iscc = 1 or message_post_release.IsReview = 1)
						and (message_post_release.OrgId in   
						 (
            					Select org_sign.OrgId
							from org_sign
							where org_sign.ParentOrgId = @OrgId 
							and org_sign.ParentOrgId in 
						(
							Select org.Id
							from org
							where org.AuthType = '2'
							and org.IsEnable = 1
						)
						))
						)))
						or (a.Id in
           		(
            					Select message_post_release.MessageId
							from message_post_release
							where message_post_release.OrgId = @OrgId
           		))
						)
	group by a.AlertCode
	
ELSE IF @OrgType= '3'
select a.AlertCode as Name, count(a.AlertCode) as count
	from message a
	where a.AlertCode is not null
	  and ( a.PostDateTime >= ISNULL(@Sdate,a.PostDateTime) or @Sdate is null) 
      and ( a.PostDateTime <= ISNULL(@Edate,a.PostDateTime) or @Edate is null) 
	  and (a.Status = 5 or a.Status = 7 or a.Status = 8)
	  and (a.Id in
           		(
            					Select message_post_release.MessageId
							from message_post_release
							where message_post_release.OrgId = @OrgId
           		))
	group by a.AlertCode
	
END
GO
/****** Object:  StoredProcedure [dbo].[xp_message_form_count]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[xp_message_form_count] 
 @RoleId bigint, 
 @OrgId bigint, 
 @MemberId bigint =NULL, 
 @Status nvarchar(2) =NULL, 
 @IsReply bit =NULL, 
 @Keyword nvarchar(30) , 
 @SpostDateTime datetime =NULL, 
 @EpostDateTime datetime =NULL 


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
10-會員機構聯絡人
11-會員機構管理者

*/


     
        

     IF @RoleId=5 
          SELECT 
			count(a.Id)
			FROM   message  a
			  where
           		a.Status ='1'
           or  	a.Status ='2'
           or  	a.Status ='10'
			  
	ELSE IF @RoleId=6 
          SELECT 
			DISTINCT count(a.Id)
			FROM   message  a
			  where
           		a.Status ='3'
           	or (a.Status = '5'
           		and a.Id in
           		(
            					Select message_post_release.MessageId
							from message_post_release
							where message_post_release.Status ='63'										
           		))
    ELSE IF @RoleId=7 
          SELECT 
			DISTINCT count(a.Id)
			FROM   message  a
			  where
           		a.Status = '5'           		  
           		and a.Id in
           		(
           			Select message_post_release.MessageId
           			from message_post_release
           			where (((message_post_release.OrgId in 
           			(
           					Select org_sign.OrgId
							from org_sign
							where org_sign.ParentOrgId = @OrgId 
							and org_sign.ParentOrgId in 
						(
							Select org.Id
							from org
							where org.AuthType = '1'
							and org.IsEnable = 1
						)
           			))
           			and message_post_release.Status = '62')
           			or ((message_post_release.Iscc = 1 or message_post_release.IsReview = 1)
						and (message_post_release.OrgId in   
						 (
            					Select org_sign.OrgId
							from org_sign
							where org_sign.ParentOrgId = @OrgId 
							and org_sign.ParentOrgId in 
						(
							Select org.Id
							from org
							where org.AuthType = '2'
							and org.IsEnable = 1
						)
						))
						and message_post_release.Status = '61')))						
	 ELSE IF @RoleId=10 
          SELECT 
			DISTINCT count(a.Id)
			FROM   message  a
			where
			a.Status = '5'  
			and (a.Id in
           		(
            					Select message_post_release.MessageId
							from message_post_release
							where message_post_release.Status ='51'
							and message_post_release.OrgId = @OrgId
           		))			
			
    

END
GO
/****** Object:  StoredProcedure [dbo].[xp_message_group_org_name]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[xp_message_group_org_name]
 @MessageGroupId bigint
AS


BEGIN

 SET NOCOUNT ON;

	select *
	from 
	(

	select b.Id,b.Orgid,c.Name as Name ,'1' as Flag 
	from message_group_org b, org c 
	where b.Orgid=c.Id and c.OrgType='3' and c.IsEnable='1' and b.MessageGroupId=@MessageGroupId
	union 

	select '0' as Id,a.Id as OrgId,a.Name as Name,'0' as Flag 
	from org a 
	where a.OrgType='3' and a.IsEnable='1' and a.Id not in (select Orgid from message_group_org  where  MessageGroupId=@MessageGroupId)

	)  d order by d.Orgid
END

GO
/****** Object:  StoredProcedure [dbo].[xp_message_month_report]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[xp_message_month_report]
 @Sdate datetime,
 @Edate datetime
AS


BEGIN

 SET NOCOUNT ON;

 select   NEWID() as Id,e.AlertCode,e.Year,e.Month,count(e.Id) as Count
 from
    (
	select DISTINCT  a.Id as Id,b.Code as AlertCode,year(a.PostDateTime) as Year,month(a.PostDateTime) as Month
	from message a,alert_type b ,message_post c,member d,information_source e
	where a.AlertCode=b.Code and a.PostDateTime>=@Sdate and a.PostDateTime<=@Edate and a.Id=c.MessageId and a.IsEnable='1'
	and a.CreateId=d.Id and  a.SourceCode=e.Code 
	) e
	group by e.AlertCode,e.Year, e.Month 
    

END
GO
/****** Object:  StoredProcedure [dbo].[xp_message_month_report_list]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[xp_message_month_report_list]
 @Tdate datetime,
 @AlertCode nvarchar(8)
AS


BEGIN

 SET NOCOUNT ON;

 
	select DISTINCT   a.Id as Id,a.PostId as PostId,b.Code as AlertCode,a.PostDateTime as PostDateTime,a.Subject as Subject,a.Status as Status, c.Name as CreateName,a.IsReply as IsReply,e.Name as SourceName  
	from message a,alert_type b,member c,message_post d,information_source e
	where a.AlertCode=b.Code and   YEAR(a.PostDateTime)=YEAR(@Tdate) and MONTH(a.PostDateTime)=MONTH(@Tdate) and  b.Code=@AlertCode
	and a.CreateId=c.Id and a.Id=d.MessageId and a.SourceCode=e.Code 
	and a.IsEnable='1'
	
	

END
GO
/****** Object:  StoredProcedure [dbo].[xp_message_size]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[xp_message_size] 
 @RoleId bigint, 
 @OrgId bigint, 
 @MemberId bigint =NULL, 
 @Status nvarchar(2) =NULL, 
 @IsReply bit =NULL, 
 @Keyword nvarchar(30) , 
 @SpostDateTime datetime =NULL, 
 @EpostDateTime datetime =NULL 


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
10-會員機構聯絡人
11-會員機構管理者

*/


     IF @RoleId=2 or @RoleId=1

       SELECT 
        count(a.Id)
        FROM   message  a
         where 
          ((@Status = '1' and (a.Status = '1' or a.Status = '10')) 
          or (@Status = '2' and a.Status = '2')
          or (@Status = '3' and a.Status = '3')
          or (@Status = '4' and (a.Status = '5' or a.Status = '7' or a.Status = '8')) 
          or (@Status = '5' and (a.Status = '5' and a.Id in 
          (
          	Select message_post_release.MessageId
						from message_post_release
						where message_post_release.Status = '51'						
          )))  
          or (@Status = '6' and (a.Status = '5' and a.Id in 
          (
          	Select message_post_release.MessageId
						from message_post_release
						where message_post_release.Status = '61'	
						or message_post_release.Status = '62'	
						or message_post_release.Status = '63'					
          )))
          or (@Status = '7' and a.Status = '7')
          or (@Status = '8' and a.Status = '8')
          or (@Status = '9' and a.Status = '9')
          or  @Status is NULL)       
          and ( a.IsReply = ISNULL( @IsReply,a.IsReply)  or @IsReply is null) 
          and ( a.PostDateTime >= ISNULL(@SpostDateTime,a.PostDateTime) or @SpostDateTime is null) 
          and ( a.PostDateTime <= ISNULL( @EpostDateTime,a.PostDateTime) or @SpostDateTime is null) 
          and ( a.PostId like '%'+@Keyword+'%' or a.Subject like '%'+@Keyword+'%' )

      ELSE IF @RoleId=5 
          SELECT 
			count(a.Id)
			FROM   message  a
			  where
          ((@Status = '1' and (a.Status = '1' or a.Status = '10')) 
          or (@Status = '2' and a.Status = '2')
          or (@Status = '3' and a.Status = '3')
          or (@Status = '4' and (a.Status = '5' or a.Status = '7' or a.Status = '8')) 
          or (@Status = '5' and (a.Status = '5' and a.Id in 
          (
          	Select message_post_release.MessageId
						from message_post_release
						where message_post_release.Status = '51'						
          )))  
          or (@Status = '6' and (a.Status = '5' and a.Id in 
          (
          	Select message_post_release.MessageId
						from message_post_release
						where message_post_release.Status = '61'	
						or message_post_release.Status = '62'	
						or message_post_release.Status = '63'					
          )))
          or (@Status = '7' and a.Status = '7')
          or (@Status = '8' and a.Status = '8')
          or (@Status = '9' and a.Status = '9')
          or  @Status is NULL)
			  and ( a.IsReply = ISNULL( @IsReply,a.IsReply)  or @IsReply is null) 
			  and ( a.PostDateTime >= ISNULL(@SpostDateTime,a.PostDateTime) or @SpostDateTime is null) 
              and ( a.PostDateTime <= ISNULL( @EpostDateTime,a.PostDateTime) or @SpostDateTime is null) 
			  and ( a.PostId like '%'+@Keyword+'%' or a.Subject like '%'+@Keyword+'%' )   
			  
	ELSE IF @RoleId=6 
          SELECT 
			count(a.Id)
			FROM   message  a
			  where
          ((@Status = '1' and a.Status = '10')
          or (@Status = '2' and a.Status = '2')
          or (@Status = '3' and a.Status = '3')
          or (@Status = '4' and (a.Status = '5' or a.Status = '7' or a.Status = '8')) 
          or (@Status = '5' and (a.Status = '5' and a.Id in 
          (
          	Select message_post_release.MessageId
						from message_post_release
						where message_post_release.Status = '51'						
          )))  
          or (@Status = '6' and (a.Status = '5' and a.Id in 
      (
       	Select message_post_release.MessageId
						from message_post_release
						where message_post_release.Status = '61'	
						or message_post_release.Status = '62'	
						or message_post_release.Status = '63'					
          )))     
          or (@Status = '7' and a.Status = '7')
          or (@Status = '8' and a.Status = '8')
          or (@Status = '9' and a.Status = '9')
          or  @Status is NULL)
			  and ( a.IsReply = ISNULL( @IsReply,a.IsReply)  or @IsReply is null) 
			  and ( a.PostDateTime >= ISNULL(@SpostDateTime,a.PostDateTime) or @SpostDateTime is null) 
              and ( a.PostDateTime <= ISNULL( @EpostDateTime,a.PostDateTime) or @SpostDateTime is null) 
			  and ( a.PostId like '%'+@Keyword+'%' or a.Subject like '%'+@Keyword+'%' )  
			  and a.Status !='1'  


      ELSE IF @RoleId=7 

				 SELECT distinct
					count(a.Id)
					FROM   message  a
					  where   
				  ((@Status = '1' and a.Status = '1')
				  or (@Status = '2' and a.Status = '2')
				  or (@Status = '3' and a.Status = '3')
				  or (@Status = '4' and (a.Status = '5' or a.Status = '7' or a.Status = '8')) 
				  or (@Status = '5' and (a.Status = '5' and a.Id in 
				  (
          			Select message_post_release.MessageId
								from message_post_release
								where message_post_release.Status = '51'						
				  )))  
				  or (@Status = '6' and (a.Status = '5' and a.Id in 
				  (
          			Select message_post_release.MessageId
								from message_post_release
								where message_post_release.Status = '61'	
								or message_post_release.Status = '62'	
								or message_post_release.Status = '63'					
				  )))    
				  or (@Status = '7' and a.Status = '7')
				  or (@Status = '8' and a.Status = '8')
				  or (@Status = '9' and a.Status = '9')
				  or  @Status is NULL)
						  and ( a.IsReply = ISNULL( @IsReply,a.IsReply)  or @IsReply is null) 
						  and ( a.PostDateTime >= ISNULL(@SpostDateTime,a.PostDateTime) or @SpostDateTime is null) 
						  and ( a.PostDateTime <= ISNULL( @EpostDateTime,a.PostDateTime) or @SpostDateTime is null) 
						  and ( a.PostId like '%'+@Keyword+'%' or a.Subject like '%'+@Keyword+'%' )
						  and (a.Status = '5' or a.Status = '7')
						  and a.Id in
           		(
           			Select message_post_release.MessageId
           			from message_post_release
           			where (((message_post_release.OrgId in 
           			(
           					Select org_sign.OrgId
							from org_sign
							where org_sign.ParentOrgId = @OrgId 
							and org_sign.ParentOrgId in 
						(
							Select org.Id
							from org
							where org.AuthType = '1'
							and org.IsEnable = 1
						)
           			))
           			)
           			or ((message_post_release.Iscc = 1 or message_post_release.IsReview = 1)
						and (message_post_release.OrgId in   
						 (
            					Select org_sign.OrgId
							from org_sign
							where org_sign.ParentOrgId = @OrgId 
							and org_sign.ParentOrgId in 
						(
							Select org.Id
							from org
							where org.AuthType = '2'
							and org.IsEnable = 1
						)
						))
						)))	
            
      ELSE IF @RoleId=8 
         SELECT 
			count(a.Id)
			FROM   message  a
          where  
          ((@Status = '1' and a.Status = '1')
          or (@Status = '2' and a.Status = '2')
          or (@Status = '3' and a.Status = '3')
          or (@Status = '4' and (a.Status = '5' or a.Status = '7' or a.Status = '8'))  
          or (@Status = '5' and a.Status = '5')
          or (@Status = '6' and a.Status = '6')
          or (@Status = '7' and a.Status = '7')
          or (@Status = '8' and a.Status = '8')
          or (@Status = '9' and a.Status = '9')
          or  @Status is NULL)
           and ( a.IsReply = ISNULL( @IsReply,a.IsReply)  or @IsReply is null) 
           and ( a.PostDateTime >= ISNULL(@SpostDateTime,a.PostDateTime) or @SpostDateTime is null) 
           and ( a.PostDateTime <= ISNULL( @EpostDateTime,a.PostDateTime) or @SpostDateTime is null) 
           and ( a.PostId like '%'+@Keyword+'%' or a.Subject like '%'+@Keyword+'%' )
           and (a.Status = '5' or a.Status = '7' or a.Status = '8')
          and (a.Id in
           		(
            					Select message_post_release.MessageId
							from message_post_release
							where message_post_release.OrgId = @OrgId
           		))	

      ELSE IF @RoleId=10 
          SELECT
          COUNT(a.Id) 
          FROM    message  a
          where
         ((@Status = '1' and a.Status = '1')  
          or (@Status = '2' and a.Status = '2')  
          or (@Status = '3' and a.Status = '3')  
          or (@Status = '4' and (a.Status = '5' or a.Status = '7' or a.Status = '8')) 
          or (@Status = '5' and (a.Status = '5' and a.Id in 
          (
          	Select message_post_release.MessageId
						from message_post_release
						where  message_post_release.OrgId = @OrgId  
						and message_post_release.Status = '51'						
          )))  
          or (@Status = '6' and (a.Status = '5' and a.Id in 
          (
          	Select message_post_release.MessageId
						from message_post_release
						where  message_post_release.OrgId = @OrgId  
						and (message_post_release.Status = '61'	
						or message_post_release.Status = '62'	
						or message_post_release.Status = '63')					
          )))
          or (@Status = '7' and a.Status = '7')  
          or (@Status = '8' and a.Status = '8')  
          or (@Status = '9' and a.Status = '9')
          or  @Status is NULL)
           and ( a.IsReply = ISNULL( @IsReply,a.IsReply)  or @IsReply is null) 
           and ( a.PostDateTime >= ISNULL(@SpostDateTime,a.PostDateTime) or @SpostDateTime is null) 
           and ( a.PostDateTime <= ISNULL( @EpostDateTime,a.PostDateTime) or @SpostDateTime is null) 
           and ( a.PostId like '%'+@Keyword+'%' or a.Subject like '%'+@Keyword+'%' )
           and (a.Status = '5' or a.Status = '7' or a.Status = '8')
           and (a.Id in
           		(
            					Select message_post_release.MessageId
							from message_post_release
							where message_post_release.OrgId = @OrgId
           		))	

END
GO
/****** Object:  StoredProcedure [dbo].[xp_message_week_report]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[xp_message_week_report]
 @Sdate datetime,
 @Edate datetime
AS


BEGIN

 SET NOCOUNT ON;

 select e.AlertCode,e.EventCode,count(e.Id) as Count
 from
    (
	select DISTINCT a.Id as Id,a.AlertCode as AlertCode,a.EventCode as EventCode
	from message a,alert_type b,message_post c ,member d,information_source e
	where a.AlertCode=b.Code and  a.PostDateTime>=@Sdate and a.PostDateTime<=@Edate and a.Id=c.MessageId
	and a.CreateId=d.Id and  a.SourceCode=e.Code 
	) e
	
group by e.AlertCode,e.EventCode


END
GO
/****** Object:  StoredProcedure [dbo].[xp_news_management]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE  PROCEDURE [dbo].[xp_news_management] 
	@start bigint,
	@maxRows bigint,
	@dir bit,
	@sort nvarchar(30),
	@RoleId bigint,
	@MemberId bigint = NULL,

	@PostType nchar(1),
	@NewsManagementGroupId bigint = NULL,
	@PostDateTime datetime = NULL,
	@Title nvarchar(255) = NULL,
	@IsEnable bit = NULL,
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
10-會員機構聯絡人
11-會員機構管理者

*/


IF @RoleId=0 /* 訪客 For 公開資訊那4個窗格讀取資料用 */

    SELECT
        *
    FROM
        v_news_management_member
    WHERE
        PostType = ISNULL(@PostType, PostType)
		AND PostDateTime = ISNULL(@PostDateTime, PostDateTime)
		AND IsEnable = ISNULL(@IsEnable, IsEnable)
		AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
		AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
		AND Status = ISNULL(@Status, Status)
    ORDER BY
        postDateTime DESC
    OFFSET  @start ROWS FETCH NEXT @maxRows ROWS ONLY

ELSE IF @RoleId=1 OR @RoleId=2 OR @RoleId=3 OR @RoleId=4 /* 1-SuperAdmin、2-H-ISAC管理者、3-H-ISAC內容維護者、4-H-ISAC內容審核者 */

	IF (@Status = '1')
	
		SELECT
			*
		FROM
			v_news_management_member
		WHERE
			PostType = ISNULL(@PostType, PostType)
			AND NewsManagementGroupId = ISNULL(@NewsManagementGroupId, NewsManagementGroupId)
			AND PostDateTime = ISNULL(@PostDateTime, PostDateTime)
			AND (Title LIKE '%' + ISNULL(@Title, '') + '%' OR Content LIKE '%' + ISNULL(@Title, '') + '%' )
			AND IsEnable = ISNULL(@IsEnable, IsEnable)
			AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
			AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
			AND Status IN (1, 6)
		ORDER BY
			CASE WHEN @sort = 'newsManagementGroupId' AND @dir = 0 THEN NewsManagementGroupId END ASC,
			CASE WHEN @sort = 'newsManagementGroupId' AND @dir = 1 THEN NewsManagementGroupId END DESC,
			CASE WHEN @sort = 'postDateTime' AND @dir = 0 THEN PostDateTime end ASC,
			CASE WHEN @sort = 'postDateTime' AND @dir = 1 THEN PostDateTime end DESC,
			CASE WHEN @sort = 'title' AND @dir = 0 THEN Title end ASC,
			CASE WHEN @sort = 'title' AND @dir = 1 THEN Title end DESC,
			CASE WHEN @sort = 'isEnable' AND @dir = 0 THEN IsEnable end ASC,
			CASE WHEN @sort = 'isEnable' AND @dir = 1 THEN IsEnable end DESC,
			CASE WHEN @sort = 'status' AND @dir = 0 THEN Status end ASC,
			CASE WHEN @sort = 'status' AND @dir = 1 THEN Status end DESC
		OFFSET  @start ROWS FETCH NEXT @maxRows ROWS ONLY

	ELSE
    
		SELECT
			*
		FROM
			v_news_management_member
		WHERE
			PostType = ISNULL(@PostType, PostType)
			AND NewsManagementGroupId = ISNULL(@NewsManagementGroupId, NewsManagementGroupId)
			AND PostDateTime = ISNULL(@PostDateTime, PostDateTime)
			AND (Title LIKE '%' + ISNULL(@Title, '') + '%' OR Content LIKE '%' + ISNULL(@Title, '') + '%' )
			AND IsEnable = ISNULL(@IsEnable, IsEnable)
			AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
			AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
			AND Status = ISNULL(@Status, Status)
		ORDER BY
			CASE WHEN @sort = 'newsManagementGroupId' AND @dir = 0 THEN NewsManagementGroupId END ASC,
			CASE WHEN @sort = 'newsManagementGroupId' AND @dir = 1 THEN NewsManagementGroupId END DESC,
			CASE WHEN @sort = 'postDateTime' AND @dir = 0 THEN PostDateTime end ASC,
			CASE WHEN @sort = 'postDateTime' AND @dir = 1 THEN PostDateTime end DESC,
			CASE WHEN @sort = 'title' AND @dir = 0 THEN Title end ASC,
			CASE WHEN @sort = 'title' AND @dir = 1 THEN Title end DESC,
			CASE WHEN @sort = 'isEnable' AND @dir = 0 THEN IsEnable end ASC,
			CASE WHEN @sort = 'isEnable' AND @dir = 1 THEN IsEnable end DESC,
			CASE WHEN @sort = 'status' AND @dir = 0 THEN Status end ASC,
			CASE WHEN @sort = 'status' AND @dir = 1 THEN Status end DESC
		OFFSET  @start ROWS FETCH NEXT @maxRows ROWS ONLY


END
GO
/****** Object:  StoredProcedure [dbo].[xp_news_management_button_count]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[xp_news_management_button_count] 
	@RoleId bigint,
	@MemberId bigint = NULL,

	@PostType nchar(1),
	@NewsManagementGroupId bigint = NULL,
	@PostDateTime datetime = NULL,
	@Title nvarchar(255) = NULL,
	@IsEnable bit = NULL,
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
	10-會員機構聯絡人
	11-會員機構管理者

	*/
	
	IF @RoleId=1 OR @RoleId=2 /* 1-SuperAdmin、2-H-ISAC管理者 */
		BEGIN
			SELECT
				Status,
				COUNT(Id) AS Count
			FROM
				v_news_management_member
			WHERE
				PostType = ISNULL( @PostType, PostType)
				AND NewsManagementGroupId = ISNULL( @NewsManagementGroupId, NewsManagementGroupId)
				AND PostDateTime = ISNULL( @PostDateTime, PostDateTime)
				AND (Title LIKE '%' + ISNULL( @Title, '') + '%' OR Content LIKE '%' + ISNULL( @Title, '') + '%' )
				AND IsEnable = ISNULL( @IsEnable, IsEnable)
				AND Status IN (1, 2, 3, 6)
			GROUP BY Status
		END
	ELSE IF @RoleId=3 /* 3-H-ISAC內容維護者 */
		BEGIN
			SELECT
				Status,
				COUNT(Id) AS Count
			FROM
				v_news_management_member
			WHERE
				PostType = ISNULL( @PostType, PostType)
				AND NewsManagementGroupId = ISNULL( @NewsManagementGroupId, NewsManagementGroupId)
				AND PostDateTime = ISNULL( @PostDateTime, PostDateTime)
				AND (Title LIKE '%' + ISNULL( @Title, '') + '%' OR Content LIKE '%' + ISNULL( @Title, '') + '%' )
				AND IsEnable = ISNULL( @IsEnable, IsEnable)
				AND Status IN (1, 2, 6)
			GROUP BY Status
		END
	ELSE IF @RoleId=4 /* 4-H-ISAC內容審核者 */
		BEGIN
			SELECT
				Status,
				COUNT(Id) AS Count
			FROM
				v_news_management_member
			WHERE
				PostType = ISNULL( @PostType, PostType)
				AND NewsManagementGroupId = ISNULL( @NewsManagementGroupId, NewsManagementGroupId)
				AND PostDateTime = ISNULL( @PostDateTime, PostDateTime)
				AND (Title LIKE '%' + ISNULL( @Title, '') + '%' OR Content LIKE '%' + ISNULL( @Title, '') + '%' )
				AND IsEnable = ISNULL( @IsEnable, IsEnable)
				AND Status = 3
			GROUP BY Status
		END
END
GO
/****** Object:  StoredProcedure [dbo].[xp_news_management_from_count]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE  PROCEDURE [dbo].[xp_news_management_from_count] 
	@RoleId bigint,
	@MemberId bigint = NULL
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
10-會員機構聯絡人
11-會員機構管理者

*/

IF @RoleId=1 OR @RoleId=2 /* 1-SuperAdmin、2-H-ISAC管理者，統計階段 1-編輯中、2-撤銷中、3-審核中、6-編輯中(退回) 之件數 */
	BEGIN
		SELECT
			COUNT(Id)
		FROM
			v_news_management_member
		WHERE
			PostType = '1'
			AND Status IN (1, 2, 3, 6)
	END
ELSE IF @RoleId=3 /* 3-H-ISAC內容維護者，統計階段 1-編輯中、2-撤銷中、6-編輯中(退回) 之件數 */
	BEGIN
		SELECT
			COUNT(Id)
		FROM
			v_news_management_member
		WHERE
			PostType = '1'
			AND Status IN (1, 2, 6)
	END
ELSE IF @RoleId=4 /* 4-H-ISAC內容審核者，統計階段 3-審核中 之件數 */
	BEGIN
		SELECT
			COUNT(Id)
		FROM
			v_news_management_member
		WHERE
			PostType = '1'
			AND Status IN (3)
	END
END
GO
/****** Object:  StoredProcedure [dbo].[xp_news_management_size]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE  PROCEDURE [dbo].[xp_news_management_size] 
	@RoleId bigint,
	@MemberId bigint = NULL,

	@PostType nchar(1),
	@NewsManagementGroupId bigint = NULL,
	@PostDateTime datetime = NULL,
	@Title nvarchar(255) = NULL,
	@IsEnable bit = NULL,
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
10-會員機構聯絡人
11-會員機構管理者

*/


IF @RoleId=0 /* 訪客 For 公開資訊那4個窗格讀取資料用 */

	SELECT
		COUNT(Id)
	FROM
		v_news_management_member
	WHERE
		PostType = ISNULL( @PostType, PostType)
		AND PostDateTime = ISNULL( @PostDateTime, PostDateTime)
		AND IsEnable = ISNULL( @IsEnable, IsEnable)
		AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
		AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
		AND Status = ISNULL( @Status, Status)

ELSE IF @RoleId=1 OR @RoleId=2 OR @RoleId=3 OR @RoleId=4 /* 1-SuperAdmin、2-H-ISAC管理者、3-H-ISAC內容維護者、4-H-ISAC內容審核者 */

	IF (@Status = '1')

		SELECT
			COUNT(Id)
		FROM
			v_news_management_member
		WHERE
			PostType = ISNULL( @PostType, PostType)
			AND NewsManagementGroupId = ISNULL( @NewsManagementGroupId, NewsManagementGroupId)
			AND PostDateTime = ISNULL( @PostDateTime, PostDateTime)
			AND (Title LIKE '%' + ISNULL( @Title, '') + '%' OR Content LIKE '%' + ISNULL( @Title, '') + '%' )
			AND IsEnable = ISNULL( @IsEnable, IsEnable)
			AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
			AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
			AND Status IN (1, 6)

	ELSE
	
		SELECT
			COUNT(Id)
		FROM
			v_news_management_member
		WHERE
			PostType = ISNULL( @PostType, PostType)
			AND NewsManagementGroupId = ISNULL( @NewsManagementGroupId, NewsManagementGroupId)
			AND PostDateTime = ISNULL( @PostDateTime, PostDateTime)
			AND (Title LIKE '%' + ISNULL( @Title, '') + '%' OR Content LIKE '%' + ISNULL( @Title, '') + '%' )
			AND IsEnable = ISNULL( @IsEnable, IsEnable)
			AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
			AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
			AND Status = ISNULL( @Status, Status)

END
GO
/****** Object:  StoredProcedure [dbo].[xp_notification]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE  PROCEDURE [dbo].[xp_notification] 
 @start bigint , 
 @maxRows bigint, 
 @dir bit, 
 @sort varchar(30), 

 @RoleId bigint, 
 @OrgId  bigint , 
 @MemberId bigint =NULL, 
 @Status bigint =NULL, 
 @Keyword nvarchar(30) , 
 @SApplyDateTime datetime =NULL, 
 @EApplyDateTime datetime =NULL 
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
10-會員機構聯絡人
11-會員機構管理者

*/


IF @RoleId=2 or @RoleId=1 /*2-H-ISAC管理者  1-SuperAdmin*/


	    SELECT 
			a.*
          
        FROM   notification  a
          where
		  (
		  (@Status = 1 and (a.Status = 1 or a.Status = 8)) 
          or (@Status = 2 and a.Status = 2)
          or (@Status = 3 and (a.Status = 31 or a.Status = 32 or a.Status = 33))
          or (@Status = 4 and (a.Status = 4 or a.Status = 9)) 
          or (@Status = 5 and (a.Status = 51 or a.Status = 52 or a.Status = 53))
          or (@Status = 6 and a.Status = 6)
          or (@Status = 7 and a.Status = 7)                  
          or  @Status is NULL
		  )
          and (a.ApplyDateTime >= ISNULL(@SApplyDateTime,a.ApplyDateTime) or @SApplyDateTime is null)
          and (a.ApplyDateTime <= ISNULL(@EApplyDateTime,a.ApplyDateTime) or @EApplyDateTime is null)
          and ( a.PostId like '%'+@Keyword+'%' or a.EventRemark like '%'+@Keyword+'%' )
		  order by 
          case when @sort = 'postId' and @dir = 0  then a.PostId end ASC,
		  case when @sort = 'postId' and @dir = 1  then a.PostId end DESC, 
		  case when @sort = 'eventRemark' and @dir = 0  then a.EventRemark end ASC, 
          case when @sort = 'eventRemark' and @dir = 1  then a.EventRemark end DESC, 
		  case when @sort = 'messagePostId' and @dir = 0 then a.EventSource end ASC, 
          case when @sort = 'messagePostId' and @dir = 1 then a.EventSource end DESC, 
		  case when @sort = 'applyDateTime' and @dir = 0 then a.ApplyDateTime end ASC, 
          case when @sort = 'applyDateTime' and @dir = 1 then a.ApplyDateTime end DESC, 
          case when @sort = 'status' and @dir = 0 then a.Status end ASC, 
          case when @sort = 'status' and @dir = 1 then a.Status end DESC

		  offset  @start rows fetch next  @maxRows rows only 
		 
ELSE IF   @RoleId=12 /*12-HISAC通報審核者 */


	    SELECT 
			a.*
          
        FROM   notification  a
          where
		  (
		   (@Status = 1 and (a.Status = 1 or a.Status = 8)) 
          or (@Status = 2 and a.Status = 2)
          or (@Status = 3 and (a.Status = 31 or a.Status = 32 or a.Status = 33))
          or (@Status = 4 and (a.Status = 4 or a.Status = 9)) 
          or (@Status = 5 and (a.Status = 51 or a.Status = 52 or a.Status = 53))
          or (@Status = 6 and a.Status = 6)
          or (@Status = 7 and a.Status = 7)          
          or  @Status is NULL
		  ) 
          and (a.ApplyDateTime >= ISNULL(@SApplyDateTime,a.ApplyDateTime) or @SApplyDateTime is null)
          and (a.ApplyDateTime <= ISNULL(@EApplyDateTime,a.ApplyDateTime) or @EApplyDateTime is null)
          and ( a.PostId like '%'+@Keyword+'%' or a.EventRemark like '%'+@Keyword+'%' )
		  and a.Status!=1
		   order by 
           case when @sort = 'postId' and @dir = 0  then a.PostId end ASC,
		  case when @sort = 'postId' and @dir = 1  then a.PostId end DESC, 
		  case when @sort = 'eventRemark' and @dir = 0  then a.EventRemark end ASC, 
          case when @sort = 'eventRemark' and @dir = 1  then a.EventRemark end DESC, 
		  case when @sort = 'messagePostId' and @dir = 0 then a.EventSource end ASC, 
          case when @sort = 'messagePostId' and @dir = 1 then a.EventSource end DESC, 
		  case when @sort = 'applyDateTime' and @dir = 0 then a.ApplyDateTime end ASC, 
          case when @sort = 'applyDateTime' and @dir = 1 then a.ApplyDateTime end DESC, 
          case when @sort = 'status' and @dir = 0 then a.Status end ASC, 
          case when @sort = 'status' and @dir = 1 then a.Status end DESC
          offset  @start rows fetch next  @maxRows rows only 


	 ELSE IF @RoleId=15 /* 15-權責單位通報審核者 */ 

        SELECT 
			a.*
          
        FROM   notification  a
          where
		  (
		  (@Status = 1 and (a.Status = 1 or a.Status = 8)) 
          or (@Status = 2 and a.Status = 2)
          or (@Status = 3 and (a.Status = 31 or a.Status = 32 or a.Status = 33))
         or (@Status = 4 and (a.Status = 4 or a.Status = 9)) 
          or (@Status = 5 and (a.Status = 51 or a.Status = 52 or a.Status = 53))
          or (@Status = 6 and a.Status = 6)
          or (@Status = 7 and a.Status = 7)          
          or  @Status is NULL
		  ) 
          and (a.ApplyDateTime >= ISNULL(@SApplyDateTime,a.ApplyDateTime) or @SApplyDateTime is null)
          and (a.ApplyDateTime <= ISNULL(@EApplyDateTime,a.ApplyDateTime) or @EApplyDateTime is null)
          and ( a.PostId like '%'+@Keyword+'%' or a.EventRemark like '%'+@Keyword+'%' )
		  and a.Status!=1
		  and
		  (
			(
				(
					(
						(
							a.Iscc3 = 1 or a.IsReview3 = 1
						)
						and
						(
							a.Status != 1 or a.Status != 2
						)
					)
					or
					(
						(
							a.Iscc5 = 1 or a.IsReview5 = 1
						)
						and
						(
							a.Status != 1 or a.Status != 2 or a.Status != 31 or a.Status != 32 or a.Status != 33 or a.Status != 4
						)
					)
				)
				and
				(
					a.CreateId in
					(
						Select member.Id from member where member.orgId in
						(
            				Select org_sign.OrgId from org_sign
							where org_sign.ParentOrgId = @OrgId and org_sign.ParentOrgId in 
							(
								Select org.Id from org where org.AuthType = '2' and org.IsEnable = 1
							)
						)
					)
				)
			)
			or
			(
				(
					a.CreateId in
					(
						Select member.Id
						from member
						where member.orgId in
						(
            				Select org_sign.OrgId from org_sign
							where org_sign.ParentOrgId = @OrgId and org_sign.ParentOrgId in 
							(
								Select org.Id from org
								where org.AuthType = '1' and org.IsEnable = 1
							)
						)
					)
				)
				and
				(
					a.Status != 1 or a.Status != 2 or a.Status != 31
				)
			)
		)
		   order by 
          case when @sort = 'postId' and @dir = 0  then a.PostId end ASC,
		  case when @sort = 'postId' and @dir = 1  then a.PostId end DESC, 
		  case when @sort = 'eventRemark' and @dir = 0  then a.EventRemark end ASC, 
          case when @sort = 'eventRemark' and @dir = 1  then a.EventRemark end DESC, 
		  case when @sort = 'messagePostId' and @dir = 0 then a.EventSource end ASC, 
          case when @sort = 'messagePostId' and @dir = 1 then a.EventSource end DESC, 
		  case when @sort = 'applyDateTime' and @dir = 0 then a.ApplyDateTime end ASC, 
          case when @sort = 'applyDateTime' and @dir = 1 then a.ApplyDateTime end DESC, 
          case when @sort = 'status' and @dir = 0 then a.Status end ASC, 
          case when @sort = 'status' and @dir = 1 then a.Status end DESC
          offset  @start rows fetch next  @maxRows rows only 
	 
               

      ELSE IF @RoleId=10  /* 10-會員機構聯絡人 */


	   SELECT 
			a.*
          
        FROM   notification  a
          where
		  (
		   (@Status = 1 and (a.Status = 1 or a.Status = 8)) 
          or (@Status = 2 and a.Status = 2)
          or (@Status = 3 and (a.Status = 31 or a.Status = 32 or a.Status = 33))
           or (@Status = 4 and (a.Status = 4 or a.Status = 9)) 
          or (@Status = 5 and (a.Status = 51 or a.Status = 52 or a.Status = 53))
          or (@Status = 6 and a.Status = 6)
          or (@Status = 7 and a.Status = 7)          
          or  @Status is NULL
		  )
          and (a.ApplyDateTime >= ISNULL(@SApplyDateTime,a.ApplyDateTime) or @SApplyDateTime is null)
          and (a.ApplyDateTime <= ISNULL(@EApplyDateTime,a.ApplyDateTime) or @EApplyDateTime is null)
          and ( a.PostId like '%'+@Keyword+'%' or a.EventRemark like '%'+@Keyword+'%' )
		  and a.CreateId=@MemberId 
		    order by 
          case when @sort = 'postId' and @dir = 0  then a.PostId end ASC,
		  case when @sort = 'postId' and @dir = 1  then a.PostId end DESC, 
		  case when @sort = 'eventRemark' and @dir = 0  then a.EventRemark end ASC, 
          case when @sort = 'eventRemark' and @dir = 1  then a.EventRemark end DESC, 
		  case when @sort = 'messagePostId' and @dir = 0 then a.EventSource end ASC, 
          case when @sort = 'messagePostId' and @dir = 1 then a.EventSource end DESC, 
		  case when @sort = 'applyDateTime' and @dir = 0 then a.ApplyDateTime end ASC, 
          case when @sort = 'applyDateTime' and @dir = 1 then a.ApplyDateTime end DESC, 
          case when @sort = 'status' and @dir = 0 then a.Status end ASC, 
          case when @sort = 'status' and @dir = 1 then a.Status end DESC
          offset  @start rows fetch next  @maxRows rows only 

      

     
      

       


END
GO
/****** Object:  StoredProcedure [dbo].[xp_notification_button_count]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[xp_notification_button_count] 
 @RoleId bigint, 
 @OrgId bigint, 
 @MemberId bigint =NULL, 
 @Status bigint =NULL, 
 @Keyword nvarchar(30) , 
 @SApplyDateTime datetime =NULL, 
 @EApplyDateTime datetime =NULL 


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
10-會員機構聯絡人
11-會員機構管理者

*/



		  
		 
IF   @RoleId=12 /*12-HISAC通報審核者 */


	    SELECT 
			a.Status as Status,count(a.Id) as Count
          
        FROM   notification  a
           where
           		a.Status =33
           or  	a.Status =53
		   group by a.Status  		         
		  
	 ELSE IF @RoleId=15 /* 15-權責單位通報審核者 */ 

        SELECT 
			a.Status as Status,count(a.Id) as Count
          
       FROM   notification  a
          where
		  (
		  (@Status = 1 and a.Status = 1) 
          or (@Status = 2 and a.Status = 2)
          or (@Status = 3 and (a.Status = 31 or a.Status = 32 or a.Status = 33))
          or (@Status = 4 and a.Status = 4)
          or (@Status = 5 and (a.Status = 51 or a.Status = 52 or a.Status = 53))
          or (@Status = 6 and a.Status = 6)
          or (@Status = 7 and a.Status = 7)
          or (@Status = 8 and a.Status = 8)
          or (@Status = 9 and a.Status = 9)
          or  @Status is NULL
		  ) 
          and (a.ApplyDateTime >= ISNULL(@SApplyDateTime,a.ApplyDateTime) or @SApplyDateTime is null)
          and (a.ApplyDateTime <= ISNULL(@EApplyDateTime,a.ApplyDateTime) or @EApplyDateTime is null)
          and ( a.PostId like '%'+@Keyword+'%' or a.EventRemark like '%'+@Keyword+'%' )
		  and a.Status!=1
		  and
		  (
			(
				(
					(
						(
							a.Iscc3 = 1 or a.IsReview3 = 1
						)
						and
						(
							a.Status = 31
						)
					)
					or
					(
						(
							a.Iscc5 = 1 or a.IsReview5 = 1
						)
						and
						(
							a.Status =51
						)
					)
				)
				and
				(
					a.CreateId in
					(
						Select member.Id from member where member.orgId in
						(
            				Select org_sign.OrgId from org_sign
							where org_sign.ParentOrgId = @OrgId and org_sign.ParentOrgId in 
							(
								Select org.Id from org where org.AuthType = '2' and org.IsEnable = 1
							)
						)
					)
				)
			)
			or
			(
				(
					a.CreateId in
					(
						Select member.Id
						from member
						where member.orgId in
						(
            				Select org_sign.OrgId from org_sign
							where org_sign.ParentOrgId = @OrgId and org_sign.ParentOrgId in 
							(
								Select org.Id from org
								where org.AuthType = '1' and org.IsEnable = 1
							)
						)
					)
				)
				and
				(
					a.Status = 32 or a.Status = 52 
				)
			)
		)
		 
		   group by a.Status  		
      	 
               

      ELSE IF @RoleId=10  /* 10-會員機構聯絡人 */


	   SELECT 
			a.Status as Status,count(a.Id) as Count
          
        FROM   notification  a
           where
           a.CreateId=@MemberId 
           and	(a.Status =1
           or  	a.Status =2
           or  	a.Status =4
           or  	a.Status =8
           or  	a.Status =9)
		   group by a.Status  		

      

     

       


END
GO
/****** Object:  StoredProcedure [dbo].[xp_notification_dashboard]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[xp_notification_dashboard]
	@Sdate datetime =NULL, 
	@Edate datetime =NULL 
AS

BEGIN

SET NOCOUNT ON;

	
	select a.EventType as Name, count(a.EventType) as count
	from notification a
	where a.EventType is not null
	   and (a.ApplyDateTime >= ISNULL(@Sdate,a.ApplyDateTime) or @Sdate is null)
       and (a.ApplyDateTime <= ISNULL(@Edate,a.ApplyDateTime) or @Edate is null)
	   and (a.Status = 6)
	group by a.EventType

END
GO
/****** Object:  StoredProcedure [dbo].[xp_notification_form_count]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[xp_notification_form_count] 
 @RoleId bigint, 
 @OrgId bigint, 
 @MemberId bigint =NULL, 
 @Status bigint =NULL, 
 @Keyword nvarchar(30) , 
 @SApplyDateTime datetime =NULL, 
 @EApplyDateTime datetime =NULL 


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
10-會員機構聯絡人
11-會員機構管理者

*/



		  
		 
IF   @RoleId=12 /*12-HISAC通報審核者 */


	    SELECT 
			count(a.Id)
          
        FROM   notification  a
           where
           		a.Status =33
           or  	a.Status =53         
		  
	 ELSE IF @RoleId=15 /* 15-權責單位通報審核者 */ 

        SELECT 
			count(a.Id)
          
        FROM   notification  a
            where
           ((((a.Iscc3 = 1 or a.IsReview3 = 1) and a.Status = 31) or ((a.Iscc5 = 1 or a.IsReview5 = 1) and a.Status = 51))
						and (a.CreateId in  
						( Select member.Id
							from member
							where member.orgId in
						 (
            					Select org_sign.OrgId
							from org_sign
							where org_sign.ParentOrgId = @OrgId 
							and org_sign.ParentOrgId in 
						(
							Select org.Id
							from org
							where org.AuthType = '2'
							and org.IsEnable = 1
						)
						))))
		or ((a.CreateId in  
						( Select member.Id
							from member
							where member.orgId in
						 (
            					Select org_sign.OrgId
							from org_sign
							where org_sign.ParentOrgId = @OrgId 
							and org_sign.ParentOrgId in 
						(
							Select org.Id
							from org
							where org.AuthType = '1'
							and org.IsEnable = 1
						)
						)))
						and (a.Status = 32 or a.Status = 52))
		 
      	 
               

      ELSE IF @RoleId=10  /* 10-會員機構聯絡人 */


	   SELECT 
			count(a.Id)
          
        FROM   notification  a
           where
           a.CreateId=@MemberId 
           and	(a.Status =1
           or  	a.Status =2
           or  	a.Status =4
           or  	a.Status =8
           or  	a.Status =9)
		 

      

     

       


END
GO
/****** Object:  StoredProcedure [dbo].[xp_notification_size]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[xp_notification_size] 
 @RoleId bigint, 
 @OrgId bigint, 
 @MemberId bigint =NULL, 
 @Status bigint =NULL, 
 @Keyword nvarchar(30) , 
 @SApplyDateTime datetime =NULL, 
 @EApplyDateTime datetime =NULL 


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
10-會員機構聯絡人
11-會員機構管理者

*/


IF @RoleId=2 or @RoleId=1/*2-H-ISAC管理者  1-SuperAdmin*/


	    SELECT 
			count(a.Id)
          
        FROM   notification  a
          where
		   (
		  (@Status = 1 and (a.Status = 1 or a.Status = 8)) 
          or (@Status = 2 and a.Status = 2)
          or (@Status = 3 and (a.Status = 31 or a.Status = 32 or a.Status = 33))
          or (@Status = 4 and (a.Status = 4 or a.Status = 9)) 
          or (@Status = 5 and (a.Status = 51 or a.Status = 52 or a.Status = 53))
          or (@Status = 6 and a.Status = 6)
          or (@Status = 7 and a.Status = 7)                  
          or  @Status is NULL
		  )
          and (a.ApplyDateTime >= ISNULL(@SApplyDateTime,a.ApplyDateTime) or @SApplyDateTime is null)
          and (a.ApplyDateTime <= ISNULL(@EApplyDateTime,a.ApplyDateTime) or @SApplyDateTime is null)
          and ( a.PostId like '%'+@Keyword+'%' or a.EventRemark like '%'+@Keyword+'%' )
		  
		 
ELSE IF   @RoleId=12 /*12-HISAC通報審核者 */


	    SELECT 
			count(a.Id)
          
        FROM   notification  a
          where
		  (
		  (@Status = 1 and (a.Status = 1 or a.Status = 8)) 
          or (@Status = 2 and a.Status = 2)
          or (@Status = 3 and (a.Status = 31 or a.Status = 32 or a.Status = 33))
          or (@Status = 4 and (a.Status = 4 or a.Status = 9)) 
          or (@Status = 5 and (a.Status = 51 or a.Status = 52 or a.Status = 53))
          or (@Status = 6 and a.Status = 6)
          or (@Status = 7 and a.Status = 7)                  
          or  @Status is NULL
		  )
          and (a.ApplyDateTime >= ISNULL(@SApplyDateTime,a.ApplyDateTime) or @SApplyDateTime is null)
          and (a.ApplyDateTime <= ISNULL(@EApplyDateTime,a.ApplyDateTime) or @SApplyDateTime is null)
          and ( a.PostId like '%'+@Keyword+'%' or a.EventRemark like '%'+@Keyword+'%' )
		  and a.Status!=1
		  
	 ELSE IF @RoleId=15 /* 15-權責單位通報審核者 */ 

        SELECT 
			count(a.Id)
          
        FROM   notification  a
          where
		   (
		  (@Status = 1 and (a.Status = 1 or a.Status = 8)) 
          or (@Status = 2 and a.Status = 2)
          or (@Status = 3 and (a.Status = 31 or a.Status = 32 or a.Status = 33))
          or (@Status = 4 and (a.Status = 4 or a.Status = 9)) 
          or (@Status = 5 and (a.Status = 51 or a.Status = 52 or a.Status = 53))
          or (@Status = 6 and a.Status = 6)
          or (@Status = 7 and a.Status = 7)                  
          or  @Status is NULL
		  )
          and (a.ApplyDateTime >= ISNULL(@SApplyDateTime,a.ApplyDateTime) or @SApplyDateTime is null)
          and (a.ApplyDateTime <= ISNULL(@EApplyDateTime,a.ApplyDateTime) or @EApplyDateTime is null)
          and ( a.PostId like '%'+@Keyword+'%' or a.EventRemark like '%'+@Keyword+'%' )
		  and a.Status!=1
		  and
		  (
			(
				(
					(
						(
							a.Iscc3 = 1 or a.IsReview3 = 1
						)
						and
						(
							a.Status != 1 or a.Status != 2
						)
					)
					or
					(
						(
							a.Iscc5 = 1 or a.IsReview5 = 1
						)
						and
						(
							a.Status != 1 or a.Status != 2 or a.Status != 31 or a.Status != 32 or a.Status != 33 or a.Status != 4
						)
					)
				)
				and
				(
					a.CreateId in
					(
						Select member.Id from member where member.orgId in
						(
            				Select org_sign.OrgId from org_sign
							where org_sign.ParentOrgId = @OrgId and org_sign.ParentOrgId in 
							(
								Select org.Id from org where org.AuthType = '2' and org.IsEnable = 1
							)
						)
					)
				)
			)
			or
			(
				(
					a.CreateId in
					(
						Select member.Id
						from member
						where member.orgId in
						(
            				Select org_sign.OrgId from org_sign
							where org_sign.ParentOrgId = @OrgId and org_sign.ParentOrgId in 
							(
								Select org.Id from org
								where org.AuthType = '1' and org.IsEnable = 1
							)
						)
					)
				)
				and
				(
					a.Status != 1 or a.Status != 2 or a.Status != 31
				)
			)
		)
		 
      	 
               

      ELSE IF @RoleId=10  /* 10-會員機構聯絡人 */


	   SELECT 
			count(a.Id)
          
        FROM   notification  a
          where
		  (
		  (@Status = 1 and (a.Status = 1 or a.Status = 8)) 
          or (@Status = 2 and a.Status = 2)
          or (@Status = 3 and (a.Status = 31 or a.Status = 32 or a.Status = 33))
          or (@Status = 4 and (a.Status = 4 or a.Status = 9)) 
          or (@Status = 5 and (a.Status = 51 or a.Status = 52 or a.Status = 53))
          or (@Status = 6 and a.Status = 6)
          or (@Status = 7 and a.Status = 7)                  
          or  @Status is NULL
		  )
          and (a.ApplyDateTime >= ISNULL(@SApplyDateTime,a.ApplyDateTime) or @SApplyDateTime is null)
          and (a.ApplyDateTime <= ISNULL(@EApplyDateTime,a.ApplyDateTime) or @SApplyDateTime is null)
          and ( a.PostId like '%'+@Keyword+'%' or a.EventRemark like '%'+@Keyword+'%' )
		  and a.CreateId=@MemberId 
		 

      

     

       


END
GO
/****** Object:  StoredProcedure [dbo].[xp_org_sign]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[xp_org_sign]
 @OrgId bigint
AS


BEGIN

 SET NOCOUNT ON;

	select *
	from 
	(

	select '0' as Id,a.Id as OrgId,a.Name as Name,'0' as Flag ,'0' as WarningIsExamine,'0' as NotificationIsExamine,'0' as NotificationClosingIsExamine
	from org a 
	where a.OrgType='2' and a.IsEnable='1' and a.Id not in (select ParentOrgId from org_sign  where  OrgId=@OrgId)
	
	union 
	
	select b.Id,b.ParentOrgId as OrgId,d.Name as Name ,'1' as Flag ,b.WarningIsExamine,b.NotificationIsExamine,b.NotificationClosingIsExamine
	from org_sign b, org c ,org d
	where b.Orgid=c.Id and c.IsEnable='1' and b.OrgId=@OrgId and b.ParentOrgId=d.Id

	)  d order by d.Flag desc, d.Name
END

GO
/****** Object:  StoredProcedure [dbo].[xp_public_dashboard]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[xp_public_dashboard]
	@Sdate datetime =NULL, 
	@Edate datetime =NULL 
AS

BEGIN

SET NOCOUNT ON;
	(select 'News' as Name, count(a.Id) as count
	from news_management a
	where
	   ( a.CreateTime >= ISNULL(@Sdate,a.CreateTime) or @Sdate is null) 
	  and ( a.CreateTime <= ISNULL(@Edate,a.CreateTime) or @Edate is null) 
	  and a.Status = 4
	  ) union (select 'Activity' as Name, count(b.Id) as count
	from activity_management b
	where
	   ( b.CreateTime >= ISNULL(@Sdate,b.CreateTime) or @Sdate is null) 
	  and ( b.CreateTime <= ISNULL(@Edate,b.CreateTime) or @Edate is null) 
	  and b.Status = 4
	  ) union (select 'Ana' as Name, count(c.Id) as count
	from ana_management c
	where
	   ( c.CreateTime >= ISNULL(@Sdate,c.CreateTime) or @Sdate is null) 
	  and ( c.CreateTime <= ISNULL(@Edate,c.CreateTime) or @Edate is null) 
	  and c.Status = 4
	  ) union (select 'Weakness' as Name, count(d.Id) as count
	from weakness_management d
	where
	   ( d.CreateTime >= ISNULL(@Sdate,d.CreateTime) or @Sdate is null) 
	  and ( d.CreateTime <= ISNULL(@Edate,d.CreateTime) or @Edate is null) 
	  and d.Status = 4
	  )
END
GO
/****** Object:  StoredProcedure [dbo].[xp_weakness_management]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE  PROCEDURE [dbo].[xp_weakness_management] 
	@start bigint,
	@maxRows bigint,
	@dir bit,
	@sort nvarchar(30),
	@RoleId bigint,
	@MemberId bigint = NULL,

	@IncidentTitle nvarchar(MAX) = NULL,
	@IncidentDiscoveryTime datetime = NULL,
	@IncidentReportedTime datetime = NULL,
	@EventTypeCode varchar(8) = NULL,
	@ImpactQualification tinyint = NULL,
	@IsEnable bit = NULL,
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
10-會員機構聯絡人
11-會員機構管理者

*/


IF @RoleId=0 /* 訪客 For 公開資訊那4個窗格讀取資料用 */

    SELECT
        *
    FROM
        v_weakness_management_member
    WHERE
		IncidentTitle LIKE '%' + ISNULL(@IncidentTitle, '') + '%'
		AND IncidentDiscoveryTime = ISNULL(@IncidentDiscoveryTime, IncidentDiscoveryTime)
		AND IncidentReportedTime = ISNULL(@IncidentReportedTime, IncidentReportedTime)
		AND EventTypeCode = ISNULL(@EventTypeCode, EventTypeCode)
		AND ImpactQualification = ISNULL(@ImpactQualification, ImpactQualification)
		AND IsEnable = ISNULL(@IsEnable, IsEnable)
		AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
		AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
		AND Status = ISNULL(@Status, Status)
    ORDER BY
        IncidentReportedTime DESC
    OFFSET  @start ROWS FETCH NEXT @maxRows ROWS ONLY

ELSE IF @RoleId=1 OR @RoleId=2 OR @RoleId=3 OR @RoleId=4 /* 1-SuperAdmin、2-H-ISAC管理者、3-H-ISAC內容維護者、4-H-ISAC內容審核者 */

	IF (@Status = '1')
	
		SELECT
			*
		FROM
			v_weakness_management_member
		WHERE
			IncidentTitle LIKE '%' + ISNULL(@IncidentTitle, '') + '%'
			AND IncidentDiscoveryTime = ISNULL(@IncidentDiscoveryTime, IncidentDiscoveryTime)
			AND IncidentReportedTime = ISNULL(@IncidentReportedTime, IncidentReportedTime)
			AND EventTypeCode = ISNULL(@EventTypeCode, EventTypeCode)
			AND ImpactQualification = ISNULL(@ImpactQualification, ImpactQualification)
			AND IsEnable = ISNULL(@IsEnable, IsEnable)
			AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
			AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
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
			CASE WHEN @sort = 'status' AND @dir = 0 THEN Status end ASC,
			CASE WHEN @sort = 'status' AND @dir = 1 THEN Status end DESC
		OFFSET  @start ROWS FETCH NEXT @maxRows ROWS ONLY

	ELSE
    
		SELECT
			*
		FROM
			v_weakness_management_member
		WHERE
			IncidentTitle LIKE '%' + ISNULL(@IncidentTitle, '') + '%'
			AND IncidentDiscoveryTime = ISNULL(@IncidentDiscoveryTime, IncidentDiscoveryTime)
			AND IncidentReportedTime = ISNULL(@IncidentReportedTime, IncidentReportedTime)
			AND EventTypeCode = ISNULL(@EventTypeCode, EventTypeCode)
			AND ImpactQualification = ISNULL(@ImpactQualification, ImpactQualification)
			AND IsEnable = ISNULL(@IsEnable, IsEnable)
			AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
			AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
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
			CASE WHEN @sort = 'status' AND @dir = 0 THEN Status end ASC,
			CASE WHEN @sort = 'status' AND @dir = 1 THEN Status end DESC
		OFFSET  @start ROWS FETCH NEXT @maxRows ROWS ONLY


END
GO
/****** Object:  StoredProcedure [dbo].[xp_weakness_management_button_count]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[xp_weakness_management_button_count] 
	@RoleId bigint,
	@MemberId bigint = NULL,

	@IncidentTitle nvarchar(MAX) = NULL,
	@IncidentDiscoveryTime datetime = NULL,
	@IncidentReportedTime datetime = NULL,
	@EventTypeCode varchar(8) = NULL,
	@ImpactQualification tinyint = NULL,
	@IsEnable bit = NULL,
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
	10-會員機構聯絡人
	11-會員機構管理者

	*/
	
	IF  @RoleId=1 OR @RoleId=2 /* 1-SuperAdmin、2-H-ISAC管理者，統計階段 1-編輯中、2-撤銷中、3-審核中、6-編輯中(退回) 之件數 */　
		BEGIN
			SELECT
				Status,
				COUNT(Id) AS Count
			FROM
				v_weakness_management_member
			WHERE
				IncidentTitle LIKE '%' + ISNULL(@IncidentTitle, '') + '%'
				AND IncidentDiscoveryTime = ISNULL(@IncidentDiscoveryTime, IncidentDiscoveryTime)
				AND IncidentReportedTime = ISNULL(@IncidentReportedTime, IncidentReportedTime)
				AND EventTypeCode = ISNULL(@EventTypeCode, EventTypeCode)
				AND ImpactQualification = ISNULL(@ImpactQualification, ImpactQualification)
				AND IsEnable = ISNULL(@IsEnable, IsEnable)
				AND Status IN (1, 2, 3, 6)
			GROUP BY Status
		END
	ELSE IF @RoleId=3 /* 3-H-ISAC內容維護者，統計階段 1-編輯中、2-撤銷中、6-編輯中(退回) 之件數 */
		BEGIN
			SELECT
				Status,
				COUNT(Id) AS Count
			FROM
				v_weakness_management_member
			WHERE
				IncidentTitle LIKE '%' + ISNULL(@IncidentTitle, '') + '%'
				AND IncidentDiscoveryTime = ISNULL(@IncidentDiscoveryTime, IncidentDiscoveryTime)
				AND IncidentReportedTime = ISNULL(@IncidentReportedTime, IncidentReportedTime)
				AND EventTypeCode = ISNULL(@EventTypeCode, EventTypeCode)
				AND ImpactQualification = ISNULL(@ImpactQualification, ImpactQualification)
				AND IsEnable = ISNULL(@IsEnable, IsEnable)
				AND Status IN (1, 2, 6)
			GROUP BY Status
		END
	ELSE IF @RoleId=4 /* 4-H-ISAC內容審核者，統計階段 3-審核中 之件數 */
		BEGIN
			SELECT
				Status,
				COUNT(Id) AS Count
			FROM
				v_weakness_management_member
			WHERE
				IncidentTitle LIKE '%' + ISNULL(@IncidentTitle, '') + '%'
				AND IncidentDiscoveryTime = ISNULL(@IncidentDiscoveryTime, IncidentDiscoveryTime)
				AND IncidentReportedTime = ISNULL(@IncidentReportedTime, IncidentReportedTime)
				AND EventTypeCode = ISNULL(@EventTypeCode, EventTypeCode)
				AND ImpactQualification = ISNULL(@ImpactQualification, ImpactQualification)
				AND IsEnable = ISNULL(@IsEnable, IsEnable)
				AND Status = 3
			GROUP BY Status
		END
END
GO
/****** Object:  StoredProcedure [dbo].[xp_weakness_management_from_count]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE  PROCEDURE [dbo].[xp_weakness_management_from_count] 
	@RoleId bigint,
	@MemberId bigint = NULL
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
10-會員機構聯絡人
11-會員機構管理者

*/

IF  @RoleId=1 OR @RoleId=2 /* 1-SuperAdmin、2-H-ISAC管理者，統計階段 1-編輯中、2-撤銷中、3-審核中、6-編輯中(退回) 之件數 */
	BEGIN
		SELECT
			COUNT(Id)
		FROM
			v_weakness_management_member
		WHERE
			Status IN (1, 2, 3, 6)
	END
ELSE IF @RoleId=3 /* 3-H-ISAC內容維護者，統計階段 1-編輯中、2-撤銷中、6-編輯中(退回) 之件數 */
	BEGIN
		SELECT
			COUNT(Id)
		FROM
			v_weakness_management_member
		WHERE
			Status IN (1, 2, 6)
	END
ELSE IF @RoleId=4 /* 4-H-ISAC內容審核者，統計階段 3-審核中 之件數 */
	BEGIN
		SELECT
			COUNT(Id)
		FROM
			v_weakness_management_member
		WHERE
			Status IN (3)
	END
END
GO
/****** Object:  StoredProcedure [dbo].[xp_weakness_management_size]    Script Date: 2018/4/10 下午 12:12:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE  PROCEDURE [dbo].[xp_weakness_management_size] 
	@RoleId bigint,
	@MemberId bigint = NULL,

	@IncidentTitle nvarchar(MAX) = NULL,
	@IncidentDiscoveryTime datetime = NULL,
	@IncidentReportedTime datetime = NULL,
	@EventTypeCode varchar(8) = NULL,
	@ImpactQualification tinyint = NULL,
	@IsEnable bit = NULL,
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
10-會員機構聯絡人
11-會員機構管理者

*/


IF @RoleId=0 /* 訪客 For 公開資訊那4個窗格讀取資料用 */

	SELECT
		COUNT(Id)
	FROM
		v_weakness_management_member
	WHERE
		IncidentTitle LIKE '%' + ISNULL(@IncidentTitle, '') + '%'
		AND IncidentDiscoveryTime = ISNULL(@IncidentDiscoveryTime, IncidentDiscoveryTime)
		AND IncidentReportedTime = ISNULL(@IncidentReportedTime, IncidentReportedTime)
		AND EventTypeCode = ISNULL(@EventTypeCode, EventTypeCode)
		AND ImpactQualification = ISNULL(@ImpactQualification, ImpactQualification)
		AND IsEnable = ISNULL(@IsEnable, IsEnable)
		AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
		AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
		AND Status = ISNULL(@Status, Status)

ELSE IF @RoleId=1 OR @RoleId=2 OR @RoleId=3 OR @RoleId=4 /* 1-SuperAdmin、2-H-ISAC管理者、3-H-ISAC內容維護者、4-H-ISAC內容審核者 */

	IF (@Status = '1')

		SELECT
			COUNT(Id)
		FROM
			v_weakness_management_member
		WHERE
			IncidentTitle LIKE '%' + ISNULL(@IncidentTitle, '') + '%'
			AND IncidentDiscoveryTime = ISNULL(@IncidentDiscoveryTime, IncidentDiscoveryTime)
			AND IncidentReportedTime = ISNULL(@IncidentReportedTime, IncidentReportedTime)
			AND EventTypeCode = ISNULL(@EventTypeCode, EventTypeCode)
			AND ImpactQualification = ISNULL(@ImpactQualification, ImpactQualification)
			AND IsEnable = ISNULL(@IsEnable, IsEnable)
			AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
			AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
			AND Status IN (1, 6)

	ELSE
	
		SELECT
			COUNT(Id)
		FROM
			v_weakness_management_member
		WHERE
			IncidentTitle LIKE '%' + ISNULL(@IncidentTitle, '') + '%'
			AND IncidentDiscoveryTime = ISNULL(@IncidentDiscoveryTime, IncidentDiscoveryTime)
			AND IncidentReportedTime = ISNULL(@IncidentReportedTime, IncidentReportedTime)
			AND EventTypeCode = ISNULL(@EventTypeCode, EventTypeCode)
			AND ImpactQualification = ISNULL(@ImpactQualification, ImpactQualification)
			AND IsEnable = ISNULL(@IsEnable, IsEnable)
			AND (StartDateTime <= ISNULL(@StartDateTime, StartDateTime) OR @StartDateTime IS NULL)
			AND (EndDateTime >= ISNULL(@StartDateTime, EndDateTime) OR @StartDateTime IS NULL)
			AND Status = ISNULL( @Status, Status)

END
GO

