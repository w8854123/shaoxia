package com.shaoxia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaoxia.bean.ListOption;
import com.shaoxia.mapper.OptionsMapper;

@Service
public class OptionsService {

	@Autowired
	private OptionsMapper optionsMapper;
	
	/**
	 * 批量插入设置选项
	 * @param listOption
	 */
	public void saveBatchOptions(ListOption listOption) {
		optionsMapper.insertBatchOptions(listOption.getListOption());
	}

	/**
	 * 批量更新设置选项
	 * @param listOption
	 */
	public void updateOptionsById(ListOption listOption) {
		optionsMapper.updateBatchOptionsByIdSelective(listOption.getListOption());
	}
}
