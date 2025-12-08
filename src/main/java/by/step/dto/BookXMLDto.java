package by.step.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;


@Data
@JacksonXmlRootElement(localName = "book")
public class BookXMLDto {

    @JacksonXmlProperty(isAttribute = true) // id как атрибут (опционально)
    private Long id;

    @JacksonXmlProperty(localName = "title")
    private String title;

    @JacksonXmlProperty(localName = "author")
    private String author;

    @JacksonXmlProperty(localName = "year")
    private Integer year;

    @JacksonXmlProperty(localName = "available")
    private Boolean available;

    @JacksonXmlProperty(localName = "rating")
    private Double rating;

    @JacksonXmlProperty(localName = "isbn")
    private String isbn; // может быть null → будет отсутствовать или как <isbn xsi:nil="true"/>

    @JacksonXmlElementWrapper(localName = "tags")
    @JacksonXmlProperty(localName = "tag")
    private List<String> tags;

    @JacksonXmlProperty(localName = "publisher")
    private Publisher publisher;

    @JacksonXmlElementWrapper(localName = "translations")
    @JacksonXmlProperty(localName = "translation")
    private List<Translation> translations;

    @JacksonXmlProperty(localName = "metadata")
    private Metadata metadata;

    // === Вложенные классы ===

    @Data
    public static class Publisher {
        @JacksonXmlProperty(localName = "name")
        private String name;

        @JacksonXmlProperty(localName = "country")
        private String country;

        @JacksonXmlProperty(localName = "founded")
        private Integer founded;

        @JacksonXmlProperty(localName = "active")
        private Boolean active;
    }

    @Data
    public static class Translation {
        @JacksonXmlProperty(localName = "language")
        private String language;

        @JacksonXmlProperty(localName = "title")
        private String title;

        @JacksonXmlProperty(localName = "translator")
        private String translator;
    }

    @Data
    public static class Metadata {
        @JacksonXmlProperty(localName = "pages")
        private Integer pages;

        @JacksonXmlElementWrapper(localName = "dimensions")
        @JacksonXmlProperty(localName = "dimension")
        private List<Double> dimensions;

        @JacksonXmlProperty(localName = "hasIllustrations")
        private Boolean hasIllustrations;

        @JacksonXmlElementWrapper(localName = "reviews")
        @JacksonXmlProperty(localName = "review")
        private List<String> reviews; // пустой список → <reviews/>
    }
}
