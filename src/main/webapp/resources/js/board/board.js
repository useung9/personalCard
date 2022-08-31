

/*var sendData =*/
 function btnclick(e){
	e.preventDefault();
	/*sendData= [];
	
	var findkey = $("#findkey").val();
	var findvalue = $("#findvalue").text();
	var findcareer = $("#findcareer").val();
	var genderSelect = $("#genderSelect").val();
	var userMaritalStatusSelect = $("#userMaritalStatusSelect").val();
	var userInfoDataSize = $("#userInfoDataSize").val();
	
	sendData.push(findkey);
	sendData.push(findvalue);
	sendData.push(findcareer);
	sendData.push(genderSelect);
	sendData.push(userMaritalStatusSelect);
	sendData.push(userInfoDataSize);
	
	console.log(sendData);*/
	
	
	// key value 
	var userDataObjArr = [];
	var userDataObjPlainText = "";
	$("#navbarColor02").find("input:not(input[name='file']), select").each(function(){ // 유동 테이블 두곳 루프 돌면서 데이터 획득
	var nowLoopData = this;
		var nowLoopId = $(nowLoopData).attr("id")+"";
		var nowLoopValue = $(nowLoopData).val()+"";
	console.log("id : " + nowLoopId + "value : " + nowLoopValue);
	var nowDataPlainText = '"' + nowLoopId + '":"' + nowLoopValue + '"';
	userDataObjArr.push(nowDataPlainText);
	});
	userDataObjPlainText = "{" + userDataObjArr.join(",") + "}";
	console.log("senddata : "+userDataObjPlainText);
	var sendData = JSON.parse(userDataObjPlainText);
	console.log(sendData);
	
	/*var formData = new FormData();
	formData.append('test',sendData);
	
	//폼
	$.ajax({
		url:'/prac/board',
		type:'post',
		data : sendData,    
		dataType: "json",
		success : function(result){
			console.log(result);
		},
		error :function(e){
			console.log(e);
		}
	})*/
	document.getElementById('fmtPost').method='POST';
	document.getElementById('fmtPost').action='/prac/board';
	document.getElementById('fmtPost').submit();
	
}




function idxbtn (useridx){
	alert(useridx);
	//var ret = window.open('http://localhost:8090/prac/');
	//var ret = location.href('http://localhost:8090/prac/');
	
	$("#userIdx").val(useridx);
	
	$("#detailFrm").submit();
	
	//getRegisterData(useridx);
	
		
	}
	
	


	