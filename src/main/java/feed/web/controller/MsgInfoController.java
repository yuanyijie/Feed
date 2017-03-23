package feed.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import feed.web.common.ResponseEntity;
import feed.web.common.ResponseEnum;
import feed.web.service.MsgInfoService;

@RestController
@RequestMapping("/msg")
public class MsgInfoController extends BaseController {
	
	@Autowired
	private MsgInfoService msgInfoService; 
	
	// 发表新的feed
	@RequestMapping(value = "/postfeed", method = RequestMethod.POST)
	public ResponseEntity<Void> postFeed(@RequestBody String feedContent) {
		msgInfoService.postFeed(feedContent);
		return ResponseEnum.VOID_SUCCESS;
	}
}
