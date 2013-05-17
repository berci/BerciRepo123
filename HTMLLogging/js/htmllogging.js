	// var getdata2 = function() {
        // var loadurl2 = "{{=URL('default', 'returndatafromdb2')}}";
        // $('#table').load(loadurl2);
    // };
	
	function initValue() {
		//var myloc = window.location.href;
		//var locarray = myloc.split("/");
		//delete locarray[(locarray.length-1)];
		//var arraytext = locarray.join("/");
		
		
      	//var loadurl2 = "{{=URL('default', 'returndatafromdb2')}}";
        $("#table").load("_test.html");
    	
		
		//$("#table").load(getdata2);
		
		var levels = document.getElementsByName("level");
		var selectedLevel = null;
		for(var i=0; i<levels.length; i++) {
			selectedLevel = levels[i].value;
			if(levels[i].checked) {
				break;
			} 
		}
		manageLogging(selectedLevel);
	}
	
	function call(value) {
		var selectedLevel = document.getElementById(value).value;
		manageLogging(selectedLevel);
	}
	
	function manageLogging(selectedLevel) {
		var elements = document.getElementsByTagName("tr");
		for(var i=0; i<elements.length; i++) {
			var actElementLevel = elements[i].id;
			if(selectedLevel == "TRACE") {
				elements[i].style.display="table-row";
			} else if(selectedLevel == "DEBUG") {
				if(actElementLevel == "TRACE") {
					elements[i].style.display="none";
				} else {
					elements[i].style.display="table-row";
				}
			} else if(selectedLevel == "INFO") {
				if(actElementLevel == "TRACE" || actElementLevel == "DEBUG") {
					elements[i].style.display="none";
				} else {
					elements[i].style.display="table-row";
				}
			} else if(selectedLevel == "WARN") {
				if(actElementLevel == "TRACE" || actElementLevel == "DEBUG" || actElementLevel == "INFO") {
					elements[i].style.display="none";
				} else {
					elements[i].style.display="table-row";
				}
			} else if(selectedLevel == "ERROR") {
				if(actElementLevel == "TRACE" || actElementLevel == "DEBUG" || actElementLevel == "INFO" || actElementLevel == "WARN") {
					elements[i].style.display="none";
				} else {
					elements[i].style.display="table-row";
				}
			} else if(selectedLevel == "FATAL") {
				if(actElementLevel == "TRACE" || actElementLevel == "DEBUG" || actElementLevel == "INFO" || actElementLevel == "WARN" || actElementLevel == "ERROR") {
					elements[i].style.display="none";
				} else {
					elements[i].style.display="table-row";
				}
			}	
		}
	}