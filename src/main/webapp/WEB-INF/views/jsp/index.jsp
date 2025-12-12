<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Spring MVC Web App | Modern & Responsive</title>
	
	<!-- Bootstrap 5 CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	<!-- Font Awesome Icons -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&family=Poppins:wght@600;700;800&display=swap" rel="stylesheet">
	
	<!-- Modern Custom Styles -->
	<spring:url value="/resources/core/css/modern-style.css" var="modernCss" />
	<link href="${modernCss}" rel="stylesheet" />

	<!-- Context-aware Home URL -->
	<spring:url value="/" var="homeUrl" />
	<!-- Context-aware Hello URL (POST) -->
	<spring:url value="/hello" var="helloUrl" />
</head>
<body>
	<!-- Navbar -->
	<nav class="navbar navbar-expand-lg navbar-light sticky-top">
		<div class="container-fluid px-4">
			<a class="navbar-brand" href="${homeUrl}">
				<i class="fas fa-rocket"></i> Rushi Technologies
			</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item">
						<a class="nav-link active" href="${homeUrl}">Home</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#about">About</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#contact">Contact</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Hero Section -->
	<div class="hero-section" id="home">
		<div class="hero-content">
			<c:if test="${not empty msg}">
				<h1>${msg}</h1>
			</c:if>
			<c:if test="${empty msg}">
				<h1>Welcome to Our Platform</h1>
			</c:if>

			<!-- Name input form (posts to /hello) -->
			<form id="nameForm" class="d-flex justify-content-center my-3" action="${helloUrl}" method="post">
				<div class="input-group" style="max-width:420px;">
					<input id="userNameInput" name="userName" type="text" class="form-control" placeholder="Enter your name" aria-label="Enter your name" required>
					<button id="submitNameBtn" class="btn btn-submit" type="submit" aria-label="Submit name">
						<i class="fas fa-paper-plane"></i>
						<span class="d-none d-sm-inline">Submit</span>
					</button>
				</div>
			</form>
			<!-- Server-side validation feedback (shown when redirected with ?error=1 or returned via POST model attribute) -->
			<c:if test="${param.error == '1' or error == '1'}">
				<div id="nameErrorAlert" class="alert alert-danger mt-3" role="alert">
					Please enter a name before submitting. Try again.
				</div>
			</c:if>
			<c:choose>
				<c:when test="${not empty userName}">
					<p class="subtitle">Welcome <span class="welcome-name">${userName}</span></p>
					<div class="mt-3">
						<a class="btn btn-outline-light" href="${homeUrl}" role="button">Back to Home</a>
					</div>
				</c:when>
				<c:when test="${not empty greeting}">
					<p class="subtitle">${greeting}</p>
					<div class="mt-3">
						<a class="btn btn-outline-light" href="${homeUrl}" role="button">Back to Home</a>
					</div>
				</c:when>
				<c:otherwise>
					<p class="subtitle">Modern, Scalable & Enterprise-Ready Solutions</p>
				</c:otherwise>
			</c:choose>
			<p>
				Experience the power of cloud-native applications with cutting-edge technology and seamless integration.
			</p>
			<c:if test="${not empty currentTime}">
				<p style="font-size: 0.9rem; opacity: 0.8;">
					<i class="fas fa-clock"></i> Current Time: ${currentTime}
				</p>
			</c:if>
			<div class="btn-hero">
				<a class="btn btn-primary me-2" href="${homeUrl}" role="button">Home</a>
				<a class="btn btn-outline-light" href="#about" role="button">Learn More</a>
			</div>
		</div>
	</div>

	<!-- Content Section -->
	<div class="container-fluid">
		<div class="content-section" id="about">
			<div class="row">
				<div class="col-lg-8">
					<h2>About Rushi Technologies</h2>
					<h3><i class="fas fa-map-marker-alt"></i> Bangalore, Karnataka, India</h3>
					<p class="lead">
						Rushi Technologies Private Limited is a leading provider of innovative IT solutions, 
						specializing in cloud infrastructure, DevOps, and modern application development.
					</p>
				</div>
				<div class="col-lg-4">
					<div class="contact-info" id="contact">
						<div class="mb-2">
							<i class="fas fa-phone-alt"></i>
							<strong>Phone:</strong> +91-99000 12028
						</div>
						<div class="mb-2">
							<i class="fas fa-envelope"></i>
							<strong>Email:</strong> rushitechnologiesbanglore@gmail.com
						</div>
						<div>
							<i class="fas fa-globe"></i>
							<strong>Website:</strong> www.rushitechnologies.com
						</div>
					</div>
				</div>
			</div>

			<!-- Features -->
			<div class="row mt-4">
				<div class="col-md-6">
					<div class="feature-card">
						<i class="fas fa-cloud"></i>
						<h5>Cloud Solutions</h5>
						<p>Enterprise-grade cloud infrastructure with 99.99% uptime guarantee and advanced security features.</p>
					</div>
				</div>
				<div class="col-md-6">
					<div class="feature-card">
						<i class="fas fa-cogs"></i>
						<h5>DevOps Expertise</h5>
						<p>Streamlined deployment pipelines and continuous integration for faster time-to-market.</p>
					</div>
				</div>
				<div class="col-md-6">
					<div class="feature-card">
						<i class="fas fa-lock"></i>
						<h5>Security First</h5>
						<p>Industry-leading security practices with compliance to international standards.</p>
					</div>
				</div>
				<div class="col-md-6">
					<div class="feature-card">
						<i class="fas fa-chart-line"></i>
						<h5>Scalability</h5>
						<p>Built to scale with your business needs, handling millions of transactions seamlessly.</p>
					</div>
				</div>
			</div>

			<!-- System Information Section -->
			<c:if test="${not empty metadata}">
				<div class="row mt-5">
					<div class="col-12">
						<h3 style="border-bottom: 2px solid #667eea; padding-bottom: 1rem; margin-bottom: 1.5rem;">
							<i class="fas fa-info-circle"></i> System Information
						</h3>
					</div>
					<div class="col-md-6">
						<div class="feature-card" style="background: #e8f4f8;">
							<i class="fas fa-server"></i>
							<h5>Application Details</h5>
							<ul style="list-style: none; padding: 0; text-align: left;">
								<li><strong>Name:</strong> ${metadata.applicationName}</li>
								<li><strong>Timestamp:</strong> ${metadata.timestamp}</li>
							</ul>
						</div>
					</div>
					<div class="col-md-6">
						<div class="feature-card" style="background: #f0e8f8;">
							<i class="fas fa-desktop"></i>
							<h5>Runtime Environment</h5>
							<ul style="list-style: none; padding: 0; text-align: left;">
								<li><strong>Java Version:</strong> ${metadata.javaVersion}</li>
								<li><strong>OS Name:</strong> ${metadata.osName}</li>
								<li><strong>Current Time:</strong> ${currentTime}</li>
								<li><strong>Status:</strong> <span style="color: #28a745;">Running</span></li>
							</ul>
						</div>
					</div>
				</div>
			</c:if>
		</div>

		<!-- Footer -->
		<footer>
			<p>&copy; 2025 Rushi Technologies. All rights reserved. | Modern Web Application</p>
		</footer>
	</div>

	<!-- Bootstrap 5 JS -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Expose HOME_URL to JS and load custom scripts -->
	<script>const HOME_URL = '${homeUrl}';</script>
	<spring:url value="/resources/core/js/modern-script.js" var="modernJs" />
	<script src="${modernJs}"></script>
</body>
</html>
