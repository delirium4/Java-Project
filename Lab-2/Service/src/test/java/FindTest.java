import org.dao.OwnerDao;
import org.dao.models.Owner;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.service.*;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindTest {
    @Test
    void findMethodTest(){
        OwnerDao mockOwnerDao = Mockito.mock(OwnerDao.class);
        OwnerService ownerService = new OwnerService(mockOwnerDao);
        Owner owner = new Owner(Date.valueOf("2000-01-01"), "Vasya");
        Mockito.when(mockOwnerDao.findOwnerById(1)).thenReturn(owner);

        Owner ownerTest = ownerService.findOwner(1);

        assertEquals(owner, ownerTest);
    }
}
