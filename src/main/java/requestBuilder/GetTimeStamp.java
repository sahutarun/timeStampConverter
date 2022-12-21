package requestBuilder;

import helpers.BaseApi;
import io.restassured.response.Response;
import properties.getProperties;


public class GetTimeStamp extends BaseApi implements APIInterface {
    private Response response;

    public GetTimeStamp(String timeStamp,boolean cached) {
        setMethod("GET"); //setting the method type to execute e.g. GET, POST
        setContentType("application/json"); //setting the content type application/json
        setBaseUri(getProperties.host);
        if(cached){
            addQueryParam("cached", "");
        }
        addQueryParam("s",timeStamp);


    }


    @Override
    public RequestPojo getRequestPojo(){
        return null;
    }

    @Override
    public ResponsePojo getResponsePojo(){
        return null;
    }


    @Override
    public Response getApiResponse() {
        return response;
    }

    @Override
    public void createRequestJsonAndExecute() {
        response = execute("GET");
    }
}

