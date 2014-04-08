<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div>
	<br />
	<span style="font-size: x-large; font-family: monospace; color: #EE3344;">Регистрация</span>
	<br /><br />
	<form:form id="createUserForm" method="POST" action="/database/createuser">
		<table style="width: 400px;">
			<tr>
				<td><form:input path="firstName" placeholder="First name" style="width: 185px;" /></td>
				<td><form:input path="lastName" placeholder="Last name" style="width: 185px;" /></td>
			</tr>
			<tr>
				<td colspan="2"><form:input path="nikname" placeholder="Login" style="width: 385px;" /></td>
			</tr>
			<tr>
				<td><form:input path="password" placeholder="Password" style="width: 185px;" /></td>
				<td><form:input path="confirmPassword" placeholder="Confirm password" style="width: 185px;" /></td>
			</tr>
			<tr>
				<td colspan="2"><form:input path="phone" placeholder="Phone" style="width: 385px;" /></td>
			</tr>
			<tr>
				<td colspan="2"><form:input path="email" placeholder="E-Mail" style="width: 385px;" /></td>
			</tr>
			<tr>
				<td colspan="2"><form:input path="country" placeholder="Country" style="width: 385px;" /></td>
			</tr>
			<tr>
				<td colspan="2"><form:input path="city" placeholder="City" style="width: 385px;" /></td>
			</tr>
			<tr>
				<td><form:input path="date" placeholder="Date birth" style="width: 185px;" /></td>
				<td>
					<span style="margin-left: 10px;">F</span><form:radiobutton path="sex" value="F" />
					<span style="margin-left: 10px;">M</span><form:radiobutton path="sex" value="M" />
				</td>
			</tr>
		</table>
	</form:form>
	<br /><br />
</div>