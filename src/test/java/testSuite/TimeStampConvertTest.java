package testSuite;

import global.Groups;
import helpers.Utils;
import org.junit.Assert;
import org.testng.annotations.Test;
import requestBuilder.GetTimeStamp;

import java.sql.Timestamp;
import java.text.ParseException;

public class TimeStampConvertTest {


    @Test(description = "To verify unix timestamp conversion with valid data in Y-m-d h:i:s",
            groups = {Groups.REGRESSION,Groups.SANITY,Groups.SMOKE})
    public void convertDateStringToUnixTimeStamp() throws ParseException{
        String timeStamp = Utils.getSystemTimeStamp();
        GetTimeStamp timeStampConverter = new GetTimeStamp(timeStamp,false);
        timeStampConverter.createRequestJsonAndExecute(timeStamp);
        Assert.assertEquals(200,timeStampConverter.getApiResponse().getStatusCode());
        Assert.assertEquals(String.valueOf(Utils.convertToEpoc(Timestamp.valueOf(timeStamp))),
                timeStampConverter.getApiResponse().getBody().toString());
    }

    @Test(description = "To verify unix timestamp conversion with valid data in Y-m-d h:i:s when request is cached",
            groups = {Groups.REGRESSION,Groups.SMOKE})
    public void convertDateStringToUnixTimeStampWhenCached() throws ParseException{
        String timeStamp = Utils.getSystemTimeStamp();
        GetTimeStamp timeStampConverter = new GetTimeStamp(timeStamp,false);
        timeStampConverter.createRequestJsonAndExecute();
        Assert.assertEquals(200,timeStampConverter.getApiResponse().getStatusCode());
        Assert.assertEquals(String.valueOf(Utils.convertToEpoc(Timestamp.valueOf(timeStamp))),
                timeStampConverter.getApiResponse().getBody().toString());

        timeStampConverter = new GetTimeStamp(timeStamp,true);
        timeStampConverter.createRequestJsonAndExecute();
        Assert.assertEquals(304,timeStampConverter.getApiResponse().getStatusCode());
        Assert.assertEquals(String.valueOf(Utils.convertToEpoc(Timestamp.valueOf(timeStamp))),
                timeStampConverter.getApiResponse().getBody().toString());
    }

    @Test(description = "To verify date conversion with valid epoc value", groups = {Groups.REGRESSION,Groups.SANITY,Groups.SMOKE})
    public void convertUnixTimeStampToDateString() throws ParseException{
        String timeStamp = Utils.getSystemTimeStamp();
        String epoc = String.valueOf(Utils.convertToEpoc(Timestamp.valueOf(timeStamp)));
        GetTimeStamp timeStampConverter = new GetTimeStamp(epoc,false);
        timeStampConverter.createRequestJsonAndExecute();
        Assert.assertEquals(200,timeStampConverter.getApiResponse().getStatusCode());
        Assert.assertEquals(timeStamp,timeStampConverter.getApiResponse().getBody().toString());
    }

    @Test(description = "To verify date conversion with valid epoc value when request is cached",
            groups = {Groups.REGRESSION,Groups.SMOKE})
    public void convertUnixTimeStampToDateStringWhenCached() throws ParseException{
        String timeStamp = Utils.getSystemTimeStamp();
        String epoc = String.valueOf(Utils.convertToEpoc(Timestamp.valueOf(timeStamp)));
        GetTimeStamp timeStampConverter = new GetTimeStamp(epoc,false);
        timeStampConverter.createRequestJsonAndExecute();
        Assert.assertEquals(200,timeStampConverter.getApiResponse().getStatusCode());
        Assert.assertEquals(timeStamp,timeStampConverter.getApiResponse().getBody().toString());

        timeStampConverter = new GetTimeStamp(epoc,true);
        timeStampConverter.createRequestJsonAndExecute();
        Assert.assertEquals(304,timeStampConverter.getApiResponse().getStatusCode());
        Assert.assertEquals(timeStamp,timeStampConverter.getApiResponse().getBody().toString());

    }

    @Test(description = "To verify time unix timestamp conversion with invalid value",groups = {Groups.REGRESSION})
    public void convertUnixTimeStampWithInvalidValue() throws ParseException{
        String timeStamp = "123";
        GetTimeStamp timeStampConverter = new GetTimeStamp(timeStamp,false);
        timeStampConverter.createRequestJsonAndExecute();
        Assert.assertEquals(200,timeStampConverter.getApiResponse().getStatusCode());
        Assert.assertEquals("false",timeStampConverter.getApiResponse().getBody().toString());

    }

    @Test(description = "To verify time unix timestamp conversion with blank value",groups = {Groups.REGRESSION})
    public void convertUnixTimeStampWithBlankValue() throws ParseException{
        String timeStamp = "";
        GetTimeStamp timeStampConverter = new GetTimeStamp(timeStamp,false);
        timeStampConverter.createRequestJsonAndExecute();
        Assert.assertEquals(200,timeStampConverter.getApiResponse().getStatusCode());
        Assert.assertEquals("false",timeStampConverter.getApiResponse().getBody().toString());

    }

    @Test(description = "To verify time unix timestamp conversion with alphanumeric invalid value",groups = {Groups.REGRESSION})
    public void convertUnixTimeStampWithAplhanumericValue() throws ParseException{
        String timeStamp = "123";
        GetTimeStamp timeStampConverter = new GetTimeStamp(timeStamp,false);
        timeStampConverter.createRequestJsonAndExecute();
        Assert.assertEquals(200,timeStampConverter.getApiResponse().getStatusCode());
        Assert.assertEquals("false",timeStampConverter.getApiResponse().getBody().toString());

    }



}
