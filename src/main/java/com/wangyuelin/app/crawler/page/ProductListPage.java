package com.wangyuelin.app.crawler.page;

import com.wangyuelin.app.crawler.page.itf.IPage;
import org.apache.http.util.TextUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductListPage implements IPage<Object> {
    private final Logger logger = LoggerFactory.getLogger(ProductListPage.class);

    public static final String PRE_FUIX = "http://list.secoo.com/";//列表页的前缀


    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public List<Object> parse(Page page) {
        String url = page.getUrl().get();
        if (TextUtils.isEmpty(url)){
            return null;
        }

       //循环解析出当前列表也每个商品详情的url
        List<String> detailUrls = parseDetailUrls(page);
        if (detailUrls != null){
            page.addTargetRequests(detailUrls);
        }

        addNextPage(page);

        return null;
    }

    /**
     * 获取页面的范围
     * @param doc
     * @return
     */
    private int[] getPageRange(Document doc){
        int[] range = null;
        Elements elements = doc.select("div.product_page > a");
        if (elements == null || elements.size() <= 0){
            return range;
        }

        int size = elements.size();
        if (size < 3){//最少有上一页 下一页 当前页
            return range;
        }
        int min = 0;
        int max = 0;
        int cur = 0;
        for (int i = 0; i < size; i++){
            Element a = elements.get(i);
            if (i == 1){//最小页码
                min = Integer.valueOf(a.text());
            }else if (i == size - 3){//最大页码
                max = Integer.valueOf(a.text());
            }
            String status = a.attr("class");
            if (!TextUtils.isEmpty(status) && status.equalsIgnoreCase("on")){//当前页面
                cur = Integer.valueOf(a.text());
            }
        }
        logger.info("页码的范围： min" + min + " cur:" + cur + " max:" + max);
        return new int[]{min, cur, max};

    }


    /**
     * 据前一个页面计算出下一个页面
     * @param pageUrl
     * @param nextPage
     * @return
     */
    private String getUrl(String pageUrl, int nextPage){
        if (TextUtils.isEmpty(pageUrl) || !pageUrl.startsWith(PRE_FUIX)){
            return null;
        }
        String[] strArray = pageUrl.split("-");
        if (strArray == null){
            return null;
        }

        StringBuffer buffer = new StringBuffer();
        int size = strArray.length;
        for (int i = 0; i < size; i++){
            if (i == 0){
                buffer.append(strArray[0]);
            }else{
                if (i == 8){
                    buffer.append("-").append(nextPage);
                }else {
                    buffer.append("-").append(strArray[i]);
                }
            }
        }

        return buffer.toString();
    }


    private static final String DETAIL_PREFUIX = "http://item.secoo.com/";//商品详情页的前缀
    private static final String DETAIL_SUFUIX = ".shtml?source=list";//商品详情页的后缀

    /**
     * 解析得到一页的商品详情链接
     * @param page
     * @return
     */
    private List<String> parseDetailUrls(Page page){
        Document doc = page.getHtml().getDocument();
        Elements elements = doc.select("div.commodity-list.clearfix > dl");
        logger.info("商品的数量");
        if (elements == null){
            return null;
        }
        ArrayList<String> urls = new ArrayList<String>();
        for (Element dl : elements){
            String productId = dl.attr("dlproid");
            logger.info("商品的id：" + productId);
            if (!TextUtils.isEmpty(productId)){
                String urlDetail = DETAIL_PREFUIX + productId + DETAIL_SUFUIX;
                urls.add(urlDetail);
                logger.info("商品详情链接：" + urlDetail);
            }
        }
        return urls;
    }

    /**
     * 添加下一个商品列表也
     * @param page
     */
    private void addNextPage(Page page){
        int[] range = getPageRange(page.getHtml().getDocument());
        if (range == null){
            return ;
        }
        int min = range[0];
        int cur = range[1];
        int max = range[2];
        if (cur >= min && cur < max){
            cur++;
            String listUrl = page.getUrl().get();
            String nextUrl = getUrl(listUrl, cur);
            if (!TextUtils.isEmpty(nextUrl)){
                page.addTargetRequest(nextUrl);
                logger.info("将下一个商品列表页面的的url添加：" + nextUrl);
            }
        }

    }

}
