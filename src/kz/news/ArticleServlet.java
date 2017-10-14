package kz.news;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kz.news.dao.ArticleDao;
import kz.news.dao.IArticleDao;
import kz.news.dto.ArticleDTO;
import kz.news.exceptions.ArticleNotFoundException;
import kz.news.exceptions.DBException;
import kz.news.util.DBUtil;

public class ArticleServlet extends HttpServlet {

    private IArticleDao articleDao;

    @Override
    public void init(ServletConfig config) throws ServletException {

        String jdbcUrl = config.getInitParameter("jdbcUrl");
        String dbUsername = config.getInitParameter("dbUsername");
        String dbPassword = config.getInitParameter("dbPassword");

        Connection conn = null;

        try {
            conn = DBUtil.createConnection(jdbcUrl, dbUsername, dbPassword);
        } catch (DBException e) {
            e.printStackTrace();
        }

        articleDao = new ArticleDao(conn);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long articleId = getArticleIdFromURL(request.getRequestURL().toString());

        ArticleDTO result = null;

        try {
            result = articleDao.getArticle(articleId);
        } catch (SQLException e) {
            throw new ArticleNotFoundException(articleId);
        }

        request.setAttribute("result", result);

        request.getRequestDispatcher("/WEB-INF/article.jsp").forward(request, response);
    }

    private Long getArticleIdFromURL(String url) throws ArticleNotFoundException {
        int _index = url.lastIndexOf("-");

        if (_index > -1) {
            String idStr = url.substring(_index + 1);

            try {
                return Long.parseLong(idStr);
            } catch (NumberFormatException e) {
                throw new ArticleNotFoundException(idStr);
            }

        } 

        throw new ArticleNotFoundException(url);
    }

}
