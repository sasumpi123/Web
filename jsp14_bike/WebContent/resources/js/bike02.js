/**
 * 
 */

$(function() {
	parseJsonTest();
})

/*
 * JSON.parse() : String - > json JSON.stringify() : json -> String
 * 
 */
function parseJsonTest() {
	$.getJSON("resources/json/bike.json", function(data) {
		$.ajax({
			url : "bike.do?command=second_db",
			method : "post",
			// 앞 data : ajax의 key
			// 뒤 data : 위 function에서 받아온 data
			data : {
				"obj" : JSON.stringify(data)
			},
			success : function(msg) {
				var list = data.DATA;
				if (msg == list.length) {
					// table에 표시
					$.each(data, function(key, val) {
						if (key == "DESCRIPTION") {
							$("table").attr("border", "1");
							var $tr = $("<tr>");
							for ( var i in val) {
								$tr.append($("<th>").html(val[i]));
							}
							$("thead").append($tr);
						} else {
							for (var i = 0; i < val.length; i++) {
								var $tr = $("<tr>");
								for ( var j in val[i]) {
									$tr.append($("<td>").html(val[i][j]));
								}
							}
							$("tbody").append($tr);
						}
					});
					console.log(list.length);
				} else {
					alert("저장 실패");
				}
			},
			error : function() {
				alert("통신 실패");
			}
		});
	});
}