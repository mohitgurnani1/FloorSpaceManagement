package com.sarthak;

import com.google.gson.Gson;
import com.mysql.fabric.xmlrpc.base.Data;
import com.sarthak.HibernateMapping.Desk;
import com.sarthak.HibernateMapping.LayoutExtremes;
import com.sarthak.HibernateMapping.Location;
import com.sarthak.HibernateMapping.TableData;
import com.sarthak.Parser.DataLoader;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Sarthak on 14-09-2016.
 */
@Path("/datafetch")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class DataFetchService {

    @GET
    @Path("/getlayout")
    public LayoutExtremes getLayoutInfo() {
        //Gson gson=new Gson();


        DataLoader dataLoader=new DataLoader();
        LayoutExtremes layoutExtremes=dataLoader.getLayoutExtremes();
        List<TableData> table=dataLoader.getTableData();
        layoutExtremes.tableList=table;
        // String jsonArray=gson.toJson(layoutExtremes);
        //return Response.ok(layoutExtremes).header("Access-Control-Allow-Origin", "*").build();
   return layoutExtremes;
    }

    @POST
    @Path("/getlayout")
    public LayoutExtremes getLayout(Location location){
       // System.out.println("rest input="+location.getCountry()+location.getCity()+location.getBranch()+location.getFloor());
        DataLoader dataLoader=new DataLoader();
        LayoutExtremes layoutExtremes=dataLoader.getLayoutExtremes(location.getCountry(),location.getCity(),location.getBranch(),location.getFloor());
        if(layoutExtremes==null)
            layoutExtremes=new LayoutExtremes();

        List<TableData> table=dataLoader.getTableData(location.getCountry(),location.getCity(),location.getBranch(),location.getFloor());
        if(table!=null)
            layoutExtremes.tableList=table;

      return  layoutExtremes;
    }

    /*
    @GET
    @Path("/gettable")
    public List<TableData> getTableInfo(){
   //     Gson gson=new Gson();
        DataLoader dataLoader=new DataLoader();
        List<TableData> tableList=dataLoader.getTableData();
     //   String jsonArray=gson.toJson(tableList);
       // return Response.ok(jsonArray).header("Access-Control-Allow-Origin", "*").build();
    return tableList;
    }
*/
}
