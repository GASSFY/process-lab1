package cn.edu.hit.action;

//B3变化之二
//
//
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String usename = request.getParameter("usename");
        String password = request.getParameter("password");
        UserDao userDao = new UserDaoImpl();
        if(userDao.login(usename,password)){
            String remenberMe = request.getParameter("rememberMe");
            if(remenberMe!=null){
                Cookie cookie1 = new Cookie("usename",usename);
                Cookie cookie2 = new Cookie("password",password);
                //给cookie设置有效期，这下面的单位是秒
                cookie1.setMaxAge(60*60*24*7);
                cookie2.setMaxAge(60*60*24*7);
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }
            //判断正确的话，我可以获取session对象
            HttpSession session = request.getSession();
            session.setAttribute("usename",usename);
            //这里的session是传给index.jsp的
            response.sendRedirect("index.jsp");
        }
        else{
            response.sendRedirect("login.html");
        }
    }
}