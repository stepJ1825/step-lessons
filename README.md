### PECS означает:

* Producer Extends - если параметр только производит данные (читается), используйте `? extends T`
* Consumer Super - если параметр только потребляет данные (записывается), используйте `? super T`

### Ключевые выводы:

1. Producer Extends (? extends T) - когда нужно только читать данные
2. Consumer Super (? super T) - когда нужно только записывать данные
3. Ни то, ни другое - если нужно и читать, и писать, используйте конкретный тип
4. PECS делает API более гибким и безопасным на этапе компиляции

Эти примеры показывают, как PECS помогает создавать более гибкие и типобезопасные API, работающие с различными уровнями
иерархии наследования.

### Что почитать (посмотреть)

[Java. Ковариантность и контравариантность обобщенных типов на примере ArrayList. Видео](https://www.youtube.com/watch?v=2yeFSrcTQh8&ab_channel=SergeyArkhipovJavaTutorials)

[Вариантность обобщенных типов в картинках и простых примерах](https://habr.com/ru/articles/795083/)

[Шпаргалка](https://disk.yandex.ru/i/Gs9GUas9q8gBCg)

[Презентация - PECS.pdf](src/main/resources/PECS.pdf)

### Примеры:
1. [PECSExample.java](src/main/java/by/step/PECSExample.java)
2. [NumberUtils.java](src/main/java/by/step/NumberUtils.java)
3. [ConsumerExample.java](src/main/java/by/step/ConsumerExample.java)
4. [AnimalExample.java](src/main/java/by/step/AnimalExample.java)
5. [AdvancedPECS.java](src/main/java/by/step/AdvancedPECS.java)
6. [CollectionsPECS.java](src/main/java/by/step/CollectionsPECS.java) 
