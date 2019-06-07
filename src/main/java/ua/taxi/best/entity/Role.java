package ua.taxi.best.entity;

public class Role {
    private Long id;
    private String title;
    private String description;

    public Role() {
    }

    private Role(RoleBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
    }

    public static RoleBuilder builder(){
        return new RoleBuilder();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public static class RoleBuilder{
        private Long id;
        private String title;
        private String description;

        private RoleBuilder() {
        }

        public Role build(){
            return new Role(this);
        }

        public RoleBuilder withId(Long id){
            this.id = id;
            return this;
        }

        public RoleBuilder withTitle(String title){
            this.title = title;
            return this;
        }

        public RoleBuilder withDescription(String description){
            this.description = description;
            return this;
        }
    }
}
