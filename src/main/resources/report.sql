SELECT count_orders.all_orders AS count_orders, (turnover_without_vat.final_price / count_orders.all_orders) AS average_order_amount,
turnover_without_vat.final_price AS turnover_without_vat, (turnover_without_vat.final_price * 0.2) AS turnover_vat,
(turnover_without_vat.final_price + turnover_without_vat.final_price * 0.2) AS turnover_with_vat
FROM (SELECT count(order_id) AS all_orders from (SELECT order_id from "ORDER")) AS count_orders,
(SELECT sum((I.quantity * I.price)) AS final_price FROM "ORDER" inner join ITEM I on "ORDER".order_id = I.order_id)
AS turnover_without_vat;