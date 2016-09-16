package com.sarthak;

import com.sarthak.HibernateMapping.LayoutExtremes;
import com.sarthak.HibernateMapping.Location;
import com.sarthak.HibernateMapping.TableData;
import com.sarthak.Parser.DataLoader;

import java.util.List;

/**
 * Created by Mohit on 9/16/2016.
 */
public class test {
    public static void main(String[] args){
        Location location=new Location("India","Pune","Kharadi","3");
        DataLoader dataLoader=new DataLoader();
        LayoutExtremes layoutExtremes=dataLoader.getLayoutExtremes(location.getCountry(),location.getCity(),location.getBranch(),location.getFloor());
        List<TableData> table=dataLoader.getTableData(location.getCountry(),location.getCity(),location.getBranch(),location.getFloor());
        layoutExtremes.tableList=table;

    }
}
