package com.wangyuelin.app.crawler.page.itf;

import com.wangyuelin.app.bean.Category;
import us.codecraft.webmagic.Page;

import java.util.List;

/**
 * 页面操作的基本行为
 */
public interface IPage<T> {
    String getUrl();//获得页面的对应的url
    List<T> parse(Page page);//解析页面得到想要的数据
}
