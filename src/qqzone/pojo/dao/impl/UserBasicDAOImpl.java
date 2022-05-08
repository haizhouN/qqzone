package qqzone.pojo.dao.impl;

import myssm.basedao.BaseDAO;
import qqzone.pojo.UserBasic;
import qqzone.pojo.dao.UserBasicDAO;

import java.util.List;

public class UserBasicDAOImpl extends BaseDAO<UserBasic> implements UserBasicDAO {
    @Override
    public UserBasic getUserBasic(String loginId, String pwd) {
        return super.load("select * from t_user_basic where loginId = ? and pwd = ?", loginId, pwd);
    }

    @Override
    public List<UserBasic> getUserBasicList(UserBasic userBasic) {
        String sql = "select fid as id from t_friend where uid=?";
        return super.executeQuery(sql, userBasic.getId());
    }

    @Override
    public UserBasic getUserBasticById(Integer id) {
        return load("select * from t_user_basic where id=?", id);
    }

    @Override
    public void addUser(String loginId, String nickName, String pwd, String headImg) {
        super.executeUpdate("insert into t_user_basic value(0,?,?,?,?)", loginId, nickName, pwd, headImg);
    }
}
