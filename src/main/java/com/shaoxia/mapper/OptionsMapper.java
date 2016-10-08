package com.shaoxia.mapper;

import java.util.List;

import com.shaoxia.pojo.Options;

import tk.mybatis.mapper.common.Mapper;

public interface OptionsMapper extends Mapper<Options>{

	/**
	 * 根据id批量更新非空的字段
	 * @param list
	 * @return
	 */
	int updateBatchOptionsByIdSelective(List<Options> list);
	
}
