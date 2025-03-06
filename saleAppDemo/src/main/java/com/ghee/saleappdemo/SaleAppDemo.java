/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ghee.saleappdemo;

import com.ghee.repository.impl.CategoryRepositoryImpl;
import com.ghee.repository.impl.ProductRepositoryImpl;
import com.ghee.repository.impl.StatsRepositoryImpl;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author admin
 */
public class SaleAppDemo {

    public static void main(String[] args) {
        CategoryRepositoryImpl s = new CategoryRepositoryImpl();
        s.getCates().forEach(c -> System.out.println(c.getName()));
        
        Map<String, String> params = new HashMap<>();
        // params.put("kw", "Iphone");
        
        ProductRepositoryImpl prod = new ProductRepositoryImpl();
        prod.getProducts(params).forEach(c -> System.out.printf("%d - %s - %d\n",
                c.getId(), c.getName(), c.getPrice()));
        
        
        StatsRepositoryImpl stats = new StatsRepositoryImpl();
        stats.statsRevenueByProduct().forEach(o -> System.out.printf("%d - %s - %d\n", o[0], o[1], o[2]));
        
        stats.statsRevenueByPeriod(2020, "").forEach(o -> System.out.printf("%s - %d\n", o[0], o[1]));
        
    }
}
