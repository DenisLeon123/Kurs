public class ServiceMemory {
    private static final int BYTES_TO_MBYTES_DEL = 1048576;

    public long getUsedBytes() {
        return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / BYTES_TO_MBYTES_DEL;
    }

    public long getMaxMemory() {
        return (Runtime.getRuntime().maxMemory() / BYTES_TO_MBYTES_DEL);
    }

    public long getFreeBytes() {
        return this.getMaxMemory() - this.getUsedBytes();
    }

}
