package com.nomura.sandeep.chronicle;

import gnu.trove.map.hash.TObjectLongHashMap;
import net.openhft.chronicle.Chronicle;
import net.openhft.chronicle.ChronicleQueueBuilder;
import net.openhft.chronicle.ExcerptAppender;
import net.openhft.chronicle.ExcerptTailer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

/**
 * @author Sandeep.Jakka
 */
public class ChronicleTest {
    private static final String TMP = System.getProperty("java.io.tmpdir");

    @NotNull
    private final Chronicle chronicle;
    //@NotNull
    //private final Excerpt excerpt ;
    @NotNull
    private final ExcerptAppender appender;

    @NotNull
    private final ExcerptTailer reader;


    private final TObjectLongHashMap<String> keyToExcerpt = new TObjectLongHashMap<String>() {
        @Override
        public long getNoEntryValue() {
            return -1;
        }
    };

    public ChronicleTest(String basePath) throws IOException {
        //   this.chronicle = new IndexedChronicle(basePath);
        this.chronicle = ChronicleQueueBuilder
                .indexed(basePath)
                //     .small()
                .build();
        this.appender = chronicle.createAppender();
        this.reader = chronicle.createTailer().toStart();
    }

    public static void main(String[] args) {
        String basePath = TMP + "/ChronicleTest";
        System.out.println("basePath = [" + basePath + "]");
        //ChronicleTools.deleteOnExit(basePath);
        ChronicleTest map = null, map2 = null;
        long start = 0, start2 = 0;
        int keys = 1000000;
        try {
            map = new ChronicleTest(basePath);
            map.load();
            start = System.nanoTime();

            for (int i = 0; i < keys; i++) {
                Map<String, String> props = new LinkedHashMap<String, String>();
                props.put("a", Integer.toString(i));
                props.put("b", "value-" + i);
                props.put("c", Double.toString(i / 1000));
                map.putForMap(Integer.toHexString(i), props);
            }
            map.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            map2 = new ChronicleTest(basePath);
            map2.load();
            start2 = System.nanoTime();
            //   int keys=1000000;
            for (int i = 0; i < keys; i++) {
                //  System.out.printf("i = " + i);
                Map<String, String> props = new LinkedHashMap<String, String>();
                props.put("a", Integer.toString(i));
                props.put("b", "value-" + i);
                props.put("c", Double.toString(i / 1000));
                //    System.out.println(props);
                Map<String, String> props2 = map2.getForMap(Integer.toHexString(i));
                assertEquals(props, props2);
            }
            // map.close();
            map2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long time = System.nanoTime() - start;
        long time2 = System.nanoTime() - start2;

        System.out.printf(" Read/Write time for 1 %d , 2 %d ", time, time2);

    }

    public void load() {
        while (reader.nextIndex()) {
            String key = reader.readUTF();
            keyToExcerpt.put(key, reader.index());
            reader.finish(); //ready for next read
        }
    }

    public void putForMap(String key, Map<String, String> map) {
        appender.startExcerpt(4096);
        appender.writeUTF(key);
        appender.writeMap(map);
        appender.finish();
    }

    public Map<String, String> getForMap(String key) {
        long value = keyToExcerpt.get(key);
        if (value < 0) {
            return Collections.emptyMap();
        }

        if (reader.index(value)) {
            //skip the val
            //   long by = reader.readStopBit();
            //  System.out.println("by  + \"val =\"  + val = " + by  + "val ="  + val);
            // reader.skip(by);
            reader.readUTF();
            Map<String, String> out = new HashMap<String, String>();
            reader.readMap(out, String.class, String.class);
            //  System.out.println(actual + "===========>" + out);
            return out;
        } else {
            System.out.printf("Gochi");
            return Collections.emptyMap();
        }
    }

    public void close() throws IOException {
        appender.close();
        reader.close();
        chronicle.close();
    }
}
