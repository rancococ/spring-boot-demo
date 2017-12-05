package com.catvgd.springbootdemo.common.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * HTML工具
 * 
 * @author zhuyong
 * @date 2016年12月22日
 */
public class HTMLUtil {

    /**
     * 从文本内容中提取Tag的属性值
     * 
     * @param tagName
     * @param attrName
     * @param htmlFrag
     * @return
     */
    public static String[] getAttrFromTag(String tagName, String attrName, String htmlFrag) {
        String[] attrValues = new String[0];
        if (StringUtil.isEmpty(tagName) || StringUtil.isEmpty(attrName) || StringUtil.isEmpty(htmlFrag)) {
            return attrValues;
        }
        String html = htmlFrag;
        Document doc = Jsoup.parse(html);
        Elements tagElems = doc.select(tagName);
        List<String> attrValueList = new ArrayList<String>();
        for (Element elem : tagElems) {
            attrValueList.add(elem.attr(attrName));
        }
        attrValues = attrValueList.toArray(new String[attrValueList.size()]);
        return attrValues;
    }
}
