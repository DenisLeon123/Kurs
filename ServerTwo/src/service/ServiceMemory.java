package service;

public class ServiceMemory {
    private static final int BYTES_TO_MBYTES_DEL = 1048576;

    public double getUsedBytes() {
        return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / BYTES_TO_MBYTES_DEL;
    }

    public double getMaxMemory() {
        return Runtime.getRuntime().maxMemory() / BYTES_TO_MBYTES_DEL;
    }

    public double getFreeBytes() {
        return this.getMaxMemory() - this.getUsedBytes();
    }

    public double getFreeMemory() {
        return getFreeBytes() / getMaxMemory() * 100;
    }
}
