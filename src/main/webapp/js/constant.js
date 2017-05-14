/**
 * 常量js
 */
var APPID = "/feed";
var FEED_SESSION = "feedSession";
var localStorage = window.localStorage;

String.prototype.format = function() {
	if (arguments.length == 0)
		return this;
	for (var s = this, i = 0; i < arguments.length; i++)
		s = s.replace(new RegExp("\\{" + i + "\\}", "g"), arguments[i]);
	return s;
};


/**
 * 封装的自定义ajax，默认如果没有请求权限，则会跳转到login页面
 * @param url 
 * @param data 
 * @param successCallback code为0的回调函数
 * @param requestMethod  请求方法，默认为post
 * @param contentType 
 * @param failCallback code不为0的回调函数
 * @param dataType
 * @returns
 */
function feedAjax(url, data, successCallback, requestMethod, contentType, failCallback, dataType){
	var requestMethod = requestMethod || "post";
    var dataType = dataType || "json";
    var contentType = contentType || "application/json";
	$.ajax({
	    type: requestMethod,
	    url: url,
	    data: data,
	    dataType: dataType,
	    contentType: contentType,
	    beforeSend: function(request){  
	    	var token = localStorage.getItem(FEED_SESSION);
	    	request.setRequestHeader(FEED_SESSION, token);
	    },

	    success: function(onData){
	    	// 授权没通过
	    	if(onData.response.code == -4){
	    		window.location.href=APPID+"/login.html";
	    		return;
	    	} 
	    	if(onData.response.code == 0){
	    		if(successCallback){
		    		successCallback(onData);
		    	}
	    	}else{
	    		if(failCallback){
	    			failCallback(onData);
	    		}else{
	    			commonFailCallback(onData.response);
	    		}
	    	}
	    },

	    complete: function(){   
	    }
	});
}

/**
 * 
 * @param response
 * @returns
 */
function commonFailCallback(response){
	BootstrapDialog.show({
		type: BootstrapDialog.TYPE_WARNING,
		message: response.message
	})
}