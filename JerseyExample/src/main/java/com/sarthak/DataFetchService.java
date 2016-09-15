package com.sarthak;

import com.google.gson.Gson;
import com.mysql.fabric.xmlrpc.base.Data;
import com.sarthak.HibernateMapping.Desk;
import com.sarthak.HibernateMapping.LayoutExtremes;
import com.sarthak.HibernateMapping.TableData;
import com.sarthak.Parser.DataLoader;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Sarthak on 14-09-2016.
 */
@Path("/datafetch")
public class DataFetchService {
    @GET
    @Path("/getdesk")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDeskInfo() {
        Gson gson=new Gson();
        DataLoader dataLoader=new DataLoader();
        List<Desk> deskList=dataLoader.getDesks();
        String jsonArray=gson.toJson(deskList).toString();
        return Response.ok(jsonArray).header("Access-Control-Allow-Origin", "*").build();
    }
    @GET
    @Path("/getlayout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLayoutInfo() {
        Gson gson=new Gson();
        DataLoader dataLoader=new DataLoader();
        LayoutExtremes layoutExtremes=dataLoader.getLayoutExtremes();
        String jsonArray=gson.toJson(layoutExtremes);
        return Response.ok(jsonArray).header("Access-Control-Allow-Origin", "*").build();
    }
    @GET
    @Path("/gettable")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTableInfo(){
        Gson gson=new Gson();
        DataLoader dataLoader=new DataLoader();
        List<TableData> tableList=dataLoader.getTableData();
        String jsonArray=gson.toJson(tableList);
        return Response.ok(jsonArray).header("Access-Control-Allow-Origin", "*").build();
    }
}
