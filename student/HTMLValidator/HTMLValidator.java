public class HTMLValidator {

    public boolean validateHTML(String html) {
        String str = "<html><body>(<h1>.*</h1>|<h2>.*</h2>|<div>(<p>(<b>.*</b>|<i>.*</i>|.*)</p>)</div>)</body></html>";
        return html.matches(str);
    }
}
