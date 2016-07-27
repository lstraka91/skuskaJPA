
<h2 class="text-center">Average rating of total	${count} ratings</h2>

<br>

<div class="row">
	<div class="col-md-2"></div>
	<div class="col-md-7">
		<span class="stars">${average}</span>
	</div>

</div>
<script type="text/javascript">
	$.fn.stars = function() {
		return $(this).each(function() {
			// Get the value
			var val = parseFloat($(this).html());
			// Make sure that the value is in 0 - 5 range, multiply to get width
			var size = Math.max(0, (Math.min(5, val))) * 128;
			// Create stars holder
			var $span = $('<span />').width(size);
			// Replace the numerical value with stars
			$(this).html($span);
		});

	}

	$(function() {
		$('span.stars').stars();
	});
</script>