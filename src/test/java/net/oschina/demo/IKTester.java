package net.oschina.demo;


import net.oschina.common.search.SearchHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * 测试 IK 分词器
 * User: Winter Lau
 * Date: 13-1-10
 * Time: 上午11:48
 */
public class IKTester {

    @Test
    public void test_highlight() throws Exception {
        String key = "sql server";
        String text = "SQL server 是最好的 数据库 应用服务器";
        Assert.assertEquals("<span class=\"highlight\">SQL</span> <span class=\"highlight\">server</span> 是最好的 数据库 应用服务器", SearchHelper.highlight(text, key));

        key = "开源社区";
        text = "开源中国，成立于2008年8月，是目前国内最大的开源技术社区，拥有超过200万会员，形成了由开源软件库、代码分享、资讯、协作翻译、讨论区和博客等几大频道内容。";
        String expected = "<span class=\"highlight\">开源</span>中国，成立于2008年8月，是目前国内最大的<span class=\"highlight\">开源</span>技术<span class=\"highlight\">社区</span>，拥有超过200万会员，形成了由<span class=\"highlight\">开源</span>软件库、代码分享、资讯、协作翻译、讨论区和博客等几大频道内容。";
        Assert.assertEquals(expected, SearchHelper.highlight(text, key));

        key = "社区开源";
        Assert.assertEquals(expected, SearchHelper.highlight(text, key));

        key = "开源社区";
        text = "开源中国";
        Assert.assertEquals("开源中国", SearchHelper.highlight(text, key));

        key = "中国";
        text = "开源中国";
        Assert.assertEquals("开源<span class=\"highlight\">中国</span>", SearchHelper.highlight(text, key));
    }

    @Test
    public void test_split() throws Exception {
        String text = "android 刷机";
        long ct = System.currentTimeMillis();
        List<String> stopWords = SearchHelper.splitKeywords(text);
        Assert.assertEquals("android", stopWords.get(0));
        Assert.assertEquals("刷机", stopWords.get(1));
        Assert.assertTrue((System.currentTimeMillis() - ct) < 1200);
    }


}
