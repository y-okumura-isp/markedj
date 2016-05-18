package io.github.gitbucket.markedj;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class ExtendParseTest {
    
    public static void main(String[] args) throws IOException {
        ExtendParseTest test = new ExtendParseTest();
        test.debug();
    }
    
    private void debug() throws IOException {
        Options options = new Options();
        options.setBreaks(true);
        options.setLinkTargetBlank(true);
        options.setHeaderPrefix("markdown-agenda-");
        options.setHeaderIdSequential(true);

        String md = loadResourceAsString("debug.md");
        String result = Marked.marked(md, options);
        System.out.println(result);
    }
    
    @Test
    public void testHeaderId() throws Exception {
        Options options = new Options();
        options.setBreaks(true);
        options.setLinkTargetBlank(true);
        options.setHeaderPrefix("markdown-agenda-");
        options.setHeaderIdSequential(true);

        String md = loadResourceAsString("header_id.md");
        String result = Marked.marked(md, options);
        System.out.println(result);
        String expect = loadResourceAsString("header_id.html");
        System.out.println(expect);
        assertEquals(expect, result);
    }
    

    
    

    @Test
    public void testLinkTarget() throws Exception {
        Options options = new Options();
        options.setBreaks(true);
        options.setLinkTargetBlank(true);

        String md = loadResourceAsString("link_target.md");
        String result = Marked.marked(md, options);
//        System.out.println(result);
        String expect = loadResourceAsString("link_target.html");
//        System.out.println(expect);
        assertEquals(expect, result);
    }

    private String loadResourceAsString(String path) throws IOException {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024 * 8];
            int length = 0;
            while ((length = in.read(buf)) != -1) {
                out.write(buf, 0, length);
            }
            return new String(out.toByteArray(), "UTF-8");
        } finally {
            in.close();
        }
    }

}
