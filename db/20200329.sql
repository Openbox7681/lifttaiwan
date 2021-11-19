
--新增資料用參數


DECLARE @TotalNum INT, --執行次數
 @Num INT       --目前次數


--設定迴圈參數
SET @TotalNum = 1000 --執行次數
SET @Num =1        --目前次數 

WHILE @Num <= @TotalNum  --當目前次數小於等於執行次數
BEGIN

  INSERT INTO hisac.dbo.ana_management  (
[IncidentId]
      ,[IncidentTitle]
      ,[IncidentDiscoveryTime]
      ,[IncidentReportedTime]
      ,[Description]
      ,[EventTypeCode]
      ,[ReporterName]
      ,[ResponderPartyName]
      ,[ResponderContactNumbers]
      ,[ResponderElectronicAddressIdentifiers]
      ,[ImpactQualification]
      ,[CoaDescription]
      ,[Confidence]
      ,[Reference]
      ,[AffectedSoftwareDescription]
      ,[StartDateTime]
      ,[EndDateTime]
      ,[IsEnable]
      ,[CreateId]
      ,[CreateTime]
      ,[ModifyId]
      ,[ModifyTime]
      ,[Status]
      ,[PostId]
      ,[TransferInType]
      ,[TransferInId]
      ,[TransferOutType]
      ,[TransferOutId]
      ,[IsMedical]
      ,[Sort]

  )SELECT [IncidentId]
      ,[IncidentTitle]
      ,[IncidentDiscoveryTime]
      ,[IncidentReportedTime]
      ,[Description]
      ,[EventTypeCode]
      ,[ReporterName]
      ,[ResponderPartyName]
      ,[ResponderContactNumbers]
      ,[ResponderElectronicAddressIdentifiers]
      ,[ImpactQualification]
      ,[CoaDescription]
      ,[Confidence]
      ,[Reference]
      ,[AffectedSoftwareDescription]
      ,[StartDateTime]
      ,[EndDateTime]
      ,[IsEnable]
      ,[CreateId]
      ,[CreateTime]
      ,[ModifyId]
      ,[ModifyTime]
      ,[Status]
      ,[PostId]
      ,[TransferInType]
      ,[TransferInId]
      ,[TransferOutType]
      ,[TransferOutId]
      ,[IsMedical]
      ,[Sort] FROM [hisac].[dbo].[ana_management] WHERE Id = 10070

SET @Num = @Num + 1
END

  SELECT TOP (1000) [Id]
      ,[IncidentId]
      ,[IncidentTitle]
      ,[IncidentDiscoveryTime]
      ,[IncidentReportedTime]
      ,[Description]
      ,[EventTypeCode]
      ,[ReporterName]
      ,[ResponderPartyName]
      ,[ResponderContactNumbers]
      ,[ResponderElectronicAddressIdentifiers]
      ,[ImpactQualification]
      ,[CoaDescription]
      ,[Confidence]
      ,[Reference]
      ,[AffectedSoftwareDescription]
      ,[StartDateTime]
      ,[EndDateTime]
      ,[IsEnable]
      ,[CreateId]
      ,[CreateTime]
      ,[ModifyId]
      ,[ModifyTime]
      ,[Status]
      ,[PostId]
      ,[TransferInType]
      ,[TransferInId]
      ,[TransferOutType]
      ,[TransferOutId]
      ,[IsMedical]
      ,[Sort]
  FROM [hisac].[dbo].[ana_management]
 
