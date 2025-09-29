The line:

```java
Image image = new Image(1000, 1000);
```

is **creating a new instance** of the `Image` class (defined in the shapes package), with a *width and height of 1000 pixels each*. Here's a detailed breakdown of what's happening:

### 1\. Class Instantiation

-   `Image image = new Image(1000, 1000);`
    
-   You're creating a new **object** of the `Image` class and assigning it to the variable `image`.
    

### 2\. Constructor Call

-   `new Image(1000, 1000)` calls the constructor of the `Image` class:
    
    ```java
    public Image(int width, int height)
    ```
    
    This constructor initializes a blank canvas of size 1000x1000 pixels.
    

### 3\. BufferedImage Creation

```java
this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
```

#### ➤ What is `BufferedImage`?

-   `BufferedImage` is a class in `java.awt.image` used to store an **image in memory**.
    
-   It acts like a **canvas** that you can read from and write to pixel-by-pixel.
    
-   It supports various image types (ARGB, RGB, Grayscale, etc.) and lets you directly manipulate pixels.
    

#### ➤ Parameters

-   `width = 1000` — number of horizontal pixels.
    
-   `height = 1000` — number of vertical pixels.
    
-   `BufferedImage.TYPE_INT_RGB` — format of each pixel:
    
    -   Each pixel stores **3 color channels**: **Red**, **Green**, and **Blue**.
        
    -   **No alpha channel** (no transparency).
        
    -   Each pixel is packed into a **single 32-bit `int`**, but only the **lowest 24 bits** are used for color.
        

#### 🔬 How pixels are stored

-   Internally, `BufferedImage` uses a **raster** (a 2D array of pixels).
    
-   For `TYPE_INT_RGB`, each pixel is stored as a single 32-bit integer:
    
    ```
    0x00RRGGBB
    ```
    
    -   The highest byte (alpha) is **0x00** (not used).
        
    -   `RR` = red (0-255)
        
    -   `GG` = green (0-255)
        
    -   `BB` = blue (0-255)
        

#### ➤ Behind the scenes

When you call:

```java
new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
```

Java does the following internally:

1.  Allocates memory for 1,000,000 pixels (`1000 × 1000`).
    
2.  Each pixel is initialized to black (`0x000000`), which is RGB(0, 0, 0).
    
3.  A `WritableRaster` is created to manage pixel data.
    
4.  A `ColorModel` is set to interpret pixel integers as RGB colors.
    
5.  A full image object is returned that supports reading and writing pixels.
    

---

### 4. Graphics2D Object Creation

```java
this.g2d = image.createGraphics();
```

#### ➤ What is `Graphics2D`?

-   `Graphics2D` is a subclass of `Graphics` in Java that provides **more sophisticated control** over geometry, coordinate transformations, color management, and rendering.
    

#### ➤ What does `createGraphics()` do?

-   It creates a `Graphics2D` context tied directly to the `BufferedImage`.
    
-   This allows you to **draw on the image** like a physical canvas using Java 2D API.
    

##### With this object, you can now:

-   Draw shapes (`drawRect`, `drawOval`, `drawLine`, etc.)
    
-   Fill areas (`fillRect`, `fillPolygon`, etc.)
    
-   Set colors, fonts, strokes (line thickness), transformations
    
-   Draw images or text
    
-   Apply anti-aliasing or rendering hints
    

#### ➤ How it works internally:

-   `BufferedImage.createGraphics()` returns a new instance of `SunGraphics2D` (or equivalent class depending on JVM implementation).
    
-   It binds the image’s internal raster to the graphics context.
    
-   Any operation done through `g2d` directly modifies the pixel data of the `BufferedImage`.
    

---

## ✅ Summary of These Two Lines:

| Line                                          | Description                                                                                            |
| --------------------------------------------- | ------------------------------------------------------------------------------------------------------ |
| `new BufferedImage(1000, 1000, TYPE_INT_RGB)` | Allocates a 1000×1000 image in memory using 24-bit RGB format (black by default).                      |
| `.createGraphics()`                           | Returns a `Graphics2D` object that allows drawing shapes, lines, text, and more directly on the image. |

> Can't we draw directly without the `Graphics2D` API ?? [[graphic2d|🔗]]