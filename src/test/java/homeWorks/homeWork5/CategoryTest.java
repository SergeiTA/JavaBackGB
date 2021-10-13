package homeWorks.homeWork5;

import lombok.SneakyThrows;
import modelsProductController.Category;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import service.CategoryInterface;
import utils.RetrofitUtil;
import static org.hamcrest.MatcherAssert.assertThat;

public class CategoryTest {

    static CategoryInterface categoryInterface;

    @BeforeAll
    static void beforeAll() {
        categoryInterface = RetrofitUtil.getRetrofit().create(CategoryInterface.class);
    }

    @SneakyThrows
    @Test
    void getCategoryByIdPositiveTest() {
        Response<Category> response = categoryInterface.getCategory(1).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

}
