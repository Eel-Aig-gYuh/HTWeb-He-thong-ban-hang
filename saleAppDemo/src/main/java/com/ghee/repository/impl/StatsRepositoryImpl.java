/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ghee.repository.impl;

import com.ghee.pojo.OrderDetail;
import com.ghee.pojo.Product;
import com.ghee.saleappdemo.HibernateUtils;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author admin
 */
public class StatsRepositoryImpl {
    public List<Object[]> statsRevenueByProduct() {
        try (Session s = HibernateUtils.getFactory().openSession()) {
            CriteriaBuilder b = s.getCriteriaBuilder();
            CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
            Root root = q.from(OrderDetail.class);
            
            Join<OrderDetail, Product> join = root.join("productId", JoinType.RIGHT);
            
            q.multiselect(join.get("id"), join.get("name"), b.sum(b.prod(root.get("quantity"), root.get("unitPrice"))));
            q.groupBy(join.get("id"));
            
            Query query = s.createQuery(q);
            return query.getResultList();            
        }
    }
    
    
//    public List<Object[]> statsRevenueByPeriod(Map<String, String> params) {
//        try (Session s = HibernateUtils.getFactory().openSession()) {
//            CriteriaBuilder b = s.getCriteriaBuilder();
//            CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
//            Root root = q.from(Product.class);
//            
//            
//            Root rProd = q.from(Product.class);
//            Root rDetail = q.from(OrderDetail.class);
//            Root rOrder = q.from(SaleOrder.class);
//            
//            
//            predicates.add(b.equal(rDetail.get("productId"), rProd.get("id")));
//            predicates.add(b.equal(rDetail.get("orderId"), rOrder.get("id")));
//            
//            if (params != null) {
//                String kw = params.get("kw");
//                if (kw != null && !kw.isEmpty()) 
//                    predicates.add(b.like(rProd.get("name"), String.format("%%%s%%", kw)));
//                
//                String fd = params.get("fromDate");
//                if (fd != null && !fd.isEmpty()) 
//                    predicates.add(b.greaterThanOrEqualTo(rOrder.get("createdDate"), F.parse(fd)));
//                
//                String td = params.get("toDate");
//                if (td != null && !td.isEmpty()) 
//                    predicates.add(b.lessThanOrEqualTo(rOrder.get("createdDate"), F.parse(td)));
//                
//                String quarter = params.get("quarter");
//                if (quarter != null && !quarter.isEmpty()) {
//                    String year = params.get("year");
//                    if (year != null && !year.isEmpty()) {
//                        predicates.add(b.equal(b.function("YEAR", Integer.class, rOrder.get("createdDate")), Integer.parseInt(year)));
//                        predicates.add(b.equal(b.function("QUARTER", Integer.class, rOrder.get("createdDate")), Integer.parseInt(quarter)));
//                    }
//                }
//            }
//            
//            q.where(predicates.toArray(Predicate[]::new));
//            
//            q.multiselect(rProd.get("id"), rProd.get("name"), b.sum(b.prod(rDetail.get("unitPrice"), rDetail.get("num"))));
//            q.groupBy(rProd.get("id"));
//            q.orderBy(b.desc(rProd.get("id")));
//            
//            Query query = s.createQuery(q);
//            return query.getResultList();         
//        }
//    }
}
