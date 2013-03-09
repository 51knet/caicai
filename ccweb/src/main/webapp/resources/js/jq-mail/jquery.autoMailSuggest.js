jQuery.autoMailSuggest = function(input, options) {
	// Create a link to self
	var me = this;

	// Create jQuery object for input element
	var $input = $(input).attr("autocomplete", "off");

	// Apply inputClass if necessary
	if (options.inputClass)
		$input.addClass(options.inputClass);

	// Create results
	var results = document.createElement("div");
	// Create jQuery object for results
	var $results = $(results);
	$results.hide().addClass(options.resultsClass).css("position", "absolute");
	if (options.width > 0)
		$results.css("width", options.width);

	// Add to body element
	$("body").append(results);

	input.autocompleter = me;

	var timeout = null;
	var prev = "";
	var active = -1;
	var hasFocus = false;
	//var lastKeyPressCode = null;
	var suffix = options.suffix;

	$input.keydown(function(e) {
		// track last key pressed
		lastKeyPressCode = e.keyCode;
		switch (e.keyCode) {
		case 38: // up
			e.preventDefault();
			moveSelect(-1);
			break;
		case 40: // down
			e.preventDefault();
			moveSelect(1);
			break;
		case 9: // tab
		case 13: // return
			if (selectCurrent()) {
				// make sure to blur off the current field
				$input.get(0).blur();
				e.preventDefault();
			}
			break;
		default:
			active = -1;
			if (timeout)
				clearTimeout(timeout);
			timeout = setTimeout(function() {
				onChange();
			}, options.delay);
			break;
		}
	}).focus(function() {
		// track whether the field has focus, we shouldn't process any results
		// if the field no longer has focus
		hasFocus = true;
	}).blur(function() {
		// track whether the field has focus
		hasFocus = false;
		hideResults();
	});

	hideResultsNow();

	function onChange() {
		// ignore if the following keys are pressed: [del] [shift] [capslock]
		/*if (lastKeyPressCode == 46
				|| (lastKeyPressCode > 8 && lastKeyPressCode < 32))
			return $results.hide();*/
		var v = $input.val();
		if (v == prev)
			return;
		prev = v;
		if (v.length >= options.minChars) {
			reqSuggestData(v);
		} else {
			$results.hide();
		}
	}
	;

	function moveSelect(step) {

		var lis = $("li", results);
		if (!lis)
			return;

		active += step;

		if (active < 0) {
			active = 0;
		} else if (active >= lis.size()) {
			active = lis.size() - 1;
		}

		lis.removeClass("ac_over");

		$(lis[active]).addClass("ac_over");
	}
	;

	function selectCurrent() {
		var li = $("li.ac_over", results)[0];
		if (!li) {
			var $li = $("li", results);
			if (options.selectOnly) {
				if ($li.length == 1)
					li = $li[0];
			} else if (options.selectFirst) {
				li = $li[0];
			}
		}
		if (li) {
			selectItem(li);
			return true;
		} else {
			return false;
		}
	}
	;

	function selectItem(li) {
		if (!li) {
			li = document.createElement("li");
			li.extra = [];
			li.selectValue = "";
		}
		var v = $.trim(li.selectValue ? li.selectValue : li.innerHTML);
		input.lastSelected = v;
		prev = v;
		$results.html("");
		$input.val(v);
		hideResultsNow();
		if (options.onItemSelect)
			setTimeout(function() {
				options.onItemSelect(li);
			}, 1);
	}
	;

	// selects a portion of the input string
	function createSelection(start, end) {
		// get a reference to the input element
		var field = $input.get(0);
		if (field.createTextRange) {
			var selRange = field.createTextRange();
			selRange.collapse(true);
			selRange.moveStart("character", start);
			selRange.moveEnd("character", end);
			selRange.select();
		} else if (field.setSelectionRange) {
			field.setSelectionRange(start, end);
		} else {
			if (field.selectionStart) {
				field.selectionStart = start;
				field.selectionEnd = end;
			}
		}
		field.focus();
	}
	;

	function showResults() {
		// get the position of the input field right now (in case the DOM is
		// shifted)
		var pos = findPos(input);
		// either use the specified width, or autocalculate based on form
		// element
		var iWidth = (options.width > 0) ? options.width : $input.width();
		// reposition
		$results.css({
			width : parseInt(iWidth) + "px",
			top : (pos.y + input.offsetHeight) + "px",
			left : pos.x + "px"
		}).show();
	}
	;

	function hideResults() {
		if (timeout)
			clearTimeout(timeout);
		timeout = setTimeout(hideResultsNow, 200);
	}
	;

	function hideResultsNow() {
		if (timeout)
			clearTimeout(timeout);
		if ($results.is(":visible")) {
			$results.hide();
		}
	}
	;

	function dataToDom(data) {
		var ul = document.createElement("ul");
		var num = data.length;

		// limited results to a max number
		if ((options.maxItemsToShow > 0) && (options.maxItemsToShow < num))
			num = options.maxItemsToShow;

		for ( var i = 0; i < num; i++) {
			var row = data[i];
			if (!row)
				continue;
			var li = document.createElement("li");
			li.innerHTML = row;
			li.selectValue = row;
			ul.appendChild(li);
			$(li).hover(function() {
				$("li", ul).removeClass("ac_over");
				$(this).addClass("ac_over");
				active = $("li", ul).indexOf($(this).get(0));
			}, function() {
				$(this).removeClass("ac_over");
			}).click(function(e) {
				e.preventDefault();
				e.stopPropagation();
				selectItem(this);
			});
		}
		return ul;
	}
	;

	function reqSuggestData(q) {
		var data = [];
		var indexAt = q.indexOf('@');
		if (indexAt < 0) {
			for ( var i = 0, len = suffix.length; i < len; i++) {
				data.push(q + "@" + suffix[i]);
			}
		} else {
			var head = q.substring(0, indexAt + 1);
			var mid = q.substring(indexAt + 1);
			for ( var i = 0, len = suffix.length; i < len; i++) {
				if (suffix[i].indexOf(mid) == 0) {
					data.push(head + suffix[i]);
				}
			}
		}

		if (data) {
			results.innerHTML = "";

			// if the field no longer has focus or if there are no matches, do
			// not display the drop down
			if (!hasFocus || data.length == 0)
				return hideResultsNow();

			if ($.browser.msie) {
				// we put a styled iframe behind the calendar so HTML SELECT
				// elements don't show through
				$results.append(document.createElement('iframe'));
			}
			results.appendChild(dataToDom(data));
			showResults();
		} else {
			hideResultsNow();
		}
	}
	;

	function findPos(obj) {
		var curleft = obj.offsetLeft || 0;
		var curtop = obj.offsetTop || 0;
		while (obj = obj.offsetParent) {
			curleft += obj.offsetLeft;
			curtop += obj.offsetTop;
		}
		return {
			x : curleft,
			y : curtop
		};
	}
};

