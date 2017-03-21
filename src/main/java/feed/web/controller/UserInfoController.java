package feed.web.controller;

import static feed.web.common.ResponseEnum.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import feed.web.common.ResponseEntity;
import feed.web.model.vo.UserInfoVo;
import feed.web.service.UserInfoService;

@RestController
@RequestMapping(value = "/users")
public class UserInfoController extends BaseController {

	@Autowired
	private UserInfoService userService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody UserInfoVo user) {
		userService.add(user);
		return new ResponseEntity<Void>(null, SUCCESS);
	}

	@RequestMapping(value = "/{userName}", method = RequestMethod.GET)
	public ResponseEntity<UserInfoVo> get(
			@PathVariable(value = "userName") String userName) {
		UserInfoVo user = userService.get(userName);
		return new ResponseEntity<UserInfoVo>(user, SUCCESS);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(
			@PathVariable(value = "userId") int userId,
			@RequestBody UserInfoVo userInfo) {
		userService.update(userId, userInfo);
		return new ResponseEntity<Void>(null, SUCCESS);
	}

	@RequestMapping(value = "/login/{userEmail}/{userPwd}", method = RequestMethod.POST)
	public ResponseEntity<String> login(
			@PathVariable(value = "userEmail") String userEmail,
			@PathVariable(value = "userPwd") String userPwd) {
		String token = userService.login(userEmail, userPwd);
		return new ResponseEntity<String>(token, SUCCESS);
	}
}
