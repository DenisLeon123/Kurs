public class Test {
    public static void main(String[] args) {
        ServiceMemory m = new ServiceMemory();
        System.out.println(m.getFreeBytes());
        System.out.println(m.getUsedBytes());
        System.out.println(m.getMaxMemory());
        System.out.println((double)m.getFreeBytes()/m.getMaxMemory()*100);
    }
}
