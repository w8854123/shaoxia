package com.shaoxia.pojo;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 多媒体资源表
 * @author wu885
 *
 */
@Table(name="shaoxia_media")
public class Media {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String resourceUrl;
	
	private String resourcePath;
	
	private String resourceName;
	
	private String resourceSuffix;
	
	private String storageLocation;
	
	private String cloudServer;
	
	private String resourceType;
	
	private String qiniuKey;
	//状态：-1：删除
	private Integer status;
	
	private Date created;
	
	private Date updated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceSuffix() {
		return resourceSuffix;
	}

	public void setResourceSuffix(String resourceSuffix) {
		this.resourceSuffix = resourceSuffix;
	}

	public String getStorageLocation() {
		return storageLocation;
	}

	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}

	public String getCloudServer() {
		return cloudServer;
	}

	public void setCloudServer(String cloudServer) {
		this.cloudServer = cloudServer;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	public String getQiniuKey() {
		return qiniuKey;
	}

	public void setQiniuKey(String qiniuKey) {
		this.qiniuKey = qiniuKey;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
}
