package com.shaoxia.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shaoxia.bean.DataTablesParam;
import com.shaoxia.bean.DataTablesResult;
import com.shaoxia.mapper.ArticleCommentMapper;
import com.shaoxia.mapper.ArticleContentMapper;
import com.shaoxia.mapper.ArticleMainMapper;
import com.shaoxia.pojo.ArticleComment;
import com.shaoxia.pojo.ArticleContent;
import com.shaoxia.pojo.ArticleMain;
import com.shaoxia.threadlocal.UserThreadLocal;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class ArticleService {

	public static final Logger LOGGER = LoggerFactory.getLogger(ArticleService.class);

	@Autowired
	private ArticleCommentMapper articleCommentMapper;
	@Autowired
	private ArticleContentMapper articleContentMapper;
	@Autowired
	private ArticleMainMapper articleMainMapper;

	public boolean insertArticle(ArticleMain articleMain, String content) throws Exception {
		if (articleMain != null) {
			String uuid = UUID.randomUUID().toString();
			Date time = new Date();
			articleMain.setArticleId(uuid);
			articleMain.setCreated(time);
			articleMain.setUpdated(time);
			articleMain.setArticleAuthor(UserThreadLocal.get().getNickname());

			ArticleContent articleContent = new ArticleContent();
			articleContent.setArticleId(uuid);
			articleContent.setArticleContent(content);

			articleMainMapper.insertSelective(articleMain);
			articleContentMapper.insert(articleContent);
			return true;
		}
		LOGGER.info("没有-ArticleMain-数据！");
		return false;
	}

	/**
	 * 条件分页查询数据并排序
	 * 
	 * @param dtParam
	 * @return
	 */
	public DataTablesResult<ArticleMain> queryArticle(DataTablesParam dtParam) {
		// 计算当前页码
		int pageSize = dtParam.getLength();
		int pageNum = dtParam.getStart() / pageSize + 1;
		// 设置分页
		PageHelper.startPage(pageNum, pageSize);
		// 自定义条件查询
		Example example = new Example(ArticleMain.class);
		if (dtParam.getOrder() != null && !"".equals(dtParam.getOrder())) {
			// 排序
			example.setOrderByClause(dtParam.getOrder());
		}
		Criteria criteria = null;
		if (dtParam.getSearch() != null && !"".equals(dtParam.getSearch())) {
			// 设置查询条件
			criteria = example.createCriteria();
			criteria.andCondition(dtParam.getSearch());
		}
		List<ArticleMain> list = articleMainMapper.selectByExample(example);
		PageInfo<ArticleMain> pageInfo = new PageInfo<ArticleMain>(list);
		// 封装返回值
		DataTablesResult<ArticleMain> dtResult = new DataTablesResult<ArticleMain>();
		dtResult.setDraw(dtParam.getDraw());
		dtResult.setRecordsTotal((long) articleMainMapper.selectCount(null));
		dtResult.setRecordsFiltered(pageInfo.getTotal());

		dtResult.setData(pageInfo.getList());

		return dtResult;
	}

	/**
	 * 根据id更新文章主数据
	 * @param id
	 * @param articleMain
	 * @throws Exception 
	 */
	public void updateArticleMainById(String id, ArticleMain articleMain) throws Exception {
		if(articleMain!=null){
			articleMain.setArticleId(id);
			articleMainMapper.updateByPrimaryKeySelective(articleMain);
		}else{
			throw new Exception("articleMain不存在！");
		}
	}

	/**
	 * 根据id删除文章
	 * @param id
	 */
	public void deleteArticleById(String id) {
		
		ArticleContent articleContent=new ArticleContent();
		articleContent.setArticleId(id);
		ArticleComment articleComment=new ArticleComment();
		articleComment.setArticleId(id);
		
		articleMainMapper.deleteByPrimaryKey(id);  //删除主数据
		articleContentMapper.delete(articleContent); //删除文章正文
		articleCommentMapper.delete(articleComment); //删除文章评论
		
	}
}
