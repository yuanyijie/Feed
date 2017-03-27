package feed.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import feed.web.common.ResponseEntity;
import feed.web.common.ResponseEnum;
import feed.web.model.data.MsgInfoData;
import feed.web.service.MsgInfoService;

@RestController
@RequestMapping("/msg")
public class MsgInfoController extends BaseController {

	@Autowired
	private MsgInfoService msgInfoService;

	// 发表新的feed
	@RequestMapping(value = "/feed", method = RequestMethod.POST)
	public ResponseEntity<Void> postFeed(@RequestBody String feedContent) {
		msgInfoService.postFeed(feedContent);
		return ResponseEnum.VOID_SUCCESS;
	}

	// 分页获取home的信息
	@RequestMapping(value = "/home/{index}/{chunk}", method = RequestMethod.GET)
	public ResponseEntity<List<MsgInfoData>> getHome(
			@PathVariable("index") int index, @PathVariable("chunk") int chunk) {
		return null;
	}

}
