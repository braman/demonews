package kz.news.dao;

import java.sql.SQLException;

import kz.news.dto.ArticleDTO;
import kz.news.dto.PaginatedResult;
import kz.news.dto.SmallArticleDTO;

public interface IArticleDao {

    PaginatedResult<SmallArticleDTO> getArticles(int page, int size) throws SQLException;
    ArticleDTO getArticle(Long id) throws SQLException;
    
}
