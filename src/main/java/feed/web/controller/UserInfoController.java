package feed.web.controller;

import static feed.web.common.ResponseEnum.SUCCESS;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import feed.web.common.ResponseEntity;
import feed.web.model.data.AvatarData;
import feed.web.model.data.UserInfoData;
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
	public ResponseEntity<UserInfoVo> get(@PathVariable(value = "userName") String userName) {
		UserInfoVo user = userService.get(userName);
		return new ResponseEntity<UserInfoVo>(user, SUCCESS);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UserInfoVo userInfo) {
		userService.update(userInfo);
		return new ResponseEntity<Void>(null, SUCCESS);
	}

	@RequestMapping(value = "/login/{userEmail}/{userPwd}", method = RequestMethod.POST)
	public ResponseEntity<String> login(@PathVariable(value = "userEmail") String userEmail,
			@PathVariable(value = "userPwd") String userPwd) {
		String token = userService.login(userEmail, userPwd);
		return new ResponseEntity<String>(token, SUCCESS);
	}

	@RequestMapping(value = "/card", method = RequestMethod.GET)
	public ResponseEntity<UserInfoData> getCard() {
		UserInfoData result = userService.getCard();
		return new ResponseEntity<UserInfoData>(result, SUCCESS);
	}

	@RequestMapping(value = "/avatar", method = RequestMethod.POST)
	public ResponseEntity<String> upLoadAvatar(@RequestParam(value = "avatar_file") MultipartFile avatarFile,
			@RequestParam(value = "avatar_src") String avatarSrc,
			@RequestParam(value = "avatar_data") String avatarData, HttpServletRequest request) {
		// 用户上传自己的头像
		AvatarData avaData = JSON.parseObject(avatarData, AvatarData.class);
		String picturePath = userService.uploadAvatar(avatarFile, avaData);
		return new ResponseEntity<String>(picturePath, SUCCESS);
	}

}
