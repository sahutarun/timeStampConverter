package helpers;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BaseApi {
    private RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    private String method;
    private Object body;
    private String contentType;
    private String baseUri;
    private Map<String, Object> pathParams = new HashMap();
    private Map<String, Object> queryParams = new HashMap();
    private Map<String, Object> formURLEncoded = new HashMap();
    private Map<String, Object> params = new HashMap();
    private String basePath;
    private String cookie;
    private Map<String, Object> headers = new HashMap();
    private Response response;
    private Boolean redirectFlag = true;

    public BaseApi() {
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setBody(Object obj) {
        this.body = obj;
        this.requestSpecBuilder.setBody(obj);
    }

    public void setBody(byte[] obj) {
        this.body = obj;
        this.requestSpecBuilder.setBody(obj);
    }

    public void setBasicAuth(String userName, String password) {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName(userName);
        authScheme.setPassword(password);
        this.requestSpecBuilder.setAuth(authScheme);
    }

    public Object getBody() {
        return this.body;
    }
    public void setContentType(String contentType) {
        this.contentType = contentType;
        this.requestSpecBuilder.setContentType(contentType);
    }

    public String getContentType() {
        return this.contentType;
    }
    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
        this.requestSpecBuilder.setBaseUri(baseUri);
    }

    public String getBaseUri() {
        return this.baseUri;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
        this.requestSpecBuilder.addCookie(cookie);
    }

    public String getCookie() {
        return this.cookie;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
        this.requestSpecBuilder.setBasePath(basePath);
    }

    public String getBasePath() {
        return this.basePath;
    }

    public void addHeaders(Map<String, String> headers) {
        this.headers.putAll(headers);
        this.requestSpecBuilder.addHeaders(headers);
    }

    public void addHeader(String headerKey, String headerValue) {
        this.headers.put(headerKey, headerValue);
        this.requestSpecBuilder.addHeader(headerKey, headerValue);
    }

    public Map<String, Object> getHeaders() {
        return this.headers;
    }

    public void addQueryParam(String paramKey, Object paramValue) {
        this.queryParams.put(paramKey, paramValue);
        this.requestSpecBuilder.addQueryParam(paramKey, new Object[]{paramValue});
    }

    public void addQueryParams(Map<String, String> queryParams) {
        this.queryParams.putAll(queryParams);
        this.requestSpecBuilder.addQueryParams(queryParams);
    }

    public Map<String, Object> getQueryParams() {
        return this.queryParams;
    }

    public void addPathParam(String paramKey, Object paramValue) {
        this.pathParams.put(paramKey, paramValue);
        this.requestSpecBuilder.addPathParam(paramKey, paramValue);
    }

    public void addPathParams(Map<String, String> pathParams) {
        this.pathParams.putAll(pathParams);
        this.requestSpecBuilder.addPathParams(pathParams);
    }

    public Map<String, Object> getPathParams() {
        return this.pathParams;
    }

    public void addFormURLEncoded(String paramKey, Object paramValue) {
        this.formURLEncoded.put(paramKey, paramValue);
        this.requestSpecBuilder.addFormParam(paramKey, new Object[]{paramValue});
    }

    public void addFormURLEncoded(Map<String, Object> formURLEncoded) {
        this.formURLEncoded.putAll(formURLEncoded);
        this.requestSpecBuilder.addFormParams(formURLEncoded);
    }

    public Map<String, Object> getFormURLEncoded() {
        return this.formURLEncoded;
    }

    public void addParam(String paramKey, Object paramValue) {
        this.params.put(paramKey, paramValue);
        this.requestSpecBuilder.addParam(paramKey, new Object[]{paramValue});
    }

    public void addParams(Map<String, String> queryParams) {
        this.params.putAll(queryParams);
        this.requestSpecBuilder.addParams(queryParams);
    }

    public void addMultiPart(String controlName, File file, String mimeType) {
        this.requestSpecBuilder.addMultiPart(controlName, file, mimeType);
    }

    public void addMultiPart(String controlName, File file) {
        this.requestSpecBuilder.addMultiPart(controlName, file);
    }

    public void addMultiPart(String controlName, String contentBody) {
        this.requestSpecBuilder.addMultiPart(controlName, contentBody);
    }

    public void addMultiPart(String controlName, String contentBody, String mimeType) {
        this.requestSpecBuilder.addMultiPart(controlName, contentBody, mimeType);
    }

    public Map<String, Object> getParams() {
        return this.params;
    }

    public RequestSpecBuilder getRequestSpecBuilder() {
        return this.requestSpecBuilder;
    }

    public Response execute(String key) {
        RequestSpecification requestSpecification = this.requestSpecBuilder.addFilter(new RequestLoggingFilter()).addFilter(new ResponseLoggingFilter()).build();
        RestAssured.defaultParser = Parser.JSON;

        Response response;
        switch(key.toUpperCase()) {
            case "GET":
                response = (Response)RestAssured.given().spec(requestSpecification).when().get();
                break;
            case "POST":
                response = (Response)RestAssured.given().spec(requestSpecification).when().post();
                break;
            case "PUT":
                response = (Response)RestAssured.given().spec(requestSpecification).when().put();
                break;
            case "DELETE":
                response = (Response)RestAssured.given().spec(requestSpecification).when().delete();
                break;
            case "PATCH":
                response = (Response)RestAssured.given().spec(requestSpecification).when().patch();
                break;
            default:
                throw new RuntimeException("API method not specified");
        }

        this.response = response;
        return response;
    }

}
