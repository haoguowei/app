package com.hao.app.service;

import java.io.InputStream;


/**
 * Created by haoguowei.
 * Time 2017/11/30 23:29
 * Desc
 */
public interface ReadExcelService {

    /**
     *
     * @param str
     * @return
     */
    String genNumb(String str);

    /**
     *
     * @param input
     * @param fileExt
     * @throws Exception
     */
    void readExcel(InputStream input, String fileExt) throws Exception;
}
