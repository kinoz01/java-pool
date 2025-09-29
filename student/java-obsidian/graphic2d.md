**You absolutely can draw directly on a `BufferedImage`** without using the `Graphics2D` API — and in fact, your custom `Image` class is already doing that with this method:

```java
@Override
public void display(int x, int y, Color color) {
    if (x >= 0 && x < width && y >= 0 && y < height) {
        image.setRGB(x, y, color.getRGB());
    }
}
```

---

## ✅ Drawing Without `Graphics2D`: Using `setRGB(...)`

### ➤ How It Works

-   `BufferedImage.setRGB(int x, int y, int rgb)` sets the color of a single pixel at `(x, y)`.
    
-   `Color.getRGB()` converts a `java.awt.Color` to a 32-bit `int` representing RGB color.
    

### ➤ Example

You can draw a red dot at (100, 200) like this:

```java
image.setRGB(100, 200, Color.RED.getRGB());
```

Or in your case:

```java
Image img = new Image(1000, 1000);
img.display(100, 200, Color.RED); // custom method wraps setRGB
```

---

## 🛠️ Pros vs Cons of Direct Pixel Manipulation

### ✅ Pros

-   **Fine control**: Set or read individual pixels.
    
-   **Efficient for pixel-level operations**: Great for procedural generation, filters, etc.
    
-   No dependency on `Graphics2D`.
    

### ❌ Cons

-   **No support for drawing shapes, text, curves, strokes, or transformations**.
    
-   Tedious for drawing complex graphics (like lines, filled shapes, etc.).
    
-   Anti-aliasing, strokes, and styles require manual handling (very complex).
    

---

## 👨‍💻 When to Use Which?

| Task | Use `setRGB` (direct pixel) | Use `Graphics2D` |
| --- | --- | --- |
| Pixel-by-pixel drawing | ✅ | ❌ |
| Drawing lines, rectangles, ovals | ❌ (too hard) | ✅ |
| Applying fonts, text | ❌ | ✅ |
| Anti-aliasing and styles | ❌ | ✅ |
| Procedural image filters (blur, brightness, etc.) | ✅ | ❌ |

---

## ✅ Final Answer:

Yes, you **can** draw directly on `BufferedImage` using `setRGB()` or your `display()` method. This gives you **manual pixel-level control**, which is perfect for some cases. However, for drawing structured shapes, styled lines, or text, `Graphics2D` is much more practical.