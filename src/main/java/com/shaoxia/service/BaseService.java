package com.shaoxia.service;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

public abstract class BaseService<T> {
    
    /**
     * 泛型注入（spring4的新特性）
     */
    @Autowired
    private Mapper<T> mapper;
    
    private Class<T> clazz;
    
    public Mapper<T> getMapper(){
        return this.mapper;
    }
    
    public BaseService() {
        super();
        Type type=this.getClass().getGenericSuperclass();
        ParameterizedType ptype=(ParameterizedType)type;
        this.clazz=(Class<T>)ptype.getActualTypeArguments()[0];
    }



    /**
     * 根据ID查找
     * @param id
     * @return
     */
    public T queryByID(Long id){
        return this.getMapper().selectByPrimaryKey(id);
    }
    
    /**
     * 查询所有数据
     * @return
     */
    public List<T> queryAll(){
        return this.getMapper().selectAll();
    }
    
    /**
     * 根据条件查询
     * @param t
     * @return
     */
    public List<T> queryListByWhere(T t){
        return this.getMapper().select(t);
    }
    
    /**
     * 查询数据总条数
     * @return
     */
    public Integer queryCount(){
        return this.getMapper().selectCount(null);
    }

    /**
     * 根据条件分页查询
     * @param t
     * @param page
     * @param rows
     * @return
     */
    public PageInfo<T> queryPageByWhere(T t,Integer page,Integer rows){
        // 第一个参数是起始页，第二个参数是，页面显示的数据条数
        PageHelper.startPage(page,rows);
        List<T> list=this.getMapper().select(t);
        return new PageInfo<T>(list);
    }

    /**
     * 条件排序分页查询
     * @param t 实体
     * @param page 页码
     * @param rows 每页多少条
     * @param order 排序字段
     * @param flag false：全字段精确查询；true：全字段模糊查询
     * @return
     * @throws Exception
     */
    public PageInfo<T> queryPageListByExample(T t,Integer page,Integer rows,String order,boolean flag) throws Exception{
        PageHelper.startPage(page,rows);
        Example example=new Example(this.clazz);
        example.setOrderByClause(order);
        List<T> list;
        if(t==null){ //条件为空，只排序
            list=this.getMapper().selectByExample(example); 
            return new PageInfo<T>(list);
        }
        Criteria criteria=example.createCriteria();
        //利用反射获取查询条件
        Field[] fields=t.getClass().getDeclaredFields();
        for(Field field:fields){
        	//设置为true，可以获取声明的私有字段的值
            field.setAccessible(true);
            if(field.get(t)!=null){
            	if(flag){
            		criteria.andLike(field.getName(), "%"+field.get(t)+"%");
            	}else{
            		criteria.andEqualTo(field.getName(), field.get(t));
            	}
            }
        }
        list=this.getMapper().selectByExample(example);
        return new PageInfo<T>(list);
    }
    
    /**
     * 条件排序分页查询
     * @param t 实体
     * @param page 页码
     * @param rows 每页多少条
     * @param order 排序字段
     * @param exactFields 设置精确查询的字段,多字段用英文逗号隔开
     * @return
     * @throws Exception
     */
    public PageInfo<T> queryPageListByExample(T t,Integer page,Integer rows,String order,String exactFields) throws Exception{
    	String[] exactField=null;
    	if(StringUtils.isNotBlank(exactFields)){
    		exactField=exactFields.split(",");
    	}
    	
        PageHelper.startPage(page,rows);
        Example example=new Example(this.clazz);
        example.setOrderByClause(order);
        List<T> list;
        if(t==null){ //条件为空，只排序
            list=this.getMapper().selectByExample(example); 
            return new PageInfo<T>(list);
        }
        Criteria criteria=example.createCriteria();
        //利用反射获取查询条件
        Field[] fields=t.getClass().getDeclaredFields();
        
        boolean flag=true;
        for(int i=0;i<fields.length;i++){
        	flag=true;
        	//设置为true，可以获取声明的私有字段的值
        	fields[i].setAccessible(true);
        	
            if(fields[i].get(t)!=null){
            	if(exactField!=null){
            		for(int n=0;n<exactField.length;n++){
            			if(fields[i].getName().equals(exactField[n])){
            				criteria.andEqualTo(fields[i].getName(), fields[i].get(t));
            				flag=false;
            				break;
            			}
            		}
            	}
            	
            	if(flag){
            		criteria.andLike(fields[i].getName(), "%"+fields[i].get(t)+"%");
            	}
            }
        }
        
        list=this.getMapper().selectByExample(example);
        return new PageInfo<T>(list);
    }
    
    /**
     * 自定义条件查询
     * @param page
     * @param rows
     * @return
     */
    public List<T> queryListByExample(Example example){
        List<T> list=this.getMapper().selectByExample(example);
        return list;
    }
    
    /**
     * 根据条件查询一个
     * @param t
     * @return
     */
    public T queryOne(T t){
        return this.getMapper().selectOne(t);
    }
    
    /**
     * 保存
     */
    public void save(T t){
        this.getMapper().insert(t);
    }
    
    /**
     * 保存忽略空
     */
    public void saveSelective(T t){
        this.getMapper().insertSelective(t);
    }
    
    /**
     * 更新
     * @param t
     */
    public void updateById(T t){
        this.getMapper().updateByPrimaryKey(t);
    }
    
    /**
     * 更新忽略空
     * @param t
     */
    public void updateByIdSelective(T t){
        this.getMapper().updateByPrimaryKeySelective(t);
    }
    
    /**
     * 根据id删除
     * @param id
     */
    public void deleteById(Long id){
        this.getMapper().deleteByPrimaryKey(id);
    }
    
    /**
     * 根据ids批量删除
     * @param ids
     */
    public void deleteByIds(List<Object> ids){
        //设置条件
        Example example=new Example(clazz);
        example.createCriteria().andIn("id", ids);
        //根据条件删除
        this.getMapper().deleteByExample(example);
    }
    
    
}
