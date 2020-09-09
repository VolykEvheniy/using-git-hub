package com.company.forum.web;

import com.company.forum.model.Topic;
import com.company.forum.model.User;
import com.company.forum.services.PostService;
import com.company.forum.services.TopicService;
import com.company.forum.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.spi.AbstractResourceBundleProvider;

@WebServlet(name = "FrontControllerServlet", urlPatterns = {"/do/*"})
public class FrontControllerServlet extends HttpServlet {

    UserService userService;
    TopicService topicService;
    PostService postService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute("userService");
        topicService = (TopicService) config.getServletContext().getAttribute("topicService");
        postService = (PostService) config.getServletContext().getAttribute("postService");
    }

    protected void processor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String path = request.getPathInfo();

        if (path == null) {
            path = "/";
        }

        try {
            switch (path) {
                case "/createAccount":
                    createAccount(request, response);
                    break;
                case "/login":
                    login(request, response);
                    break;
                case "/logout":
                    logout(request, response);
                    break;
                case "/topic":
                    topic(request, response);
                    break;
                case "/addTopic":
                    addTopic(request, response);
                    break;
                case "/editTopicOpen":
                    editTopicOpen(request, response);
                    break;
                case "/editTopic":
                    editTopic(request, response);
                    break;
                case "/removeTopic":
                    removeTopic(request, response);
                    break;
                case "/addPostOpen":
                    addPostOpen(request, response);
                    break;
                case "/addPost":
                    addPost(request, response);
                    break;
                case "/":
                default:
                    topics(request, response);
                    break;
            }
        } catch (RuntimeException e) {
            error(request, response, "Exception! " + e.getMessage());
        }

    }

    protected void createAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        request.getRequestDispatcher("/WEB-INF/view/createAccount.jsp").forward(request, response);
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email == null || password == null || email.equals("") || password.equals("")) {
            error(request, response, "Поля не можуть бути порожніми");
            return;
        } else if (userService.getByEmail(email) != null) {
            error(request, response, "Користувач з такою поштою вже зареєстрований");
            return;
        }
        userService.registerUser(email, password, "user");
        response.sendRedirect("/");
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null || email.equals("") || password.equals("")) {
            error(request, response, "Поля не можуть бути порожніми");
        } else if (userService.getByEmail(email) == null) {
            error(request, response, "Користувача з такою поштою не існує");
        } else if (userService.checkPassword(email, password)) {
            User user = userService.getByEmail(email);
            request.getSession().setAttribute("user", user);
            response.sendRedirect(".");
        } else {
            error(request, response, "Перевірте будь ласка дані");
        }
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(".");
    }

    protected void topic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int topicId = Integer.parseInt(request.getParameter("topicId"));
        Topic topic = topicService.getById(topicId);
        request.setAttribute("topic", topic);
        request.getRequestDispatcher("/WEB-INF/view/topic.jsp").forward(request, response);
    }

    protected void topics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Topic> topics = topicService.getAllTopics();
        request.setAttribute("topics", topics);

        request.getRequestDispatcher("/WEB-INF/view/topics.jsp").forward(request, response);
    }

    protected void addTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/addTopic.jsp").forward(request, response);

        User user = (User) request.getSession().getAttribute("user");
        String topicTitle = request.getParameter("topicTitle");
        String topicText = request.getParameter("topicText");
        if (topicTitle == null || "".equals(topicTitle) || topicText == null || "".equals(topicText)) {
            return;
        }
        Date date = new Date();
        topicService.addTopic(user, topicTitle, topicText, date.toString());
        response.sendRedirect("/WEB-INF/view/topics.jsp");
    }

    protected void editTopicOpen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int topicId = Integer.parseInt(request.getParameter("topicId"));
        Topic topic = topicService.getById(topicId);
        request.getSession().setAttribute("topic", topic);
        request.getRequestDispatcher("/WEB-INF/view/editTopic.jsp").forward(request, response);
    }

    protected void editTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String topicTitle = request.getParameter("topicTitle");
        String topicText = request.getParameter("topicText");
        if (topicTitle == null || "".equals(topicTitle) || topicText == null || "".equals(topicText)) {
            return;
        }
        Topic topic = (Topic) request.getSession().getAttribute("topic");
        int topicId = topic.getTopicId();
        topicService.editTopic(topicId, topicTitle, topicText);

        response.sendRedirect("/");
    }

    protected void removeTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int topicId = Integer.parseInt(request.getParameter("topicId"));
        topicService.removeTopic(topicId);
        response.sendRedirect("/");
    }

    protected void addPostOpen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int topicId = Integer.parseInt(request.getParameter("topicId"));
        Topic topic = topicService.getById(topicId);
        request.getSession().setAttribute("topic", topic);
        request.getRequestDispatcher("/WEB-INF/view/addPost.jsp").forward(request, response);
    }

    protected void addPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Topic topic = (Topic) request.getSession().getAttribute("topic");

        User user = (User) request.getSession().getAttribute("user");
        String postText = request.getParameter("postText");
        if (postText == null || "".equals(postText)) {
            return;
        }
        Date date = new Date();
        postService.addPost(topic, user, date.toString(), postText);
        response.sendRedirect("/");
    }

    protected void error(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.getSession().setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processor(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processor(request, response);
    }
}
