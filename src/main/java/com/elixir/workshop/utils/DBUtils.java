package com.elixir.workshop.utils;

import com.elixir.workshop.beans.Root;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

public class DBUtils {

    public static void initRootData(Object o) {
        if (o instanceof Root) {
            if (StringUtils.isNotBlank(((Root) o).getCreatedBy()))
                ((Root) o).setCreatedBy(UserInfoUtil.getCurrentUser().getUserId());
            if (((Root) o).getCreatedDate() != null)
                ((Root) o).setCreatedDate(new Date());
            ((Root) o).setUpdatedBy(UserInfoUtil.getCurrentUser().getUserId());
            ((Root) o).setUpdatedDate(new Date());
        }
    }
}
