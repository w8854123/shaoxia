package com.shaoxia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.shaoxia.bean.ListOption;
import com.shaoxia.cache.CacheManager;
import com.shaoxia.mapper.OptionsMapper;
import com.shaoxia.pojo.Options;

@Service
public class OptionsService {

	@Value("${CACHE_KEY_OPTIONS}")
	private String CACHE_KEY_OPTIONS;
	
	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	private OptionsMapper optionsMapper;
	
	/**
	 * 批量插入设置选项
	 * @param listOption
	 */
	public void saveBatchOptions(ListOption listOption) {
		//清空缓存
		cacheManager.clearCache();
		optionsMapper.insertBatchOptions(listOption.getListOption());
	}

	/**
	 * 批量更新设置选项
	 * @param listOption
	 */
	public void updateOptionsById(ListOption listOption) {
		//清空缓存
		cacheManager.clearCache();
		optionsMapper.updateBatchOptionsByIdSelective(listOption.getListOption());
	}

	/**
	 * 查询所有设置选项
	 * @return
	 */
	public List<Options> queryAll() {
		//读取缓存中的数据
		List<Options> result=(List<Options>)cacheManager.getValueByKey(CACHE_KEY_OPTIONS);
		//缓存未命中
		if(result==null || result.size()==0){
			result=optionsMapper.selectAll();
			//放入缓存
			cacheManager.putCache(CACHE_KEY_OPTIONS, result);
		}
		return result;
	}
	
}
