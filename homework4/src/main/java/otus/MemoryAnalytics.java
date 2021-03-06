package otus;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.ListenerNotFoundException;
import javax.management.Notification;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 * Created by svetlana on 4/27/17.
 */

public class MemoryAnalytics {
    private final int NORM_NAME_LENGTH = 25;
    private final long SIZE_KB = 1024;
    private final long SIZE_MB = SIZE_KB * 1024;
    private final long SIZE_GB = SIZE_MB * 1024;
    private final String SPACES = "                    ";
    private Map<String, Region> memRegions;

    private Logger logger = Logger.getLogger("MyLog");

    private class Region {
        private boolean heap;
        private String normName;
        public Region(String name, boolean heap) {
            this.heap = heap;
            normName = name.length() < NORM_NAME_LENGTH ? name.concat(SPACES.substring(0, NORM_NAME_LENGTH - name.length())) : name;
        }
        public boolean isHeap() {
            return heap;
        }
        public String getNormName() {
            return normName;
        }
    }

    private MemoryAnalytics (){
        memRegions = new HashMap<String, Region>(ManagementFactory.getMemoryPoolMXBeans().size());
        for(MemoryPoolMXBean mBean: ManagementFactory.getMemoryPoolMXBeans()) {
            memRegions.put(mBean.getName(), new Region(mBean.getName(), mBean.getType() == MemoryType.HEAP));
        }
    }

    private static class MemoryAnalyticsHolder{
        private final static MemoryAnalytics instance = new MemoryAnalytics();
    }

    public static MemoryAnalytics getInstance() {
        return MemoryAnalyticsHolder.instance;
    }

    private NotificationListener gcHandler = new NotificationListener() {
        @Override
        public void handleNotification(Notification notification, Object handback) {
            if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                GarbageCollectionNotificationInfo gcInfo = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                Map<String, MemoryUsage> memBefore = gcInfo.getGcInfo().getMemoryUsageBeforeGc();
                Map<String, MemoryUsage> memAfter = gcInfo.getGcInfo().getMemoryUsageAfterGc();
                StringBuilder sb = new StringBuilder();
                sb.append("[").append(gcInfo.getGcAction()).append(" / ").append(gcInfo.getGcCause())
                        .append(" / ").append(gcInfo.getGcName()).append(" / (");
                appendMemUsage(sb, memBefore);
                sb.append(") -> (");
                appendMemUsage(sb, memAfter);
                sb.append("), ").append(gcInfo.getGcInfo().getDuration()).append(" ms]");

                System.out.println(sb.toString());
                logger.info(sb.toString());

            }
        }
    };

    public void pSetLogger(FileHandler fHeader) {
        // set file fo recording of the log
        logger.addHandler(fHeader);
    }

    public void printUsage(boolean heapOnly) {
        for(MemoryPoolMXBean mBean: ManagementFactory.getMemoryPoolMXBeans()) {
            if (!heapOnly || mBean.getType() == MemoryType.HEAP) {
                printMemUsage(mBean.getName(), mBean.getUsage());
            }
        }
    }

    public void startGCMonitor() {
        for(GarbageCollectorMXBean mBean: ManagementFactory.getGarbageCollectorMXBeans()) {
            ((NotificationEmitter) mBean).addNotificationListener(gcHandler, null, null);
        }
    }

    public void stopGCMonitor() {
        for(GarbageCollectorMXBean mBean: ManagementFactory.getGarbageCollectorMXBeans()) {
            try {
                ((NotificationEmitter) mBean).removeNotificationListener(gcHandler);
            } catch(ListenerNotFoundException e) {
            }
        }
    }

    private void printMemUsage(String title, MemoryUsage usage) {
        System.out.println(String.format("%s%s\t%.1f%%\t[%s]",
                memRegions.get(title).getNormName(),
                formatMemory(usage.getUsed()),
                usage.getMax() < 0 ? 0.0 : (double)usage.getUsed() / (double)usage.getMax() * 100,
                formatMemory(usage.getMax())));
        logger.info(String.format("%s%s\t%.1f%%\t[%s]",
                memRegions.get(title).getNormName(),
                formatMemory(usage.getUsed()),
                usage.getMax() < 0 ? 0.0 : (double)usage.getUsed() / (double)usage.getMax() * 100,
                formatMemory(usage.getMax())));
    }

    private String formatMemory(long bytes) {
        if (bytes > SIZE_GB) {
            return String.format("%.2fG", bytes / (double)SIZE_GB);
        } else if (bytes > SIZE_MB) {
            return String.format("%.2fM", bytes / (double)SIZE_MB);
        } else if (bytes > SIZE_KB) {
            return String.format("%.2fK", bytes / (double)SIZE_KB);
        }
        return Long.toString(bytes);
    }

    private void appendMemUsage(StringBuilder sb, Map<String, MemoryUsage> memUsage) {
        for(Map.Entry<String, MemoryUsage> entry: memUsage.entrySet()) {
            if (memRegions.get(entry.getKey()).isHeap()) {
                sb.append(entry.getKey()).append(" used=")
                        .append(entry.getValue().getUsed() >> 10)
                        .append("K; ");
            }
        }
    }
}