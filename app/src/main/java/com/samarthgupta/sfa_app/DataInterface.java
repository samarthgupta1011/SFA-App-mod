package com.samarthgupta.sfa_app;

import com.samarthgupta.sfa_app.POJO.Employee;
import com.samarthgupta.sfa_app.POJO.UpdateObject;
import com.samarthgupta.sfa_app.POJO.WT_JobTicket.JobTicket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by samarthgupta on 27/07/17.
 */

public interface DataInterface {

    @POST("/register")
    Call<String> postEmployee(@Body Employee emp);

    @POST("/ticket")
    Call<JobTicket> postTicket(@Body JobTicket jt);

    @POST("/login")
    Call<Employee> empLogin(@Body Employee emp);

    //Get all tickets
    @GET("/ticket")
    Call<List<JobTicket>> getTickets();

    //Get specific tickets
//    empticket?empId=A

    @GET("/empticket")
    Call<List<JobTicket>> getEmpTickets(@Query("empId") String empId);

    @POST("/updates")
    Call<UpdateObject> updateProgress(@Query("wt") String wt, @Query("empId") String empId, @Body UpdateObject obj);


}
