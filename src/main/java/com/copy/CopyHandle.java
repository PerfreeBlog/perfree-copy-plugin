package com.copy;

import com.perfree.cache.OptionCacheService;
import com.perfree.plugin.proxy.HtmlRenderProxy;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dromara.hutool.core.text.StrUtil;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
public class CopyHandle extends HtmlRenderProxy {

    @Resource
    private OptionCacheService optionCacheService;

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
        String text = optionCacheService.getDefaultValue("PERFREE_COPY_TEXT", "plugin_perfree-copy-plugin","");
        js = StrUtil.format(js,text);
        document.body().append(js);
        return document;
    }
}
