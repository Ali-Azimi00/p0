import com.revature.service.CustomerService;
import com.revature.service.DonutsService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class DonutServiceTest {
    DonutsService donutsService = new DonutsService();

    @Test
    public void getDonutByIdZero(){
        assertNull(donutsService.getDonutById(0));
    }

    @Test
    public void getDonutIdWithinBounds(){
        //There is no donut order above 5
        assertNull(donutsService.getDonutById(6));
    }
}
