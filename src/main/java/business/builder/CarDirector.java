package business.builder;

public class CarDirector {

    public static CarBuilder createCarBuilder(String type) {
        CarBuilder builder = new CarBuilder();
        switch (type) {
            case "Diesel":
                return builder.withFuelType("Diesel"); // 预定义燃料类型
            case "Electronic":
                return builder.withFuelType("Electronic");
            case "Hybrid":
                return builder.withFuelType("Hybrid");
            default:
                System.out.println("only three types：Diesel, Electronic, Hybrid");
                return null;
        }
    }
}