package com.example;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.List;

/**
 * Created by tangzhilong on 17/4/18.
 */
@ConfigurationProperties(prefix = "mylog")
public class LogProperties {
    private String logrex;
    private List<String> logrexArr;
    public String getLogrex() {
        return logrex;
    }

    public void setLogrex(String logrex) {
        this.logrex = logrex;
        if (!StringUtils.isEmpty(logrex)) {
            logrexArr = Lists.newArrayList();
            Iterable<String> s = Splitter.on(",").trimResults().omitEmptyStrings().split(logrex);
            for(Iterator<String> it =s.iterator();it.hasNext();) {
                logrexArr.add(it.next());
            }
        }
    }

    public List<String> getLogrexArr() {
        return logrexArr;
    }

    public void setLogrexArr(List<String> logrexArr) {
        this.logrexArr = logrexArr;
    }
}
