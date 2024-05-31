package cn.edu.hit.action;

import cn.edu.hit.dao.StudentDao;
import cn.edu.hit.dao.impl.StudentDaoImpl;
import cn.edu.hit.entity.Student;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "StudentServlet", value = "/student-servlet")
public class StudentServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        //为了判断是谁提交的操作(add.html还是modify.jsp都提交到student-servlet)
        //所以我们需要设置一个隐藏的变量，用于判断
        String action = request.getParameter("action");
        StudentDao dao = new StudentDaoImpl();
        if(action.equals("add")) {
            //以下的sid指的就是我表单里的name
            String sid = request.getParameter("sid");
            String name = request.getParameter("name");
            //这里要做数据类型的强制转换，因为getParameter方法默认读取的是String类型
            int age = Integer.parseInt(request.getParameter("age"));
            String birthday = request.getParameter("birthday");
            String gender = request.getParameter("gender");
            Student student = new Student(sid, name, age, birthday,gender);
            dao.Add(student);
        }
        else if (action.equals("modify")){
            String sid = request.getParameter("sid");
            String name = request.getParameter("name");
            //这里要做数据类型的强制转换，因为getParameter方法默认读取的是String类型
            int age = Integer.parseInt(request.getParameter("age"));
            String birthday = request.getParameter("birthday");
            String gender = request.getParameter("gender");
            Student student = new Student(sid,name,age,birthday,gender);
            dao.Modify(student);
        } else if (action.equals("remove")) {
            String sid = request.getParameter("sid");
            dao.Remove(sid);
        }
        //进行重定向操作，表明无论进行什么操作，最后都能回到最开始的页面
        response.sendRedirect("index.jsp");
    }
}