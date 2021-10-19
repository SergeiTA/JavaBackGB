package utils;

import com.github.javafaker.Faker;
import db.dao.CategoriesMapper;
import db.dao.ProductsMapper;
import db.model.Categories;
import db.model.CategoriesExample;
import db.model.Products;
import db.model.ProductsExample;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class DBUtils {

    static Faker faker = new Faker();
    private static String mBatisConfig = "mybatisConfig.xml";


    @SneakyThrows
    private static SqlSession getSqlSession() {
        SqlSessionFactory sqlSessionFactory;
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(mBatisConfig));
        return sqlSessionFactory.openSession(true);
    }

    @SneakyThrows
    public static CategoriesMapper getCategoriesMapper(){
        return getSqlSession().getMapper(CategoriesMapper.class);
    }

    @SneakyThrows
    public static ProductsMapper getProductsMapper() {
        return getSqlSession().getMapper(ProductsMapper.class);
    }


    private static void createNewCategory(CategoriesMapper categoriesMapper) {
        Categories newCategory = new Categories();
        newCategory.setTitle(faker.animal().name());
        categoriesMapper.insert(newCategory);
    }

    public static Integer countCategories(CategoriesMapper categoriesMapper) {
        return Math.toIntExact(categoriesMapper.countByExample(new CategoriesExample()));
    }

    public static Integer countProducts(ProductsMapper productsMapper) {
        return Math.toIntExact(productsMapper.countByExample(new ProductsExample()));
    }

    public static List<Products> getListOfProducts(ProductsMapper productsMapper) {
        return productsMapper.selectByExample(new ProductsExample());
    }

    public static Products getProductByID(ProductsMapper productsMapper, long id) {
        return productsMapper.selectByPrimaryKey(id);
    }

}
