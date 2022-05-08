package qqzone.pojo.service.impl;

import qqzone.pojo.UserBasic;
import qqzone.pojo.dao.UserBasicDAO;
import qqzone.pojo.service.UserBasicService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserBasicService {
    private UserBasicDAO userBasicDAO = null;

    @Override
    public UserBasic login(String loginId, String pwd) {
        UserBasic userBasic = userBasicDAO.getUserBasic(loginId, pwd);
        return userBasic;
    }

    @Override
    public List<UserBasic> getFriendList(UserBasic userBasic) {
        List<UserBasic> userBasicList = userBasicDAO.getUserBasicList(userBasic);
        List<UserBasic> friendList = new ArrayList<>(userBasicList.size());
        for (int i = 0; i < userBasicList.size(); i++) {
            UserBasic friend = userBasicList.get(i);
            friend = userBasicDAO.getUserBasticById(friend.getId());
            friendList.add(friend);
        }
        return friendList;
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        return userBasicDAO.getUserBasticById(id);
    }

    @Override
    public void addUser(String loginId, String nickName, String pwd, String headImg) {
        userBasicDAO.addUser(loginId, nickName, pwd, headImg);
    }

}
