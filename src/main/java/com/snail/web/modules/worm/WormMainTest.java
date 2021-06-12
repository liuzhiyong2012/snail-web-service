package com.snail.web.modules.worm;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

public class WormMainTest {
    public static void main(String[] args){
        try {
            login();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.getMessage();
        }
    }
    public static void login() throws Exception {


        Connection con2 = Jsoup
                .connect("https://campus.alibaba.com/positionList.htm");
        con2.header("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        Response rs = con2.execute();
        // 转换为Dom树
        Document d1 = Jsoup.parse(rs.body());
        // 获取td，可以通过查看页面源码代码得知
        Element eone = d1.select("#J-filter").get(0);
        // 获取tbody，可以通过查看页面源码代码得知
        List<Element> et = d1.select("tbody[class=campus-position-table-body]");
        // 获取，cooking和表单属性，下面map存放post时的数据

        for (Element e : et.get(0).getAllElements()) {
            if (e.attr("data-type").equals("11")) {
                //System.out.println("DD"+e.html());
                String url =e.select("td[class = position-detail] a[href]").attr("href");
                String title =e.select("th").text();
                String city =e.select("td[class =work-city]").text();
                String experience ="请看详情";
                String salary ="请看详情";
                String education ="请看详情";
                String publictime =e.select("td").get(2).html();
                String details = url;
                String neednum ="请看详情";
                String company ="阿里巴巴";
                String jobtype =eone.select("a[data-type=11]").text();
                try {
                    String sql ="insert into jobtable(url,title,city,experience,salary,education,publictime,details,neednum,company,jobtype) values('"+url+"','"+title+"','"+city+"','"+experience+"','"+salary+"','"+education+"','"+publictime+"','"+details+"','"+neednum+"','"+company+"','"+jobtype+"')";
                  /*  int r =mysqlUtil.add(sql);
                    if (r==1) {
                        System.out.println("插入成功");
                    }else {
                        System.out.println("失败");
                    }*/
                } catch (Exception e2) {
                    // TODO: handle exception
                    throw new Exception("login()方法的foreach循环的第插入失败了，在e.attr(\"data-type\"):"+e.attr("data-type")+"类型中");
                }

            }
            if (e.attr("data-type").equals("1")) {
                //e.attr("value", pwd); // 设置用户密码
                //System.out.println(e.attr("data-type").equals("1"));
                String url =e.select("td[class = position-detail] a[href]").attr("href");
                String title =e.select("th").text();
                String city =e.select("td[class =work-city]").text();
                String experience ="请看详情";
                String salary ="请看详情";
                String education ="请看详情";
                String publictime =e.select("td").get(2).text();
                String details = url;
                String neednum ="请看详情";
                String company ="阿里巴巴";
                String jobtype =eone.select("a[data-type=1]").text();
                System.out.println(jobtype);
                try {
                    String sql ="insert into jobtable(url,title,city,experience,salary,education,publictime,details,neednum,company,jobtype) values('"+url+"','"+title+"','"+city+"','"+experience+"','"+salary+"','"+education+"','"+publictime+"','"+details+"','"+neednum+"','"+company+"','"+jobtype+"')";
                 /*   int r =mysqlUtil.add(sql);
                    if (r==1) {
                        System.out.println("插入成功");
                    }else {
                        System.out.println("失败");
                    }*/
                } catch (Exception e2) {
                    // TODO: handle exception
                    throw new Exception("login()方法的foreach循环的第插入失败了，在e.attr(\"data-type\"):"+e.attr("data-type")+"类型中");
                }
            }
            if (e.attr("data-type").equals("5")) {
                // e.attr("value", pwd); // 设置用户密码
                // System.out.println(e.attr("data-type").equals("5"));
                String url =e.select("td[class = position-detail] a[href]").attr("href");
                String title =e.select("th").text();
                String city =e.select("td[class =work-city]").text();
                String experience ="请看详情";
                String salary ="请看详情";
                String education ="请看详情";
                String publictime =e.select("td").get(2).text();
                String details = url;
                String neednum ="请看详情";
                String company ="阿里巴巴";
                String jobtype =eone.select("a[data-type=5]").text();
                System.out.println(jobtype);
                try {
                    String sql ="insert into jobtable(url,title,city,experience,salary,education,publictime,details,neednum,company,jobtype) values('"+url+"','"+title+"','"+city+"','"+experience+"','"+salary+"','"+education+"','"+publictime+"','"+details+"','"+neednum+"','"+company+"','"+jobtype+"')";
                   /* int r =mysqlUtil.add(sql);//执行自己的数据库链接
                    if (r==1) {
                        System.out.println("插入成功");
                    }else {
                        System.out.println("失败");
                    }*/
                } catch (Exception e2) {
                    // TODO: handle exception
                    throw new Exception("login()方法的foreach循环的第插入失败了，在e.attr(\"data-type\"):"+e.attr("data-type")+"类型中");
                }
            }
            if (e.attr("data-type").equals("3")) {
                //  e.attr("value", pwd); // 设置用户密码
                //  System.out.println(e.attr("data-type").equals("3"));
                String url =e.select("td[class = position-detail] a[href]").attr("href");
                String title =e.select("th").text();
                String city =e.select("td[class =work-city]").text();
                String experience ="请看详情";
                String salary ="请看详情";
                String education ="请看详情";
                String publictime =e.select("td").get(2).text();
                String details = url;
                String neednum ="请看详情";
                String company ="阿里巴巴";
                String jobtype =eone.select("a[data-type=3]").text();
                System.out.println(jobtype);
                try {
                    String sql ="insert into jobtable(url,title,city,experience,salary,education,publictime,details,neednum,company,jobtype) values('"+url+"','"+title+"','"+city+"','"+experience+"','"+salary+"','"+education+"','"+publictime+"','"+details+"','"+neednum+"','"+company+"','"+jobtype+"')";
                   /* int r =mysqlUtil.add(sql);
                    if (r==1) {
                        System.out.println("插入成功");
                    }else {
                        System.out.println("失败");
                    }*/
                } catch (Exception e2) {
                    // TODO: handle exception
                    throw new Exception("login()方法的foreach循环的第插入失败了，在e.attr(\"data-type\"):"+e.attr("data-type")+"类型中");
                }
            }
            if (e.attr("data-type").equals("18")) {
                //e.attr("value", pwd); // 设置用户密码
                // System.out.println(e.attr("data-type").equals("18"));
                String url =e.select("td[class = position-detail] a[href]").attr("href");
                String title =e.select("th").text();
                String city =e.select("td[class =work-city]").text();
                String experience ="请看详情";
                String salary ="请看详情";
                String education ="请看详情";
                String publictime =e.select("td").get(2).text();
                String details = url;
                String neednum ="请看详情";
                String company ="阿里巴巴";
                String jobtype =eone.select("a[data-type=18]").text();
                System.out.println(jobtype);
                try {
                    String sql ="insert into jobtable(url,title,city,experience,salary,education,publictime,details,neednum,company,jobtype) values('"+url+"','"+title+"','"+city+"','"+experience+"','"+salary+"','"+education+"','"+publictime+"','"+details+"','"+neednum+"','"+company+"','"+jobtype+"')";
                   /* int r =mysqlUtil.add(sql);
                    if (r==1) {
                        System.out.println("插入成功");
                    }else {
                        System.out.println("失败");
                    }*/
                } catch (Exception e2) {
                    // TODO: handle exception
                    throw new Exception("login()方法的foreach循环的第插入失败了，在e.attr(\"data-type\"):"+e.attr("data-type")+"类型中");
                }
            }
            if (e.attr("data-type").equals("19")) {
                // e.attr("value", pwd); // 设置用户密码
                //  System.out.println(e.attr("data-type").equals("19"));
                String url =e.select("td[class = position-detail] a[href]").attr("href");
                String title =e.select("th").text();
                String city =e.select("td[class =work-city]").text();
                String experience ="请看详情";
                String salary ="请看详情";
                String education ="请看详情";
                String publictime =e.select("td").get(2).text();
                String details = url;
                String neednum ="请看详情";
                String company ="阿里巴巴";
                String jobtype =eone.select("a[data-type=19]").text();
                System.out.println(jobtype);
                try {
                   /* String sql ="insert into jobtable(url,title,city,experience,salary,education,publictime,details,neednum,company,jobtype) values('"+url+"','"+title+"','"+city+"','"+experience+"','"+salary+"','"+education+"','"+publictime+"','"+details+"','"+neednum+"','"+company+"','"+jobtype+"')";
                    int r =mysqlUtil.add(sql);
                    if (r==1) {
                        System.out.println("插入成功");
                    }else {
                        System.out.println("失败");
                    }*/
                } catch (Exception e2) {
                    // TODO: handle exception
                    throw new Exception("login()方法的foreach循环的第插入失败了，在e.attr(\"data-type\"):"+e.attr("data-type")+"类型中");
                }
            }
            if (e.attr("data-type").equals("20")) {
                //  e.attr("value", pwd); // 设置用户密码
                //  System.out.println(e.attr("data-type").equals("20"));
                String url =e.select("td[class = position-detail] a[href]").attr("href");
                String title =e.select("th").text();
                String city =e.select("td[class =work-city]").text();
                String experience ="请看详情";
                String salary ="请看详情";
                String education ="请看详情";
                String publictime =e.select("td").get(2).text();
                String details = url;
                String neednum ="请看详情";
                String company ="阿里巴巴";
                String jobtype =eone.select("a[data-type=20]").text();
                System.out.println(jobtype);
                try {
                    String sql ="insert into jobtable(url,title,city,experience,salary,education,publictime,details,neednum,company,jobtype) values('"+url+"','"+title+"','"+city+"','"+experience+"','"+salary+"','"+education+"','"+publictime+"','"+details+"','"+neednum+"','"+company+"','"+jobtype+"')";
                    /*int r =mysqlUtil.add(sql);
                    if (r==1) {
                        System.out.println("插入成功");
                    }else {
                        System.out.println("失败");
                    }*/
                } catch (Exception e2) {
                    // TODO: handle exception
                    throw new Exception("login()方法的foreach循环的第插入失败了，在e.attr(\"data-type\"):"+e.attr("data-type")+"类型中");
                }
            }
        }
    }
}
