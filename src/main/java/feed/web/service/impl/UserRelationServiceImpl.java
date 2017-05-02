package feed.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import feed.web.common.TypeEnum.FanEnum;
import feed.web.common.TypeEnum.FollowEnum;
import feed.web.common.util.FeedAssert;
import feed.web.dao.UserFanDao;
import feed.web.dao.UserFollowDao;
import feed.web.dao.UserInfoDao;
import feed.web.model.po.UserFanPo;
import feed.web.model.po.UserFollowPo;
import feed.web.service.UserRelationService;

@Service
@Transactional
public class UserRelationServiceImpl extends BaseService implements
		UserRelationService {

	@Autowired
	private UserFanDao userFanDao;
	@Autowired
	private UserFollowDao userFollowDao;
	@Autowired
	private UserInfoDao userInfoDao;

	@Override
	public void follow(int followId) {
		int userId = getUserId();
		// 创建A关注B的关系
		UserFollowPo userFollow = new UserFollowPo();
		userFollow.setUserId(userId);
		userFollow.setFollowId(followId);
		checkFollow(userFollow);
		// 校验是否是互相关注的关系
		boolean isEachother = hasBeenFollowed(followId, userId);
		userFollow.setType(isEachother ? FollowEnum.EACHOTHER.code()
				: FollowEnum.FOLLOW.code());

		UserFanPo userFan = new UserFanPo();
		userFan.setUserId(followId);
		userFan.setFanId(userId);
		userFan.setUserId(isEachother ? FanEnum.EACHOTHER.code() : FanEnum.FAN
				.code());

		userFollowDao.add(userFollow);
		userFanDao.add(userFan);

		if (isEachother) {
			updateEachOtherType(followId, userId, FollowEnum.EACHOTHER.code(),
					FanEnum.EACHOTHER.code());
		}

		// A的关注数加一
		userInfoDao.followIncrement(userId);
		// B的粉丝数加一
		userInfoDao.fansIncrement(followId);
	}

	@Override
	public void unFollow(int followId) {
		int userId = getUserId();
		// 删除A关注B的关系
		UserFollowPo userFollow = new UserFollowPo();
		userFollow.setUserId(userId);
		userFollow.setFollowId(followId);
		checkUnFollow(userFollow);
		// 检验是否是互相关注的关系
		boolean isEachother = checkFollowEachother(userId, followId);

		// 删除B拥有粉丝A的关系
		UserFanPo userFan = new UserFanPo();
		userFan.setUserId(followId);
		userFan.setFanId(userId);
		
		userFollowDao.delete(userFollow);
		userFanDao.delete(userFan);
		
		if(isEachother){
			updateEachOtherType(followId, userId, FollowEnum.FOLLOW.code(), FanEnum.FAN.code());
		}
		
		// A的关注数减一
		userInfoDao.followDecrement(userId);
		// B的粉丝数减一
		userInfoDao.fansDecrement(followId);
	}

	/**
	 * 校验这次关注
	 * 
	 * @param userFollow
	 */
	private void checkFollow(UserFollowPo userFollow) {
		int followId = userFollow.getFollowId();
		FeedAssert.numberIsNotZero(followId, "illegal followId");

		// 校验是否有这样的target用户
		int followCount = userInfoDao.countById(followId);
		FeedAssert.numberIsNotZero(followCount, "illegal followId");

		// 校验是否是重复关注的
		int preFollow = userFollowDao.getCount(userFollow);
		FeedAssert.numberIsZero(preFollow, "repeated followId");
	}

	/**
	 * 检验这次取消关注
	 * 
	 * @param userFollow
	 */
	private void checkUnFollow(UserFollowPo userFollow) {
		int followId = userFollow.getFollowId();
		FeedAssert.numberIsNotZero(followId, "illegal unfollowId");

		// 校验是否有这样的target用户
		int followCount = userInfoDao.countById(followId);
		FeedAssert.numberIsNotZero(followCount, "illegal unfollowId");

		// 检验是否是重复取消关注的
		int preFollow = userFollowDao.getCount(userFollow);
		FeedAssert.numberIsNotZero(preFollow, "repeated unfollowId");
	}

	/**
	 * 校验是否被双向关注
	 * 
	 * @param userId
	 * @param followId
	 * @return
	 */
	private boolean hasBeenFollowed(int userId, int followId) {
		UserFollowPo userFollow = new UserFollowPo();
		userFollow.setUserId(userId);
		userFollow.setFollowId(followId);
		int followCount = userFollowDao.getCount(userFollow);
		return followCount > 0;
	}

	/**
	 * 同时更新关注和粉丝的类型
	 * 
	 * @param userId
	 * @param followId
	 * @param followCode
	 * @param fanCode
	 */
	private void updateEachOtherType(int userId, int followId, int followCode,
			int fanCode) {
		UserFollowPo userFollow = new UserFollowPo();
		userFollow.setUserId(userId);
		userFollow.setFollowId(followId);
		userFollow.setType(followCode);

		UserFanPo userFan = new UserFanPo();
		userFan.setUserId(followId);
		userFan.setFanId(userId);
		userFan.setType(fanCode);

		userFollowDao.updateType(userFollow);
		userFanDao.updateType(userFan);
	}

	/**
	 * 检查是否是互相关注的关系
	 * 
	 * @param userId
	 * @param followId
	 * @return
	 */
	private boolean checkFollowEachother(int userId, int followId) {
		UserFollowPo userFollow = new UserFollowPo();
		userFollow.setUserId(userId);
		userFollow.setFollowId(followId);
		int type = userFollowDao.getType(userFollow);
		return type == FollowEnum.EACHOTHER.code();
	}

}
