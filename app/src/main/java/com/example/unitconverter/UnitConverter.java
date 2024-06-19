package com.example.unitconverter;

public class UnitConverter {

    public static double convertToMeters(String unit, double value) {
        switch (unit) {
            case "Kilometer":
                return value * 1000;
            case "Meter":
                return value;
            case "Centimeter":
                return value * 0.01;
            case "Millimeter":
                return value * 0.001;
            case "Micrometer":
                return value * 0.000001;
            case "Nanometer":
                return value * 0.000000001;
            case "Yard":
                return value * 0.9144;
            case "Foot":
                return value * 0.3048;
            case "Inch":
                return value * 0.0254;
            case "Nautical Mile":
                return value * 1852;
            default:
                throw new IllegalArgumentException("Unknown unit: " + unit);
        }
    }

    public static double convertFromMeters(String unit, double value) {
        switch (unit) {
            case "Kilometer":
                return value / 1000;
            case "Meter":
                return value;
            case "Centimeter":
                return value / 0.01;
            case "Millimeter":
                return value / 0.001;
            case "Micrometer":
                return value / 0.000001;
            case "Nanometer":
                return value / 0.000000001;
            case "Yard":
                return value / 0.9144;
            case "Foot":
                return value / 0.3048;
            case "Inch":
                return value / 0.0254;
            case "Nautical Mile":
                return value / 1852;
            default:
                throw new IllegalArgumentException("Unknown unit: " + unit);
        }
    }

    public static double performConversion(String fromUnit, String toUnit, double inputValue) {
        double valueInMeters = convertToMeters(fromUnit, inputValue);
        return convertFromMeters(toUnit, valueInMeters);
    }

}
