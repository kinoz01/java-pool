
You've probably heard the term **object** in a programming context before. But what does it really mean? Let's start by looking at some real-world objects, like pens, books, smartphones, computers, etc.

[![Image 2: All sorts of objects!](https://user.oc-static.com/upload/2018/12/19/15452115917643_AdobeStock_109504367.jpg)](https://user.oc-static.com/upload/2018/12/19/15452115917643_AdobeStock_109504367.jpg)

All sorts of objects!

Each object comes in different forms and shapes, but you can classify different versions of the same object into a category or group. It's why you can go to a furniture store and recognize different items as "chairs" even if they look very different from one another.

You recognize all of these different objects as being part of the same group or type.You notice commonalities between different objects, collect the information, and create a mental representation for a given category of objects.

For example, there are different kinds of books out there, but they all tend to have a title, an author, a cover, pages, etc. In other words, individual books all have similar attributes that allow you to classify them in your mind as part of the category "book."

[![Image 3: Books of different sizes, colors, and lengths](https://user.oc-static.com/upload/2018/12/19/15452117745311_AdobeStock_106263298.jpg)](https://user.oc-static.com/upload/2018/12/19/15452117745311_AdobeStock_106263298.jpg)

Books of different sizes, colors, and lengths

This mental list of attributes we've just described for a book acts as a kind of **blueprint** for that object. In programming, it's called a **class.** When creating a class,you can come up with any custom name, which is why they are called a **named type.** As you'll see, they also allow you to group lots of details together, which is also why they can be referred to as**complex types**.On the other hand, the simple types you are already familiar with, like`int`or`double`, have names that are predefined by the programming language and cannot be modified.

How do we come up with class names?

Similar to naming variables, class names must be descriptive and spelled out (remember, avoid abbreviations!). A key difference is that, instead of using standard camelCase, **the first letter should also be capital**like`MarvelousCreature`and not`marvelousCreature`.

### Designing classes

To see how to design a class, let's continue with the book example. Below, we've identified a sample of information that could describe any given book:

*   title

*   author

*   number of pages

*   publisher

Those are **attributes** of any book in real life. In the context of classes, those attributes are called **fields**in Java. The fields of a class are simply a fancy name for something you're already familiar with: **variables!**

Now, you've got the theory, so let's put this in writing!

To declare a class in Java, use a keyword`class`followed by a custom name. After that, end it with curly braces (`{}`) that will embrace the entire contents.This includes the full list of properties:

```java
class Book {
// functionality of the class
}
```

Now let's add fields as defined earlier:

```java
class Book {
    String title;
    String author;
    int numberOfPages;
    String publisher="OC";
}
```

Notice how the first three don't have values, but the last one does? If you are a publisher who wants to catalog your books, then you know that the publisher value is always going to be the same no matter what book you produce. The book titles, authors, and page numbers, however, will change depending on the particular book.

If you go back to the original example of an online bookstore, though,the class would look more like this:

```java
class Book {
    String title;
    String author;
    int numberOfPages;
    String publisher;
}
```

Since you'll have books from lots of different publishers,you can't put in a default value for all of them. So, here, define the field and put in a value later!

### Utilizing classes

You've got a brand new type - `Book` 📖 - sorted out! What can you do with it? After all, classes are more abstract or conceptual. Class fields are like a template on an online bookstore: no matter what book you look for, the same information will pop up (title, author, number of pages, etc.).

But when you're searching, you don't just type "book," right? That's not useful! You need a **specific instance** of a book, say, _Alice in Wonderland_. You're looking for an actual object you can page through and read. It's the same in computer programming.

To work with a class, you need to create a **concrete object** of that class. In other words, you need a specific object, like a particular book (_Alice in Wonderland_). That specific book is called an **instance** of a class! As its name implies, the process is called **instantiating** or **initializing** an object. For that,you create a variable of the class.

In Java, each field of the created object must have a value. Those values can be provided in a few ways. You saw one in the publisher example: hard-setting a value in the class definition.

Another way is providing a value in the statement that creates the class. Let's look at how to do that. Here is the code for creating a book with values provided at the moment the object is created:

[![Image 4: Instantiation of an object](https://user.oc-static.com/upload/2018/12/19/15452208786759_Capture%20d%E2%80%99e%CC%81cran%202018-12-19%20a%CC%80%2013.01.02.png)](https://user.oc-static.com/upload/2018/12/19/15452208786759_Capture%20d%E2%80%99e%CC%81cran%202018-12-19%20a%CC%80%2013.01.02.png)

Instantiation of an object

As you can see, there are a few different elements. First, do a regular declaration of a variable, with its name`myBook`and its type`Book`. Remember how we said that a class is a named complex type? Well, here's the proof! Instead of`int`,`double`or`string`, our type is the class we've come up with! 💫

Now, here comes the cool part. The variable is initialized with the object creation expression `new Book("Coding is art","Becky James",425);`. This expression is composed of the`new`keyword, followed by the name of the class again (`Book`), and some parentheses`()`with values inside.As you can see, the parenthesis contain a specified value for each of the original fields: `title`, `author`, and `numberOfPages`. We've made sure to separate each one with a comma. Finally, end with a semi-colon`;`. With that, you've created an instance of the class Book! 📔

### Working with attributes (fields)

Phew! That was a lot of vocabulary and concepts. Before we move on, let's recap with a quick diagram:

[![Image 5: From class to object!](https://user.oc-static.com/upload/2018/12/19/15452218134233_Capture%20d%E2%80%99e%CC%81cran%202018-12-19%20a%CC%80%2013.16.39.png)](https://user.oc-static.com/upload/2018/12/19/15452218134233_Capture%20d%E2%80%99e%CC%81cran%202018-12-19%20a%CC%80%2013.16.39.png)

From class to object!

Is that better? Just remember:

*   Use a class as a template for your future objects. In a class, define the name and type of some variables. In Java, these are called attributes generally, and fields specifically.

*   To use the cool plan you made, you have to create an object using the process of instantiation. This means declaring a variable with your class as a type, then using the object creation expression you saw above.

*   When you instantiate an object, you create values for each of the fields you've already defined in your class.

*   An object is called an instance of a class.

Now you're ready! You've got your book object, but say you change your mind about the value of your variables. How do you access the fields associated with your shiny new object?

A common way to access fields in many programming languages is using what's called a **dot notation.** It means you need to write the name of an instance or object followed by an attribute name of interest, separated by a dot:`instanceVariableName.attributeName`.

```java
myBook.title = "Coding is Art";
myBook.author = "Becky James";
myBook.numberOfPages = myBook.numberOfPages + 10;
````

Now you can set change the value of fields within your object! Imagine that you've added ten pages because you forgot to add the index of the book.You could either type the new number directly or add ten pages to the existing value like in the third line. This is pretty handy when making small changes. 🙂

### Summary

*   A **class** is a blueprint of an object.

*   A variable of a class is called an **instance of a class** or an **object.**

*   A class allows you to **create complex types** by grouping its attributes by defining **fields.**

*   To create an **object,**you need to declare a variable of a class and instantiate it.

*   The **dot notation** provides access to **fields.**