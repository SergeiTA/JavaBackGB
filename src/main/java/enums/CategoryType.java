package enums;


public enum CategoryType {
    FOOD("Food", 1),
    ELECTRONIC("Electronic", 2),
    FURNITURE("Furniture", 3);


    private final String title;
    private final Integer id;

    CategoryType(String title, Integer id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getId() {
        return id;
    }

}
