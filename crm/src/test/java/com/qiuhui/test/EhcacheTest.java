package com.qiuhui.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.qiuhui.entity.Staff;
import com.qiuhui.service.StaffService;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public class EhcacheTest {

	StaffService staffService = new StaffService();
	
	@Test
	public void FindAllStaffsTest(){
		CacheManager ehcacheManager= new CacheManager();
		
		Ehcache ehcache = ehcacheManager.getEhcache("staff");
		
		Element ele = ehcache.get("staffList");
		if(ele == null){
			List<Staff> staffList = staffService.findAllStaffs();
			 ele = new Element("staffList",staffList);
			ehcache.put(ele);
			
		}
		System.out.println(System.getProperty("java.io.tmpdir"));
		Element element1 = ehcache.get("staffList");
		List<Staff> staList1 = (ArrayList<Staff>) element1.getObjectValue();
		Element element2 = ehcache.get("staffList");
		List<Staff> staList2 = (ArrayList<Staff>) element2.getObjectValue();
		
	}
	
	
	
}
