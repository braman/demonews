package kz.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kz.news.dto.ArticleDTO;
import kz.news.dto.PaginatedResult;
import kz.news.dto.SmallArticleDTO;

public class ArticleDao implements IArticleDao {
	
    private Connection connection;

    public ArticleDao(Connection connection) {
        this.connection = connection;
    }
    
	public PaginatedResult<SmallArticleDTO> getArticles(int page, int size) throws SQLException {
	    if (size < 1) {
	        throw new IllegalArgumentException("size cannot be non-positive!");
	    }
	    
	    int offset = page > 0 ? (page - 1) * size : 0; 

	    Statement stmt = connection.createStatement();
	    
	    ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM articles"); 
	    
	    int total = 0;
	    
	    if (rs.next()) {
	        total = rs.getInt(1);
	    }
	    
	    int remainder = total % size > 0 ? 1 : 0;
	    total = total/size + remainder;
	    
	    String query = "SELECT id, title, content_preview, sub_url FROM articles LIMIT ? OFFSET ?";
	    
	    PreparedStatement pstmt = connection.prepareStatement(query);
	    
	    pstmt.setInt(1, size);
	    pstmt.setInt(2, offset);

	    rs = pstmt.executeQuery();
	    
	    List<SmallArticleDTO> resultList = new ArrayList<SmallArticleDTO>();
	    
	    while (rs.next()) {
	        SmallArticleDTO dto = new SmallArticleDTO();
	        dto.setId(rs.getLong("id"));
	        dto.setTitle(rs.getString("title"));
	        dto.setContentPreview(rs.getString("content_preview"));
	        dto.setSubURL(rs.getString("sub_url"));
	        
	        resultList.add(dto);
	    }
	    
	    return new PaginatedResult(resultList, total);
	}

    @Override
    public ArticleDTO getArticle(Long id) throws SQLException {
        String query = "SELECT id, title, content_full, sub_url FROM articles WHERE id=?";
        
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setLong(1, id);
        
        ResultSet rs = pstmt.executeQuery();
        
        
        if (rs.next()) {
            ArticleDTO dto = new ArticleDTO();
            dto.setId(rs.getLong("id"));
            dto.setTitle(rs.getString("title"));
            dto.setContent(rs.getString("content_full"));
            dto.setSubURL(rs.getString("sub_url"));
            
            return dto;
        }
        
        return null;
    }
}
