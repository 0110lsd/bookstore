package com.atschool.web;

import com.atschool.pojo.User;
import com.atschool.service.UserService;
import com.atschool.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    /**
     * 用户注册
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        if("doge".equalsIgnoreCase(code)) {         // 验证码验证

            if(userService.existsUsername(username)) {  // 用户名是否已存在

                String msg = "用户名已存在";
                req.setAttribute("msg", msg);
                req.setAttribute("username", username);
                req.setAttribute("email", email);

                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);

            } else {

                User user = new User(null, username, password, email);

                userService.register(user);

                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        }

        String msg = "验证码错误";
        req.setAttribute("username", username);
        req.setAttribute("email", email);

        req.setAttribute("msg", msg);
        req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
    }

    /**
     * 登录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User login = userService.login(new User(null, username, password, null));

        if(login == null) {

            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);

        } else {

            req.getSession().setAttribute("user", login);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);

        }

    }

    /**
     * 登出
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().invalidate();

        resp.sendRedirect(req.getContextPath());
    }
}
