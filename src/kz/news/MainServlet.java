package kz.news;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kz.news.dao.ArticleDao;
import kz.news.dao.IArticleDao;
import kz.news.dto.PaginatedResult;
import kz.news.dto.SmallArticleDTO;
import kz.news.exceptions.DBException;
import kz.news.util.DBUtil;

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
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
		
	    int currentPageNo = getArticlePageNoFromURL(request.getRequestURL().toString());
	    
	    final int size = 20;
	    
	    try {
	        PaginatedResult<SmallArticleDTO> top10News = articleDao.getArticles(currentPageNo, size); 

	        request.setAttribute("top10News", top10News);
	        request.setAttribute("currentPageNo", currentPageNo);
	        
	        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	    } catch (SQLException e) {
	        //TODO: 500 error page
	    }
		
	}

	private int getArticlePageNoFromURL(String url) {
        int _index = url.lastIndexOf("//");
        
        if (_index > -1) {
            String valStr = url.substring(_index + 1);
            
            try {
                return Integer.parseInt(valStr);
            } catch (NumberFormatException e) {
                return 0;
            }
        } 
        
        return 0;
    }
	
}
