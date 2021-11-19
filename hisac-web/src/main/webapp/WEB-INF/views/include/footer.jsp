<%@ page language="java" pageEncoding="UTF8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<footer id="footer" class="footer">
	<div class="gotop pull-right">
		<a id="gotop" href="#"><i class="fas fa-chevron-circle-up fa-2x"></i></a>
	</div>
	<div class="container">
		<div class="col-xs-10 col-md-3 text-left no_padding">
			<h5>
				<s:message code="globalTotalUser" />
				:
				<c:out value="${totalUser}" />
				<br />
				<s:message code="globalNowUser" />
				:
				<c:out value="${nowUser}" />
			</h5>
		</div>
		<div class="col-xs-12 col-md-8 text-left no_padding">
			<h5>
				&copy; 2018&nbsp;<s:message code="globalCopyright" />
				<br />
				<!--<i class="fas fa-fw fa-hospital"></i>
				<s:message code="globalFooterDirectedBy" />
				:
				<c:out value="${globalFooterDirectedBy}" />
				<span class="hidden-xs">&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;</span><br
					class="visible-xs" /> --><i class="fas fa-fw fa-map-marker"></i>&nbsp;
				<s:message code="globalFooterAddress" />
				: <a
					href="https://www.google.com.tw/maps?q=<c:out value="${globalFooterAddressValue}" />"
					target="_googlemap"><c:out value="${globalFooterAddressText}" /></a><br />
				<i class="fas fa-fw fa-phone"></i>&nbsp;
				<s:message code="globalFooterTel" />
				: <a href="tel:<c:out value="${globalFooterTelValue}" />"
					target="_tel"><c:out value="${globalFooterTelText}" /></a><span
					class="hidden-xs">&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;</span><br
					class="visible-xs" /> <i class="fas fa-fw fa-envelope"></i>&nbsp;
				<s:message code="globalFooterEmail" />
				: <a href="mailto:<c:out value="${globalFooterEmail}" />"><c:out
						value="${globalFooterEmail}" /></a><br />
				<s:message code="globalOptimalWindowSize" />
				<br />				
				<a href="<c:out value="${pageContext.request.contextPath}" />/about" class="footer_about"><s:message code="globalAbout" /></a>
				<span class="hidden-xs">&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;</span>
				<c:if test="${memberAccount==null}">
				<a href="<c:out value="${pageContext.request.contextPath}" />/information_share" class="footer_about">我要分享情資</a>
				</c:if>
				<c:if test="${memberAccount!=null}">
				<a href="<c:out value="${pageContext.request.contextPath}" />/sys/s42" class="footer_about">我要分享情資</a>
				</c:if>
			</h5>
		</div>
	</div>
</footer>
