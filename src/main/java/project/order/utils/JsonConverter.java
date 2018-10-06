package project.order.utils;

import project.order.model.Order;

public class JsonConverter {

    public Order convertJsonToPojo(String json) {
        String orderNameTrim = json.replaceAll("\\s", "");
        String orderName = orderNameTrim.substring(orderNameTrim.indexOf(":\"") + 2,
                orderNameTrim.indexOf("\"}"));

        Order order = new Order();
        order.setOrderNumber(orderName);

        return order;
    }

    public String convertPojoToJson(Order order) {
        return "{\"id\": " + order.getId() +", \"orderNumber\" : \"" + order.getOrderNumber() + "\"}";
    }
}
