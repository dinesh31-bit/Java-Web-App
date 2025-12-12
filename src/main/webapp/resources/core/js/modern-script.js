// Smooth scroll for navigation links
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
	anchor.addEventListener('click', function (e) {
		e.preventDefault();
		const target = document.querySelector(this.getAttribute('href'));
		if (target) {
			target.scrollIntoView({ behavior: 'smooth', block: 'start' });
		}
	});
});

// Add animation on scroll
const observerOptions = {
	threshold: 0.1,
	rootMargin: '0px 0px -100px 0px'
};

const observer = new IntersectionObserver((entries) => {
	entries.forEach(entry => {
		if (entry.isIntersecting) {
			entry.target.style.opacity = '1';
			entry.target.style.transform = 'translateY(0)';
		}
	});
}, observerOptions);

document.querySelectorAll('.feature-card').forEach(card => {
	card.style.opacity = '0';
	card.style.transform = 'translateY(20px)';
	card.style.transition = 'all 0.6s ease';
	observer.observe(card);
});
// The name form now posts to the server (no client-side redirect required).
// Keep client-side validation minimal; HTML `required` enforces non-empty input.

// Enhance client-side UX: hide server error and validate before submit
document.addEventListener('DOMContentLoaded', function () {
	const nameInput = document.getElementById('userNameInput');
	const nameForm = document.getElementById('nameForm');
	let errorAlert = document.getElementById('nameErrorAlert');

	function ensureAlertExists() {
		if (!errorAlert) {
			// create and insert alert after the form
			errorAlert = document.createElement('div');
			errorAlert.id = 'nameErrorAlert';
			errorAlert.className = 'alert alert-danger mt-3';
			errorAlert.setAttribute('role', 'alert');
			errorAlert.textContent = 'Please enter a name before submitting. Try again.';
			const form = nameForm || document.querySelector('#nameForm');
			if (form && form.parentNode) {
				form.parentNode.insertBefore(errorAlert, form.nextSibling);
			}
		}
	}

	if (nameInput) {
		nameInput.addEventListener('input', function () {
			if (errorAlert && nameInput.value.trim().length > 0) {
				errorAlert.style.display = 'none';
			}
		});
	}

	if (nameForm) {
		nameForm.addEventListener('submit', function (e) {
			const val = nameInput ? nameInput.value.trim() : '';
			if (!val) {
				e.preventDefault();
				ensureAlertExists();
				errorAlert.style.display = 'block';
				// move focus to input for accessibility
				if (nameInput) nameInput.focus();
			}
		});
	}
});