jQuery.fn.autoMailSuggest = function(suffix, options) {
	// Make sure options exists
	options = options || {};

	// set some bulk local data
	options.suffix = ((typeof suffix == "object") && (suffix.constructor == Array)) ? suffix
			: null;

	// Set default values for required options
	options.inputClass = options.inputClass || "ac_input";
	options.resultsClass = options.resultsClass || "ac_results";
	options.lineSeparator = options.lineSeparator || "\n";
	options.cellSeparator = options.cellSeparator || "|";
	options.minChars = options.minChars || 1;
	options.delay = options.delay || 50;
	options.selectFirst = options.selectFirst || false;
	options.selectOnly = options.selectOnly || false;
	options.maxItemsToShow = options.maxItemsToShow || -1;
	options.width = parseInt(options.width, 10) || 0;

	this.each(function() {
		var input = this;
		new jQuery.autoMailSuggest(input, options);
	});

	// Don't break the chain
	return this;
};

jQuery.fn.indexOf = function(e) {
	for ( var i = 0; i < this.length; i++) {
		if (this[i] == e)
			return i;
	}
	return -1;
};

var defaultMailSuffix = ['qq.com', 'gmail.com', '163.com', '139.com', 'yahoo.com', '126.com', 'hotmail.com', 'sina.com',
                         'yahoo.com.cn', 'sohu.com'];