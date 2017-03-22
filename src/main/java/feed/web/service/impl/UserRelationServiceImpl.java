package feed.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import feed.web.common.TypeEnum.FollowEnum;
import feed.web.dao.UserInfoDao;
import feed.web.dao.UserRelationDao;
import feed.web.model.po.UserRelationPo;
import feed.web.service.UserRelationService;

@Service
@Transactional
public class UserRelationServiceImpl extends BaseService implements UserRelationService {
	
	@Autowired
	private UserRelationDao userRelationDao;
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Override
	public void follow(int followId) {
		int userId = getUserId();
		// 创建A关注B的关系
		UserRelationPo userRelation = new UserRelationPo();
		userRelation.setUserId(userId);
		userRelation.setFollowId(followId);
		userRelation.setType(FollowEnum.FOLLOW.code());
		userRelationDao.add(userRelation);
		
		// 创建B被A关注的关系
		userRelation.setUserId(followId);
		userRelation.setFollowId(userId);
		userRelation.setType(FollowEnum.FANS.code());
		userRelationDao.add(userRelation);
		
		// A的关注数加一
		userInfoDao.followIncrement(userId);
		// B的粉丝数加一
		userInfoDao.fansIncrement(followId);
	}

	@Override
	public void unFollow(int followId) {
		int userId = getUserId();
		// 删除A关注B的关系
		UserRelationPo userRelation = new UserRelationPo();
		userRelation.setUserId(userId);
		userRelation.setFollowId(followId);
		userRelation.setType(FollowEnum.FOLLOW.code());
		userRelationDao.delete(userRelation);
		
		// 删除B被A关注的关系
		userRelation.setUserId(followId);
		userRelation.setFollowId(userId);
		userRelation.setType(FollowEnum.FANS.code());
		userRelationDao.delete(userRelation);
		// A的关注数减一
		userInfoDao.followDecrement(userId);
		// B的粉丝数减一
		userInfoDao.fansDecrement(followId);
	}

}
