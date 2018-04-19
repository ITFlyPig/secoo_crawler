package com.wangyuelin.app.crawler.page;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.wangyuelin.app.bean.Product;
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
public class DetailPage implements IPage<Product> {
    private final Logger logger = LoggerFactory.getLogger(DetailPage.class);
    public static final String DETAIL_PREFIX = "http://item.secoo.com/";//详情页前缀

    public static final String SPECIAL_URL = "http://lr.secooimg.com/special?productId=";//查询是否是特例品的url


    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public List<Product> parse(Page page) {

        Product product = getProduct(page);
        if (product != null){
            ArrayList<Product> list = new ArrayList<Product>(1);
            list.add(product);
            return list;
        }
        return null;

    }


    /**
     * 解析得到商品的信息
     * @param page
     * @return
     */
    private Product getProduct(Page page) {
        Product product = new Product();
        Document doc = page.getHtml().getDocument();
        //名称
        Element nameL = getOne(doc, "div.proName > h2");
        if (nameL != null){
            product.setName(nameL.text());
        }
        //价钱
        Element priceL = getOne(doc, "strong#secooPriceJs");
        if (priceL != null){
            String priceStr = priceL.text();
            if (!TextUtils.isEmpty(priceStr)){
                priceStr = priceStr.replaceAll(",", "");
                product.setPrice(Float.valueOf(priceStr));
            }
        }


        //是否是特例品，需要查询才知道
        String productId = getProductId(page.getUrl().get());
        if (!TextUtils.isEmpty(productId)){
            String specialUrl = SPECIAL_URL + productId;
            logger.info("将js请求添加：" + specialUrl);
            page.addTargetRequest(specialUrl);
        }

        product.setProductId(productId);


        //url
        product.setUrl(page.getUrl().get());



        logger.info("解析得到的商品：" + product);
        //折扣信息，暂时没有
        return product;
    }


    private Element getOne(Document doc, String select) {
        if (doc == null || TextUtils.isEmpty(select)) {
            return null;
        }
        Elements elements = doc.select(select);
        if (elements == null || elements.size() == 0) {
            return null;
        }
        return elements.first();

    }

    private Elements getAll(Document doc, String select) {
        if (doc == null || TextUtils.isEmpty(select)) {
            return null;
        }
        Elements elements = doc.select(select);
        return elements;

    }

    /**
     * 据url解析得到商品的id
     * @param url
     * @return
     */
    private String getProductId(String url){
        if (TextUtils.isEmpty(url)){
            return null;
        }
        String left = url.replaceAll("http://item.secoo.com/", "");
        return left.substring(0, left.indexOf("."));
    }

    /**
     * 处理是否是特例品
     * @param json
     */
    public String[] handleSpecial(String json){
        if (TextUtils.isEmpty(json)){
            return null;
        }

        String[] res = new String[2];
        logger.info(json);
        ReadContext context = JsonPath.parse(json);
        List<String> specialStrs = context.read("$..t");//2：本商品为特例品,不参与会员折扣  3：本商品为特例品,不支持使用优惠券 2,3/3,2:本商品为特例品,不支持优惠券和不参与会员折扣
        if (specialStrs != null && specialStrs.size() >0){
            res[1] = specialStrs.get(0);
        }
//        //商品id
        List<String> proIs = context.read("$..p");
        if (proIs != null && proIs.size() >0){
            res[0] = proIs.get(0);
        }
        logger.info( res.toString());
        return res;

    }

}
