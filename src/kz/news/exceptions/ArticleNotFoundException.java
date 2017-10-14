package kz.news.exceptions;

import javax.servlet.ServletException;

public class ArticleNotFoundException extends ServletException {
	
	public ArticleNotFoundException(String id) {
		super("Article not found with id " + id);
	}
	
	public ArticleNotFoundException(Long id) {
		super("Article not found with id " + id);
	}
	
}
