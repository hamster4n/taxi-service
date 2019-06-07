package ua.taxi.best.entity;

public class Model {
    private Long id;
    private String name;
    private String photo;

    public Model() {
    }

    private Model(ModelBuilder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.photo = builder.photo;
    }

    public static ModelBuilder builder(){
        return new ModelBuilder();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public static class ModelBuilder{
        private Long id;
        private String name;
        private String photo;

        private ModelBuilder(){}

        public Model build(){
            return new Model(this);
        }

        public ModelBuilder withId(Long id){
            this.id = id;
            return this;
        }

        public ModelBuilder withName(String name){
            this.name = name;
            return this;
        }

        public ModelBuilder withPhoto(String photo){
            this.photo = photo;
            return this;
        }
    }
}
