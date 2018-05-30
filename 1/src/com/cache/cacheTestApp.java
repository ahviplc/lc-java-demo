package com.cache;

public class cacheTestApp {
	
	public static void main(String[] args) {
		CacheManager.setSimpleFlag("abcBoolean", true);
		System.out.println(CacheManager.getSimpleFlag("abcBoolean"));
		
		//CacheManager.clearOnly("abc");
		System.out.println(CacheManager.getCacheAllkey());
		
		Cache abc=new Cache();
		abc.setExpired(false);
		abc.setValue("我是缓存数据");
		abc.setTimeOut(2000);
		CacheManager.putCache("abc", abc);
		
		System.out.println(CacheManager.getCacheAllkey());
		
		System.out.println(CacheManager.getCacheInfo("abc").getValue());
		
		System.out.println("ok!2018-5-30 12:19:03!LC ");
		
		//CacheManager.putCacheInfo("abc", 我是缓存123, 1000);
		
		// CacheManager.putCache("def", new Cache());
		// CacheManager.putCache("ccc", new Cache());
		// CacheManager.clearOnly("");
		// Cache c = new Cache();
		// for (int i = 0; i < 10; i++) {
		// CacheManager.putCache("" + i, c);
		// }
		// CacheManager.putCache("aaaaaaaa", c);
		// CacheManager.putCache("abchcy;alskd", c);
		// CacheManager.putCache("cccccccc", c);
		// CacheManager.putCache("abcoqiwhcy", c);
		// System.out.println("删除前的大小："+CacheManager.getCacheSize());
		// CacheManager.getCacheAllkey();
		// CacheManager.clearAll("aaaa");
		// System.out.println("删除后的大小："+CacheManager.getCacheSize());
		// CacheManager.getCacheAllkey();
	}

}
