## Составить цепочку слов

В методе `main` считай с консоли имя файла, который содержит слова, разделенные пробелом.  
В методе `getLine` используя **StringBuilder** расставь все слова в таком порядке, чтобы последняя буква данного слова совпадала с первой буквой следующего не учитывая регистр.  
Каждое слово должно участвовать **1** раз.  
Считай, что абсолютно все слова из исходного списка могут (и **должны!**) быть включены в результат (лишних слов нет).  
Метод `getLine` должен возвращать любой правильный вариант при наличии нескольких таковых (см. пример).  
Слова разделять пробелом.  
Вывести полученную строку на экран.

**Пример тела входного файла:**
<pre>
Киев Нью-Йорк Амстердам Вена Мельбурн
</pre>

**Результат:**
<pre>
Амстердам Мельбурн Нью-Йорк Киев Вена
</pre>
или
<pre>
Вена Амстердам Мельбурн Нью-Йорк Киев
</pre>
или
<pre>
Мельбурн Нью-Йорк Киев Вена Амстердам
</pre>
и т.п.
