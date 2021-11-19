USE [hisac]
GO

/****** Object:  StoredProcedure [dbo].[xp_public_dashboard]    Script Date: 2018/6/4 上午 09:48:02 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


ALTER PROCEDURE [dbo].[xp_public_dashboard]
	@Sdate datetime =NULL, 
	@Edate datetime =NULL 
AS

BEGIN

SET NOCOUNT ON;
	(select 'News' as Name, count(a.Id) as count
	from news_management a
	where
	   ( a.PostDateTime >= ISNULL(@Sdate,a.PostDateTime) or @Sdate is null) 
	  and ( a.PostDateTime <= ISNULL(@Edate,a.PostDateTime) or @Edate is null) 
	  and a.Status = 4
	  and a.IsEnable = 1
	  ) union (select 'Activity' as Name, count(b.Id) as count
	from activity_management b
	where
	   ( b.PostDateTime >= ISNULL(@Sdate,b.PostDateTime) or @Sdate is null) 
	  and ( b.PostDateTime <= ISNULL(@Edate,b.PostDateTime) or @Edate is null) 
	  and b.Status = 4
	  and b.IsEnable = 1
	  ) union (select 'Ana' as Name, count(c.Id) as count
	from ana_management c
	where
	   ( c.IncidentReportedTime >= ISNULL(@Sdate,c.IncidentReportedTime) or @Sdate is null) 
	  and ( c.IncidentReportedTime <= ISNULL(@Edate,c.IncidentReportedTime) or @Edate is null) 
	  and c.Status = 4
	  and c.IsEnable = 1
	  ) union (select 'Weakness' as Name, count(d.Id) as count
	from weakness_management d
	where
	   ( d.IncidentReportedTime >= ISNULL(@Sdate,d.IncidentReportedTime) or @Sdate is null) 
	  and ( d.IncidentReportedTime <= ISNULL(@Edate,d.IncidentReportedTime) or @Edate is null) 
	  and d.Status = 4
	  and d.IsEnable = 1
	  )
END
GO


