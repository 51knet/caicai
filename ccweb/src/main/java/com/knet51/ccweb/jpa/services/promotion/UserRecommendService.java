package com.knet51.ccweb.jpa.services.promotion;

import java.util.List;
import com.knet51.ccweb.jpa.entities.User;

public interface UserRecommendService {

	public List<User> getRandomUsers(String role);

	public List<User> getRandomUsers(String role, int count);

	public List<User> getRecommendTeachersFromMyTeacher(Long id);

	public List<User> getRecommendTeachersFromMyTeacher(Long id, int count);

	public List<User> getRecommendTeachersFromFriendsTeacher(Long id);

	public List<User> getRecommendTeachersFromFriendsTeacher(Long id, int count);

	// key logic, first role "friendRole" means the friends of yours who are in
	// friendRole, the second role "targetRole" means the friends of your
	// friends list, which get from the first time search, in that targetRole.
	public List<User> getRandomUsersFriends(String friendRole,
			String targetRole, Long id);

	public List<User> getRandomUsersFriends(String friendRole,
			String targetRole, Long id, int count);

}
