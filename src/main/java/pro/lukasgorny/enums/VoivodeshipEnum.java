package pro.lukasgorny.enums;

/**
 * Created by Łukasz on 04.02.2018.
 */
public enum VoivodeshipEnum {
    ZACHODNIO_POMORSKIE("Zachodnio-pomorskie"),
    POMORSKIE("Pomorskie"),
    WARMINSKO_MAZURSKIE("Warmińsko-mazurskie"),
    PODLASKIE("Podlaskie"),
    LUBUSKIE("Lubuskie"),
    WIELKOPOLSKIE("Wielkopolskie"),
    KUJAWSKO_POMORSKIE("Kujawsko-pomorskie"),
    MAZOWIECKIE("Mazowieckie"),
    DOLNOSLASKIE("Dolnośląskie"),
    LODZKIE("Łódzkie"),
    LUBELSKIE("Lubelskie"),
    OPOLSKIE("Opolskie"),
    SLASKIE("Śląśkie"),
    SWIETOKRZYSKIE("Świętokrzyskie"),
    MALOPOLSKIE("Małopolskie"),
    PODKARPACKIE("Podkarpackie");

    private String name;

    VoivodeshipEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
