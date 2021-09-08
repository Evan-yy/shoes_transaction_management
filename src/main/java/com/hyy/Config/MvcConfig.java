package com.hyy.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/").setViewName("login");
            registry.addViewController("login.html").setViewName("login");
            registry.addViewController("index.html").setViewName("Admin/index");
            registry.addViewController("u_index.html").setViewName("u_index");
            registry.addViewController("shoes.html").setViewName("shoes");




            registry.addViewController("welcome.html").setViewName("Admin/welcome");
            registry.addViewController("welcome1.html").setViewName("Admin/welcome1");

            registry.addViewController("order-list.html").setViewName("Admin/order-list");


            registry.addViewController("admin-list.html").setViewName("Admin/admin-list");
            registry.addViewController("admin-add.html").setViewName("Admin/admin-add");
            registry.addViewController("admin-edit.html").setViewName("Admin/admin-edit");
            registry.addViewController("member-list.html").setViewName("Admin/member-list");
            registry.addViewController("member-del.html").setViewName("Admin/member-del");

            registry.addViewController("echarts1.html").setViewName("Admin/echarts1");
            registry.addViewController("echarts4.html").setViewName("Admin/echarts4");
            registry.addViewController("member-del.html").setViewName("Admin/member-del");



        }

}
