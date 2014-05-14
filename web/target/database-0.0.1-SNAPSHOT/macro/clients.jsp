<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<br>
	<c:if test="${empty onlineUser}">
		Для просмотра путевок необходимо авторизоваться
	</c:if>
	<c:if test="${not empty onlineUser and empty travels}">
		Вы не заказывали путевок. Заказать путевку вы можете в любом из наших <a href="/database/offices">офисов</a>
	</c:if>
	<c:if test="${not empty onlineUser and not empty travels}">
		<!-- тут вся страница по сути -->
		<c:forEach var="tr" items="${travels}"> 
			<table class="offices-table">
				<tr>
					<td>Тур</td>
					<td>Состояние</td>
					<td>Турагент</td>
					<td>Группа</td>
				</tr>
				<tr>
					<td>
						Тур "${tr.getTourName()}"<br>
						"${tr.getTargetPlaceName()}"<br>
						<span class="external-description" data-tour="ext_desc">Подробнее</span>
					</td>
					<td>
						<c:if test="${tr.isPayment()}">
							оплачено<br>
							${tr.getDatePayment().toString()}
						</c:if>
						<c:if test="${not tr.isPayment()}">
							ожидает оплаты
						</c:if>
					</td>
					<td>
						${tr.getFirstNameEmployee()} ${tr.getLastNameEmployee()}<br>
						${tr.getOfficeName()}
					</td>
					<td>
						Взрослых: ${tr.getNumberAdults()}<br>
						Детей: ${tr.getNumberChild()}<br>
					</td>
				</tr>
			</table>
		</c:forEach>
	</c:if>
<br>