function require(jsPath, async, callback, callackParan) {
	var script = document.createElement_x("script")
	script.type = "text/javascript";
	script.async = async;
	if (script.readyState) { // IE
		script.onreadystatechange = function() {
			if (script.readyState == "loaded"
					|| script.readyState == "complete") {
				script.onreadystatechange = null;
				callback(callackParan);
			}
		};
	} else {
		script.onload = function() {
			callback(callackParan);
		};
	}
	script.src = jsPath;
	document.body.appendChild(script);
}