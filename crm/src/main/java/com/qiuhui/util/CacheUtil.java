package com.qiuhui.util;

import java.io.Serializable;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public class CacheUtil {

	private static CacheManager cacheManager = new CacheManager();
	private Ehcache cache;
	private String cacheName;
	
	
	public CacheUtil(String cacheName){
		this.cacheName = cacheName;
		
	}
	
	
	public Ehcache getEhcache(){
		if(cache == null){
			cache = cacheManager.getEhcache(cacheName);
		}
		return cache;
	}
	
	/**
	 * 
	 * 根据Object的key，value将对象放进缓存
	 * @param key
	 * @param value
	 */
	public void setCache(Object key,Object value){
		Element element = new Element(key,value);
		getEhcache().put(element);
	}
	
	/**
	 * 根据Serializable的key，value将对象放入缓存
	 * @param key
	 * @param value
	 */
	public void setCache(Serializable key,Serializable value){
		Element element = new Element(key,value);
		getEhcache().put(element);
	}
	
	
	/**
	 * 
	 * 根据Object key 从缓存中查找出存入的对象返回
	 * @param key
	 * @return
	 */
	public Object getCache(Object key){
		Element element = getEhcache().get(key);
		return element == null ? null :element.getObjectValue();
	}
	
	/**根据Serializable key从缓存中查找出存入的对象返回
	 * @param key
	 * @return
	 */
	public Object getCache(Serializable key){
		Element element = getEhcache().get(key);
		return element == null ? null : element.getObjectValue();
	}
	
	/**
	 * 
	 * 根据传过来的Object key 删除缓存中相应的数据
	 * @param key
	 */
	public void delCacheByKey(Object key){
		getEhcache().remove(key);
	}
	
	/**
	 * 
	 * 根据传过来的Object key 删除缓存中相应的数据
	 * @param key
	 */
	public void delCacheByKey(Serializable key){
		getEhcache().remove(key);
		
	}
	
	
	/**
	 * 清空所有缓存
	 */
	public void removeAll(){
		getEhcache().removeAll();
	}
}
