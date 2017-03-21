package feed.web.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feed.web.common.ResponseEntity;
import feed.web.service.UserRelationService;

@RequestMapping("/userrelation")
public class UserRelationController extends BaseController {
	@Autowired
	private UserRelationService relationService;

	@RequestMapping(value = "/follow/{followId}", method = RequestMethod.POST)
	public ResponseEntity<Void> follow(@Param(value = "followId") int followId) {
		return null;
	}

	@RequestMapping(value = "/unfollow/{followId}", method = RequestMethod.POST)
	public ResponseEntity<Void> unFollow(@Param(value = "followId") int followId) {
		return null;
	}
}
