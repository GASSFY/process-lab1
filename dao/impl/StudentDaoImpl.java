package cn.edu.hit.dao.impl;

import cn.edu.hit.dao.StudentDao;
import cn.edu.hit.entity.Student;
import cn.edu.hit.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao{
    //在进行实现方法的定义时，IDE就会自动把@Override的标签给打上，非常方便
    //但是以后自己写实现方法的话也尽量打标签

    @Override
    public void Add(Student student) {
        String sid = student.getSid();
        String name = student.getName();
        int age = student.getAge();
        String birthday = student.getBirthday();
        String gender = student.getGender();
        //先读取所有的变量信息
        String sql = "insert into student values(?,?,?,?,?)";
        //这里的student是我们创建的表的名字
        //调用工具类创建连接
        Connection con = DbUtil.GetConnection();
        PreparedStatement ps = null;
        try {
            //把数据写入
            ps = con.prepareStatement(sql);
            ps.setString(1,sid);
            ps.setString(2,name);
            ps.setInt(3,age);
            //sql语句的Date类型允许由String类型转型，使用ValueOf方法
            ps.setDate(4, Date.valueOf(birthday));
            ps.setString(5,gender);
            //ps更新
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            //结束了就关闭
            DbUtil.close(ps,con);
        }
    }

    @Override
    public void Modify(Student student) {
        String sid = student.getSid();
        String name = student.getName();
        int age = student.getAge();
        String birthday = student.getBirthday();
        String gender = student.getGender();
        String sql = "update student set name = ?,age = ?,birthday=? ,gender=? where sid = ?";
        Connection con = DbUtil.GetConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(5,sid);
            ps.setString(4,gender);
            ps.setString(1,name);
            ps.setInt(2,age);
            ps.setDate(3, Date.valueOf(birthday));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DbUtil.close(ps,con);
        }
    }

    @Override
    public void Remove(String sid) {
        String sql = "delete from student where sid = ?";
        //都是先建立连接然后读取sql语句
        Connection con = DbUtil.GetConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,sid);
            //如果内容有更新就加上以下语句
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DbUtil.close(ps,con);
        }
    }

    @Override
    public List<Student> GetAll(String sql) {
        String sid;
        String name;
        int age;
        String birthday;
        String gender;
        Connection con = DbUtil.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> List = new ArrayList<>();
        try {
            //ps读取sql语句
            ps = con.prepareStatement(sql);
            //以下是循环的写法
            rs = ps.executeQuery();
            //ResultSet对象可用于执行遍历操作
            while(rs.next()){
                sid = rs.getString("sid");
                name = rs.getString("name");
                age = rs.getInt("age");
                birthday = rs.getString("birthday");
                gender = rs.getString("gender");
                List.add(new Student(sid,name,age,birthday,gender));
            }
            return List;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            DbUtil.close(rs,ps,con);
        }
    }

    @Override
    public Student GetStu(String sid) {
        String name;
        int age;
        String birthday;
        String gender;
        String sql = "select * from student where sid = ?";
        Connection con = DbUtil.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student student = null;
        try {
            ps = con.prepareStatement(sql);
            //寻找特定sid的学生
            ps.setString(1,sid);
            rs = ps.executeQuery();
            if(rs.next()) {
                sid = rs.getString("sid");
                name = rs.getString("name");
                age = rs.getInt("age");
                birthday = rs.getString("birthday");
                gender = rs.getString("gender");
                student = new Student(sid, name, age, birthday,gender);
            }
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            DbUtil.close(rs,ps,con);
        }
    }

    @Override
    public List<Student> GetByName(String name) {
        String sid;
        int age;
        String birthday;
        String gender;
        //模糊查询？
        String sql = "select * from student where name like '%" + name + "%'";
        Connection con = DbUtil.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> List = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                sid = rs.getString("sid");
                name = rs.getString("name");
                age = rs.getInt("age");
                birthday = rs.getString("birthday");
                gender = rs.getString("gender");
                List.add(new Student(sid,name,age,birthday,gender));
            }
            return List;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            DbUtil.close(rs,ps,con);
        }
    }
}
