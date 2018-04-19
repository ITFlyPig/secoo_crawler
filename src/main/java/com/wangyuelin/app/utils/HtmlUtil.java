package com.wangyuelin.app.utils;

import org.apache.http.util.TextUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;


public class HtmlUtil {
    private static final XPath xpath;

    static {
        xpath = XPathFactory.newInstance().newXPath();
    }

    /**
     * 解析得到一个节点
     * @param str
     * @return
     */
    public static Element toElement(String str, String tag) {
        Element element = null;
        Elements elements = toElements(str, tag);
        if (elements == null || elements.size() <= 0){
            return element;
        }else {
            element = elements.first();
        }

        return element;

    }

    /**
     * 解析得到节点的集合
     * @param str
     * @return
     */
    public static Elements toElements(String str, String tag) {
        Elements elements = null;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(tag)) {
            return elements;
        }
        Document document = Jsoup.parse(str);
        if (document == null) {
            return elements;
        }

        Elements temps = document.getAllElements();
        if (temps == null || temps.size() <= 0){
            return elements;
        }
        elements = temps.first().getElementsByTag(tag);

        return elements;
    }




}
