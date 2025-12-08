package by.step.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookWithTagsDto {
    private String title;
    private String genre;         // например, enum или строка
    private Long authorId;        // ID выбранного автора
    private Integer year;
    private List<Long> tagIds;    // выбранные теги (множественный выбор)
}
