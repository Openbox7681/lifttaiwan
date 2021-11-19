##
# 新增HisacBoss權限
#
# 新增HisacBoss系統管理權限s08,s05,s06,s13,s31,s32,s39,s37,s41
#
##

##
# 新增ResourceMessage
#
# mailVerifySubject	會員申請電子郵件驗證通知	
# mailVerifyBody	您的電子郵件驗證碼為 {0}
#
##

USE [hisac]
GO

/****** Object:  Table [dbo].[verify_email]    Script Date: 2018/5/14 下午 03:50:28 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[verify_email](
	[Email] [nvarchar](128) NOT NULL,
	[Code] [varchar](32) NOT NULL,
 CONSTRAINT [PK_verify_email] PRIMARY KEY CLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'電子郵件信箱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'verify_email', @level2type=N'COLUMN',@level2name=N'Email'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'驗證碼' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'verify_email', @level2type=N'COLUMN',@level2name=N'Code'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'電子郵件驗證' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'verify_email'
GO

/****** Object:  Table [dbo].[survey]    Script Date: 2018/5/14 上午 11:59:20 ******/
DROP TABLE [dbo].[survey]
GO

/****** Object:  Table [dbo].[survey]    Script Date: 2018/5/14 下午 12:00:24 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[survey](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[SurveyPublic01] [int] NOT NULL,
	[SurveyPublic01Suggest] [nvarchar](max) NULL,
	[SurveyPublic02] [int] NOT NULL,
	[SurveyPublic02Suggest] [nvarchar](max) NULL,
	[SurveyPublic03] [int] NOT NULL,
	[SurveyPublic03Suggest] [nvarchar](max) NULL,
	[SurveyNotify01] [int] NOT NULL,
	[SurveyNotify01Suggest] [nvarchar](max) NULL,
	[SurveyNotify02] [int] NOT NULL,
	[SurveyNotify02Suggest] [nvarchar](max) NULL,
	[SurveyNotify03] [int] NOT NULL,
	[SurveyNotify03Suggest] [nvarchar](max) NULL,
	[SurveyAlert01] [int] NOT NULL,
	[SurveyAlert01Suggest] [nvarchar](max) NULL,
	[SurveyAlert02] [int] NOT NULL,
	[SurveyAlert02Suggest] [nvarchar](max) NULL,
	[SurveyAlert03] [int] NOT NULL,
	[SurveyAlert03Suggest] [nvarchar](max) NULL,
	[SurveyCheck01] [int] NOT NULL,
	[SurveyCheck01Suggest] [nvarchar](max) NULL,
	[SurveyCheck02] [int] NOT NULL,
	[SurveyCheck02Suggest] [nvarchar](max) NULL,
	[SurveyCheck03] [int] NOT NULL,
	[SurveyCheck03Suggest] [nvarchar](max) NULL,
	[SurveyTotal01] [int] NOT NULL,
	[SurveyTotal01Suggest] [nvarchar](max) NULL,
	[SurveySuggest] [nvarchar](max) NULL,
	[CreateId] [bigint] NULL,
	[CreateTime] [datetime] NULL,
 CONSTRAINT [PK_survey] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'公開資訊滿意度
資訊內容與工作性質是否相關
5:非常相關
4:相關
3:尚可
2:不相關
1:非常不相關
0:未使用功能' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyPublic01'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'SurveyPublic01=1 填寫建議增加資訊內容' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyPublic01Suggest'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'公開資訊滿意度
資訊內容是否符合需求
5:非常滿意
4:滿意
3:尚可
2:不太滿意
1:很不滿意
0:未使用功能' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyPublic02'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'SurveyPublic02=1 填寫改善建議' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyPublic02Suggest'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'公開資訊滿意度
資訊是否對醫院資安推動有幫助
5:非常滿意
4:滿意
3:尚可
2:不太滿意
1:很不滿意
0:未使用功能' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyPublic03'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'SurveyPublic03=1 填寫改善建議' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyPublic03Suggest'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'通報服務滿意度
通報方式是否符合需求
5:非常滿意
4:滿意
3:尚可
2:不太滿意
1:很不滿意
0:未使用功能' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyNotify01'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'SurveyNotify01=1 填寫改善建議' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyNotify01Suggest'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'通報服務滿意度
通報內容是否符合需求
5:非常滿意
4:滿意
3:尚可
2:不太滿意
1:很不滿意
0:未使用功能' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyNotify02'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'SurveyNotify02=1 填寫改善建議' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyNotify02Suggest'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'通報服務滿意度

通報網頁的處理流程是否滿意
5:非常滿意
4:滿意
3:尚可
2:不太滿意
1:很不滿意
0:未使用功能' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyNotify03'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'SurveyNotify03=1 填寫改善建議' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyNotify03Suggest'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'警訊發布滿意度
警訊發布方式是否符合需求
5:非常滿意
4:滿意
3:尚可
2:不太滿意
1:很不滿意
0:未使用功能' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyAlert01'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'SurveyAlert01=1 填寫改善建議' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyAlert01Suggest'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'警訊發布滿意度
警訊內容是否對醫院資安推動有幫助
5:非常滿意
4:滿意
3:尚可
2:不太滿意
1:很不滿意
0:未使用功能' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyAlert02'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'SurveyAlert02=1 填寫改善建議' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyAlert02Suggest'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'警訊發布滿意度

警訊處理流程是否滿意
5:非常滿意
4:滿意
3:尚可
2:不太滿意
1:很不滿意
0:未使用功能' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyAlert03'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'SurveyAlert03=1 填寫改善建議' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyAlert03Suggest'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'資訊資產盤點與風險評估輔助系統滿意度
盤點上傳方式是否符合需求
5:非常滿意
4:滿意
3:尚可
2:不太滿意
1:很不滿意
0:未使用功能' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyCheck01'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'SurveyCheck01=1 填寫改善建議' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyCheck01Suggest'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'資訊資產盤點與風險評估輔助系統滿意度
明細查詢內容是否符合需求
5:非常滿意
4:滿意
3:尚可
2:不太滿意
1:很不滿意
0:未使用功能' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyCheck02'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'SurveyCheck02=1 填寫改善建議' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyCheck02Suggest'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'資訊資產盤點與風險評估輔助系統滿意度

盤點與風險評估是否對醫院資安推動有幫助
5:非常滿意
4:滿意
3:尚可
2:不太滿意
1:很不滿意
0:未使用功能' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyCheck03'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'SurveyCheck03=1 填寫改善建議' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyCheck03Suggest'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'整體滿意度' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyTotal01'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'SurveyTotal=1 填寫改善建議' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveyTotal01Suggest'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'其他建議' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'SurveySuggest'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'CreateId'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'問卷調查' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'survey'
GO


