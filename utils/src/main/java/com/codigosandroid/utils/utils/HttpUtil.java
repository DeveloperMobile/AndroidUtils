package com.codigosandroid.utils.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by Tiago on 17/11/2017.
 */

public class HttpUtil {

    private final String TAG = HttpUtil.class.getSimpleName();

    private final int TIMEOUT_MILLIS = 15000;
    private boolean LOG_ON = false;
    private String contentType;
    private String charsetToEncode;

    public String doGet(String url) throws Exception {
        return doGet(url, null, "UTF-8");
    }

    public String doGet(String url, Map<String, String> params, String charset) throws Exception {
        String queryString = getQueryString(params);

        if (queryString != null && queryString.trim().length() > 0) {
            url += "?" + queryString;
        }

        if (LOG_ON) {
            LogUtil.debug(TAG, "Http.doGet: " + url);
        }

        URL u = new URL(url);
        HttpURLConnection conn = null;
        String s = null;

        try {
            conn = (HttpURLConnection) u.openConnection();

            if (contentType != null) {
                conn.setRequestProperty("Content-Type", contentType);
            }

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(TIMEOUT_MILLIS);
            conn.setReadTimeout(TIMEOUT_MILLIS);
            conn.connect();

            InputStream in = null;

            int status = conn.getResponseCode();

            if (status >= HttpURLConnection.HTTP_BAD_REQUEST) {
                LogUtil.error(TAG, "Error code: " + status);
                in = conn.getErrorStream();
            } else {
                in = conn.getInputStream();
            }

            s = IOUtil.toString(in, charset);

            if (LOG_ON) {
                LogUtil.debug(TAG, "<< Http.doGet: " + s);
            }

            in.close();
        } catch (Exception e) {
            LogUtil.error(TAG, e.getMessage(), e);
        } finally {
            if (conn != null) { conn.disconnect(); }
        }
        return s;
    }

    public String doDelete(String url) throws Exception {
        return doDelete(url, null, "UTF-8");
    }

    public String doDelete(String url, Map<String, String> params, String charset) throws Exception {
        String queryString = getQueryString(params);

        if (queryString != null && queryString.trim().length() > 0) {
            url += "?" + queryString;
        }

        if (LOG_ON) {
            LogUtil.debug(TAG, ">> Http.doDelete: " + url);
        }

        URL u = new URL(url);
        HttpURLConnection conn = null;
        String s = null;

        try {
            conn = (HttpURLConnection) u.openConnection();

            if (contentType != null) {
                conn.setRequestProperty("Content-Type", contentType);
            }

            conn.setRequestMethod("DELETE");
            conn.setConnectTimeout(TIMEOUT_MILLIS);
            conn.setReadTimeout(TIMEOUT_MILLIS);
            conn.connect();

            InputStream in = conn.getInputStream();
            s = IOUtil.toString(in, charset);

            if (LOG_ON) {
                LogUtil.debug(TAG, "<< Http.doGet: " + s);
            }

            in.close();
        } catch (Exception e) {
            LogUtil.error(TAG, e.getMessage(), e);
        } finally {
            if (conn != null) { conn.disconnect(); }
        }
        return s;
    }

    public String doPost(String url, Map<String, String> params, String charset) throws Exception {
        String queryString = getQueryString(params);
        byte[] bytes = params != null ? queryString.getBytes(charset) : null;

        if (LOG_ON) {
            LogUtil.debug(TAG, "Http.doPost: " + url + "?" + params);
        }

        return doPost(url, params, charset);
    }

    public String doPost(String url, byte[] params, String charset) throws Exception {
        if (LOG_ON) {
            LogUtil.info(TAG, ">> Http.doPost: " + url);
        }

        URL u = new URL(url);
        HttpURLConnection conn = null;
        String s = null;

        try {
            conn = (HttpURLConnection) u.openConnection();

            if (contentType != null) {
                conn.setRequestProperty("Content-Type", contentType);
            }

            conn.setRequestMethod("POST");
            conn.setConnectTimeout(TIMEOUT_MILLIS);
            conn.setReadTimeout(TIMEOUT_MILLIS);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();

            if (params != null) {
                OutputStream out = conn.getOutputStream();
                out.write(params);
                out.flush();
                out.close();
            }

            InputStream in = null;
            int status = conn.getResponseCode();

            if (status >= HttpURLConnection.HTTP_BAD_REQUEST) {
                LogUtil.error(TAG, "Error code: " + status);
                in = conn.getErrorStream();
            } else {
                in = conn.getInputStream();
            }

            s = IOUtil.toString(in, charset);

            if (LOG_ON) {
                LogUtil.info(TAG, "<< Http.doPost: " + s);
            }
        } catch (Exception e) {
            LogUtil.error(TAG, e.getMessage(), e);
        } finally {
            if (conn != null) { conn.disconnect(); }
        }
        return s;
    }

    // Retorna a QueryString para GET
    public String getQueryString(Map<String, String> params) throws Exception {
        if (params == null || params.size() == 0) {
            return null;
        }

        String urlParams = null;

        for (String chave : params.keySet()) {
            Object objValor = params.get(chave);

            if (objValor != null) {
                String valor = objValor.toString();

                if (charsetToEncode != null) {
                    valor = URLEncoder.encode(valor, charsetToEncode);
                }

                urlParams = urlParams == null ? "" : urlParams + "&";
                urlParams += chave + "=" + valor;
            }
        }
        return urlParams;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCharsetToEncode() {
        return charsetToEncode;
    }

    public void setCharsetToEncode(String charsetToEncode) {
        this.charsetToEncode = charsetToEncode;
    }

}
