### Hexlet tests and linter status:
[![Actions Status](https://github.com/RomanVoronovskiy/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/RomanVoronovskiy/java-project-78/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/f1f04d0d85fa0d0800d4/maintainability)](https://codeclimate.com/github/RomanVoronovskiy/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/f1f04d0d85fa0d0800d4/test_coverage)](https://codeclimate.com/github/RomanVoronovskiy/java-project-78/test_coverage)

## Цель
Валидатор данных – проект, направленный на прокачку проектирования архитектуры в объектно-ориентированном стиле. Здесь вам понадобится применять практически все, чему вы научились в курсах по ООП: проектирование структуры классов, композиция объектов, возможно наследование и, обязательно, fluent-интерфейс. Вам придется задумываться о глобальном и локальном состоянии, думать о расширяемости кода без его переписывания, соблюдать SOLID принципы.

## Описание
Валидатор данных – библиотека, с помощью которой можно проверять корректность любых данных. Подобных библиотек множество в каждом языке, так как практически все программы работают с внешними данными, которые нужно проверять на корректность. В первую очередь речь идет про данные форм заполняемых пользователями. За основу для проекта взята библиотека yup.

## Пример использования:

```
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

Validator v = new Validator();

// Строки
StringSchema schema = v.string().required();

schema.isValid("what does the fox say"); // true
schema.isValid(""); // false

// Числа
NumberSchema schema = v.number().required().positive();

schema.isValid(-10); // false
schema.isValid(10); // true

// Объект Map с поддержкой проверки структуры
Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());

MapSchema schema = v.map().sizeof(2).shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "");
human2.put("age", null);
schema.isValid(human2); // false
```

## Предметно-ориентированные языки (DSL)
Интерфейс библиотеки для валидации – яркий пример DSL, специализированного языка, позволяющего декларативно (описательно) описывать то, что вы хотите от кода. Код, написанный в таком стиле, читается значительно легче, чем работа с прямым созданием объектов. Во многом этот подход базируется на паттерне fluent-интерфейс.

## Архитектура
Ключевая часть внутренней архитектуры – организация валидаторов. Эту задачу можно решить множеством разных способов, но только некоторые из них дают по-настоящему удобную и расширяемую структуру без излишней сложности. Проектируя архитектуру, крайне легко перестараться и сделать что-то очень сложное.

## Тестирование и Отладка
Автоматизированные тесты – неотъемлемая часть профессиональной разработки. Валидатор данных – идеальный проект для прокачки навыка тестирования. Он достаточно простой и удобный для написания тестов, и достаточно сложный для того, чтобы прочувствовать важность этих тестов во время рефакторинга и отладки. В отличие от практики Хекслета, в этом случае вам предстоит писать тесты самостоятельно. Причем это можно делать до кода, практикуя TDD.

Для написания тестов используется фреймворк JUnit.