/*
	home页面的js
 */
var carUrl = "/users/card";
var msgUrl = "/msg/home/{0}/{1}";
var feedTemp = $("#feedTemplate");
var feedArea = $("#feedArea");
var curIndex = 1;

$(function() {
	initUserInfo();
	initFeedList();
});

/**
 * 加载左边的用户信息
 * 
 * @returns
 */
function initUserInfo() {
	feedAjax(APPID + carUrl, null, function(onData) {
		var data = onData.data;
		$("#userName").html(data.userName);
		$("#followCount").html(data.followCount);
		$("#fansCount").html(data.fansCount);
		$("#msgCount").html(data.msgCount);
	}, "get");
}

/**
 * 初始化feed列表
 * 
 * @returns
 */
function initFeedList() {
	getAndAppendFeeds(curIndex++);
}

/**
 * 从Server获取数据并且添加到feedArea中
 * 
 * @param pageIndex
 * @returns
 */
function getAndAppendFeeds(pageIndex) {
	var curUrl = APPID + msgUrl.format(pageIndex, PAGE_COUNT);
	feedAjax(curUrl, null, function(onData) {
		appendFeeds(onData.data);
	}, "get");
}

function appendFeeds(feedList) {
	var curData;
	var curFeed;
	// 当前的时间戳
	var curTimeStamp = new Date().getTime()/1000;
	for (var i = 0; i < feedList.length; i++) {
		curData = feedList[i];
		curFeed = feedTemp.clone();
		dealWithCloneFeed(curFeed, curData);
		curFeed.appendTo(feedArea);
	}
	var timeagoInstance = timeago();
	timeagoInstance.render($('.timestamp'), 'zh_CN');

	// 处理clone后的Feed
	function dealWithCloneFeed(feed, data) {
		feed.css("display", "inline");
		// feedId暂时 = userId_msgId
		feed.attr("id", data.userId + "_" + data.msgId);
		feed.find("h4").html(data.userName);
		feed.find("div.feedContent").html(data.content);
		feed.find("i.icon-reply").html(data.transferCount);
		feed.find("i.icon-comment-alt").html(data.commentCount);
		// 显示时间戳
		var dtimeStamp = data.timeStamp;
		var curTelement = feed.find("i.feedt");
		// 当时间戳跟当前相差一个小时
		if(curTimeStamp - dtimeStamp < EXPIRE_TIME){
			curTelement.attr("data-timeago", dtimeStamp*1000);
			curTelement.addClass(".timestamp");
		}
		else
			curTelement.html(new Date(dtimeStamp*1000).format(curTimeStamp*1000));
			
	}
}
