package com.shaoxia.controller.admin;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shaoxia.bean.ArticleData;
import com.shaoxia.bean.DataTablesParam;
import com.shaoxia.bean.DataTablesResult;
import com.shaoxia.pojo.ArticleMain;
import com.shaoxia.service.ArticleService;

@RequestMapping("admin/article")
@Controller
public class ArticleController {

	public static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);
	
	@Autowired
	private ArticleService articleService;
	
	/**
	 * 新增文章
	 * @param articleMain
	 * @param content
	 * @return
	 */
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public ResponseEntity<Void> insertArticle(ArticleMain articleMain,@RequestParam("articleContent")String content){
		try {
			if(articleService.insertArticle(articleMain,content)){
				return ResponseEntity.status(HttpStatus.CREATED).build(); //201 创建成功
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //400 没有创建成功
		} catch (Exception e) {
			LOGGER.info("新增文章数据异常,输出参数： articleMain = {} , content = {} ",articleMain,content);
			LOGGER.error("新增文章数据异常,异常信息:", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500 服务器错误
	}
	
	/**
	 * 获取全部文章主数据
	 * @return
	 */
	@RequestMapping(value="/query/all",method=RequestMethod.GET)
	public ResponseEntity<DataTablesResult<ArticleMain>> queryAllArticle(){
		try {
			DataTablesResult<ArticleMain> dtResult=articleService.queryAllArticle();
			return ResponseEntity.ok(dtResult); //200
		} catch (Exception e) {
			LOGGER.error("查询所有文章异常,异常信息:", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 条件分页查询文章数据
	 * @param dtParam
	 * @return
	 */
	@RequestMapping(value="/query",method=RequestMethod.GET)
	public ResponseEntity<DataTablesResult<ArticleMain>> queryArticle(DataTablesParam dtParam){
		try {
			DataTablesResult<ArticleMain> dtResult=articleService.queryArticle(dtParam);
			return ResponseEntity.ok(dtResult); //200
		} catch (Exception e) {
			LOGGER.info("查询文章异常,输出参数： dtParam = {} ",dtParam);
			LOGGER.error("查询文章异常,异常信息:", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); //500 服务器错误
	}
	
	public ResponseEntity<Map<String, Object>> getOne(){
		return null;
	}
	
	/**
	 * 根据id获取文章主数据和正文
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/query/{id}",method=RequestMethod.GET)
	public ResponseEntity<ArticleData> queryArticleDataById(@PathVariable("id")String id){
		try {
			ArticleData articleData=articleService.queryArticleDataById(id);
			if(articleData==null){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  //404没有查询到数据
			}
			return ResponseEntity.ok(articleData);
		} catch (Exception e) {
			LOGGER.error("查询文章主数据和正文异常,异常信息:", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 更新文章主数据
	 * @param id
	 * @param articleMain
	 * @return
	 */
	@RequestMapping(value="/update/{id}",method=RequestMethod.PATCH)
	public ResponseEntity<Void> updateArticleMainById(@PathVariable("id")String id,ArticleMain articleMain){
		try {
			articleService.updateArticleMainById(id,articleMain);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  //204
		} catch (Exception e) {
			LOGGER.error("查询文章异常,异常信息:", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500 服务器错误
	}
	
	/**
	 * 更新文章主数据和正文
	 * @param id
	 * @param content
	 * @param articleMain
	 * @return
	 */
	@RequestMapping(value="/update/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> updateArticleDataById(@PathVariable("id")String id,@RequestParam("articleContent")String content,ArticleMain articleMain){
		try {
			articleService.updateArticleDataById(id,content,articleMain);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			LOGGER.error("修改文章异常,异常信息:", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	/**
	 * 根据id删除文章
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteArticleById(@PathVariable("id")String id){
		try {
			articleService.deleteArticleById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  //204
		} catch (Exception e) {
			LOGGER.error("查询文章异常,异常信息:", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500 服务器错误
	}
}
