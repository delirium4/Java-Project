import org.dao.CatDao;
import org.dao.CatRelationsDao;
import org.dao.models.Cat;
import org.dao.models.Colors;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.service.CatService;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MakeFriendsTest {
    @Test
    void makeFriendTest(){
        CatDao mockCatDao = Mockito.mock(CatDao.class);
        CatRelationsDao mockCatRelationsDao = Mockito.mock(CatRelationsDao.class);
        CatService catService = new CatService(mockCatDao, mockCatRelationsDao);
        Cat cat = new Cat("Pushok", Date.valueOf("2022-01-01"), Colors.gray, "Bengal");
        Cat catFriend = new Cat("Burbon", Date.valueOf("2022-01-01"), Colors.brown, "Bengal");
        cat.setId(1);
        catFriend.setId(2);

        catService.createRelations(cat, catFriend);

        assertEquals(cat.getFriends().size(), 1);
    }
}
