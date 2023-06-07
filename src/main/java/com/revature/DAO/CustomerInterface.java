package com.revature.DAO;

import com.revature.models.Customer;

public interface CustomerInterface {

    Customer getCustomerByNumber(int order_number);

}
