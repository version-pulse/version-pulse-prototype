package io.versionpulse.doc.models;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateModel {
    public Parent parent;
    public Properties properties;
    public List<Child> children;

    @AllArgsConstructor
    public static class Parent {
        public String database_id;
    }

    public static class Properties {
        public Name name;
        public Description description;
        public Path path;
        public Check check;
        public Method method;

        public static class Name {
            public List<Title> title;

            public static class Title {
                public Text text;

                public static class Text {
                    public String content;
                }
            }
        }

        public static class Description {
            public List<RichText> rich_text;

            public static class RichText {
                public Text text;

                public static class Text {
                    public String content;
                }
            }
        }

        public static class Path {
            public List<RichText> rich_text;

            public static class RichText {
                public Text text;

                public static class Text {
                    public String content;
                }
            }
        }

        public static class Check {
            public boolean checkbox;
        }

        public static class Method {
            public Select select;

            public static class Select {
                public String name;
            }
        }
    }

    public static class Child {
        public String object;
        public String type;
        public Heading2 heading_2;
        public Paragraph paragraph;

        public static class Heading2 {
            public List<RichText> rich_text;

            public static class RichText {
                public Text text;

                public static class Text {
                    public String content;
                }
            }
        }

        public static class Paragraph {
            public List<RichText> rich_text;

            public static class RichText {
                public Text text;

                public static class Text {
                    public String content;
                    public Link link;

                    public static class Link {
                        public String url;
                    }
                }
            }
        }
    }
}