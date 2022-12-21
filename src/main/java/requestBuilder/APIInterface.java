package requestBuilder;

import io.restassured.response.Response;

public interface APIInterface {

    RequestPojo getRequestPojo(); //for using requests
    ResponsePojo getResponsePojo(); //for using response
    Response getApiResponse(); //for using api response

    interface ResponsePojo {
    }

    interface RequestPojo {

    }
    void createRequestJsonAndExecute();
}
