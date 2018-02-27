<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Default</title>
</head>
<body>
	<table>
		<tr>
			<td><a href="CookiesHandler">to cookie test</a></td>
		</tr>
		<tr>
			<td><a href="SessionHandler">to session test</a></td>
		</tr>
		<tr>
			<td><a href="DataBaseAssess">to DataBaseAssess test</a></td>
		</tr>
		<tr>
			<td>
				<form action="UploadServlet" method="post" enctype="multipart/form-data">
					<input type="file" name="file" value="Select">
					<input type="submit" name="upload" value="Upload">
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<form action="DownloadServlet" method="post">
					<input type="text" name="fileName">
					<input type="submit" name="download" value="Download">
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<form action="SendEmailServlet" method="post">
					<table>
						<tr>
							<td style="align:center">Send Email</td>
						</tr>
						<tr>
							<td>From:</td>
							<td><input type="text" name="from"></td>
						</tr>
						<tr>
							<td>User:</td>
							<td><input type="text" name="user"></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td>To:</td>
							<td><input type="text" name="to"></td>
						</tr>
						<tr>
							<td>Subject:</td>
							<td><input type="text" name="subject"></td>
						</tr>
						<tr>
							<td>Message:</td>
							<td><input type="text" name="message"></td>
						</tr>
						<tr>
							<td><input type="submit" name="send" value="Send"></td>
						</tr>
					</table>					
				</form>
			</td>
		</tr>
	</table>
</body>
</html>