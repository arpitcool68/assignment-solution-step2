package com.stackroute.newz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.stackroute.newz.dao.NewsDAO;
import com.stackroute.newz.model.News;

@Controller
@ComponentScan("com.stackroute.newz.dao")
public class NewsController {

	private static final String INDEX_PAGE = "index";

	@Autowired
	@Qualifier("newsDaoImpl")
	private NewsDAO newsDAO;

	@RequestMapping(value = "/")
	public String getNewsList(ModelMap model) {

		List<News> newsList = getAllNews();
		model.addAttribute("news", new News());
		model.addAttribute("isUpdate", false);
		model.addAttribute("news", newsList);
		return INDEX_PAGE;
	}

	@RequestMapping(value = "/add"  , method = RequestMethod.POST )
	public String saveNews(@ModelAttribute("news") News newsObj, ModelMap modelMap) {
		System.out.println("HIiiiiiiiiiiiiiiiiiiii");
		if (newsObj.getNewsId() != null) {
			News newsObjFromDb = newsDAO.getNewsById(newsObj.getNewsId());
			if (newsObjFromDb != null)
				newsObj.setPublishedAt(newsObjFromDb.getPublishedAt());
			boolean isUpdated = newsDAO.updateNews(newsObj);
			if (newsObj.getAuthor().equals("") && newsObj.getName().equals(""))
				isUpdated = false;
			List<News> newsList = getAllNews();
			modelMap.addAttribute("news", newsList);
			if (!isUpdated)
				return INDEX_PAGE;
		} else {
			boolean isAdded = newsDAO.addNews(newsObj);
			List<News> newsLst = getAllNews();
			modelMap.addAttribute("news", newsLst);
			if (!isAdded)
				return INDEX_PAGE;
		}

		return "redirect:/";
	}

	@RequestMapping(value = "/delete")
	public String deleteNews(ModelMap map, @RequestParam("newsId") int newsId) {
		if (!newsDAO.deleteNews(newsId)) {
			return INDEX_PAGE;
		}
		List<News> newsLst = getAllNews();
		map.addAttribute("news", newsLst);

		return "redirect:/";
	}

	@RequestMapping(value = "/update")
	public String updateNews(ModelMap map, @RequestParam("newsId") int newsId) {

		News currentNews = newsDAO.getNewsById(newsId);
		boolean isUpdated = newsDAO.updateNews(currentNews);
		List<News> newsLst = getAllNews();
		map.addAttribute("news", newsLst);
		map.addAttribute("isUpdate", isUpdated);
		return INDEX_PAGE;
	}

	private List<News> getAllNews() {
		return newsDAO.getAllNews();
	}

}