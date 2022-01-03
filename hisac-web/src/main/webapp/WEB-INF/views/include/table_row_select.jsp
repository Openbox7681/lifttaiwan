<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<label><span id="imgLoading"><i
		class="fas fa-spinner fa-spin"></i> <s:message code="dataLoading" />
</span>
</label>
<label ng-if="total == 0"><s:message code="tableRowNoRecord" />,
</label>
<label ng-if="total == 1"><s:message code="tableRowOneRecord" />,
</label>
<label ng-if="total > 1"><s:message code="tableRowTotalRecord"
		arguments="{{total}}" />, </label>
<label><s:message code="tableRowDisplayStart" /><select
	size="1" ng-model="maxRows"
	ng-change="maxRowsChange()">
		<option ng-value="5">5</option>
		<option ng-value="10">10</option>
		<option ng-value="25">25</option>
		<option ng-value="50">50</option>
		<option ng-value="100">100</option>
</select> <s:message code="tableRowDisplayEnd" /> </label>
