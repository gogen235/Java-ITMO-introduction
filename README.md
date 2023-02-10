# java-ITMO-introduction
Домашние задания с курса Введение в программирование(Java).
## Условия:
### wortStat
* WortStatInput
  * Разработайте класс WordStatInput, подсчитывающий статистику встречаемости слов во входном файле.
  * Словом называется непрерывная последовательность букв, апострофов (') и дефисов (Unicode category Punctuation, Dash). Для подсчета статистики слова приводятся к нижнему регистру.
  * Выходной файл должен содержать все различные слова, встречающиеся во входном файле, в порядке их появления. Для каждого слова должна быть выведена одна строка, содержащая слово и число его вхождений во входном файле.
  * Имена входного и выходного файла задаются в качестве аргументов командной строки. Кодировка файлов: UTF-8.
* WortStatInputShingles
  * Модификация к WortStatInput.
  * Выходной файл должен содержать все различные подстроки длины 3 слов, встречающихся во входном файле, в лексикографическом порядке. Слова длины меньшей 3 используются как есть.
* MyScanner
  * Реализуйте свой аналог класса Scanner на основе Reader.
* IntList
  * Реализуйте и примените класс IntList, компактно хранящий список целых чисел.
* Wspp
  * Разработайте класс Wspp, который будет подсчитывать статистику встречаемости слов во входном файле.
  * Словом называется непрерывная последовательность букв, апострофов и тире (Unicode category Punctuation, Dash). Для подсчета статистики, слова приводятся к нижнему регистру.
  * Выходной файл должен содержать все различные слова, встречающиеся во входном файле, в порядке их появления. Для каждого слова должна быть выведена одна строка, содержащая слово, число его вхождений во входной файл и номера вхождений этого слова среди всех слов во входном файле.
  * Имена входного и выходного файла задаются в качестве аргументов командной строки. Кодировка файлов: UTF-8.
  * Программа должна работать за линейное от размера входного файла время.
* WsppCountLastL
  * Модификация к Wspp.
  * В выходном файле слова должны быть упорядочены по возрастанию числа вхождений, а при равном числе вхождений – по порядку первого вхождения во входном файле.
  * Вместо номеров вхождений во всем файле надо указывать только последнее вхождение в каждой строке.
### markup
  * Разработайте набор классов для текстовой разметки.
  * Класс Paragraph может содержать произвольное число других элементов разметки и текстовых элементов.
  * Класс Text – текстовый элемент.
  * Классы разметки Emphasis, Strong, Strikeout – выделение, сильное выделение и зачеркивание. Элементы разметки могут содержать произвольное число других элементов разметки и текстовых элементов.
  * Все классы должны реализовывать метод toMarkdown(StringBuilder), который должен генерировать Markdown-разметку по следующим правилам:
    * текстовые элементы выводятся как есть;
    * выделенный текст окружается символами '*';
    * сильно выделенный текст окружается символами '__';
    * зачеркнутый текст окружается символами '~'.
### md2html
  * Разработайте конвертер из Markdown-разметки в HTML.
  * Конвертер должен поддерживать следующие возможности:
    * Абзацы текста разделяются пустыми строками.
    * Элементы строчной разметки: выделение (* или _), сильное выделение (** или __), зачеркивание (--), код (`)
    * Заголовки (# * уровень заголовка)
  * Конвертер должен называться md2html.Md2Html и принимать два аргумента: название входного файла с Markdown-разметкой и название выходного файла c HTML-разметкой. Оба файла должны иметь кодировку UTF-8.
### game 
  * Реализуйте игру m,n,k (k в ряд на доске m×n).
  * Добавьте обработку ошибок ввода пользователя. В случае ошибочного хода пользователь должен иметь возможность сделать другой ход.
  * Добавьте обработку ошибок игроков. В случае ошибки игрок автоматически проигрывает.
  * Доска должна производить обработку хода (проверку корректности, изменение состояния и определение результата) за O(k).
  * Предотвратите жульничество: у игрока не должно быть возможности достать Board из Position.
### expression
* base
  * Разработайте классы Const, Variable, Add, Subtract, Multiply, Divide для вычисления выражений с одной переменной в типе int (интерфейс Expression).          
  * При вычислении такого выражения вместо каждой переменной подставляется значение, переданное в качестве параметра методу evaluate.
  * Метод toString должен выдавать запись выражения в полноскобочной форме.
  * Метод toMiniString (интерфейс ToMiniString) должен выдавать выражение с минимальным числом скобок.
  * Реализуйте метод equals, проверяющий, что два выражения совпадают.
* parser
  * Доработайте предыдущее домашнее задание, так что бы выражение строилось по записи вида
x * (x - 2)*x + 1
  * В записи выражения могут встречаться:
    * бинарные операции: умножение *, деление /, сложение + и вычитание -;
    * унарный минус -;
    * переменные x, y и z;
    * целочисленные константы в десятичной системе счисления, помещающиеся в 32-битный знаковый целочисленный тип;
    * круглые скобки для явного обозначения приоритета операций;
    * произвольное число пробельных символов в любом месте, не влияющем на однозначность понимания формулы (например, между операцией и переменной, но не внутри констант).
  * Приоритет операций, начиная с наивысшего
    * унарный минус;
    * умножение и деление;
    * сложение и вычитание.
* exceptions
  * Добавьте в программу, вычисляющую выражения, обработку ошибок, в том числе:
    * ошибки разбора выражений;
    * ошибки вычисления выражений.            
  * При выполнении задания следует обратить внимание на дизайн и обработку исключений.
  * Человеко-читаемые сообщения об ошибках должны выводиться на консоль.
