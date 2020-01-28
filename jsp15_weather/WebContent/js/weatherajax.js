/**
 * 
 */
$(function() {
	$("#weaview").click(function() {
		var url = "weather";
		var code = $("#address option:selected").val();
		$.ajax({
			type : "GET",
			url : url + "?code=" + code,
			dataType : "text",
			success : function(msg) {
				alert(msg);
				var tmp = $.trim(msg);
				alert(tmp);
				var obj = JSON.parse(tmp);
				alert(obj.pubDate);
				
				
				$("#pubDate").val(obj.pubDate);
				$("#temp").val(obj.temp);
				$("#x").val(obj.x);
				$("#y").val(obj.y);
				$("#reh").val(obj.reh);
				$("#pop").val(obj.pop);
				$("#wfKor").val(obj.wfKor);

				
				if (obj.wfKor == "맑음") {
					$("#weather_img").attr("src", "./image/sun.png");
				} else if (obj.wfKor == "비") {
					$("#weather_img").attr("src", "./image/rain.png");
				} else if (obj.wfKor == "눈") {
					$("#weather_img").attr("src", "./image/snow.png");
				} else if (obj.wfKor == "흐림") {
					$("#weather_img").attr("src", "./image/cloud.png");
				} else if (obj.wfKor == "구름 조금") {
					$("#weather_img").attr("src", "./image/cloud_sun.png");
				} else {
					$("#weather_img").attr("src", "./image/etc.png");
				}

			},
			error : function() {
				alert("data load failed");
			}
		})
	})
})