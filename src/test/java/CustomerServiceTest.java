import com.revature.models.Customer;
import com.revature.service.CustomerService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest {

    CustomerService customerService = new CustomerService();

    @Test
    public void checkOrderByNumberZero(){
        assertNull(customerService.checkOrderByNumber(0));
    }

    @Test
    public void checkOrderByNumberNegativeInt(){
        assertNull(customerService.checkOrderByNumber(-3));
    }

    @Test
    public void checkOrderNumberExists() {
        assertNull(customerService.checkOrderByNumber(9000000^10));
    }

    @Test
    public void deleteNumberExists(){
        assertFalse(customerService.deleteByOrderNumber(-2));
    }

    @Test void getAllOrdersNotNull(){
        assertNotNull(customerService.getAllCustomerOrders());
    }




}
