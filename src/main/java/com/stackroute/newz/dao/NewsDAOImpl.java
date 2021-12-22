package com.stackroute.newz.dao;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.newz.model.News;

@Repository("newsDaoImpl")
@Transactional
public class NewsDAOImpl implements NewsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public NewsDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean addNews(News news) {
		Session currentSession = getCurrentSession();
		news.setPublishedAt(LocalDateTime.now());
		currentSession.save(news);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<News> getAllNews() {
		Session session = getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<News> criteriaQuery = criteriaBuilder.createQuery(News.class);
		Root<News> root = criteriaQuery.from(News.class);
		criteriaQuery.select(root);
		Query query = session.createQuery(criteriaQuery);
		return null != query.getResultList() ? query.getResultList() : Collections.emptyList();
	}

	public News getNewsById(int newsId) {
		Session currentSession = getCurrentSession();
		return currentSession.get(News.class, newsId);
	}

	public boolean updateNews(News news) {
		Session currentSession = getCurrentSession();
		currentSession.update(news);
		return true;
	}

	public boolean deleteNews(int newsId) {
		Session session = getCurrentSession();
		News news = session.byId(News.class).load(newsId);
		session.delete(news);
		return true;
	}
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}