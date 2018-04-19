package com.wangyuelin.app.service.itf;

import com.wangyuelin.app.bean.Product;

import java.util.List;

public interface IProduct {
    void addAll(List<Product> products);
    void updateSpecial(String proId, String specialStatus);
}
