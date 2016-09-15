package com.sarthak.Parser;

import com.sarthak.HibernateMapping.Desk;
import com.sarthak.HibernateMapping.LayoutExtremes;
import com.sarthak.HibernateMapping.TableData;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

/**
 * Created by Sarthak on 13-09-2016.
 */
public class DataLoader {
    private SessionFactory factory;
    public DataLoader(){
        try {
            //factory = new Configuration().configure().buildSessionFactory();
            Configuration configuration=new Configuration();
            configuration.configure("hibernate.cfg.xml");
            ServiceRegistryBuilder srb=new ServiceRegistryBuilder().applySettings(configuration.getProperties());
            factory = configuration.buildSessionFactory(srb.buildServiceRegistry());
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Session factory failed");
        }
    }
    public void saveDesk(List<Desk> desks){
        Session session=factory.openSession();
        Transaction transaction=session.beginTransaction();
        for(Desk desk:desks){
            session.save(desk);
        }
        transaction.commit();
        session.close();
    }

    public void saveExtremes(LayoutExtremes layoutExtremes) {
        Session session=factory.openSession();
        Transaction transaction=session.beginTransaction();
        session.save(layoutExtremes);
        transaction.commit();
        session.close();
    }
    public void deleteData(String locationId){
        Session session=factory.openSession();
        Transaction transaction=session.beginTransaction();
        //Query q=session.createQuery("delete Desk where locationId='"+locationId+"'");
        //Query q1=session.createQuery("delete LayoutExtremes where layoutId='"+locationId+"'");
        Query q=session.createQuery("delete Desk");
        Query q1=session.createQuery("delete LayoutExtremes");
        Query q2=session.createQuery("delete TableData");
        q.executeUpdate();
        q1.executeUpdate();
        q2.executeUpdate();
        transaction.commit();
        session.close();
    }
    public List<Desk> getDesks(){
        Session session=factory.openSession();
        Transaction transaction=session.beginTransaction();
        List<Desk> users = session.createQuery("from Desk").list();
        return users;
    }
    public LayoutExtremes getLayoutExtremes(){
        Session session=factory.openSession();
        Transaction transaction=session.beginTransaction();
        LayoutExtremes layoutExtremes = (LayoutExtremes) session.createQuery("from LayoutExtremes ").list().get(0);
        return layoutExtremes;
    }
    public void saveTableData(List<TableData> tableData){
        Session session=factory.openSession();
        Transaction transaction=session.beginTransaction();
        for(TableData td:tableData){
            session.save(td);
        }
        transaction.commit();
        session.close();
    }
    public List<TableData> getTableData(){
        Session session=factory.openSession();
        Transaction transaction=session.beginTransaction();
        List<TableData>  tableDatas = session.createQuery("from TableData").list();
        return tableDatas;
    }
}
