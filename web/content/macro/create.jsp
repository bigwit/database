<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div>
	<br />
	<span style="font-size: x-large; font-family: monospace; color: #EE3344;">Регистрация</span>
	<br /><br />
	<!-- Объект ViewUser будет доступен а аттрибутах запроса по ключу viewuser -->
	<form:form id="createUserForm" method="POST" action="/database/createuser" commandName="viewuser">
		<table style="width: 400px;">
			<tr>
				<td><form:input id="fname" path="firstName" placeholder="First name" style="width: 185px;" /></td>
				<td><form:input id="lname" path="lastName" placeholder="Last name" style="width: 185px;" /></td>
			</tr>
			<tr>
				<td colspan="2"><form:input id="nikname" path="nikname" placeholder="Login" style="width: 385px;" /></td>
			</tr>
			<tr>
				<td><form:input id="pswd" path="password" placeholder="Password" style="width: 185px;" type="password" /></td>
				<td><form:input id="cpswd" path="confirmPassword" placeholder="Confirm password" style="width: 185px;" type="password" /></td>
			</tr>
			<tr>
				<td colspan="2"><form:input id="telephone" path="phone" placeholder="Phone" style="width: 385px;" /></td>
			</tr>
			<tr>
				<td colspan="2"><form:input id="email" path="email" placeholder="E-Mail" style="width: 385px;" /></td>
			</tr>
			<tr>
				<td colspan="2"><form:input id="country" path="country" placeholder="Country" style="width: 385px;" /></td>
			</tr>
			<tr>
				<td colspan="2"><form:input id="city" path="city" placeholder="City" style="width: 385px;" /></td>
			</tr>
			<tr>
				<td><form:input id="date" path="date" placeholder="Date birth 12-12-2012" style="width: 185px;" /></td>
				<td>
					<b style="margin-left: 10px;" >F</b><form:radiobutton path="sex" value="F" checked="true" />
					<b style="margin-left: 10px;" >M</b><form:radiobutton path="sex" value="M" />
				</td>
			</tr>
		</table>
		<input id="join" type="submit" value="Зарегистрировать" class="button orange" />
	</form:form>
	<br /><br />
</div>