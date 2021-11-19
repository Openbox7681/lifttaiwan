<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<table ng-if="total <=0 && returnTotal === true"
	class="table table-striped table-bordered table-hover table_customer table_gray">
	<tfoot>
		<tr>
			<td class="text-center">
				<h4>
					<s:message code="globalNoResultMessage" />
				</h4>
			</td>
		</tr>
	</tfoot>
</table>