package testSuite;

import helpers.Utils;
import org.junit.Assert;
import org.testng.annotations.Test;
import requestBuilder.GetTimeStamp;

import java.sql.Timestamp;
import java.text.ParseException;

public class TimeStampConvertTest {


    @Test(description = "To verify unix timestamp conversion with valid data in Y-m-d h:i:s")
    public void convertDateStringToUnixTimeStamp(){
        String timeStamp = Utils.getSystemTimeStamp();
        GetTimeStamp timeStampConverter = new GetTimeStamp(timeStamp,false);
        timeStampConverter.createRequestJsonAndExecute();
        Assert.assertEquals(200,timeStampConverter.getApiResponse().getStatusCode());
        Assert.assertEquals(timeStamp,(timeStampConverter.getResponsePojo().toString()));
    }

    @Test(description = "To verify unix timestamp conversion with valid data in Y-m-d h:i:s when request is cached")
    public void convertDateStringToUnixTimeStampWhenCached(){
        String timeStamp = Utils.getSystemTimeStamp();
        GetTimeStamp timeStampConverter = new GetTimeStamp(timeStamp,false);
        timeStampConverter.createRequestJsonAndExecute();
        Assert.assertEquals(200,timeStampConverter.getApiResponse().getStatusCode());
        Assert.assertEquals(timeStamp,(timeStampConverter.getResponsePojo().toString()));

        timeStampConverter = new GetTimeStamp(timeStamp,true);
        timeStampConverter.createRequestJsonAndExecute();
        Assert.assertEquals(304,timeStampConverter.getApiResponse().getStatusCode());
        Assert.assertEquals(timeStamp,(timeStampConverter.getResponsePojo().toString()));
    }

    @Test(description = "To verify date conversion with valid epoc value")
    public void convertUnixTimeStampToDateString() throws ParseException{
        String timeStamp = Utils.getSystemTimeStamp();
        String epoc = String.valueOf(Utils.convertToEpoc(Timestamp.valueOf(timeStamp)));
        GetTimeStamp timeStampConverter = new GetTimeStamp(epoc,false);
        timeStampConverter.createRequestJsonAndExecute();
        Assert.assertEquals(200,timeStampConverter.getApiResponse().getStatusCode());
        Assert.assertEquals(timeStamp,(timeStampConverter.getResponsePojo().toString()));
    }

    @Test(description = "To verify date conversion with valid epoc value when request is cached")
    public void convertUnixTimeStampToDateStringWhenCached() throws ParseException{
        String timeStamp = Utils.getSystemTimeStamp();
        String epoc = String.valueOf(Utils.convertToEpoc(Timestamp.valueOf(timeStamp)));
        GetTimeStamp timeStampConverter = new GetTimeStamp(epoc,false);
        timeStampConverter.createRequestJsonAndExecute();
        Assert.assertEquals(200,timeStampConverter.getApiResponse().getStatusCode());
        Assert.assertEquals(timeStamp,(timeStampConverter.getResponsePojo().toString()));

        timeStampConverter = new GetTimeStamp(epoc,true);
        timeStampConverter.createRequestJsonAndExecute();
        Assert.assertEquals(304,timeStampConverter.getApiResponse().getStatusCode());
        Assert.assertEquals(timeStamp,(timeStampConverter.getResponsePojo().toString()));

    }

    @Test(description = "To verify time unix timestamp conversion with valid value")
    public void convertUnixTimeStampWithInvalidValue() throws ParseException{
        String timeStamp = "123";
        GetTimeStamp timeStampConverter = new GetTimeStamp(timeStamp,false);
        timeStampConverter.createRequestJsonAndExecute();
        Assert.assertEquals(200,timeStampConverter.getApiResponse().getStatusCode());
        Assert.assertEquals("false",(timeStampConverter.getResponsePojo().toString()));

    }

    @Test(description = "To verify time unix timestamp conversion with blank value")
    public void convertUnixTimeStampWithBlankValue() throws ParseException{
        String timeStamp = "";
        GetTimeStamp timeStampConverter = new GetTimeStamp(timeStamp,false);
        timeStampConverter.createRequestJsonAndExecute();
        Assert.assertEquals(200,timeStampConverter.getApiResponse().getStatusCode());
        Assert.assertEquals("false",(timeStampConverter.getResponsePojo().toString()));

    }


}
