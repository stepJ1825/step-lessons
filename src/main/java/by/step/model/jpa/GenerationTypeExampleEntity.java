//package by.step.model.jpa;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class GenerationTypeExampleEntity {
//    /**
//     * Использует отдельную таблицу в БД для хранения и генерации уникальных значений ключей (эмуляция
//     * последовательности).
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE,
//                    generator = "user_gen")
//    @TableGenerator(name = "user_gen",              // Уникальное имя генератора
//                    table = "id_generator",         // Имя таблицы в БД, которая хранит значения генераторов.
//                    pkColumnName = "gen_name",      // Имя колонки, которая хранит имя генератора (ключ в таблице)
//                    valueColumnName = "gen_value",  // Имя колонки, которая хранит текущее значение счётчика.
//                    pkColumnValue = "user_id",      // Значение, которое записывается в pkColumnName для данной сущности
//                    initialValue = 1,               // Начальное значение счётчика при первом обращении к генератору
//                    allocationSize = 1)             // Размер "пакета" ID, который провайдер забирает из БД за один раз
//    private Long id1;
//
//    /**
//     * Использует нативную последовательность базы данных (например, CREATE SEQUENCE в PostgreSQL, Oracle).
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,
//                    generator = "user_seq")
//    @SequenceGenerator(name = "user_seq",
//                       sequenceName = "user_id_seq",
//                       initialValue = 1,
//                       allocationSize = 1)
//    private Long id2;
//
//    /**
//     * Полагается на автоинкремент-колонку (AUTO_INCREMENT в MySQL, IDENTITY в SQL Server).
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id3;
//
//    /**
//     * Генерирует RFC 4122 UUID (Universally Unique Identifier) — 128-битное значение, обычно в формате строки.
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private String id4; // или UUID тип
//
//    // @formatter:off
//    /**
//     * Провайдер JPA сам выбирает подходящую стратегию в зависимости от диалекта БД.
//     * PostgreSQL             - SEQUENCE
//     * Oracle                 - SEQUENCE
//     * MySQL                  - IDENTITY
//     * SQL Server             - IDENTITY
//     * H2 (в режиме MySQL)    - IDENTITY
//     * H2 (по умолчанию)      - SEQUENCE или TABLE
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id5;
//}
