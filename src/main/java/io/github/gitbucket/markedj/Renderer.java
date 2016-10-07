package io.github.gitbucket.markedj;

import static io.github.gitbucket.markedj.Utils.*;

import java.util.Map;

public class Renderer {

    protected Options options;

    public Renderer(Options options){
        this.options = options;
    }

    public String code(String code, String lang, boolean escaped){
        if(lang != null){
            StringBuilder sb = new StringBuilder();
            if (escape(lang, true).equals("math")) {
                sb.append("<div class=\"" + options.getLangPrefix() + escape(lang, true) + " hljs\">\n");
                String[] lines = code.split("\n");
                for (String line : lines) {
                    if (!line.trim().equals("")) {
                        sb.append("$").append(line).append("$\n<br/>");
                    }
                }
                sb.append("\n</div>\n");
            } else {
                sb.append("<pre><code class=\"" + options.getLangPrefix() + escape(lang, true) + " hljs\">");
                if(escaped){
                    sb.append(code);
                } else {
                    sb.append(escape(code, true));
                }
                sb.append("\n</code></pre>\n");
            }
            return sb.toString();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("<pre><code>");
            if(escaped){
                sb.append(code);
            } else {
                sb.append(escape(code, true));
            }
            sb.append("\n</code></pre>\n");
            return sb.toString();
        }
    }

    public String blockquote(String quote){
        return "<blockquote>\n" + quote + "</blockquote>\n";
    }

    public String html(String html){
        return html;
    }

    public String heading(String text, int level, String raw){
        if (options.isHeaderIdSequential()) {
            return "<h" + level + " id=\"" + options.getHeaderPrefix() +
                    options.SequentialId() + "\">" + text + "</h" + level + ">\n";
        }
        return "<h" + level + " id=\"" + options.getHeaderPrefix() +
                raw.toLowerCase().replaceAll("[^\\w]+", "-") + "\">" + text + "</h" + level + ">\n";
    }

    public String hr() {
        if (options.isXhtml()){
            return "<hr/>\n";
        } else {
            return "<hr>\n";
        }
    }

    public String list(String body, boolean ordered){
        String listType;
        if(ordered){
            listType = "ol";
        } else {
            listType = "ul";
        }
        return "<" + listType + ">\n" + body + "</" + listType + ">\n";
    }

    public String listitem(String text){
        return "<li>" + text + "</li>\n";
    }

    public String paragraph(String text){
        return "<p>" + text + "</p>\n";
    }

    public String table(String header, String body){
        return "<table>\n<thead>\n" + header + "</thead>\n<tbody>\n" + body + "</tbody>\n</table>\n";
    }

    public String tablerow(String content){
        return "<tr>\n" + content + "</tr>\n";
    }

    public String tablecell(String content, TableCellFlags flags){
        String cellType;
        if(flags.isHeader()){
            cellType = "th";
        } else {
            cellType = "td";
        }

        String align = flags.getAlign();
        if(align != null){
            return "<" + cellType + " style=\"text-align: " + align + "\">" + content + "</" + cellType + ">\n";
        } else {
            return "<" + cellType + ">" + content + "</" + cellType + ">\n";
        }
    }

    public String strong(String text){
        return "<strong>" + text + "</strong>";
    }

    public String em(String text){
        return "<em>" + text + "</em>";
    }

    public String codespan(String text){
        return "<code>" + text + "</code>";
    }

    public String br(){
        if(options.isXhtml()){
            return "<br/>";
        } else {
            return "<br>";
        }
    }

    public String del(String text){
        return "<del>" + text + "</del>";
    }

    public String oembed(String href) {
        return "<a class=\"oembed\" href=\"" + href + "\">" + href + "</a>";
    }

    public String slide(String fileNo) {
        return "<var class=\"slideshow\" id=\"slide-" + fileNo +"\" slide=\"" + fileNo + "\">" + fileNo + "</var>";
    }

    public Object internallink(String noTerm) {
        String no = noTerm.substring("#".length());
        return "<var class=\"internallink\" id=\"internallink-" + no +"\" internallink=\"" + no + "\">" + noTerm + "</var>";
    }


    public String footnoteref(String key) {
        return "<sup class=\"footnote-ref\" id=\"fnref-" + escape(key) + "\">"
            + "<a href=\"#fn-" + escape(key) + "\">" + key + "</a></sup>";
    }

    public String footnote(Map<String, String> notes) {
        String out = "<ol class=\"footnotes\">";
        for (Map.Entry<String, String> noteEntry : notes.entrySet()) {
            String key = noteEntry.getKey();
            String text = noteEntry.getValue();
            out += "<li id=\"fn-" + key + "\">";
            out += text.trim();
            out += "<a href=\"#fnref-" + key + "\">&#8617;</a>";
            out += "</li>";
        }
        out += "</ol>";
        return out;
    }

    public String link(String href, String title, String text){
        if(options.isSanitize()){
            // TODO
        }

        String titleAttr = "";
        if(title != null){
            titleAttr = " title=\"" + title + "\"";
        }

        boolean external = false;
        if (href.startsWith("http")) {
            external = true;
        }
        
        StringBuilder builder = new StringBuilder();
        builder.append("<a href=\"").append(href).append("\"").append(titleAttr);
        if (external && options.isLinkTargetBlank()) {
            builder.append(" target=\"_blank\"");
        }
        builder.append(">").append(text).append("</a>");
        
        return builder.toString();
    }

    public String image(String href, String title, String text){
        String titleAttr = "";
        if(title != null){
            titleAttr = " title=\"" + title + "\"";
        }

        if(options.isXhtml()){
            return "<img src=\"" + href + "\" alt=\"" + text + "\"" + titleAttr + "/>";
        } else {
            return "<img src=\"" + href + "\" alt=\"" + text + "\"" + titleAttr + ">";
        }
    }

    public String nolink(String text){
        return escape(text);
    }

    public String text(String text){
        return text;
    }

    public static class TableCellFlags {
        private boolean header;
        private String align;

        public TableCellFlags(boolean header, String align){
            this.header = header;
            this.align = align;
        }

        public boolean isHeader() {
            return header;
        }

        public String getAlign() {
            return align;
        }
    }


}
