package com.stackroute.newz.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "news")
public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "newsId")
	private Integer newsId;

	@Column(name = "title")
	private String name;

	@Column(name = "author")
	private String author;

	@Column(name = "description")
	private String description;

	@Column(name = "content")
	private String content;

	@Column(name = "publishedAt")
	private LocalDateTime publishedAt;

	public News() {
	}

	
	public Integer getNewsId() {
		return newsId;
	}


	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public LocalDateTime getPublishedAt() {
		return publishedAt;
	}


	public void setPublishedAt(LocalDateTime publishedAt) {
		this.publishedAt = publishedAt;
	}


	public News(int newsId, String name, String author, String description, String content, LocalDateTime publishedAt) {
		super();
		this.newsId = newsId;
		this.name = name;
		this.author = author;
		this.description = description;
		this.content = content;
		this.publishedAt = publishedAt;
	}

	@Override
	public String toString() {
		return "News [newsId=" + newsId + ", name=" + name + ", author=" + author + ", description=" + description
				+ ", content=" + content + ", publishedAt=" + publishedAt + "]";
	}

	

}
