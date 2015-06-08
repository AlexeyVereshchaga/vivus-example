package com.Cadient.QMobile.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 05.11.2014.
 */
public enum Unit {
    NONE(1, "", 1f),
    OUNCE(2, "Ounce", 0.02834952f), TEASPOON(3, "Teaspoon", 0.004928922f), TABLESPOON(4, "Tablespoon", 0.01478676f),
    EACH(5, "Each", 1f), CUP(6, "Cup", 0.2365882f), PIECE(7, "Piece", 1f), POUND(8, "Pound", 0.4535924f), MILLIGRAM(9, "mg", 0.000001f),
    GRAM(10, "g", 0.001f), PERCENT(11, "%", 1f), IU(12, "IU", 1f), MICROGRAM(13, "Microgram", 0.000000001f), QUART(14, "Quart", 0.946353f),
    GALLON(15, "Gallon", 3.785412f), FLUID_OUNCE(16, "Fluid ounce", .02957353f), LINK(17, "Link", 1f), SERVING(18, "Serving", 1f),
    GRAM_DONT_USE(19, "Gram", 0.001f), PACKAGE(20, "Package", 1f), CAN_JAR(22, "can/jar", 1f),
    SLICE(23, "Slice", 1f), KILOGRAM(24, "Kilogram", 1f), MILLILITER(25, "Milliliter", 0.001f), LITER(26, "Liter", 1f),
    PINT(27, "Pint", 0.4731765f), DRY_TEASPOON(28, "Teaspoon", 0.005735293f), DRY_TABLESPOON(29, "Tablespoon", 0.01720588f),
    DRY_CUP(30, "Dry Cup", 0.2752941f), DRY_PINT(31, "Dry Pint", 0.5505881f), DRY_QUART(32, "Dry Quart", 1.101176f),
    SCOOP(33, "Scoop", 1f), HALF_GALLON(34, "Half Gallon", 1.892706f), APPETIZER(42, "appetizer", 1f), BAG(52, "bag", 1f),
    BARREL(62, "barrel", 1f), BOTTLE(72, "bottle", 1f), BOWL(82, "bowl", 1f), BOX(92, "box", 1f), CAN(102, "can", 1f),
    CARTON(112, "carton", 1f), CASE(122, "case", 1f), CONTAINER(132, "container", 1f), CUBE(142, "cube", 1f),
    CUBIC_INCH(152, "cubic inch", 1f), DOZEN(162, "dozen", 1f), DROP(172, "drop", 1f), DRY_SERVING(182, "dry serving", 1f),
    ENTREE(192, "entree", 1f), EXTRA_LARGE(202, "extra large", 1f), FAMILY(212, "family", 1f),
    INDIVIDUAL(222, "individual", 1f), INDIVIDUAL_BAG(232, "individual bag", 1f),
    INDIVIDUAL_CUP(242, "individual cup", 1f), INDIVIDUAL_PACKAGE(252, "individual package", 1f),
    INDIVIDUAL_PACKET(262, "individual packet", 1f), JAR(272, "jar", 1f), JUMBO(282, "jumbo", 1f),
    KIDS(292, "kids", 1f), LARGE(302, "large", 1f), MEAL(312, "meal", 1f), MEDIUM(322, "medium", 1f), MINI(332, "mini", 1f),
    ORDER(342, "order", 1f), PAT(352, "pat", 1f), PATTY(362, "patty", 1f), PORTION_CUP(372, "portion cup", 1f),
    POUCH(382, "pouch", 1f), REGULAR(392, "regular", 1f), SACK(402, "sack", 1f), SECOND_SPRAY(412, "second spray", 1f),
    SHEET(422, "sheet", 1f), SIDE(432, "side", 1f), SMALL(442, "small", 1f), STICK(452, "stick", 1f),
    THICK_SLICE(462, "thick slice", 1f), THIN_SLICE(472, "thin slice", 1f), TOPPING(482, "topping", 1f), WHOLE(492, "whole", 1f);

    private static Map<Integer, String> mapNameById;
    private static Map<Integer, Float> mapCoefficientById;
    private int id;
    private String name;
    private Float coefficient;

    Unit(int id, String name, Float coefficient) {
        this.id = id;
        this.name = name;
        this.coefficient = coefficient;
    }

    public static String getNameById(Integer id) {
        if (mapNameById == null) {
            initializeMappingNameById();
        }
        if (mapNameById.containsKey(id)) {
            return mapNameById.get(id);
        }
        return "Unit" + Integer.toString(id);
    }

    public static Float getCoefficientById(Integer id) {
        if (mapCoefficientById == null) {
            initializeMappingCoefficientById();
        }
        if (mapCoefficientById.containsKey(id)) {
            return mapCoefficientById.get(id);
        }
        return 1f;
    }

    private static void initializeMappingNameById() {
        mapNameById = new HashMap<Integer, String>();
        for (Unit unit : Unit.values()) {
            mapNameById.put(unit.id, unit.name);
        }
    }

    private static void initializeMappingCoefficientById() {
        mapCoefficientById = new HashMap<Integer, Float>();
        for (Unit unit : Unit.values()) {
            mapCoefficientById.put(unit.id, unit.coefficient);
        }
    }
}
