package by.step.common;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO для представления информации о валюте из внешнего API.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDto {

    @JsonProperty("Cur_ID")
    private Long id;

    @JsonProperty("Cur_ParentID")
    private Long parentId;

    @JsonProperty("Cur_Code")
    private String code;

    @JsonProperty("Cur_Abbreviation")
    private String abbreviation;

    @JsonProperty("Cur_Name")
    private String name;

    @JsonProperty("Cur_Name_Bel")
    private String nameBel;

    @JsonProperty("Cur_Name_Eng")
    private String nameEng;

    @JsonProperty("Cur_QuotName")
    private String quotationName;

    @JsonProperty("Cur_QuotName_Bel")
    private String quotationNameBel;

    @JsonProperty("Cur_QuotName_Eng")
    private String quotationNameEng;

    @JsonProperty("Cur_NameMulti")
    private String nameMulti;

    @JsonProperty("Cur_Name_BelMulti")
    private String nameBelMulti;

    @JsonProperty("Cur_Name_EngMulti")
    private String nameEngMulti;

    @JsonProperty("Cur_Scale")
    private Integer scale;

    @JsonProperty("Cur_Periodicity")
    private Integer periodicity;

    @JsonProperty("Cur_DateStart")
    private LocalDateTime dateStart;

    @JsonProperty("Cur_DateEnd")
    private LocalDateTime dateEnd;
}
