package cn.edu.hit.dao.impl;

import cn.edu.hit.dao.UserDao;
import cn.edu.hit.entity.Student;
import cn.edu.hit.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean login(String usename, String password) {
        String sql = "select count(*) from alluser where usename=? and password=?";
        Connection con = DbUtil.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count=0;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,usename);
            ps.setString(2,password);
            rs = ps.executeQuery();
            rs.next();
            count = rs.getInt(1);
            return count>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            DbUtil.close(rs,ps,con);
        }
    }
}
