package com.jxnydx.store.frame.front.student;

import com.jxnydx.store.factory.ServiceFrontFactory;
import com.jxnydx.store.vo.Orders;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

public class StudentUserFindOrdersFrame extends JFrame {
    private String sno;

    private List<Orders> ordersList;

    public StudentUserFindOrdersFrame(String frameName, String sno) {
        this.sno = sno;
        try {
            this.ordersList = ServiceFrontFactory.getIOrdersServiceFrontInstance().getStudentAllOrders(this.sno);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setSize(800, 600);
        this.setTitle(frameName);
        this.setLayout(new GridLayout(this.ordersList.size(),5));

        Iterator<Orders> iter = ordersList.iterator();
        while (iter.hasNext()) {
            final Orders orders = iter.next();
            JPanel jPanelOnePointOne = new JPanel();
            JPanel jPanelOnePointTwo = new JPanel();
            JPanel jPanelOnePointThree = new JPanel();
            JPanel jPanelOnePointFour = new JPanel();
            JPanel jPanelOnePointFive = new JPanel();
            JLabel ordersMerchant = null;
            try {
                ordersMerchant = new JLabel(ServiceFrontFactory.getIMerchantServiceFrontInstance().getMerchantInfo(orders.getMid()).getMname());
            } catch (Exception e) {
                e.printStackTrace();
            }
            JLabel ordersGoods = new JLabel(orders.getGname());
            JLabel ordersPrice = new JLabel(String.valueOf(orders.getPay()) + "¥");
            JLabel ordersDate = new JLabel(orders.getCredate().toString());
            JLabel ordersStatus = null;
            if (orders.getOstatus() == 0) {
                ordersStatus = new JLabel("已处理");
            } else if (orders.getOstatus() == 1) {
                ordersStatus = new JLabel("未处理");
            }

            jPanelOnePointOne.add(ordersMerchant);
            jPanelOnePointTwo.add(ordersGoods);
            jPanelOnePointThree.add(ordersPrice);
            jPanelOnePointFour.add(ordersDate);
            jPanelOnePointFive.add(ordersStatus);

            this.add(jPanelOnePointOne);
            this.add(jPanelOnePointTwo);
            this.add(jPanelOnePointThree);
            this.add(jPanelOnePointFour);
            this.add(jPanelOnePointFive);
        }

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
