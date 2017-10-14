package kz.news.dto;

import kz.news.util.ArticleHelper;

public class SmallArticleDTO {

	private Long id;
	private String title;
	private String contentPreview;
	private String subURL;
	
	public SmallArticleDTO() {}
	public SmallArticleDTO(Long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.contentPreview = content;
		
		setSubURL(ArticleHelper.slugify(title) + "-" + id);
	}
	
	public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContentPreview() {
        return contentPreview;
    }

    public void setContentPreview(String contentPreview) {
        this.contentPreview = contentPreview;
    }

    public String getSubURL() {
		return subURL;
	}

	public void setSubURL(String subURL) {
		this.subURL = subURL;
	}
	
}
