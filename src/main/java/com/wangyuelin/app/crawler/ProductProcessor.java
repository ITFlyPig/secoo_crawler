package com.wangyuelin.app.crawler;

import com.wangyuelin.app.bean.Category;
import com.wangyuelin.app.bean.Product;
import com.wangyuelin.app.crawler.page.CategoryPage;
import com.wangyuelin.app.crawler.page.DetailPage;
import com.wangyuelin.app.crawler.page.ProductListPage;
import com.wangyuelin.app.service.CategoryService;
import com.wangyuelin.app.service.ProductService;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

@Component
public class ProductProcessor implements PageProcessor {
    private final Logger logger = LoggerFactory.getLogger(ProductProcessor.class);
    private Site site = Site.me().setRetryTimes(3).setSleepTime(0)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    @Autowired
    private CategoryPage categoryPage;

    @Autowired
    private ProductListPage productListPage;

    @Autowired
    private DetailPage detailPage;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Override
    public void process(Page page) {

        String url = page.getUrl().get();
        if (TextUtils.isEmpty(url)){
            return;
        }
        logger.info("开始处理网页，url为：" + url);
        if (url.startsWith(CategoryPage.PAGE_URL)){//交给分类页解析器
            List<Category> categorys = categoryPage.parse(page);
            categoryService.addAll(categorys);
        }else if (url.startsWith(ProductListPage.PRE_FUIX)){//交给列表页解析器
            productListPage.parse(page);
        }else if (url.startsWith(DetailPage.DETAIL_PREFIX)){//交给详情页解析
            List<Product> products = detailPage.parse(page);
            //将商品插入数据库
            productService.addAll(products);
        }else if (url.startsWith(DetailPage.SPECIAL_URL)){//处理特例品的js查询结果
            String[] specailInfo = detailPage.handleSpecial(page.getRawText());
            if (specailInfo != null && specailInfo.length == 2){
                productService.updateSpecial(specailInfo[0], specailInfo[1]);

            }
        }



    }

    @Override
    public Site getSite() {
        return site;
    }
}
