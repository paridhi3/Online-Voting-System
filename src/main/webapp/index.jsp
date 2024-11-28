<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/jspStyles.css" rel="stylesheet" type="text/css">
    <title>Home</title>
</head>
<body class="welcome-body">
<%-- <jsp:include page="header.jsp"></jsp:include> --%>

<div class="welcome">
	<h1>Welcome to India's Online Voting System!</h1>
	<img src="images/vot.jpg" alt="Welcome" width="300px" height="300px">
    <p>Your vote is your voice! Join us in shaping a better tomorrow by casting your vote securely and conveniently.</p>
    <div class="welcome-links">
        <a href="login.jsp" class="btn">LOGIN</a>
        <a href="register.jsp" class="btn">REGISTER</a>
      </div>
      <small><p> To Login as <span class="small">ADMIN</span>, click <a href="adminLogin.jsp">here</a>.</p></small>
      <div class="githublinks">
        <p>
          View on <a href="https://github.com/paridhi3/Online-Voting-System">GitHub</a>
        </p>
      </div>
</div>

</body>
</html>
