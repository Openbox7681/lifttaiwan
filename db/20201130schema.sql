CREATE INDEX IDX_MEMBER_ID ON [hisac].[dbo].[member](Id);
CREATE INDEX IDX_EVENT_TYPE_CODE ON [hisac].[dbo].[event_type](Code);
CREATE INDEX IDX_ACTIVITY_MANAGEMENT_ID ON [hisac].[dbo].[activity_management](Id);
CREATE INDEX IDX_ACTIVITY_MANAGEMENT_M_ID ON [hisac].[dbo].[activity_management](ModifyId);
CREATE INDEX IDX_ACTIVITY_MANAGEMENT_C_ID ON [hisac].[dbo].[activity_management](CreateId);
CREATE INDEX IDX_ANA_MANAGEMENT_ID ON [hisac].[dbo].[ana_management](Id);
CREATE INDEX IDX_ANA_MANAGEMENT_M_ID ON [hisac].[dbo].[ana_management](ModifyId);
CREATE INDEX IDX_ANA_MANAGEMENT_C_ID ON [hisac].[dbo].[ana_management](CreateId);
CREATE INDEX IDX_WEAKNESS_MANAGEMENT_ID ON [hisac].[dbo].[weakness_management](Id);
CREATE INDEX IDX_WEAKNESS_MANAGEMENT_M_ID ON [hisac].[dbo].[weakness_management](ModifyId);
CREATE INDEX IDX_WEAKNESS_MANAGEMENT_C_ID ON [hisac].[dbo].[weakness_management](CreateId);

ALTER TABLE hisac.dbo.org ADD ApiKeyStatus varchar(20) go










