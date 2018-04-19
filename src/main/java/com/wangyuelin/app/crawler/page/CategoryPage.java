package com.wangyuelin.app.crawler.page;

import com.wangyuelin.app.bean.Category;
import com.wangyuelin.app.crawler.page.itf.IPage;
import org.apache.http.util.TextUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析分类页面
 */
@Component
public class CategoryPage implements IPage<Category> {
    private final Logger logger = LoggerFactory.getLogger(DetailPage.class);

    public static final String PAGE_URL = "http://www.secoo.com/";//获取分类的url


    @Override
    public String getUrl() {
        return PAGE_URL;
    }

    @Override
    public List<Category> parse(Page page) {
        //获取所有分类（也就是a标签）
        Elements elements = page.getHtml().getDocument().select("div.nav-item > a");
        //解析a标签
        List<Category> categories = null;
        if (elements != null && elements.size() > 0){
            int size = elements.size();
             categories = new ArrayList<Category>(size);
            for (int i = 0; i < size; i++){
                Element a = elements.get(i);
                String name = a.text();
                if (TextUtils.isEmpty(name) || name.contains("首页")){//首页不需要
                    continue;
                }
                String url = a.attr("href");
                if (TextUtils.isEmpty(url)){
                    continue;
                }

                logger.info("添加分类的url：" + url);
                page.addTargetRequest(url);


                Category category = new Category();
                category.setName(name);
                category.setListUrl(url);
                categories.add(category);
            }
        }
        return categories;
    }


}
