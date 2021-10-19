package homeWorks.homeWork5;

import com.github.javafaker.Faker;
import enums.CategoryType;
import modelsProductController.ErrorInfo;
import modelsProductController.Product;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.*;
import retrofit2.Response;
import retrofit2.Retrofit;
import service.CategoryInterface;
import service.ProductInterface;
import utils.PrettyLogger;
import utils.RetrofitUtil;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTest {
    static Retrofit client;
    static ProductInterface productInterface;
    static CategoryInterface categoryInterface;
    Product product, productWithId;
    Faker faker = new Faker();
    PrettyLogger prettyLogger = new PrettyLogger();
    static Product newProduct;
    static int id;


    @BeforeAll
    static void beforeAll() {
        client = RetrofitUtil.getRetrofit();
        productInterface = client.create(ProductInterface.class);
        categoryInterface = client.create(CategoryInterface.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().fruit())
                .withCategoryTitle(CategoryType.ELECTRONIC.getTitle())
                .withPrice((int) (Math.random() * 10000));

        productWithId = new Product()
                .withId((int) (Math.random() * 10))
                .withTitle(faker.beer().name())
                .withCategoryTitle(CategoryType.FURNITURE.getTitle())
                .withPrice((int) (Math.random() * 10000));

    }

    //Позитивная проверка на поиск всех продуктов
    @Test
    @Order(1)
    void getAllProductsTest() throws IOException {
        Response<ArrayList<Product>> response = productInterface.getAllProducts().execute();
        assertThat(response.code(), equalTo(200));
        assertThat(response.body(), isA(ArrayList.class));
        assert response.body() != null;
        assertThat(response.body().toArray(), is(not(emptyArray())));
        assertThat(response.headers().get("content-type"), equalTo("application/json"));
        assertThat(response.body().toString(), containsString(CategoryType.FOOD.getTitle()));
        assertThat(response.body().toString(), stringContainsInOrder(CategoryType.FOOD.getTitle()
                , CategoryType.ELECTRONIC.getTitle(), CategoryType.FURNITURE.getTitle()));
    }

    //Негативная проверка с не верной версией API в URL
    @Test
    @Order(2)
    void getAllProductsWithInvalidURLTest() throws IOException {

        client = RetrofitUtil.getRetrofit("http://80.78.248.82:8189/market/api/v99/");
        productInterface = client.create(ProductInterface.class);

        Response<ResponseBody> response = productInterface.getAllProductsDefaultResponse().execute();

        assertThat(response.code(), equalTo(404));
        assertThat(response.body(), nullValue());
        assertThat(response.isSuccessful(), is(not(true)));
        assertThat(response.headers().get("content-type"), equalTo("application/json"));

        beforeAll();
    }


    //Позитивная проверка на создание продукта
    @Test
    @Order(3)
    void postProductTest() throws IOException {
        Response<Product> response = productInterface.createProduct(product).execute();
        System.out.println(response.body().toString());
        id = response.body().getId();
        System.out.println("ID = " + id);
        assertThat(response.code(), equalTo(201));
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));
        assertThat(response.body().getCategoryTitle(), equalTo(product.getCategoryTitle()));
    }


    //Негативная проверка на создание продукта с указанием ID
    @Test
    @Order(4)
    void postProductWithIDTest() throws IOException {
        Response<ErrorInfo> response = productInterface.createProductWithId(productWithId).execute();
        assertThat(response.code(), equalTo(400));
    }


    //Позитивная проверка изменения продукта
    @Test
    @Order(5)
    void modifyProductByIDTest() throws IOException {

        System.out.println("Old TITLE : " + product.getTitle());
        //Так как у нас перед каждым запросом создается новый экземпляр продукта с геренацией рандомных данных,
        // нам не прийдется формировать новый экземпляр продукта полностью, просто добавим ID
        product.setId(id);
        System.out.println(product.toString());

        System.out.println("New TITLE : " + product.getTitle());
        newProduct = product;
        Response<Product> response = productInterface.modifyProduct(product).execute();

        assertThat(response.code(), equalTo(200));
        assert response.body() != null;
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));
        assertThat(response.body().getCategoryTitle(), equalTo(product.getCategoryTitle()));
    }

    //Негативная проверка изменения продукта
    @Test
    @Order(6)
    void modifyProductByIDEqlNULLTest() throws IOException {

        System.out.println("Old TITLE : " + product.getTitle());
        product.setId(0);
        product.setTitle(faker.food().ingredient());
        System.out.println(product.toString());
        System.out.println("New TITLE : " + product.getTitle());

        Response<ErrorInfo> response = productInterface.modifyProductWithNULLId(product).execute();

        assertThat(response.code(), equalTo(400));

    }

    //Позитивная проверка поиска продукта
    @Test
    @Order(7)
    void getProductByIDTest() throws IOException {
        Response<Product> response = productInterface.getProduct(id).execute();

        assert response.body() != null;
        System.out.println(response.body());
        id = response.body().getId();
        System.out.println("ID = " + id);

        assertThat(response.code(), equalTo(200));
        assertThat(response.body().getId(), equalTo(newProduct.getId()));
        assertThat(response.body().getTitle(), equalTo(newProduct.getTitle()));
        assertThat(response.body().getPrice(), equalTo(newProduct.getPrice()));
        assertThat(response.body().getCategoryTitle(), equalTo(newProduct.getCategoryTitle()));
    }

    //Позитивная проверка удаления продукта
    @Test
    @Order(8)
    void deleteProductByIDTest() throws IOException {
        Response<ResponseBody> response = productInterface.deleteProduct(newProduct.getId()).execute();
        assertThat(response.code(), equalTo(200));
    }


    //Негативная проверка, поиск удаленного продукта по id
    @Test
    @Order(9)
    void getUNExistProductByIDTest() throws IOException {
        Response<Product> response = productInterface.getProduct(newProduct.getId()).execute();
        assertThat(response.code(), equalTo(404));
    }









}
