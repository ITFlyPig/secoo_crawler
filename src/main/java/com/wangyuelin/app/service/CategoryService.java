package com.wangyuelin.app.service;

import com.wangyuelin.app.bean.Category;
import com.wangyuelin.app.dao.CategoryDao;
import com.wangyuelin.app.service.itf.ICategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategory {
    private final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public void addAll(List<Category> categories) {
        logger.info("开始插入分类");
        if (categories == null || categories.size()  == 0){
            return;
        }
        for (Category category : categories){
            List<Category> olds = categoryDao.selectByName(category.getName());
            if (olds == null || olds.size() == 0){
                categoryDao.insert(category);
                logger.info("插入");
            }else {
                category.setId(olds.get(0).getId());
                categoryDao.updateByPrimaryKey(category);
                logger.info("更新");
            }
            logger.info(category.toString());



        }

    }
}
