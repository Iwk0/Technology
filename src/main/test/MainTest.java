import com.technology.model.User;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: imishev
 * Date: 15-3-11
 * Time: 10:55
 */
public class MainTest {

    private static User user;

    @BeforeClass
    public static void before() {
        user = mock(User.class);
        when(user.getPassword()).thenReturn("admin");
        when(user.getUsername()).thenReturn("admin");
        when(user.getRole()).thenReturn(User.Role.ADMIN);
    }

    @Test
    public void test() {
        assertEquals("admin", user.getUsername());
        assertEquals(User.Role.ADMIN, user.getRole());
    }

    @Test
    public void test1() {
        assertEquals(1, 1);
    }

    @Test
    public void test2() {
        assertEquals(1, 1);
        assertEquals(1, 1);
        assertEquals(1, 1);
        assertEquals(1, 1);
        assertEquals(1, 1);
        assertEquals(1, 1);
    }
}