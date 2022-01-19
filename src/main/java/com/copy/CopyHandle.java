package com.copy;

import cn.hutool.core.util.StrUtil;
import com.perfree.commons.OptionCacheUtil;
import com.perfree.plugin.proxy.HtmlRenderProxy;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CopyHandle extends HtmlRenderProxy {

    @Override
    public Document editFrontDocument(Document document, HttpServletResponse response, HttpServletRequest request) {
        String js = "<script>document.addEventListener('copy',function(e){\n" +
                "                let clipboardData = e.clipboardData || window.clipboardData;\n" +
                "                if(!clipboardData) return ;\n" +
                "                let text = window.getSelection().toString();\n" +
                "                if(text){\n" +
                "                    e.preventDefault();\n" +
                "                    clipboardData.setData('text/plain', text + '\\r\\n{}')\n" +
                "                }\n" +
                "            })</script>";
        String text = OptionCacheUtil.getDefaultValue("PERFREE_COPY_TEXT", "");
        js = StrUtil.format(js,text);
        document.body().append(js);
        return document;
    }
}
