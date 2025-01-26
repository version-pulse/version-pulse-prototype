package io.versionpulse.doc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
public class CreateModel {
    private Parent parent;
    private Icon icon;
    private List<Title> title;
    private Properties properties;
     private Boolean is_inline;

    /**
     * private LocalDateTime createTime;
     * private LocalDateTime lastEditedTime;
     * private String url;
     */

    @Data
    @AllArgsConstructor
    public static class Parent {
        private String type;
        private String page_id;
    }

    @Data
    @AllArgsConstructor
    public static class Icon {
        private String type;
        private String emoji;
    }

    @Data
    @AllArgsConstructor
    public static class Title {
        private String type;
        private Text text;

        @Data
        @AllArgsConstructor
        public static class Text {
            private String content;
            private String link;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Properties {
        private Name name;
        private Description description;
        private Check check;
        private Method method;
        private Path path;
        private By by;

        @Data
        @AllArgsConstructor
        public static class Path {
            private Map<String, Object> rich_text;
        }
        @Data
        @AllArgsConstructor
        public static class Name {
            private Map<String, Object> title;
        }

        @Data
        @AllArgsConstructor
        public static class Description {
            private Map<String, Object> rich_text;
        }

        @Data
        @AllArgsConstructor
        public static class Check {
            private Map<String, Object> checkbox;
        }

        @Data
        @AllArgsConstructor
        public static class Method {
            private Select select;

            @Data
            @AllArgsConstructor
            public static class Select {
                private List<Option> options;

                @Data
                @AllArgsConstructor
                public static class Option {
                    private String name;
                    private String color;
                }
            }
        }
        @Data
        @AllArgsConstructor
        public static class By {
            private Map<String, Object> people;
        }
    }
}
