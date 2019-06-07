package ua.taxi.best.entity;

public class ComfortType {
    private Long id;
    private String title;
    private String description;

    public ComfortType() {
    }

    private ComfortType(ComfortTypeBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
    }

    public static ComfortTypeBuilder builder() {
        return new ComfortTypeBuilder();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class ComfortTypeBuilder {
        private Long id;
        private String title;
        private String description;

        private ComfortTypeBuilder() {
        }

        public ComfortType build() {
            return new ComfortType(this);
        }

        public ComfortTypeBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public ComfortTypeBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public ComfortTypeBuilder withDescription(String description) {
            this.description = description;
            return this;
        }
    }
}
