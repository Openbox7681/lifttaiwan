USE [hisac]
GO

/****** Object:  StoredProcedure [dbo].[xp_activity_management_report]    Script Date: 2018/8/14 下午 12:12:32 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[xp_activity_management_report] 
	@StartPostDateTime datetime = NULL,
	@EndPostDateTime datetime = NULL
AS 
BEGIN

select count(*) as Count, Name=N'編輯中' from activity_management
where (status=1 or status=6)
and PostDateTime >= ISNULL(@StartPostDateTime, PostDateTime)
and PostDateTime < ISNULL(@EndPostDateTime, PostDateTime)
union
select count(*) as Count, Name=N'已撤銷' from activity_management
where status=5
and PostDateTime >= ISNULL(@StartPostDateTime, PostDateTime)
and PostDateTime < ISNULL(@EndPostDateTime, PostDateTime)
union
select count(*) as Count, Name=N'審核中' from activity_management
where (status=2 or status=3)
and PostDateTime >= ISNULL(@StartPostDateTime, PostDateTime)
and PostDateTime < ISNULL(@EndPostDateTime, PostDateTime)
union
select count(*) as Count, Name=N'已公告,啟用' from activity_management
where status=4 and IsEnable = 1
and PostDateTime >= ISNULL(@StartPostDateTime, PostDateTime)
and PostDateTime < ISNULL(@EndPostDateTime, PostDateTime)
union
select count(*) as Count, Name=N'已公告,不啟用' from activity_management
where status=4 and IsEnable = 0
and PostDateTime >= ISNULL(@StartPostDateTime, PostDateTime)
and PostDateTime < ISNULL(@EndPostDateTime, PostDateTime)

END
SET ANSI_NULLS ON
GO


USE [hisac]
GO

/****** Object:  StoredProcedure [dbo].[xp_ana_management_report]    Script Date: 2018/8/14 下午 12:12:41 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[xp_ana_management_report] 
	@StartPostDateTime datetime = NULL,
	@EndPostDateTime datetime = NULL
AS 
BEGIN

select count(*) as Count, Name=N'編輯中' from ana_management
where (status=1 or status=6)
and IncidentReportedTime >= ISNULL(@StartPostDateTime, IncidentReportedTime)
and IncidentReportedTime < ISNULL(@EndPostDateTime, IncidentReportedTime)
union
select count(*) as Count, Name=N'已撤銷' from ana_management
where status=5
and IncidentReportedTime >= ISNULL(@StartPostDateTime, IncidentReportedTime)
and IncidentReportedTime < ISNULL(@EndPostDateTime, IncidentReportedTime)
union
select count(*) as Count, Name=N'審核中' from ana_management
where (status=2 or status=3)
and IncidentReportedTime >= ISNULL(@StartPostDateTime, IncidentReportedTime)
and IncidentReportedTime < ISNULL(@EndPostDateTime, IncidentReportedTime)
union
select count(*) as Count, Name=N'已公告,啟用' from ana_management
where status=4 and IsEnable = 1
and IncidentReportedTime >= ISNULL(@StartPostDateTime, IncidentReportedTime)
and IncidentReportedTime < ISNULL(@EndPostDateTime, IncidentReportedTime)
union
select count(*) as Count, Name=N'已公告,不啟用' from ana_management
where status=4 and IsEnable = 0
and IncidentReportedTime >= ISNULL(@StartPostDateTime, IncidentReportedTime)
and IncidentReportedTime < ISNULL(@EndPostDateTime, IncidentReportedTime)

END
SET ANSI_NULLS ON
GO


USE [hisac]
GO

/****** Object:  StoredProcedure [dbo].[xp_information_exchange_report]    Script Date: 2018/8/14 下午 12:13:06 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[xp_information_exchange_report] 
	@StartPostDateTime datetime = NULL,
	@EndPostDateTime datetime = NULL
AS 
BEGIN
select SourceCode, information_source.Name, ISNULL(Edit, 0) as Edit, ISNULL(Del, 0) as Del, ISNULL(Sign, 0) as Sign
, ISNULL(Pub, 0) as Pub, ISNULL(Alert, 0) as Alert, ISNULL(Nisac, 0) as Nisac, ISNULL(Edit, 0) + ISNULL(Del, 0) + ISNULL(Sign, 0) + ISNULL(Pub, 0) + ISNULL(Alert, 0) + ISNULL(Nisac, 0) as Total
from 
(
select count(*) as cnt, name=N'Edit', SourceCode from information_exchange
where (status=1 or status=8)
and CreateTime >= ISNULL(@StartPostDateTime, CreateTime)
and CreateTime < ISNULL(@EndPostDateTime, CreateTime)
group by SourceCode
union
select count(*) as cnt, name=N'Del', SourceCode from information_exchange
where status=7
and CreateTime >= ISNULL(@StartPostDateTime, CreateTime)
and CreateTime < ISNULL(@EndPostDateTime, CreateTime)
group by SourceCode
union
select count(*) as cnt, name=N'Sign', SourceCode from information_exchange
where (status=2 or status=3)
and CreateTime >= ISNULL(@StartPostDateTime, CreateTime)
and CreateTime < ISNULL(@EndPostDateTime, CreateTime)
group by SourceCode
union
select count(*) as cnt, name=N'Pub', SourceCode from information_exchange
where status=4
and CreateTime >= ISNULL(@StartPostDateTime, CreateTime)
and CreateTime < ISNULL(@EndPostDateTime, CreateTime)
group by SourceCode
union
select count(*) as cnt, name=N'Alert', SourceCode from information_exchange
where status=5
and CreateTime >= ISNULL(@StartPostDateTime, CreateTime)
and CreateTime < ISNULL(@EndPostDateTime, CreateTime)
group by SourceCode
union
select count(*) as cnt, name=N'Nisac', SourceCode from information_exchange
where status=6
and CreateTime >= ISNULL(@StartPostDateTime, CreateTime)
and CreateTime < ISNULL(@EndPostDateTime, CreateTime)
group by SourceCode
) as a
pivot
(
	sum(cnt)
	FOR name IN ([Edit],[Del],[sign],[Pub],[Alert],[Nisac])
) p
left join information_source on information_source.Code = p.SourceCode
where information_source.Name is not null

END
SET ANSI_NULLS ON
GO


USE [hisac]
GO

/****** Object:  StoredProcedure [dbo].[xp_news_management_report]    Script Date: 2018/8/14 下午 12:13:20 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[xp_news_management_report] 
	@StartPostDateTime datetime = NULL,
	@EndPostDateTime datetime = NULL
AS 
BEGIN

select count(*) as Count, Name=N'編輯中' from news_management
where (status=1 or status=6)
and PostDateTime >= ISNULL(@StartPostDateTime, PostDateTime)
and PostDateTime < ISNULL(@EndPostDateTime, PostDateTime)
union
select count(*) as Count, Name=N'已撤銷' from news_management
where status=5
and PostDateTime >= ISNULL(@StartPostDateTime, PostDateTime)
and PostDateTime < ISNULL(@EndPostDateTime, PostDateTime)
union
select count(*) as Count, Name=N'審核中' from news_management
where (status=2 or status=3)
and PostDateTime >= ISNULL(@StartPostDateTime, PostDateTime)
and PostDateTime < ISNULL(@EndPostDateTime, PostDateTime)
union
select count(*) as Count, Name=N'已公告,啟用' from news_management
where status=4 and IsEnable = 1
and PostDateTime >= ISNULL(@StartPostDateTime, PostDateTime)
and PostDateTime < ISNULL(@EndPostDateTime, PostDateTime)
union
select count(*) as Count, Name=N'已公告,不啟用' from news_management
where status=4 and IsEnable = 0
and PostDateTime >= ISNULL(@StartPostDateTime, PostDateTime)
and PostDateTime < ISNULL(@EndPostDateTime, PostDateTime)

END
SET ANSI_NULLS ON
GO


