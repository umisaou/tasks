## Знакомство с тегами

Считайте с консоли имя файла, который имеет HTML-формат.

**Пример:**
<pre>
Info about Leela &lt;span xml:lang="en" lang="en">&lt;b>&lt;span>Turanga Leela
&lt;/span>&lt;/b>&lt;/span>&lt;span>Super&lt;/span>&lt;span>girl&lt;/span>
</pre>

Первым параметром в метод main приходит тег. Например, "**span**".  
Вывести на консоль все теги, которые соответствуют заданному тегу.  
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле.  
Количество пробелов, **\n**, **\r** не влияют на результат.  
Файл не содержит тег **CDATA**, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нет.  
Тег может содержать вложенные теги.

**Пример вывода:**
<pre>
&lt;span xml:lang="en" lang="en">&lt;b>&lt;span>Turanga Leela&lt;/span>&lt;/b>&lt;/span>
&lt;span>Turanga Leela&lt;/span>
&lt;span>Super&lt;/span>
&lt;span>girl&lt;/span>
</pre>

**Шаблон тега:**
<pre>
&lt;tag>text1&lt;/tag>
&lt;tag text2>text1&lt;/tag>
&lt;tag
text2>text1&lt;/tag>
</pre>

**text1**, **text2** могут быть пустыми
