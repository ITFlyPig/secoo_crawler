package com.wangyuelin.app.service;

import com.wangyuelin.app.bean.Product;
import com.wangyuelin.app.dao.ProductDao;
import com.wangyuelin.app.service.itf.IProduct;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProduct {
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductDao productDao;

    @Override
    public void addAll(List<Product> products) {
        if (products == null || products.size() == 0){
            return;
        }
        for (Product product : products){
            if (TextUtils.isEmpty(product.getName())){
                continue;
            }
            List<Product> olds = productDao.setletByname(product.getName());
            if (olds == null || olds.size() == 0){
                logger.info("插入");
                productDao.insert(product);
            }else {
                logger.info("更新");
                product.setId(olds.get(0).getId());
                productDao.updateByPrimaryKey(product);
            }
            logger.info(product.toString());

        }

    }

    @Override
    public void updateSpecial(String proId, String specialStatus) {
        if (TextUtils.isEmpty(proId)){
            return;
        }
        logger.info("id:" + proId + " 特例品的状态：" + specialStatus);
        productDao.updateSpecialStatus(proId, specialStatus);
    }


}
