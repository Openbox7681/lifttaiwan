USE [hisac]
GO
/****** Object:  Table [dbo].[activity_management]    Script Date: 2018/3/28 下午 06:06:21 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[activity_management](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[PostType] [nchar](1) NOT NULL,
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
 CONSTRAINT [PK_activity_management] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[activity_management_attach]    Script Date: 2018/3/28 下午 06:06:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[activity_management_attach](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[ActivityManagementId] [bigint] NOT NULL,
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
 CONSTRAINT [PK_activity_management_attach] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[activity_management_pic]    Script Date: 2018/3/28 下午 06:06:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[activity_management_pic](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[ActivityManagementId] [bigint] NOT NULL,
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
 CONSTRAINT [PK_activity_management_pic] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[alert_type]    Script Date: 2018/3/28 下午 06:06:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[alert_type](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Code] [varchar](8) NOT NULL,
	[Name] [nvarchar](max) NOT NULL,
	[IsEnable] [bit] NOT NULL,
	[Sort] [bigint] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
 CONSTRAINT [PK_alert_type] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ana_management]    Script Date: 2018/3/28 下午 06:06:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ana_management](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[IncidentId] [nvarchar](128) NULL,
	[IncidentTitle] [nvarchar](max) NOT NULL,
	[IncidentDiscoveryTime] [datetime] NOT NULL,
	[IncidentReportedTime] [datetime] NULL,
	[Description] [nvarchar](max) NULL,
	[EventTypeCode] [varchar](8) NOT NULL,
	[ReporterName] [nvarchar](256) NOT NULL,
	[ResponderPartyName] [nvarchar](64) NULL,
	[ResponderContactNumbers] [nvarchar](64) NULL,
	[ResponderElectronicAddressIdentifiers] [nvarchar](128) NULL,
	[ImpactQualification] [tinyint] NOT NULL,
	[CoaDescription] [nvarchar](max) NULL,
	[Confidence] [nvarchar](128) NULL,
	[Reference] [nvarchar](max) NULL,
	[AffectedSoftwareDescription] [nvarchar](128) NULL,
	[StartDateTime] [datetime] NULL,
	[EndDateTime] [datetime] NULL,
	[IsEnable] [bit] NULL,
	[CreateId] [bigint] NULL,
	[CreateTime] [datetime] NULL,
	[ModifyId] [bigint] NULL,
	[ModifyTime] [datetime] NULL,
	[Status] [tinyint] NULL,
	[PostId] [varchar](128) NULL,
	[TransferInType] [tinyint] NULL,
	[TransferInId] [varchar](32) NULL,
	[TransferOutType] [tinyint] NULL,
	[TransferOutId] [varchar](32) NULL,
 CONSTRAINT [PK_ana_management] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ana_management_attach]    Script Date: 2018/3/28 下午 06:06:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ana_management_attach](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[AnaManagementId] [bigint] NOT NULL,
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
 CONSTRAINT [PK_ana_management_attach] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[common_post]    Script Date: 2018/3/28 下午 06:06:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[common_post](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[PostType] [bigint] NOT NULL,
	[Title] [nvarchar](max) NOT NULL,
	[Content] [nvarchar](max) NOT NULL,
	[IsBreakLine] [bit] NOT NULL,
	[StartDateTime] [datetime] NULL,
	[EndDateTime] [datetime] NULL,
	[IsEnable] [bit] NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
 CONSTRAINT [PK_common_post] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[common_post_attach]    Script Date: 2018/3/28 下午 06:06:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[common_post_attach](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[CommonPostId] [bigint] NOT NULL,
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
 CONSTRAINT [PK_common_post_attach] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[common_post_pic]    Script Date: 2018/3/28 下午 06:06:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[common_post_pic](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[CommonPostId] [bigint] NOT NULL,
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
 CONSTRAINT [PK_common_post_pic] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[event_type]    Script Date: 2018/3/28 下午 06:06:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[event_type](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[AlertCode] [varchar](8) NOT NULL,
	[Code] [varchar](8) NOT NULL,
	[Name] [nvarchar](max) NOT NULL,
	[StixXsd] [nvarchar](16) NULL,
	[IsEnable] [bit] NOT NULL,
	[Sort] [bigint] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
 CONSTRAINT [PK_event_type] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[forgot_temp]    Script Date: 2018/3/28 下午 06:06:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[forgot_temp](
	[MemberId] [bigint] NOT NULL,
	[ExpireTime] [datetime] NOT NULL,
	[Code] [varchar](128) NOT NULL,
 CONSTRAINT [PK__forgot_t__A25C5AA646672B0A] PRIMARY KEY CLUSTERED 
(
	[Code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[form]    Script Date: 2018/3/28 下午 06:06:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[form](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[SubsystemId] [bigint] NOT NULL,
	[Code] [varchar](32) NOT NULL,
	[Name] [nvarchar](max) NOT NULL,
	[ControllerName] [varchar](64) NOT NULL,
	[ActionName] [varchar](64) NOT NULL,
	[IsExternalLink] [bit] NOT NULL,
	[IsEnable] [bit] NOT NULL,
	[IsShow] [bit] NOT NULL,
	[Sort] [bigint] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
 CONSTRAINT [PK_form] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[healthcare]    Script Date: 2018/3/28 下午 06:06:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[healthcare](
	[Code] [varchar](10) NOT NULL,
	[Name] [nvarchar](128) NULL,
 CONSTRAINT [PK_healthcare_institution] PRIMARY KEY CLUSTERED 
(
	[Code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[information_exchange]    Script Date: 2018/3/28 下午 06:06:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[information_exchange](
	[Id] [varchar](32) NOT NULL,
	[PostId] [varchar](128) NULL,
	[SourceCode] [varchar](8) NULL,
	[StixTitle] [varchar](8) NULL,
	[IncidentId] [nvarchar](128) NULL,
	[IncidentTitle] [nvarchar](max) NULL,
	[IncidentDiscoveryTime] [datetime] NULL,
	[IncidentReportedTime] [datetime] NULL,
	[IncidentClosedTime] [datetime] NULL,
	[Description] [nvarchar](max) NULL,
	[Category] [varchar](8) NULL,
	[ReporterName] [nvarchar](256) NULL,
	[ResponderPartyName] [nvarchar](64) NULL,
	[ResponderContactNumbers] [nvarchar](64) NULL,
	[ResponderElectronicAddressIdentifiers] [nvarchar](128) NULL,
	[ImpactQualification] [tinyint] NULL,
	[CoaDescription] [nvarchar](max) NULL,
	[Confidence] [nvarchar](max) NULL,
	[Reference] [nvarchar](max) NULL,
	[ObservableAttach] [nvarchar](max) NULL,
	[ObservableIpAddress] [nvarchar](64) NULL,
	[SocketIpAddress] [nvarchar](64) NULL,
	[SocketPort] [nvarchar](64) NULL,
	[SocketProtocol] [nvarchar](64) NULL,
	[CustomIpAddress] [nvarchar](64) NULL,
	[CustomPort] [nvarchar](64) NULL,
	[CustomProtocol] [nvarchar](64) NULL,
	[DestinationIpAddress] [nvarchar](64) NULL,
	[DestinationPort] [nvarchar](64) NULL,
	[DestinationProtocol] [nvarchar](64) NULL,
	[LeveragedDescription] [nvarchar](max) NULL,
	[AffectedSoftwareDescription] [nvarchar](128) NULL,
	[ResourcesSourceIpAddress] [nvarchar](64) NULL,
	[ResourcesDestinationPort] [nvarchar](64) NULL,
	[ResourcesDestinationProtocol] [nvarchar](64) NULL,
	[ResourcesDestination] [nvarchar](128) NULL,
	[ScanEngine] [nvarchar](128) NULL,
	[ScanVersion] [nvarchar](128) NULL,
	[ScanResult] [nvarchar](128) NULL,
	[RelatedIncidentId] [nvarchar](128) NULL,
	[RelatedIncidentTimestamp] [datetime] NULL,
	[Status] [tinyint] NULL,
	[SourceContentXml] [varbinary](max) NULL,
	[IsEnable] [bit] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
	[ReporterNameUrl] [varchar](max) NULL,
	[NewsManagementGroupId] [bigint] NULL,
	[NhiProcess] [varchar](128) NULL,
	[NhiSourceRecord] [varchar](max) NULL,
	[NhiImpact] [varchar](max) NULL,
	[NhiRiskGrade] [varchar](128) NULL,
	[NhiRiskType] [varchar](128) NULL,
	[NhiProblemIpAddress] [varchar](64) NULL,
	[NhiProblemPort] [varchar](64) NULL,
	[NhiProblemUrl] [varchar](max) NULL,
	[NhiProblemEquipmentOwner] [varchar](128) NULL,
	[NhiProblemEquipmentUse] [varchar](max) NULL,
	[NhiRemark] [varchar](max) NULL,
	[TransferInType] [tinyint] NULL,
	[TransferInId] [varchar](32) NULL,
	[TransferOutType] [tinyint] NULL,
	[TransferOutId] [varchar](32) NULL,
	[ResourcesSourcePort] [nvarchar](64) NULL,
	[NisacIncidentId] [nvarchar](128) NULL,
	[NisacSourceContentXML] [varbinary](max) NULL,
 CONSTRAINT [PK_information_exchange] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[information_exchange_attach]    Script Date: 2018/3/28 下午 06:06:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[information_exchange_attach](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[InformationExchangeId] [varchar](32) NOT NULL,
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
 CONSTRAINT [PK_informationExchange_attach] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[information_source]    Script Date: 2018/3/28 下午 06:06:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[information_source](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Code] [varchar](8) NOT NULL,
	[Name] [nvarchar](max) NOT NULL,
	[IsEnable] [bit] NOT NULL,
	[Sort] [bigint] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
 CONSTRAINT [PK_information_source] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[links]    Script Date: 2018/3/28 下午 06:06:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[links](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[PostDateTime] [datetime] NOT NULL,
	[Title] [nvarchar](255) NOT NULL,
	[SourceName] [nvarchar](255) NULL,
	[SourceLink] [nvarchar](max) NULL,
	[IsBreakLine] [bit] NULL,
	[StartDateTime] [datetime] NULL,
	[EndDateTime] [datetime] NULL,
	[IsEnable] [bit] NULL,
	[CreateId] [bigint] NULL,
	[CreateTime] [datetime] NULL,
	[ModifyId] [bigint] NULL,
	[ModifyTime] [datetime] NULL,
 CONSTRAINT [PK_links] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[links_pic]    Script Date: 2018/3/28 下午 06:06:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[links_pic](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[LinksId] [bigint] NOT NULL,
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
 CONSTRAINT [PK_links_pic] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[member]    Script Date: 2018/3/28 下午 06:06:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[member](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[OrgId] [bigint] NOT NULL,
	[Account] [varchar](64) NOT NULL,
	[Name] [nvarchar](128) NOT NULL,
	[Email] [nvarchar](128) NOT NULL,
	[SpareEmail] [nvarchar](128) NULL,
	[MobilePhone] [varchar](16) NOT NULL,
	[CityPhone] [varchar](16) NULL,
	[FaxPhone] [varchar](16) NULL,
	[Address] [nvarchar](256) NULL,
	[Department] [nvarchar](128) NULL,
	[Title] [nvarchar](128) NULL,
	[IsEnable] [bit] NOT NULL,
	[EnableTime] [datetime] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
 CONSTRAINT [PK_member] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[member_history]    Script Date: 2018/3/28 下午 06:06:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[member_history](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[MemberId] [bigint] NOT NULL,
	[Password] [varchar](128) NOT NULL,
	[Salt] [varchar](32) NOT NULL,
	[ErrorCount] [smallint] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
 CONSTRAINT [PK_member_history] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[member_role]    Script Date: 2018/3/28 下午 06:06:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[member_role](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[MemberId] [bigint] NOT NULL,
	[RoleId] [bigint] NOT NULL,
	[IsEnable] [bit] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
 CONSTRAINT [PK_member_identity] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[message]    Script Date: 2018/3/28 下午 06:06:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[message](
	[Id] [varchar](32) NOT NULL,
	[PostId] [varchar](128) NOT NULL,
	[AlertCode] [nvarchar](8) NULL,
	[EventCode] [nvarchar](8) NULL,
	[SourceCode] [varchar](8) NULL,
	[ExternalId] [nvarchar](128) NULL,
	[FindDate] [date] NULL,
	[PostDateTime] [datetime] NULL,
	[Subject] [nvarchar](max) NULL,
	[Description] [nvarchar](max) NULL,
	[Suggestion] [nvarchar](max) NULL,
	[Reference] [nvarchar](max) NULL,
	[Determine] [nvarchar](max) NULL,
	[Contents] [nvarchar](max) NULL,
	[AffectPlatform] [nvarchar](max) NULL,
	[ImpactLevel] [varchar](1) NULL,
	[Status] [varchar](2) NULL,
	[IsReply] [bit] NULL,
	[IsEnable] [bit] NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
	[TransferInId] [varchar](32) NULL,
	[TransferOutType] [tinyint] NULL,
	[TransferInType] [tinyint] NULL,
	[TransferOutId] [varchar](32) NULL,
 CONSTRAINT [PK_message] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[message_group]    Script Date: 2018/3/28 下午 06:06:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[message_group](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NULL,
	[IsEnable] [bit] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
 CONSTRAINT [PK_message_group] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[message_group_org]    Script Date: 2018/3/28 下午 06:06:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[message_group_org](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[MessageGroupId] [bigint] NOT NULL,
	[OrgId] [bigint] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
 CONSTRAINT [PK_message_group_org] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[message_post]    Script Date: 2018/3/28 下午 06:06:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[message_post](
	[Id] [varchar](32) NOT NULL,
	[MessageId] [varchar](32) NOT NULL,
	[PostName] [nvarchar](100) NULL,
	[PostType] [nvarchar](50) NULL,
	[PostContent] [nvarchar](100) NULL,
	[IsEnable] [bit] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
 CONSTRAINT [PK_message_post] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[message_post_attach]    Script Date: 2018/3/28 下午 06:06:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[message_post_attach](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[MessageId] [varchar](32) NULL,
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
 CONSTRAINT [PK_message_post_attach] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[message_post_release]    Script Date: 2018/3/28 下午 06:06:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[message_post_release](
	[Id] [varchar](32) NOT NULL,
	[MessageId] [varchar](32) NOT NULL,
	[OrgId] [bigint] NOT NULL,
	[ReplyContent] [nvarchar](max) NULL,
	[IsCC] [bit] NOT NULL,
	[IsReview] [bit] NOT NULL,
	[Status] [nvarchar](64) NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
	[CreateId] [bigint] NULL,
	[ModifyId] [bigint] NULL,
	[TransferOutId] [varchar](32) NULL,
	[TransferOutType] [tinyint] NULL,
 CONSTRAINT [PK_message_post_release] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[news_management]    Script Date: 2018/3/28 下午 06:06:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[news_management](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[PostType] [nchar](1) NULL,
	[NewsManagementGroupId] [bigint] NULL,
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
	[TransferInType] [tinyint] NULL,
	[TransferInId] [varchar](32) NULL,
	[TransferOutType] [tinyint] NULL,
	[TransferOutId] [varchar](32) NULL,
 CONSTRAINT [PK_common_article] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[news_management_attach]    Script Date: 2018/3/28 下午 06:06:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[news_management_attach](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[NewsManagementId] [bigint] NOT NULL,
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
 CONSTRAINT [PK_news_management_attach] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[news_management_group]    Script Date: 2018/3/28 下午 06:06:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[news_management_group](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](32) NOT NULL,
	[IsEnable] [bit] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
 CONSTRAINT [PK_news_management] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[news_management_pic]    Script Date: 2018/3/28 下午 06:06:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[news_management_pic](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[NewsManagementId] [bigint] NOT NULL,
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
 CONSTRAINT [PK_news_management_pic] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[notification]    Script Date: 2018/3/28 下午 06:06:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[notification](
	[Id] [varchar](32) NOT NULL,
	[ApplyDateTime] [datetime] NOT NULL,
	[PostId] [nvarchar](64) NOT NULL,
	[ContactorUnit] [bigint] NULL,
	[MainUnit1] [bigint] NULL,
	[MainUnit2] [bigint] NULL,
	[ContactorId] [bigint] NULL,
	[ContactorTel] [nvarchar](32) NULL,
	[ContactorFax] [nvarchar](32) NULL,
	[ContactorEmail] [nvarchar](64) NULL,
	[IsSub] [bit] NULL,
	[IsSubUnitName] [nvarchar](128) NULL,
	[EventDateTime] [datetime] NULL,
	[HostAmount] [smallint] NULL,
	[ServerAmount] [smallint] NULL,
	[IpExternal] [nvarchar](32) NULL,
	[IpInternal] [nvarchar](32) NULL,
	[WebUrl] [nvarchar](128) NULL,
	[IsOsOpt1] [bit] NULL,
	[IsOsOpt2] [bit] NULL,
	[IsOsOpt3] [bit] NULL,
	[IsOsOpt3Other] [nvarchar](max) NULL,
	[IsGuardOpt1] [bit] NULL,
	[IsGuardOpt2] [bit] NULL,
	[IsGuardOpt3] [bit] NULL,
	[IsGuardOpt4] [bit] NULL,
	[IsGuardOpt4Other] [nvarchar](max) NULL,
	[SocOpt] [tinyint] NULL,
	[SocOptCompany] [nvarchar](max) NULL,
	[IsIsms] [bit] NULL,
	[MaintainCompany] [nvarchar](max) NULL,
	[CeffectLevel] [tinyint] NULL,
	[IeffectLevel] [tinyint] NULL,
	[AeffectLevel] [tinyint] NULL,
	[EffectLevel] [tinyint] NULL,
	[EventType] [tinyint] NULL,
	[IsEventType1Opt1] [bit] NULL,
	[IsEventType1Opt2] [bit] NULL,
	[IsEventType1Opt3] [bit] NULL,
	[IsEventType1Opt4] [bit] NULL,
	[IsEventType1Opt5] [bit] NULL,
	[IsEventType1Opt6] [bit] NULL,
	[IsEventType2Opt1] [bit] NULL,
	[IsEventType2Opt2] [bit] NULL,
	[IsEventType2Opt3] [bit] NULL,
	[IsEventType2Opt4] [bit] NULL,
	[IsEventType2Opt5] [bit] NULL,
	[IsEventType3Opt1] [bit] NULL,
	[IsEventType3Opt2] [bit] NULL,
	[IsEventType4Opt1] [bit] NULL,
	[IsEventType4Opt2] [bit] NULL,
	[IsEventType4Opt3] [bit] NULL,
	[IsEventType4Opt4] [bit] NULL,
	[EventType5Other] [nvarchar](max) NULL,
	[EventRemark] [nvarchar](max) NULL,
	[IsAffectOthers] [bit] NULL,
	[EventSource] [tinyint] NULL,
	[EventSourceNo] [nvarchar](128) NULL,
	[IsRes1LogOpt1] [bit] NULL,
	[IsRes1LogOpt2] [bit] NULL,
	[IsRes1LogOpt3] [bit] NULL,
	[IsRes1LogOpt4] [bit] NULL,
	[Res1LogOpt1] [tinyint] NULL,
	[Res1LogOpt1Other] [nvarchar](max) NULL,
	[Res1LogOpt2] [tinyint] NULL,
	[Res1LogOpt2Other] [nvarchar](max) NULL,
	[Res1LogOpt3Amount] [smallint] NULL,
	[Res1LogOpt4Remark] [nvarchar](max) NULL,
	[IsRes1EvaOpt1] [bit] NULL,
	[IsRes1EvaOpt2] [bit] NULL,
	[IsRes1EvaOpt3] [bit] NULL,
	[IsRes1EvaOpt4] [bit] NULL,
	[IsRes1EvaOpt5] [bit] NULL,
	[IsRes1EvaOpt6] [bit] NULL,
	[IsRes1EvaOpt7] [bit] NULL,
	[IsRes1EvaOpt8] [bit] NULL,
	[Res1EvaOpt1Remark] [nvarchar](max) NULL,
	[Res1EvaOpt2Remark] [nvarchar](max) NULL,
	[Res1EvaOpt3Remark] [nvarchar](max) NULL,
	[Res1EvaOpt4Remark] [nvarchar](max) NULL,
	[Res1EvaOpt6Amount] [smallint] NULL,
	[Res1EvaOpt6Type] [tinyint] NULL,
	[Res1EvaOpt6TypeOpt3Other] [nvarchar](max) NULL,
	[Res1EvaOpt7Amount] [smallint] NULL,
	[Res1EvaOpt7Remark] [nvarchar](max) NULL,
	[Res1EvaOpt8Remark] [nvarchar](max) NULL,
	[IsRes1DoOpt1] [bit] NULL,
	[IsRes1DoOpt2] [bit] NULL,
	[IsRes1DoOpt3] [bit] NULL,
	[IsRes1DoOpt4] [bit] NULL,
	[IsRes1DoOpt5] [bit] NULL,
	[IsRes1DoOpt6] [bit] NULL,
	[IsRes1DoOpt7] [bit] NULL,
	[IsRes1DoOpt8] [bit] NULL,
	[IsRes1DoOpt9] [bit] NULL,
	[IsRes1DoOpt10] [bit] NULL,
	[IsRes1DoOpt11] [bit] NULL,
	[IsRes1DoOpt12] [bit] NULL,
	[Res1DoOpt1Amount] [smallint] NULL,
	[Res1DoOpt2Remark] [nvarchar](max) NULL,
	[Res1DoOpt3Remark] [nvarchar](max) NULL,
	[Res1DoOpt4Remark] [nvarchar](max) NULL,
	[Res1DoOpt5Amount] [smallint] NULL,
	[IsRes1DoOpt9EngineOpt1] [bit] NULL,
	[IsRes1DoOpt9EngineOpt2] [bit] NULL,
	[IsRes1DoOpt9EngineOpt3] [bit] NULL,
	[IsRes1DoOpt9EngineOpt4] [bit] NULL,
	[IsRes1DoOpt9EngineOpt5] [bit] NULL,
	[IsRes1DoOpt9EngineOpt6] [bit] NULL,
	[Res1DoOpt9EngineOpt6Other] [nvarchar](max) NULL,
	[Res1DoOpt10Date] [date] NULL,
	[Res1DoOpt11Date] [date] NULL,
	[Res1DoOpt12Remark] [nvarchar](max) NULL,
	[IsRes2LogOpt1] [bit] NULL,
	[IsRes2LogOpt2] [bit] NULL,
	[IsRes2LogOpt3] [bit] NULL,
	[IsRes2LogOpt4] [bit] NULL,
	[Res2LogOpt1] [tinyint] NULL,
	[Res2LogOpt1Other] [nvarchar](max) NULL,
	[Res2LogOpt2] [tinyint] NULL,
	[Res2LogOpt2Other] [nvarchar](max) NULL,
	[Res2LogOpt3Amount] [smallint] NULL,
	[Res2LogOpt4Remark] [nvarchar](max) NULL,
	[IsRes2EvaOpt1] [bit] NULL,
	[IsRes2EvaOpt2] [bit] NULL,
	[IsRes2EvaOpt3] [bit] NULL,
	[IsRes2EvaOpt4] [bit] NULL,
	[IsRes2EvaOpt5] [bit] NULL,
	[Res2EvaOpt1Remark] [nvarchar](max) NULL,
	[Res2EvaOpt2Remark] [nvarchar](max) NULL,
	[Res2EvaOpt3Remark] [nvarchar](max) NULL,
	[Res2EvaOpt4Remark] [nvarchar](max) NULL,
	[Res2EvaOpt5Remark] [nvarchar](max) NULL,
	[IsRes2DoOpt1] [bit] NULL,
	[IsRes2DoOpt2] [bit] NULL,
	[IsRes2DoOpt3] [bit] NULL,
	[IsRes2DoOpt4] [bit] NULL,
	[IsRes2DoOpt5] [bit] NULL,
	[IsRes2DoOpt6] [bit] NULL,
	[IsRes2DoOpt7] [bit] NULL,
	[Res2DoOpt1Remark] [nvarchar](max) NULL,
	[Res2DoOpt2Remark] [nvarchar](max) NULL,
	[Res2DoOpt3Remark] [nvarchar](max) NULL,
	[Res2DoOpt6Amount] [smallint] NULL,
	[Res2DoOpt7Remark] [nvarchar](max) NULL,
	[IsRes3LogOpt1] [bit] NULL,
	[IsRes3LogOpt2] [bit] NULL,
	[IsRes3LogOpt3] [bit] NULL,
	[IsRes3LogOpt4] [bit] NULL,
	[Res3LogOpt1] [tinyint] NULL,
	[Res3LogOpt1Other] [nvarchar](max) NULL,
	[Res3LogOpt2] [tinyint] NULL,
	[Res3LogOpt2Other] [nvarchar](max) NULL,
	[Res3LogOpt3Amount] [smallint] NULL,
	[Res3LogOpt4Remark] [nvarchar](max) NULL,
	[IsRes3EvaOpt1] [bit] NULL,
	[IsRes3EvaOpt2] [bit] NULL,
	[IsRes3EvaOpt3] [bit] NULL,
	[Res3EvaOpt1Amount] [smallint] NULL,
	[Res3EvaOpt2Remark] [nvarchar](max) NULL,
	[Res3EvaOpt3Remark] [nvarchar](max) NULL,
	[IsRes3DoOpt1] [bit] NULL,
	[IsRes3DoOpt2] [bit] NULL,
	[IsRes3DoOpt3] [bit] NULL,
	[IsRes3DoOpt4] [bit] NULL,
	[Res3DoOpt1Remark] [nvarchar](max) NULL,
	[Res3DoOpt3Remark] [nvarchar](max) NULL,
	[Res3DoOpt4Remark] [nvarchar](max) NULL,
	[IsRes4LogOpt1] [bit] NULL,
	[Res4LogOpt1Remark] [nvarchar](max) NULL,
	[IsRes4EvaOpt1] [bit] NULL,
	[IsRes4EvaOpt2] [bit] NULL,
	[Res4EvaOpt1] [tinyint] NULL,
	[Res4EvaOpt1Amount] [smallint] NULL,
	[Res4EvaOpt2Remark] [nvarchar](max) NULL,
	[IsRes4DoOpt1] [bit] NULL,
	[IsRes4DoOpt2] [bit] NULL,
	[IsRes4DoOpt3] [bit] NULL,
	[Res4DoOpt3Remark] [nvarchar](max) NULL,
	[IsRes5LogOpt1] [bit] NULL,
	[IsRes5LogOpt2] [bit] NULL,
	[IsRes5LogOpt3] [bit] NULL,
	[IsRes5LogOpt4] [bit] NULL,
	[Res5LogOpt1] [tinyint] NULL,
	[Res5LogOpt1Other] [nvarchar](max) NULL,
	[Res5LogOpt2] [tinyint] NULL,
	[Res5LogOpt2Other] [nvarchar](max) NULL,
	[Res5LogOpt3Amount] [smallint] NULL,
	[Res5LogOpt4Remark] [nvarchar](max) NULL,
	[IsRes5EvaOpt1] [bit] NULL,
	[IsRes5EvaOpt2] [bit] NULL,
	[IsRes5EvaOpt3] [bit] NULL,
	[IsRes5EvaOpt4] [bit] NULL,
	[IsRes5EvaOpt5] [bit] NULL,
	[Res5EvaOpt1Remark] [nvarchar](max) NULL,
	[Res5EvaOpt2Remark] [nvarchar](max) NULL,
	[Res5EvaOpt3Remark] [nvarchar](max) NULL,
	[Res5EvaOpt4Remark] [nvarchar](max) NULL,
	[Res5EvaOpt5Remark] [nvarchar](max) NULL,
	[IsRes5DoOpt1] [bit] NULL,
	[IsRes5DoOpt2] [bit] NULL,
	[IsRes5DoOpt3] [bit] NULL,
	[IsRes5DoOpt4] [bit] NULL,
	[IsRes5DoOpt5] [bit] NULL,
	[IsRes5DoOpt6] [bit] NULL,
	[IsRes5DoOpt7] [bit] NULL,
	[Res5DoOpt1Remark] [nvarchar](max) NULL,
	[Res5DoOpt2Remark] [nvarchar](max) NULL,
	[Res5DoOpt3Remark] [nvarchar](max) NULL,
	[Res5DoOpt5Date] [date] NULL,
	[Res5DoOpt6Amount] [smallint] NULL,
	[Res5DoOpt7Remark] [nvarchar](max) NULL,
	[IsNeedSupport] [bit] NULL,
	[NeedSupportRemark] [nvarchar](max) NULL,
	[Finish1Reason] [tinyint] NULL,
	[Finish1ReasonOther] [nvarchar](max) NULL,
	[IsFinish1DoSysOpt1] [bit] NULL,
	[IsFinish1DoSysOpt2] [bit] NULL,
	[IsFinish1DoSysOpt3] [bit] NULL,
	[IsFinish1DoSysOpt4] [bit] NULL,
	[IsFinish1DoSysOpt5] [bit] NULL,
	[IsFinish1DoSysOpt6] [bit] NULL,
	[IsFinish1DoSysOpt7] [bit] NULL,
	[IsFinish1DoSysOpt8] [bit] NULL,
	[IsFinish1DoSysOpt9] [bit] NULL,
	[IsFinish1DoSysOpt10] [bit] NULL,
	[Finish1DoSysOpt3Remark] [nvarchar](max) NULL,
	[Finish1DoSysOpt6Remark] [nvarchar](max) NULL,
	[Finish1DoSysOpt7Remark] [nvarchar](max) NULL,
	[IsFinish1DoEduOpt1] [bit] NULL,
	[IsFinish1DoEduOpt2] [bit] NULL,
	[IsFinish1DoEduOpt3] [bit] NULL,
	[IsFinish1DoEduOpt4] [bit] NULL,
	[Finish2Reason] [tinyint] NULL,
	[Finish2ReasonOther] [nvarchar](max) NULL,
	[Finish2ReasonRemark] [nvarchar](max) NULL,
	[IsFinish2DoSysOpt1] [bit] NULL,
	[IsFinish2DoSysOpt2] [bit] NULL,
	[IsFinish2DoSysOpt3] [bit] NULL,
	[IsFinish2DoSysOpt4] [bit] NULL,
	[IsFinish2DoSysOpt5] [bit] NULL,
	[Finish2DoSysOpt1Remark] [nvarchar](max) NULL,
	[IsFinish2DoEduOpt1] [bit] NULL,
	[IsFinish2DoEduOpt2] [bit] NULL,
	[IsFinish2DoEduOpt3] [bit] NULL,
	[IsFinish2DoEduOpt4] [bit] NULL,
	[IsFinish3DoSysOpt1] [bit] NULL,
	[IsFinish3DoSysOpt2] [bit] NULL,
	[IsFinish3DoSysOpt3] [bit] NULL,
	[IsFinish3DoSysOpt4] [bit] NULL,
	[Finish3DoSysOpt3Remark] [nvarchar](max) NULL,
	[Finish3DoSysOpt4Remark] [nvarchar](max) NULL,
	[IsFinish3DoEduOpt1] [bit] NULL,
	[IsFinish3DoEduOpt2] [bit] NULL,
	[Finish4Reason] [tinyint] NULL,
	[Finish4ReasonOther] [nvarchar](max) NULL,
	[Finish4ReasonRemark] [nvarchar](max) NULL,
	[IsFinish4DoSysOpt1] [bit] NULL,
	[IsFinish4DoEduOpt1] [bit] NULL,
	[IsFinish4DoEduOpt2] [bit] NULL,
	[IsFinish4DoEduOpt3] [bit] NULL,
	[IsFinish4DoEduOpt4] [bit] NULL,
	[Finish5Reason] [tinyint] NULL,
	[Finish5ReasonOther] [nvarchar](max) NULL,
	[Finish5ReasonRemark] [nvarchar](max) NULL,
	[IsFinish5DoSysOpt1] [bit] NULL,
	[IsFinish5DoSysOpt2] [bit] NULL,
	[IsFinish5DoSysOpt3] [bit] NULL,
	[IsFinish5DoSysOpt4] [bit] NULL,
	[Finish5DoSysOpt1Remark] [nvarchar](max) NULL,
	[IsFinish5DoEduOpt1] [bit] NULL,
	[IsFinish5DoEduOpt2] [bit] NULL,
	[IsFinish5DoEduOpt3] [bit] NULL,
	[IsFinish5DoEduOpt4] [bit] NULL,
	[FinishDoOther] [nvarchar](max) NULL,
	[Status] [tinyint] NULL,
	[FinishDateTime] [datetime] NULL,
	[ExamineDateTime] [datetime] NULL,
	[RealFinishDateTime] [datetime] NULL,
	[MessageId] [varchar](32) NULL,
	[MessagePostId] [varchar](128) NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
	[IsCC3] [bit] NOT NULL,
	[IsCC5] [bit] NOT NULL,
	[IsReview5] [bit] NOT NULL,
	[IsReview3] [bit] NOT NULL,
	[TransferInId] [varchar](32) NULL,
	[TransferOutType] [tinyint] NULL,
	[TransferInType] [tinyint] NULL,
	[TransferOutId] [varchar](32) NULL,
 CONSTRAINT [PK_notification] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[org]    Script Date: 2018/3/28 下午 06:06:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[org](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Code] [nvarchar](16) NOT NULL,
	[OrgType] [varchar](1) NOT NULL,
	[AuthType] [varchar](1) NOT NULL,
	[Tel] [nvarchar](15) NULL,
	[Fax] [nvarchar](15) NULL,
	[City] [nvarchar](10) NULL,
	[Town] [nvarchar](10) NULL,
	[Address] [nvarchar](256) NULL,
	[IsEnable] [bit] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
 CONSTRAINT [PK_org] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[org_sign]    Script Date: 2018/3/28 下午 06:06:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[org_sign](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[OrgId] [bigint] NOT NULL,
	[ParentOrgId] [bigint] NOT NULL,
	[WarningIsExamine] [tinyint] NULL,
	[NotificationIsExamine] [tinyint] NULL,
	[NotificationClosingIsExamine] [tinyint] NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
 CONSTRAINT [PK_org_sign] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[process_log]    Script Date: 2018/3/28 下午 06:06:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[process_log](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[PostId] [varchar](32) NOT NULL,
	[TableName] [varchar](64) NOT NULL,
	[PreStatus] [varchar](64) NOT NULL,
	[Status] [varchar](64) NOT NULL,
	[PreId] [bigint] NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[Opinion] [nvarchar](max) NULL,
 CONSTRAINT [PK_process_log] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[qa_management]    Script Date: 2018/3/28 下午 06:06:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[qa_management](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[QAManagementGroupId] [bigint] NOT NULL,
	[QName] [nvarchar](200) NOT NULL,
	[AName] [nvarchar](max) NOT NULL,
	[IsEnable] [bit] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
 CONSTRAINT [PK_qa_management2] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[qa_management_group]    Script Date: 2018/3/28 下午 06:06:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[qa_management_group](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](200) NOT NULL,
	[IsEnable] [bit] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
 CONSTRAINT [PK_qa_management] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[resource_message]    Script Date: 2018/3/28 下午 06:06:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[resource_message](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[MessageKey] [varchar](128) NOT NULL,
	[MessageValue] [nvarchar](max) NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
 CONSTRAINT [PK_resource_message] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[role]    Script Date: 2018/3/28 下午 06:06:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[role](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](max) NOT NULL,
	[IsEnable] [bit] NOT NULL,
	[Sort] [bigint] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
 CONSTRAINT [PK_role] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[role_form]    Script Date: 2018/3/28 下午 06:06:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[role_form](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[RoleId] [bigint] NOT NULL,
	[FormId] [bigint] NOT NULL,
	[ActionCreate] [bit] NOT NULL,
	[ActionUpdate] [bit] NOT NULL,
	[ActionDelete] [bit] NOT NULL,
	[ActionRead] [bit] NOT NULL,
	[ActionSign] [bit] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
 CONSTRAINT [PK_role_acl] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sso]    Script Date: 2018/3/28 下午 06:06:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sso](
	[MemberId] [bigint] NOT NULL,
	[ExpireTime] [datetime] NOT NULL,
	[Code] [varchar](128) NOT NULL,
 CONSTRAINT [PK_sso] PRIMARY KEY CLUSTERED 
(
	[Code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[subsystem]    Script Date: 2018/3/28 下午 06:06:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[subsystem](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Code] [varchar](32) NOT NULL,
	[IconStyle] [varchar](32) NULL,
	[Name] [nvarchar](max) NOT NULL,
	[IsEnable] [bit] NOT NULL,
	[IsShow] [bit] NOT NULL,
	[Sort] [bigint] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
 CONSTRAINT [PK_grp] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[system_counter]    Script Date: 2018/3/28 下午 06:06:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[system_counter](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Ip] [varchar](1024) NOT NULL,
	[CreateTime] [datetime] NOT NULL,
 CONSTRAINT [PK_system_counter] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[system_log]    Script Date: 2018/3/28 下午 06:06:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[system_log](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[AppName] [varchar](64) NOT NULL,
	[FuncName] [varchar](64) NOT NULL,
	[InputValue] [nvarchar](max) NOT NULL,
	[ActionName] [varchar](16) NOT NULL,
	[Status] [varchar](16) NOT NULL,
	[Ip] [varchar](1024) NOT NULL,
	[HashCode] [varchar](128) NOT NULL,
	[CreateAccount] [varchar](64) NOT NULL,
	[CreateTime] [datetime] NOT NULL,
 CONSTRAINT [PK_action_log] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ticket_queue]    Script Date: 2018/3/28 下午 06:06:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ticket_queue](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[TableName] [varchar](50) NOT NULL,
	[IsApply] [bit] NOT NULL,
	[Pre] [varchar](16) NOT NULL,
	[Code] [varchar](16) NOT NULL,
	[Year] [varchar](4) NOT NULL,
	[Number] [int] NOT NULL,
 CONSTRAINT [PK_ticket_queue] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[weakness_management]    Script Date: 2018/3/28 下午 06:06:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[weakness_management](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[IncidentId] [nvarchar](128) NULL,
	[IncidentTitle] [nvarchar](max) NOT NULL,
	[IncidentDiscoveryTime] [datetime] NOT NULL,
	[IncidentReportedTime] [datetime] NULL,
	[Description] [nvarchar](max) NULL,
	[EventTypeCode] [varchar](8) NOT NULL,
	[ReporterName] [nvarchar](256) NOT NULL,
	[ResponderPartyName] [nvarchar](64) NULL,
	[ResponderContactNumbers] [nvarchar](64) NULL,
	[ResponderElectronicAddressIdentifiers] [nvarchar](128) NULL,
	[ImpactQualification] [tinyint] NOT NULL,
	[CoaDescription] [nvarchar](max) NULL,
	[Confidence] [nvarchar](128) NULL,
	[Reference] [nvarchar](max) NULL,
	[AffectedSoftwareDescription] [nvarchar](128) NULL,
	[StartDateTime] [datetime] NULL,
	[EndDateTime] [datetime] NULL,
	[IsEnable] [bit] NULL,
	[CreateId] [bigint] NULL,
	[CreateTime] [datetime] NULL,
	[ModifyId] [bigint] NULL,
	[ModifyTime] [datetime] NULL,
	[Status] [tinyint] NULL,
	[PostId] [varchar](128) NULL,
	[TransferInType] [tinyint] NULL,
	[TransferInId] [varchar](32) NULL,
	[TransferOutType] [tinyint] NULL,
	[TransferOutId] [varchar](32) NULL,
 CONSTRAINT [PK_weakness_management] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[weakness_management_attach]    Script Date: 2018/3/28 下午 06:06:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[weakness_management_attach](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[WeaknessManagementId] [bigint] NOT NULL,
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
 CONSTRAINT [PK_weakness_management_attach] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE [dbo].[information_exchange] ADD  CONSTRAINT [DF__informati__Trans__66D536B1]  DEFAULT ((0)) FOR [TransferInType]
GO
ALTER TABLE [dbo].[information_exchange] ADD  CONSTRAINT [DF__informati__Trans__67C95AEA]  DEFAULT ((0)) FOR [TransferOutType]
GO
ALTER TABLE [dbo].[message] ADD  CONSTRAINT [DF__message__Transfe__444B1483]  DEFAULT ((0)) FOR [TransferOutType]
GO
ALTER TABLE [dbo].[message] ADD  CONSTRAINT [DF__message__Transfe__453F38BC]  DEFAULT ((0)) FOR [TransferInType]
GO
ALTER TABLE [dbo].[message_post_release] ADD  CONSTRAINT [DF__message_p__Trans__46335CF5]  DEFAULT ((0)) FOR [TransferOutType]
GO
ALTER TABLE [dbo].[notification] ADD  DEFAULT ((0)) FOR [IsCC3]
GO
ALTER TABLE [dbo].[notification] ADD  DEFAULT ((0)) FOR [IsCC5]
GO
ALTER TABLE [dbo].[notification] ADD  DEFAULT ((0)) FOR [IsReview5]
GO
ALTER TABLE [dbo].[notification] ADD  DEFAULT ((0)) FOR [IsReview3]
GO
ALTER TABLE [dbo].[activity_management_attach]  WITH CHECK ADD  CONSTRAINT [FK_activity_management_attach_activity_management] FOREIGN KEY([ActivityManagementId])
REFERENCES [dbo].[activity_management] ([Id])
GO
ALTER TABLE [dbo].[activity_management_attach] CHECK CONSTRAINT [FK_activity_management_attach_activity_management]
GO
ALTER TABLE [dbo].[activity_management_pic]  WITH CHECK ADD  CONSTRAINT [FK_activity_management_pic_activity_management] FOREIGN KEY([ActivityManagementId])
REFERENCES [dbo].[activity_management] ([Id])
GO
ALTER TABLE [dbo].[activity_management_pic] CHECK CONSTRAINT [FK_activity_management_pic_activity_management]
GO
ALTER TABLE [dbo].[ana_management_attach]  WITH CHECK ADD  CONSTRAINT [FK_ana_management_attach_ana_management] FOREIGN KEY([AnaManagementId])
REFERENCES [dbo].[ana_management] ([Id])
GO
ALTER TABLE [dbo].[ana_management_attach] CHECK CONSTRAINT [FK_ana_management_attach_ana_management]
GO
ALTER TABLE [dbo].[common_post_attach]  WITH CHECK ADD  CONSTRAINT [FK_common_post_attach_common_post] FOREIGN KEY([CommonPostId])
REFERENCES [dbo].[common_post] ([Id])
GO
ALTER TABLE [dbo].[common_post_attach] CHECK CONSTRAINT [FK_common_post_attach_common_post]
GO
ALTER TABLE [dbo].[common_post_pic]  WITH CHECK ADD  CONSTRAINT [FK_common_post_pic_common_post] FOREIGN KEY([CommonPostId])
REFERENCES [dbo].[common_post] ([Id])
GO
ALTER TABLE [dbo].[common_post_pic] CHECK CONSTRAINT [FK_common_post_pic_common_post]
GO
ALTER TABLE [dbo].[event_type]  WITH CHECK ADD  CONSTRAINT [FK_event_type_alert_type] FOREIGN KEY([AlertCode])
REFERENCES [dbo].[alert_type] ([Code])
GO
ALTER TABLE [dbo].[event_type] CHECK CONSTRAINT [FK_event_type_alert_type]
GO
ALTER TABLE [dbo].[forgot_temp]  WITH CHECK ADD  CONSTRAINT [FK_fogot_temp_member] FOREIGN KEY([MemberId])
REFERENCES [dbo].[member] ([Id])
GO
ALTER TABLE [dbo].[forgot_temp] CHECK CONSTRAINT [FK_fogot_temp_member]
GO
ALTER TABLE [dbo].[form]  WITH CHECK ADD  CONSTRAINT [FK_form_grp] FOREIGN KEY([SubsystemId])
REFERENCES [dbo].[subsystem] ([Id])
GO
ALTER TABLE [dbo].[form] CHECK CONSTRAINT [FK_form_grp]
GO
ALTER TABLE [dbo].[links_pic]  WITH CHECK ADD  CONSTRAINT [FK_links_pic_links] FOREIGN KEY([LinksId])
REFERENCES [dbo].[links] ([Id])
GO
ALTER TABLE [dbo].[links_pic] CHECK CONSTRAINT [FK_links_pic_links]
GO
ALTER TABLE [dbo].[member]  WITH CHECK ADD  CONSTRAINT [FK_member_org] FOREIGN KEY([OrgId])
REFERENCES [dbo].[org] ([Id])
GO
ALTER TABLE [dbo].[member] CHECK CONSTRAINT [FK_member_org]
GO
ALTER TABLE [dbo].[member_history]  WITH CHECK ADD  CONSTRAINT [FK_member_history_member] FOREIGN KEY([MemberId])
REFERENCES [dbo].[member] ([Id])
GO
ALTER TABLE [dbo].[member_history] CHECK CONSTRAINT [FK_member_history_member]
GO
ALTER TABLE [dbo].[member_role]  WITH CHECK ADD  CONSTRAINT [FK_member_role_member] FOREIGN KEY([MemberId])
REFERENCES [dbo].[member] ([Id])
GO
ALTER TABLE [dbo].[member_role] CHECK CONSTRAINT [FK_member_role_member]
GO
ALTER TABLE [dbo].[member_role]  WITH CHECK ADD  CONSTRAINT [FK_member_role_role] FOREIGN KEY([RoleId])
REFERENCES [dbo].[role] ([Id])
GO
ALTER TABLE [dbo].[member_role] CHECK CONSTRAINT [FK_member_role_role]
GO
ALTER TABLE [dbo].[message_group_org]  WITH CHECK ADD  CONSTRAINT [FK_message_group_org_message_group] FOREIGN KEY([MessageGroupId])
REFERENCES [dbo].[message_group] ([Id])
GO
ALTER TABLE [dbo].[message_group_org] CHECK CONSTRAINT [FK_message_group_org_message_group]
GO
ALTER TABLE [dbo].[message_post]  WITH CHECK ADD  CONSTRAINT [FK_message_post_message] FOREIGN KEY([MessageId])
REFERENCES [dbo].[message] ([Id])
GO
ALTER TABLE [dbo].[message_post] CHECK CONSTRAINT [FK_message_post_message]
GO
ALTER TABLE [dbo].[message_post_attach]  WITH CHECK ADD  CONSTRAINT [FK_message_post_attach_message1] FOREIGN KEY([MessageId])
REFERENCES [dbo].[message] ([Id])
GO
ALTER TABLE [dbo].[message_post_attach] CHECK CONSTRAINT [FK_message_post_attach_message1]
GO
ALTER TABLE [dbo].[message_post_release]  WITH CHECK ADD  CONSTRAINT [FK_message_post_release_message] FOREIGN KEY([MessageId])
REFERENCES [dbo].[message] ([Id])
GO
ALTER TABLE [dbo].[message_post_release] CHECK CONSTRAINT [FK_message_post_release_message]
GO
ALTER TABLE [dbo].[message_post_release]  WITH CHECK ADD  CONSTRAINT [FK_message_post_release_org] FOREIGN KEY([OrgId])
REFERENCES [dbo].[org] ([Id])
GO
ALTER TABLE [dbo].[message_post_release] CHECK CONSTRAINT [FK_message_post_release_org]
GO
ALTER TABLE [dbo].[news_management_attach]  WITH CHECK ADD  CONSTRAINT [FK_news_management_attach_news_management] FOREIGN KEY([NewsManagementId])
REFERENCES [dbo].[news_management] ([Id])
GO
ALTER TABLE [dbo].[news_management_attach] CHECK CONSTRAINT [FK_news_management_attach_news_management]
GO
ALTER TABLE [dbo].[news_management_pic]  WITH CHECK ADD  CONSTRAINT [FK_news_management_pic_news_management] FOREIGN KEY([NewsManagementId])
REFERENCES [dbo].[news_management] ([Id])
GO
ALTER TABLE [dbo].[news_management_pic] CHECK CONSTRAINT [FK_news_management_pic_news_management]
GO
ALTER TABLE [dbo].[org_sign]  WITH CHECK ADD  CONSTRAINT [FK_org_sign_org] FOREIGN KEY([OrgId])
REFERENCES [dbo].[org] ([Id])
GO
ALTER TABLE [dbo].[org_sign] CHECK CONSTRAINT [FK_org_sign_org]
GO
ALTER TABLE [dbo].[qa_management]  WITH CHECK ADD  CONSTRAINT [FK_qa_management_qa_management_group] FOREIGN KEY([QAManagementGroupId])
REFERENCES [dbo].[qa_management_group] ([Id])
GO
ALTER TABLE [dbo].[qa_management] CHECK CONSTRAINT [FK_qa_management_qa_management_group]
GO
ALTER TABLE [dbo].[role_form]  WITH CHECK ADD  CONSTRAINT [FK_role_acl_form] FOREIGN KEY([FormId])
REFERENCES [dbo].[form] ([Id])
GO
ALTER TABLE [dbo].[role_form] CHECK CONSTRAINT [FK_role_acl_form]
GO
ALTER TABLE [dbo].[role_form]  WITH CHECK ADD  CONSTRAINT [FK_role_form_role] FOREIGN KEY([RoleId])
REFERENCES [dbo].[role] ([Id])
GO
ALTER TABLE [dbo].[role_form] CHECK CONSTRAINT [FK_role_form_role]
GO
ALTER TABLE [dbo].[weakness_management_attach]  WITH CHECK ADD  CONSTRAINT [FK_weakness_management_attach_weakness_management] FOREIGN KEY([WeaknessManagementId])
REFERENCES [dbo].[weakness_management] ([Id])
GO
ALTER TABLE [dbo].[weakness_management_attach] CHECK CONSTRAINT [FK_weakness_management_attach_weakness_management]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'公開資訊分類
1:最新消息
2:最新活動' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management', @level2type=N'COLUMN',@level2name=N'PostType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management', @level2type=N'COLUMN',@level2name=N'PostDateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'標題' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management', @level2type=N'COLUMN',@level2name=N'Title'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'來源名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management', @level2type=N'COLUMN',@level2name=N'SourceName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'來源網址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management', @level2type=N'COLUMN',@level2name=N'SourceLink'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'文章型態
1:內容文件
2:外部連結' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management', @level2type=N'COLUMN',@level2name=N'ContentType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'內容' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management', @level2type=N'COLUMN',@level2name=N'Content'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'外部連結' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management', @level2type=N'COLUMN',@level2name=N'ExternalLink'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'內容是否自動分行
0:否
1:是' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management', @level2type=N'COLUMN',@level2name=N'IsBreakLine'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發布開始日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management', @level2type=N'COLUMN',@level2name=N'StartDateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發布結束日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management', @level2type=N'COLUMN',@level2name=N'EndDateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否啟用
0:不啟用
1:啟用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management', @level2type=N'COLUMN',@level2name=N'IsEnable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'狀態' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management', @level2type=N'COLUMN',@level2name=N'Status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'活動編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management', @level2type=N'COLUMN',@level2name=N'PostId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'最新活動管理' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'自動編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_attach', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'最新活動文章Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_attach', @level2type=N'COLUMN',@level2name=N'ActivityManagementId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_attach', @level2type=N'COLUMN',@level2name=N'FileName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案類型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_attach', @level2type=N'COLUMN',@level2name=N'FileType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案內容' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_attach', @level2type=N'COLUMN',@level2name=N'FileContent'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案雜湊值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_attach', @level2type=N'COLUMN',@level2name=N'FileHash'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案大小' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_attach', @level2type=N'COLUMN',@level2name=N'FileSize'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案說明' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_attach', @level2type=N'COLUMN',@level2name=N'FileDesc'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_attach', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_attach', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_attach', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_attach', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'最新活動文章附件' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_attach'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'自動編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_pic', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'最新活動文章Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_pic', @level2type=N'COLUMN',@level2name=N'ActivityManagementId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_pic', @level2type=N'COLUMN',@level2name=N'FileName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔類型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_pic', @level2type=N'COLUMN',@level2name=N'FileType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔內容' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_pic', @level2type=N'COLUMN',@level2name=N'FileContent'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔雜湊值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_pic', @level2type=N'COLUMN',@level2name=N'FileHash'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔大小' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_pic', @level2type=N'COLUMN',@level2name=N'FileSize'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔寬度' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_pic', @level2type=N'COLUMN',@level2name=N'ImageWidth'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔高度' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_pic', @level2type=N'COLUMN',@level2name=N'ImageHeight'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔說明' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_pic', @level2type=N'COLUMN',@level2name=N'FileDesc'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_pic', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_pic', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_pic', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_pic', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'最新活動文章引用圖檔' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'activity_management_pic'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'alert_type', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'代號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'alert_type', @level2type=N'COLUMN',@level2name=N'Code'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'alert_type', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否啟用
0:不啟用
1:啟用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'alert_type', @level2type=N'COLUMN',@level2name=N'IsEnable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'顯示排序號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'alert_type', @level2type=N'COLUMN',@level2name=N'Sort'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'alert_type', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'alert_type', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'alert_type', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'alert_type', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'警訊種類資料表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'alert_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'自動編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發送編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'IncidentId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'情報名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'IncidentTitle'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發現時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'IncidentDiscoveryTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發送時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'IncidentReportedTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'內容說明' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'Description'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事件類型代號
enent_type.Code' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'EventTypeCode'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發布單位' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'ReporterName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'聯絡資訊 (姓名)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'ResponderPartyName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'聯絡資訊 (電話)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'ResponderContactNumbers'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'聯絡資訊 (電子郵件)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'ResponderElectronicAddressIdentifiers'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'影響等級
1: 低 (low)
2: 中 (medium)
3: 高 (high)
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'ImpactQualification'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建議措施/解決辦法' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'CoaDescription'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保密程度' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'Confidence'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'參考資料' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'Reference'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'影響平台' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'AffectedSoftwareDescription'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發布開始日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'StartDateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發布結束日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'EndDateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否啟用
0: 不啟用
1: 啟用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'IsEnable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立使用者
系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動使用者
系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'狀態
1: 編輯中
2: 撤銷中
3: 審核中
4: 已公告
5: 已銷案' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'Status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'資安訊息編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'PostId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'轉入型態
0:
3: 通報轉資安訊息情報' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'TransferInType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'轉入資料來源Id
TransferInType=3
InformationExchange.Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'TransferInId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'轉出型態
0:' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'TransferOutType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'轉出目的資料Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management', @level2type=N'COLUMN',@level2name=N'TransferOutId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'自動編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management_attach', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'資安資訊情報文章Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management_attach', @level2type=N'COLUMN',@level2name=N'AnaManagementId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management_attach', @level2type=N'COLUMN',@level2name=N'FileName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案類型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management_attach', @level2type=N'COLUMN',@level2name=N'FileType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案內容' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management_attach', @level2type=N'COLUMN',@level2name=N'FileContent'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案雜湊值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management_attach', @level2type=N'COLUMN',@level2name=N'FileHash'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案大小' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management_attach', @level2type=N'COLUMN',@level2name=N'FileSize'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案說明' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management_attach', @level2type=N'COLUMN',@level2name=N'FileDesc'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management_attach', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management_attach', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management_attach', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management_attach', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'資安資訊情報文章附件' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ana_management_attach'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'自動編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'文章分類
1:認識本系統
2:組織架構
3:何謂ISAC' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post', @level2type=N'COLUMN',@level2name=N'PostType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'標題' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post', @level2type=N'COLUMN',@level2name=N'Title'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'內容' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post', @level2type=N'COLUMN',@level2name=N'Content'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'內容是否自動分行
0:否
1:是' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post', @level2type=N'COLUMN',@level2name=N'IsBreakLine'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發布開始日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post', @level2type=N'COLUMN',@level2name=N'StartDateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發布結束日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post', @level2type=N'COLUMN',@level2name=N'EndDateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否啟用
0:不啟用
1:啟用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post', @level2type=N'COLUMN',@level2name=N'IsEnable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'關於我們文章' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'自動編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_attach', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'關於我們文章Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_attach', @level2type=N'COLUMN',@level2name=N'CommonPostId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_attach', @level2type=N'COLUMN',@level2name=N'FileName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案類型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_attach', @level2type=N'COLUMN',@level2name=N'FileType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案內容' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_attach', @level2type=N'COLUMN',@level2name=N'FileContent'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案雜湊值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_attach', @level2type=N'COLUMN',@level2name=N'FileHash'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案大小' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_attach', @level2type=N'COLUMN',@level2name=N'FileSize'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案說明' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_attach', @level2type=N'COLUMN',@level2name=N'FileDesc'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_attach', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_attach', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_attach', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_attach', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'關於我們文章附件' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_attach'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'自動編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_pic', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'關於我們文章Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_pic', @level2type=N'COLUMN',@level2name=N'CommonPostId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_pic', @level2type=N'COLUMN',@level2name=N'FileName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔類型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_pic', @level2type=N'COLUMN',@level2name=N'FileType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔內容' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_pic', @level2type=N'COLUMN',@level2name=N'FileContent'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔雜湊值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_pic', @level2type=N'COLUMN',@level2name=N'FileHash'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔大小' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_pic', @level2type=N'COLUMN',@level2name=N'FileSize'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔寬度' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_pic', @level2type=N'COLUMN',@level2name=N'ImageWidth'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔高度' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_pic', @level2type=N'COLUMN',@level2name=N'ImageHeight'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔說明' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_pic', @level2type=N'COLUMN',@level2name=N'FileDesc'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_pic', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_pic', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_pic', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_pic', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'關於我們文章引用圖檔' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'common_post_pic'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'event_type', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'警訊種類.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'event_type', @level2type=N'COLUMN',@level2name=N'AlertCode'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'代號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'event_type', @level2type=N'COLUMN',@level2name=N'Code'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'event_type', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'XSD (STIX驗證檔)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'event_type', @level2type=N'COLUMN',@level2name=N'StixXsd'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否啟用
0:不啟用
1:啟用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'event_type', @level2type=N'COLUMN',@level2name=N'IsEnable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'顯示排序號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'event_type', @level2type=N'COLUMN',@level2name=N'Sort'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'event_type', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'event_type', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'event_type', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'event_type', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事件類型資料表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'event_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'forgot_temp', @level2type=N'COLUMN',@level2name=N'MemberId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'過期時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'forgot_temp', @level2type=N'COLUMN',@level2name=N'ExpireTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'驗證碼' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'forgot_temp', @level2type=N'COLUMN',@level2name=N'Code'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'忘記密碼申請對應暫存表單' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'forgot_temp'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'form', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系統.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'form', @level2type=N'COLUMN',@level2name=N'SubsystemId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'表單編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'form', @level2type=N'COLUMN',@level2name=N'Code'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'表單名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'form', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'控制器名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'form', @level2type=N'COLUMN',@level2name=N'ControllerName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'方法名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'form', @level2type=N'COLUMN',@level2name=N'ActionName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否開新視窗' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'form', @level2type=N'COLUMN',@level2name=N'IsExternalLink'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否啟用
0:不啟用
1:啟用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'form', @level2type=N'COLUMN',@level2name=N'IsEnable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否顯示
0:不顯示
1:顯示' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'form', @level2type=N'COLUMN',@level2name=N'IsShow'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'顯示排序號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'form', @level2type=N'COLUMN',@level2name=N'Sort'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'form', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'form', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'form', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'form', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'表單資料表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'form'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'醫事機構代號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'healthcare', @level2type=N'COLUMN',@level2name=N'Code'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'醫事機構名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'healthcare', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'醫事機構' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'healthcare'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'警訊編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'PostId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'情報種類' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'StixTitle'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發送編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'IncidentId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事件主旨/情報名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'IncidentTitle'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發現時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'IncidentDiscoveryTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發送時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'IncidentReportedTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'解決時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'IncidentClosedTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'內容說明/事件描述' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'Description'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事件類型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'Category'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發布單位' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'ReporterName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'聯絡資訊 (姓名)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'ResponderPartyName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'聯絡資訊 (電話)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'ResponderContactNumbers'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'聯絡資訊 (電子郵件)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'ResponderElectronicAddressIdentifiers'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'影響等級' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'ImpactQualification'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建議措施/解決辦法' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'CoaDescription'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保密程度' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'Confidence'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'參考資料' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'Reference'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'附件' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'ObservableAttach'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'受害IP' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'ObservableIpAddress'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'C&C資訊(IP)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'SocketIpAddress'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'C&C資訊(通訊埠)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'SocketPort'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'C&C資訊(通訊埠協定)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'SocketProtocol'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'中繼站資訊(IP)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'CustomIpAddress'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'中繼站資訊(通訊埠)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'CustomPort'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'中繼站資訊(通訊埠協定)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'CustomProtocol'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'目的端資訊(IP)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'DestinationIpAddress'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'目的端資訊(通訊埠)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'DestinationPort'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'目的端資訊(通訊埠協定)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'DestinationProtocol'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'手法研判' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'LeveragedDescription'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'影響平台' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'AffectedSoftwareDescription'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'來源IP' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'ResourcesSourceIpAddress'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'目標資訊(通訊埠)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'ResourcesDestinationPort'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'目標資訊(通訊埠協定)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'ResourcesDestinationProtocol'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'目標對象' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'ResourcesDestination'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'掃描資訊(掃描引擎)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'ScanEngine'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'掃描資訊(掃描版本)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'ScanVersion'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'掃描資訊(掃描結果)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'ScanResult'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'來源編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'RelatedIncidentId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'來源時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'RelatedIncidentTimestamp'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否啟用
0:不啟用
1:啟用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'IsEnable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'情資交換事件資料' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'自動編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange_attach', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'情資交換事件資料Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange_attach', @level2type=N'COLUMN',@level2name=N'InformationExchangeId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange_attach', @level2type=N'COLUMN',@level2name=N'FileName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案類型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange_attach', @level2type=N'COLUMN',@level2name=N'FileType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案內容' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange_attach', @level2type=N'COLUMN',@level2name=N'FileContent'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案雜湊值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange_attach', @level2type=N'COLUMN',@level2name=N'FileHash'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案大小' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange_attach', @level2type=N'COLUMN',@level2name=N'FileSize'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案說明' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange_attach', @level2type=N'COLUMN',@level2name=N'FileDesc'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange_attach', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange_attach', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange_attach', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_exchange_attach', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_source', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'情資編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_source', @level2type=N'COLUMN',@level2name=N'Code'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'情資名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_source', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否啟用
0:不啟用
1:啟用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_source', @level2type=N'COLUMN',@level2name=N'IsEnable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'顯示排序號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_source', @level2type=N'COLUMN',@level2name=N'Sort'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_source', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_source', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_source', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_source', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'情資資料來源檔' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'information_source'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'自動編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links', @level2type=N'COLUMN',@level2name=N'PostDateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'標題' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links', @level2type=N'COLUMN',@level2name=N'Title'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'來源名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links', @level2type=N'COLUMN',@level2name=N'SourceName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'來源網址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links', @level2type=N'COLUMN',@level2name=N'SourceLink'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'內容是否自動分行
0:否
1:是' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links', @level2type=N'COLUMN',@level2name=N'IsBreakLine'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發布開始日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links', @level2type=N'COLUMN',@level2name=N'StartDateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發布結束日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links', @level2type=N'COLUMN',@level2name=N'EndDateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否啟用
0:不啟用
1:啟用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links', @level2type=N'COLUMN',@level2name=N'IsEnable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'相關連結' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'自動編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links_pic', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links_pic', @level2type=N'COLUMN',@level2name=N'FileName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔類型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links_pic', @level2type=N'COLUMN',@level2name=N'FileType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔內容' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links_pic', @level2type=N'COLUMN',@level2name=N'FileContent'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔雜湊值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links_pic', @level2type=N'COLUMN',@level2name=N'FileHash'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔大小' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links_pic', @level2type=N'COLUMN',@level2name=N'FileSize'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔寬度' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links_pic', @level2type=N'COLUMN',@level2name=N'ImageWidth'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔高度' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links_pic', @level2type=N'COLUMN',@level2name=N'ImageHeight'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔說明' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links_pic', @level2type=N'COLUMN',@level2name=N'FileDesc'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links_pic', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links_pic', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links_pic', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links_pic', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'最新消息文章引用圖檔' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'links_pic'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'單位機關基本資料.單位編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member', @level2type=N'COLUMN',@level2name=N'OrgId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'使用者登入帳號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member', @level2type=N'COLUMN',@level2name=N'Account'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'使用者顯示名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'電子郵件信箱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member', @level2type=N'COLUMN',@level2name=N'Email'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'備用電子郵件信箱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member', @level2type=N'COLUMN',@level2name=N'SpareEmail'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'行動電話' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member', @level2type=N'COLUMN',@level2name=N'MobilePhone'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'市內電話' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member', @level2type=N'COLUMN',@level2name=N'CityPhone'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'傳真號碼' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member', @level2type=N'COLUMN',@level2name=N'FaxPhone'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'聯絡地址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member', @level2type=N'COLUMN',@level2name=N'Address'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'所屬單位或部門' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member', @level2type=N'COLUMN',@level2name=N'Department'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'職稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member', @level2type=N'COLUMN',@level2name=N'Title'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否啟用
0:不啟用
1:啟用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member', @level2type=N'COLUMN',@level2name=N'IsEnable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'可使用開始時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member', @level2type=N'COLUMN',@level2name=N'EnableTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'使用者資料表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member_history', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member_history', @level2type=N'COLUMN',@level2name=N'MemberId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'使用者密碼' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member_history', @level2type=N'COLUMN',@level2name=N'Password'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Salt' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member_history', @level2type=N'COLUMN',@level2name=N'Salt'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'錯誤次數' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member_history', @level2type=N'COLUMN',@level2name=N'ErrorCount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member_history', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member_history', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member_history', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member_history', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'使用者密碼歷程資料表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member_history'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member_role', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member_role', @level2type=N'COLUMN',@level2name=N'MemberId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'角色.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member_role', @level2type=N'COLUMN',@level2name=N'RoleId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否啟用
0:不啟用
1:啟用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member_role', @level2type=N'COLUMN',@level2name=N'IsEnable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member_role', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member_role', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member_role', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member_role', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'使用者角色對應表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'member_role'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'警訊代碼' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'警訊編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message', @level2type=N'COLUMN',@level2name=N'PostId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'警訊類型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message', @level2type=N'COLUMN',@level2name=N'AlertCode'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事件類型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message', @level2type=N'COLUMN',@level2name=N'EventCode'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'警訊來源' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message', @level2type=N'COLUMN',@level2name=N'SourceCode'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'外部發布編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message', @level2type=N'COLUMN',@level2name=N'ExternalId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'警訊發現日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message', @level2type=N'COLUMN',@level2name=N'FindDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'警訊發佈時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message', @level2type=N'COLUMN',@level2name=N'PostDateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'警訊主旨' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message', @level2type=N'COLUMN',@level2name=N'Subject'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事件描述' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message', @level2type=N'COLUMN',@level2name=N'Description'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建議措施' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message', @level2type=N'COLUMN',@level2name=N'Suggestion'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'參考資料' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message', @level2type=N'COLUMN',@level2name=N'Reference'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'手法研判' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message', @level2type=N'COLUMN',@level2name=N'Determine'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'內容說明' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message', @level2type=N'COLUMN',@level2name=N'Contents'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'影響平台' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message', @level2type=N'COLUMN',@level2name=N'AffectPlatform'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'影響等級' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message', @level2type=N'COLUMN',@level2name=N'ImpactLevel'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'警訊狀態' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message', @level2type=N'COLUMN',@level2name=N'Status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否需回覆' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message', @level2type=N'COLUMN',@level2name=N'IsReply'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否啟用
0:不啟用
1:啟用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message', @level2type=N'COLUMN',@level2name=N'IsEnable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'警訊單' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_group', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'會員群組名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_group', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否啟用
0:不啟用
1:啟用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_group', @level2type=N'COLUMN',@level2name=N'IsEnable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_group', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_group', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_group', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_group', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'警訊會員群組' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_group'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_group_org', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'會員群組編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_group_org', @level2type=N'COLUMN',@level2name=N'MessageGroupId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'單位機關編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_group_org', @level2type=N'COLUMN',@level2name=N'OrgId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_group_org', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_group_org', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_group_org', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_group_org', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'警訊會員群組會員機構' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_group_org'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_post', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'警訊代碼' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_post', @level2type=N'COLUMN',@level2name=N'MessageId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'收件者名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_post', @level2type=N'COLUMN',@level2name=N'PostName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'警訊發布類別 1.群組,2.單位,3.會員,4.其他' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_post', @level2type=N'COLUMN',@level2name=N'PostType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'收件者(群組,單位,會員Id,E-Mail)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_post', @level2type=N'COLUMN',@level2name=N'PostContent'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否啟用
0:不啟用
1:啟用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_post', @level2type=N'COLUMN',@level2name=N'IsEnable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_post', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_post', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_post', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_post', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'警訊發布' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message_post'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'自動編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'公開資訊分類
1:最新消息
2:最新活動' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management', @level2type=N'COLUMN',@level2name=N'PostType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'最新消息類別Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management', @level2type=N'COLUMN',@level2name=N'NewsManagementGroupId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management', @level2type=N'COLUMN',@level2name=N'PostDateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'標題' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management', @level2type=N'COLUMN',@level2name=N'Title'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'來源名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management', @level2type=N'COLUMN',@level2name=N'SourceName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'來源網址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management', @level2type=N'COLUMN',@level2name=N'SourceLink'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'文章型態
1:內容文件
2:外部連結' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management', @level2type=N'COLUMN',@level2name=N'ContentType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'內容' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management', @level2type=N'COLUMN',@level2name=N'Content'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'外部連結' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management', @level2type=N'COLUMN',@level2name=N'ExternalLink'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'內容是否自動分行
0:否
1:是' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management', @level2type=N'COLUMN',@level2name=N'IsBreakLine'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發布開始日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management', @level2type=N'COLUMN',@level2name=N'StartDateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發布結束日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management', @level2type=N'COLUMN',@level2name=N'EndDateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否啟用
0:不啟用
1:啟用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management', @level2type=N'COLUMN',@level2name=N'IsEnable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'狀態' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management', @level2type=N'COLUMN',@level2name=N'Status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'最新消息編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management', @level2type=N'COLUMN',@level2name=N'PostId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'轉入型態
0:
2: 通報轉最新消息' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management', @level2type=N'COLUMN',@level2name=N'TransferInType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'轉入資料來源Id
TransferInType=2
InformationExchange.Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management', @level2type=N'COLUMN',@level2name=N'TransferInId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'轉出型態
0:' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management', @level2type=N'COLUMN',@level2name=N'TransferOutType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'轉出目的資料Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management', @level2type=N'COLUMN',@level2name=N'TransferOutId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'公開資訊文章' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'自動編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_attach', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'最新消息文章Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_attach', @level2type=N'COLUMN',@level2name=N'NewsManagementId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_attach', @level2type=N'COLUMN',@level2name=N'FileName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案類型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_attach', @level2type=N'COLUMN',@level2name=N'FileType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案內容' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_attach', @level2type=N'COLUMN',@level2name=N'FileContent'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案雜湊值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_attach', @level2type=N'COLUMN',@level2name=N'FileHash'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案大小' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_attach', @level2type=N'COLUMN',@level2name=N'FileSize'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案說明' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_attach', @level2type=N'COLUMN',@level2name=N'FileDesc'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_attach', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_attach', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_attach', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_attach', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'最新消息文章附件' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_attach'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'自動編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_group', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'最新消息類別名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_group', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否啟用
0:不啟用
1:啟用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_group', @level2type=N'COLUMN',@level2name=N'IsEnable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_group', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_group', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_group', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_group', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'自動編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_pic', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'最新消息文章Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_pic', @level2type=N'COLUMN',@level2name=N'NewsManagementId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_pic', @level2type=N'COLUMN',@level2name=N'FileName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔類型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_pic', @level2type=N'COLUMN',@level2name=N'FileType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔內容' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_pic', @level2type=N'COLUMN',@level2name=N'FileContent'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔雜湊值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_pic', @level2type=N'COLUMN',@level2name=N'FileHash'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔大小' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_pic', @level2type=N'COLUMN',@level2name=N'FileSize'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔寬度' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_pic', @level2type=N'COLUMN',@level2name=N'ImageWidth'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔高度' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_pic', @level2type=N'COLUMN',@level2name=N'ImageHeight'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'圖檔說明' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_pic', @level2type=N'COLUMN',@level2name=N'FileDesc'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_pic', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_pic', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_pic', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_pic', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'最新消息文章引用圖檔' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'news_management_pic'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'通報單id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'填報時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'ApplyDateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'通報單編號,暫存單：TEMP-醫事機構代碼-yyyy-99999,正式單：醫事機構代碼-yyyy-99999' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'PostId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'通報單位,org.Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'ContactorUnit'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'權責機關(隸屬主管機關),org.Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'MainUnit1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'權責機關(業務主管機關),org.Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'MainUnit2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'通報人,Member.Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'ContactorId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'電話' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'ContactorTel'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'傳真' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'ContactorFax'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'E-MAIL' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'ContactorEmail'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否代其他單位通報,0: 否,1: 是' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsSub'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否代其他單位通報-該單位名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsSubUnitName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故發生時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'EventDateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'受害主機數量-總計' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'HostAmount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'受害主機數量-伺服器總計' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'ServerAmount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'IP位址-外部' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IpExternal'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'IP位址-內部' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IpInternal'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'網際網路位置(Web-url)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'WebUrl'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Windows系列,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsOsOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Linux系列,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsOsOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'其他作業平台,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsOsOpt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'作業系統名稱、版本-其他,IsOsOpt3為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsOsOpt3Other'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'防火牆,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsGuardOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'防毒軟體,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsGuardOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'入侵防禦系統,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsGuardOpt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'其他,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsGuardOpt4'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'已裝置之安全機制-其他,IsGuardOpt4為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsGuardOpt4Other'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'資安監控中心(SOC),0: 無,1: 機關自行建置,2: 委外建置' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'SocOpt'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'資安監控中心(SOC)-委外建置-廠商名稱,SocOpt選擇2,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'SocOptCompany'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'受害系統是否通過資安管理認證(ISMS),0: 否,1: 是' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsIsms'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'資安維護廠商' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'MaintainCompany'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故影響等級-機密性衝擊,0: 無需通報,1: 1級,2: 2級,3: 3級,4: 4級' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'CeffectLevel'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故影響等級-完整性衝擊,0: 無需通報,1: 1級,2: 2級,3: 3級,4: 4級' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IeffectLevel'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故影響等級-可用性衝擊,0: 無需通報,1: 1級,2: 2級,3: 3級,4: 4級' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'AeffectLevel'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故影響等級,機密性、完整性、可用性衝擊最嚴重者,0: 無需通報,1: 1級,2: 2級,3: 3級,4: 4級' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'EffectLevel'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分類
1: 網頁攻擊
2: 非法入侵
3: 阻斷服務
4: 設備異常
5: 其他
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'EventType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'網頁置換,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsEventType1Opt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'惡意留言,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsEventType1Opt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'惡意網頁,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsEventType1Opt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'釣魚網頁,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsEventType1Opt4'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'網頁木馬,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsEventType1Opt5'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'網站個資外洩,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsEventType1Opt6'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系統遭入侵,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsEventType2Opt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'植入惡意程式,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsEventType2Opt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異常連線,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsEventType2Opt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發送垃圾郵件,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsEventType2Opt4'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'資料外洩,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsEventType2Opt5'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'服務中斷,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsEventType3Opt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'效能降低,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsEventType3Opt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'設備毀損,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsEventType4Opt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'電力異常,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsEventType4Opt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'網路服務中斷,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsEventType4Opt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'設備遺失,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsEventType4Opt4'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分類-其他 EventType選擇5，此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'EventType5Other'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故說明' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'EventRemark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否影響其他政府機關(構)或重要民生設施運作
0: 否
1: 是
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsAffectOthers'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'此事故通報來源
1: 自行發現
2: 警訊通知
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'EventSource'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'此事故通報來源-警訊發布編號,EventSource選擇2,此欄位必填 ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'EventSourceNo'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'已保存遭受害主機事件紀錄檔,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1LogOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'已保存防火牆紀錄,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1LogOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'已保存惡意程式樣本,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1LogOpt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'其他保留資料或處罝說明【如未保存資料亦請說明】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1LogOpt4'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-已保存遭受害主機事件紀錄檔,1: 1個月,2: 1-6個月,3: 6個月以上,4: 其他,IsRes1LogOpt1為1，此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1LogOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-已保存遭受害主機事件紀錄檔-其他,Res1LogOpt1選擇4,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1LogOpt1Other'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-已保存防火牆紀錄,1: 1個月,2: 1-6個月,3: 6個月以上,4: 其他,IsRes1LogOpt2為1，此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1LogOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-已保存防火牆紀錄-其他,Res1LogOpt2選擇4,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1LogOpt2Other'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-已保存惡意程式樣本-個數,IsRes1LogOpt3為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1LogOpt3Amount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-其他保留資料或處罝說明-說明,IsRes1LogOpt4為1，此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1LogOpt4Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發現異常連線IP【請列出異常 IP 與異常連線原因，如：存取後台管理頁面】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1EvaOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發現異常帳號【請列出帳號並說明權限】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1EvaOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'清查網頁目錄內容，網站內存在未授權之程式/檔案【請說明程式名稱或路徑、檔名】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1EvaOpt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發現帳號異常登入 /登出紀錄【請提供異常帳號與判別準則】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1EvaOpt4'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'網站資料庫內容遭竄改,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1EvaOpt5'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'外洩資料包含機敏性資料,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1EvaOpt6'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'外洩資料包含非機敏性,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1EvaOpt7'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'影響評估說明補充,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1EvaOpt8'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-發現異常連線IP-說明,IsRes1EvaOpt1為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1EvaOpt1Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-發現異常帳號-說明,IsRes1EvaOpt2為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1EvaOpt2Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-清查網頁目錄內容，網站內存在未授權之程式/檔案-說明,IsRes1EvaOpt3為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1EvaOpt3Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-發現帳號異常登入/登出紀錄-說明,IsRes1EvaOpt4為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1EvaOpt4Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-外洩資料包含機敏性資料-筆數,IsRes1EvaOpt6為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1EvaOpt6Amount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-外洩資料包含機敏性資料-性質,1: 個人敏感性資料,2: 機密性資料,3: 其他【請說明外洩資料類型】' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1EvaOpt6Type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-外洩資料包含機敏性資料-性質-其他,Res1EvaOpt6Type選擇3,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1EvaOpt6TypeOpt3Other'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-外洩資料包含非機敏性-筆數,IsRes1EvaOpt7為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1EvaOpt7Amount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-外洩資料包含非機敏性-請說明內容【請簡述資料欄位】,IsRes1EvaOpt7為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1EvaOpt7Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-影響評估說明補充【請填寫補充說明】,IsRes1EvaOpt8為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1EvaOpt8Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'移除惡意網頁/留言,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1DoOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'移除惡意程式【請說明名稱或路徑、檔名】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1DoOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'將異常連線IP列入阻擋清單【請說明設定阻檔之資訊備與之阻檔之IP 】【請說明停用 /刪除之帳號】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1DoOpt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'停用/刪除異常帳號【請說明停用 /刪除之帳號】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1DoOpt4'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'移除未授權存在之程式/檔案,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1DoOpt5'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'移除網站外洩資料,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1DoOpt6'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'通知事故相關當事人，並依內部資安通報作業向上級呈報,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1DoOpt7'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'暫時中斷受害主機網路連線行為至主機無安全性疑慮,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1DoOpt8'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'已向搜尋引擎提供者申請移除庫存頁面,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1DoOpt9'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'修改網站程式碼，並檢視其他網站程式碼，完成日期,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1DoOpt10'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'重新建置作業系統與環境，完成日期,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1DoOpt11'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'應變措施補充說明【請填寫補充說明】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1DoOpt12'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'封鎖、根除及復原-移除惡意網頁/留言-筆數,IsRes1DoOpt1為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1DoOpt1Amount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'封鎖、根除及復原-移除惡意程式-說明,IsRes1DoOpt2為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1DoOpt2Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'封鎖、根除及復原-將異常連線IP列入阻擋清單-說明,IsRes1DoOpt3為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1DoOpt3Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'封鎖、根除及復原-停用/刪除異常帳號-說明,IsRes1DoOpt4為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1DoOpt4Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'封鎖、根除及復原-移除未授權存在之程式/檔案-個數,IsRes1DoOpt5為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1DoOpt5Amount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Google,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1DoOpt9EngineOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Yahoo,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1DoOpt9EngineOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Yam,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1DoOpt9EngineOpt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Bing,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1DoOpt9EngineOpt4'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Hinet,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1DoOpt9EngineOpt5'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'其他搜尋引擎提供者,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes1DoOpt9EngineOpt6'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'封鎖、根除及復原-已向搜尋引擎提供者申請移除庫存頁面-其他搜尋引擎提供者,IsRes1DoOpt9EngineOpt6為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1DoOpt9EngineOpt6Other'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'封鎖、根除及復原-修改網站程式碼，並檢視其他網站程式碼，完成日期-完成日期,IsRes1DoOpt10為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1DoOpt10Date'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'封鎖、根除及復原-重新建置作業系統與環境，完成日期-完成日期,IsRes1DoOpt11為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1DoOpt11Date'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'封鎖、根除及復原-應變措施補充說明-說明,IsRes1DoOpt12為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res1DoOpt12Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'已保存遭受害主機事件紀錄檔,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes2LogOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'已保存防火牆紀錄,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes2LogOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'已保存惡意程式樣本,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes2LogOpt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'其他保留資料或處罝說明【如未保存資料亦請說明】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes2LogOpt4'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-已保存遭受害主機事件紀錄檔,1: 1個月,2: 1-6個月,3: 6個月以上,4: 其他,IsRes2LogOpt1為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res2LogOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-已保存遭受害主機事件紀錄檔-其他,Res2LogOpt1選擇4,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res2LogOpt1Other'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-已保存防火牆紀錄,1: 1個月,2: 1-6個月,3: 6個月以上,4: 其他,IsRes2LogOpt1為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res2LogOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-已保存防火牆紀錄-其他,保留資料-已保存防火牆紀錄-其他' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res2LogOpt2Other'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-已保存惡意程式樣本-個數,IsRes2LogOpt3為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res2LogOpt3Amount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-其他保留資料或處罝說明-說明,IsRes2LogOpt4為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res2LogOpt4Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發現設備連線至可疑IP/Domain Name【請說明連線資訊設備用途與目的 IP/Domain】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes2EvaOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發現受害主機機帳號異常入/登出紀錄【請提供異常帳號與判別準則】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes2EvaOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發現異常帳號【請列出帳號並說明帳號權限】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes2EvaOpt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發現資料外洩情況(如：異常打包資料)【請說明外洩資料內容】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes2EvaOpt4'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'影響評估說明補充【請填寫補充說明】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes2EvaOpt5'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-發現設備連線至可疑IP/Domain Name-說明,IsRes2EvaOpt1為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res2EvaOpt1Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-發現受害主機機帳號異常入/登出紀錄-說明,IsRes2EvaOpt2為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res2EvaOpt2Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-發現異常帳號-說明,IsRes2EvaOpt3為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res2EvaOpt3Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-發現資料外洩情況-說明,IsRes2EvaOpt4為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res2EvaOpt4Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-影響評估說明補充-說明,IsRes2EvaOpt5為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res2EvaOpt5Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'移除惡意程式【請說明名稱或路徑、檔名】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes2DoOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'將可疑IP/Domain Name列入阻擋清單【請說明設定阻檔之資訊設備與阻擋之IP/Domain】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes2DoOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'停用/刪除異常帳號【請說明停用 /刪除之帳號】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes2DoOpt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'中斷受害主機網路連線行為至無安全性疑慮,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes2DoOpt4'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'重新建置作業系統與作業環境,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes2DoOpt5'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'惡意程式樣本送交防毒軟體廠商,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes2DoOpt6'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'應變措施補充說明【請填寫補充說明】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes2DoOpt7'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'封鎖、根除及復原-移除惡意程式-說明,IsRes2DoOpt1為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res2DoOpt1Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'封鎖、根除及復原-將可疑IP/Domain Name列入阻擋清單-說明,IsRes2DoOpt2為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res2DoOpt2Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'封鎖、根除及復原-停用/刪除異常帳號-說明,IsRes2DoOpt3為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res2DoOpt3Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'封鎖、根除及復原-惡意程式樣本送交防毒軟體廠商-個數,IsRes2DoOpt6為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res2DoOpt6Amount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'封鎖、根除及復原-應變措施補充說明-說明,IsRes2DoOpt7為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res2DoOpt7Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'已保存遭受害主機事件紀錄檔,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes3LogOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'已保存防火牆紀錄,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes3LogOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'已保存惡意程式樣本,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes3LogOpt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'其他保留資料或處罝說明【如未保存資料亦請說明】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes3LogOpt4'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-已保存遭受害主機事件紀錄檔,1: 1個月,2: 1-6個月,3: 6個月以上,4: 其他,IsRes3LogOpt1為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res3LogOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-已保存遭受害主機事件紀錄檔-其他,Res3LogOpt1選擇4,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res3LogOpt1Other'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-已保存防火牆紀錄,1: 1個月,2: 1-6個月,3: 6個月以上,4: 其他,IsRes3LogOpt2為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res3LogOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-已保存防火牆紀錄-其他,Res3LogOpt2選擇4,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res3LogOpt2Other'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-已保存惡意程式樣本-個數,IsRes3LogOpt3為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res3LogOpt3Amount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-其他保留資料或處罝說明-說明,IsRes3LogOpt4為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res3LogOpt4Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'攻擊來源 IP 數量,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes3EvaOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'確認遭攻擊主機用途 【請說明主機用途】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes3EvaOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'影響評估補充說明,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes3EvaOpt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-攻擊來源 IP 數量-個數,IsRes3EvaOpt1為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res3EvaOpt1Amount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-確認遭攻擊主機用途-說明,IsRes3EvaOpt2為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res3EvaOpt2Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-影響評估說明補充-說明,IsRes3EvaOpt3為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res3EvaOpt3Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'阻擋攻擊來源 IP 【請說明設定阻擋之資訊設備與阻擋之 IP範圍/Domain】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes3DoOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'調整網路頻寬,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes3DoOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'聯繫網路服務提供業者(ISP),(請提供ISP業者名稱)，請其協助進行阻擋,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes3DoOpt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'應變措施補充說明【請填寫補充說明】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes3DoOpt4'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'封鎖、根除及復原-阻擋攻擊來源 IP-說明,IsRes3DoOpt1為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res3DoOpt1Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'封鎖、根除及復原-聯繫網路服務提供業者(ISP)-ISP業者名稱,IsRes3DoOpt3為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res3DoOpt3Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'封鎖、根除及復原-應變措施補充說明-說明,IsRes3DoOpt4為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res3DoOpt4Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'其他保留資料或處罝說明【如未保存資料亦請說明】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes4LogOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-其他保留資料或處罝說明-說明,IsRes4LogOpt1為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res4LogOpt1Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'評估設備影響情況,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes4EvaOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'影響評估補充說明,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes4EvaOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-評估設備影響情況,1: 無資料遭損毀,2: 資料損毀，但可由備份檔案還原,3: 資料損毀，且資料無法復原,4: 資料損毀，僅可復原部分,IsRes4EvaOpt1為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res4EvaOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-評估設備影響情況-資料損毀，僅可復原部分-百分比,Res4EvaOpt1選擇4,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res4EvaOpt1Amount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-影響評估補充說明-說明,IsRes4EvaOpt2為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res4EvaOpt2Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'毀損資料 /系統已恢復正常運作,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes4DoOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'完成系統復原測試,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes4DoOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'應變措施補充說明【請填寫補充說明】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes4DoOpt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'封鎖、根除及復原-應變措施補充說明-說明,IsRes4DoOpt3為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res4DoOpt3Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'已保存遭受害主機事件紀錄檔,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes5LogOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'已保存防火牆紀錄,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes5LogOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'已保存惡意程式樣本,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes5LogOpt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'其他保留資料或處罝說明【如未保存資料亦請說明】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes5LogOpt4'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-已保存遭受害主機事件紀錄檔,1: 1個月,2: 1-6個月,3: 6個月以上,4: 其他,IsRes5LogOpt1為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res5LogOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-已保存遭受害主機事件紀錄檔-其他,Res5LogOpt1選擇4,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res5LogOpt1Other'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-已保存防火牆紀錄,1: 1個月,2: 1-6個月,3: 6個月以上,4: 其他,IsRes5LogOpt2為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res5LogOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-已保存防火牆紀錄-其他,Res5LogOpt2選擇4,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res5LogOpt2Other'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-已保存惡意程式樣本-個數,IsRes5LogOpt3為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res5LogOpt3Amount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保留資料-其他保留資料或處罝說明-說明,IsRes5LogOpt4為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res5LogOpt4Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發現設備連線至可疑IP/Domain Name【請說明連線資訊設備用途與目的 IP/Domain】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes5EvaOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發現受害主機機帳號異常入/登出紀錄【請提供異常帳號與判別準則】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes5EvaOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發現異常帳號【請列出帳號並說明帳號權限】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes5EvaOpt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發現資料外洩情況(如：異常打包資料)【請說明外洩資料內容】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes5EvaOpt4'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'影響評估說明補充【請填寫補充說明】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes5EvaOpt5'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-發現設備連線至可疑IP/Domain Name-說明,IsRes5EvaOpt1為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res5EvaOpt1Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-發現受害主機機帳號異常入/登出紀錄-說明,IsRes5EvaOpt2為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res5EvaOpt2Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-發現異常帳號-說明,IsRes5EvaOpt3為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res5EvaOpt3Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-發現資料外洩情況-說明,IsRes5EvaOpt4為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res5EvaOpt4Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故分析與影響評估-影響評估說明補充-說明,IsRes5EvaOpt5為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res5EvaOpt5Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'移除惡意程式【請說明名稱或路徑、檔名】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes5DoOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'將可疑IP/Domain Name列入阻擋清單【請說明設定阻檔之資訊設備與阻擋之IP/Domain】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes5DoOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'停用/刪除異常帳號【請說明停用 /刪除之帳號】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes5DoOpt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'中斷受害主機網路連線行為至無安全性疑慮,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes5DoOpt4'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'重新建置作業系統與作業環境，完成日期,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes5DoOpt5'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'惡意程式樣本送交防毒軟體廠商,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes5DoOpt6'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'應變措施補充說明【請填寫補充說明】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsRes5DoOpt7'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'封鎖、根除及復原-移除惡意程式-說明,IsRes5DoOpt2為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res5DoOpt1Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'封鎖、根除及復原-將可疑IP/Domain Name列入阻擋清單-說明,IsRes5DoOpt2為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res5DoOpt2Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'封鎖、根除及復原-停用/刪除異常帳號-說明,IsRes5DoOpt3為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res5DoOpt3Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'封鎖、根除及復原-重新建置作業系統與作業環境，完成日期-完成日期,IsRes5DoOpt5為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res5DoOpt5Date'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'封鎖、根除及復原-惡意程式樣本送交防毒軟體廠商-個數,IsRes5DoOpt6為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res5DoOpt6Amount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'封鎖、根除及復原-應變措施補充說明-說明,IsRes5DoOpt7為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Res5DoOpt7Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否需要支援,0: 否 (免填期望支援內容),1: 是 (請續填期望支援內容)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsNeedSupport'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'期望支援內容,IsNeedSupport為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'NeedSupportRemark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故發生原因,1: 作業系統漏洞,2: 弱密碼,3: 應用程式漏洞,4: 網站設計不當,5: 人為疏失,6: 其他' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Finish1Reason'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故發生原因-其他,Finish1Reason選擇6,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Finish1ReasonOther'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'變更透過受害主機登入應用系統之密碼 (如：使用受害主機登入之網域帳號密碼、公務系統帳號密碼、郵件帳號密碼等),0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish1DoSysOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'變更受害主機中所有帳號之密碼 (含本機管理者),0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish1DoSysOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新受害主機系統與所有應用程式至最版本 (包含網站編輯管理程式，如：FrontPage)【請說明主要更新之程式名稱】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish1DoSysOpt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'關閉網路芳鄰功能,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish1DoSysOpt4'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'設定robots.txt檔，控制網站可被搜尋頁面,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish1DoSysOpt5'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'已針對所有需要特殊存取權限之網頁加強身分驗證機制【請說明制名稱或類別】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish1DoSysOpt6'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'限制網站主機上傳之附件檔案類型【請說明附檔名】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish1DoSysOpt7'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'限制網頁存取資料庫的使用權，對於讀取資料庫的帳戶身分及權限加以管制,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish1DoSysOpt8'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'限制連線資料庫之主機 IP,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish1DoSysOpt9'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'關閉 WebDAV(Web Distribution Authoring and Versioning),0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish1DoSysOpt10'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'I.補強系統/程式安全設定-更新受害主機系統與所有應用程式至最版本-說明,IsFinish1DoSysOpt3為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Finish1DoSysOpt3Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'I.補強系統/程式安全設定-已針對所有需要特殊存取權限之網頁加強身分驗證機制-說明,IsFinish1DoSysOpt6為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Finish1DoSysOpt6Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'I.補強系統/程式安全設定-限制網站主機上傳之附件檔案類型-說明,IsFinish1DoSysOpt7為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Finish1DoSysOpt7Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'重新檢視機關網路架構適切性' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish1DoEduOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'機關內部全面性安全檢測' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish1DoEduOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'加強內部同仁資安教育訓練' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish1DoEduOpt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'修正內部資安防護計畫' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish1DoEduOpt4'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故發生原因,1: 社交工程,2: 作業系統漏洞,3: 弱密碼,4: 應用程式漏洞,5: 網站設計不當,6: 其他' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Finish2Reason'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故發生原因-其他,Finish2Reason選擇6,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Finish2ReasonOther'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故發生原因-事故調查情況說明【請說明事故調查情況】' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Finish2ReasonRemark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'已更新受害主機系統與所有應用程式至最版本【請說明主要之程式名稱】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish2DoSysOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'變更透過受害主機登入應用系統之密碼 (如：使用受害主機登入之網域帳號密碼、公務系統帳號密碼、郵件帳號密碼等),0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish2DoSysOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'變更受害主機中所有帳號之密碼 (含本機管理者),0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish2DoSysOpt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'關閉郵件伺服器 Open Relay功能,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish2DoSysOpt4'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'關閉網路芳鄰功能,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish2DoSysOpt5'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'I.補強系統/程式安全設定-已更新受害主機系統與所有應用程式至最版本-說明,IsFinish2DoSysOpt1為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Finish2DoSysOpt1Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'重新檢視機關網路架構適切性' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish2DoEduOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'機關內部全面性安全檢測' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish2DoEduOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'加強內部同仁資安教育訓練' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish2DoEduOpt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'修正內部資安防護計畫' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish2DoEduOpt4'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'限制同時間單一 IP 連線,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish3DoSysOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'DNS主機停用外部遞迴查詢,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish3DoSysOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'移除主機 /伺服器不必要務功能【請說明服務功能名稱】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish3DoSysOpt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新受害主機系統與所有應用程式至最版本【請說明要之程式名稱】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish3DoSysOpt4'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'I.補強系統/程式安全設定-移除主機 /伺服器不必要務功能-說明,sFinish3DoSysOpt3為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Finish3DoSysOpt3Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'I.補強系統/程式安全設定-更新受害主機系統與所有應用程式至最版本-說明,IsFinish3DoSysOpt4為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Finish3DoSysOpt4Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'重新檢視機關網路架構適切性' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish3DoEduOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'修正內部資安防護計畫' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish3DoEduOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故發生原因,1: 設定錯誤,2: 設備毀損,3: 系統遭入侵,4: 電力供應異常,5: 其他' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Finish4Reason'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故發生原因-其他,Finish4Reason選擇5,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Finish4ReasonOther'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故發生原因-事故調查情況說明【請說明事故調查情況】' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Finish4ReasonRemark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檢視資訊設備使用年限,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish4DoSysOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'重新檢視機關網路架構適切性' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish4DoEduOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'機關內部全面性安全檢測' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish4DoEduOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'加強內部同仁資安教育訓練' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish4DoEduOpt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'修正內部資安防護計畫' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish4DoEduOpt4'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故發生原因,1: 社交工程,2: 作業系統漏洞,3: 弱密碼,4: 應用程式漏洞,5: 網站設計不當,6: 人為疏失,7: 設定錯誤,8: 設備毀損,9: 系統遭入侵,10: 電力供應異常,11: 其他' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Finish5Reason'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故發生原因-其他,Finish5Reason選擇11,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Finish5ReasonOther'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事故發生原因-事故調查情況說明【請說明事故調查情況】' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Finish5ReasonRemark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'已更新受害主機系統與所有應用程式至最新版本【請說明主要之程式名稱】,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish5DoSysOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'變更透過受害主機登入應用系統之密碼 (如：使用受害主機登入之網域帳號密碼、公務系統帳號密碼、郵件帳號密碼等),0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish5DoSysOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'變更受害主機中所有帳號之密碼 (含本機管理者),0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish5DoSysOpt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'關閉網路芳鄰功能,0: 未選,1: 選擇' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish5DoSysOpt4'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'I.補強系統/程式安全設定-已更新受害主機系統與所有應用程式至最新版本-說明,IsFinish5DoSysOpt1為1,此欄位必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Finish5DoSysOpt1Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'重新檢視機關網路架構適切性' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish5DoEduOpt1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'機關內部全面性安全檢測' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish5DoEduOpt2'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'加強內部同仁資安教育訓練' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish5DoEduOpt3'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'修正內部資安防護計畫' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'IsFinish5DoEduOpt4'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'其他相關安全處置【請填寫其他安全處置】' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'FinishDoOther'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'通報單狀態,1: 編輯中,2: 通報審核中,3: 處理中,4: 通報結案審核中,5: 已結案,6: 已銷案' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'Status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'已解決時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'FinishDateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'通報審查時間,H-ISAC通報審核者完成通報審查時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'ExamineDateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'通報結案時間,H-ISAC通報審核者完成通報審查時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'RealFinishDateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'警訊單ID,Message.Id (警訊轉通報)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'MessageId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'警訊單編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'MessagePostId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'通報單' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'notification'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'單位編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'單位名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'單位代碼' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org', @level2type=N'COLUMN',@level2name=N'Code'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'單位類別
0:系統
1:HISAC
2:權責單位
3:會員機構' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org', @level2type=N'COLUMN',@level2name=N'OrgType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'權責單位類別
0:非權責單位
1:地方主管機關
2:權責主管單位' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org', @level2type=N'COLUMN',@level2name=N'AuthType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'縣市' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org', @level2type=N'COLUMN',@level2name=N'City'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'鄉鎮區' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org', @level2type=N'COLUMN',@level2name=N'Town'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'單位地址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org', @level2type=N'COLUMN',@level2name=N'Address'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否啟用
0:不啟用
1:啟用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org', @level2type=N'COLUMN',@level2name=N'IsEnable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'單位機關基本資料' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'單位編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org_sign', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'警訊回覆是否需經權責單位審核' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org_sign', @level2type=N'COLUMN',@level2name=N'WarningIsExamine'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事件通報是否需經權責單位審核' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org_sign', @level2type=N'COLUMN',@level2name=N'NotificationIsExamine'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事件通報結案是否需經權責單位審核' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org_sign', @level2type=N'COLUMN',@level2name=N'NotificationClosingIsExamine'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org_sign', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org_sign', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org_sign', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org_sign', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'表格名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'process_log', @level2type=N'COLUMN',@level2name=N'TableName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'問題Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'qa_management', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'常見問題類別Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'qa_management', @level2type=N'COLUMN',@level2name=N'QAManagementGroupId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'問題名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'qa_management', @level2type=N'COLUMN',@level2name=N'QName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'答案名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'qa_management', @level2type=N'COLUMN',@level2name=N'AName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否啟用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'qa_management', @level2type=N'COLUMN',@level2name=N'IsEnable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立者Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'qa_management', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'qa_management', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'修改者Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'qa_management', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'修改時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'qa_management', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'常見問題' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'qa_management'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'類別Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'qa_management_group', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'類別名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'qa_management_group', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否啟用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'qa_management_group', @level2type=N'COLUMN',@level2name=N'IsEnable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立者Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'qa_management_group', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'qa_management_group', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'修改者Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'qa_management_group', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'修改時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'qa_management_group', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'常見問題類別' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'qa_management_group'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'resource_message', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'資源名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'resource_message', @level2type=N'COLUMN',@level2name=N'MessageKey'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'顯示中文' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'resource_message', @level2type=N'COLUMN',@level2name=N'MessageValue'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'resource_message', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'resource_message', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'resource_message', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'resource_message', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系統環境變數' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'resource_message'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'role', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'角色名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'role', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否啟用
0:不啟用
1:啟用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'role', @level2type=N'COLUMN',@level2name=N'IsEnable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'顯示排序號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'role', @level2type=N'COLUMN',@level2name=N'Sort'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'role', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'role', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'role', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'role', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'角色資料表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'role'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'role_form', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'角色.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'role_form', @level2type=N'COLUMN',@level2name=N'RoleId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'表單.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'role_form', @level2type=N'COLUMN',@level2name=N'FormId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'新增權限
0:無權限
1:有權限' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'role_form', @level2type=N'COLUMN',@level2name=N'ActionCreate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動權限
0:無權限
1:有權限' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'role_form', @level2type=N'COLUMN',@level2name=N'ActionUpdate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'刪除權限
0:無權限
1:有權限' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'role_form', @level2type=N'COLUMN',@level2name=N'ActionDelete'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'讀取權限
0:無權限
1:有權限' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'role_form', @level2type=N'COLUMN',@level2name=N'ActionRead'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'簽核權限
0:無權限
1:有權限' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'role_form', @level2type=N'COLUMN',@level2name=N'ActionSign'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'role_form', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'role_form', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'role_form', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'role_form', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'角色表單權限對應表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'role_form'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sso', @level2type=N'COLUMN',@level2name=N'MemberId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'過期時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sso', @level2type=N'COLUMN',@level2name=N'ExpireTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'驗證碼' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sso', @level2type=N'COLUMN',@level2name=N'Code'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'單一簽入' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sso'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'subsystem', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'子系統編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'subsystem', @level2type=N'COLUMN',@level2name=N'Code'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'選單顯示文字' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'subsystem', @level2type=N'COLUMN',@level2name=N'IconStyle'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系統名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'subsystem', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否啟用
0:不啟用
1:啟用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'subsystem', @level2type=N'COLUMN',@level2name=N'IsEnable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否顯示
0:不顯示
1:顯示' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'subsystem', @level2type=N'COLUMN',@level2name=N'IsShow'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'顯示排序號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'subsystem', @level2type=N'COLUMN',@level2name=N'Sort'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'subsystem', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'subsystem', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'subsystem', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'subsystem', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系統資料表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'subsystem'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'system_counter', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'來源位置' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'system_counter', @level2type=N'COLUMN',@level2name=N'Ip'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'system_counter', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'計數器' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'system_counter'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'system_log', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'應用程式名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'system_log', @level2type=N'COLUMN',@level2name=N'AppName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'應用程式執行方法' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'system_log', @level2type=N'COLUMN',@level2name=N'FuncName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'輸入值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'system_log', @level2type=N'COLUMN',@level2name=N'InputValue'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'執行動作' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'system_log', @level2type=N'COLUMN',@level2name=N'ActionName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'執行狀態' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'system_log', @level2type=N'COLUMN',@level2name=N'Status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'來源位置' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'system_log', @level2type=N'COLUMN',@level2name=N'Ip'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'比對碼' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'system_log', @level2type=N'COLUMN',@level2name=N'HashCode'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'使用者登入帳號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'system_log', @level2type=N'COLUMN',@level2name=N'CreateAccount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'system_log', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系統紀錄資料表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'system_log'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ticket_queue', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'表格名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ticket_queue', @level2type=N'COLUMN',@level2name=N'TableName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否為正式表單' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ticket_queue', @level2type=N'COLUMN',@level2name=N'IsApply'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'表單名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ticket_queue', @level2type=N'COLUMN',@level2name=N'Pre'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'表單類別' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ticket_queue', @level2type=N'COLUMN',@level2name=N'Code'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'表單年度' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ticket_queue', @level2type=N'COLUMN',@level2name=N'Year'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'表單流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ticket_queue', @level2type=N'COLUMN',@level2name=N'Number'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'取號器' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ticket_queue'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'自動編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發送編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'IncidentId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'情報名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'IncidentTitle'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發現時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'IncidentDiscoveryTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發送時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'IncidentReportedTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'內容說明' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'Description'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'事件類型代號
enent_type.Code' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'EventTypeCode'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發布單位' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'ReporterName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'聯絡資訊 (姓名)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'ResponderPartyName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'聯絡資訊 (電話)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'ResponderContactNumbers'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'聯絡資訊 (電子郵件)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'ResponderElectronicAddressIdentifiers'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'影響等級
1: 低 (low)
2: 中 (medium)
3: 高 (high)
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'ImpactQualification'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建議措施/解決辦法' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'CoaDescription'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'保密程度' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'Confidence'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'參考資料' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'Reference'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'影響平台' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'AffectedSoftwareDescription'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發布開始日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'StartDateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發布結束日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'EndDateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否啟用
0: 不啟用
1: 啟用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'IsEnable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立使用者
系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動使用者
系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'狀態
1: 編輯中
2: 撤銷中
3: 審核中
4: 已公告
5: 已銷案' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'Status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'軟體弱點漏洞編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'PostId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'轉入型態
0:
4: 通報轉軟體弱點漏洞通告' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'TransferInType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'轉入資料來源Id
TransferInType=4
InformationExchange.Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'TransferInId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'轉出型態
0:' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'TransferOutType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'轉出目的資料Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management', @level2type=N'COLUMN',@level2name=N'TransferOutId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'自動編號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management_attach', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'軟體弱點漏洞通告文章Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management_attach', @level2type=N'COLUMN',@level2name=N'WeaknessManagementId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案名稱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management_attach', @level2type=N'COLUMN',@level2name=N'FileName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案類型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management_attach', @level2type=N'COLUMN',@level2name=N'FileType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案內容' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management_attach', @level2type=N'COLUMN',@level2name=N'FileContent'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案雜湊值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management_attach', @level2type=N'COLUMN',@level2name=N'FileHash'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案大小' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management_attach', @level2type=N'COLUMN',@level2name=N'FileSize'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'檔案說明' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management_attach', @level2type=N'COLUMN',@level2name=N'FileDesc'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management_attach', @level2type=N'COLUMN',@level2name=N'CreateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'建立時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management_attach', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動.使用者.系統流水號' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management_attach', @level2type=N'COLUMN',@level2name=N'ModifyId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'異動時間' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management_attach', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'軟體弱點漏洞通告文章附件' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'weakness_management_attach'
GO
