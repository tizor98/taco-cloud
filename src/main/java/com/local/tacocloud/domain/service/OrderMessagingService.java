package com.local.tacocloud.domain.service;

import com.local.tacocloud.database.entity.TacoOrder;

// Use inside the OrderApiController
public interface OrderMessagingService {

   void sendOrder(TacoOrder order);

}
