/**
 * 常量js
 */
// 系统名称
var APPID = "/feed";
// header key
var FEED_SESSION = "feedSession";
var localStorage = window.localStorage;
// 分页每页的默认条目数
var PAGE_COUNT = 10;
// 超过这个时间段就显示完整时间格式
var EXPIRE_TIME = 24*60*60;

String.prototype.format = function() {
	if (arguments.length == 0)
		return this;
	for (var s = this, i = 0; i < arguments.length; i++)
		s = s.replace(new RegExp("\\{" + i + "\\}", "g"), arguments[i]);
	return s;
};

Date.prototype.format = function(standardTime) {
	var curTime = standardTime || new Date().getTime();
	var year = this.getFullYear();
	var month = this.getMonth() + 1 < 10 ? "0" + (this.getMonth() + 1) : this.getMonth() + 1;
	var date = this.getDate() < 10 ? "0" + this.getDate() : this.getDate();
	var hour = this.getHours()< 10 ? "0" + this.getHours() : this.getHours();
	var minute = this.getMinutes()< 10 ? "0" + this.getMinutes() : this.getMinutes();
//	var second = this.getSeconds()< 10 ? "0" + this.getSeconds() : this.getSeconds();
	var monthDate = month + "-" + date+" "+hour+":"+minute;
	if((curTime - this.getTime()) > 365*24*3600*1000)
		monthDate = year + "-" + monthDate;
	return monthDate;
}


/**
 * 封装的自定义ajax，默认如果没有请求权限，则会跳转到login页面
 * 
 * @param url
 * @param data
 * @param successCallback
 *            code为0的回调函数
 * @param requestMethod
 *            请求方法，默认为post
 * @param contentType
 * @param failCallback
 *            code不为0的回调函数
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
	    		localStorage.removeItem(FEED_SESSION);
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
 * 通用的调用失败ajax处理
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