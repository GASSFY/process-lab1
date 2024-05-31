package cn.edu.hit.dao;

import cn.edu.hit.entity.Student;

import java.util.List;
//一般来说，方法的实现我们都放在dao目录下，并且将实现类与接口分离开
public interface StudentDao {
    void Add(Student student);
    //添加
    void Modify(Student student);
    //修改
    void Remove(String sid);
    //删除
    List<Student> GetAll(String sql);
    //读取所有的学生信息，相当于遍历数据库
    Student GetStu(String sid);
    //通过学号来获取学生信息
    List<Student> GetByName(String name);
    //用名字来模糊查询
}
