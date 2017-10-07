package kz.news;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kz.news.dao.ArticleDao;
import kz.news.dto.ArticleDTO;

@WebServlet("/")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MainServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    int currentPageNo = getArticlePageNoFromURL(request.getRequestURL().toString());
	    
	    final int size = 20;
	    final int totalPages = 100;
	    
		List<ArticleDTO> top10NewsList = ArticleDao.getInstance().getArticles(currentPageNo, size); 
		
		request.setAttribute("top10NewsList", top10NewsList);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("currentPageNo", currentPageNo);
		
		request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
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
